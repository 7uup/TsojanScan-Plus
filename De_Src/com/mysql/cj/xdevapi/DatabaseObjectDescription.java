/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.xdevapi.DatabaseObject;

public class DatabaseObjectDescription {
    private String objectName;
    private DatabaseObject.DbObjectType objectType;

    public DatabaseObjectDescription(String name, String type) {
        this.objectName = name;
        this.objectType = DatabaseObject.DbObjectType.valueOf(type);
    }

    public String getObjectName() {
        return this.objectName;
    }

    public DatabaseObject.DbObjectType getObjectType() {
        return this.objectType;
    }
}

