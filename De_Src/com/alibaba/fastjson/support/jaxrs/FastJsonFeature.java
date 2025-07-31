/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  javax.ws.rs.RuntimeType
 *  javax.ws.rs.core.Configuration
 *  javax.ws.rs.core.Feature
 *  javax.ws.rs.core.FeatureContext
 *  javax.ws.rs.ext.MessageBodyReader
 *  javax.ws.rs.ext.MessageBodyWriter
 *  org.glassfish.jersey.CommonProperties
 *  org.glassfish.jersey.internal.util.PropertiesHelper
 */
package com.alibaba.fastjson.support.jaxrs;

import com.alibaba.fastjson.support.jaxrs.FastJsonProvider;
import java.util.Map;
import javax.ws.rs.RuntimeType;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.internal.util.PropertiesHelper;

public class FastJsonFeature
implements Feature {
    private static final String JSON_FEATURE = FastJsonFeature.class.getSimpleName();

    public boolean configure(FeatureContext context) {
        try {
            Configuration config = context.getConfiguration();
            String jsonFeature = (String)CommonProperties.getValue((Map)config.getProperties(), (RuntimeType)config.getRuntimeType(), (String)"jersey.config.jsonFeature", (Object)JSON_FEATURE, String.class);
            if (!JSON_FEATURE.equalsIgnoreCase(jsonFeature)) {
                return false;
            }
            context.property(PropertiesHelper.getPropertyNameForRuntime((String)"jersey.config.jsonFeature", (RuntimeType)config.getRuntimeType()), (Object)JSON_FEATURE);
            if (!config.isRegistered(FastJsonProvider.class)) {
                context.register(FastJsonProvider.class, new Class[]{MessageBodyReader.class, MessageBodyWriter.class});
            }
        } catch (NoSuchMethodError noSuchMethodError) {
            // empty catch block
        }
        return true;
    }
}

