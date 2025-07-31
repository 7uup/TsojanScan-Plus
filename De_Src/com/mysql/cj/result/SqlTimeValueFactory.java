/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.result;

import com.mysql.cj.Messages;
import com.mysql.cj.WarningListener;
import com.mysql.cj.exceptions.DataReadException;
import com.mysql.cj.result.DefaultValueFactory;
import java.sql.Time;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class SqlTimeValueFactory
extends DefaultValueFactory<Time> {
    private TimeZone tz;
    private WarningListener warningListener;
    private Calendar cal;

    public SqlTimeValueFactory(TimeZone tz) {
        this.tz = tz;
        this.cal = Calendar.getInstance(this.tz, Locale.US);
        this.cal.setLenient(false);
    }

    public SqlTimeValueFactory(TimeZone tz, WarningListener warningListener) {
        this(tz);
        this.warningListener = warningListener;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Time createFromTime(int hours, int minutes, int seconds, int nanos) {
        if (hours < 0 || hours >= 24) {
            throw new DataReadException(Messages.getString("ResultSet.InvalidTimeValue", new Object[]{"" + hours + ":" + minutes + ":" + seconds}));
        }
        Calendar calendar = this.cal;
        synchronized (calendar) {
            this.cal.set(1970, 0, 1, hours, minutes, seconds);
            this.cal.set(14, 0);
            long ms = (long)(nanos / 1000000) + this.cal.getTimeInMillis();
            return new Time(ms);
        }
    }

    @Override
    public Time createFromTimestamp(int year, int month, int day, int hours, int minutes, int seconds, int nanos) {
        if (this.warningListener != null) {
            this.warningListener.warningEncountered(Messages.getString("ResultSet.PrecisionLostWarning", new Object[]{"java.sql.Time"}));
        }
        return this.createFromTime(hours, minutes, seconds, nanos);
    }

    @Override
    public String getTargetTypeName() {
        return Time.class.getName();
    }
}

