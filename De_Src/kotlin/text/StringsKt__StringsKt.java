/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CharIterator;
import kotlin.collections.CollectionsKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.CharsKt;
import kotlin.text.DelimitedRangesSequence;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=5, xi=1, d1={"\u0000|\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0019\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001b\u001a\u001c\u0010\t\u001a\u00020\n*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u001c\u0010\u000e\u001a\u00020\n*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u001f\u0010\u000f\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\rH\u0086\u0002\u001a\u001f\u0010\u000f\u001a\u00020\r*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\rH\u0086\u0002\u001a\u0015\u0010\u000f\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0087\n\u001a\u001c\u0010\u0014\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u001c\u0010\u0014\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a:\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n\u0018\u00010\u0017*\u00020\u00022\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001aE\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n\u0018\u00010\u0017*\u00020\u00022\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\u00192\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\rH\u0002\u00a2\u0006\u0002\b\u001c\u001a:\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n\u0018\u00010\u0017*\u00020\u00022\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u0012\u0010\u001e\u001a\u00020\r*\u00020\u00022\u0006\u0010\u001f\u001a\u00020\u0006\u001a7\u0010 \u001a\u0002H!\"\f\b\u0000\u0010\"*\u00020\u0002*\u0002H!\"\u0004\b\u0001\u0010!*\u0002H\"2\f\u0010#\u001a\b\u0012\u0004\u0012\u0002H!0$H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010%\u001a7\u0010&\u001a\u0002H!\"\f\b\u0000\u0010\"*\u00020\u0002*\u0002H!\"\u0004\b\u0001\u0010!*\u0002H\"2\f\u0010#\u001a\b\u0012\u0004\u0012\u0002H!0$H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010%\u001a&\u0010'\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a;\u0010'\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u001b\u001a\u00020\rH\u0002\u00a2\u0006\u0002\b)\u001a&\u0010'\u001a\u00020\u0006*\u00020\u00022\u0006\u0010*\u001a\u00020\n2\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a&\u0010+\u001a\u00020\u0006*\u00020\u00022\u0006\u0010,\u001a\u00020-2\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a,\u0010+\u001a\u00020\u0006*\u00020\u00022\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a\r\u0010.\u001a\u00020\r*\u00020\u0002H\u0087\b\u001a\r\u0010/\u001a\u00020\r*\u00020\u0002H\u0087\b\u001a\r\u00100\u001a\u00020\r*\u00020\u0002H\u0087\b\u001a \u00101\u001a\u00020\r*\u0004\u0018\u00010\u0002H\u0087\b\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000\u001a \u00102\u001a\u00020\r*\u0004\u0018\u00010\u0002H\u0087\b\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000\u001a\r\u00103\u001a\u000204*\u00020\u0002H\u0086\u0002\u001a&\u00105\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a&\u00105\u001a\u00020\u0006*\u00020\u00022\u0006\u0010*\u001a\u00020\n2\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a&\u00106\u001a\u00020\u0006*\u00020\u00022\u0006\u0010,\u001a\u00020-2\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a,\u00106\u001a\u00020\u0006*\u00020\u00022\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u0010\u00107\u001a\b\u0012\u0004\u0012\u00020\n08*\u00020\u0002\u001a\u0010\u00109\u001a\b\u0012\u0004\u0012\u00020\n0:*\u00020\u0002\u001a\u0015\u0010;\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0087\f\u001a\u000f\u0010<\u001a\u00020\n*\u0004\u0018\u00010\nH\u0087\b\u001a\u001c\u0010=\u001a\u00020\u0002*\u00020\u00022\u0006\u0010>\u001a\u00020\u00062\b\b\u0002\u0010?\u001a\u00020\u0011\u001a\u001c\u0010=\u001a\u00020\n*\u00020\n2\u0006\u0010>\u001a\u00020\u00062\b\b\u0002\u0010?\u001a\u00020\u0011\u001a\u001c\u0010@\u001a\u00020\u0002*\u00020\u00022\u0006\u0010>\u001a\u00020\u00062\b\b\u0002\u0010?\u001a\u00020\u0011\u001a\u001c\u0010@\u001a\u00020\n*\u00020\n2\u0006\u0010>\u001a\u00020\u00062\b\b\u0002\u0010?\u001a\u00020\u0011\u001aG\u0010A\u001a\b\u0012\u0004\u0012\u00020\u000108*\u00020\u00022\u000e\u0010B\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0C2\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010D\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\bE\u0010F\u001a=\u0010A\u001a\b\u0012\u0004\u0012\u00020\u000108*\u00020\u00022\u0006\u0010B\u001a\u00020-2\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010D\u001a\u00020\u0006H\u0002\u00a2\u0006\u0002\bE\u001a4\u0010G\u001a\u00020\r*\u00020\u00022\u0006\u0010H\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010I\u001a\u00020\u00062\u0006\u0010>\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rH\u0000\u001a\u0012\u0010J\u001a\u00020\u0002*\u00020\u00022\u0006\u0010K\u001a\u00020\u0002\u001a\u0012\u0010J\u001a\u00020\n*\u00020\n2\u0006\u0010K\u001a\u00020\u0002\u001a\u001a\u0010L\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u0006\u001a\u0012\u0010L\u001a\u00020\u0002*\u00020\u00022\u0006\u0010M\u001a\u00020\u0001\u001a\u001d\u0010L\u001a\u00020\n*\u00020\n2\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u0006H\u0087\b\u001a\u0015\u0010L\u001a\u00020\n*\u00020\n2\u0006\u0010M\u001a\u00020\u0001H\u0087\b\u001a\u0012\u0010N\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0002\u001a\u0012\u0010N\u001a\u00020\n*\u00020\n2\u0006\u0010\u0015\u001a\u00020\u0002\u001a\u0012\u0010O\u001a\u00020\u0002*\u00020\u00022\u0006\u0010P\u001a\u00020\u0002\u001a\u001a\u0010O\u001a\u00020\u0002*\u00020\u00022\u0006\u0010K\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0002\u001a\u0012\u0010O\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\u0002\u001a\u001a\u0010O\u001a\u00020\n*\u00020\n2\u0006\u0010K\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0002\u001a.\u0010Q\u001a\u00020\n*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0014\b\b\u0010R\u001a\u000e\u0012\u0004\u0012\u00020T\u0012\u0004\u0012\u00020\u00020SH\u0087\b\u00f8\u0001\u0000\u001a\u001d\u0010Q\u001a\u00020\n*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010U\u001a\u00020\nH\u0087\b\u001a$\u0010V\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\u00112\u0006\u0010U\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a$\u0010V\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\n2\u0006\u0010U\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a$\u0010X\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\u00112\u0006\u0010U\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a$\u0010X\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\n2\u0006\u0010U\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a$\u0010Y\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\u00112\u0006\u0010U\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a$\u0010Y\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\n2\u0006\u0010U\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a$\u0010Z\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\u00112\u0006\u0010U\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a$\u0010Z\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\n2\u0006\u0010U\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a\u001d\u0010[\u001a\u00020\n*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010U\u001a\u00020\nH\u0087\b\u001a\"\u0010\\\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u00062\u0006\u0010U\u001a\u00020\u0002\u001a\u001a\u0010\\\u001a\u00020\u0002*\u00020\u00022\u0006\u0010M\u001a\u00020\u00012\u0006\u0010U\u001a\u00020\u0002\u001a%\u0010\\\u001a\u00020\n*\u00020\n2\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u00062\u0006\u0010U\u001a\u00020\u0002H\u0087\b\u001a\u001d\u0010\\\u001a\u00020\n*\u00020\n2\u0006\u0010M\u001a\u00020\u00012\u0006\u0010U\u001a\u00020\u0002H\u0087\b\u001a=\u0010]\u001a\b\u0012\u0004\u0012\u00020\n0:*\u00020\u00022\u0012\u0010B\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0C\"\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010D\u001a\u00020\u0006\u00a2\u0006\u0002\u0010^\u001a0\u0010]\u001a\b\u0012\u0004\u0012\u00020\n0:*\u00020\u00022\n\u0010B\u001a\u00020-\"\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010D\u001a\u00020\u0006\u001a/\u0010]\u001a\b\u0012\u0004\u0012\u00020\n0:*\u00020\u00022\u0006\u0010P\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010D\u001a\u00020\u0006H\u0002\u00a2\u0006\u0002\b_\u001a%\u0010]\u001a\b\u0012\u0004\u0012\u00020\n0:*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010D\u001a\u00020\u0006H\u0087\b\u001a=\u0010`\u001a\b\u0012\u0004\u0012\u00020\n08*\u00020\u00022\u0012\u0010B\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0C\"\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010D\u001a\u00020\u0006\u00a2\u0006\u0002\u0010a\u001a0\u0010`\u001a\b\u0012\u0004\u0012\u00020\n08*\u00020\u00022\n\u0010B\u001a\u00020-\"\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010D\u001a\u00020\u0006\u001a\u001c\u0010b\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u001c\u0010b\u001a\u00020\r*\u00020\u00022\u0006\u0010K\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a$\u0010b\u001a\u00020\r*\u00020\u00022\u0006\u0010K\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u0012\u0010c\u001a\u00020\u0002*\u00020\u00022\u0006\u0010M\u001a\u00020\u0001\u001a\u001d\u0010c\u001a\u00020\u0002*\u00020\n2\u0006\u0010d\u001a\u00020\u00062\u0006\u0010e\u001a\u00020\u0006H\u0087\b\u001a\u001f\u0010f\u001a\u00020\n*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010(\u001a\u00020\u0006H\u0087\b\u001a\u0012\u0010f\u001a\u00020\n*\u00020\u00022\u0006\u0010M\u001a\u00020\u0001\u001a\u0012\u0010f\u001a\u00020\n*\u00020\n2\u0006\u0010M\u001a\u00020\u0001\u001a\u001c\u0010g\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\u00112\b\b\u0002\u0010W\u001a\u00020\n\u001a\u001c\u0010g\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a\u001c\u0010h\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\u00112\b\b\u0002\u0010W\u001a\u00020\n\u001a\u001c\u0010h\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a\u001c\u0010i\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\u00112\b\b\u0002\u0010W\u001a\u00020\n\u001a\u001c\u0010i\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a\u001c\u0010j\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\u00112\b\b\u0002\u0010W\u001a\u00020\n\u001a\u001c\u0010j\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a\n\u0010k\u001a\u00020\u0002*\u00020\u0002\u001a$\u0010k\u001a\u00020\u0002*\u00020\u00022\u0012\u0010l\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0SH\u0086\b\u00f8\u0001\u0000\u001a\u0016\u0010k\u001a\u00020\u0002*\u00020\u00022\n\u0010,\u001a\u00020-\"\u00020\u0011\u001a\r\u0010k\u001a\u00020\n*\u00020\nH\u0087\b\u001a$\u0010k\u001a\u00020\n*\u00020\n2\u0012\u0010l\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0SH\u0086\b\u00f8\u0001\u0000\u001a\u0016\u0010k\u001a\u00020\n*\u00020\n2\n\u0010,\u001a\u00020-\"\u00020\u0011\u001a\n\u0010m\u001a\u00020\u0002*\u00020\u0002\u001a$\u0010m\u001a\u00020\u0002*\u00020\u00022\u0012\u0010l\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0SH\u0086\b\u00f8\u0001\u0000\u001a\u0016\u0010m\u001a\u00020\u0002*\u00020\u00022\n\u0010,\u001a\u00020-\"\u00020\u0011\u001a\r\u0010m\u001a\u00020\n*\u00020\nH\u0087\b\u001a$\u0010m\u001a\u00020\n*\u00020\n2\u0012\u0010l\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0SH\u0086\b\u00f8\u0001\u0000\u001a\u0016\u0010m\u001a\u00020\n*\u00020\n2\n\u0010,\u001a\u00020-\"\u00020\u0011\u001a\n\u0010n\u001a\u00020\u0002*\u00020\u0002\u001a$\u0010n\u001a\u00020\u0002*\u00020\u00022\u0012\u0010l\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0SH\u0086\b\u00f8\u0001\u0000\u001a\u0016\u0010n\u001a\u00020\u0002*\u00020\u00022\n\u0010,\u001a\u00020-\"\u00020\u0011\u001a\r\u0010n\u001a\u00020\n*\u00020\nH\u0087\b\u001a$\u0010n\u001a\u00020\n*\u00020\n2\u0012\u0010l\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0SH\u0086\b\u00f8\u0001\u0000\u001a\u0016\u0010n\u001a\u00020\n*\u00020\n2\n\u0010,\u001a\u00020-\"\u00020\u0011\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F\u00a2\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00028F\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\b\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00a8\u0006o"}, d2={"indices", "Lkotlin/ranges/IntRange;", "", "getIndices", "(Ljava/lang/CharSequence;)Lkotlin/ranges/IntRange;", "lastIndex", "", "getLastIndex", "(Ljava/lang/CharSequence;)I", "commonPrefixWith", "", "other", "ignoreCase", "", "commonSuffixWith", "contains", "char", "", "regex", "Lkotlin/text/Regex;", "endsWith", "suffix", "findAnyOf", "Lkotlin/Pair;", "strings", "", "startIndex", "last", "findAnyOf$StringsKt__StringsKt", "findLastAnyOf", "hasSurrogatePairAt", "index", "ifBlank", "R", "C", "defaultValue", "Lkotlin/Function0;", "(Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "ifEmpty", "indexOf", "endIndex", "indexOf$StringsKt__StringsKt", "string", "indexOfAny", "chars", "", "isEmpty", "isNotBlank", "isNotEmpty", "isNullOrBlank", "isNullOrEmpty", "iterator", "Lkotlin/collections/CharIterator;", "lastIndexOf", "lastIndexOfAny", "lineSequence", "Lkotlin/sequences/Sequence;", "lines", "", "matches", "orEmpty", "padEnd", "length", "padChar", "padStart", "rangesDelimitedBy", "delimiters", "", "limit", "rangesDelimitedBy$StringsKt__StringsKt", "(Ljava/lang/CharSequence;[Ljava/lang/String;IZI)Lkotlin/sequences/Sequence;", "regionMatchesImpl", "thisOffset", "otherOffset", "removePrefix", "prefix", "removeRange", "range", "removeSuffix", "removeSurrounding", "delimiter", "replace", "transform", "Lkotlin/Function1;", "Lkotlin/text/MatchResult;", "replacement", "replaceAfter", "missingDelimiterValue", "replaceAfterLast", "replaceBefore", "replaceBeforeLast", "replaceFirst", "replaceRange", "split", "(Ljava/lang/CharSequence;[Ljava/lang/String;ZI)Ljava/util/List;", "split$StringsKt__StringsKt", "splitToSequence", "(Ljava/lang/CharSequence;[Ljava/lang/String;ZI)Lkotlin/sequences/Sequence;", "startsWith", "subSequence", "start", "end", "substring", "substringAfter", "substringAfterLast", "substringBefore", "substringBeforeLast", "trim", "predicate", "trimEnd", "trimStart", "kotlin-stdlib"}, xs="kotlin/text/StringsKt")
class StringsKt__StringsKt
extends StringsKt__StringsJVMKt {
    @NotNull
    public static final CharSequence trim(@NotNull CharSequence $this$trim, @NotNull Function1<? super Character, Boolean> predicate) {
        int $i$f$trim = 0;
        Intrinsics.checkNotNullParameter($this$trim, "$this$trim");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int startIndex = 0;
        int endIndex = $this$trim.length() - 1;
        boolean startFound = false;
        while (startIndex <= endIndex) {
            int index = !startFound ? startIndex : endIndex;
            boolean match = predicate.invoke(Character.valueOf($this$trim.charAt(index)));
            if (!startFound) {
                if (!match) {
                    startFound = true;
                    continue;
                }
                ++startIndex;
                continue;
            }
            if (!match) break;
            --endIndex;
        }
        return $this$trim.subSequence(startIndex, endIndex + 1);
    }

    @NotNull
    public static final String trim(@NotNull String $this$trim, @NotNull Function1<? super Character, Boolean> predicate) {
        int $i$f$trim = 0;
        Intrinsics.checkNotNullParameter($this$trim, "$this$trim");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        CharSequence $this$trim$iv = $this$trim;
        boolean $i$f$trim2 = false;
        int startIndex$iv = 0;
        int endIndex$iv = $this$trim$iv.length() - 1;
        boolean startFound$iv = false;
        while (startIndex$iv <= endIndex$iv) {
            int index$iv = !startFound$iv ? startIndex$iv : endIndex$iv;
            boolean match$iv = predicate.invoke(Character.valueOf($this$trim$iv.charAt(index$iv)));
            if (!startFound$iv) {
                if (!match$iv) {
                    startFound$iv = true;
                    continue;
                }
                ++startIndex$iv;
                continue;
            }
            if (!match$iv) break;
            --endIndex$iv;
        }
        return ((Object)$this$trim$iv.subSequence(startIndex$iv, endIndex$iv + 1)).toString();
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public static final CharSequence trimStart(@NotNull CharSequence $this$trimStart, @NotNull Function1<? super Character, Boolean> predicate) {
        int $i$f$trimStart = 0;
        Intrinsics.checkNotNullParameter($this$trimStart, "$this$trimStart");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int n = 0;
        int n2 = $this$trimStart.length();
        while (n < n2) {
            void index;
            if (!predicate.invoke(Character.valueOf($this$trimStart.charAt((int)index))).booleanValue()) {
                return $this$trimStart.subSequence((int)index, $this$trimStart.length());
            }
            ++index;
        }
        return "";
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public static final String trimStart(@NotNull String $this$trimStart, @NotNull Function1<? super Character, Boolean> predicate) {
        CharSequence charSequence;
        block2: {
            int $i$f$trimStart = 0;
            Intrinsics.checkNotNullParameter($this$trimStart, "$this$trimStart");
            Intrinsics.checkNotNullParameter(predicate, "predicate");
            CharSequence $this$trimStart$iv = $this$trimStart;
            boolean $i$f$trimStart2 = false;
            int n = 0;
            int n2 = $this$trimStart$iv.length();
            while (n < n2) {
                void index$iv;
                if (!predicate.invoke(Character.valueOf($this$trimStart$iv.charAt((int)index$iv))).booleanValue()) {
                    charSequence = $this$trimStart$iv.subSequence((int)index$iv, $this$trimStart$iv.length());
                    break block2;
                }
                ++index$iv;
            }
            charSequence = "";
        }
        return ((Object)charSequence).toString();
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public static final CharSequence trimEnd(@NotNull CharSequence $this$trimEnd, @NotNull Function1<? super Character, Boolean> predicate) {
        int $i$f$trimEnd = 0;
        Intrinsics.checkNotNullParameter($this$trimEnd, "$this$trimEnd");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int n = $this$trimEnd.length();
        --n;
        boolean bl = false;
        while (n >= 0) {
            void index;
            if (!predicate.invoke(Character.valueOf($this$trimEnd.charAt((int)index))).booleanValue()) {
                return $this$trimEnd.subSequence(0, (int)(index + true));
            }
            --index;
        }
        return "";
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public static final String trimEnd(@NotNull String $this$trimEnd, @NotNull Function1<? super Character, Boolean> predicate) {
        CharSequence charSequence;
        block2: {
            int $i$f$trimEnd = 0;
            Intrinsics.checkNotNullParameter($this$trimEnd, "$this$trimEnd");
            Intrinsics.checkNotNullParameter(predicate, "predicate");
            CharSequence $this$trimEnd$iv = $this$trimEnd;
            boolean $i$f$trimEnd2 = false;
            int n = $this$trimEnd$iv.length();
            --n;
            boolean bl = false;
            while (n >= 0) {
                void index$iv;
                if (!predicate.invoke(Character.valueOf($this$trimEnd$iv.charAt((int)index$iv))).booleanValue()) {
                    charSequence = $this$trimEnd$iv.subSequence(0, (int)(index$iv + true));
                    break block2;
                }
                --index$iv;
            }
            charSequence = "";
        }
        return ((Object)charSequence).toString();
    }

    @NotNull
    public static final CharSequence trim(@NotNull CharSequence $this$trim, @NotNull char ... chars) {
        Intrinsics.checkNotNullParameter($this$trim, "$this$trim");
        Intrinsics.checkNotNullParameter(chars, "chars");
        CharSequence $this$trim$iv = $this$trim;
        boolean $i$f$trim = false;
        int startIndex$iv = 0;
        int endIndex$iv = $this$trim$iv.length() - 1;
        boolean startFound$iv = false;
        while (startIndex$iv <= endIndex$iv) {
            int index$iv = !startFound$iv ? startIndex$iv : endIndex$iv;
            char it = $this$trim$iv.charAt(index$iv);
            boolean bl = false;
            boolean match$iv = ArraysKt.contains(chars, it);
            if (!startFound$iv) {
                if (!match$iv) {
                    startFound$iv = true;
                    continue;
                }
                ++startIndex$iv;
                continue;
            }
            if (!match$iv) break;
            --endIndex$iv;
        }
        return $this$trim$iv.subSequence(startIndex$iv, endIndex$iv + 1);
    }

    @NotNull
    public static final String trim(@NotNull String $this$trim, @NotNull char ... chars) {
        Intrinsics.checkNotNullParameter($this$trim, "$this$trim");
        Intrinsics.checkNotNullParameter(chars, "chars");
        String $this$trim$iv = $this$trim;
        boolean $i$f$trim = false;
        CharSequence $this$trim$iv$iv = $this$trim$iv;
        boolean $i$f$trim2 = false;
        int startIndex$iv$iv = 0;
        int endIndex$iv$iv = $this$trim$iv$iv.length() - 1;
        boolean startFound$iv$iv = false;
        while (startIndex$iv$iv <= endIndex$iv$iv) {
            int index$iv$iv = !startFound$iv$iv ? startIndex$iv$iv : endIndex$iv$iv;
            char it = $this$trim$iv$iv.charAt(index$iv$iv);
            boolean bl = false;
            boolean match$iv$iv = ArraysKt.contains(chars, it);
            if (!startFound$iv$iv) {
                if (!match$iv$iv) {
                    startFound$iv$iv = true;
                    continue;
                }
                ++startIndex$iv$iv;
                continue;
            }
            if (!match$iv$iv) break;
            --endIndex$iv$iv;
        }
        return ((Object)$this$trim$iv$iv.subSequence(startIndex$iv$iv, endIndex$iv$iv + 1)).toString();
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public static final CharSequence trimStart(@NotNull CharSequence $this$trimStart, @NotNull char ... chars) {
        CharSequence charSequence;
        block2: {
            Intrinsics.checkNotNullParameter($this$trimStart, "$this$trimStart");
            Intrinsics.checkNotNullParameter(chars, "chars");
            CharSequence $this$trimStart$iv = $this$trimStart;
            boolean $i$f$trimStart = false;
            int n = 0;
            int n2 = $this$trimStart$iv.length();
            while (n < n2) {
                void index$iv;
                char it = $this$trimStart$iv.charAt((int)index$iv);
                boolean bl = false;
                if (!ArraysKt.contains(chars, it)) {
                    charSequence = $this$trimStart$iv.subSequence((int)index$iv, $this$trimStart$iv.length());
                    break block2;
                }
                ++index$iv;
            }
            charSequence = "";
        }
        return charSequence;
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public static final String trimStart(@NotNull String $this$trimStart, @NotNull char ... chars) {
        CharSequence charSequence;
        block2: {
            Intrinsics.checkNotNullParameter($this$trimStart, "$this$trimStart");
            Intrinsics.checkNotNullParameter(chars, "chars");
            String $this$trimStart$iv = $this$trimStart;
            boolean $i$f$trimStart = false;
            CharSequence $this$trimStart$iv$iv = $this$trimStart$iv;
            boolean $i$f$trimStart2 = false;
            int n = 0;
            int n2 = $this$trimStart$iv$iv.length();
            while (n < n2) {
                void index$iv$iv;
                char it = $this$trimStart$iv$iv.charAt((int)index$iv$iv);
                boolean bl = false;
                if (!ArraysKt.contains(chars, it)) {
                    charSequence = $this$trimStart$iv$iv.subSequence((int)index$iv$iv, $this$trimStart$iv$iv.length());
                    break block2;
                }
                ++index$iv$iv;
            }
            charSequence = "";
        }
        return ((Object)charSequence).toString();
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public static final CharSequence trimEnd(@NotNull CharSequence $this$trimEnd, @NotNull char ... chars) {
        CharSequence charSequence;
        block2: {
            Intrinsics.checkNotNullParameter($this$trimEnd, "$this$trimEnd");
            Intrinsics.checkNotNullParameter(chars, "chars");
            CharSequence $this$trimEnd$iv = $this$trimEnd;
            boolean $i$f$trimEnd = false;
            int n = $this$trimEnd$iv.length();
            --n;
            boolean bl = false;
            while (n >= 0) {
                void index$iv;
                char it = $this$trimEnd$iv.charAt((int)index$iv);
                boolean bl2 = false;
                if (!ArraysKt.contains(chars, it)) {
                    charSequence = $this$trimEnd$iv.subSequence(0, (int)(index$iv + true));
                    break block2;
                }
                --index$iv;
            }
            charSequence = "";
        }
        return charSequence;
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public static final String trimEnd(@NotNull String $this$trimEnd, @NotNull char ... chars) {
        CharSequence charSequence;
        block2: {
            Intrinsics.checkNotNullParameter($this$trimEnd, "$this$trimEnd");
            Intrinsics.checkNotNullParameter(chars, "chars");
            String $this$trimEnd$iv = $this$trimEnd;
            boolean $i$f$trimEnd = false;
            CharSequence $this$trimEnd$iv$iv = $this$trimEnd$iv;
            boolean $i$f$trimEnd2 = false;
            int n = $this$trimEnd$iv$iv.length();
            --n;
            boolean bl = false;
            while (n >= 0) {
                void index$iv$iv;
                char it = $this$trimEnd$iv$iv.charAt((int)index$iv$iv);
                boolean bl2 = false;
                if (!ArraysKt.contains(chars, it)) {
                    charSequence = $this$trimEnd$iv$iv.subSequence(0, (int)(index$iv$iv + true));
                    break block2;
                }
                --index$iv$iv;
            }
            charSequence = "";
        }
        return ((Object)charSequence).toString();
    }

    @NotNull
    public static final CharSequence trim(@NotNull CharSequence $this$trim) {
        Intrinsics.checkNotNullParameter($this$trim, "$this$trim");
        CharSequence $this$trim$iv = $this$trim;
        boolean $i$f$trim = false;
        int startIndex$iv = 0;
        int endIndex$iv = $this$trim$iv.length() - 1;
        boolean startFound$iv = false;
        while (startIndex$iv <= endIndex$iv) {
            int index$iv = !startFound$iv ? startIndex$iv : endIndex$iv;
            char p1 = $this$trim$iv.charAt(index$iv);
            boolean bl = false;
            boolean match$iv = CharsKt.isWhitespace(p1);
            if (!startFound$iv) {
                if (!match$iv) {
                    startFound$iv = true;
                    continue;
                }
                ++startIndex$iv;
                continue;
            }
            if (!match$iv) break;
            --endIndex$iv;
        }
        return $this$trim$iv.subSequence(startIndex$iv, endIndex$iv + 1);
    }

    @InlineOnly
    private static final String trim(String $this$trim) {
        int $i$f$trim = 0;
        String string = $this$trim;
        if (string == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return ((Object)StringsKt.trim((CharSequence)string)).toString();
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public static final CharSequence trimStart(@NotNull CharSequence $this$trimStart) {
        CharSequence charSequence;
        block2: {
            Intrinsics.checkNotNullParameter($this$trimStart, "$this$trimStart");
            CharSequence $this$trimStart$iv = $this$trimStart;
            boolean $i$f$trimStart = false;
            int n = 0;
            int n2 = $this$trimStart$iv.length();
            while (n < n2) {
                void index$iv;
                char p1 = $this$trimStart$iv.charAt((int)index$iv);
                boolean bl = false;
                if (!CharsKt.isWhitespace(p1)) {
                    charSequence = $this$trimStart$iv.subSequence((int)index$iv, $this$trimStart$iv.length());
                    break block2;
                }
                ++index$iv;
            }
            charSequence = "";
        }
        return charSequence;
    }

    @InlineOnly
    private static final String trimStart(String $this$trimStart) {
        int $i$f$trimStart = 0;
        String string = $this$trimStart;
        if (string == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return ((Object)StringsKt.trimStart((CharSequence)string)).toString();
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public static final CharSequence trimEnd(@NotNull CharSequence $this$trimEnd) {
        CharSequence charSequence;
        block2: {
            Intrinsics.checkNotNullParameter($this$trimEnd, "$this$trimEnd");
            CharSequence $this$trimEnd$iv = $this$trimEnd;
            boolean $i$f$trimEnd = false;
            int n = $this$trimEnd$iv.length();
            --n;
            boolean bl = false;
            while (n >= 0) {
                void index$iv;
                char p1 = $this$trimEnd$iv.charAt((int)index$iv);
                boolean bl2 = false;
                if (!CharsKt.isWhitespace(p1)) {
                    charSequence = $this$trimEnd$iv.subSequence(0, (int)(index$iv + true));
                    break block2;
                }
                --index$iv;
            }
            charSequence = "";
        }
        return charSequence;
    }

    @InlineOnly
    private static final String trimEnd(String $this$trimEnd) {
        int $i$f$trimEnd = 0;
        String string = $this$trimEnd;
        if (string == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return ((Object)StringsKt.trimEnd((CharSequence)string)).toString();
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public static final CharSequence padStart(@NotNull CharSequence $this$padStart, int length, char padChar) {
        Intrinsics.checkNotNullParameter($this$padStart, "$this$padStart");
        if (length < 0) {
            throw (Throwable)new IllegalArgumentException("Desired length " + length + " is less than zero.");
        }
        if (length <= $this$padStart.length()) {
            return $this$padStart.subSequence(0, $this$padStart.length());
        }
        StringBuilder sb = new StringBuilder(length);
        int n = 1;
        int n2 = length - $this$padStart.length();
        if (n <= n2) {
            while (true) {
                void i;
                sb.append(padChar);
                if (i == n2) break;
                ++i;
            }
        }
        sb.append($this$padStart);
        return sb;
    }

    public static /* synthetic */ CharSequence padStart$default(CharSequence charSequence, int n, char c, int n2, Object object) {
        if ((n2 & 2) != 0) {
            c = (char)32;
        }
        return StringsKt.padStart(charSequence, n, c);
    }

    @NotNull
    public static final String padStart(@NotNull String $this$padStart, int length, char padChar) {
        Intrinsics.checkNotNullParameter($this$padStart, "$this$padStart");
        return ((Object)StringsKt.padStart((CharSequence)$this$padStart, length, padChar)).toString();
    }

    public static /* synthetic */ String padStart$default(String string, int n, char c, int n2, Object object) {
        if ((n2 & 2) != 0) {
            c = (char)32;
        }
        return StringsKt.padStart(string, n, c);
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public static final CharSequence padEnd(@NotNull CharSequence $this$padEnd, int length, char padChar) {
        Intrinsics.checkNotNullParameter($this$padEnd, "$this$padEnd");
        if (length < 0) {
            throw (Throwable)new IllegalArgumentException("Desired length " + length + " is less than zero.");
        }
        if (length <= $this$padEnd.length()) {
            return $this$padEnd.subSequence(0, $this$padEnd.length());
        }
        StringBuilder sb = new StringBuilder(length);
        sb.append($this$padEnd);
        int n = 1;
        int n2 = length - $this$padEnd.length();
        if (n <= n2) {
            while (true) {
                void i;
                sb.append(padChar);
                if (i == n2) break;
                ++i;
            }
        }
        return sb;
    }

    public static /* synthetic */ CharSequence padEnd$default(CharSequence charSequence, int n, char c, int n2, Object object) {
        if ((n2 & 2) != 0) {
            c = (char)32;
        }
        return StringsKt.padEnd(charSequence, n, c);
    }

    @NotNull
    public static final String padEnd(@NotNull String $this$padEnd, int length, char padChar) {
        Intrinsics.checkNotNullParameter($this$padEnd, "$this$padEnd");
        return ((Object)StringsKt.padEnd((CharSequence)$this$padEnd, length, padChar)).toString();
    }

    public static /* synthetic */ String padEnd$default(String string, int n, char c, int n2, Object object) {
        if ((n2 & 2) != 0) {
            c = (char)32;
        }
        return StringsKt.padEnd(string, n, c);
    }

    @InlineOnly
    private static final boolean isNullOrEmpty(CharSequence $this$isNullOrEmpty) {
        int $i$f$isNullOrEmpty = 0;
        boolean bl = false;
        return $this$isNullOrEmpty == null || $this$isNullOrEmpty.length() == 0;
    }

    @InlineOnly
    private static final boolean isEmpty(CharSequence $this$isEmpty) {
        int $i$f$isEmpty = 0;
        return $this$isEmpty.length() == 0;
    }

    @InlineOnly
    private static final boolean isNotEmpty(CharSequence $this$isNotEmpty) {
        int $i$f$isNotEmpty = 0;
        return $this$isNotEmpty.length() > 0;
    }

    @InlineOnly
    private static final boolean isNotBlank(CharSequence $this$isNotBlank) {
        int $i$f$isNotBlank = 0;
        return !StringsKt.isBlank($this$isNotBlank);
    }

    @InlineOnly
    private static final boolean isNullOrBlank(CharSequence $this$isNullOrBlank) {
        int $i$f$isNullOrBlank = 0;
        boolean bl = false;
        return $this$isNullOrBlank == null || StringsKt.isBlank($this$isNullOrBlank);
    }

    @NotNull
    public static final CharIterator iterator(@NotNull CharSequence $this$iterator) {
        Intrinsics.checkNotNullParameter($this$iterator, "$this$iterator");
        return new CharIterator($this$iterator){
            private int index;
            final /* synthetic */ CharSequence $this_iterator;

            public char nextChar() {
                int n = this.index;
                this.index = n + 1;
                return this.$this_iterator.charAt(n);
            }

            public boolean hasNext() {
                return this.index < this.$this_iterator.length();
            }
            {
                this.$this_iterator = $receiver;
            }
        };
    }

    @InlineOnly
    private static final String orEmpty(String $this$orEmpty) {
        int $i$f$orEmpty = 0;
        String string = $this$orEmpty;
        if (string == null) {
            string = "";
        }
        return string;
    }

    @SinceKotlin(version="1.3")
    @InlineOnly
    private static final <C extends CharSequence & R, R> R ifEmpty(C $this$ifEmpty, Function0<? extends R> defaultValue) {
        int $i$f$ifEmpty = 0;
        C c = $this$ifEmpty;
        boolean bl = false;
        return (R)(c.length() == 0 ? defaultValue.invoke() : $this$ifEmpty);
    }

    @SinceKotlin(version="1.3")
    @InlineOnly
    private static final <C extends CharSequence & R, R> R ifBlank(C $this$ifBlank, Function0<? extends R> defaultValue) {
        int $i$f$ifBlank = 0;
        return (R)(StringsKt.isBlank($this$ifBlank) ? defaultValue.invoke() : $this$ifBlank);
    }

    @NotNull
    public static final IntRange getIndices(@NotNull CharSequence $this$indices) {
        Intrinsics.checkNotNullParameter($this$indices, "$this$indices");
        int n = 0;
        return new IntRange(n, $this$indices.length() - 1);
    }

    public static final int getLastIndex(@NotNull CharSequence $this$lastIndex) {
        Intrinsics.checkNotNullParameter($this$lastIndex, "$this$lastIndex");
        return $this$lastIndex.length() - 1;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static final boolean hasSurrogatePairAt(@NotNull CharSequence $this$hasSurrogatePairAt, int index) {
        Intrinsics.checkNotNullParameter($this$hasSurrogatePairAt, "$this$hasSurrogatePairAt");
        int n = index;
        if (0 > n) return false;
        if ($this$hasSurrogatePairAt.length() - 2 < n) return false;
        char c = $this$hasSurrogatePairAt.charAt(index);
        boolean bl = false;
        if (!Character.isHighSurrogate(c)) return false;
        char c2 = $this$hasSurrogatePairAt.charAt(index + 1);
        bl = false;
        if (!Character.isLowSurrogate(c2)) return false;
        return true;
    }

    @NotNull
    public static final String substring(@NotNull String $this$substring, @NotNull IntRange range) {
        Intrinsics.checkNotNullParameter($this$substring, "$this$substring");
        Intrinsics.checkNotNullParameter(range, "range");
        String string = $this$substring;
        int n = range.getStart();
        int n2 = range.getEndInclusive() + 1;
        boolean bl = false;
        String string2 = string.substring(n, n2);
        Intrinsics.checkNotNullExpressionValue(string2, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
        return string2;
    }

    @NotNull
    public static final CharSequence subSequence(@NotNull CharSequence $this$subSequence, @NotNull IntRange range) {
        Intrinsics.checkNotNullParameter($this$subSequence, "$this$subSequence");
        Intrinsics.checkNotNullParameter(range, "range");
        return $this$subSequence.subSequence(range.getStart(), range.getEndInclusive() + 1);
    }

    @Deprecated(message="Use parameters named startIndex and endIndex.", replaceWith=@ReplaceWith(imports={}, expression="subSequence(startIndex = start, endIndex = end)"))
    @InlineOnly
    private static final CharSequence subSequence(String $this$subSequence, int start, int end) {
        int $i$f$subSequence = 0;
        return $this$subSequence.subSequence(start, end);
    }

    @InlineOnly
    private static final String substring(CharSequence $this$substring, int startIndex, int endIndex) {
        int $i$f$substring = 0;
        return ((Object)$this$substring.subSequence(startIndex, endIndex)).toString();
    }

    static /* synthetic */ String substring$default(CharSequence $this$substring, int startIndex, int endIndex, int n, Object object) {
        if ((n & 2) != 0) {
            endIndex = $this$substring.length();
        }
        boolean $i$f$substring = false;
        return ((Object)$this$substring.subSequence(startIndex, endIndex)).toString();
    }

    @NotNull
    public static final String substring(@NotNull CharSequence $this$substring, @NotNull IntRange range) {
        Intrinsics.checkNotNullParameter($this$substring, "$this$substring");
        Intrinsics.checkNotNullParameter(range, "range");
        return ((Object)$this$substring.subSequence(range.getStart(), range.getEndInclusive() + 1)).toString();
    }

    @NotNull
    public static final String substringBefore(@NotNull String $this$substringBefore, char delimiter, @NotNull String missingDelimiterValue) {
        String string;
        Intrinsics.checkNotNullParameter($this$substringBefore, "$this$substringBefore");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.indexOf$default((CharSequence)$this$substringBefore, delimiter, 0, false, 6, null);
        if (index == -1) {
            string = missingDelimiterValue;
        } else {
            String string2 = $this$substringBefore;
            int n = 0;
            boolean bl = false;
            String string3 = string2.substring(n, index);
            string = string3;
            Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
        }
        return string;
    }

    public static /* synthetic */ String substringBefore$default(String string, char c, String string2, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = string;
        }
        return StringsKt.substringBefore(string, c, string2);
    }

    @NotNull
    public static final String substringBefore(@NotNull String $this$substringBefore, @NotNull String delimiter, @NotNull String missingDelimiterValue) {
        String string;
        Intrinsics.checkNotNullParameter($this$substringBefore, "$this$substringBefore");
        Intrinsics.checkNotNullParameter(delimiter, "delimiter");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.indexOf$default((CharSequence)$this$substringBefore, delimiter, 0, false, 6, null);
        if (index == -1) {
            string = missingDelimiterValue;
        } else {
            String string2 = $this$substringBefore;
            int n = 0;
            boolean bl = false;
            String string3 = string2.substring(n, index);
            string = string3;
            Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
        }
        return string;
    }

    public static /* synthetic */ String substringBefore$default(String string, String string2, String string3, int n, Object object) {
        if ((n & 2) != 0) {
            string3 = string;
        }
        return StringsKt.substringBefore(string, string2, string3);
    }

    @NotNull
    public static final String substringAfter(@NotNull String $this$substringAfter, char delimiter, @NotNull String missingDelimiterValue) {
        String string;
        Intrinsics.checkNotNullParameter($this$substringAfter, "$this$substringAfter");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.indexOf$default((CharSequence)$this$substringAfter, delimiter, 0, false, 6, null);
        if (index == -1) {
            string = missingDelimiterValue;
        } else {
            String string2 = $this$substringAfter;
            int n = index + 1;
            int n2 = $this$substringAfter.length();
            boolean bl = false;
            String string3 = string2.substring(n, n2);
            string = string3;
            Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
        }
        return string;
    }

    public static /* synthetic */ String substringAfter$default(String string, char c, String string2, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = string;
        }
        return StringsKt.substringAfter(string, c, string2);
    }

    @NotNull
    public static final String substringAfter(@NotNull String $this$substringAfter, @NotNull String delimiter, @NotNull String missingDelimiterValue) {
        String string;
        Intrinsics.checkNotNullParameter($this$substringAfter, "$this$substringAfter");
        Intrinsics.checkNotNullParameter(delimiter, "delimiter");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.indexOf$default((CharSequence)$this$substringAfter, delimiter, 0, false, 6, null);
        if (index == -1) {
            string = missingDelimiterValue;
        } else {
            String string2 = $this$substringAfter;
            int n = index + delimiter.length();
            int n2 = $this$substringAfter.length();
            boolean bl = false;
            String string3 = string2.substring(n, n2);
            string = string3;
            Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
        }
        return string;
    }

    public static /* synthetic */ String substringAfter$default(String string, String string2, String string3, int n, Object object) {
        if ((n & 2) != 0) {
            string3 = string;
        }
        return StringsKt.substringAfter(string, string2, string3);
    }

    @NotNull
    public static final String substringBeforeLast(@NotNull String $this$substringBeforeLast, char delimiter, @NotNull String missingDelimiterValue) {
        String string;
        Intrinsics.checkNotNullParameter($this$substringBeforeLast, "$this$substringBeforeLast");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.lastIndexOf$default((CharSequence)$this$substringBeforeLast, delimiter, 0, false, 6, null);
        if (index == -1) {
            string = missingDelimiterValue;
        } else {
            String string2 = $this$substringBeforeLast;
            int n = 0;
            boolean bl = false;
            String string3 = string2.substring(n, index);
            string = string3;
            Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
        }
        return string;
    }

    public static /* synthetic */ String substringBeforeLast$default(String string, char c, String string2, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = string;
        }
        return StringsKt.substringBeforeLast(string, c, string2);
    }

    @NotNull
    public static final String substringBeforeLast(@NotNull String $this$substringBeforeLast, @NotNull String delimiter, @NotNull String missingDelimiterValue) {
        String string;
        Intrinsics.checkNotNullParameter($this$substringBeforeLast, "$this$substringBeforeLast");
        Intrinsics.checkNotNullParameter(delimiter, "delimiter");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.lastIndexOf$default((CharSequence)$this$substringBeforeLast, delimiter, 0, false, 6, null);
        if (index == -1) {
            string = missingDelimiterValue;
        } else {
            String string2 = $this$substringBeforeLast;
            int n = 0;
            boolean bl = false;
            String string3 = string2.substring(n, index);
            string = string3;
            Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
        }
        return string;
    }

    public static /* synthetic */ String substringBeforeLast$default(String string, String string2, String string3, int n, Object object) {
        if ((n & 2) != 0) {
            string3 = string;
        }
        return StringsKt.substringBeforeLast(string, string2, string3);
    }

    @NotNull
    public static final String substringAfterLast(@NotNull String $this$substringAfterLast, char delimiter, @NotNull String missingDelimiterValue) {
        String string;
        Intrinsics.checkNotNullParameter($this$substringAfterLast, "$this$substringAfterLast");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.lastIndexOf$default((CharSequence)$this$substringAfterLast, delimiter, 0, false, 6, null);
        if (index == -1) {
            string = missingDelimiterValue;
        } else {
            String string2 = $this$substringAfterLast;
            int n = index + 1;
            int n2 = $this$substringAfterLast.length();
            boolean bl = false;
            String string3 = string2.substring(n, n2);
            string = string3;
            Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
        }
        return string;
    }

    public static /* synthetic */ String substringAfterLast$default(String string, char c, String string2, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = string;
        }
        return StringsKt.substringAfterLast(string, c, string2);
    }

    @NotNull
    public static final String substringAfterLast(@NotNull String $this$substringAfterLast, @NotNull String delimiter, @NotNull String missingDelimiterValue) {
        String string;
        Intrinsics.checkNotNullParameter($this$substringAfterLast, "$this$substringAfterLast");
        Intrinsics.checkNotNullParameter(delimiter, "delimiter");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.lastIndexOf$default((CharSequence)$this$substringAfterLast, delimiter, 0, false, 6, null);
        if (index == -1) {
            string = missingDelimiterValue;
        } else {
            String string2 = $this$substringAfterLast;
            int n = index + delimiter.length();
            int n2 = $this$substringAfterLast.length();
            boolean bl = false;
            String string3 = string2.substring(n, n2);
            string = string3;
            Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
        }
        return string;
    }

    public static /* synthetic */ String substringAfterLast$default(String string, String string2, String string3, int n, Object object) {
        if ((n & 2) != 0) {
            string3 = string;
        }
        return StringsKt.substringAfterLast(string, string2, string3);
    }

    @NotNull
    public static final CharSequence replaceRange(@NotNull CharSequence $this$replaceRange, int startIndex, int endIndex, @NotNull CharSequence replacement) {
        StringBuilder sb;
        Intrinsics.checkNotNullParameter($this$replaceRange, "$this$replaceRange");
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        if (endIndex < startIndex) {
            throw (Throwable)new IndexOutOfBoundsException("End index (" + endIndex + ") is less than start index (" + startIndex + ").");
        }
        StringBuilder stringBuilder = sb = new StringBuilder();
        int n = 0;
        boolean bl = false;
        Intrinsics.checkNotNullExpressionValue(stringBuilder.append($this$replaceRange, n, startIndex), "this.append(value, startIndex, endIndex)");
        sb.append(replacement);
        stringBuilder = sb;
        n = $this$replaceRange.length();
        bl = false;
        Intrinsics.checkNotNullExpressionValue(stringBuilder.append($this$replaceRange, endIndex, n), "this.append(value, startIndex, endIndex)");
        return sb;
    }

    @InlineOnly
    private static final String replaceRange(String $this$replaceRange, int startIndex, int endIndex, CharSequence replacement) {
        int $i$f$replaceRange = 0;
        String string = $this$replaceRange;
        if (string == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return ((Object)StringsKt.replaceRange((CharSequence)string, startIndex, endIndex, replacement)).toString();
    }

    @NotNull
    public static final CharSequence replaceRange(@NotNull CharSequence $this$replaceRange, @NotNull IntRange range, @NotNull CharSequence replacement) {
        Intrinsics.checkNotNullParameter($this$replaceRange, "$this$replaceRange");
        Intrinsics.checkNotNullParameter(range, "range");
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        return StringsKt.replaceRange($this$replaceRange, (int)range.getStart(), range.getEndInclusive() + 1, replacement);
    }

    @InlineOnly
    private static final String replaceRange(String $this$replaceRange, IntRange range, CharSequence replacement) {
        int $i$f$replaceRange = 0;
        String string = $this$replaceRange;
        if (string == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return ((Object)StringsKt.replaceRange((CharSequence)string, range, replacement)).toString();
    }

    @NotNull
    public static final CharSequence removeRange(@NotNull CharSequence $this$removeRange, int startIndex, int endIndex) {
        StringBuilder sb;
        Intrinsics.checkNotNullParameter($this$removeRange, "$this$removeRange");
        if (endIndex < startIndex) {
            throw (Throwable)new IndexOutOfBoundsException("End index (" + endIndex + ") is less than start index (" + startIndex + ").");
        }
        if (endIndex == startIndex) {
            return $this$removeRange.subSequence(0, $this$removeRange.length());
        }
        StringBuilder stringBuilder = sb = new StringBuilder($this$removeRange.length() - (endIndex - startIndex));
        int n = 0;
        boolean bl = false;
        Intrinsics.checkNotNullExpressionValue(stringBuilder.append($this$removeRange, n, startIndex), "this.append(value, startIndex, endIndex)");
        stringBuilder = sb;
        n = $this$removeRange.length();
        bl = false;
        Intrinsics.checkNotNullExpressionValue(stringBuilder.append($this$removeRange, endIndex, n), "this.append(value, startIndex, endIndex)");
        return sb;
    }

    @InlineOnly
    private static final String removeRange(String $this$removeRange, int startIndex, int endIndex) {
        int $i$f$removeRange = 0;
        String string = $this$removeRange;
        if (string == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return ((Object)StringsKt.removeRange((CharSequence)string, startIndex, endIndex)).toString();
    }

    @NotNull
    public static final CharSequence removeRange(@NotNull CharSequence $this$removeRange, @NotNull IntRange range) {
        Intrinsics.checkNotNullParameter($this$removeRange, "$this$removeRange");
        Intrinsics.checkNotNullParameter(range, "range");
        return StringsKt.removeRange($this$removeRange, (int)range.getStart(), range.getEndInclusive() + 1);
    }

    @InlineOnly
    private static final String removeRange(String $this$removeRange, IntRange range) {
        int $i$f$removeRange = 0;
        String string = $this$removeRange;
        if (string == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return ((Object)StringsKt.removeRange((CharSequence)string, range)).toString();
    }

    @NotNull
    public static final CharSequence removePrefix(@NotNull CharSequence $this$removePrefix, @NotNull CharSequence prefix) {
        Intrinsics.checkNotNullParameter($this$removePrefix, "$this$removePrefix");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        if (StringsKt.startsWith$default($this$removePrefix, prefix, false, 2, null)) {
            return $this$removePrefix.subSequence(prefix.length(), $this$removePrefix.length());
        }
        return $this$removePrefix.subSequence(0, $this$removePrefix.length());
    }

    @NotNull
    public static final String removePrefix(@NotNull String $this$removePrefix, @NotNull CharSequence prefix) {
        Intrinsics.checkNotNullParameter($this$removePrefix, "$this$removePrefix");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        if (StringsKt.startsWith$default((CharSequence)$this$removePrefix, prefix, false, 2, null)) {
            String string = $this$removePrefix;
            int n = prefix.length();
            boolean bl = false;
            String string2 = string.substring(n);
            Intrinsics.checkNotNullExpressionValue(string2, "(this as java.lang.String).substring(startIndex)");
            return string2;
        }
        return $this$removePrefix;
    }

    @NotNull
    public static final CharSequence removeSuffix(@NotNull CharSequence $this$removeSuffix, @NotNull CharSequence suffix) {
        Intrinsics.checkNotNullParameter($this$removeSuffix, "$this$removeSuffix");
        Intrinsics.checkNotNullParameter(suffix, "suffix");
        if (StringsKt.endsWith$default($this$removeSuffix, suffix, false, 2, null)) {
            return $this$removeSuffix.subSequence(0, $this$removeSuffix.length() - suffix.length());
        }
        return $this$removeSuffix.subSequence(0, $this$removeSuffix.length());
    }

    @NotNull
    public static final String removeSuffix(@NotNull String $this$removeSuffix, @NotNull CharSequence suffix) {
        Intrinsics.checkNotNullParameter($this$removeSuffix, "$this$removeSuffix");
        Intrinsics.checkNotNullParameter(suffix, "suffix");
        if (StringsKt.endsWith$default((CharSequence)$this$removeSuffix, suffix, false, 2, null)) {
            String string = $this$removeSuffix;
            int n = 0;
            int n2 = $this$removeSuffix.length() - suffix.length();
            boolean bl = false;
            String string2 = string.substring(n, n2);
            Intrinsics.checkNotNullExpressionValue(string2, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
            return string2;
        }
        return $this$removeSuffix;
    }

    @NotNull
    public static final CharSequence removeSurrounding(@NotNull CharSequence $this$removeSurrounding, @NotNull CharSequence prefix, @NotNull CharSequence suffix) {
        Intrinsics.checkNotNullParameter($this$removeSurrounding, "$this$removeSurrounding");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(suffix, "suffix");
        if ($this$removeSurrounding.length() >= prefix.length() + suffix.length() && StringsKt.startsWith$default($this$removeSurrounding, prefix, false, 2, null) && StringsKt.endsWith$default($this$removeSurrounding, suffix, false, 2, null)) {
            return $this$removeSurrounding.subSequence(prefix.length(), $this$removeSurrounding.length() - suffix.length());
        }
        return $this$removeSurrounding.subSequence(0, $this$removeSurrounding.length());
    }

    @NotNull
    public static final String removeSurrounding(@NotNull String $this$removeSurrounding, @NotNull CharSequence prefix, @NotNull CharSequence suffix) {
        Intrinsics.checkNotNullParameter($this$removeSurrounding, "$this$removeSurrounding");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(suffix, "suffix");
        if ($this$removeSurrounding.length() >= prefix.length() + suffix.length() && StringsKt.startsWith$default((CharSequence)$this$removeSurrounding, prefix, false, 2, null) && StringsKt.endsWith$default((CharSequence)$this$removeSurrounding, suffix, false, 2, null)) {
            String string = $this$removeSurrounding;
            int n = prefix.length();
            int n2 = $this$removeSurrounding.length() - suffix.length();
            boolean bl = false;
            String string2 = string.substring(n, n2);
            Intrinsics.checkNotNullExpressionValue(string2, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
            return string2;
        }
        return $this$removeSurrounding;
    }

    @NotNull
    public static final CharSequence removeSurrounding(@NotNull CharSequence $this$removeSurrounding, @NotNull CharSequence delimiter) {
        Intrinsics.checkNotNullParameter($this$removeSurrounding, "$this$removeSurrounding");
        Intrinsics.checkNotNullParameter(delimiter, "delimiter");
        return StringsKt.removeSurrounding($this$removeSurrounding, delimiter, delimiter);
    }

    @NotNull
    public static final String removeSurrounding(@NotNull String $this$removeSurrounding, @NotNull CharSequence delimiter) {
        Intrinsics.checkNotNullParameter($this$removeSurrounding, "$this$removeSurrounding");
        Intrinsics.checkNotNullParameter(delimiter, "delimiter");
        return StringsKt.removeSurrounding($this$removeSurrounding, delimiter, delimiter);
    }

    @NotNull
    public static final String replaceBefore(@NotNull String $this$replaceBefore, char delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
        String string;
        Intrinsics.checkNotNullParameter($this$replaceBefore, "$this$replaceBefore");
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.indexOf$default((CharSequence)$this$replaceBefore, delimiter, 0, false, 6, null);
        if (index == -1) {
            string = missingDelimiterValue;
        } else {
            String string2 = $this$replaceBefore;
            int n = 0;
            boolean bl = false;
            string = ((Object)StringsKt.replaceRange((CharSequence)string2, n, index, (CharSequence)replacement)).toString();
        }
        return string;
    }

    public static /* synthetic */ String replaceBefore$default(String string, char c, String string2, String string3, int n, Object object) {
        if ((n & 4) != 0) {
            string3 = string;
        }
        return StringsKt.replaceBefore(string, c, string2, string3);
    }

    @NotNull
    public static final String replaceBefore(@NotNull String $this$replaceBefore, @NotNull String delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
        String string;
        Intrinsics.checkNotNullParameter($this$replaceBefore, "$this$replaceBefore");
        Intrinsics.checkNotNullParameter(delimiter, "delimiter");
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.indexOf$default((CharSequence)$this$replaceBefore, delimiter, 0, false, 6, null);
        if (index == -1) {
            string = missingDelimiterValue;
        } else {
            String string2 = $this$replaceBefore;
            int n = 0;
            boolean bl = false;
            string = ((Object)StringsKt.replaceRange((CharSequence)string2, n, index, (CharSequence)replacement)).toString();
        }
        return string;
    }

    public static /* synthetic */ String replaceBefore$default(String string, String string2, String string3, String string4, int n, Object object) {
        if ((n & 4) != 0) {
            string4 = string;
        }
        return StringsKt.replaceBefore(string, string2, string3, string4);
    }

    @NotNull
    public static final String replaceAfter(@NotNull String $this$replaceAfter, char delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
        String string;
        Intrinsics.checkNotNullParameter($this$replaceAfter, "$this$replaceAfter");
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.indexOf$default((CharSequence)$this$replaceAfter, delimiter, 0, false, 6, null);
        if (index == -1) {
            string = missingDelimiterValue;
        } else {
            String string2 = $this$replaceAfter;
            int n = index + 1;
            int n2 = $this$replaceAfter.length();
            boolean bl = false;
            string = ((Object)StringsKt.replaceRange((CharSequence)string2, n, n2, (CharSequence)replacement)).toString();
        }
        return string;
    }

    public static /* synthetic */ String replaceAfter$default(String string, char c, String string2, String string3, int n, Object object) {
        if ((n & 4) != 0) {
            string3 = string;
        }
        return StringsKt.replaceAfter(string, c, string2, string3);
    }

    @NotNull
    public static final String replaceAfter(@NotNull String $this$replaceAfter, @NotNull String delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
        String string;
        Intrinsics.checkNotNullParameter($this$replaceAfter, "$this$replaceAfter");
        Intrinsics.checkNotNullParameter(delimiter, "delimiter");
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.indexOf$default((CharSequence)$this$replaceAfter, delimiter, 0, false, 6, null);
        if (index == -1) {
            string = missingDelimiterValue;
        } else {
            String string2 = $this$replaceAfter;
            int n = index + delimiter.length();
            int n2 = $this$replaceAfter.length();
            boolean bl = false;
            string = ((Object)StringsKt.replaceRange((CharSequence)string2, n, n2, (CharSequence)replacement)).toString();
        }
        return string;
    }

    public static /* synthetic */ String replaceAfter$default(String string, String string2, String string3, String string4, int n, Object object) {
        if ((n & 4) != 0) {
            string4 = string;
        }
        return StringsKt.replaceAfter(string, string2, string3, string4);
    }

    @NotNull
    public static final String replaceAfterLast(@NotNull String $this$replaceAfterLast, @NotNull String delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
        String string;
        Intrinsics.checkNotNullParameter($this$replaceAfterLast, "$this$replaceAfterLast");
        Intrinsics.checkNotNullParameter(delimiter, "delimiter");
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.lastIndexOf$default((CharSequence)$this$replaceAfterLast, delimiter, 0, false, 6, null);
        if (index == -1) {
            string = missingDelimiterValue;
        } else {
            String string2 = $this$replaceAfterLast;
            int n = index + delimiter.length();
            int n2 = $this$replaceAfterLast.length();
            boolean bl = false;
            string = ((Object)StringsKt.replaceRange((CharSequence)string2, n, n2, (CharSequence)replacement)).toString();
        }
        return string;
    }

    public static /* synthetic */ String replaceAfterLast$default(String string, String string2, String string3, String string4, int n, Object object) {
        if ((n & 4) != 0) {
            string4 = string;
        }
        return StringsKt.replaceAfterLast(string, string2, string3, string4);
    }

    @NotNull
    public static final String replaceAfterLast(@NotNull String $this$replaceAfterLast, char delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
        String string;
        Intrinsics.checkNotNullParameter($this$replaceAfterLast, "$this$replaceAfterLast");
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.lastIndexOf$default((CharSequence)$this$replaceAfterLast, delimiter, 0, false, 6, null);
        if (index == -1) {
            string = missingDelimiterValue;
        } else {
            String string2 = $this$replaceAfterLast;
            int n = index + 1;
            int n2 = $this$replaceAfterLast.length();
            boolean bl = false;
            string = ((Object)StringsKt.replaceRange((CharSequence)string2, n, n2, (CharSequence)replacement)).toString();
        }
        return string;
    }

    public static /* synthetic */ String replaceAfterLast$default(String string, char c, String string2, String string3, int n, Object object) {
        if ((n & 4) != 0) {
            string3 = string;
        }
        return StringsKt.replaceAfterLast(string, c, string2, string3);
    }

    @NotNull
    public static final String replaceBeforeLast(@NotNull String $this$replaceBeforeLast, char delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
        String string;
        Intrinsics.checkNotNullParameter($this$replaceBeforeLast, "$this$replaceBeforeLast");
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.lastIndexOf$default((CharSequence)$this$replaceBeforeLast, delimiter, 0, false, 6, null);
        if (index == -1) {
            string = missingDelimiterValue;
        } else {
            String string2 = $this$replaceBeforeLast;
            int n = 0;
            boolean bl = false;
            string = ((Object)StringsKt.replaceRange((CharSequence)string2, n, index, (CharSequence)replacement)).toString();
        }
        return string;
    }

    public static /* synthetic */ String replaceBeforeLast$default(String string, char c, String string2, String string3, int n, Object object) {
        if ((n & 4) != 0) {
            string3 = string;
        }
        return StringsKt.replaceBeforeLast(string, c, string2, string3);
    }

    @NotNull
    public static final String replaceBeforeLast(@NotNull String $this$replaceBeforeLast, @NotNull String delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
        String string;
        Intrinsics.checkNotNullParameter($this$replaceBeforeLast, "$this$replaceBeforeLast");
        Intrinsics.checkNotNullParameter(delimiter, "delimiter");
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.lastIndexOf$default((CharSequence)$this$replaceBeforeLast, delimiter, 0, false, 6, null);
        if (index == -1) {
            string = missingDelimiterValue;
        } else {
            String string2 = $this$replaceBeforeLast;
            int n = 0;
            boolean bl = false;
            string = ((Object)StringsKt.replaceRange((CharSequence)string2, n, index, (CharSequence)replacement)).toString();
        }
        return string;
    }

    public static /* synthetic */ String replaceBeforeLast$default(String string, String string2, String string3, String string4, int n, Object object) {
        if ((n & 4) != 0) {
            string4 = string;
        }
        return StringsKt.replaceBeforeLast(string, string2, string3, string4);
    }

    @InlineOnly
    private static final String replace(CharSequence $this$replace, Regex regex, String replacement) {
        int $i$f$replace = 0;
        return regex.replace($this$replace, replacement);
    }

    @InlineOnly
    private static final String replace(CharSequence $this$replace, Regex regex, Function1<? super MatchResult, ? extends CharSequence> transform) {
        int $i$f$replace = 0;
        return regex.replace($this$replace, transform);
    }

    @InlineOnly
    private static final String replaceFirst(CharSequence $this$replaceFirst, Regex regex, String replacement) {
        int $i$f$replaceFirst = 0;
        return regex.replaceFirst($this$replaceFirst, replacement);
    }

    @InlineOnly
    private static final boolean matches(CharSequence $this$matches, Regex regex) {
        int $i$f$matches = 0;
        return regex.matches($this$matches);
    }

    /*
     * WARNING - void declaration
     */
    public static final boolean regionMatchesImpl(@NotNull CharSequence $this$regionMatchesImpl, int thisOffset, @NotNull CharSequence other, int otherOffset, int length, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter($this$regionMatchesImpl, "$this$regionMatchesImpl");
        Intrinsics.checkNotNullParameter(other, "other");
        if (otherOffset < 0 || thisOffset < 0 || thisOffset > $this$regionMatchesImpl.length() - length || otherOffset > other.length() - length) {
            return false;
        }
        int n = 0;
        int n2 = length;
        while (n < n2) {
            void index;
            if (!CharsKt.equals($this$regionMatchesImpl.charAt(thisOffset + index), other.charAt(otherOffset + index), ignoreCase)) {
                return false;
            }
            ++index;
        }
        return true;
    }

    public static final boolean startsWith(@NotNull CharSequence $this$startsWith, char c, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter($this$startsWith, "$this$startsWith");
        return $this$startsWith.length() > 0 && CharsKt.equals($this$startsWith.charAt(0), c, ignoreCase);
    }

    public static /* synthetic */ boolean startsWith$default(CharSequence charSequence, char c, boolean bl, int n, Object object) {
        if ((n & 2) != 0) {
            bl = false;
        }
        return StringsKt.startsWith(charSequence, c, bl);
    }

    public static final boolean endsWith(@NotNull CharSequence $this$endsWith, char c, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter($this$endsWith, "$this$endsWith");
        return $this$endsWith.length() > 0 && CharsKt.equals($this$endsWith.charAt(StringsKt.getLastIndex($this$endsWith)), c, ignoreCase);
    }

    public static /* synthetic */ boolean endsWith$default(CharSequence charSequence, char c, boolean bl, int n, Object object) {
        if ((n & 2) != 0) {
            bl = false;
        }
        return StringsKt.endsWith(charSequence, c, bl);
    }

    public static final boolean startsWith(@NotNull CharSequence $this$startsWith, @NotNull CharSequence prefix, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter($this$startsWith, "$this$startsWith");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        if (!ignoreCase && $this$startsWith instanceof String && prefix instanceof String) {
            return StringsKt.startsWith$default((String)$this$startsWith, (String)prefix, false, 2, null);
        }
        return StringsKt.regionMatchesImpl($this$startsWith, 0, prefix, 0, prefix.length(), ignoreCase);
    }

    public static /* synthetic */ boolean startsWith$default(CharSequence charSequence, CharSequence charSequence2, boolean bl, int n, Object object) {
        if ((n & 2) != 0) {
            bl = false;
        }
        return StringsKt.startsWith(charSequence, charSequence2, bl);
    }

    public static final boolean startsWith(@NotNull CharSequence $this$startsWith, @NotNull CharSequence prefix, int startIndex, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter($this$startsWith, "$this$startsWith");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        if (!ignoreCase && $this$startsWith instanceof String && prefix instanceof String) {
            return StringsKt.startsWith$default((String)$this$startsWith, (String)prefix, startIndex, false, 4, null);
        }
        return StringsKt.regionMatchesImpl($this$startsWith, startIndex, prefix, 0, prefix.length(), ignoreCase);
    }

    public static /* synthetic */ boolean startsWith$default(CharSequence charSequence, CharSequence charSequence2, int n, boolean bl, int n2, Object object) {
        if ((n2 & 4) != 0) {
            bl = false;
        }
        return StringsKt.startsWith(charSequence, charSequence2, n, bl);
    }

    public static final boolean endsWith(@NotNull CharSequence $this$endsWith, @NotNull CharSequence suffix, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter($this$endsWith, "$this$endsWith");
        Intrinsics.checkNotNullParameter(suffix, "suffix");
        if (!ignoreCase && $this$endsWith instanceof String && suffix instanceof String) {
            return StringsKt.endsWith$default((String)$this$endsWith, (String)suffix, false, 2, null);
        }
        return StringsKt.regionMatchesImpl($this$endsWith, $this$endsWith.length() - suffix.length(), suffix, 0, suffix.length(), ignoreCase);
    }

    public static /* synthetic */ boolean endsWith$default(CharSequence charSequence, CharSequence charSequence2, boolean bl, int n, Object object) {
        if ((n & 2) != 0) {
            bl = false;
        }
        return StringsKt.endsWith(charSequence, charSequence2, bl);
    }

    @NotNull
    public static final String commonPrefixWith(@NotNull CharSequence $this$commonPrefixWith, @NotNull CharSequence other, boolean ignoreCase) {
        int i;
        Intrinsics.checkNotNullParameter($this$commonPrefixWith, "$this$commonPrefixWith");
        Intrinsics.checkNotNullParameter(other, "other");
        int n = $this$commonPrefixWith.length();
        int n2 = other.length();
        boolean bl = false;
        int shortestLength = Math.min(n, n2);
        for (i = 0; i < shortestLength && CharsKt.equals($this$commonPrefixWith.charAt(i), other.charAt(i), ignoreCase); ++i) {
        }
        if (StringsKt.hasSurrogatePairAt($this$commonPrefixWith, i - 1) || StringsKt.hasSurrogatePairAt(other, i - 1)) {
            --i;
        }
        return ((Object)$this$commonPrefixWith.subSequence(0, i)).toString();
    }

    public static /* synthetic */ String commonPrefixWith$default(CharSequence charSequence, CharSequence charSequence2, boolean bl, int n, Object object) {
        if ((n & 2) != 0) {
            bl = false;
        }
        return StringsKt.commonPrefixWith(charSequence, charSequence2, bl);
    }

    @NotNull
    public static final String commonSuffixWith(@NotNull CharSequence $this$commonSuffixWith, @NotNull CharSequence other, boolean ignoreCase) {
        int i;
        Intrinsics.checkNotNullParameter($this$commonSuffixWith, "$this$commonSuffixWith");
        Intrinsics.checkNotNullParameter(other, "other");
        int thisLength = $this$commonSuffixWith.length();
        int otherLength = other.length();
        boolean bl = false;
        int shortestLength = Math.min(thisLength, otherLength);
        for (i = 0; i < shortestLength && CharsKt.equals($this$commonSuffixWith.charAt(thisLength - i - 1), other.charAt(otherLength - i - 1), ignoreCase); ++i) {
        }
        if (StringsKt.hasSurrogatePairAt($this$commonSuffixWith, thisLength - i - 1) || StringsKt.hasSurrogatePairAt(other, otherLength - i - 1)) {
            --i;
        }
        return ((Object)$this$commonSuffixWith.subSequence(thisLength - i, thisLength)).toString();
    }

    public static /* synthetic */ String commonSuffixWith$default(CharSequence charSequence, CharSequence charSequence2, boolean bl, int n, Object object) {
        if ((n & 2) != 0) {
            bl = false;
        }
        return StringsKt.commonSuffixWith(charSequence, charSequence2, bl);
    }

    /*
     * WARNING - void declaration
     */
    public static final int indexOfAny(@NotNull CharSequence $this$indexOfAny, @NotNull char[] chars, int startIndex, boolean ignoreCase) {
        int n;
        Intrinsics.checkNotNullParameter($this$indexOfAny, "$this$indexOfAny");
        Intrinsics.checkNotNullParameter(chars, "chars");
        if (!ignoreCase && chars.length == 1 && $this$indexOfAny instanceof String) {
            char c = ArraysKt.single(chars);
            String string = (String)$this$indexOfAny;
            boolean bl = false;
            return string.indexOf(c, startIndex);
        }
        int c = RangesKt.coerceAtLeast(startIndex, 0);
        if (c <= (n = StringsKt.getLastIndex($this$indexOfAny))) {
            while (true) {
                boolean bl;
                void index;
                block5: {
                    char charAtIndex = $this$indexOfAny.charAt((int)index);
                    char[] $this$any$iv = chars;
                    boolean $i$f$any = false;
                    char[] cArray = $this$any$iv;
                    int n2 = cArray.length;
                    for (int i = 0; i < n2; ++i) {
                        char element$iv;
                        char it = element$iv = cArray[i];
                        boolean bl2 = false;
                        if (!CharsKt.equals(it, charAtIndex, ignoreCase)) continue;
                        bl = true;
                        break block5;
                    }
                    bl = false;
                }
                if (bl) {
                    return (int)index;
                }
                if (index == n) break;
                ++index;
            }
        }
        return -1;
    }

    public static /* synthetic */ int indexOfAny$default(CharSequence charSequence, char[] cArray, int n, boolean bl, int n2, Object object) {
        if ((n2 & 2) != 0) {
            n = 0;
        }
        if ((n2 & 4) != 0) {
            bl = false;
        }
        return StringsKt.indexOfAny(charSequence, cArray, n, bl);
    }

    /*
     * WARNING - void declaration
     */
    public static final int lastIndexOfAny(@NotNull CharSequence $this$lastIndexOfAny, @NotNull char[] chars, int startIndex, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter($this$lastIndexOfAny, "$this$lastIndexOfAny");
        Intrinsics.checkNotNullParameter(chars, "chars");
        if (!ignoreCase && chars.length == 1 && $this$lastIndexOfAny instanceof String) {
            char c = ArraysKt.single(chars);
            String string = (String)$this$lastIndexOfAny;
            boolean bl = false;
            return string.lastIndexOf(c, startIndex);
        }
        int c = RangesKt.coerceAtMost(startIndex, StringsKt.getLastIndex($this$lastIndexOfAny));
        boolean bl = false;
        while (c >= 0) {
            boolean bl2;
            void index;
            block4: {
                char charAtIndex = $this$lastIndexOfAny.charAt((int)index);
                char[] $this$any$iv = chars;
                boolean $i$f$any = false;
                char[] cArray = $this$any$iv;
                int n = cArray.length;
                for (int i = 0; i < n; ++i) {
                    char element$iv;
                    char it = element$iv = cArray[i];
                    boolean bl3 = false;
                    if (!CharsKt.equals(it, charAtIndex, ignoreCase)) continue;
                    bl2 = true;
                    break block4;
                }
                bl2 = false;
            }
            if (bl2) {
                return (int)index;
            }
            --index;
        }
        return -1;
    }

    public static /* synthetic */ int lastIndexOfAny$default(CharSequence charSequence, char[] cArray, int n, boolean bl, int n2, Object object) {
        if ((n2 & 2) != 0) {
            n = StringsKt.getLastIndex(charSequence);
        }
        if ((n2 & 4) != 0) {
            bl = false;
        }
        return StringsKt.lastIndexOfAny(charSequence, cArray, n, bl);
    }

    private static final int indexOf$StringsKt__StringsKt(CharSequence $this$indexOf, CharSequence other, int startIndex, int endIndex, boolean ignoreCase, boolean last) {
        int index;
        IntProgression indices;
        IntProgression intProgression;
        int n;
        if (!last) {
            n = RangesKt.coerceAtLeast(startIndex, 0);
            intProgression = new IntRange(n, RangesKt.coerceAtMost(endIndex, $this$indexOf.length()));
        } else {
            intProgression = indices = RangesKt.downTo(RangesKt.coerceAtMost(startIndex, StringsKt.getLastIndex($this$indexOf)), RangesKt.coerceAtLeast(endIndex, 0));
        }
        if ($this$indexOf instanceof String && other instanceof String) {
            IntProgression intProgression2 = indices;
            n = intProgression2.getFirst();
            int n2 = intProgression2.getLast();
            int n3 = intProgression2.getStep();
            int n4 = n;
            int n5 = n2;
            if (n3 >= 0 ? n4 <= n5 : n4 >= n5) {
                while (true) {
                    if (StringsKt.regionMatches((String)other, 0, (String)$this$indexOf, index, other.length(), ignoreCase)) {
                        return index;
                    }
                    if (index != n2) {
                        index += n3;
                        continue;
                    }
                    break;
                }
            }
        } else {
            IntProgression intProgression3 = indices;
            index = intProgression3.getFirst();
            int n6 = intProgression3.getLast();
            int n7 = intProgression3.getStep();
            int n8 = index;
            int n9 = n6;
            if (n7 >= 0 ? n8 <= n9 : n8 >= n9) {
                while (true) {
                    if (StringsKt.regionMatchesImpl(other, 0, $this$indexOf, index, other.length(), ignoreCase)) {
                        return index;
                    }
                    if (index == n6) break;
                    index += n7;
                }
            }
        }
        return -1;
    }

    static /* synthetic */ int indexOf$StringsKt__StringsKt$default(CharSequence charSequence, CharSequence charSequence2, int n, int n2, boolean bl, boolean bl2, int n3, Object object) {
        if ((n3 & 0x10) != 0) {
            bl2 = false;
        }
        return StringsKt__StringsKt.indexOf$StringsKt__StringsKt(charSequence, charSequence2, n, n2, bl, bl2);
    }

    private static final Pair<Integer, String> findAnyOf$StringsKt__StringsKt(CharSequence $this$findAnyOf, Collection<String> strings, int startIndex, boolean ignoreCase, boolean last) {
        IntProgression indices;
        IntProgression intProgression;
        int index2;
        if (!ignoreCase && strings.size() == 1) {
            String string = (String)CollectionsKt.single((Iterable)strings);
            int index2 = !last ? StringsKt.indexOf$default($this$findAnyOf, string, startIndex, false, 4, null) : StringsKt.lastIndexOf$default($this$findAnyOf, string, startIndex, false, 4, null);
            return index2 < 0 ? null : TuplesKt.to(index2, string);
        }
        if (!last) {
            index2 = RangesKt.coerceAtLeast(startIndex, 0);
            intProgression = new IntRange(index2, $this$findAnyOf.length());
        } else {
            intProgression = indices = RangesKt.downTo(RangesKt.coerceAtMost(startIndex, StringsKt.getLastIndex($this$findAnyOf)), 0);
        }
        if ($this$findAnyOf instanceof String) {
            IntProgression intProgression2 = indices;
            index2 = intProgression2.getFirst();
            int n = intProgression2.getLast();
            int n2 = intProgression2.getStep();
            int n3 = index2;
            int n4 = n;
            if (n2 >= 0 ? n3 <= n4 : n3 >= n4) {
                while (true) {
                    Object v4;
                    block14: {
                        Iterable $this$firstOrNull$iv = strings;
                        boolean $i$f$firstOrNull = false;
                        for (Object element$iv : $this$firstOrNull$iv) {
                            String it = (String)element$iv;
                            boolean bl = false;
                            if (!StringsKt.regionMatches(it, 0, (String)$this$findAnyOf, index2, it.length(), ignoreCase)) continue;
                            v4 = element$iv;
                            break block14;
                        }
                        v4 = null;
                    }
                    String matchingString = v4;
                    if (matchingString != null) {
                        return TuplesKt.to(index2, matchingString);
                    }
                    if (index2 != n) {
                        index2 += n2;
                        continue;
                    }
                    break;
                }
            }
        } else {
            IntProgression intProgression3 = indices;
            index2 = intProgression3.getFirst();
            int n = intProgression3.getLast();
            int n5 = intProgression3.getStep();
            int n6 = index2;
            int n7 = n;
            if (n5 >= 0 ? n6 <= n7 : n6 >= n7) {
                while (true) {
                    Object v8;
                    block16: {
                        Iterable $this$firstOrNull$iv = strings;
                        boolean $i$f$firstOrNull = false;
                        for (Object element$iv : $this$firstOrNull$iv) {
                            String it = (String)element$iv;
                            boolean bl = false;
                            if (!StringsKt.regionMatchesImpl(it, 0, $this$findAnyOf, index2, it.length(), ignoreCase)) continue;
                            v8 = element$iv;
                            break block16;
                        }
                        v8 = null;
                    }
                    String matchingString = v8;
                    if (matchingString != null) {
                        return TuplesKt.to(index2, matchingString);
                    }
                    if (index2 == n) break;
                    index2 += n5;
                }
            }
        }
        return null;
    }

    @Nullable
    public static final Pair<Integer, String> findAnyOf(@NotNull CharSequence $this$findAnyOf, @NotNull Collection<String> strings, int startIndex, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter($this$findAnyOf, "$this$findAnyOf");
        Intrinsics.checkNotNullParameter(strings, "strings");
        return StringsKt__StringsKt.findAnyOf$StringsKt__StringsKt($this$findAnyOf, strings, startIndex, ignoreCase, false);
    }

    public static /* synthetic */ Pair findAnyOf$default(CharSequence charSequence, Collection collection, int n, boolean bl, int n2, Object object) {
        if ((n2 & 2) != 0) {
            n = 0;
        }
        if ((n2 & 4) != 0) {
            bl = false;
        }
        return StringsKt.findAnyOf(charSequence, collection, n, bl);
    }

    @Nullable
    public static final Pair<Integer, String> findLastAnyOf(@NotNull CharSequence $this$findLastAnyOf, @NotNull Collection<String> strings, int startIndex, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter($this$findLastAnyOf, "$this$findLastAnyOf");
        Intrinsics.checkNotNullParameter(strings, "strings");
        return StringsKt__StringsKt.findAnyOf$StringsKt__StringsKt($this$findLastAnyOf, strings, startIndex, ignoreCase, true);
    }

    public static /* synthetic */ Pair findLastAnyOf$default(CharSequence charSequence, Collection collection, int n, boolean bl, int n2, Object object) {
        if ((n2 & 2) != 0) {
            n = StringsKt.getLastIndex(charSequence);
        }
        if ((n2 & 4) != 0) {
            bl = false;
        }
        return StringsKt.findLastAnyOf(charSequence, collection, n, bl);
    }

    public static final int indexOfAny(@NotNull CharSequence $this$indexOfAny, @NotNull Collection<String> strings, int startIndex, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter($this$indexOfAny, "$this$indexOfAny");
        Intrinsics.checkNotNullParameter(strings, "strings");
        Serializable serializable = StringsKt__StringsKt.findAnyOf$StringsKt__StringsKt($this$indexOfAny, strings, startIndex, ignoreCase, false);
        return serializable != null && (serializable = ((Pair)serializable).getFirst()) != null ? (Integer)serializable : -1;
    }

    public static /* synthetic */ int indexOfAny$default(CharSequence charSequence, Collection collection, int n, boolean bl, int n2, Object object) {
        if ((n2 & 2) != 0) {
            n = 0;
        }
        if ((n2 & 4) != 0) {
            bl = false;
        }
        return StringsKt.indexOfAny(charSequence, collection, n, bl);
    }

    public static final int lastIndexOfAny(@NotNull CharSequence $this$lastIndexOfAny, @NotNull Collection<String> strings, int startIndex, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter($this$lastIndexOfAny, "$this$lastIndexOfAny");
        Intrinsics.checkNotNullParameter(strings, "strings");
        Serializable serializable = StringsKt__StringsKt.findAnyOf$StringsKt__StringsKt($this$lastIndexOfAny, strings, startIndex, ignoreCase, true);
        return serializable != null && (serializable = ((Pair)serializable).getFirst()) != null ? (Integer)serializable : -1;
    }

    public static /* synthetic */ int lastIndexOfAny$default(CharSequence charSequence, Collection collection, int n, boolean bl, int n2, Object object) {
        if ((n2 & 2) != 0) {
            n = StringsKt.getLastIndex(charSequence);
        }
        if ((n2 & 4) != 0) {
            bl = false;
        }
        return StringsKt.lastIndexOfAny(charSequence, collection, n, bl);
    }

    public static final int indexOf(@NotNull CharSequence $this$indexOf, char c, int startIndex, boolean ignoreCase) {
        int n;
        Intrinsics.checkNotNullParameter($this$indexOf, "$this$indexOf");
        if (ignoreCase || !($this$indexOf instanceof String)) {
            n = StringsKt.indexOfAny($this$indexOf, new char[]{c}, startIndex, ignoreCase);
        } else {
            String string = (String)$this$indexOf;
            boolean bl = false;
            n = string.indexOf(c, startIndex);
        }
        return n;
    }

    public static /* synthetic */ int indexOf$default(CharSequence charSequence, char c, int n, boolean bl, int n2, Object object) {
        if ((n2 & 2) != 0) {
            n = 0;
        }
        if ((n2 & 4) != 0) {
            bl = false;
        }
        return StringsKt.indexOf(charSequence, c, n, bl);
    }

    public static final int indexOf(@NotNull CharSequence $this$indexOf, @NotNull String string, int startIndex, boolean ignoreCase) {
        int n;
        Intrinsics.checkNotNullParameter($this$indexOf, "$this$indexOf");
        Intrinsics.checkNotNullParameter(string, "string");
        if (ignoreCase || !($this$indexOf instanceof String)) {
            n = StringsKt__StringsKt.indexOf$StringsKt__StringsKt$default($this$indexOf, string, startIndex, $this$indexOf.length(), ignoreCase, false, 16, null);
        } else {
            String string2 = (String)$this$indexOf;
            boolean bl = false;
            n = string2.indexOf(string, startIndex);
        }
        return n;
    }

    public static /* synthetic */ int indexOf$default(CharSequence charSequence, String string, int n, boolean bl, int n2, Object object) {
        if ((n2 & 2) != 0) {
            n = 0;
        }
        if ((n2 & 4) != 0) {
            bl = false;
        }
        return StringsKt.indexOf(charSequence, string, n, bl);
    }

    public static final int lastIndexOf(@NotNull CharSequence $this$lastIndexOf, char c, int startIndex, boolean ignoreCase) {
        int n;
        Intrinsics.checkNotNullParameter($this$lastIndexOf, "$this$lastIndexOf");
        if (ignoreCase || !($this$lastIndexOf instanceof String)) {
            n = StringsKt.lastIndexOfAny($this$lastIndexOf, new char[]{c}, startIndex, ignoreCase);
        } else {
            String string = (String)$this$lastIndexOf;
            boolean bl = false;
            n = string.lastIndexOf(c, startIndex);
        }
        return n;
    }

    public static /* synthetic */ int lastIndexOf$default(CharSequence charSequence, char c, int n, boolean bl, int n2, Object object) {
        if ((n2 & 2) != 0) {
            n = StringsKt.getLastIndex(charSequence);
        }
        if ((n2 & 4) != 0) {
            bl = false;
        }
        return StringsKt.lastIndexOf(charSequence, c, n, bl);
    }

    public static final int lastIndexOf(@NotNull CharSequence $this$lastIndexOf, @NotNull String string, int startIndex, boolean ignoreCase) {
        int n;
        Intrinsics.checkNotNullParameter($this$lastIndexOf, "$this$lastIndexOf");
        Intrinsics.checkNotNullParameter(string, "string");
        if (ignoreCase || !($this$lastIndexOf instanceof String)) {
            n = StringsKt__StringsKt.indexOf$StringsKt__StringsKt($this$lastIndexOf, string, startIndex, 0, ignoreCase, true);
        } else {
            String string2 = (String)$this$lastIndexOf;
            boolean bl = false;
            n = string2.lastIndexOf(string, startIndex);
        }
        return n;
    }

    public static /* synthetic */ int lastIndexOf$default(CharSequence charSequence, String string, int n, boolean bl, int n2, Object object) {
        if ((n2 & 2) != 0) {
            n = StringsKt.getLastIndex(charSequence);
        }
        if ((n2 & 4) != 0) {
            bl = false;
        }
        return StringsKt.lastIndexOf(charSequence, string, n, bl);
    }

    public static final boolean contains(@NotNull CharSequence $this$contains, @NotNull CharSequence other, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter($this$contains, "$this$contains");
        Intrinsics.checkNotNullParameter(other, "other");
        return other instanceof String ? StringsKt.indexOf$default($this$contains, (String)other, 0, ignoreCase, 2, null) >= 0 : StringsKt__StringsKt.indexOf$StringsKt__StringsKt$default($this$contains, other, 0, $this$contains.length(), ignoreCase, false, 16, null) >= 0;
    }

    public static /* synthetic */ boolean contains$default(CharSequence charSequence, CharSequence charSequence2, boolean bl, int n, Object object) {
        if ((n & 2) != 0) {
            bl = false;
        }
        return StringsKt.contains(charSequence, charSequence2, bl);
    }

    public static final boolean contains(@NotNull CharSequence $this$contains, char c, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter($this$contains, "$this$contains");
        return StringsKt.indexOf$default($this$contains, c, 0, ignoreCase, 2, null) >= 0;
    }

    public static /* synthetic */ boolean contains$default(CharSequence charSequence, char c, boolean bl, int n, Object object) {
        if ((n & 2) != 0) {
            bl = false;
        }
        return StringsKt.contains(charSequence, c, bl);
    }

    @InlineOnly
    private static final boolean contains(CharSequence $this$contains, Regex regex) {
        int $i$f$contains = 0;
        Intrinsics.checkNotNullParameter($this$contains, "$this$contains");
        return regex.containsMatchIn($this$contains);
    }

    private static final Sequence<IntRange> rangesDelimitedBy$StringsKt__StringsKt(CharSequence $this$rangesDelimitedBy, char[] delimiters, int startIndex, boolean ignoreCase, int limit) {
        boolean bl = limit >= 0;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "Limit must be non-negative, but was " + limit + '.';
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        return new DelimitedRangesSequence($this$rangesDelimitedBy, startIndex, limit, (Function2<? super CharSequence, ? super Integer, Pair<Integer, Integer>>)new Function2<CharSequence, Integer, Pair<? extends Integer, ? extends Integer>>(delimiters, ignoreCase){
            final /* synthetic */ char[] $delimiters;
            final /* synthetic */ boolean $ignoreCase;

            @Nullable
            public final Pair<Integer, Integer> invoke(@NotNull CharSequence $receiver, int currentIndex) {
                Intrinsics.checkNotNullParameter($receiver, "$receiver");
                int n = StringsKt.indexOfAny($receiver, this.$delimiters, currentIndex, this.$ignoreCase);
                boolean bl = false;
                boolean bl2 = false;
                int it = n;
                boolean bl3 = false;
                return it < 0 ? null : TuplesKt.to(it, 1);
            }
            {
                this.$delimiters = cArray;
                this.$ignoreCase = bl;
                super(2);
            }
        });
    }

    static /* synthetic */ Sequence rangesDelimitedBy$StringsKt__StringsKt$default(CharSequence charSequence, char[] cArray, int n, boolean bl, int n2, int n3, Object object) {
        if ((n3 & 2) != 0) {
            n = 0;
        }
        if ((n3 & 4) != 0) {
            bl = false;
        }
        if ((n3 & 8) != 0) {
            n2 = 0;
        }
        return StringsKt__StringsKt.rangesDelimitedBy$StringsKt__StringsKt(charSequence, cArray, n, bl, n2);
    }

    private static final Sequence<IntRange> rangesDelimitedBy$StringsKt__StringsKt(CharSequence $this$rangesDelimitedBy, String[] delimiters, int startIndex, boolean ignoreCase, int limit) {
        boolean bl = limit >= 0;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "Limit must be non-negative, but was " + limit + '.';
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        List<String> delimitersList = ArraysKt.asList(delimiters);
        return new DelimitedRangesSequence($this$rangesDelimitedBy, startIndex, limit, (Function2<? super CharSequence, ? super Integer, Pair<Integer, Integer>>)new Function2<CharSequence, Integer, Pair<? extends Integer, ? extends Integer>>(delimitersList, ignoreCase){
            final /* synthetic */ List $delimitersList;
            final /* synthetic */ boolean $ignoreCase;

            @Nullable
            public final Pair<Integer, Integer> invoke(@NotNull CharSequence $receiver, int currentIndex) {
                Pair<A, Integer> pair;
                Intrinsics.checkNotNullParameter($receiver, "$receiver");
                Pair pair2 = StringsKt__StringsKt.access$findAnyOf($receiver, this.$delimitersList, currentIndex, this.$ignoreCase, false);
                if (pair2 != null) {
                    Pair pair3 = pair2;
                    boolean bl = false;
                    boolean bl2 = false;
                    Pair it = pair3;
                    boolean bl3 = false;
                    pair = TuplesKt.to(it.getFirst(), ((String)it.getSecond()).length());
                } else {
                    pair = null;
                }
                return pair;
            }
            {
                this.$delimitersList = list;
                this.$ignoreCase = bl;
                super(2);
            }
        });
    }

    static /* synthetic */ Sequence rangesDelimitedBy$StringsKt__StringsKt$default(CharSequence charSequence, String[] stringArray, int n, boolean bl, int n2, int n3, Object object) {
        if ((n3 & 2) != 0) {
            n = 0;
        }
        if ((n3 & 4) != 0) {
            bl = false;
        }
        if ((n3 & 8) != 0) {
            n2 = 0;
        }
        return StringsKt__StringsKt.rangesDelimitedBy$StringsKt__StringsKt(charSequence, stringArray, n, bl, n2);
    }

    @NotNull
    public static final Sequence<String> splitToSequence(@NotNull CharSequence $this$splitToSequence, @NotNull String[] delimiters, boolean ignoreCase, int limit) {
        Intrinsics.checkNotNullParameter($this$splitToSequence, "$this$splitToSequence");
        Intrinsics.checkNotNullParameter(delimiters, "delimiters");
        return SequencesKt.map(StringsKt__StringsKt.rangesDelimitedBy$StringsKt__StringsKt$default($this$splitToSequence, delimiters, 0, ignoreCase, limit, 2, null), (Function1)new Function1<IntRange, String>($this$splitToSequence){
            final /* synthetic */ CharSequence $this_splitToSequence;

            @NotNull
            public final String invoke(@NotNull IntRange it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return StringsKt.substring(this.$this_splitToSequence, it);
            }
            {
                this.$this_splitToSequence = charSequence;
                super(1);
            }
        });
    }

    public static /* synthetic */ Sequence splitToSequence$default(CharSequence charSequence, String[] stringArray, boolean bl, int n, int n2, Object object) {
        if ((n2 & 2) != 0) {
            bl = false;
        }
        if ((n2 & 4) != 0) {
            n = 0;
        }
        return StringsKt.splitToSequence(charSequence, stringArray, bl, n);
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public static final List<String> split(@NotNull CharSequence $this$split, @NotNull String[] delimiters, boolean ignoreCase, int limit) {
        void $this$mapTo$iv$iv;
        Intrinsics.checkNotNullParameter($this$split, "$this$split");
        Intrinsics.checkNotNullParameter(delimiters, "delimiters");
        if (delimiters.length == 1) {
            String delimiter = delimiters[0];
            CharSequence charSequence = delimiter;
            boolean bl = false;
            if (!(charSequence.length() == 0)) {
                return StringsKt__StringsKt.split$StringsKt__StringsKt($this$split, delimiter, ignoreCase, limit);
            }
        }
        Iterable $this$map$iv = SequencesKt.asIterable(StringsKt__StringsKt.rangesDelimitedBy$StringsKt__StringsKt$default($this$split, delimiters, 0, ignoreCase, limit, 2, null));
        boolean $i$f$map = false;
        Iterable iterable = $this$map$iv;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        boolean $i$f$mapTo = false;
        for (Object item$iv$iv : $this$mapTo$iv$iv) {
            void it;
            IntRange intRange = (IntRange)item$iv$iv;
            Collection collection = destination$iv$iv;
            boolean bl = false;
            String string = StringsKt.substring($this$split, (IntRange)it);
            collection.add(string);
        }
        return (List)destination$iv$iv;
    }

    public static /* synthetic */ List split$default(CharSequence charSequence, String[] stringArray, boolean bl, int n, int n2, Object object) {
        if ((n2 & 2) != 0) {
            bl = false;
        }
        if ((n2 & 4) != 0) {
            n = 0;
        }
        return StringsKt.split(charSequence, stringArray, bl, n);
    }

    @NotNull
    public static final Sequence<String> splitToSequence(@NotNull CharSequence $this$splitToSequence, @NotNull char[] delimiters, boolean ignoreCase, int limit) {
        Intrinsics.checkNotNullParameter($this$splitToSequence, "$this$splitToSequence");
        Intrinsics.checkNotNullParameter(delimiters, "delimiters");
        return SequencesKt.map(StringsKt__StringsKt.rangesDelimitedBy$StringsKt__StringsKt$default($this$splitToSequence, delimiters, 0, ignoreCase, limit, 2, null), (Function1)new Function1<IntRange, String>($this$splitToSequence){
            final /* synthetic */ CharSequence $this_splitToSequence;

            @NotNull
            public final String invoke(@NotNull IntRange it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return StringsKt.substring(this.$this_splitToSequence, it);
            }
            {
                this.$this_splitToSequence = charSequence;
                super(1);
            }
        });
    }

    public static /* synthetic */ Sequence splitToSequence$default(CharSequence charSequence, char[] cArray, boolean bl, int n, int n2, Object object) {
        if ((n2 & 2) != 0) {
            bl = false;
        }
        if ((n2 & 4) != 0) {
            n = 0;
        }
        return StringsKt.splitToSequence(charSequence, cArray, bl, n);
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public static final List<String> split(@NotNull CharSequence $this$split, @NotNull char[] delimiters, boolean ignoreCase, int limit) {
        void $this$mapTo$iv$iv;
        Intrinsics.checkNotNullParameter($this$split, "$this$split");
        Intrinsics.checkNotNullParameter(delimiters, "delimiters");
        if (delimiters.length == 1) {
            return StringsKt__StringsKt.split$StringsKt__StringsKt($this$split, String.valueOf(delimiters[0]), ignoreCase, limit);
        }
        Iterable $this$map$iv = SequencesKt.asIterable(StringsKt__StringsKt.rangesDelimitedBy$StringsKt__StringsKt$default($this$split, delimiters, 0, ignoreCase, limit, 2, null));
        boolean $i$f$map = false;
        Iterable iterable = $this$map$iv;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        boolean $i$f$mapTo = false;
        for (Object item$iv$iv : $this$mapTo$iv$iv) {
            void it;
            IntRange intRange = (IntRange)item$iv$iv;
            Collection collection = destination$iv$iv;
            boolean bl = false;
            String string = StringsKt.substring($this$split, (IntRange)it);
            collection.add(string);
        }
        return (List)destination$iv$iv;
    }

    public static /* synthetic */ List split$default(CharSequence charSequence, char[] cArray, boolean bl, int n, int n2, Object object) {
        if ((n2 & 2) != 0) {
            bl = false;
        }
        if ((n2 & 4) != 0) {
            n = 0;
        }
        return StringsKt.split(charSequence, cArray, bl, n);
    }

    private static final List<String> split$StringsKt__StringsKt(CharSequence $this$split, String delimiter, boolean ignoreCase, int limit) {
        int n;
        CharSequence charSequence;
        boolean bl = limit >= 0;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "Limit must be non-negative, but was " + limit + '.';
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        int currentOffset = 0;
        int nextIndex = StringsKt.indexOf($this$split, delimiter, currentOffset, ignoreCase);
        if (nextIndex == -1 || limit == 1) {
            return CollectionsKt.listOf(((Object)$this$split).toString());
        }
        boolean isLimited = limit > 0;
        ArrayList<String> result = new ArrayList<String>(isLimited ? RangesKt.coerceAtMost(limit, 10) : 10);
        do {
            charSequence = $this$split;
            n = 0;
            result.add(((Object)charSequence.subSequence(currentOffset, nextIndex)).toString());
            currentOffset = nextIndex + delimiter.length();
        } while ((!isLimited || result.size() != limit - 1) && (nextIndex = StringsKt.indexOf($this$split, delimiter, currentOffset, ignoreCase)) != -1);
        charSequence = $this$split;
        n = $this$split.length();
        boolean bl5 = false;
        result.add(((Object)charSequence.subSequence(currentOffset, n)).toString());
        return result;
    }

    @InlineOnly
    private static final List<String> split(CharSequence $this$split, Regex regex, int limit) {
        int $i$f$split = 0;
        return regex.split($this$split, limit);
    }

    static /* synthetic */ List split$default(CharSequence $this$split, Regex regex, int limit, int n, Object object) {
        if ((n & 2) != 0) {
            limit = 0;
        }
        boolean $i$f$split = false;
        return regex.split($this$split, limit);
    }

    @NotNull
    public static final Sequence<String> lineSequence(@NotNull CharSequence $this$lineSequence) {
        Intrinsics.checkNotNullParameter($this$lineSequence, "$this$lineSequence");
        return StringsKt.splitToSequence$default($this$lineSequence, new String[]{"\r\n", "\n", "\r"}, false, 0, 6, null);
    }

    @NotNull
    public static final List<String> lines(@NotNull CharSequence $this$lines) {
        Intrinsics.checkNotNullParameter($this$lines, "$this$lines");
        return SequencesKt.toList(StringsKt.lineSequence($this$lines));
    }

    public static final /* synthetic */ Pair access$findAnyOf(CharSequence $this$access_u24findAnyOf, Collection strings, int startIndex, boolean ignoreCase, boolean last) {
        return StringsKt__StringsKt.findAnyOf$StringsKt__StringsKt($this$access_u24findAnyOf, strings, startIndex, ignoreCase, last);
    }
}

