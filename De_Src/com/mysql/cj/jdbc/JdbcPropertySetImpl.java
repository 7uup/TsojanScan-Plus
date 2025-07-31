/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc;

import com.mysql.cj.conf.DefaultPropertySet;
import com.mysql.cj.conf.PropertyDefinition;
import com.mysql.cj.conf.PropertyDefinitions;
import com.mysql.cj.conf.RuntimeProperty;
import com.mysql.cj.jdbc.JdbcPropertySet;
import com.mysql.cj.util.StringUtils;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcPropertySetImpl
extends DefaultPropertySet
implements JdbcPropertySet {
    private static final long serialVersionUID = -8223499903182568260L;

    @Override
    public void postInitialization() {
        String testEncoding;
        if (this.getIntegerProperty("maxRows").getValue() == 0) {
            super.getProperty("maxRows").setValue(-1, null);
        }
        if ((testEncoding = this.getStringProperty("characterEncoding").getValue()) != null) {
            String testString = "abc";
            StringUtils.getBytes(testString, testEncoding);
        }
        if (this.getBooleanProperty("useCursorFetch").getValue().booleanValue()) {
            super.getProperty("useServerPrepStmts").setValue(true);
        }
    }

    @Override
    public DriverPropertyInfo[] exposeAsDriverPropertyInfo(Properties info, int slotsToReserve) throws SQLException {
        this.initializeProperties(info);
        int numProperties = PropertyDefinitions.PROPERTY_NAME_TO_PROPERTY_DEFINITION.size();
        int listSize = numProperties + slotsToReserve;
        DriverPropertyInfo[] driverProperties = new DriverPropertyInfo[listSize];
        int i = slotsToReserve;
        for (String propName : PropertyDefinitions.PROPERTY_NAME_TO_PROPERTY_DEFINITION.keySet()) {
            driverProperties[i++] = this.getAsDriverPropertyInfo(this.getProperty(propName));
        }
        return driverProperties;
    }

    private DriverPropertyInfo getAsDriverPropertyInfo(RuntimeProperty<?> pr) {
        PropertyDefinition<?> pdef = pr.getPropertyDefinition();
        DriverPropertyInfo dpi = new DriverPropertyInfo(pdef.getName(), null);
        dpi.choices = pdef.getAllowableValues();
        dpi.value = pr.getStringValue() != null ? pr.getStringValue() : null;
        dpi.required = false;
        dpi.description = pdef.getDescription();
        return dpi;
    }
}

