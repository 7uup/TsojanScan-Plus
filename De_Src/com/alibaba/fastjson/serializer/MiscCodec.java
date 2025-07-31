/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.JSONStreamAware;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.util.IOUtils;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Pattern;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Node;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class MiscCodec
implements ObjectSerializer,
ObjectDeserializer {
    private static boolean FILE_RELATIVE_PATH_SUPPORT = false;
    public static final MiscCodec instance = new MiscCodec();
    private static Method method_paths_get;
    private static boolean method_paths_get_error;

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        String strVal;
        SerializeWriter out = serializer.out;
        if (object == null) {
            out.writeNull();
            return;
        }
        Class<?> objClass = object.getClass();
        if (objClass == SimpleDateFormat.class) {
            String pattern = ((SimpleDateFormat)object).toPattern();
            if (out.isEnabled(SerializerFeature.WriteClassName) && object.getClass() != fieldType) {
                out.write(123);
                out.writeFieldName(JSON.DEFAULT_TYPE_KEY);
                serializer.write(object.getClass().getName());
                out.writeFieldValue(',', "val", pattern);
                out.write(125);
                return;
            }
            strVal = pattern;
        } else if (objClass == Class.class) {
            Class clazz = (Class)object;
            strVal = clazz.getName();
        } else {
            if (objClass == InetSocketAddress.class) {
                InetSocketAddress address = (InetSocketAddress)object;
                InetAddress inetAddress = address.getAddress();
                out.write(123);
                if (inetAddress != null) {
                    out.writeFieldName("address");
                    serializer.write(inetAddress);
                    out.write(44);
                }
                out.writeFieldName("port");
                out.writeInt(address.getPort());
                out.write(125);
                return;
            }
            if (object instanceof File) {
                strVal = ((File)object).getPath();
            } else if (object instanceof InetAddress) {
                strVal = ((InetAddress)object).getHostAddress();
            } else if (object instanceof TimeZone) {
                TimeZone timeZone = (TimeZone)object;
                strVal = timeZone.getID();
            } else if (object instanceof Currency) {
                Currency currency = (Currency)object;
                strVal = currency.getCurrencyCode();
            } else {
                if (object instanceof JSONStreamAware) {
                    JSONStreamAware aware = (JSONStreamAware)object;
                    aware.writeJSONString(out);
                    return;
                }
                if (object instanceof Iterator) {
                    Iterator it = (Iterator)object;
                    this.writeIterator(serializer, out, it);
                    return;
                }
                if (object instanceof Iterable) {
                    Iterator it = ((Iterable)object).iterator();
                    this.writeIterator(serializer, out, it);
                    return;
                }
                if (object instanceof Map.Entry) {
                    Map.Entry entry = (Map.Entry)object;
                    Object objKey = entry.getKey();
                    Object objVal = entry.getValue();
                    if (objKey instanceof String) {
                        String key = (String)objKey;
                        if (objVal instanceof String) {
                            String value = (String)objVal;
                            out.writeFieldValueStringWithDoubleQuoteCheck('{', key, value);
                        } else {
                            out.write(123);
                            out.writeFieldName(key);
                            serializer.write(objVal);
                        }
                    } else {
                        out.write(123);
                        serializer.write(objKey);
                        out.write(58);
                        serializer.write(objVal);
                    }
                    out.write(125);
                    return;
                }
                if (object.getClass().getName().equals("net.sf.json.JSONNull")) {
                    out.writeNull();
                    return;
                }
                if (object instanceof Node) {
                    strVal = MiscCodec.toString((Node)object);
                } else {
                    throw new JSONException("not support class : " + objClass);
                }
            }
        }
        out.writeString(strVal);
    }

    private static String toString(Node node) {
        try {
            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transformer = transFactory.newTransformer();
            DOMSource domSource = new DOMSource(node);
            StringWriter out = new StringWriter();
            transformer.transform(domSource, new StreamResult(out));
            return out.toString();
        } catch (TransformerException e) {
            throw new JSONException("xml node to string error", e);
        }
    }

    protected void writeIterator(JSONSerializer serializer, SerializeWriter out, Iterator<?> it) {
        int i = 0;
        out.write(91);
        while (it.hasNext()) {
            if (i != 0) {
                out.write(44);
            }
            Object item = it.next();
            serializer.write(item);
            ++i;
        }
        out.write(93);
    }

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type clazz, Object fieldName) {
        String strVal;
        Object objVal;
        JSONLexer lexer = parser.lexer;
        if (clazz == InetSocketAddress.class) {
            if (lexer.token() == 8) {
                lexer.nextToken();
                return null;
            }
            parser.accept(12);
            InetAddress address = null;
            int port = 0;
            while (true) {
                String key = lexer.stringVal();
                lexer.nextToken(17);
                if (key.equals("address")) {
                    parser.accept(17);
                    address = parser.parseObject(InetAddress.class);
                } else if (key.equals("port")) {
                    parser.accept(17);
                    if (lexer.token() != 2) {
                        throw new JSONException("port is not int");
                    }
                    port = lexer.intValue();
                    lexer.nextToken();
                } else {
                    parser.accept(17);
                    parser.parse();
                }
                if (lexer.token() != 16) break;
                lexer.nextToken();
            }
            parser.accept(13);
            return (T)new InetSocketAddress(address, port);
        }
        if (parser.resolveStatus == 2) {
            parser.resolveStatus = 0;
            parser.accept(16);
            if (lexer.token() == 4) {
                if (!"val".equals(lexer.stringVal())) {
                    throw new JSONException("syntax error");
                }
            } else {
                throw new JSONException("syntax error");
            }
            lexer.nextToken();
            parser.accept(17);
            objVal = parser.parse();
            parser.accept(13);
        } else {
            objVal = parser.parse();
        }
        if (objVal == null) {
            strVal = null;
        } else if (objVal instanceof String) {
            strVal = (String)objVal;
        } else {
            if (objVal instanceof JSONObject) {
                JSONObject jsonObject = (JSONObject)objVal;
                if (clazz == Currency.class) {
                    String currency = jsonObject.getString("currency");
                    if (currency != null) {
                        return (T)Currency.getInstance(currency);
                    }
                    String symbol = jsonObject.getString("currencyCode");
                    if (symbol != null) {
                        return (T)Currency.getInstance(symbol);
                    }
                }
                if (clazz == Map.Entry.class) {
                    return (T)jsonObject.entrySet().iterator().next();
                }
                return jsonObject.toJavaObject(clazz);
            }
            throw new JSONException("expect string");
        }
        if (strVal == null || strVal.length() == 0) {
            return null;
        }
        if (clazz == UUID.class) {
            return (T)UUID.fromString(strVal);
        }
        if (clazz == URI.class) {
            return (T)URI.create(strVal);
        }
        if (clazz == URL.class) {
            try {
                return (T)new URL(strVal);
            } catch (MalformedURLException e) {
                throw new JSONException("create url error", e);
            }
        }
        if (clazz == Pattern.class) {
            return (T)Pattern.compile(strVal);
        }
        if (clazz == Locale.class) {
            return (T)TypeUtils.toLocale(strVal);
        }
        if (clazz == SimpleDateFormat.class) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(strVal, lexer.getLocale());
            dateFormat.setTimeZone(lexer.getTimeZone());
            return (T)dateFormat;
        }
        if (clazz == InetAddress.class || clazz == Inet4Address.class || clazz == Inet6Address.class) {
            try {
                return (T)InetAddress.getByName(strVal);
            } catch (UnknownHostException e) {
                throw new JSONException("deserialize inet adress error", e);
            }
        }
        if (clazz == File.class) {
            if (strVal.indexOf("..") >= 0 && !FILE_RELATIVE_PATH_SUPPORT) {
                throw new JSONException("file relative path not support.");
            }
            return (T)new File(strVal);
        }
        if (clazz == TimeZone.class) {
            return (T)TimeZone.getTimeZone(strVal);
        }
        if (clazz instanceof ParameterizedType) {
            ParameterizedType parmeterizedType = (ParameterizedType)clazz;
            clazz = parmeterizedType.getRawType();
        }
        if (clazz == Class.class) {
            return (T)TypeUtils.loadClass(strVal, parser.getConfig().getDefaultClassLoader(), false);
        }
        if (clazz == Charset.class) {
            return (T)Charset.forName(strVal);
        }
        if (clazz == Currency.class) {
            return (T)Currency.getInstance(strVal);
        }
        if (clazz == JSONPath.class) {
            return (T)new JSONPath(strVal);
        }
        if (clazz instanceof Class) {
            String className = ((Class)clazz).getName();
            if (className.equals("java.nio.file.Path")) {
                try {
                    if (method_paths_get == null && !method_paths_get_error) {
                        Class<?> paths = TypeUtils.loadClass("java.nio.file.Paths");
                        method_paths_get = paths.getMethod("get", String.class, String[].class);
                    }
                    if (method_paths_get != null) {
                        return (T)method_paths_get.invoke(null, strVal, new String[0]);
                    }
                    throw new JSONException("Path deserialize erorr");
                } catch (NoSuchMethodException ex) {
                    method_paths_get_error = true;
                } catch (IllegalAccessException ex) {
                    throw new JSONException("Path deserialize erorr", ex);
                } catch (InvocationTargetException ex) {
                    throw new JSONException("Path deserialize erorr", ex);
                }
            }
            throw new JSONException("MiscCodec not support " + className);
        }
        throw new JSONException("MiscCodec not support " + clazz.toString());
    }

    @Override
    public int getFastMatchToken() {
        return 4;
    }

    static {
        method_paths_get_error = false;
        FILE_RELATIVE_PATH_SUPPORT = "true".equals(IOUtils.getStringProperty("fastjson.deserializer.fileRelativePathSupport"));
    }
}

