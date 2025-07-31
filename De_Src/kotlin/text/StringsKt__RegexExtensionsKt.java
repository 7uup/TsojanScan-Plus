/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.text;

import java.util.Set;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt__RegexExtensionsJVMKt;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=5, xi=1, d1={"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\u001b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0087\b\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005H\u0087\b\u00a8\u0006\u0007"}, d2={"toRegex", "Lkotlin/text/Regex;", "", "options", "", "Lkotlin/text/RegexOption;", "option", "kotlin-stdlib"}, xs="kotlin/text/StringsKt")
class StringsKt__RegexExtensionsKt
extends StringsKt__RegexExtensionsJVMKt {
    @InlineOnly
    private static final Regex toRegex(String $this$toRegex) {
        int $i$f$toRegex = 0;
        return new Regex($this$toRegex);
    }

    @InlineOnly
    private static final Regex toRegex(String $this$toRegex, RegexOption option) {
        int $i$f$toRegex = 0;
        return new Regex($this$toRegex, option);
    }

    @InlineOnly
    private static final Regex toRegex(String $this$toRegex, Set<? extends RegexOption> options) {
        int $i$f$toRegex = 0;
        return new Regex($this$toRegex, options);
    }
}

