/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc.admin;

import com.mysql.cj.util.TimeUtil;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class TimezoneDump {
    private static final String DEFAULT_URL = "jdbc:mysql:///test";

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void main(String[] args2) throws Exception {
        String jdbcUrl = DEFAULT_URL;
        if (args2.length == 1 && args2[0] != null) {
            jdbcUrl = args2[0];
        }
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        try (ResultSet rs = null;){
            rs = DriverManager.getConnection(jdbcUrl).createStatement().executeQuery("SHOW VARIABLES LIKE 'timezone'");
            while (rs.next()) {
                String timezoneFromServer = rs.getString(2);
                System.out.println("MySQL timezone name: " + timezoneFromServer);
                String canonicalTimezone = TimeUtil.getCanonicalTimezone(timezoneFromServer, null);
                System.out.println("Java timezone name: " + canonicalTimezone);
            }
        }
    }
}

