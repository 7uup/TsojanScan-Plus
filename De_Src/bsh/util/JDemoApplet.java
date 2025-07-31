/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh.util;

import bsh.EvalError;
import bsh.Interpreter;
import bsh.TargetError;
import bsh.util.JConsole;
import java.awt.BorderLayout;
import javax.swing.JApplet;

public class JDemoApplet
extends JApplet {
    public void init() {
        String type;
        String debug = this.getParameter("debug");
        if (debug != null && debug.equals("true")) {
            Interpreter.DEBUG = true;
        }
        if ((type = this.getParameter("type")) != null && type.equals("desktop")) {
            try {
                new Interpreter().eval("desktop()");
            } catch (TargetError te) {
                te.printStackTrace();
                System.out.println(te.getTarget());
                te.getTarget().printStackTrace();
            } catch (EvalError evalError) {
                System.out.println(evalError);
                evalError.printStackTrace();
            }
        } else {
            this.getContentPane().setLayout(new BorderLayout());
            JConsole console = new JConsole();
            this.getContentPane().add("Center", console);
            Interpreter interpreter = new Interpreter(console);
            new Thread(interpreter).start();
        }
    }
}

