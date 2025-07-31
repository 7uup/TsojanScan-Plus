/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.x;

import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.protocol.MessageListener;
import com.mysql.cj.protocol.ProtocolEntityFactory;
import com.mysql.cj.protocol.ResultListener;
import com.mysql.cj.protocol.x.Notice;
import com.mysql.cj.protocol.x.StatementExecuteOk;
import com.mysql.cj.protocol.x.StatementExecuteOkBuilder;
import com.mysql.cj.protocol.x.XMessage;
import com.mysql.cj.protocol.x.XProtocolError;
import com.mysql.cj.protocol.x.XProtocolRow;
import com.mysql.cj.result.DefaultColumnDefinition;
import com.mysql.cj.result.Field;
import com.mysql.cj.x.protobuf.Mysqlx;
import com.mysql.cj.x.protobuf.MysqlxNotice;
import com.mysql.cj.x.protobuf.MysqlxResultset;
import com.mysql.cj.x.protobuf.MysqlxSql;
import java.util.ArrayList;

public class ResultMessageListener
implements MessageListener<XMessage> {
    private ResultListener<StatementExecuteOk> callbacks;
    private ProtocolEntityFactory<Field, XMessage> fieldFactory;
    private ProtocolEntityFactory<Notice, XMessage> noticeFactory;
    private ArrayList<Field> fields = new ArrayList();
    private ColumnDefinition metadata = null;
    private boolean metadataSent = false;
    private StatementExecuteOkBuilder okBuilder = new StatementExecuteOkBuilder();

    public ResultMessageListener(ProtocolEntityFactory<Field, XMessage> colToField, ProtocolEntityFactory<Notice, XMessage> noticeFactory, ResultListener<StatementExecuteOk> callbacks) {
        this.callbacks = callbacks;
        this.fieldFactory = colToField;
        this.noticeFactory = noticeFactory;
    }

    @Override
    public Boolean createFromMessage(XMessage message) {
        Class<?> msgClass = message.getMessage().getClass();
        if (MysqlxResultset.ColumnMetaData.class.equals(msgClass)) {
            Field f = this.fieldFactory.createFromMessage(message);
            this.fields.add(f);
            return false;
        }
        if (!this.metadataSent) {
            if (this.metadata == null) {
                this.metadata = new DefaultColumnDefinition(this.fields.toArray(new Field[0]));
            }
            this.callbacks.onMetadata(this.metadata);
            this.metadataSent = true;
        }
        if (MysqlxSql.StmtExecuteOk.class.equals(msgClass)) {
            this.callbacks.onComplete(this.okBuilder.build());
            return true;
        }
        if (MysqlxResultset.FetchDone.class.equals(msgClass)) {
            return false;
        }
        if (MysqlxResultset.Row.class.equals(msgClass)) {
            if (this.metadata == null) {
                this.metadata = new DefaultColumnDefinition(this.fields.toArray(new Field[0]));
            }
            XProtocolRow row = new XProtocolRow(this.metadata, (MysqlxResultset.Row)MysqlxResultset.Row.class.cast(message.getMessage()));
            this.callbacks.onRow(row);
            return false;
        }
        if (Mysqlx.Error.class.equals(msgClass)) {
            XProtocolError e = new XProtocolError((Mysqlx.Error)Mysqlx.Error.class.cast(message.getMessage()));
            this.callbacks.onException(e);
            return true;
        }
        if (MysqlxNotice.Frame.class.equals(msgClass)) {
            this.okBuilder.addNotice(this.noticeFactory.createFromMessage(message));
            return false;
        }
        this.callbacks.onException(new WrongArgumentException("Unhandled msg class (" + msgClass + ") + msg=" + message.getMessage()));
        return false;
    }

    @Override
    public void error(Throwable ex) {
        this.callbacks.onException(ex);
    }
}

