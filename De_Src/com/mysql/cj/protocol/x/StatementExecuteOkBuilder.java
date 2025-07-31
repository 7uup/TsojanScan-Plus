/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.x;

import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.protocol.Warning;
import com.mysql.cj.protocol.x.Notice;
import com.mysql.cj.protocol.x.StatementExecuteOk;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StatementExecuteOkBuilder {
    private long rowsAffected = 0L;
    private Long lastInsertId = null;
    private List<String> generatedIds = Collections.emptyList();
    private List<Warning> warnings = new ArrayList<Warning>();

    public void addNotice(Notice notice) {
        if (notice.getType() == 1) {
            this.warnings.add(notice);
        } else if (notice.getType() == 3) {
            switch (notice.getParamType()) {
                case 3: {
                    this.lastInsertId = notice.getValue().getVUnsignedInt();
                    break;
                }
                case 4: {
                    this.rowsAffected = notice.getValue().getVUnsignedInt();
                    break;
                }
                case 10: {
                    break;
                }
                case 12: {
                    this.generatedIds = notice.getValueList().stream().map(v -> v.getVOctets().getValue().toStringUtf8()).collect(Collectors.toList());
                    break;
                }
                default: {
                    new WrongArgumentException("unhandled SessionStateChanged notice! " + notice).printStackTrace();
                    break;
                }
            }
        } else {
            new WrongArgumentException("Got an unknown notice: " + notice).printStackTrace();
        }
    }

    public StatementExecuteOk build() {
        return new StatementExecuteOk(this.rowsAffected, this.lastInsertId, this.generatedIds, this.warnings);
    }
}

