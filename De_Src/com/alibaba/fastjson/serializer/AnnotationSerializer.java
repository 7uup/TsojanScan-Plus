/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;

public class AnnotationSerializer
implements ObjectSerializer {
    private static volatile Class sun_AnnotationType = null;
    private static volatile boolean sun_AnnotationType_error = false;
    private static volatile Method sun_AnnotationType_getInstance = null;
    private static volatile Method sun_AnnotationType_members = null;
    public static AnnotationSerializer instance = new AnnotationSerializer();

    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        Class<?> objClass = object.getClass();
        Class<?>[] interfaces = objClass.getInterfaces();
        if (interfaces.length == 1 && interfaces[0].isAnnotation()) {
            Map members;
            Object type;
            Class<?> annotationClass = interfaces[0];
            if (sun_AnnotationType == null && !sun_AnnotationType_error) {
                try {
                    sun_AnnotationType = Class.forName("sun.reflect.annotation.AnnotationType");
                } catch (Throwable ex) {
                    sun_AnnotationType_error = true;
                    throw new JSONException("not support Type Annotation.", ex);
                }
            }
            if (sun_AnnotationType == null) {
                throw new JSONException("not support Type Annotation.");
            }
            if (sun_AnnotationType_getInstance == null && !sun_AnnotationType_error) {
                try {
                    sun_AnnotationType_getInstance = sun_AnnotationType.getMethod("getInstance", Class.class);
                } catch (Throwable ex) {
                    sun_AnnotationType_error = true;
                    throw new JSONException("not support Type Annotation.", ex);
                }
            }
            if (sun_AnnotationType_members == null && !sun_AnnotationType_error) {
                try {
                    sun_AnnotationType_members = sun_AnnotationType.getMethod("members", new Class[0]);
                } catch (Throwable ex) {
                    sun_AnnotationType_error = true;
                    throw new JSONException("not support Type Annotation.", ex);
                }
            }
            if (sun_AnnotationType_getInstance == null || sun_AnnotationType_error) {
                throw new JSONException("not support Type Annotation.");
            }
            try {
                type = sun_AnnotationType_getInstance.invoke(null, annotationClass);
            } catch (Throwable ex) {
                sun_AnnotationType_error = true;
                throw new JSONException("not support Type Annotation.", ex);
            }
            try {
                members = (Map)sun_AnnotationType_members.invoke(type, new Object[0]);
            } catch (Throwable ex) {
                sun_AnnotationType_error = true;
                throw new JSONException("not support Type Annotation.", ex);
            }
            JSONObject json = new JSONObject(members.size());
            Iterator iterator2 = members.entrySet().iterator();
            Object val = null;
            while (iterator2.hasNext()) {
                Map.Entry entry = iterator2.next();
                try {
                    val = ((Method)entry.getValue()).invoke(object, new Object[0]);
                } catch (IllegalAccessException illegalAccessException) {
                } catch (InvocationTargetException invocationTargetException) {
                    // empty catch block
                }
                json.put((String)entry.getKey(), JSON.toJSON(val));
            }
            serializer.write(json);
            return;
        }
    }
}

