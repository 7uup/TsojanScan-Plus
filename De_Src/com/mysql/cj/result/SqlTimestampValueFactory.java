/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.result;

import com.mysql.cj.Messages;
import com.mysql.cj.exceptions.DataReadException;
import com.mysql.cj.result.DefaultValueFactory;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class SqlTimestampValueFactory
extends DefaultValueFactory<Timestamp> {
    private TimeZone tz;
    private Calendar cal;

    public SqlTimestampValueFactory(TimeZone tz) {
        this.tz = tz;
        this.cal = Calendar.getInstance(this.tz, Locale.US);
        this.cal.setLenient(false);
    }

    public TimeZone getTimeZone() {
        return this.tz;
    }

    @Override
    public Timestamp createFromDate(int year, int month, int day) {
        return this.createFromTimestamp(year, month, day, 0, 0, 0, 0);
    }

    @Override
    public Timestamp createFromTime(int hours, int minutes, int seconds, int nanos) {
        if (hours < 0 || hours >= 24) {
            throw new DataReadException(Messages.getString("ResultSet.InvalidTimeValue", new Object[]{"" + hours + ":" + minutes + ":" + seconds}));
        }
        return this.createFromTimestamp(1970, 1, 1, hours, minutes, seconds, nanos);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Timestamp createFromTimestamp(int year, int month, int day, int hours, int minutes, int seconds, int nanos) {
        if (year == 0 && month == 0 && day == 0) {
            throw new DataReadException(Messages.getString("ResultSet.InvalidZeroDate"));
        }
        Calendar calendar = this.cal;
        synchronized (calendar) {
            this.cal.set(year, month - 1, day, hours, minutes, seconds);
            Timestamp ts = new Timestamp(this.cal.getTimeInMillis());
            ts.setNanos(nanos);
            return ts;
        }
    }

    @Override
    public String getTargetTypeName() {
        return Timestamp.class.getName();
    }
}

