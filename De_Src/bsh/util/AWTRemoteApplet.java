/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh.util;

import bsh.util.AWTConsole;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Label;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;

public class AWTRemoteApplet
extends Applet {
    OutputStream out;
    InputStream in;

    public void init() {
        this.setLayout(new BorderLayout());
        try {
            URL base = this.getDocumentBase();
            Socket s2 = new Socket(base.getHost(), base.getPort() + 1);
            this.out = s2.getOutputStream();
            this.in = s2.getInputStream();
        } catch (IOException e) {
            this.add("Center", new Label("Remote Connection Failed", 1));
            return;
        }
        AWTConsole console = new AWTConsole(this.in, this.out);
        this.add("Center", console);
    }
}

