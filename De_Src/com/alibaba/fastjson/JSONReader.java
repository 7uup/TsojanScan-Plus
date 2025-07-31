/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONStreamContext;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONReaderScanner;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.Closeable;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class JSONReader
implements Closeable {
    private final DefaultJSONParser parser;
    private JSONStreamContext context;
    private transient JSONStreamContext lastContext;

    public JSONReader(Reader reader) {
        this(reader, new Feature[0]);
    }

    public JSONReader(Reader reader, Feature ... features) {
        this(new JSONReaderScanner(reader));
        for (Feature feature : features) {
            this.config(feature, true);
        }
    }

    public JSONReader(JSONLexer lexer) {
        this(new DefaultJSONParser(lexer));
    }

    public JSONReader(DefaultJSONParser parser) {
        this.parser = parser;
    }

    public void setTimzeZone(TimeZone timezone) {
        this.parser.lexer.setTimeZone(timezone);
    }

    public void setLocale(Locale locale) {
        this.parser.lexer.setLocale(locale);
    }

    public void config(Feature feature, boolean state) {
        this.parser.config(feature, state);
    }

    public Locale getLocal() {
        return this.parser.lexer.getLocale();
    }

    public TimeZone getTimzeZone() {
        return this.parser.lexer.getTimeZone();
    }

    public void startObject() {
        if (this.context == null) {
            this.context = new JSONStreamContext(null, 1001);
        } else {
            this.startStructure();
            if (this.lastContext != null && this.lastContext.parent == this.context) {
                this.context = this.lastContext;
                if (this.context.state != 1001) {
                    this.context.state = 1001;
                }
            } else {
                this.context = new JSONStreamContext(this.context, 1001);
            }
        }
        this.parser.accept(12, 18);
    }

    public void endObject() {
        this.parser.accept(13);
        this.endStructure();
    }

    public void startArray() {
        if (this.context == null) {
            this.context = new JSONStreamContext(null, 1004);
        } else {
            this.startStructure();
            this.context = new JSONStreamContext(this.context, 1004);
        }
        this.parser.accept(14);
    }

    public void endArray() {
        this.parser.accept(15);
        this.endStructure();
    }

    private void startStructure() {
        int state = this.context.state;
        switch (state) {
            case 1002: {
                this.parser.accept(17);
                break;
            }
            case 1003: 
            case 1005: {
                this.parser.accept(16);
                break;
            }
            case 1001: 
            case 1004: {
                break;
            }
            default: {
                throw new JSONException("illegal state : " + this.context.state);
            }
        }
    }

    private void endStructure() {
        this.lastContext = this.context;
        this.context = this.context.parent;
        if (this.context == null) {
            return;
        }
        int state = this.context.state;
        int newState = -1;
        switch (state) {
            case 1002: {
                newState = 1003;
                break;
            }
            case 1004: {
                newState = 1005;
                break;
            }
            case 1001: 
            case 1003: {
                newState = 1002;
                break;
            }
        }
        if (newState != -1) {
            this.context.state = newState;
        }
    }

    public boolean hasNext() {
        if (this.context == null) {
            throw new JSONException("context is null");
        }
        int token = this.parser.lexer.token();
        int state = this.context.state;
        switch (state) {
            case 1004: 
            case 1005: {
                return token != 15;
            }
            case 1001: 
            case 1003: {
                return token != 13;
            }
        }
        throw new JSONException("illegal state : " + state);
    }

    public int peek() {
        return this.parser.lexer.token();
    }

    @Override
    public void close() {
        this.parser.close();
    }

    public Integer readInteger() {
        Object object;
        if (this.context == null) {
            object = this.parser.parse();
        } else {
            this.readBefore();
            object = this.parser.parse();
            this.readAfter();
        }
        return TypeUtils.castToInt(object);
    }

    public Long readLong() {
        Object object;
        if (this.context == null) {
            object = this.parser.parse();
        } else {
            this.readBefore();
            object = this.parser.parse();
            this.readAfter();
        }
        return TypeUtils.castToLong(object);
    }

    public String readString() {
        Object object;
        if (this.context == null) {
            object = this.parser.parse();
        } else {
            this.readBefore();
            JSONLexer lexer = this.parser.lexer;
            if (this.context.state == 1001 && lexer.token() == 18) {
                object = lexer.stringVal();
                lexer.nextToken();
            } else {
                object = this.parser.parse();
            }
            this.readAfter();
        }
        return TypeUtils.castToString(object);
    }

    public <T> T readObject(TypeReference<T> typeRef) {
        return this.readObject(typeRef.getType());
    }

    public <T> T readObject(Type type) {
        if (this.context == null) {
            return this.parser.parseObject(type);
        }
        this.readBefore();
        Object object = this.parser.parseObject(type);
        this.readAfter();
        return object;
    }

    public <T> T readObject(Class<T> type) {
        if (this.context == null) {
            return this.parser.parseObject(type);
        }
        this.readBefore();
        T object = this.parser.parseObject(type);
        this.parser.handleResovleTask(object);
        this.readAfter();
        return object;
    }

    public void readObject(Object object) {
        if (this.context == null) {
            this.parser.parseObject(object);
            return;
        }
        this.readBefore();
        this.parser.parseObject(object);
        this.readAfter();
    }

    public Object readObject() {
        Object object;
        if (this.context == null) {
            return this.parser.parse();
        }
        this.readBefore();
        switch (this.context.state) {
            case 1001: 
            case 1003: {
                object = this.parser.parseKey();
                break;
            }
            default: {
                object = this.parser.parse();
            }
        }
        this.readAfter();
        return object;
    }

    public Object readObject(Map object) {
        if (this.context == null) {
            return this.parser.parseObject(object);
        }
        this.readBefore();
        Object value = this.parser.parseObject(object);
        this.readAfter();
        return value;
    }

    private void readBefore() {
        int state = this.context.state;
        switch (state) {
            case 1002: {
                this.parser.accept(17);
                break;
            }
            case 1003: {
                this.parser.accept(16, 18);
                break;
            }
            case 1005: {
                this.parser.accept(16);
                break;
            }
            case 1001: {
                break;
            }
            case 1004: {
                break;
            }
            default: {
                throw new JSONException("illegal state : " + state);
            }
        }
    }

    private void readAfter() {
        int state = this.context.state;
        int newStat = -1;
        switch (state) {
            case 1001: {
                newStat = 1002;
                break;
            }
            case 1002: {
                newStat = 1003;
                break;
            }
            case 1003: {
                newStat = 1002;
                break;
            }
            case 1005: {
                break;
            }
            case 1004: {
                newStat = 1005;
                break;
            }
            default: {
                throw new JSONException("illegal state : " + state);
            }
        }
        if (newStat != -1) {
            this.context.state = newStat;
        }
    }
}

