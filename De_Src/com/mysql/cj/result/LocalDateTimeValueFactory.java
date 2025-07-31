/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.result;

import com.mysql.cj.Messages;
import com.mysql.cj.exceptions.DataReadException;
import com.mysql.cj.result.DefaultValueFactory;
import java.time.LocalDateTime;

public class LocalDateTimeValueFactory
extends DefaultValueFactory<LocalDateTime> {
    @Override
    public LocalDateTime createFromDate(int year, int month, int day) {
        return this.createFromTimestamp(year, month, day, 0, 0, 0, 0);
    }

    @Override
    public LocalDateTime createFromTime(int hours, int minutes, int seconds, int nanos) {
        if (hours < 0 || hours >= 24) {
            throw new DataReadException(Messages.getString("ResultSet.InvalidTimeValue", new Object[]{"" + hours + ":" + minutes + ":" + seconds}));
        }
        return this.createFromTimestamp(1970, 1, 1, hours, minutes, seconds, nanos);
    }

    @Override
    public LocalDateTime createFromTimestamp(int year, int month, int day, int hours, int minutes, int seconds, int nanos) {
        if (year == 0 && month == 0 && day == 0) {
            throw new DataReadException(Messages.getString("ResultSet.InvalidZeroDate"));
        }
        return LocalDateTime.of(year, month, day, hours, minutes, seconds, nanos);
    }

    @Override
    public String getTargetTypeName() {
        return LocalDateTime.class.getName();
    }
}

