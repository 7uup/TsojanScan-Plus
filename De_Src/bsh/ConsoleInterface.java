/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import java.io.PrintStream;
import java.io.Reader;

public interface ConsoleInterface {
    public Reader getIn();

    public PrintStream getOut();

    public PrintStream getErr();

    public void println(Object var1);

    public void print(Object var1);

    public void error(Object var1);
}

