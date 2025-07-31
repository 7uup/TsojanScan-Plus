/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.x;

public enum XpluginStatementCommand {
    XPLUGIN_STMT_CREATE_COLLECTION("create_collection"),
    XPLUGIN_STMT_CREATE_COLLECTION_INDEX("create_collection_index"),
    XPLUGIN_STMT_DROP_COLLECTION("drop_collection"),
    XPLUGIN_STMT_DROP_COLLECTION_INDEX("drop_collection_index"),
    XPLUGIN_STMT_PING("ping"),
    XPLUGIN_STMT_LIST_OBJECTS("list_objects"),
    XPLUGIN_STMT_ENABLE_NOTICES("enable_notices"),
    XPLUGIN_STMT_DISABLE_NOTICES("disable_notices"),
    XPLUGIN_STMT_LIST_NOTICES("list_notices");

    public String commandName;

    private XpluginStatementCommand(String commandName) {
        this.commandName = commandName;
    }
}

