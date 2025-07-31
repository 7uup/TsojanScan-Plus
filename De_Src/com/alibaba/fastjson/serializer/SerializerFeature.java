/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public enum SerializerFeature {
    QuoteFieldNames,
    UseSingleQuotes,
    WriteMapNullValue,
    WriteEnumUsingToString,
    WriteEnumUsingName,
    UseISO8601DateFormat,
    WriteNullListAsEmpty,
    WriteNullStringAsEmpty,
    WriteNullNumberAsZero,
    WriteNullBooleanAsFalse,
    SkipTransientField,
    SortField,
    WriteTabAsSpecial,
    PrettyFormat,
    WriteClassName,
    DisableCircularReferenceDetect,
    WriteSlashAsSpecial,
    BrowserCompatible,
    WriteDateUseDateFormat,
    NotWriteRootClassName,
    DisableCheckSpecialChar,
    BeanToArray,
    WriteNonStringKeyAsString,
    NotWriteDefaultValue,
    BrowserSecure,
    IgnoreNonFieldGetter,
    WriteNonStringValueAsString,
    IgnoreErrorGetter,
    WriteBigDecimalAsPlain,
    MapSortField;

    public final int mask = 1 << this.ordinal();
    public static final SerializerFeature[] EMPTY;
    public static final int WRITE_MAP_NULL_FEATURES;

    public final int getMask() {
        return this.mask;
    }

    public static boolean isEnabled(int features, SerializerFeature feature) {
        return (features & feature.mask) != 0;
    }

    public static boolean isEnabled(int features, int featuresB, SerializerFeature feature) {
        int mask = feature.mask;
        return (features & mask) != 0 || (featuresB & mask) != 0;
    }

    public static int config(int features, SerializerFeature feature, boolean state) {
        features = state ? (features |= feature.mask) : (features &= ~feature.mask);
        return features;
    }

    public static int of(SerializerFeature[] features) {
        if (features == null) {
            return 0;
        }
        int value = 0;
        for (SerializerFeature feature : features) {
            value |= feature.mask;
        }
        return value;
    }

    static {
        EMPTY = new SerializerFeature[0];
        WRITE_MAP_NULL_FEATURES = WriteMapNullValue.getMask() | WriteNullBooleanAsFalse.getMask() | WriteNullListAsEmpty.getMask() | WriteNullNumberAsZero.getMask() | WriteNullStringAsEmpty.getMask();
    }
}

