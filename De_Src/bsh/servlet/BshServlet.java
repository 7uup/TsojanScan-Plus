/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  javax.servlet.ServletException
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 */
package bsh.servlet;

import bsh.EvalError;
import bsh.Interpreter;
import bsh.servlet.SimpleTemplate;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BshServlet
extends HttpServlet {
    static String bshVersion;
    static String exampleScript;

    static String getBshVersion() {
        if (bshVersion != null) {
            return bshVersion;
        }
        Interpreter bsh = new Interpreter();
        try {
            bsh.eval(new InputStreamReader(BshServlet.class.getResource("getVersion.bsh").openStream()));
            bshVersion = (String)bsh.eval("getVersion()");
        } catch (Exception e) {
            bshVersion = "BeanShell: unknown version";
        }
        return bshVersion;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String script = request.getParameter("bsh.script");
        String client = request.getParameter("bsh.client");
        String output = request.getParameter("bsh.servlet.output");
        String captureOutErr = request.getParameter("bsh.servlet.captureOutErr");
        boolean capture = false;
        if (captureOutErr != null && captureOutErr.equalsIgnoreCase("true")) {
            capture = true;
        }
        Object scriptResult = null;
        Exception scriptError = null;
        StringBuffer scriptOutput = new StringBuffer();
        if (script != null) {
            try {
                scriptResult = this.evalScript(script, scriptOutput, capture, request, response);
            } catch (Exception e) {
                scriptError = e;
            }
        }
        response.setHeader("Bsh-Return", String.valueOf(scriptResult));
        if (output != null && output.equalsIgnoreCase("raw") || client != null && client.equals("Remote")) {
            this.sendRaw(request, response, scriptError, scriptResult, scriptOutput);
        } else {
            this.sendHTML(request, response, script, scriptError, scriptResult, scriptOutput, capture);
        }
    }

    void sendHTML(HttpServletRequest request, HttpServletResponse response, String script, Exception scriptError, Object scriptResult, StringBuffer scriptOutput, boolean capture) throws IOException {
        SimpleTemplate st = new SimpleTemplate(BshServlet.class.getResource("page.template"));
        st.replace("version", BshServlet.getBshVersion());
        String requestURI = request.getRequestURI();
        st.replace("servletURL", requestURI);
        if (script != null) {
            st.replace("script", script);
        } else {
            st.replace("script", exampleScript);
        }
        if (capture) {
            st.replace("captureOutErr", "CHECKED");
        } else {
            st.replace("captureOutErr", "");
        }
        if (script != null) {
            st.replace("scriptResult", this.formatScriptResultHTML(script, scriptResult, scriptError, scriptOutput));
        }
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        st.write(out);
        out.flush();
    }

    void sendRaw(HttpServletRequest request, HttpServletResponse response, Exception scriptError, Object scriptResult, StringBuffer scriptOutput) throws IOException {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        if (scriptError != null) {
            out.println("Script Error:\n" + scriptError);
        } else {
            out.println(scriptOutput.toString());
        }
        out.flush();
    }

    String formatScriptResultHTML(String script, Object result, Exception error, StringBuffer scriptOutput) throws IOException {
        SimpleTemplate tmplt;
        if (error != null) {
            String errString;
            tmplt = new SimpleTemplate(((Object)((Object)this)).getClass().getResource("error.template"));
            if (error instanceof EvalError) {
                int lineNo = ((EvalError)error).getErrorLineNumber();
                String msg = error.getMessage();
                int contextLines = 4;
                errString = BshServlet.escape(msg);
                if (lineNo > -1) {
                    errString = errString + "<hr>" + this.showScriptContextHTML(script, lineNo, contextLines);
                }
            } else {
                errString = BshServlet.escape(error.toString());
            }
            tmplt.replace("error", errString);
        } else {
            tmplt = new SimpleTemplate(((Object)((Object)this)).getClass().getResource("result.template"));
            tmplt.replace("value", BshServlet.escape(String.valueOf(result)));
            tmplt.replace("output", BshServlet.escape(scriptOutput.toString()));
        }
        return tmplt.toString();
    }

    String showScriptContextHTML(String s2, int lineNo, int context) {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(new StringReader(s2));
        int beginLine = Math.max(1, lineNo - context);
        int endLine = lineNo + context;
        for (int i = 1; i <= lineNo + context + 1; ++i) {
            String line;
            if (i < beginLine) {
                try {
                    br.readLine();
                    continue;
                } catch (IOException e) {
                    throw new RuntimeException(e.toString());
                }
            }
            if (i > endLine) break;
            try {
                line = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e.toString());
            }
            if (line == null) break;
            if (i == lineNo) {
                sb.append("<font color=\"red\">" + i + ": " + line + "</font><br/>");
                continue;
            }
            sb.append(i + ": " + line + "<br/>");
        }
        return sb.toString();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    Object evalScript(String script, StringBuffer scriptOutput, boolean captureOutErr, HttpServletRequest request, HttpServletResponse response) throws EvalError {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream pout = new PrintStream(baos);
        Interpreter bsh = new Interpreter(null, pout, pout, false);
        bsh.set("bsh.httpServletRequest", request);
        bsh.set("bsh.httpServletResponse", response);
        Object result = null;
        Object error = null;
        PrintStream sout = System.out;
        PrintStream serr = System.err;
        if (captureOutErr) {
            System.setOut(pout);
            System.setErr(pout);
        }
        try {
            result = bsh.eval(script);
        } finally {
            if (captureOutErr) {
                System.setOut(sout);
                System.setErr(serr);
            }
        }
        pout.flush();
        scriptOutput.append(baos.toString());
        return result;
    }

    public static String escape(String value) {
        String search = "&<>";
        String[] replace = new String[]{"&amp;", "&lt;", "&gt;"};
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < value.length(); ++i) {
            char c = value.charAt(i);
            int pos = search.indexOf(c);
            if (pos < 0) {
                buf.append(c);
                continue;
            }
            buf.append(replace[pos]);
        }
        return buf.toString();
    }

    static {
        exampleScript = "print(\"hello!\");";
    }
}

