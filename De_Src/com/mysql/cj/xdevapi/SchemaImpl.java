/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.MysqlxSession;
import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.protocol.x.XMessageBuilder;
import com.mysql.cj.protocol.x.XProtocolError;
import com.mysql.cj.result.Row;
import com.mysql.cj.result.StringValueFactory;
import com.mysql.cj.result.ValueFactory;
import com.mysql.cj.xdevapi.Collection;
import com.mysql.cj.xdevapi.CollectionImpl;
import com.mysql.cj.xdevapi.DatabaseObject;
import com.mysql.cj.xdevapi.ExprUnparser;
import com.mysql.cj.xdevapi.Schema;
import com.mysql.cj.xdevapi.Session;
import com.mysql.cj.xdevapi.Table;
import com.mysql.cj.xdevapi.TableImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SchemaImpl
implements Schema {
    private MysqlxSession mysqlxSession;
    private XMessageBuilder xbuilder;
    private Session session;
    private String name;
    private ValueFactory<String> svf = new StringValueFactory();

    SchemaImpl(MysqlxSession mysqlxSession, Session session, String name) {
        this.mysqlxSession = mysqlxSession;
        this.session = session;
        this.name = name;
        this.xbuilder = (XMessageBuilder)this.mysqlxSession.getMessageBuilder();
    }

    @Override
    public Session getSession() {
        return this.session;
    }

    @Override
    public Schema getSchema() {
        return this;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public DatabaseObject.DbObjectStatus existsInDatabase() {
        StringBuilder stmt = new StringBuilder("select count(*) from information_schema.schemata where schema_name = '");
        stmt.append(this.name.replaceAll("'", "\\'"));
        stmt.append("'");
        return this.mysqlxSession.getDataStoreMetadata().schemaExists(this.name) ? DatabaseObject.DbObjectStatus.EXISTS : DatabaseObject.DbObjectStatus.NOT_EXISTS;
    }

    @Override
    public List<Collection> getCollections() {
        return this.getCollections(null);
    }

    @Override
    public List<Collection> getCollections(String pattern) {
        Set strTypes = Arrays.stream(new DatabaseObject.DbObjectType[]{DatabaseObject.DbObjectType.COLLECTION}).map(Enum::toString).collect(Collectors.toSet());
        Predicate<Row> rowFiler = r -> strTypes.contains(r.getValue(1, this.svf));
        Function<Row, String> rowToName = r -> r.getValue(0, this.svf);
        List objectNames = this.mysqlxSession.query(this.xbuilder.buildListObjects(this.name, pattern), rowFiler, rowToName, Collectors.toList());
        return objectNames.stream().map(this::getCollection).collect(Collectors.toList());
    }

    @Override
    public List<Table> getTables() {
        return this.getTables(null);
    }

    @Override
    public List<Table> getTables(String pattern) {
        Set strTypes = Arrays.stream(new DatabaseObject.DbObjectType[]{DatabaseObject.DbObjectType.TABLE, DatabaseObject.DbObjectType.VIEW, DatabaseObject.DbObjectType.COLLECTION_VIEW}).map(Enum::toString).collect(Collectors.toSet());
        Predicate<Row> rowFiler = r -> strTypes.contains(r.getValue(1, this.svf));
        Function<Row, String> rowToName = r -> r.getValue(0, this.svf);
        List objectNames = this.mysqlxSession.query(this.xbuilder.buildListObjects(this.name, pattern), rowFiler, rowToName, Collectors.toList());
        return objectNames.stream().map(this::getTable).collect(Collectors.toList());
    }

    @Override
    public Collection getCollection(String collectionName) {
        return new CollectionImpl(this.mysqlxSession, this, collectionName);
    }

    @Override
    public Collection getCollection(String collectionName, boolean requireExists) {
        CollectionImpl coll = new CollectionImpl(this.mysqlxSession, this, collectionName);
        if (requireExists && coll.existsInDatabase() != DatabaseObject.DbObjectStatus.EXISTS) {
            throw new WrongArgumentException(coll.toString() + " doesn't exist");
        }
        return coll;
    }

    @Override
    public Table getCollectionAsTable(String collectionName) {
        return this.getTable(collectionName);
    }

    @Override
    public Table getTable(String tableName) {
        return new TableImpl(this.mysqlxSession, this, tableName);
    }

    @Override
    public Table getTable(String tableName, boolean requireExists) {
        TableImpl table = new TableImpl(this.mysqlxSession, this, tableName);
        if (requireExists && table.existsInDatabase() != DatabaseObject.DbObjectStatus.EXISTS) {
            throw new WrongArgumentException(table.toString() + " doesn't exist");
        }
        return table;
    }

    @Override
    public Collection createCollection(String collectionName) {
        this.mysqlxSession.sendMessage(this.xbuilder.buildCreateCollection(this.name, collectionName));
        return new CollectionImpl(this.mysqlxSession, this, collectionName);
    }

    @Override
    public Collection createCollection(String collectionName, boolean reuseExistingObject) {
        try {
            return this.createCollection(collectionName);
        } catch (XProtocolError ex) {
            if (reuseExistingObject && ex.getErrorCode() == 1050) {
                return this.getCollection(collectionName);
            }
            throw ex;
        }
    }

    public boolean equals(Object other) {
        return other != null && other.getClass() == SchemaImpl.class && ((SchemaImpl)other).session == this.session && ((SchemaImpl)other).mysqlxSession == this.mysqlxSession && this.name.equals(((SchemaImpl)other).name);
    }

    public int hashCode() {
        assert (false) : "hashCode not designed";
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Schema(");
        sb.append(ExprUnparser.quoteIdentifier(this.name));
        sb.append(")");
        return sb.toString();
    }

    @Override
    public void dropCollection(String collectionName) {
        block2: {
            try {
                this.mysqlxSession.sendMessage(this.xbuilder.buildDropCollection(this.name, collectionName));
            } catch (XProtocolError e) {
                if (e.getErrorCode() == 1051) break block2;
                throw e;
            }
        }
    }
}

