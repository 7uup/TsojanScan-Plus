/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONScanner;

public class JSONPatch {
    public static String apply(String original, String patch) {
        Object object = JSONPatch.apply(JSON.parse(original, Feature.OrderedField), patch);
        return JSON.toJSONString(object);
    }

    public static Object apply(Object object, String patch) {
        Operation[] operations = JSONPatch.isObject(patch) ? new Operation[]{JSON.parseObject(patch, Operation.class)} : JSON.parseObject(patch, Operation[].class);
        block7: for (Operation op : operations) {
            JSONPath path = JSONPath.compile(op.path);
            switch (op.type) {
                case add: {
                    path.patchAdd(object, op.value, false);
                    continue block7;
                }
                case replace: {
                    path.patchAdd(object, op.value, true);
                    continue block7;
                }
                case remove: {
                    path.remove(object);
                    continue block7;
                }
                case copy: 
                case move: {
                    boolean success;
                    JSONPath from = JSONPath.compile(op.from);
                    Object fromValue = from.eval(object);
                    if (op.type == OperationType.move && !(success = from.remove(object))) {
                        throw new JSONException("json patch move error : " + op.from + " -> " + op.path);
                    }
                    path.set(object, fromValue);
                    continue block7;
                }
                case test: {
                    Object result = path.eval(object);
                    if (result == null) {
                        return op.value == null;
                    }
                    return result.equals(op.value);
                }
            }
        }
        return object;
    }

    private static boolean isObject(String patch) {
        if (patch == null) {
            return false;
        }
        for (int i = 0; i < patch.length(); ++i) {
            char ch = patch.charAt(i);
            if (JSONScanner.isWhitespace(ch)) continue;
            return ch == '{';
        }
        return false;
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static enum OperationType {
        add,
        remove,
        replace,
        move,
        copy,
        test;

    }

    @JSONType(orders={"op", "from", "path", "value"})
    public static class Operation {
        @JSONField(name="op")
        public OperationType type;
        public String from;
        public String path;
        public Object value;
    }
}

