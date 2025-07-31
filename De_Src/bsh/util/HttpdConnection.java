/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.StringTokenizer;

class HttpdConnection
extends Thread {
    Socket client;
    BufferedReader in;
    OutputStream out;
    PrintStream pout;
    boolean isHttp1;

    HttpdConnection(Socket client) {
        this.client = client;
        this.setPriority(4);
    }

    public void run() {
        try {
            StringTokenizer st;
            this.in = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
            this.out = this.client.getOutputStream();
            this.pout = new PrintStream(this.out);
            String request = this.in.readLine();
            if (request == null) {
                this.error(400, "Empty Request");
            }
            if (request.toLowerCase().indexOf("http/1.") != -1) {
                String s2;
                while (!(s2 = this.in.readLine()).equals("") && s2 != null) {
                }
                this.isHttp1 = true;
            }
            if ((st = new StringTokenizer(request)).countTokens() < 2) {
                this.error(400, "Bad Request");
            } else {
                String command = st.nextToken();
                if (command.equals("GET")) {
                    this.serveFile(st.nextToken());
                } else {
                    this.error(400, "Bad Request");
                }
            }
            this.client.close();
        } catch (IOException e) {
            System.out.println("I/O error " + e);
            try {
                this.client.close();
            } catch (Exception exception) {
                // empty catch block
            }
        }
    }

    private void serveFile(String file) throws FileNotFoundException, IOException {
        if (file.equals("/")) {
            file = "/remote/remote.html";
        }
        if (file.startsWith("/remote/")) {
            file = "/bsh/util/lib/" + file.substring(8);
        }
        if (file.startsWith("/java")) {
            this.error(404, "Object Not Found");
        } else {
            try {
                System.out.println("sending file: " + file);
                this.sendFileData(file);
            } catch (FileNotFoundException e) {
                this.error(404, "Object Not Found");
            }
        }
    }

    private void sendFileData(String file) throws IOException, FileNotFoundException {
        InputStream fis = this.getClass().getResourceAsStream(file);
        if (fis == null) {
            throw new FileNotFoundException(file);
        }
        byte[] data = new byte[fis.available()];
        if (this.isHttp1) {
            this.pout.println("HTTP/1.0 200 Document follows");
            this.pout.println("Content-length: " + data.length);
            if (file.endsWith(".gif")) {
                this.pout.println("Content-type: image/gif");
            } else if (file.endsWith(".html") || file.endsWith(".htm")) {
                this.pout.println("Content-Type: text/html");
            } else {
                this.pout.println("Content-Type: application/octet-stream");
            }
            this.pout.println();
        }
        int bytesread = 0;
        do {
            if ((bytesread = fis.read(data)) <= 0) continue;
            this.pout.write(data, 0, bytesread);
        } while (bytesread != -1);
        this.pout.flush();
    }

    private void error(int num, String s2) {
        s2 = "<html><h1>" + s2 + "</h1></html>";
        if (this.isHttp1) {
            this.pout.println("HTTP/1.0 " + num + " " + s2);
            this.pout.println("Content-type: text/html");
            this.pout.println("Content-length: " + s2.length() + "\n");
        }
        this.pout.println(s2);
    }
}

