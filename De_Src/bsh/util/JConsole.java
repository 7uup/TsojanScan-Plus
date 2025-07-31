/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh.util;

import bsh.util.GUIConsoleInterface;
import bsh.util.NameCompletion;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.util.Vector;
import javax.swing.Icon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class JConsole
extends JScrollPane
implements GUIConsoleInterface,
Runnable,
KeyListener,
MouseListener,
ActionListener,
PropertyChangeListener {
    private static final String CUT = "Cut";
    private static final String COPY = "Copy";
    private static final String PASTE = "Paste";
    private OutputStream outPipe;
    private InputStream inPipe;
    private InputStream in;
    private PrintStream out;
    private int cmdStart = 0;
    private Vector history = new Vector();
    private String startedLine;
    private int histLine = 0;
    private JPopupMenu menu;
    private JTextPane text;
    private DefaultStyledDocument doc = new DefaultStyledDocument();
    NameCompletion nameCompletion;
    final int SHOW_AMBIG_MAX = 10;
    private boolean gotUp = true;
    String ZEROS = "000";

    public InputStream getInputStream() {
        return this.in;
    }

    public Reader getIn() {
        return new InputStreamReader(this.in);
    }

    public PrintStream getOut() {
        return this.out;
    }

    public PrintStream getErr() {
        return this.out;
    }

    public JConsole() {
        this(null, null);
    }

    public JConsole(InputStream cin, OutputStream cout) {
        this.text = new JTextPane(this.doc){

            public void cut() {
                if (JConsole.this.text.getCaretPosition() < JConsole.this.cmdStart) {
                    super.copy();
                } else {
                    super.cut();
                }
            }

            public void paste() {
                JConsole.this.forceCaretMoveToEnd();
                super.paste();
            }
        };
        Font font = new Font("Monospaced", 0, 14);
        this.text.setText("");
        this.text.setFont(font);
        this.text.setMargin(new Insets(7, 5, 7, 5));
        this.text.addKeyListener(this);
        this.setViewportView(this.text);
        this.menu = new JPopupMenu("JConsole\tMenu");
        this.menu.add(new JMenuItem(CUT)).addActionListener(this);
        this.menu.add(new JMenuItem(COPY)).addActionListener(this);
        this.menu.add(new JMenuItem(PASTE)).addActionListener(this);
        this.text.addMouseListener(this);
        UIManager.addPropertyChangeListener(this);
        this.outPipe = cout;
        if (this.outPipe == null) {
            this.outPipe = new PipedOutputStream();
            try {
                this.in = new PipedInputStream((PipedOutputStream)this.outPipe);
            } catch (IOException e) {
                this.print((Object)"Console internal\terror (1)...", Color.red);
            }
        }
        this.inPipe = cin;
        if (this.inPipe == null) {
            PipedOutputStream pout = new PipedOutputStream();
            this.out = new PrintStream(pout);
            try {
                this.inPipe = new BlockingPipedInputStream(pout);
            } catch (IOException e) {
                this.print("Console internal error: " + e);
            }
        }
        new Thread(this).start();
        this.requestFocus();
    }

    public void requestFocus() {
        super.requestFocus();
        this.text.requestFocus();
    }

    public void keyPressed(KeyEvent e) {
        this.type(e);
        this.gotUp = false;
    }

    public void keyTyped(KeyEvent e) {
        this.type(e);
    }

    public void keyReleased(KeyEvent e) {
        this.gotUp = true;
        this.type(e);
    }

    private synchronized void type(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 10: {
                if (e.getID() == 401 && this.gotUp) {
                    this.enter();
                    this.resetCommandStart();
                    this.text.setCaretPosition(this.cmdStart);
                }
                e.consume();
                this.text.repaint();
                break;
            }
            case 38: {
                if (e.getID() == 401) {
                    this.historyUp();
                }
                e.consume();
                break;
            }
            case 40: {
                if (e.getID() == 401) {
                    this.historyDown();
                }
                e.consume();
                break;
            }
            case 8: 
            case 37: 
            case 127: {
                if (this.text.getCaretPosition() > this.cmdStart) break;
                e.consume();
                break;
            }
            case 39: {
                this.forceCaretMoveToStart();
                break;
            }
            case 36: {
                this.text.setCaretPosition(this.cmdStart);
                e.consume();
                break;
            }
            case 85: {
                if ((e.getModifiers() & 2) <= 0) break;
                this.replaceRange("", this.cmdStart, this.textLength());
                this.histLine = 0;
                e.consume();
                break;
            }
            case 16: 
            case 17: 
            case 18: 
            case 19: 
            case 20: 
            case 27: 
            case 112: 
            case 113: 
            case 114: 
            case 115: 
            case 116: 
            case 117: 
            case 118: 
            case 119: 
            case 120: 
            case 121: 
            case 122: 
            case 123: 
            case 145: 
            case 154: 
            case 155: 
            case 157: {
                break;
            }
            case 67: {
                if (this.text.getSelectedText() != null) break;
                if ((e.getModifiers() & 2) > 0 && e.getID() == 401) {
                    this.append("^C");
                }
                e.consume();
                break;
            }
            case 9: {
                if (e.getID() == 402) {
                    String part = this.text.getText().substring(this.cmdStart);
                    this.doCommandCompletion(part);
                }
                e.consume();
                break;
            }
            default: {
                if ((e.getModifiers() & 0xE) == 0) {
                    this.forceCaretMoveToEnd();
                }
                if (e.paramString().indexOf("Backspace") == -1 || this.text.getCaretPosition() > this.cmdStart) break;
                e.consume();
            }
        }
    }

    private void doCommandCompletion(String part) {
        int i;
        if (this.nameCompletion == null) {
            return;
        }
        for (i = part.length() - 1; i >= 0 && (Character.isJavaIdentifierPart(part.charAt(i)) || part.charAt(i) == '.'); --i) {
        }
        if ((part = part.substring(i + 1)).length() < 2) {
            return;
        }
        String[] complete = this.nameCompletion.completeName(part);
        if (complete.length == 0) {
            Toolkit.getDefaultToolkit().beep();
            return;
        }
        if (complete.length == 1 && !complete.equals(part)) {
            String append = complete[0].substring(part.length());
            this.append(append);
            return;
        }
        String line = this.text.getText();
        String command = line.substring(this.cmdStart);
        for (i = this.cmdStart; line.charAt(i) != '\n' && i > 0; --i) {
        }
        String prompt = line.substring(i + 1, this.cmdStart);
        StringBuffer sb = new StringBuffer("\n");
        for (i = 0; i < complete.length && i < 10; ++i) {
            sb.append(complete[i] + "\n");
        }
        if (i == 10) {
            sb.append("...\n");
        }
        this.print((Object)sb, Color.gray);
        this.print(prompt);
        this.append(command);
    }

    private void resetCommandStart() {
        this.cmdStart = this.textLength();
    }

    private void append(String string) {
        int slen = this.textLength();
        this.text.select(slen, slen);
        this.text.replaceSelection(string);
    }

    private String replaceRange(Object s2, int start, int end) {
        String st = s2.toString();
        this.text.select(start, end);
        this.text.replaceSelection(st);
        return st;
    }

    private void forceCaretMoveToEnd() {
        if (this.text.getCaretPosition() < this.cmdStart) {
            this.text.setCaretPosition(this.textLength());
        }
        this.text.repaint();
    }

    private void forceCaretMoveToStart() {
        if (this.text.getCaretPosition() < this.cmdStart) {
            // empty if block
        }
        this.text.repaint();
    }

    private void enter() {
        String s2 = this.getCmd();
        if (s2.length() == 0) {
            s2 = ";\n";
        } else {
            this.history.addElement(s2);
            s2 = s2 + "\n";
        }
        this.append("\n");
        this.histLine = 0;
        this.acceptLine(s2);
        this.text.repaint();
    }

    private String getCmd() {
        String s2 = "";
        try {
            s2 = this.text.getText(this.cmdStart, this.textLength() - this.cmdStart);
        } catch (BadLocationException e) {
            System.out.println("Internal JConsole Error: " + e);
        }
        return s2;
    }

    private void historyUp() {
        if (this.history.size() == 0) {
            return;
        }
        if (this.histLine == 0) {
            this.startedLine = this.getCmd();
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
        this.replaceRange(showline, this.cmdStart, this.textLength());
        this.text.setCaretPosition(this.textLength());
        this.text.repaint();
    }

    private void acceptLine(String line) {
        StringBuffer buf = new StringBuffer();
        int lineLength = line.length();
        for (int i = 0; i < lineLength; ++i) {
            String val = Integer.toString(line.charAt(i), 16);
            val = this.ZEROS.substring(0, 4 - val.length()) + val;
            buf.append("\\u" + val);
        }
        line = buf.toString();
        if (this.outPipe == null) {
            this.print((Object)"Console internal\terror: cannot output ...", Color.red);
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
        this.text.repaint();
    }

    public void print(final Object o) {
        this.invokeAndWait(new Runnable(){

            public void run() {
                JConsole.this.append(String.valueOf(o));
                JConsole.this.resetCommandStart();
                JConsole.this.text.setCaretPosition(JConsole.this.cmdStart);
            }
        });
    }

    public void println() {
        this.print("\n");
        this.text.repaint();
    }

    public void error(Object o) {
        this.print(o, Color.red);
    }

    public void println(Icon icon) {
        this.print(icon);
        this.println();
        this.text.repaint();
    }

    public void print(final Icon icon) {
        if (icon == null) {
            return;
        }
        this.invokeAndWait(new Runnable(){

            public void run() {
                JConsole.this.text.insertIcon(icon);
                JConsole.this.resetCommandStart();
                JConsole.this.text.setCaretPosition(JConsole.this.cmdStart);
            }
        });
    }

    public void print(Object s2, Font font) {
        this.print(s2, font, null);
    }

    public void print(Object s2, Color color) {
        this.print(s2, null, color);
    }

    public void print(final Object o, final Font font, final Color color) {
        this.invokeAndWait(new Runnable(){

            public void run() {
                AttributeSet old = JConsole.this.getStyle();
                JConsole.this.setStyle(font, color);
                JConsole.this.append(String.valueOf(o));
                JConsole.this.resetCommandStart();
                JConsole.this.text.setCaretPosition(JConsole.this.cmdStart);
                JConsole.this.setStyle(old, true);
            }
        });
    }

    public void print(Object s2, String fontFamilyName, int size, Color color) {
        this.print(s2, fontFamilyName, size, color, false, false, false);
    }

    public void print(final Object o, final String fontFamilyName, final int size, final Color color, final boolean bold, final boolean italic, final boolean underline) {
        this.invokeAndWait(new Runnable(){

            public void run() {
                AttributeSet old = JConsole.this.getStyle();
                JConsole.this.setStyle(fontFamilyName, size, color, bold, italic, underline);
                JConsole.this.append(String.valueOf(o));
                JConsole.this.resetCommandStart();
                JConsole.this.text.setCaretPosition(JConsole.this.cmdStart);
                JConsole.this.setStyle(old, true);
            }
        });
    }

    private AttributeSet setStyle(Font font) {
        return this.setStyle(font, null);
    }

    private AttributeSet setStyle(Color color) {
        return this.setStyle(null, color);
    }

    private AttributeSet setStyle(Font font, Color color) {
        if (font != null) {
            return this.setStyle(font.getFamily(), font.getSize(), color, font.isBold(), font.isItalic(), StyleConstants.isUnderline(this.getStyle()));
        }
        return this.setStyle(null, -1, color);
    }

    private AttributeSet setStyle(String fontFamilyName, int size, Color color) {
        SimpleAttributeSet attr = new SimpleAttributeSet();
        if (color != null) {
            StyleConstants.setForeground(attr, color);
        }
        if (fontFamilyName != null) {
            StyleConstants.setFontFamily(attr, fontFamilyName);
        }
        if (size != -1) {
            StyleConstants.setFontSize(attr, size);
        }
        this.setStyle(attr);
        return this.getStyle();
    }

    private AttributeSet setStyle(String fontFamilyName, int size, Color color, boolean bold, boolean italic, boolean underline) {
        SimpleAttributeSet attr = new SimpleAttributeSet();
        if (color != null) {
            StyleConstants.setForeground(attr, color);
        }
        if (fontFamilyName != null) {
            StyleConstants.setFontFamily(attr, fontFamilyName);
        }
        if (size != -1) {
            StyleConstants.setFontSize(attr, size);
        }
        StyleConstants.setBold(attr, bold);
        StyleConstants.setItalic(attr, italic);
        StyleConstants.setUnderline(attr, underline);
        this.setStyle(attr);
        return this.getStyle();
    }

    private void setStyle(AttributeSet attributes) {
        this.setStyle(attributes, false);
    }

    private void setStyle(AttributeSet attributes, boolean overWrite) {
        this.text.setCharacterAttributes(attributes, overWrite);
    }

    private AttributeSet getStyle() {
        return this.text.getCharacterAttributes();
    }

    public void setFont(Font font) {
        super.setFont(font);
        if (this.text != null) {
            this.text.setFont(font);
        }
    }

    private void inPipeWatcher() throws IOException {
        int read;
        byte[] ba = new byte[256];
        while ((read = this.inPipe.read(ba)) != -1) {
            this.print(new String(ba, 0, read));
        }
        this.println("Console: Input\tclosed...");
    }

    public void run() {
        try {
            this.inPipeWatcher();
        } catch (IOException e) {
            this.print((Object)("Console: I/O Error: " + e + "\n"), Color.red);
        }
    }

    public String toString() {
        return "BeanShell console";
    }

    public void mouseClicked(MouseEvent event) {
    }

    public void mousePressed(MouseEvent event) {
        if (event.isPopupTrigger()) {
            this.menu.show((Component)event.getSource(), event.getX(), event.getY());
        }
    }

    public void mouseReleased(MouseEvent event) {
        if (event.isPopupTrigger()) {
            this.menu.show((Component)event.getSource(), event.getX(), event.getY());
        }
        this.text.repaint();
    }

    public void mouseEntered(MouseEvent event) {
    }

    public void mouseExited(MouseEvent event) {
    }

    public void propertyChange(PropertyChangeEvent event) {
        if (event.getPropertyName().equals("lookAndFeel")) {
            SwingUtilities.updateComponentTreeUI(this.menu);
        }
    }

    public void actionPerformed(ActionEvent event) {
        String cmd = event.getActionCommand();
        if (cmd.equals(CUT)) {
            this.text.cut();
        } else if (cmd.equals(COPY)) {
            this.text.copy();
        } else if (cmd.equals(PASTE)) {
            this.text.paste();
        }
    }

    private void invokeAndWait(Runnable run) {
        if (!SwingUtilities.isEventDispatchThread()) {
            try {
                SwingUtilities.invokeAndWait(run);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            run.run();
        }
    }

    public void setNameCompletion(NameCompletion nc) {
        this.nameCompletion = nc;
    }

    public void setWaitFeedback(boolean on) {
        if (on) {
            this.setCursor(Cursor.getPredefinedCursor(3));
        } else {
            this.setCursor(Cursor.getPredefinedCursor(0));
        }
    }

    private int textLength() {
        return this.text.getDocument().getLength();
    }

    public static class BlockingPipedInputStream
    extends PipedInputStream {
        boolean closed;

        public BlockingPipedInputStream(PipedOutputStream pout) throws IOException {
            super(pout);
        }

        public synchronized int read() throws IOException {
            if (this.closed) {
                throw new IOException("stream closed");
            }
            while (this.in < 0) {
                this.notifyAll();
                try {
                    this.wait(750L);
                } catch (InterruptedException e) {
                    throw new InterruptedIOException();
                }
            }
            int ret = this.buffer[this.out++] & 0xFF;
            if (this.out >= this.buffer.length) {
                this.out = 0;
            }
            if (this.in == this.out) {
                this.in = -1;
            }
            return ret;
        }

        public void close() throws IOException {
            this.closed = true;
            super.close();
        }
    }
}

