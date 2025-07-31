/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text.lookup;

import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import javax.xml.xpath.XPathFactory;
import org.apache.commons.text.lookup.AbstractStringLookup;
import org.apache.commons.text.lookup.IllegalArgumentExceptions;
import org.xml.sax.InputSource;

final class XmlStringLookup
extends AbstractStringLookup {
    static final XmlStringLookup INSTANCE = new XmlStringLookup();

    private XmlStringLookup() {
    }

    @Override
    public String lookup(String key) {
        if (key == null) {
            return null;
        }
        String[] keys2 = key.split(SPLIT_STR);
        int keyLen = keys2.length;
        if (keyLen != 2) {
            throw IllegalArgumentExceptions.format("Bad XML key format [%s]; expected format is DocumentPath:XPath.", key);
        }
        String documentPath = keys2[0];
        String xpath = this.substringAfter(key, ':');
        try {
            return XPathFactory.newInstance().newXPath().evaluate(xpath, new InputSource(Files.newInputStream(Paths.get(documentPath, new String[0]), new OpenOption[0])));
        } catch (Exception e) {
            throw IllegalArgumentExceptions.format(e, "Error looking up XML document [%s] and XPath [%s].", documentPath, xpath);
        }
    }
}

