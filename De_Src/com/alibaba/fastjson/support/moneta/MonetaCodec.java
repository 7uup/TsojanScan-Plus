/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  javax.money.CurrencyUnit
 *  javax.money.Monetary
 *  org.javamoney.moneta.Money
 */
package com.alibaba.fastjson.support.moneta;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import org.javamoney.moneta.Money;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class MonetaCodec
implements ObjectSerializer,
ObjectDeserializer {
    public static final MonetaCodec instance = new MonetaCodec();

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        Money money = (Money)object;
        if (money == null) {
            serializer.writeNull();
            return;
        }
        SerializeWriter out = serializer.out;
        out.writeFieldValue('{', "numberStripped", money.getNumberStripped());
        out.writeFieldValue(',', "currency", money.getCurrency().getCurrencyCode());
        out.write(125);
    }

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        JSONObject object = parser.parseObject();
        Object currency = object.get("currency");
        String currencyCode = null;
        if (currency instanceof JSONObject) {
            currencyCode = ((JSONObject)currency).getString("currencyCode");
        } else if (currency instanceof String) {
            currencyCode = (String)currency;
        }
        Object numberStripped = object.get("numberStripped");
        if (numberStripped instanceof BigDecimal || numberStripped instanceof Integer || numberStripped instanceof BigInteger) {
            return (T)Money.of((Number)((Number)numberStripped), (CurrencyUnit)Monetary.getCurrency((String)currencyCode, (String[])new String[0]));
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }
}

