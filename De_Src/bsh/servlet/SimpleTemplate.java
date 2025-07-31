/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh.servlet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SimpleTemplate {
    StringBuffer buff;
    static String NO_TEMPLATE = "NO_TEMPLATE";
    static Map templateData = new HashMap();
    static boolean cacheTemplates = true;

    public static SimpleTemplate getTemplate(String file) {
        String templateText = (String)templateData.get(file);
        if (templateText == null || !cacheTemplates) {
            try {
                FileReader fr = new FileReader(file);
                templateText = SimpleTemplate.getStringFromStream(fr);
                templateData.put(file, templateText);
            } catch (IOException e) {
                templateData.put(file, NO_TEMPLATE);
            }
        } else if (templateText.equals(NO_TEMPLATE)) {
            return null;
        }
        if (templateText == null) {
            return null;
        }
        return new SimpleTemplate(templateText);
    }

    public static String getStringFromStream(InputStream ins) throws IOException {
        return SimpleTemplate.getStringFromStream(new InputStreamReader(ins));
    }

    public static String getStringFromStream(Reader reader) throws IOException {
        String line;
        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(reader);
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        return sb.toString();
    }

    public SimpleTemplate(String template) {
        this.init(template);
    }

    public SimpleTemplate(Reader reader) throws IOException {
        String template = SimpleTemplate.getStringFromStream(reader);
        this.init(template);
    }

    public SimpleTemplate(URL url) throws IOException {
        String template = SimpleTemplate.getStringFromStream(url.openStream());
        this.init(template);
    }

    private void init(String s2) {
        this.buff = new StringBuffer(s2);
    }

    public void replace(String param, String value) {
        int[] range;
        while ((range = this.findTemplate(param)) != null) {
            this.buff.replace(range[0], range[1], value);
        }
    }

    int[] findTemplate(String name) {
        String text = this.buff.toString();
        int len = text.length();
        int start = 0;
        while (start < len) {
            char c;
            int cstart = text.indexOf("<!--", start);
            if (cstart == -1) {
                return null;
            }
            int cend = text.indexOf("-->", cstart);
            if (cend == -1) {
                return null;
            }
            cend += "-->".length();
            int tstart = text.indexOf("TEMPLATE-", cstart);
            if (tstart == -1) {
                start = cend;
                continue;
            }
            if (tstart > cend) {
                start = cend;
                continue;
            }
            int pstart = tstart + "TEMPLATE-".length();
            int pend = len;
            for (pend = pstart; pend < len && (c = text.charAt(pend)) != ' ' && c != '\t' && c != '-'; ++pend) {
            }
            if (pend >= len) {
                return null;
            }
            String param = text.substring(pstart, pend);
            if (param.equals(name)) {
                return new int[]{cstart, cend};
            }
            start = cend;
        }
        return null;
    }

    public String toString() {
        return this.buff.toString();
    }

    public void write(PrintWriter out) {
        out.println(this.toString());
    }

    public void write(PrintStream out) {
        out.println(this.toString());
    }

    public static void main(String[] args2) throws IOException {
        String filename = args2[0];
        String param = args2[1];
        String value = args2[2];
        FileReader fr = new FileReader(filename);
        String templateText = SimpleTemplate.getStringFromStream(fr);
        SimpleTemplate template = new SimpleTemplate(templateText);
        template.replace(param, value);
        template.write(System.out);
    }

    public static void setCacheTemplates(boolean b) {
        cacheTemplates = b;
    }
}

