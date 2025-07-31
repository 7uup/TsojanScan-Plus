/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.text;

import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.text.CharsKt;
import kotlin.text.CharsKt__CharJVMKt;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=5, xi=1, d1={"\u0000\u0014\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\f\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u0001\u001a\n\u0010\u0005\u001a\u00020\u0001*\u00020\u0002\u001a\u0015\u0010\u0006\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0007H\u0087\n\u00a8\u0006\b"}, d2={"equals", "", "", "other", "ignoreCase", "isSurrogate", "plus", "", "kotlin-stdlib"}, xs="kotlin/text/CharsKt")
class CharsKt__CharKt
extends CharsKt__CharJVMKt {
    @InlineOnly
    private static final String plus(char $this$plus, String other) {
        int $i$f$plus = 0;
        return String.valueOf($this$plus) + other;
    }

    public static final boolean equals(char $this$equals, char other, boolean ignoreCase) {
        if ($this$equals == other) {
            return true;
        }
        if (!ignoreCase) {
            return false;
        }
        char c = $this$equals;
        boolean bl = false;
        char c2 = Character.toUpperCase(c);
        c = other;
        bl = false;
        if (c2 == Character.toUpperCase(c)) {
            return true;
        }
        c = $this$equals;
        bl = false;
        char c3 = Character.toLowerCase(c);
        c = other;
        bl = false;
        return c3 == Character.toLowerCase(c);
    }

    public static /* synthetic */ boolean equals$default(char c, char c2, boolean bl, int n, Object object) {
        if ((n & 2) != 0) {
            bl = false;
        }
        return CharsKt.equals(c, c2, bl);
    }

    public static final boolean isSurrogate(char $this$isSurrogate) {
        char c = $this$isSurrogate;
        return '\ud800' <= c && '\udfff' >= c;
    }
}

