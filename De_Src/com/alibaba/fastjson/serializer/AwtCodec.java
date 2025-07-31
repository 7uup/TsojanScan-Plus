/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.ParseContext;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class AwtCodec
implements ObjectSerializer,
ObjectDeserializer {
    public static final AwtCodec instance = new AwtCodec();

    public static boolean support(Class<?> clazz) {
        return clazz == Point.class || clazz == Rectangle.class || clazz == Font.class || clazz == Color.class;
    }

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = serializer.out;
        if (object == null) {
            out.writeNull();
            return;
        }
        char sep = '{';
        if (object instanceof Point) {
            Point font = (Point)object;
            sep = this.writeClassName(out, Point.class, sep);
            out.writeFieldValue(sep, "x", font.x);
            out.writeFieldValue(',', "y", font.y);
        } else if (object instanceof Font) {
            Font font = (Font)object;
            sep = this.writeClassName(out, Font.class, sep);
            out.writeFieldValue(sep, "name", font.getName());
            out.writeFieldValue(',', "style", font.getStyle());
            out.writeFieldValue(',', "size", font.getSize());
        } else if (object instanceof Rectangle) {
            Rectangle rectangle = (Rectangle)object;
            sep = this.writeClassName(out, Rectangle.class, sep);
            out.writeFieldValue(sep, "x", rectangle.x);
            out.writeFieldValue(',', "y", rectangle.y);
            out.writeFieldValue(',', "width", rectangle.width);
            out.writeFieldValue(',', "height", rectangle.height);
        } else if (object instanceof Color) {
            Color color = (Color)object;
            sep = this.writeClassName(out, Color.class, sep);
            out.writeFieldValue(sep, "r", color.getRed());
            out.writeFieldValue(',', "g", color.getGreen());
            out.writeFieldValue(',', "b", color.getBlue());
            if (color.getAlpha() > 0) {
                out.writeFieldValue(',', "alpha", color.getAlpha());
            }
        } else {
            throw new JSONException("not support awt class : " + object.getClass().getName());
        }
        out.write(125);
    }

    protected char writeClassName(SerializeWriter out, Class<?> clazz, char sep) {
        if (out.isEnabled(SerializerFeature.WriteClassName)) {
            out.write(123);
            out.writeFieldName(JSON.DEFAULT_TYPE_KEY);
            out.writeString(clazz.getName());
            sep = (char)44;
        }
        return sep;
    }

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        Serializable obj;
        JSONLexer lexer = parser.lexer;
        if (lexer.token() == 8) {
            lexer.nextToken(16);
            return null;
        }
        if (lexer.token() != 12 && lexer.token() != 16) {
            throw new JSONException("syntax error");
        }
        lexer.nextToken();
        if (type == Point.class) {
            obj = this.parsePoint(parser, fieldName);
        } else if (type == Rectangle.class) {
            obj = this.parseRectangle(parser);
        } else if (type == Color.class) {
            obj = this.parseColor(parser);
        } else if (type == Font.class) {
            obj = this.parseFont(parser);
        } else {
            throw new JSONException("not support awt class : " + type);
        }
        ParseContext context = parser.getContext();
        parser.setContext(obj, fieldName);
        parser.setContext(context);
        return (T)obj;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected Font parseFont(DefaultJSONParser parser) {
        JSONLexer lexer = parser.lexer;
        int size = 0;
        int style = 0;
        String name = null;
        while (true) {
            if (lexer.token() == 13) break;
            if (lexer.token() != 4) {
                throw new JSONException("syntax error");
            }
            String key = lexer.stringVal();
            lexer.nextTokenWithColon(2);
            if (key.equalsIgnoreCase("name")) {
                if (lexer.token() != 4) throw new JSONException("syntax error");
                name = lexer.stringVal();
                lexer.nextToken();
            } else if (key.equalsIgnoreCase("style")) {
                if (lexer.token() != 2) throw new JSONException("syntax error");
                style = lexer.intValue();
                lexer.nextToken();
            } else {
                if (!key.equalsIgnoreCase("size")) throw new JSONException("syntax error, " + key);
                if (lexer.token() != 2) throw new JSONException("syntax error");
                size = lexer.intValue();
                lexer.nextToken();
            }
            if (lexer.token() != 16) continue;
            lexer.nextToken(4);
        }
        lexer.nextToken();
        return new Font(name, style, size);
    }

    protected Color parseColor(DefaultJSONParser parser) {
        JSONLexer lexer = parser.lexer;
        int r = 0;
        int g2 = 0;
        int b = 0;
        int alpha = 0;
        while (true) {
            if (lexer.token() == 13) break;
            if (lexer.token() != 4) {
                throw new JSONException("syntax error");
            }
            String key = lexer.stringVal();
            lexer.nextTokenWithColon(2);
            if (lexer.token() != 2) {
                throw new JSONException("syntax error");
            }
            int val = lexer.intValue();
            lexer.nextToken();
            if (key.equalsIgnoreCase("r")) {
                r = val;
            } else if (key.equalsIgnoreCase("g")) {
                g2 = val;
            } else if (key.equalsIgnoreCase("b")) {
                b = val;
            } else if (key.equalsIgnoreCase("alpha")) {
                alpha = val;
            } else {
                throw new JSONException("syntax error, " + key);
            }
            if (lexer.token() != 16) continue;
            lexer.nextToken(4);
        }
        lexer.nextToken();
        return new Color(r, g2, b, alpha);
    }

    protected Rectangle parseRectangle(DefaultJSONParser parser) {
        JSONLexer lexer = parser.lexer;
        int x = 0;
        int y = 0;
        int width = 0;
        int height = 0;
        while (true) {
            int val;
            if (lexer.token() == 13) break;
            if (lexer.token() != 4) {
                throw new JSONException("syntax error");
            }
            String key = lexer.stringVal();
            lexer.nextTokenWithColon(2);
            int token = lexer.token();
            if (token == 2) {
                val = lexer.intValue();
                lexer.nextToken();
            } else if (token == 3) {
                val = (int)lexer.floatValue();
                lexer.nextToken();
            } else {
                throw new JSONException("syntax error");
            }
            if (key.equalsIgnoreCase("x")) {
                x = val;
            } else if (key.equalsIgnoreCase("y")) {
                y = val;
            } else if (key.equalsIgnoreCase("width")) {
                width = val;
            } else if (key.equalsIgnoreCase("height")) {
                height = val;
            } else {
                throw new JSONException("syntax error, " + key);
            }
            if (lexer.token() != 16) continue;
            lexer.nextToken(4);
        }
        lexer.nextToken();
        return new Rectangle(x, y, width, height);
    }

    protected Point parsePoint(DefaultJSONParser parser, Object fieldName) {
        JSONLexer lexer = parser.lexer;
        int x = 0;
        int y = 0;
        while (true) {
            int val;
            String key;
            if (lexer.token() == 13) break;
            if (lexer.token() == 4) {
                key = lexer.stringVal();
                if (JSON.DEFAULT_TYPE_KEY.equals(key)) {
                    parser.acceptType("java.awt.Point");
                    continue;
                }
                if ("$ref".equals(key)) {
                    return (Point)this.parseRef(parser, fieldName);
                }
            } else {
                throw new JSONException("syntax error");
            }
            lexer.nextTokenWithColon(2);
            int token = lexer.token();
            if (token == 2) {
                val = lexer.intValue();
                lexer.nextToken();
            } else if (token == 3) {
                val = (int)lexer.floatValue();
                lexer.nextToken();
            } else {
                throw new JSONException("syntax error : " + lexer.tokenName());
            }
            if (key.equalsIgnoreCase("x")) {
                x = val;
            } else if (key.equalsIgnoreCase("y")) {
                y = val;
            } else {
                throw new JSONException("syntax error, " + key);
            }
            if (lexer.token() != 16) continue;
            lexer.nextToken(4);
        }
        lexer.nextToken();
        return new Point(x, y);
    }

    private Object parseRef(DefaultJSONParser parser, Object fieldName) {
        JSONLexer lexer = parser.getLexer();
        lexer.nextTokenWithColon(4);
        String ref = lexer.stringVal();
        parser.setContext(parser.getContext(), fieldName);
        parser.addResolveTask(new DefaultJSONParser.ResolveTask(parser.getContext(), ref));
        parser.popContext();
        parser.setResolveStatus(1);
        lexer.nextToken(13);
        parser.accept(13);
        return null;
    }

    @Override
    public int getFastMatchToken() {
        return 12;
    }
}

