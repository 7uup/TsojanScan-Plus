/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc.result;

import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.jdbc.StatementImpl;
import com.mysql.cj.jdbc.result.ResultSetImpl;
import com.mysql.cj.jdbc.result.UpdatableResultSet;
import com.mysql.cj.protocol.ProtocolEntity;
import com.mysql.cj.protocol.ProtocolEntityFactory;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.protocol.ResultsetRows;
import com.mysql.cj.protocol.a.NativePacketPayload;
import com.mysql.cj.protocol.a.result.OkPacket;
import com.mysql.cj.protocol.a.result.ResultsetRowsCursor;
import java.sql.SQLException;

public class ResultSetFactory
implements ProtocolEntityFactory<ResultSetImpl, NativePacketPayload> {
    private JdbcConnection conn;
    private StatementImpl stmt;
    private Resultset.Type type = Resultset.Type.FORWARD_ONLY;
    private Resultset.Concurrency concurrency = Resultset.Concurrency.READ_ONLY;

    public ResultSetFactory(JdbcConnection connection, StatementImpl creatorStmt) throws SQLException {
        this.conn = connection;
        this.stmt = creatorStmt;
        if (creatorStmt != null) {
            this.type = Resultset.Type.fromValue(creatorStmt.getResultSetType(), Resultset.Type.FORWARD_ONLY);
            this.concurrency = Resultset.Concurrency.fromValue(creatorStmt.getResultSetConcurrency(), Resultset.Concurrency.READ_ONLY);
        }
    }

    @Override
    public Resultset.Type getResultSetType() {
        return this.type;
    }

    @Override
    public Resultset.Concurrency getResultSetConcurrency() {
        return this.concurrency;
    }

    @Override
    public int getFetchSize() {
        try {
            return this.stmt.getFetchSize();
        } catch (SQLException ex) {
            throw ExceptionFactory.createException(ex.getMessage(), ex);
        }
    }

    @Override
    public ResultSetImpl createFromProtocolEntity(ProtocolEntity protocolEntity) {
        try {
            if (protocolEntity instanceof OkPacket) {
                return new ResultSetImpl((OkPacket)protocolEntity, this.conn, this.stmt);
            }
            if (protocolEntity instanceof ResultsetRows) {
                int resultSetConcurrency = this.getResultSetConcurrency().getIntValue();
                int resultSetType = this.getResultSetType().getIntValue();
                return this.createFromResultsetRows(resultSetConcurrency, resultSetType, (ResultsetRows)protocolEntity);
            }
            throw ExceptionFactory.createException(WrongArgumentException.class, "Unknown ProtocolEntity class " + protocolEntity);
        } catch (SQLException ex) {
            throw ExceptionFactory.createException(ex.getMessage(), ex);
        }
    }

    public ResultSetImpl createFromResultsetRows(int resultSetConcurrency, int resultSetType, ResultsetRows rows) throws SQLException {
        ResultSetImpl rs;
        StatementImpl st = this.stmt;
        if (rows.getOwner() != null) {
            st = ((ResultSetImpl)rows.getOwner()).getOwningStatement();
        }
        switch (resultSetConcurrency) {
            case 1008: {
                rs = new UpdatableResultSet(rows, this.conn, st);
                break;
            }
            default: {
                rs = new ResultSetImpl(rows, this.conn, st);
            }
        }
        rs.setResultSetType(resultSetType);
        rs.setResultSetConcurrency(resultSetConcurrency);
        if (rows instanceof ResultsetRowsCursor && st != null) {
            rs.setFetchSize(st.getFetchSize());
        }
        return rs;
    }
}

