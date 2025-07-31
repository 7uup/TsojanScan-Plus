/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.x;

import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.protocol.MessageListener;
import com.mysql.cj.protocol.ProtocolEntityFactory;
import com.mysql.cj.protocol.x.Notice;
import com.mysql.cj.protocol.x.StatementExecuteOk;
import com.mysql.cj.protocol.x.StatementExecuteOkBuilder;
import com.mysql.cj.protocol.x.XMessage;
import com.mysql.cj.protocol.x.XProtocolError;
import com.mysql.cj.x.protobuf.Mysqlx;
import com.mysql.cj.x.protobuf.MysqlxNotice;
import com.mysql.cj.x.protobuf.MysqlxResultset;
import com.mysql.cj.x.protobuf.MysqlxSql;
import java.util.concurrent.CompletableFuture;

public class StatementExecuteOkMessageListener
implements MessageListener<XMessage> {
    private StatementExecuteOkBuilder builder = new StatementExecuteOkBuilder();
    private CompletableFuture<StatementExecuteOk> future = new CompletableFuture();
    private ProtocolEntityFactory<Notice, XMessage> noticeFactory;

    public StatementExecuteOkMessageListener(CompletableFuture<StatementExecuteOk> future, ProtocolEntityFactory<Notice, XMessage> noticeFactory) {
        this.future = future;
        this.noticeFactory = noticeFactory;
    }

    @Override
    public Boolean createFromMessage(XMessage message) {
        Class<?> msgClass = message.getMessage().getClass();
        if (MysqlxNotice.Frame.class.equals(msgClass)) {
            this.builder.addNotice(this.noticeFactory.createFromMessage(message));
            return false;
        }
        if (MysqlxSql.StmtExecuteOk.class.equals(msgClass)) {
            this.future.complete(this.builder.build());
            return true;
        }
        if (Mysqlx.Error.class.equals(msgClass)) {
            this.future.completeExceptionally(new XProtocolError((Mysqlx.Error)Mysqlx.Error.class.cast(message.getMessage())));
            return true;
        }
        if (MysqlxResultset.FetchDone.class.equals(msgClass)) {
            return false;
        }
        this.future.completeExceptionally(new WrongArgumentException("Unhandled msg class (" + msgClass + ") + msg=" + message.getMessage()));
        return true;
    }

    @Override
    public void error(Throwable ex) {
        this.future.completeExceptionally(ex);
    }
}

