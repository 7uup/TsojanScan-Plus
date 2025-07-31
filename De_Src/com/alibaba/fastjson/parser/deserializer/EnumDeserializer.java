/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class EnumDeserializer
implements ObjectDeserializer {
    protected final Class<?> enumClass;
    protected final Enum[] enums;
    protected final Enum[] ordinalEnums;
    protected long[] enumNameHashCodes;

    public EnumDeserializer(Class<?> enumClass) {
        int i;
        this.enumClass = enumClass;
        this.ordinalEnums = (Enum[])enumClass.getEnumConstants();
        HashMap<Long, Object> enumMap = new HashMap<Long, Object>();
        for (i = 0; i < this.ordinalEnums.length; ++i) {
            Enum e = this.ordinalEnums[i];
            String name = e.name();
            JSONField jsonField = null;
            try {
                String jsonFieldName;
                Field field = enumClass.getField(name);
                jsonField = TypeUtils.getAnnotation(field, JSONField.class);
                if (jsonField != null && (jsonFieldName = jsonField.name()) != null && jsonFieldName.length() > 0) {
                    name = jsonFieldName;
                }
            } catch (Exception field) {
                // empty catch block
            }
            long hash = -3750763034362895579L;
            long hash_lower = -3750763034362895579L;
            for (int j = 0; j < name.length(); ++j) {
                int ch = name.charAt(j);
                hash ^= (long)ch;
                hash_lower ^= (long)(ch >= 65 && ch <= 90 ? ch + 32 : ch);
                hash *= 1099511628211L;
                hash_lower *= 1099511628211L;
            }
            enumMap.put(hash, e);
            if (hash != hash_lower) {
                enumMap.put(hash_lower, e);
            }
            if (jsonField == null) continue;
            for (String alterName : jsonField.alternateNames()) {
                long alterNameHash = -3750763034362895579L;
                for (int j = 0; j < alterName.length(); ++j) {
                    char ch = alterName.charAt(j);
                    alterNameHash ^= (long)ch;
                    alterNameHash *= 1099511628211L;
                }
                if (alterNameHash == hash || alterNameHash == hash_lower) continue;
                enumMap.put(alterNameHash, e);
            }
        }
        this.enumNameHashCodes = new long[enumMap.size()];
        i = 0;
        for (Long h2 : enumMap.keySet()) {
            this.enumNameHashCodes[i++] = h2;
        }
        Arrays.sort(this.enumNameHashCodes);
        this.enums = new Enum[this.enumNameHashCodes.length];
        for (i = 0; i < this.enumNameHashCodes.length; ++i) {
            Enum e;
            long hash = this.enumNameHashCodes[i];
            this.enums[i] = e = (Enum)enumMap.get(hash);
        }
    }

    public Enum getEnumByHashCode(long hashCode) {
        if (this.enums == null) {
            return null;
        }
        int enumIndex = Arrays.binarySearch(this.enumNameHashCodes, hashCode);
        if (enumIndex < 0) {
            return null;
        }
        return this.enums[enumIndex];
    }

    public Enum<?> valueOf(int ordinal) {
        return this.ordinalEnums[ordinal];
    }

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        try {
            JSONLexer lexer = parser.lexer;
            int token = lexer.token();
            if (token == 2) {
                int intValue = lexer.intValue();
                lexer.nextToken(16);
                if (intValue < 0 || intValue >= this.ordinalEnums.length) {
                    throw new JSONException("parse enum " + this.enumClass.getName() + " error, value : " + intValue);
                }
                return (T)this.ordinalEnums[intValue];
            }
            if (token == 4) {
                String name = lexer.stringVal();
                lexer.nextToken(16);
                if (name.length() == 0) {
                    return null;
                }
                long hash = -3750763034362895579L;
                long hash_lower = -3750763034362895579L;
                for (int j = 0; j < name.length(); ++j) {
                    int ch = name.charAt(j);
                    hash ^= (long)ch;
                    hash_lower ^= (long)(ch >= 65 && ch <= 90 ? ch + 32 : ch);
                    hash *= 1099511628211L;
                    hash_lower *= 1099511628211L;
                }
                Enum e = this.getEnumByHashCode(hash);
                if (e == null && hash_lower != hash) {
                    e = this.getEnumByHashCode(hash_lower);
                }
                if (e == null && lexer.isEnabled(Feature.ErrorOnEnumNotMatch)) {
                    throw new JSONException("not match enum value, " + this.enumClass.getName() + " : " + name);
                }
                return (T)e;
            }
            if (token == 8) {
                Object value = null;
                lexer.nextToken(16);
                return null;
            }
            Object value = parser.parse();
            throw new JSONException("parse enum " + this.enumClass.getName() + " error, value : " + value);
        } catch (JSONException e) {
            throw e;
        } catch (Exception e) {
            throw new JSONException(e.getMessage(), e);
        }
    }

    @Override
    public int getFastMatchToken() {
        return 2;
    }
}

