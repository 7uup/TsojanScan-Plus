/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.ParseContext;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.ParameterizedTypeImpl;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ArrayListTypeFieldDeserializer
extends FieldDeserializer {
    private final Type itemType;
    private int itemFastMatchToken;
    private ObjectDeserializer deserializer;

    public ArrayListTypeFieldDeserializer(ParserConfig mapping, Class<?> clazz, FieldInfo fieldInfo) {
        super(clazz, fieldInfo);
        Type fieldType = fieldInfo.fieldType;
        if (fieldType instanceof ParameterizedType) {
            WildcardType wildcardType;
            Type[] upperBounds;
            Type argType = ((ParameterizedType)fieldInfo.fieldType).getActualTypeArguments()[0];
            if (argType instanceof WildcardType && (upperBounds = (wildcardType = (WildcardType)argType).getUpperBounds()).length == 1) {
                argType = upperBounds[0];
            }
            this.itemType = argType;
        } else {
            this.itemType = Object.class;
        }
    }

    @Override
    public int getFastMatchToken() {
        return 14;
    }

    @Override
    public void parseField(DefaultJSONParser parser, Object object, Type objectType, Map<String, Object> fieldValues) {
        JSONLexer lexer = parser.lexer;
        int token = lexer.token();
        if (token == 8 || token == 4 && lexer.stringVal().length() == 0) {
            if (object == null) {
                fieldValues.put(this.fieldInfo.name, null);
            } else {
                this.setValue(object, null);
            }
            return;
        }
        ArrayList list = new ArrayList();
        ParseContext context = parser.getContext();
        parser.setContext(context, object, this.fieldInfo.name);
        this.parseArray(parser, objectType, list);
        parser.setContext(context);
        if (object == null) {
            fieldValues.put(this.fieldInfo.name, list);
        } else {
            this.setValue(object, list);
        }
    }

    public final void parseArray(DefaultJSONParser parser, Type objectType, Collection array) {
        ObjectDeserializer itemTypeDeser;
        Type itemType;
        block21: {
            block19: {
                ParameterizedType parameterizedItemType;
                Type[] itemActualTypeArgs;
                block20: {
                    itemType = this.itemType;
                    itemTypeDeser = this.deserializer;
                    if (!(objectType instanceof ParameterizedType)) break block19;
                    if (!(itemType instanceof TypeVariable)) break block20;
                    TypeVariable typeVar = (TypeVariable)itemType;
                    ParameterizedType paramType = (ParameterizedType)objectType;
                    Class objectClass = null;
                    if (paramType.getRawType() instanceof Class) {
                        objectClass = (Class)paramType.getRawType();
                    }
                    int paramIndex = -1;
                    if (objectClass != null) {
                        int size = objectClass.getTypeParameters().length;
                        for (int i = 0; i < size; ++i) {
                            TypeVariable item = objectClass.getTypeParameters()[i];
                            if (!item.getName().equals(typeVar.getName())) continue;
                            paramIndex = i;
                            break;
                        }
                    }
                    if (paramIndex == -1 || (itemType = paramType.getActualTypeArguments()[paramIndex]).equals(this.itemType)) break block21;
                    itemTypeDeser = parser.getConfig().getDeserializer(itemType);
                    break block21;
                }
                if (!(itemType instanceof ParameterizedType) || (itemActualTypeArgs = (parameterizedItemType = (ParameterizedType)itemType).getActualTypeArguments()).length != 1 || !(itemActualTypeArgs[0] instanceof TypeVariable)) break block21;
                TypeVariable typeVar = (TypeVariable)itemActualTypeArgs[0];
                ParameterizedType paramType = (ParameterizedType)objectType;
                Class objectClass = null;
                if (paramType.getRawType() instanceof Class) {
                    objectClass = (Class)paramType.getRawType();
                }
                int paramIndex = -1;
                if (objectClass != null) {
                    int size = objectClass.getTypeParameters().length;
                    for (int i = 0; i < size; ++i) {
                        TypeVariable item = objectClass.getTypeParameters()[i];
                        if (!item.getName().equals(typeVar.getName())) continue;
                        paramIndex = i;
                        break;
                    }
                }
                if (paramIndex != -1) {
                    itemActualTypeArgs[0] = paramType.getActualTypeArguments()[paramIndex];
                    itemType = TypeReference.intern(new ParameterizedTypeImpl(itemActualTypeArgs, parameterizedItemType.getOwnerType(), parameterizedItemType.getRawType()));
                }
                break block21;
            }
            if (itemType instanceof TypeVariable && objectType instanceof Class) {
                Class objectClass = (Class)objectType;
                TypeVariable typeVar = (TypeVariable)itemType;
                objectClass.getTypeParameters();
                int size = objectClass.getTypeParameters().length;
                for (int i = 0; i < size; ++i) {
                    TypeVariable item = objectClass.getTypeParameters()[i];
                    if (!item.getName().equals(typeVar.getName())) continue;
                    Type[] bounds = item.getBounds();
                    if (bounds.length != 1) break;
                    itemType = bounds[0];
                    break;
                }
            }
        }
        JSONLexer lexer = parser.lexer;
        int token = lexer.token();
        if (token == 14) {
            if (itemTypeDeser == null) {
                itemTypeDeser = this.deserializer = parser.getConfig().getDeserializer(itemType);
                this.itemFastMatchToken = this.deserializer.getFastMatchToken();
            }
            lexer.nextToken(this.itemFastMatchToken);
            int i = 0;
            while (true) {
                if (lexer.isEnabled(Feature.AllowArbitraryCommas)) {
                    while (lexer.token() == 16) {
                        lexer.nextToken();
                    }
                }
                if (lexer.token() == 15) break;
                Object val = itemTypeDeser.deserialze(parser, itemType, i);
                array.add(val);
                parser.checkListResolve(array);
                if (lexer.token() == 16) {
                    lexer.nextToken(this.itemFastMatchToken);
                }
                ++i;
            }
            lexer.nextToken(16);
        } else if (token == 4 && this.fieldInfo.unwrapped) {
            String str = lexer.stringVal();
            lexer.nextToken();
            DefaultJSONParser valueParser = new DefaultJSONParser(str);
            valueParser.parseArray(array);
        } else {
            if (itemTypeDeser == null) {
                itemTypeDeser = this.deserializer = parser.getConfig().getDeserializer(itemType);
            }
            Object val = itemTypeDeser.deserialze(parser, itemType, 0);
            array.add(val);
            parser.checkListResolve(array);
        }
    }
}

