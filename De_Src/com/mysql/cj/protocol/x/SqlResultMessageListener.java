/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.x;

import com.google.protobuf.GeneratedMessage;
import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.protocol.MessageListener;
import com.mysql.cj.protocol.ProtocolEntityFactory;
import com.mysql.cj.protocol.x.Notice;
import com.mysql.cj.protocol.x.ResultCreatingResultListener;
import com.mysql.cj.protocol.x.ResultMessageListener;
import com.mysql.cj.protocol.x.StatementExecuteOk;
import com.mysql.cj.protocol.x.StatementExecuteOkMessageListener;
import com.mysql.cj.protocol.x.XMessage;
import com.mysql.cj.result.Field;
import com.mysql.cj.result.RowList;
import com.mysql.cj.x.protobuf.Mysqlx;
import com.mysql.cj.x.protobuf.MysqlxResultset;
import com.mysql.cj.xdevapi.SqlDataResult;
import com.mysql.cj.xdevapi.SqlResult;
import com.mysql.cj.xdevapi.SqlUpdateResult;
import java.util.TimeZone;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Supplier;

public class SqlResultMessageListener
implements MessageListener<XMessage> {
    private ResultType resultType;
    private CompletableFuture<SqlResult> resultF;
    private StatementExecuteOkMessageListener okListener;
    private ResultMessageListener resultListener;
    private ResultCreatingResultListener<SqlResult> resultCreator;

    public SqlResultMessageListener(CompletableFuture<SqlResult> resultF, ProtocolEntityFactory<Field, XMessage> colToField, ProtocolEntityFactory<Notice, XMessage> noticeFactory, TimeZone defaultTimeZone) {
        this.resultF = resultF;
        Function resultCtor = metadata -> (rows, task) -> new SqlDataResult((ColumnDefinition)metadata, defaultTimeZone, (RowList)rows, (Supplier<StatementExecuteOk>)task);
        this.resultCreator = new ResultCreatingResultListener<SqlResult>(resultCtor, resultF);
        this.resultListener = new ResultMessageListener(colToField, noticeFactory, this.resultCreator);
        CompletableFuture<StatementExecuteOk> okF = new CompletableFuture<StatementExecuteOk>();
        okF.whenComplete((ok, ex) -> {
            if (ex != null) {
                this.resultF.completeExceptionally((Throwable)ex);
            } else {
                this.resultF.complete(new SqlUpdateResult((StatementExecuteOk)ok));
            }
        });
        this.okListener = new StatementExecuteOkMessageListener(okF, noticeFactory);
    }

    @Override
    public Boolean createFromMessage(XMessage message) {
        GeneratedMessage msg = (GeneratedMessage)message.getMessage();
        Class<?> msgClass = msg.getClass();
        if (this.resultType == null) {
            if (MysqlxResultset.ColumnMetaData.class.equals(msgClass)) {
                this.resultType = ResultType.DATA;
            } else if (!Mysqlx.Error.class.equals(msgClass)) {
                this.resultType = ResultType.UPDATE;
            }
        }
        if (this.resultType == ResultType.DATA) {
            return this.resultListener.createFromMessage(message);
        }
        return this.okListener.createFromMessage(message);
    }

    @Override
    public void error(Throwable ex) {
        this.resultF.completeExceptionally(ex);
    }

    private static enum ResultType {
        UPDATE,
        DATA;

    }
}

