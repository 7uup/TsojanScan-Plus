/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.x;

import com.mysql.cj.ServerVersion;
import com.mysql.cj.exceptions.CJOperationNotSupportedException;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.protocol.ServerCapabilities;
import com.mysql.cj.protocol.ServerSession;
import com.mysql.cj.protocol.x.XServerCapabilities;
import java.util.Map;
import java.util.TimeZone;

public class XServerSession
implements ServerSession {
    XServerCapabilities serverCapabilities = null;
    private long clientId = -1L;
    private TimeZone defaultTimeZone = TimeZone.getDefault();

    @Override
    public ServerCapabilities getCapabilities() {
        return this.serverCapabilities;
    }

    @Override
    public void setCapabilities(ServerCapabilities capabilities) {
        this.serverCapabilities = (XServerCapabilities)capabilities;
    }

    @Override
    public int getStatusFlags() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public void setStatusFlags(int statusFlags) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public void setStatusFlags(int statusFlags, boolean saveOldStatusFlags) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public int getOldStatusFlags() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public void setOldStatusFlags(int statusFlags) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public int getServerDefaultCollationIndex() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public void setServerDefaultCollationIndex(int serverDefaultCollationIndex) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public int getTransactionState() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public boolean inTransactionOnServer() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public boolean cursorExists() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public boolean isAutocommit() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public boolean hasMoreResults() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public boolean isLastRowSent() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public boolean noGoodIndexUsed() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public boolean noIndexUsed() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public boolean queryWasSlow() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public long getClientParam() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public void setClientParam(long clientParam) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public boolean useMultiResults() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public boolean isEOFDeprecated() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public boolean hasLongColumnInfo() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public void setHasLongColumnInfo(boolean hasLongColumnInfo) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public Map<String, String> getServerVariables() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public String getServerVariable(String name) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public int getServerVariable(String variableName, int fallbackValue) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public void setServerVariables(Map<String, String> serverVariables) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public boolean characterSetNamesMatches(String mysqlEncodingName) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public ServerVersion getServerVersion() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public boolean isVersion(ServerVersion version) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public String getServerDefaultCharset() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public String getErrorMessageEncoding() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public void setErrorMessageEncoding(String errorMessageEncoding) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public int getMaxBytesPerChar(String javaCharsetName) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public int getMaxBytesPerChar(Integer charsetIndex, String javaCharsetName) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public String getEncodingForIndex(int collationIndex) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public void configureCharacterSets() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public String getCharacterSetMetadata() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public void setCharacterSetMetadata(String characterSetMetadata) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public int getMetadataCollationIndex() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public void setMetadataCollationIndex(int metadataCollationIndex) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public String getCharacterSetResultsOnServer() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public void setCharacterSetResultsOnServer(String characterSetResultsOnServer) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public boolean isLowerCaseTableNames() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public boolean storesLowerCaseTableNames() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public boolean isQueryCacheEnabled() {
        return false;
    }

    @Override
    public boolean isNoBackslashEscapesSet() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public boolean useAnsiQuotedIdentifiers() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public long getThreadId() {
        return this.clientId;
    }

    @Override
    public void setThreadId(long threadId) {
        this.clientId = threadId;
    }

    @Override
    public boolean isAutoCommit() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public void setAutoCommit(boolean autoCommit) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public TimeZone getServerTimeZone() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public void setServerTimeZone(TimeZone serverTimeZone) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public TimeZone getDefaultTimeZone() {
        return this.defaultTimeZone;
    }

    @Override
    public void setDefaultTimeZone(TimeZone defaultTimeZone) {
        this.defaultTimeZone = defaultTimeZone;
    }
}

