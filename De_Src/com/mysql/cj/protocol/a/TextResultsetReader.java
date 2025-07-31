/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.a;

import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.protocol.ProtocolEntityFactory;
import com.mysql.cj.protocol.ProtocolEntityReader;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.protocol.ResultsetRow;
import com.mysql.cj.protocol.a.ColumnDefinitionFactory;
import com.mysql.cj.protocol.a.NativeConstants;
import com.mysql.cj.protocol.a.NativePacketPayload;
import com.mysql.cj.protocol.a.NativeProtocol;
import com.mysql.cj.protocol.a.TextRowFactory;
import com.mysql.cj.protocol.a.result.AbstractResultsetRows;
import com.mysql.cj.protocol.a.result.OkPacket;
import com.mysql.cj.protocol.a.result.ResultsetRowsStatic;
import com.mysql.cj.protocol.a.result.ResultsetRowsStreaming;
import java.io.IOException;
import java.util.ArrayList;

public class TextResultsetReader
implements ProtocolEntityReader<Resultset, NativePacketPayload> {
    protected NativeProtocol protocol;

    public TextResultsetReader(NativeProtocol prot) {
        this.protocol = prot;
    }

    @Override
    public Resultset read(int maxRows, boolean streamResults, NativePacketPayload resultPacket, ColumnDefinition metadata, ProtocolEntityFactory<Resultset, NativePacketPayload> resultSetFactory) throws IOException {
        Resultset rs = null;
        long columnCount = resultPacket.readInteger(NativeConstants.IntegerDataType.INT_LENENC);
        if (columnCount > 0L) {
            ColumnDefinition cdef = this.protocol.read(ColumnDefinition.class, new ColumnDefinitionFactory(columnCount, metadata));
            if (!this.protocol.getServerSession().isEOFDeprecated()) {
                this.protocol.skipPacket();
            }
            AbstractResultsetRows rows = null;
            if (!streamResults) {
                TextRowFactory trf = new TextRowFactory(this.protocol, cdef, resultSetFactory.getResultSetConcurrency(), false);
                ArrayList<ResultsetRow> rowList = new ArrayList<ResultsetRow>();
                ResultsetRow row = this.protocol.read(ResultsetRow.class, trf);
                while (row != null) {
                    if (maxRows == -1 || rowList.size() < maxRows) {
                        rowList.add(row);
                    }
                    row = this.protocol.read(ResultsetRow.class, trf);
                }
                rows = new ResultsetRowsStatic(rowList, cdef);
            } else {
                rows = new ResultsetRowsStreaming<Resultset>(this.protocol, cdef, false, resultSetFactory);
                this.protocol.setStreamingData(rows);
            }
            rs = resultSetFactory.createFromProtocolEntity(rows);
        } else {
            if (columnCount == -1L) {
                String charEncoding = this.protocol.getPropertySet().getStringProperty("characterEncoding").getValue();
                String fileName = resultPacket.readString(NativeConstants.StringSelfDataType.STRING_TERM, this.protocol.doesPlatformDbCharsetMatches() ? charEncoding : null);
                resultPacket = this.protocol.sendFileToServer(fileName);
            }
            OkPacket ok = (OkPacket)this.protocol.readServerStatusForResultSets(resultPacket, false);
            rs = resultSetFactory.createFromProtocolEntity(ok);
        }
        return rs;
    }
}

