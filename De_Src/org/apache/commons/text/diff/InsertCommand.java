/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text.diff;

import org.apache.commons.text.diff.CommandVisitor;
import org.apache.commons.text.diff.EditCommand;

public class InsertCommand<T>
extends EditCommand<T> {
    public InsertCommand(T object) {
        super(object);
    }

    @Override
    public void accept(CommandVisitor<T> visitor) {
        visitor.visitInsertCommand(this.getObject());
    }
}

