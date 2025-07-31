/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

public interface FilterParams {
    public Object getCollection();

    public Object getOrder();

    public void setOrder(String ... var1);

    public Long getLimit();

    public void setLimit(Long var1);

    public Long getOffset();

    public void setOffset(Long var1);

    public Object getCriteria();

    public void setCriteria(String var1);

    public Object getArgs();

    public void addArg(String var1, Object var2);

    public void verifyAllArgsBound();

    public void clearArgs();

    public boolean isRelational();

    public void setFields(String ... var1);

    public Object getFields();

    public void setGrouping(String ... var1);

    public Object getGrouping();

    public void setGroupingCriteria(String var1);

    public Object getGroupingCriteria();

    public RowLock getLock();

    public void setLock(RowLock var1);

    public RowLockOptions getLockOption();

    public void setLockOption(RowLockOptions var1);

    public static enum RowLockOptions {
        NOWAIT(1),
        SKIP_LOCKED(2);

        private int rowLockOption;

        private RowLockOptions(int rowLockOption) {
            this.rowLockOption = rowLockOption;
        }

        public int asNumber() {
            return this.rowLockOption;
        }
    }

    public static enum RowLock {
        SHARED_LOCK(1),
        EXCLUSIVE_LOCK(2);

        private int rowLock;

        private RowLock(int rowLock) {
            this.rowLock = rowLock;
        }

        public int asNumber() {
            return this.rowLock;
        }
    }
}

