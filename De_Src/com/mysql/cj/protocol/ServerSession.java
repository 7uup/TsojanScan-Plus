/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol;

import com.mysql.cj.ServerVersion;
import com.mysql.cj.protocol.ServerCapabilities;
import java.util.Map;
import java.util.TimeZone;

public interface ServerSession {
    public static final int TRANSACTION_NOT_STARTED = 0;
    public static final int TRANSACTION_IN_PROGRESS = 1;
    public static final int TRANSACTION_STARTED = 2;
    public static final int TRANSACTION_COMPLETED = 3;
    public static final String LOCAL_CHARACTER_SET_RESULTS = "local.character_set_results";

    public ServerCapabilities getCapabilities();

    public void setCapabilities(ServerCapabilities var1);

    public int getStatusFlags();

    public void setStatusFlags(int var1);

    public void setStatusFlags(int var1, boolean var2);

    public int getOldStatusFlags();

    public void setOldStatusFlags(int var1);

    public int getServerDefaultCollationIndex();

    public void setServerDefaultCollationIndex(int var1);

    public int getTransactionState();

    public boolean inTransactionOnServer();

    public boolean cursorExists();

    public boolean isAutocommit();

    public boolean hasMoreResults();

    public boolean isLastRowSent();

    public boolean noGoodIndexUsed();

    public boolean noIndexUsed();

    public boolean queryWasSlow();

    public long getClientParam();

    public void setClientParam(long var1);

    public boolean useMultiResults();

    public boolean isEOFDeprecated();

    public boolean hasLongColumnInfo();

    public void setHasLongColumnInfo(boolean var1);

    public Map<String, String> getServerVariables();

    public String getServerVariable(String var1);

    public int getServerVariable(String var1, int var2);

    public void setServerVariables(Map<String, String> var1);

    public boolean characterSetNamesMatches(String var1);

    public ServerVersion getServerVersion();

    public boolean isVersion(ServerVersion var1);

    public String getServerDefaultCharset();

    public String getErrorMessageEncoding();

    public void setErrorMessageEncoding(String var1);

    public int getMaxBytesPerChar(String var1);

    public int getMaxBytesPerChar(Integer var1, String var2);

    public String getEncodingForIndex(int var1);

    public void configureCharacterSets();

    public String getCharacterSetMetadata();

    public void setCharacterSetMetadata(String var1);

    public int getMetadataCollationIndex();

    public void setMetadataCollationIndex(int var1);

    public String getCharacterSetResultsOnServer();

    public void setCharacterSetResultsOnServer(String var1);

    public boolean isLowerCaseTableNames();

    public boolean storesLowerCaseTableNames();

    public boolean isQueryCacheEnabled();

    public boolean isNoBackslashEscapesSet();

    public boolean useAnsiQuotedIdentifiers();

    public long getThreadId();

    public void setThreadId(long var1);

    public boolean isAutoCommit();

    public void setAutoCommit(boolean var1);

    public TimeZone getServerTimeZone();

    public void setServerTimeZone(TimeZone var1);

    public TimeZone getDefaultTimeZone();

    public void setDefaultTimeZone(TimeZone var1);
}

