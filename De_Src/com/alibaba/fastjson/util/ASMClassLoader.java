/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONAware;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.JSONPathException;
import com.alibaba.fastjson.JSONReader;
import com.alibaba.fastjson.JSONStreamAware;
import com.alibaba.fastjson.JSONWriter;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONLexerBase;
import com.alibaba.fastjson.parser.JSONReaderScanner;
import com.alibaba.fastjson.parser.JSONScanner;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.ParseContext;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.SymbolTable;
import com.alibaba.fastjson.parser.deserializer.AutowiredObjectDeserializer;
import com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessable;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.AfterFilter;
import com.alibaba.fastjson.serializer.BeanContext;
import com.alibaba.fastjson.serializer.BeforeFilter;
import com.alibaba.fastjson.serializer.ContextObjectSerializer;
import com.alibaba.fastjson.serializer.ContextValueFilter;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.JavaBeanSerializer;
import com.alibaba.fastjson.serializer.LabelFilter;
import com.alibaba.fastjson.serializer.Labels;
import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import com.alibaba.fastjson.serializer.SerialContext;
import com.alibaba.fastjson.serializer.SerializeBeanInfo;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializeFilterable;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.IOUtils;
import com.alibaba.fastjson.util.IdentityHashMap;
import com.alibaba.fastjson.util.JavaBeanInfo;
import com.alibaba.fastjson.util.ParameterizedTypeImpl;
import com.alibaba.fastjson.util.TypeUtils;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import java.util.HashMap;
import java.util.Map;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ASMClassLoader
extends ClassLoader {
    private static ProtectionDomain DOMAIN;
    private static Map<String, Class<?>> classMapping;

    public ASMClassLoader() {
        super(ASMClassLoader.getParentClassLoader());
    }

    public ASMClassLoader(ClassLoader parent) {
        super(parent);
    }

    static ClassLoader getParentClassLoader() {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        if (contextClassLoader != null) {
            try {
                contextClassLoader.loadClass(JSON.class.getName());
                return contextClassLoader;
            } catch (ClassNotFoundException classNotFoundException) {
                // empty catch block
            }
        }
        return JSON.class.getClassLoader();
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> mappingClass = classMapping.get(name);
        if (mappingClass != null) {
            return mappingClass;
        }
        return super.loadClass(name, resolve);
    }

    public Class<?> defineClassPublic(String name, byte[] b, int off, int len) throws ClassFormatError {
        Class<?> clazz = this.defineClass(name, b, off, len, DOMAIN);
        return clazz;
    }

    public boolean isExternalClass(Class<?> clazz) {
        ClassLoader classLoader = clazz.getClassLoader();
        if (classLoader == null) {
            return false;
        }
        for (ClassLoader current = this; current != null; current = current.getParent()) {
            if (current != classLoader) continue;
            return false;
        }
        return true;
    }

    static {
        Class[] jsonClasses;
        classMapping = new HashMap();
        DOMAIN = (ProtectionDomain)AccessController.doPrivileged(new PrivilegedAction<Object>(){

            @Override
            public Object run() {
                return ASMClassLoader.class.getProtectionDomain();
            }
        });
        for (Class clazz : jsonClasses = new Class[]{JSON.class, JSONObject.class, JSONArray.class, JSONPath.class, JSONAware.class, JSONException.class, JSONPathException.class, JSONReader.class, JSONStreamAware.class, JSONWriter.class, TypeReference.class, FieldInfo.class, TypeUtils.class, IOUtils.class, IdentityHashMap.class, ParameterizedTypeImpl.class, JavaBeanInfo.class, ObjectSerializer.class, JavaBeanSerializer.class, SerializeFilterable.class, SerializeBeanInfo.class, JSONSerializer.class, SerializeWriter.class, SerializeFilter.class, Labels.class, LabelFilter.class, ContextValueFilter.class, AfterFilter.class, BeforeFilter.class, NameFilter.class, PropertyFilter.class, PropertyPreFilter.class, ValueFilter.class, SerializerFeature.class, ContextObjectSerializer.class, SerialContext.class, SerializeConfig.class, JavaBeanDeserializer.class, ParserConfig.class, DefaultJSONParser.class, JSONLexer.class, JSONLexerBase.class, ParseContext.class, JSONToken.class, SymbolTable.class, Feature.class, JSONScanner.class, JSONReaderScanner.class, AutowiredObjectDeserializer.class, ObjectDeserializer.class, ExtraProcessor.class, ExtraProcessable.class, ExtraTypeProvider.class, BeanContext.class, FieldDeserializer.class, DefaultFieldDeserializer.class}) {
            classMapping.put(clazz.getName(), clazz);
        }
    }
}

