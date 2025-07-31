/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc;

import com.mysql.cj.jdbc.ClientInfoProvider;
import com.mysql.cj.jdbc.JdbcConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;

public class ClientInfoProviderSP
implements ClientInfoProvider {
    PreparedStatement setClientInfoSp;
    PreparedStatement getClientInfoSp;
    PreparedStatement getClientInfoBulkSp;

    @Override
    public synchronized void initialize(Connection conn, Properties configurationProps) throws SQLException {
        String identifierQuote = ((JdbcConnection)conn).getSession().getIdentifierQuoteString();
        String setClientInfoSpName = configurationProps.getProperty("clientInfoSetSPName", "setClientInfo");
        String getClientInfoSpName = configurationProps.getProperty("clientInfoGetSPName", "getClientInfo");
        String getClientInfoBulkSpName = configurationProps.getProperty("clientInfoGetBulkSPName", "getClientInfoBulk");
        String clientInfoCatalog = configurationProps.getProperty("clientInfoCatalog", "");
        String catalog = "".equals(clientInfoCatalog) ? conn.getCatalog() : clientInfoCatalog;
        this.setClientInfoSp = ((JdbcConnection)conn).clientPrepareStatement("CALL " + identifierQuote + catalog + identifierQuote + "." + identifierQuote + setClientInfoSpName + identifierQuote + "(?, ?)");
        this.getClientInfoSp = ((JdbcConnection)conn).clientPrepareStatement("CALL" + identifierQuote + catalog + identifierQuote + "." + identifierQuote + getClientInfoSpName + identifierQuote + "(?)");
        this.getClientInfoBulkSp = ((JdbcConnection)conn).clientPrepareStatement("CALL " + identifierQuote + catalog + identifierQuote + "." + identifierQuote + getClientInfoBulkSpName + identifierQuote + "()");
    }

    @Override
    public synchronized void destroy() throws SQLException {
        if (this.setClientInfoSp != null) {
            this.setClientInfoSp.close();
            this.setClientInfoSp = null;
        }
        if (this.getClientInfoSp != null) {
            this.getClientInfoSp.close();
            this.getClientInfoSp = null;
        }
        if (this.getClientInfoBulkSp != null) {
            this.getClientInfoBulkSp.close();
            this.getClientInfoBulkSp = null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public synchronized Properties getClientInfo(Connection conn) throws SQLException {
        Properties props = new Properties();
        try (ResultSet rs = null;){
            this.getClientInfoBulkSp.execute();
            rs = this.getClientInfoBulkSp.getResultSet();
            while (rs.next()) {
                props.setProperty(rs.getString(1), rs.getString(2));
            }
        }
        return props;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public synchronized String getClientInfo(Connection conn, String name) throws SQLException {
        String clientInfo = null;
        try (ResultSet rs = null;){
            this.getClientInfoSp.setString(1, name);
            this.getClientInfoSp.execute();
            rs = this.getClientInfoSp.getResultSet();
            if (rs.next()) {
                clientInfo = rs.getString(1);
            }
        }
        return clientInfo;
    }

    @Override
    public synchronized void setClientInfo(Connection conn, Properties properties) throws SQLClientInfoException {
        try {
            Enumeration<?> propNames = properties.propertyNames();
            while (propNames.hasMoreElements()) {
                String name = (String)propNames.nextElement();
                String value = properties.getProperty(name);
                this.setClientInfo(conn, name, value);
            }
        } catch (SQLException sqlEx) {
            SQLClientInfoException clientInfoEx = new SQLClientInfoException();
            clientInfoEx.initCause(sqlEx);
            throw clientInfoEx;
        }
    }

    @Override
    public synchronized void setClientInfo(Connection conn, String name, String value) throws SQLClientInfoException {
        try {
            this.setClientInfoSp.setString(1, name);
            this.setClientInfoSp.setString(2, value);
            this.setClientInfoSp.execute();
        } catch (SQLException sqlEx) {
            SQLClientInfoException clientInfoEx = new SQLClientInfoException();
            clientInfoEx.initCause(sqlEx);
            throw clientInfoEx;
        }
    }
}

