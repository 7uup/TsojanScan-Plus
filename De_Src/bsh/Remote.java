/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLEncoder;

public class Remote {
    public static void main(String[] args2) throws Exception {
        if (args2.length < 2) {
            System.out.println("usage: Remote URL(http|bsh) file [ file ] ... ");
            System.exit(1);
        }
        String url = args2[0];
        String text = Remote.getFile(args2[1]);
        int ret = Remote.eval(url, text);
        System.exit(ret);
    }

    public static int eval(String url, String text) throws IOException {
        String returnValue = null;
        if (url.startsWith("http:")) {
            returnValue = Remote.doHttp(url, text);
        } else if (url.startsWith("bsh:")) {
            returnValue = Remote.doBsh(url, text);
        } else {
            throw new IOException("Unrecognized URL type.Scheme must be http:// or bsh://");
        }
        try {
            return Integer.parseInt(returnValue);
        } catch (Exception e) {
            return 0;
        }
    }

    static String doBsh(String url, String text) {
        String host = "";
        String port = "";
        String returnValue = "-1";
        String orgURL = url;
        try {
            url = url.substring(6);
            int index = url.indexOf(":");
            host = url.substring(0, index);
            port = url.substring(index + 1, url.length());
        } catch (Exception ex) {
            System.err.println("Bad URL: " + orgURL + ": " + ex);
            return returnValue;
        }
        try {
            String line;
            System.out.println("Connecting to host : " + host + " at port : " + port);
            Socket s2 = new Socket(host, Integer.parseInt(port) + 1);
            OutputStream out = s2.getOutputStream();
            InputStream in = s2.getInputStream();
            Remote.sendLine(text, out);
            BufferedReader bin = new BufferedReader(new InputStreamReader(in));
            while ((line = bin.readLine()) != null) {
                System.out.println(line);
            }
            returnValue = "1";
            return returnValue;
        } catch (Exception ex) {
            System.err.println("Error communicating with server: " + ex);
            return returnValue;
        }
    }

    private static void sendLine(String line, OutputStream outPipe) throws IOException {
        outPipe.write(line.getBytes());
        outPipe.flush();
    }

    static String doHttp(String postURL, String text) {
        String returnValue = null;
        StringBuffer sb = new StringBuffer();
        sb.append("bsh.client=Remote");
        sb.append("&bsh.script=");
        sb.append(URLEncoder.encode(text));
        String formData = sb.toString();
        try {
            String line;
            URL url = new URL(postURL);
            HttpURLConnection urlcon = (HttpURLConnection)url.openConnection();
            urlcon.setRequestMethod("POST");
            urlcon.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            urlcon.setDoOutput(true);
            urlcon.setDoInput(true);
            PrintWriter pout = new PrintWriter(new OutputStreamWriter(urlcon.getOutputStream(), "8859_1"), true);
            pout.print(formData);
            pout.flush();
            int rc = urlcon.getResponseCode();
            if (rc != 200) {
                System.out.println("Error, HTTP response: " + rc);
            }
            returnValue = urlcon.getHeaderField("Bsh-Return");
            BufferedReader bin = new BufferedReader(new InputStreamReader(urlcon.getInputStream()));
            while ((line = bin.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("Return Value: " + returnValue);
        } catch (MalformedURLException e) {
            System.out.println(e);
        } catch (IOException e2) {
            System.out.println(e2);
        }
        return returnValue;
    }

    static String getFile(String name) throws FileNotFoundException, IOException {
        String line;
        StringBuffer sb = new StringBuffer();
        BufferedReader bin = new BufferedReader(new FileReader(name));
        while ((line = bin.readLine()) != null) {
            sb.append(line).append("\n");
        }
        return sb.toString();
    }
}

