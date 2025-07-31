/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.Messages;
import com.mysql.cj.MysqlxSession;
import com.mysql.cj.protocol.x.XMessageBuilder;
import com.mysql.cj.result.Row;
import com.mysql.cj.result.StringValueFactory;
import com.mysql.cj.xdevapi.DatabaseObject;
import com.mysql.cj.xdevapi.DatabaseObjectDescription;
import com.mysql.cj.xdevapi.DeleteStatement;
import com.mysql.cj.xdevapi.DeleteStatementImpl;
import com.mysql.cj.xdevapi.ExprUnparser;
import com.mysql.cj.xdevapi.InsertStatement;
import com.mysql.cj.xdevapi.InsertStatementImpl;
import com.mysql.cj.xdevapi.Schema;
import com.mysql.cj.xdevapi.SchemaImpl;
import com.mysql.cj.xdevapi.SelectStatement;
import com.mysql.cj.xdevapi.SelectStatementImpl;
import com.mysql.cj.xdevapi.Session;
import com.mysql.cj.xdevapi.Table;
import com.mysql.cj.xdevapi.UpdateStatement;
import com.mysql.cj.xdevapi.UpdateStatementImpl;
import com.mysql.cj.xdevapi.XDevAPIError;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TableImpl
implements Table {
    private MysqlxSession mysqlxSession;
    private SchemaImpl schema;
    private String name;
    private Boolean isView = null;
    private XMessageBuilder xbuilder;

    TableImpl(MysqlxSession mysqlxSession, SchemaImpl schema, String name) {
        if (mysqlxSession == null) {
            throw new XDevAPIError(Messages.getString("CreateTableStatement.0", new String[]{"mysqlxSession"}));
        }
        if (schema == null) {
            throw new XDevAPIError(Messages.getString("CreateTableStatement.0", new String[]{"schema"}));
        }
        if (name == null) {
            throw new XDevAPIError(Messages.getString("CreateTableStatement.0", new String[]{"name"}));
        }
        this.mysqlxSession = mysqlxSession;
        this.xbuilder = (XMessageBuilder)this.mysqlxSession.getMessageBuilder();
        this.schema = schema;
        this.name = name;
    }

    @Override
    public Session getSession() {
        return this.schema.getSession();
    }

    @Override
    public Schema getSchema() {
        return this.schema;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public DatabaseObject.DbObjectStatus existsInDatabase() {
        if (this.mysqlxSession.getDataStoreMetadata().tableExists(this.schema.getName(), this.name)) {
            return DatabaseObject.DbObjectStatus.EXISTS;
        }
        return DatabaseObject.DbObjectStatus.NOT_EXISTS;
    }

    @Override
    public InsertStatement insert() {
        return new InsertStatementImpl(this.mysqlxSession, this.schema.getName(), this.name, new String[0]);
    }

    @Override
    public InsertStatement insert(String ... fields) {
        return new InsertStatementImpl(this.mysqlxSession, this.schema.getName(), this.name, fields);
    }

    @Override
    public InsertStatement insert(Map<String, Object> fieldsAndValues) {
        return new InsertStatementImpl(this.mysqlxSession, this.schema.getName(), this.name, fieldsAndValues);
    }

    @Override
    public SelectStatement select(String ... projection) {
        return new SelectStatementImpl(this.mysqlxSession, this.schema.getName(), this.name, projection);
    }

    @Override
    public UpdateStatement update() {
        return new UpdateStatementImpl(this.mysqlxSession, this.schema.getName(), this.name);
    }

    @Override
    public DeleteStatement delete() {
        return new DeleteStatementImpl(this.mysqlxSession, this.schema.getName(), this.name);
    }

    @Override
    public long count() {
        return this.mysqlxSession.getDataStoreMetadata().getTableRowCount(this.schema.getName(), this.name);
    }

    public boolean equals(Object other) {
        return other != null && other.getClass() == TableImpl.class && ((TableImpl)other).schema.equals(this.schema) && ((TableImpl)other).mysqlxSession == this.mysqlxSession && this.name.equals(((TableImpl)other).name);
    }

    public int hashCode() {
        assert (false) : "hashCode not designed";
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Table(");
        sb.append(ExprUnparser.quoteIdentifier(this.schema.getName()));
        sb.append(".");
        sb.append(ExprUnparser.quoteIdentifier(this.name));
        sb.append(")");
        return sb.toString();
    }

    @Override
    public boolean isView() {
        if (this.isView == null) {
            StringValueFactory svf = new StringValueFactory();
            Function<Row, DatabaseObjectDescription> rowToDatabaseObjectDescription = r -> new DatabaseObjectDescription((String)r.getValue(0, svf), (String)r.getValue(1, svf));
            List objects = this.mysqlxSession.query(this.xbuilder.buildListObjects(this.schema.getName(), this.name), null, rowToDatabaseObjectDescription, Collectors.toList());
            if (objects.isEmpty()) {
                return false;
            }
            this.isView = ((DatabaseObjectDescription)objects.get(0)).getObjectType() == DatabaseObject.DbObjectType.VIEW || ((DatabaseObjectDescription)objects.get(0)).getObjectType() == DatabaseObject.DbObjectType.COLLECTION_VIEW;
        }
        return this.isView;
    }

    public void setView(boolean isView) {
        this.isView = isView;
    }
}

