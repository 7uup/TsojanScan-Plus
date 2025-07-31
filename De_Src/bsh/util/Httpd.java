/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh.util;

import bsh.util.HttpdConnection;
import java.io.IOException;
import java.net.ServerSocket;

public class Httpd
extends Thread {
    ServerSocket ss;

    public static void main(String[] argv) throws IOException {
        new Httpd(Integer.parseInt(argv[0])).start();
    }

    public Httpd(int port) throws IOException {
        this.ss = new ServerSocket(port);
    }

    public void run() {
        try {
            while (true) {
                new HttpdConnection(this.ss.accept()).start();
            }
        } catch (IOException e) {
            System.out.println(e);
            return;
        }
    }
}

