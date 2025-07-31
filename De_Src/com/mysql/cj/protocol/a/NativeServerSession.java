/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.a;

import com.mysql.cj.CharsetMapping;
import com.mysql.cj.Messages;
import com.mysql.cj.ServerVersion;
import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.protocol.ServerCapabilities;
import com.mysql.cj.protocol.ServerSession;
import com.mysql.cj.protocol.a.NativeCapabilities;
import com.mysql.cj.util.StringUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class NativeServerSession
implements ServerSession {
    public static final int SERVER_STATUS_IN_TRANS = 1;
    public static final int SERVER_STATUS_AUTOCOMMIT = 2;
    public static final int SERVER_MORE_RESULTS_EXISTS = 8;
    public static final int SERVER_QUERY_NO_GOOD_INDEX_USED = 16;
    public static final int SERVER_QUERY_NO_INDEX_USED = 32;
    public static final int SERVER_STATUS_CURSOR_EXISTS = 64;
    public static final int SERVER_STATUS_LAST_ROW_SENT = 128;
    public static final int SERVER_QUERY_WAS_SLOW = 2048;
    public static final int CLIENT_LONG_PASSWORD = 1;
    public static final int CLIENT_FOUND_ROWS = 2;
    public static final int CLIENT_LONG_FLAG = 4;
    public static final int CLIENT_CONNECT_WITH_DB = 8;
    public static final int CLIENT_COMPRESS = 32;
    public static final int CLIENT_LOCAL_FILES = 128;
    public static final int CLIENT_PROTOCOL_41 = 512;
    public static final int CLIENT_INTERACTIVE = 1024;
    public static final int CLIENT_SSL = 2048;
    public static final int CLIENT_TRANSACTIONS = 8192;
    public static final int CLIENT_RESERVED = 16384;
    public static final int CLIENT_SECURE_CONNECTION = 32768;
    public static final int CLIENT_MULTI_STATEMENTS = 65536;
    public static final int CLIENT_MULTI_RESULTS = 131072;
    public static final int CLIENT_PS_MULTI_RESULTS = 262144;
    public static final int CLIENT_PLUGIN_AUTH = 524288;
    public static final int CLIENT_CONNECT_ATTRS = 0x100000;
    public static final int CLIENT_PLUGIN_AUTH_LENENC_CLIENT_DATA = 0x200000;
    public static final int CLIENT_CAN_HANDLE_EXPIRED_PASSWORD = 0x400000;
    public static final int CLIENT_SESSION_TRACK = 0x800000;
    public static final int CLIENT_DEPRECATE_EOF = 0x1000000;
    private PropertySet propertySet;
    private NativeCapabilities capabilities;
    private int oldStatusFlags = 0;
    private int statusFlags = 0;
    private int serverDefaultCollationIndex;
    private long clientParam = 0L;
    private boolean hasLongColumnInfo = false;
    private Map<String, String> serverVariables = new HashMap<String, String>();
    public Map<Integer, String> indexToCustomMysqlCharset = null;
    public Map<String, Integer> mysqlCharsetToCustomMblen = null;
    private String characterSetMetadata = null;
    private int metadataCollationIndex;
    private String characterSetResultsOnServer = null;
    private String errorMessageEncoding = "Cp1252";
    private boolean autoCommit = true;
    private TimeZone serverTimeZone = null;
    private TimeZone defaultTimeZone = TimeZone.getDefault();

    public NativeServerSession(PropertySet propertySet) {
        this.propertySet = propertySet;
        this.serverVariables.put("character_set_server", "utf8");
    }

    @Override
    public NativeCapabilities getCapabilities() {
        return this.capabilities;
    }

    @Override
    public void setCapabilities(ServerCapabilities capabilities) {
        this.capabilities = (NativeCapabilities)capabilities;
    }

    @Override
    public int getStatusFlags() {
        return this.statusFlags;
    }

    @Override
    public void setStatusFlags(int statusFlags) {
        this.setStatusFlags(statusFlags, false);
    }

    @Override
    public void setStatusFlags(int statusFlags, boolean saveOldStatus) {
        if (saveOldStatus) {
            this.oldStatusFlags = this.statusFlags;
        }
        this.statusFlags = statusFlags;
    }

    @Override
    public int getOldStatusFlags() {
        return this.oldStatusFlags;
    }

    @Override
    public void setOldStatusFlags(int oldStatusFlags) {
        this.oldStatusFlags = oldStatusFlags;
    }

    @Override
    public int getTransactionState() {
        if ((this.oldStatusFlags & 1) == 0) {
            if ((this.statusFlags & 1) == 0) {
                return 0;
            }
            return 2;
        }
        if ((this.statusFlags & 1) == 0) {
            return 3;
        }
        return 1;
    }

    @Override
    public boolean inTransactionOnServer() {
        return (this.statusFlags & 1) != 0;
    }

    @Override
    public boolean cursorExists() {
        return (this.statusFlags & 0x40) != 0;
    }

    @Override
    public boolean isAutocommit() {
        return (this.statusFlags & 2) != 0;
    }

    @Override
    public boolean hasMoreResults() {
        return (this.statusFlags & 8) != 0;
    }

    @Override
    public boolean noGoodIndexUsed() {
        return (this.statusFlags & 0x10) != 0;
    }

    @Override
    public boolean noIndexUsed() {
        return (this.statusFlags & 0x20) != 0;
    }

    @Override
    public boolean queryWasSlow() {
        return (this.statusFlags & 0x800) != 0;
    }

    @Override
    public boolean isLastRowSent() {
        return (this.statusFlags & 0x80) != 0;
    }

    @Override
    public long getClientParam() {
        return this.clientParam;
    }

    @Override
    public void setClientParam(long clientParam) {
        this.clientParam = clientParam;
    }

    @Override
    public boolean useMultiResults() {
        return (this.clientParam & 0x20000L) != 0L || (this.clientParam & 0x40000L) != 0L;
    }

    @Override
    public boolean isEOFDeprecated() {
        return (this.clientParam & 0x1000000L) != 0L;
    }

    @Override
    public int getServerDefaultCollationIndex() {
        return this.serverDefaultCollationIndex;
    }

    @Override
    public void setServerDefaultCollationIndex(int serverDefaultCollationIndex) {
        this.serverDefaultCollationIndex = serverDefaultCollationIndex;
    }

    @Override
    public boolean hasLongColumnInfo() {
        return this.hasLongColumnInfo;
    }

    @Override
    public void setHasLongColumnInfo(boolean hasLongColumnInfo) {
        this.hasLongColumnInfo = hasLongColumnInfo;
    }

    @Override
    public Map<String, String> getServerVariables() {
        return this.serverVariables;
    }

    @Override
    public String getServerVariable(String name) {
        return this.serverVariables.get(name);
    }

    @Override
    public int getServerVariable(String variableName, int fallbackValue) {
        try {
            return Integer.valueOf(this.getServerVariable(variableName));
        } catch (NumberFormatException numberFormatException) {
            return fallbackValue;
        }
    }

    @Override
    public void setServerVariables(Map<String, String> serverVariables) {
        this.serverVariables = serverVariables;
    }

    @Override
    public boolean characterSetNamesMatches(String mysqlEncodingName) {
        return mysqlEncodingName != null && mysqlEncodingName.equalsIgnoreCase(this.getServerVariable("character_set_client")) && mysqlEncodingName.equalsIgnoreCase(this.getServerVariable("character_set_connection"));
    }

    @Override
    public final ServerVersion getServerVersion() {
        return this.capabilities.getServerVersion();
    }

    @Override
    public boolean isVersion(ServerVersion version) {
        return this.getServerVersion().equals(version);
    }

    public boolean isSetNeededForAutoCommitMode(boolean autoCommitFlag, boolean elideSetAutoCommitsFlag) {
        if (elideSetAutoCommitsFlag) {
            boolean autoCommitModeOnServer = this.isAutocommit();
            if (autoCommitModeOnServer && !autoCommitFlag) {
                return !this.inTransactionOnServer();
            }
            return autoCommitModeOnServer != autoCommitFlag;
        }
        return true;
    }

    @Override
    public String getErrorMessageEncoding() {
        return this.errorMessageEncoding;
    }

    @Override
    public void setErrorMessageEncoding(String errorMessageEncoding) {
        this.errorMessageEncoding = errorMessageEncoding;
    }

    @Override
    public String getServerDefaultCharset() {
        String charset = null;
        if (this.indexToCustomMysqlCharset != null) {
            charset = this.indexToCustomMysqlCharset.get(this.getServerDefaultCollationIndex());
        }
        if (charset == null) {
            charset = CharsetMapping.getMysqlCharsetNameForCollationIndex(this.getServerDefaultCollationIndex());
        }
        return charset != null ? charset : this.getServerVariable("character_set_server");
    }

    @Override
    public int getMaxBytesPerChar(String javaCharsetName) {
        return this.getMaxBytesPerChar(null, javaCharsetName);
    }

    @Override
    public int getMaxBytesPerChar(Integer charsetIndex, String javaCharsetName) {
        String charset = null;
        int res = 1;
        if (this.indexToCustomMysqlCharset != null) {
            charset = this.indexToCustomMysqlCharset.get(charsetIndex);
        }
        if (charset == null) {
            charset = CharsetMapping.getMysqlCharsetNameForCollationIndex(charsetIndex);
        }
        if (charset == null) {
            charset = CharsetMapping.getMysqlCharsetForJavaEncoding(javaCharsetName, this.getServerVersion());
        }
        Integer mblen = null;
        if (this.mysqlCharsetToCustomMblen != null) {
            mblen = this.mysqlCharsetToCustomMblen.get(charset);
        }
        if (mblen == null) {
            mblen = CharsetMapping.getMblen(charset);
        }
        if (mblen != null) {
            res = mblen;
        }
        return res;
    }

    @Override
    public String getEncodingForIndex(int charsetIndex) {
        String javaEncoding = null;
        String characterEncoding = this.propertySet.getStringProperty("characterEncoding").getValue();
        if (this.propertySet.getBooleanProperty("useOldUTF8Behavior").getValue().booleanValue()) {
            return characterEncoding;
        }
        if (charsetIndex != -1) {
            try {
                String cs;
                if (this.indexToCustomMysqlCharset != null && (cs = this.indexToCustomMysqlCharset.get(charsetIndex)) != null) {
                    javaEncoding = CharsetMapping.getJavaEncodingForMysqlCharset(cs, characterEncoding);
                }
                if (javaEncoding == null) {
                    javaEncoding = CharsetMapping.getJavaEncodingForCollationIndex(charsetIndex, characterEncoding);
                }
            } catch (ArrayIndexOutOfBoundsException outOfBoundsEx) {
                throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("Connection.11", new Object[]{charsetIndex}));
            }
            if (javaEncoding == null) {
                javaEncoding = characterEncoding;
            }
        } else {
            javaEncoding = characterEncoding;
        }
        return javaEncoding;
    }

    @Override
    public void configureCharacterSets() {
        String characterSetResultsOnServerMysql = this.getServerVariable("local.character_set_results");
        if (characterSetResultsOnServerMysql == null || StringUtils.startsWithIgnoreCaseAndWs(characterSetResultsOnServerMysql, "NULL") || characterSetResultsOnServerMysql.length() == 0) {
            String defaultMetadataCharsetMysql = this.getServerVariable("character_set_system");
            String defaultMetadataCharset = null;
            defaultMetadataCharset = defaultMetadataCharsetMysql != null ? CharsetMapping.getJavaEncodingForMysqlCharset(defaultMetadataCharsetMysql) : "UTF-8";
            this.characterSetMetadata = defaultMetadataCharset;
            this.setErrorMessageEncoding("UTF-8");
        } else {
            this.characterSetMetadata = this.characterSetResultsOnServer = CharsetMapping.getJavaEncodingForMysqlCharset(characterSetResultsOnServerMysql);
            this.setErrorMessageEncoding(this.characterSetResultsOnServer);
        }
        this.metadataCollationIndex = CharsetMapping.getCollationIndexForJavaEncoding(this.characterSetMetadata, this.getServerVersion());
    }

    @Override
    public String getCharacterSetMetadata() {
        return this.characterSetMetadata;
    }

    @Override
    public void setCharacterSetMetadata(String characterSetMetadata) {
        this.characterSetMetadata = characterSetMetadata;
    }

    @Override
    public int getMetadataCollationIndex() {
        return this.metadataCollationIndex;
    }

    @Override
    public void setMetadataCollationIndex(int metadataCollationIndex) {
        this.metadataCollationIndex = metadataCollationIndex;
    }

    @Override
    public String getCharacterSetResultsOnServer() {
        return this.characterSetResultsOnServer;
    }

    @Override
    public void setCharacterSetResultsOnServer(String characterSetResultsOnServer) {
        this.characterSetResultsOnServer = characterSetResultsOnServer;
    }

    public void preserveOldTransactionState() {
        this.statusFlags |= this.oldStatusFlags & 1;
    }

    @Override
    public boolean isLowerCaseTableNames() {
        String lowerCaseTables = this.serverVariables.get("lower_case_table_names");
        return "on".equalsIgnoreCase(lowerCaseTables) || "1".equalsIgnoreCase(lowerCaseTables) || "2".equalsIgnoreCase(lowerCaseTables);
    }

    @Override
    public boolean storesLowerCaseTableNames() {
        String lowerCaseTables = this.serverVariables.get("lower_case_table_names");
        return "1".equalsIgnoreCase(lowerCaseTables) || "on".equalsIgnoreCase(lowerCaseTables);
    }

    @Override
    public boolean isQueryCacheEnabled() {
        return "ON".equalsIgnoreCase(this.serverVariables.get("query_cache_type")) && !"0".equalsIgnoreCase(this.serverVariables.get("query_cache_size"));
    }

    @Override
    public boolean isNoBackslashEscapesSet() {
        String sqlModeAsString = this.serverVariables.get("sql_mode");
        return sqlModeAsString != null && sqlModeAsString.indexOf("NO_BACKSLASH_ESCAPES") != -1;
    }

    @Override
    public boolean useAnsiQuotedIdentifiers() {
        String sqlModeAsString = this.serverVariables.get("sql_mode");
        return sqlModeAsString != null && sqlModeAsString.indexOf("ANSI_QUOTES") != -1;
    }

    @Override
    public long getThreadId() {
        return this.capabilities.getThreadId();
    }

    @Override
    public void setThreadId(long threadId) {
        this.capabilities.setThreadId(threadId);
    }

    @Override
    public boolean isAutoCommit() {
        return this.autoCommit;
    }

    @Override
    public void setAutoCommit(boolean autoCommit) {
        this.autoCommit = autoCommit;
    }

    @Override
    public TimeZone getServerTimeZone() {
        return this.serverTimeZone;
    }

    @Override
    public void setServerTimeZone(TimeZone serverTimeZone) {
        this.serverTimeZone = serverTimeZone;
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

