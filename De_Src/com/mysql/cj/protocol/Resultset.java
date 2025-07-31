/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol;

import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.protocol.ProtocolEntity;
import com.mysql.cj.protocol.ResultsetRows;

public interface Resultset
extends ProtocolEntity {
    public void setColumnDefinition(ColumnDefinition var1);

    public ColumnDefinition getColumnDefinition();

    public boolean hasRows();

    public ResultsetRows getRows();

    public void initRowsWithMetadata();

    public int getResultId();

    public void setNextResultset(Resultset var1);

    public Resultset getNextResultset();

    public void clearNextResultset();

    public long getUpdateCount();

    public long getUpdateID();

    public String getServerInfo();

    public static enum Type {
        FORWARD_ONLY(1003),
        SCROLL_INSENSITIVE(1004),
        SCROLL_SENSITIVE(1005);

        private int value;

        private Type(int jdbcRsType) {
            this.value = jdbcRsType;
        }

        public int getIntValue() {
            return this.value;
        }

        public static Type fromValue(int rsType, Type backupValue) {
            for (Type t : Type.values()) {
                if (t.getIntValue() != rsType) continue;
                return t;
            }
            return backupValue;
        }
    }

    public static enum Concurrency {
        READ_ONLY(1007),
        UPDATABLE(1008);

        private int value;

        private Concurrency(int jdbcRsConcur) {
            this.value = jdbcRsConcur;
        }

        public int getIntValue() {
            return this.value;
        }

        public static Concurrency fromValue(int concurMode, Concurrency backupValue) {
            for (Concurrency c : Concurrency.values()) {
                if (c.getIntValue() != concurMode) continue;
                return c;
            }
            return backupValue;
        }
    }
}

