/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.result;

import com.mysql.cj.Messages;
import com.mysql.cj.WarningListener;
import com.mysql.cj.exceptions.DataReadException;
import com.mysql.cj.result.DefaultValueFactory;
import java.time.LocalTime;

public class LocalTimeValueFactory
extends DefaultValueFactory<LocalTime> {
    private WarningListener warningListener;

    public LocalTimeValueFactory() {
    }

    public LocalTimeValueFactory(WarningListener warningListener) {
        this();
        this.warningListener = warningListener;
    }

    @Override
    public LocalTime createFromTime(int hours, int minutes, int seconds, int nanos) {
        if (hours < 0 || hours >= 24) {
            throw new DataReadException(Messages.getString("ResultSet.InvalidTimeValue", new Object[]{"" + hours + ":" + minutes + ":" + seconds}));
        }
        return LocalTime.of(hours, minutes, seconds, nanos);
    }

    @Override
    public LocalTime createFromTimestamp(int year, int month, int day, int hours, int minutes, int seconds, int nanos) {
        if (this.warningListener != null) {
            this.warningListener.warningEncountered(Messages.getString("ResultSet.PrecisionLostWarning", new Object[]{this.getTargetTypeName()}));
        }
        return this.createFromTime(hours, minutes, seconds, nanos);
    }

    @Override
    public String getTargetTypeName() {
        return LocalTime.class.getName();
    }
}

