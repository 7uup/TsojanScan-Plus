/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh.commands;

import bsh.CallStack;
import bsh.Interpreter;
import bsh.StringUtil;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

public class dir {
    static final String[] months = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    public static String usage() {
        return "usage: dir( String dir )\n       dir()";
    }

    public static void invoke(Interpreter env, CallStack callstack) {
        String dir2 = ".";
        dir.invoke(env, callstack, dir2);
    }

    public static void invoke(Interpreter env, CallStack callstack, String dir2) {
        File file;
        String path;
        try {
            path = env.pathToFile(dir2).getAbsolutePath();
            file = env.pathToFile(dir2);
        } catch (IOException e) {
            env.println("error reading path: " + e);
            return;
        }
        if (!file.exists() || !file.canRead()) {
            env.println("Can't read " + file);
            return;
        }
        if (!file.isDirectory()) {
            env.println("'" + dir2 + "' is not a directory");
        }
        String[] files = file.list();
        files = StringUtil.bubbleSort(files);
        for (int i = 0; i < files.length; ++i) {
            File f = new File(path + File.separator + files[i]);
            StringBuffer sb = new StringBuffer();
            sb.append(f.canRead() ? "r" : "-");
            sb.append(f.canWrite() ? "w" : "-");
            sb.append("_");
            sb.append(" ");
            Date d = new Date(f.lastModified());
            GregorianCalendar c = new GregorianCalendar();
            c.setTime(d);
            int day = c.get(5);
            sb.append(months[c.get(2)] + " " + day);
            if (day < 10) {
                sb.append(" ");
            }
            sb.append(" ");
            int fieldlen = 8;
            StringBuffer len = new StringBuffer();
            for (int j = 0; j < fieldlen; ++j) {
                len.append(" ");
            }
            len.insert(0, f.length());
            len.setLength(fieldlen);
            int si = len.toString().indexOf(" ");
            if (si != -1) {
                String pad = len.toString().substring(si);
                len.setLength(si);
                len.insert(0, pad);
            }
            sb.append(len.toString());
            sb.append(" " + f.getName());
            if (f.isDirectory()) {
                sb.append("/");
            }
            env.println(sb.toString());
        }
    }
}

