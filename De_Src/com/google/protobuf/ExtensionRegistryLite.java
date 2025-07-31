/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.protobuf;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLite;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ExtensionRegistryLite {
    private static volatile boolean eagerlyParseMessageSets = false;
    private final Map<ObjectIntPair, GeneratedMessageLite.GeneratedExtension<?, ?>> extensionsByNumber;
    private static final ExtensionRegistryLite EMPTY = new ExtensionRegistryLite(true);

    public static boolean isEagerlyParseMessageSets() {
        return eagerlyParseMessageSets;
    }

    public static void setEagerlyParseMessageSets(boolean isEagerlyParse) {
        eagerlyParseMessageSets = isEagerlyParse;
    }

    public static ExtensionRegistryLite newInstance() {
        return new ExtensionRegistryLite();
    }

    public static ExtensionRegistryLite getEmptyRegistry() {
        return EMPTY;
    }

    public ExtensionRegistryLite getUnmodifiable() {
        return new ExtensionRegistryLite(this);
    }

    public <ContainingType extends MessageLite> GeneratedMessageLite.GeneratedExtension<ContainingType, ?> findLiteExtensionByNumber(ContainingType containingTypeDefaultInstance, int fieldNumber) {
        return this.extensionsByNumber.get(new ObjectIntPair(containingTypeDefaultInstance, fieldNumber));
    }

    public final void add(GeneratedMessageLite.GeneratedExtension<?, ?> extension) {
        this.extensionsByNumber.put(new ObjectIntPair(extension.getContainingTypeDefaultInstance(), extension.getNumber()), extension);
    }

    ExtensionRegistryLite() {
        this.extensionsByNumber = new HashMap();
    }

    ExtensionRegistryLite(ExtensionRegistryLite other) {
        this.extensionsByNumber = other == EMPTY ? Collections.emptyMap() : Collections.unmodifiableMap(other.extensionsByNumber);
    }

    private ExtensionRegistryLite(boolean empty) {
        this.extensionsByNumber = Collections.emptyMap();
    }

    private static final class ObjectIntPair {
        private final Object object;
        private final int number;

        ObjectIntPair(Object object, int number) {
            this.object = object;
            this.number = number;
        }

        public int hashCode() {
            return System.identityHashCode(this.object) * 65535 + this.number;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ObjectIntPair)) {
                return false;
            }
            ObjectIntPair other = (ObjectIntPair)obj;
            return this.object == other.object && this.number == other.number;
        }
    }
}

