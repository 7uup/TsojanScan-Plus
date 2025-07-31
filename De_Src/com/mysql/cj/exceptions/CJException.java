/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.exceptions;

public class CJException
extends RuntimeException {
    private static final long serialVersionUID = -8618536991444733607L;
    protected String exceptionMessage;
    private String SQLState = "S1000";
    private int vendorCode = 0;
    private boolean isTransient = false;

    public CJException() {
    }

    public CJException(String message) {
        super(message);
    }

    public CJException(Throwable cause) {
        super(cause);
    }

    public CJException(String message, Throwable cause) {
        super(message, cause);
    }

    protected CJException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getSQLState() {
        return this.SQLState;
    }

    public void setSQLState(String sQLState) {
        this.SQLState = sQLState;
    }

    public int getVendorCode() {
        return this.vendorCode;
    }

    public void setVendorCode(int vendorCode) {
        this.vendorCode = vendorCode;
    }

    public boolean isTransient() {
        return this.isTransient;
    }

    public void setTransient(boolean isTransient) {
        this.isTransient = isTransient;
    }

    @Override
    public String getMessage() {
        return this.exceptionMessage != null ? this.exceptionMessage : super.getMessage();
    }

    public void appendMessage(String messageToAppend) {
        this.exceptionMessage = this.getMessage() + messageToAppend;
    }
}

