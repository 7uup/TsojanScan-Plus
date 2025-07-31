/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.gson.internal;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Excluder
implements TypeAdapterFactory,
Cloneable {
    private static final double IGNORE_VERSIONS = -1.0;
    public static final Excluder DEFAULT = new Excluder();
    private double version = -1.0;
    private int modifiers = 136;
    private boolean serializeInnerClasses = true;
    private boolean requireExpose;
    private List<ExclusionStrategy> serializationStrategies = Collections.emptyList();
    private List<ExclusionStrategy> deserializationStrategies = Collections.emptyList();

    protected Excluder clone() {
        try {
            return (Excluder)super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError((Object)e);
        }
    }

    public Excluder withVersion(double ignoreVersionsAfter) {
        Excluder result = this.clone();
        result.version = ignoreVersionsAfter;
        return result;
    }

    public Excluder withModifiers(int ... modifiers) {
        Excluder result = this.clone();
        result.modifiers = 0;
        for (int modifier : modifiers) {
            result.modifiers |= modifier;
        }
        return result;
    }

    public Excluder disableInnerClassSerialization() {
        Excluder result = this.clone();
        result.serializeInnerClasses = false;
        return result;
    }

    public Excluder excludeFieldsWithoutExposeAnnotation() {
        Excluder result = this.clone();
        result.requireExpose = true;
        return result;
    }

    public Excluder withExclusionStrategy(ExclusionStrategy exclusionStrategy, boolean serialization, boolean deserialization) {
        Excluder result = this.clone();
        if (serialization) {
            result.serializationStrategies = new ArrayList<ExclusionStrategy>(this.serializationStrategies);
            result.serializationStrategies.add(exclusionStrategy);
        }
        if (deserialization) {
            result.deserializationStrategies = new ArrayList<ExclusionStrategy>(this.deserializationStrategies);
            result.deserializationStrategies.add(exclusionStrategy);
        }
        return result;
    }

    @Override
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> type) {
        boolean skipDeserialize;
        Class<T> rawType = type.getRawType();
        boolean excludeClass = this.excludeClassChecks(rawType);
        final boolean skipSerialize = excludeClass || this.excludeClassInStrategy(rawType, true);
        boolean bl = skipDeserialize = excludeClass || this.excludeClassInStrategy(rawType, false);
        if (!skipSerialize && !skipDeserialize) {
            return null;
        }
        return new TypeAdapter<T>(){
            private TypeAdapter<T> delegate;

            @Override
            public T read(JsonReader in) throws IOException {
                if (skipDeserialize) {
                    in.skipValue();
                    return null;
                }
                return this.delegate().read(in);
            }

            @Override
            public void write(JsonWriter out, T value) throws IOException {
                if (skipSerialize) {
                    out.nullValue();
                    return;
                }
                this.delegate().write(out, value);
            }

            private TypeAdapter<T> delegate() {
                TypeAdapter d = this.delegate;
                return d != null ? d : (this.delegate = gson.getDelegateAdapter(Excluder.this, type));
            }
        };
    }

    public boolean excludeField(Field field, boolean serialize) {
        List<ExclusionStrategy> list;
        Expose annotation;
        if ((this.modifiers & field.getModifiers()) != 0) {
            return true;
        }
        if (this.version != -1.0 && !this.isValidVersion(field.getAnnotation(Since.class), field.getAnnotation(Until.class))) {
            return true;
        }
        if (field.isSynthetic()) {
            return true;
        }
        if (this.requireExpose && ((annotation = field.getAnnotation(Expose.class)) == null || (serialize ? !annotation.serialize() : !annotation.deserialize()))) {
            return true;
        }
        if (!this.serializeInnerClasses && this.isInnerClass(field.getType())) {
            return true;
        }
        if (this.isAnonymousOrLocal(field.getType())) {
            return true;
        }
        List<ExclusionStrategy> list2 = list = serialize ? this.serializationStrategies : this.deserializationStrategies;
        if (!list.isEmpty()) {
            FieldAttributes fieldAttributes = new FieldAttributes(field);
            for (ExclusionStrategy exclusionStrategy : list) {
                if (!exclusionStrategy.shouldSkipField(fieldAttributes)) continue;
                return true;
            }
        }
        return false;
    }

    private boolean excludeClassChecks(Class<?> clazz) {
        if (this.version != -1.0 && !this.isValidVersion(clazz.getAnnotation(Since.class), clazz.getAnnotation(Until.class))) {
            return true;
        }
        if (!this.serializeInnerClasses && this.isInnerClass(clazz)) {
            return true;
        }
        return this.isAnonymousOrLocal(clazz);
    }

    public boolean excludeClass(Class<?> clazz, boolean serialize) {
        return this.excludeClassChecks(clazz) || this.excludeClassInStrategy(clazz, serialize);
    }

    private boolean excludeClassInStrategy(Class<?> clazz, boolean serialize) {
        List<ExclusionStrategy> list = serialize ? this.serializationStrategies : this.deserializationStrategies;
        for (ExclusionStrategy exclusionStrategy : list) {
            if (!exclusionStrategy.shouldSkipClass(clazz)) continue;
            return true;
        }
        return false;
    }

    private boolean isAnonymousOrLocal(Class<?> clazz) {
        return !Enum.class.isAssignableFrom(clazz) && (clazz.isAnonymousClass() || clazz.isLocalClass());
    }

    private boolean isInnerClass(Class<?> clazz) {
        return clazz.isMemberClass() && !this.isStatic(clazz);
    }

    private boolean isStatic(Class<?> clazz) {
        return (clazz.getModifiers() & 8) != 0;
    }

    private boolean isValidVersion(Since since, Until until) {
        return this.isValidSince(since) && this.isValidUntil(until);
    }

    private boolean isValidSince(Since annotation) {
        double annotationVersion;
        return annotation == null || !((annotationVersion = annotation.value()) > this.version);
    }

    private boolean isValidUntil(Until annotation) {
        double annotationVersion;
        return annotation == null || !((annotationVersion = annotation.value()) <= this.version);
    }
}

