/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh.engine;

import bsh.engine.BshScriptEngine;
import java.util.Arrays;
import java.util.List;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class BshScriptEngineFactory
implements ScriptEngineFactory {
    final List<String> extensions = Arrays.asList("bsh", "java");
    final List<String> mimeTypes = Arrays.asList("application/x-beanshell", "application/x-bsh", "application/x-java-source");
    final List<String> names = Arrays.asList("beanshell", "bsh", "java");

    @Override
    public String getEngineName() {
        return "BeanShell Engine";
    }

    @Override
    public String getEngineVersion() {
        return "1.0";
    }

    @Override
    public List<String> getExtensions() {
        return this.extensions;
    }

    @Override
    public List<String> getMimeTypes() {
        return this.mimeTypes;
    }

    @Override
    public List<String> getNames() {
        return this.names;
    }

    @Override
    public String getLanguageName() {
        return "BeanShell";
    }

    @Override
    public String getLanguageVersion() {
        return "2.0b5";
    }

    @Override
    public Object getParameter(String param) {
        if (param.equals("javax.script.engine")) {
            return this.getEngineName();
        }
        if (param.equals("javax.script.engine_version")) {
            return this.getEngineVersion();
        }
        if (param.equals("javax.script.name")) {
            return this.getEngineName();
        }
        if (param.equals("javax.script.language")) {
            return this.getLanguageName();
        }
        if (param.equals("javax.script.language_version")) {
            return this.getLanguageVersion();
        }
        if (param.equals("THREADING")) {
            return "MULTITHREADED";
        }
        return null;
    }

    @Override
    public String getMethodCallSyntax(String objectName, String methodName, String ... args2) {
        StringBuffer sb = new StringBuffer();
        if (objectName != null) {
            sb.append(objectName + ".");
        }
        sb.append(methodName + "(");
        if (args2.length > 0) {
            sb.append(" ");
        }
        for (int i = 0; i < args2.length; ++i) {
            sb.append((args2[i] == null ? "null" : args2[i]) + (i < args2.length - 1 ? ", " : " "));
        }
        sb.append(")");
        return sb.toString();
    }

    @Override
    public String getOutputStatement(String message) {
        return "print( \"" + message + "\" );";
    }

    @Override
    public String getProgram(String ... statements) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < statements.length; ++i) {
            sb.append(statements[i]);
            if (!statements[i].endsWith(";")) {
                sb.append(";");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public ScriptEngine getScriptEngine() {
        return new BshScriptEngine();
    }
}

