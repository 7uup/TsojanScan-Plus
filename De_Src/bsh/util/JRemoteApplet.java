/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh.util;

import bsh.util.JConsole;
import java.awt.BorderLayout;
import java.awt.Label;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import javax.swing.JApplet;

public class JRemoteApplet
extends JApplet {
    OutputStream out;
    InputStream in;

    public void init() {
        this.getContentPane().setLayout(new BorderLayout());
        try {
            URL base = this.getDocumentBase();
            Socket s2 = new Socket(base.getHost(), base.getPort() + 1);
            this.out = s2.getOutputStream();
            this.in = s2.getInputStream();
        } catch (IOException e) {
            this.getContentPane().add("Center", new Label("Remote Connection Failed", 1));
            return;
        }
        JConsole console = new JConsole(this.in, this.out);
        this.getContentPane().add("Center", console);
    }
}

