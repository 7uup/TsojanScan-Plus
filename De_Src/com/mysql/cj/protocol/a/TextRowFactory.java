/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.a;

import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.protocol.ProtocolEntityFactory;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.protocol.ResultsetRow;
import com.mysql.cj.protocol.a.AbstractRowFactory;
import com.mysql.cj.protocol.a.MysqlTextValueDecoder;
import com.mysql.cj.protocol.a.NativeConstants;
import com.mysql.cj.protocol.a.NativePacketPayload;
import com.mysql.cj.protocol.a.NativeProtocol;
import com.mysql.cj.protocol.a.result.ByteArrayRow;
import com.mysql.cj.protocol.a.result.TextBufferRow;

public class TextRowFactory
extends AbstractRowFactory
implements ProtocolEntityFactory<ResultsetRow, NativePacketPayload> {
    public TextRowFactory(NativeProtocol protocol, ColumnDefinition colDefinition, Resultset.Concurrency resultSetConcurrency, boolean canReuseRowPacketForBufferRow) {
        this.columnDefinition = colDefinition;
        this.resultSetConcurrency = resultSetConcurrency;
        this.canReuseRowPacketForBufferRow = canReuseRowPacketForBufferRow;
        this.useBufferRowSizeThreshold = protocol.getPropertySet().getMemorySizeProperty("largeRowSizeThreshold");
        this.exceptionInterceptor = protocol.getExceptionInterceptor();
        this.valueDecoder = new MysqlTextValueDecoder();
    }

    @Override
    public ResultsetRow createFromMessage(NativePacketPayload rowPacket) {
        boolean useBufferRow;
        boolean bl = useBufferRow = this.canReuseRowPacketForBufferRow || this.columnDefinition.hasLargeFields() || rowPacket.getPayloadLength() >= (Integer)this.useBufferRowSizeThreshold.getValue();
        if (this.resultSetConcurrency == Resultset.Concurrency.UPDATABLE || !useBufferRow) {
            byte[][] rowBytes = new byte[this.columnDefinition.getFields().length][];
            for (int i = 0; i < this.columnDefinition.getFields().length; ++i) {
                rowBytes[i] = rowPacket.readBytes(NativeConstants.StringSelfDataType.STRING_LENENC);
            }
            return new ByteArrayRow(rowBytes, this.exceptionInterceptor);
        }
        return new TextBufferRow(rowPacket, this.columnDefinition, this.exceptionInterceptor, this.valueDecoder);
    }

    @Override
    public boolean canReuseRowPacketForBufferRow() {
        return this.canReuseRowPacketForBufferRow;
    }
}

