/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.publicsuffix;

import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0Impl;
import okhttp3.internal.publicsuffix.PublicSuffixDatabase;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=3)
final class PublicSuffixDatabase$findMatchingRule$1
extends MutablePropertyReference0Impl {
    PublicSuffixDatabase$findMatchingRule$1(PublicSuffixDatabase publicSuffixDatabase) {
        super((Object)publicSuffixDatabase, PublicSuffixDatabase.class, "publicSuffixListBytes", "getPublicSuffixListBytes()[B", 0);
    }

    @Override
    @Nullable
    public Object get() {
        return PublicSuffixDatabase.access$getPublicSuffixListBytes$p((PublicSuffixDatabase)this.receiver);
    }

    @Override
    public void set(@Nullable Object value) {
        PublicSuffixDatabase.access$setPublicSuffixListBytes$p((PublicSuffixDatabase)this.receiver, (byte[])value);
    }
}

