/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh.util;

import bsh.Interpreter;
import bsh.NameSpace;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

class SessiondConnection
extends Thread {
    NameSpace globalNameSpace;
    Socket client;

    SessiondConnection(NameSpace globalNameSpace, Socket client) {
        this.client = client;
        this.globalNameSpace = globalNameSpace;
    }

    public void run() {
        try {
            InputStream in = this.client.getInputStream();
            PrintStream out = new PrintStream(this.client.getOutputStream());
            Interpreter i = new Interpreter(new InputStreamReader(in), out, out, true, this.globalNameSpace);
            i.setExitOnEOF(false);
            i.run();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

