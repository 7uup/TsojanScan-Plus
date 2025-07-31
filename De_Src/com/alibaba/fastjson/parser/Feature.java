/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.parser;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public enum Feature {
    AutoCloseSource,
    AllowComment,
    AllowUnQuotedFieldNames,
    AllowSingleQuotes,
    InternFieldNames,
    AllowISO8601DateFormat,
    AllowArbitraryCommas,
    UseBigDecimal,
    IgnoreNotMatch,
    SortFeidFastMatch,
    DisableASM,
    DisableCircularReferenceDetect,
    InitStringFieldAsEmpty,
    SupportArrayToBean,
    OrderedField,
    DisableSpecialKeyDetect,
    UseObjectArray,
    SupportNonPublicField,
    IgnoreAutoType,
    DisableFieldSmartMatch,
    SupportAutoType,
    NonStringKeyAsString,
    CustomMapDeserializer,
    ErrorOnEnumNotMatch,
    SafeMode,
    TrimStringFieldValue,
    UseNativeJavaObject;

    public final int mask = 1 << this.ordinal();

    public final int getMask() {
        return this.mask;
    }

    public static boolean isEnabled(int features, Feature feature) {
        return (features & feature.mask) != 0;
    }

    public static int config(int features, Feature feature, boolean state) {
        features = state ? (features |= feature.mask) : (features &= ~feature.mask);
        return features;
    }

    public static int of(Feature[] features) {
        if (features == null) {
            return 0;
        }
        int value = 0;
        for (Feature feature : features) {
            value |= feature.mask;
        }
        return value;
    }
}

