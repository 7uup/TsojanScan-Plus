/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text.lookup;

import java.util.Date;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.commons.text.lookup.AbstractStringLookup;
import org.apache.commons.text.lookup.IllegalArgumentExceptions;

final class DateStringLookup
extends AbstractStringLookup {
    static final DateStringLookup INSTANCE = new DateStringLookup();

    private DateStringLookup() {
    }

    private String formatDate(long date, String format) {
        FastDateFormat dateFormat = null;
        if (format != null) {
            try {
                dateFormat = FastDateFormat.getInstance(format);
            } catch (Exception ex) {
                throw IllegalArgumentExceptions.format(ex, "Invalid date format: [%s]", format);
            }
        }
        if (dateFormat == null) {
            dateFormat = FastDateFormat.getInstance();
        }
        return dateFormat.format(new Date(date));
    }

    @Override
    public String lookup(String key) {
        return this.formatDate(System.currentTimeMillis(), key);
    }
}

