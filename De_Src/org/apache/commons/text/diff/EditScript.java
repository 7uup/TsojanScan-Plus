/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text.diff;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.text.diff.CommandVisitor;
import org.apache.commons.text.diff.DeleteCommand;
import org.apache.commons.text.diff.EditCommand;
import org.apache.commons.text.diff.InsertCommand;
import org.apache.commons.text.diff.KeepCommand;

public class EditScript<T> {
    private final List<EditCommand<T>> commands = new ArrayList<EditCommand<T>>();
    private int lcsLength = 0;
    private int modifications = 0;

    public void append(KeepCommand<T> command) {
        this.commands.add(command);
        ++this.lcsLength;
    }

    public void append(InsertCommand<T> command) {
        this.commands.add(command);
        ++this.modifications;
    }

    public void append(DeleteCommand<T> command) {
        this.commands.add(command);
        ++this.modifications;
    }

    public void visit(CommandVisitor<T> visitor) {
        for (EditCommand<T> command : this.commands) {
            command.accept(visitor);
        }
    }

    public int getLCSLength() {
        return this.lcsLength;
    }

    public int getModifications() {
        return this.modifications;
    }
}

