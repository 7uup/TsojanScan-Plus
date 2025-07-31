/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc;

class EscapeProcessorResult {
    boolean callingStoredFunction = false;
    String escapedSql;
    byte usesVariables = 0;

    EscapeProcessorResult() {
    }
}

