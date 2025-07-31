/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  javax.annotation.Priority
 *  javax.ws.rs.core.Configuration
 *  javax.ws.rs.core.FeatureContext
 *  org.glassfish.jersey.internal.spi.AutoDiscoverable
 */
package com.alibaba.fastjson.support.jaxrs;

import com.alibaba.fastjson.support.jaxrs.FastJsonFeature;
import javax.annotation.Priority;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.FeatureContext;
import org.glassfish.jersey.internal.spi.AutoDiscoverable;

@Priority(value=1999)
public class FastJsonAutoDiscoverable
implements AutoDiscoverable {
    public static final String FASTJSON_AUTO_DISCOVERABLE = "fastjson.auto.discoverable";
    public static volatile boolean autoDiscover = true;

    public void configure(FeatureContext context) {
        Configuration config = context.getConfiguration();
        if (!config.isRegistered(FastJsonFeature.class) && autoDiscover) {
            context.register(FastJsonFeature.class);
        }
    }

    static {
        try {
            autoDiscover = Boolean.parseBoolean(System.getProperty(FASTJSON_AUTO_DISCOVERABLE, String.valueOf(autoDiscover)));
        } catch (Throwable throwable) {
            // empty catch block
        }
    }
}

