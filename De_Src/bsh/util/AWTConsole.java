/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh.util;

import bsh.ConsoleInterface;
import bsh.Interpreter;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.peer.TextComponentPeer;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.util.Vector;

public class AWTConsole
extends TextArea
implements ConsoleInterface,
Runnable,
KeyListener {
    private OutputStream outPipe;
    private InputStream inPipe;
    private InputStream in;
    private PrintStream out;
    private StringBuffer line = new StringBuffer();
    private String startedLine;
    private int textLength = 0;
    private Vector history = new Vector();
    private int histLine = 0;

    public Reader getIn() {
        return new InputStreamReader(this.in);
    }

    public PrintStream getOut() {
        return this.out;
    }

    public PrintStream getErr() {
        return this.out;
    }

    public AWTConsole(int rows, int cols, InputStream cin, OutputStream cout) {
        super(rows, cols);
        this.setFont(new Font("Monospaced", 0, 14));
        this.setEditable(false);
        this.addKeyListener(this);
        this.outPipe = cout;
        if (this.outPipe == null) {
            this.outPipe = new PipedOutputStream();
            try {
                this.in = new PipedInputStream((PipedOutputStream)this.outPipe);
            } catch (IOException e) {
                this.print("Console internal error...");
            }
        }
        this.inPipe = cin;
        new Thread(this).start();
        this.requestFocus();
    }

    public void keyPressed(KeyEvent e) {
        this.type(e.getKeyCode(), e.getKeyChar(), e.getModifiers());
        e.consume();
    }

    public AWTConsole() {
        this(12, 80, null, null);
    }

    public AWTConsole(InputStream in, OutputStream out) {
        this(12, 80, in, out);
    }

    public void type(int code, char ch, int modifiers) {
        switch (code) {
            case 8: {
                if (this.line.length() <= 0) break;
                this.line.setLength(this.line.length() - 1);
                this.replaceRange("", this.textLength - 1, this.textLength);
                --this.textLength;
                break;
            }
            case 10: {
                this.enter();
                break;
            }
            case 85: {
                if ((modifiers & 2) > 0) {
                    int len = this.line.length();
                    this.replaceRange("", this.textLength - len, this.textLength);
                    this.line.setLength(0);
                    this.histLine = 0;
                    this.textLength = this.getText().length();
                    break;
                }
                this.doChar(ch);
                break;
            }
            case 38: {
                this.historyUp();
                break;
            }
            case 40: {
                this.historyDown();
                break;
            }
            case 9: {
                this.line.append("    ");
                this.append("    ");
                this.textLength += 4;
                break;
            }
            case 67: {
                if ((modifiers & 2) > 0) {
                    this.line.append("^C");
                    this.append("^C");
                    this.textLength += 2;
                    break;
                }
                this.doChar(ch);
                break;
            }
            default: {
                this.doChar(ch);
            }
        }
    }

    private void doChar(char ch) {
        if (ch >= ' ' && ch <= '~') {
            this.line.append(ch);
            this.append(String.valueOf(ch));
            ++this.textLength;
        }
    }

    private void enter() {
        String s2;
        if (this.line.length() == 0) {
            s2 = ";\n";
        } else {
            s2 = this.line + "\n";
            this.history.addElement(this.line.toString());
        }
        this.line.setLength(0);
        this.histLine = 0;
        this.append("\n");
        this.textLength = this.getText().length();
        this.acceptLine(s2);
        this.setCaretPosition(this.textLength);
    }

    public void setCaretPosition(int pos) {
        ((TextComponentPeer)this.getPeer()).setCaretPosition(pos + this.countNLs());
    }

    private int countNLs() {
        String s2 = this.getText();
        int c = 0;
        for (int i = 0; i < s2.length(); ++i) {
            if (s2.charAt(i) != '\n') continue;
            ++c;
        }
        return c;
    }

    private void historyUp() {
        if (this.history.size() == 0) {
            return;
        }
        if (this.histLine == 0) {
            this.startedLine = this.line.toString();
        }
        if (this.histLine < this.history.size()) {
            ++this.histLine;
            this.showHistoryLine();
        }
    }

    private void historyDown() {
        if (this.histLine == 0) {
            return;
        }
        --this.histLine;
        this.showHistoryLine();
    }

    private void showHistoryLine() {
        String showline = this.histLine == 0 ? this.startedLine : (String)this.history.elementAt(this.history.size() - this.histLine);
        this.replaceRange(showline, this.textLength - this.line.length(), this.textLength);
        this.line = new StringBuffer(showline);
        this.textLength = this.getText().length();
    }

    private void acceptLine(String line) {
        if (this.outPipe == null) {
            this.print("Console internal error...");
        } else {
            try {
                this.outPipe.write(line.getBytes());
                this.outPipe.flush();
            } catch (IOException e) {
                this.outPipe = null;
                throw new RuntimeException("Console pipe broken...");
            }
        }
    }

    public void println(Object o) {
        this.print(String.valueOf(o) + "\n");
    }

    public void error(Object o) {
        this.print(o, Color.red);
    }

    public void print(Object o, Color c) {
        this.print("*** " + String.valueOf(o));
    }

    public synchronized void print(Object o) {
        this.append(String.valueOf(o));
        this.textLength = this.getText().length();
    }

    private void inPipeWatcher() throws IOException {
        int read;
        if (this.inPipe == null) {
            PipedOutputStream pout = new PipedOutputStream();
            this.out = new PrintStream(pout);
            this.inPipe = new PipedInputStream(pout);
        }
        byte[] ba = new byte[256];
        while ((read = this.inPipe.read(ba)) != -1) {
            this.print(new String(ba, 0, read));
        }
        this.println("Console: Input closed...");
    }

    public void run() {
        try {
            this.inPipeWatcher();
        } catch (IOException e) {
            this.println("Console: I/O Error...");
        }
    }

    public static void main(String[] args2) {
        AWTConsole console = new AWTConsole();
        final Frame f = new Frame("Bsh Console");
        f.add((Component)console, "Center");
        f.pack();
        f.show();
        f.addWindowListener(new WindowAdapter(){

            public void windowClosing(WindowEvent e) {
                f.dispose();
            }
        });
        Interpreter interpreter = new Interpreter(console);
        interpreter.run();
    }

    public String toString() {
        return "BeanShell AWTConsole";
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
}

