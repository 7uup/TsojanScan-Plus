/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.collections;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.BuilderInference;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.EmptyMap;
import kotlin.collections.MapsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=5, xi=1, d1={"\u0000~\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010%\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010&\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010(\n\u0002\u0010)\n\u0002\u0010'\n\u0002\b\n\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0017\u001a`\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u00020\u00052%\b\u0001\u0010\u0006\u001a\u001f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u00020\t0\u0007\u00a2\u0006\u0002\b\nH\u0087\b\u00f8\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u001aX\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032%\b\u0001\u0010\u0006\u001a\u001f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u00020\t0\u0007\u00a2\u0006\u0002\b\nH\u0087\b\u00f8\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u001a\u001e\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\u001a1\u0010\f\u001a\u001e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\rj\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003`\u000e\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003H\u0087\b\u001a_\u0010\f\u001a\u001e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\rj\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003`\u000e\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032*\u0010\u000f\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110\u0010\"\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011\u00a2\u0006\u0002\u0010\u0012\u001a1\u0010\u0013\u001a\u001e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0014j\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003`\u0015\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003H\u0087\b\u001a_\u0010\u0013\u001a\u001e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0014j\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003`\u0015\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032*\u0010\u000f\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110\u0010\"\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011\u00a2\u0006\u0002\u0010\u0016\u001a!\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003H\u0087\b\u001aO\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032*\u0010\u000f\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110\u0010\"\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011\u00a2\u0006\u0002\u0010\u0018\u001a!\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003H\u0087\b\u001aO\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032*\u0010\u000f\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110\u0010\"\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011\u00a2\u0006\u0002\u0010\u0018\u001a*\u0010\u001a\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u001bH\u0087\n\u00a2\u0006\u0002\u0010\u001c\u001a*\u0010\u001d\u001a\u0002H\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u001bH\u0087\n\u00a2\u0006\u0002\u0010\u001c\u001a9\u0010\u001e\u001a\u00020\u001f\"\t\b\u0000\u0010\u0002\u00a2\u0006\u0002\b \"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010!\u001a\u0002H\u0002H\u0087\n\u00a2\u0006\u0002\u0010\"\u001a1\u0010#\u001a\u00020\u001f\"\t\b\u0000\u0010\u0002\u00a2\u0006\u0002\b *\u000e\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0002\b\u00030\u00012\u0006\u0010!\u001a\u0002H\u0002H\u0087\b\u00a2\u0006\u0002\u0010\"\u001a7\u0010$\u001a\u00020\u001f\"\u0004\b\u0000\u0010\u0002\"\t\b\u0001\u0010\u0003\u00a2\u0006\u0002\b *\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010%\u001a\u0002H\u0003H\u0087\b\u00a2\u0006\u0002\u0010\"\u001aV\u0010&\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u001b\u0012\u0004\u0012\u00020\u001f0\u0007H\u0086\b\u00f8\u0001\u0000\u001aJ\u0010(\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u001f0\u0007H\u0086\b\u00f8\u0001\u0000\u001aV\u0010)\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u001b\u0012\u0004\u0012\u00020\u001f0\u0007H\u0086\b\u00f8\u0001\u0000\u001aq\u0010*\u001a\u0002H+\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0018\b\u0002\u0010+*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\b*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010,\u001a\u0002H+2\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u001b\u0012\u0004\u0012\u00020\u001f0\u0007H\u0086\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010-\u001aq\u0010.\u001a\u0002H+\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0018\b\u0002\u0010+*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\b*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010,\u001a\u0002H+2\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u001b\u0012\u0004\u0012\u00020\u001f0\u0007H\u0086\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010-\u001aJ\u0010/\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\u001f0\u0007H\u0086\b\u00f8\u0001\u0000\u001a;\u00100\u001a\u0004\u0018\u0001H\u0003\"\t\b\u0000\u0010\u0002\u00a2\u0006\u0002\b \"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010!\u001a\u0002H\u0002H\u0087\n\u00a2\u0006\u0002\u00101\u001aC\u00102\u001a\u0002H\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010!\u001a\u0002H\u00022\f\u00103\u001a\b\u0012\u0004\u0012\u0002H\u000304H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u00105\u001aC\u00106\u001a\u0002H\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010!\u001a\u0002H\u00022\f\u00103\u001a\b\u0012\u0004\u0012\u0002H\u000304H\u0080\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u00105\u001aC\u00107\u001a\u0002H\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b2\u0006\u0010!\u001a\u0002H\u00022\f\u00103\u001a\b\u0012\u0004\u0012\u0002H\u000304H\u0086\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u00105\u001a1\u00108\u001a\u0002H\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010!\u001a\u0002H\u0002H\u0007\u00a2\u0006\u0002\u00101\u001a?\u00109\u001a\u0002H:\"\u0014\b\u0000\u0010+*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0001*\u0002H:\"\u0004\b\u0001\u0010:*\u0002H+2\f\u00103\u001a\b\u0012\u0004\u0012\u0002H:04H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010;\u001a'\u0010<\u001a\u00020\u001f\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001H\u0087\b\u001a:\u0010=\u001a\u00020\u001f\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0012\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0001H\u0087\b\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000\u001a9\u0010>\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u001b0?\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001H\u0087\n\u001a<\u0010>\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030A0@\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\bH\u0087\n\u00a2\u0006\u0002\bB\u001a\\\u0010C\u001a\u000e\u0012\u0004\u0012\u0002H:\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010:*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u001e\u0010D\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u001b\u0012\u0004\u0012\u0002H:0\u0007H\u0086\b\u00f8\u0001\u0000\u001aw\u0010E\u001a\u0002H+\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010:\"\u0018\b\u0003\u0010+*\u0012\u0012\u0006\b\u0000\u0012\u0002H:\u0012\u0006\b\u0000\u0012\u0002H\u00030\b*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010,\u001a\u0002H+2\u001e\u0010D\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u001b\u0012\u0004\u0012\u0002H:0\u0007H\u0086\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010-\u001a\\\u0010F\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H:0\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010:*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u001e\u0010D\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u001b\u0012\u0004\u0012\u0002H:0\u0007H\u0086\b\u00f8\u0001\u0000\u001aw\u0010G\u001a\u0002H+\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010:\"\u0018\b\u0003\u0010+*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H:0\b*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010,\u001a\u0002H+2\u001e\u0010D\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u001b\u0012\u0004\u0012\u0002H:0\u0007H\u0086\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010-\u001a@\u0010H\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010!\u001a\u0002H\u0002H\u0087\u0002\u00a2\u0006\u0002\u0010I\u001aH\u0010H\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u000e\u0010J\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0010H\u0087\u0002\u00a2\u0006\u0002\u0010K\u001aA\u0010H\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\f\u0010J\u001a\b\u0012\u0004\u0012\u0002H\u00020LH\u0087\u0002\u001aA\u0010H\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\f\u0010J\u001a\b\u0012\u0004\u0012\u0002H\u00020MH\u0087\u0002\u001a2\u0010N\u001a\u00020\t\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b2\u0006\u0010!\u001a\u0002H\u0002H\u0087\n\u00a2\u0006\u0002\u0010O\u001a:\u0010N\u001a\u00020\t\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b2\u000e\u0010J\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0010H\u0087\n\u00a2\u0006\u0002\u0010P\u001a3\u0010N\u001a\u00020\t\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b2\f\u0010J\u001a\b\u0012\u0004\u0012\u0002H\u00020LH\u0087\n\u001a3\u0010N\u001a\u00020\t\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b2\f\u0010J\u001a\b\u0012\u0004\u0012\u0002H\u00020MH\u0087\n\u001a0\u0010Q\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001H\u0000\u001a3\u0010R\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0001H\u0087\b\u001aT\u0010S\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u001a\u0010\u000f\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110\u0010H\u0086\u0002\u00a2\u0006\u0002\u0010T\u001aG\u0010S\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0012\u0010U\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011H\u0086\u0002\u001aM\u0010S\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0018\u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110LH\u0086\u0002\u001aI\u0010S\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0014\u0010V\u001a\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001H\u0086\u0002\u001aM\u0010S\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0018\u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110MH\u0086\u0002\u001aJ\u0010W\u001a\u00020\t\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\b2\u001a\u0010\u000f\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110\u0010H\u0087\n\u00a2\u0006\u0002\u0010X\u001a=\u0010W\u001a\u00020\t\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\b2\u0012\u0010U\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011H\u0087\n\u001aC\u0010W\u001a\u00020\t\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\b2\u0018\u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110LH\u0087\n\u001a=\u0010W\u001a\u00020\t\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\b2\u0012\u0010V\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001H\u0087\n\u001aC\u0010W\u001a\u00020\t\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\b2\u0018\u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110MH\u0087\n\u001aG\u0010Y\u001a\u00020\t\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\b2\u001a\u0010\u000f\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110\u0010\u00a2\u0006\u0002\u0010X\u001a@\u0010Y\u001a\u00020\t\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\b2\u0018\u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110L\u001a@\u0010Y\u001a\u00020\t\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\b2\u0018\u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110M\u001a;\u0010Z\u001a\u0004\u0018\u0001H\u0003\"\t\b\u0000\u0010\u0002\u00a2\u0006\u0002\b \"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b2\u0006\u0010!\u001a\u0002H\u0002H\u0087\b\u00a2\u0006\u0002\u00101\u001a:\u0010[\u001a\u00020\t\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b2\u0006\u0010!\u001a\u0002H\u00022\u0006\u0010%\u001a\u0002H\u0003H\u0087\n\u00a2\u0006\u0002\u0010\\\u001a;\u0010]\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110\u0010\u00a2\u0006\u0002\u0010\u0018\u001aQ\u0010]\u001a\u0002H+\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0018\b\u0002\u0010+*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\b*\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110\u00102\u0006\u0010,\u001a\u0002H+\u00a2\u0006\u0002\u0010^\u001a4\u0010]\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110L\u001aO\u0010]\u001a\u0002H+\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0018\b\u0002\u0010+*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\b*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110L2\u0006\u0010,\u001a\u0002H+\u00a2\u0006\u0002\u0010_\u001a2\u0010]\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001H\u0007\u001aM\u0010]\u001a\u0002H+\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0018\b\u0002\u0010+*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\b*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010,\u001a\u0002H+H\u0007\u00a2\u0006\u0002\u0010`\u001a4\u0010]\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110M\u001aO\u0010]\u001a\u0002H+\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0018\b\u0002\u0010+*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\b*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110M2\u0006\u0010,\u001a\u0002H+\u00a2\u0006\u0002\u0010a\u001a2\u0010b\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001H\u0007\u001a1\u0010c\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u001bH\u0087\b\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00a8\u0006d"}, d2={"buildMap", "", "K", "V", "capacity", "", "builderAction", "Lkotlin/Function1;", "", "", "Lkotlin/ExtensionFunctionType;", "emptyMap", "hashMapOf", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "pairs", "", "Lkotlin/Pair;", "([Lkotlin/Pair;)Ljava/util/HashMap;", "linkedMapOf", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "([Lkotlin/Pair;)Ljava/util/LinkedHashMap;", "mapOf", "([Lkotlin/Pair;)Ljava/util/Map;", "mutableMapOf", "component1", "", "(Ljava/util/Map$Entry;)Ljava/lang/Object;", "component2", "contains", "", "Lkotlin/internal/OnlyInputTypes;", "key", "(Ljava/util/Map;Ljava/lang/Object;)Z", "containsKey", "containsValue", "value", "filter", "predicate", "filterKeys", "filterNot", "filterNotTo", "M", "destination", "(Ljava/util/Map;Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/util/Map;", "filterTo", "filterValues", "get", "(Ljava/util/Map;Ljava/lang/Object;)Ljava/lang/Object;", "getOrElse", "defaultValue", "Lkotlin/Function0;", "(Ljava/util/Map;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "getOrElseNullable", "getOrPut", "getValue", "ifEmpty", "R", "(Ljava/util/Map;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "isNotEmpty", "isNullOrEmpty", "iterator", "", "", "", "mutableIterator", "mapKeys", "transform", "mapKeysTo", "mapValues", "mapValuesTo", "minus", "(Ljava/util/Map;Ljava/lang/Object;)Ljava/util/Map;", "keys", "(Ljava/util/Map;[Ljava/lang/Object;)Ljava/util/Map;", "", "Lkotlin/sequences/Sequence;", "minusAssign", "(Ljava/util/Map;Ljava/lang/Object;)V", "(Ljava/util/Map;[Ljava/lang/Object;)V", "optimizeReadOnlyMap", "orEmpty", "plus", "(Ljava/util/Map;[Lkotlin/Pair;)Ljava/util/Map;", "pair", "map", "plusAssign", "(Ljava/util/Map;[Lkotlin/Pair;)V", "putAll", "remove", "set", "(Ljava/util/Map;Ljava/lang/Object;Ljava/lang/Object;)V", "toMap", "([Lkotlin/Pair;Ljava/util/Map;)Ljava/util/Map;", "(Ljava/lang/Iterable;Ljava/util/Map;)Ljava/util/Map;", "(Ljava/util/Map;Ljava/util/Map;)Ljava/util/Map;", "(Lkotlin/sequences/Sequence;Ljava/util/Map;)Ljava/util/Map;", "toMutableMap", "toPair", "kotlin-stdlib"}, xs="kotlin/collections/MapsKt")
class MapsKt__MapsKt
extends MapsKt__MapsJVMKt {
    @NotNull
    public static final <K, V> Map<K, V> emptyMap() {
        EmptyMap emptyMap = EmptyMap.INSTANCE;
        if (emptyMap == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.Map<K, V>");
        }
        return emptyMap;
    }

    @NotNull
    public static final <K, V> Map<K, V> mapOf(@NotNull Pair<? extends K, ? extends V> ... pairs) {
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        return pairs.length > 0 ? MapsKt.toMap(pairs, (Map)new LinkedHashMap(MapsKt.mapCapacity(pairs.length))) : MapsKt.emptyMap();
    }

    @InlineOnly
    private static final <K, V> Map<K, V> mapOf() {
        int $i$f$mapOf = 0;
        return MapsKt.emptyMap();
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final <K, V> Map<K, V> mutableMapOf() {
        int $i$f$mutableMapOf = 0;
        return new LinkedHashMap();
    }

    @NotNull
    public static final <K, V> Map<K, V> mutableMapOf(@NotNull Pair<? extends K, ? extends V> ... pairs) {
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(pairs.length));
        boolean bl = false;
        boolean bl2 = false;
        LinkedHashMap $this$apply = linkedHashMap;
        boolean bl3 = false;
        MapsKt.putAll((Map)$this$apply, pairs);
        return linkedHashMap;
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final <K, V> HashMap<K, V> hashMapOf() {
        int $i$f$hashMapOf = 0;
        return new HashMap();
    }

    @NotNull
    public static final <K, V> HashMap<K, V> hashMapOf(@NotNull Pair<? extends K, ? extends V> ... pairs) {
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        HashMap hashMap = new HashMap(MapsKt.mapCapacity(pairs.length));
        boolean bl = false;
        boolean bl2 = false;
        HashMap $this$apply = hashMap;
        boolean bl3 = false;
        MapsKt.putAll((Map)$this$apply, pairs);
        return hashMap;
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final <K, V> LinkedHashMap<K, V> linkedMapOf() {
        int $i$f$linkedMapOf = 0;
        return new LinkedHashMap();
    }

    @NotNull
    public static final <K, V> LinkedHashMap<K, V> linkedMapOf(@NotNull Pair<? extends K, ? extends V> ... pairs) {
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        return (LinkedHashMap)MapsKt.toMap(pairs, (Map)new LinkedHashMap(MapsKt.mapCapacity(pairs.length)));
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final <K, V> Map<K, V> buildMap(@BuilderInference Function1<? super Map<K, V>, Unit> builderAction) {
        int $i$f$buildMap = 0;
        boolean bl = false;
        bl = false;
        Map map = MapsKt.createMapBuilder();
        boolean bl2 = false;
        boolean bl3 = false;
        builderAction.invoke(map);
        return MapsKt.build(map);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final <K, V> Map<K, V> buildMap(int capacity, @BuilderInference Function1<? super Map<K, V>, Unit> builderAction) {
        int $i$f$buildMap = 0;
        boolean bl = false;
        bl = false;
        Map map = MapsKt.createMapBuilder(capacity);
        boolean bl2 = false;
        boolean bl3 = false;
        builderAction.invoke(map);
        return MapsKt.build(map);
    }

    @InlineOnly
    private static final <K, V> boolean isNotEmpty(Map<? extends K, ? extends V> $this$isNotEmpty) {
        int $i$f$isNotEmpty = 0;
        return !$this$isNotEmpty.isEmpty();
    }

    @SinceKotlin(version="1.3")
    @InlineOnly
    private static final <K, V> boolean isNullOrEmpty(Map<? extends K, ? extends V> $this$isNullOrEmpty) {
        int $i$f$isNullOrEmpty = 0;
        boolean bl = false;
        return $this$isNullOrEmpty == null || $this$isNullOrEmpty.isEmpty();
    }

    @InlineOnly
    private static final <K, V> Map<K, V> orEmpty(Map<K, ? extends V> $this$orEmpty) {
        int $i$f$orEmpty = 0;
        Map<K, Object> map = $this$orEmpty;
        if (map == null) {
            map = MapsKt.emptyMap();
        }
        return map;
    }

    @SinceKotlin(version="1.3")
    @InlineOnly
    private static final <M extends Map<?, ?> & R, R> R ifEmpty(M $this$ifEmpty, Function0<? extends R> defaultValue) {
        int $i$f$ifEmpty = 0;
        return (R)($this$ifEmpty.isEmpty() ? defaultValue.invoke() : $this$ifEmpty);
    }

    @InlineOnly
    private static final <K, V> boolean contains(Map<? extends K, ? extends V> $this$contains, K key) {
        int $i$f$contains = 0;
        Intrinsics.checkNotNullParameter($this$contains, "$this$contains");
        Map<K, V> map = $this$contains;
        boolean bl = false;
        return map.containsKey(key);
    }

    @InlineOnly
    private static final <K, V> V get(Map<? extends K, ? extends V> $this$get, K key) {
        int $i$f$get = 0;
        Intrinsics.checkNotNullParameter($this$get, "$this$get");
        return $this$get.get(key);
    }

    @InlineOnly
    private static final <K, V> void set(Map<K, V> $this$set, K key, V value) {
        int $i$f$set = 0;
        Intrinsics.checkNotNullParameter($this$set, "$this$set");
        $this$set.put(key, value);
    }

    @InlineOnly
    private static final <K> boolean containsKey(Map<? extends K, ?> $this$containsKey, K key) {
        int $i$f$containsKey = 0;
        Map<K, ?> map = $this$containsKey;
        if (map == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.Map<K, *>");
        }
        return map.containsKey(key);
    }

    @InlineOnly
    private static final <K, V> boolean containsValue(Map<K, ? extends V> $this$containsValue, V value) {
        int $i$f$containsValue = 0;
        return $this$containsValue.containsValue(value);
    }

    @InlineOnly
    private static final <K, V> V remove(Map<? extends K, V> $this$remove, K key) {
        int $i$f$remove = 0;
        Map<? extends K, V> map = $this$remove;
        if (map == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.MutableMap<K, V>");
        }
        return TypeIntrinsics.asMutableMap(map).remove(key);
    }

    @InlineOnly
    private static final <K, V> K component1(Map.Entry<? extends K, ? extends V> $this$component1) {
        int $i$f$component1 = 0;
        Intrinsics.checkNotNullParameter($this$component1, "$this$component1");
        return $this$component1.getKey();
    }

    @InlineOnly
    private static final <K, V> V component2(Map.Entry<? extends K, ? extends V> $this$component2) {
        int $i$f$component2 = 0;
        Intrinsics.checkNotNullParameter($this$component2, "$this$component2");
        return $this$component2.getValue();
    }

    @InlineOnly
    private static final <K, V> Pair<K, V> toPair(Map.Entry<? extends K, ? extends V> $this$toPair) {
        int $i$f$toPair = 0;
        return new Pair<K, V>($this$toPair.getKey(), $this$toPair.getValue());
    }

    @InlineOnly
    private static final <K, V> V getOrElse(Map<K, ? extends V> $this$getOrElse, K key, Function0<? extends V> defaultValue) {
        int $i$f$getOrElse = 0;
        V v = $this$getOrElse.get(key);
        if (v == null) {
            v = defaultValue.invoke();
        }
        return v;
    }

    public static final <K, V> V getOrElseNullable(@NotNull Map<K, ? extends V> $this$getOrElseNullable, K key, @NotNull Function0<? extends V> defaultValue) {
        int $i$f$getOrElseNullable = 0;
        Intrinsics.checkNotNullParameter($this$getOrElseNullable, "$this$getOrElseNullable");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        V value = $this$getOrElseNullable.get(key);
        if (value == null && !$this$getOrElseNullable.containsKey(key)) {
            return defaultValue.invoke();
        }
        return value;
    }

    @SinceKotlin(version="1.1")
    public static final <K, V> V getValue(@NotNull Map<K, ? extends V> $this$getValue, K key) {
        Intrinsics.checkNotNullParameter($this$getValue, "$this$getValue");
        return MapsKt.getOrImplicitDefaultNullable($this$getValue, key);
    }

    public static final <K, V> V getOrPut(@NotNull Map<K, V> $this$getOrPut, K key, @NotNull Function0<? extends V> defaultValue) {
        V v;
        int $i$f$getOrPut = 0;
        Intrinsics.checkNotNullParameter($this$getOrPut, "$this$getOrPut");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        V value = $this$getOrPut.get(key);
        if (value == null) {
            V answer = defaultValue.invoke();
            $this$getOrPut.put(key, answer);
            v = answer;
        } else {
            v = value;
        }
        return v;
    }

    @InlineOnly
    private static final <K, V> Iterator<Map.Entry<K, V>> iterator(Map<? extends K, ? extends V> $this$iterator) {
        int $i$f$iterator = 0;
        Intrinsics.checkNotNullParameter($this$iterator, "$this$iterator");
        return $this$iterator.entrySet().iterator();
    }

    @JvmName(name="mutableIterator")
    @InlineOnly
    private static final <K, V> Iterator<Map.Entry<K, V>> mutableIterator(Map<K, V> $this$iterator) {
        int $i$f$mutableIterator = 0;
        Intrinsics.checkNotNullParameter($this$iterator, "$this$iterator");
        return $this$iterator.entrySet().iterator();
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public static final <K, V, R, M extends Map<? super K, ? super R>> M mapValuesTo(@NotNull Map<? extends K, ? extends V> $this$mapValuesTo, @NotNull M destination, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> transform) {
        int $i$f$mapValuesTo = 0;
        Intrinsics.checkNotNullParameter($this$mapValuesTo, "$this$mapValuesTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        Iterable $this$associateByTo$iv = $this$mapValuesTo.entrySet();
        boolean $i$f$associateByTo = false;
        for (Object element$iv : $this$associateByTo$iv) {
            void it;
            Map.Entry entry = (Map.Entry)element$iv;
            M m3 = destination;
            boolean bl = false;
            Object k = it.getKey();
            m3.put(k, transform.invoke((Map.Entry<K, V>)element$iv));
        }
        return destination;
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public static final <K, V, R, M extends Map<? super R, ? super V>> M mapKeysTo(@NotNull Map<? extends K, ? extends V> $this$mapKeysTo, @NotNull M destination, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> transform) {
        int $i$f$mapKeysTo = 0;
        Intrinsics.checkNotNullParameter($this$mapKeysTo, "$this$mapKeysTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        Iterable $this$associateByTo$iv = $this$mapKeysTo.entrySet();
        boolean $i$f$associateByTo = false;
        for (Object element$iv : $this$associateByTo$iv) {
            void it;
            Map.Entry entry = (Map.Entry)element$iv;
            R r = transform.invoke((Map.Entry<K, V>)element$iv);
            M m3 = destination;
            boolean bl = false;
            Object v = it.getValue();
            m3.put(r, v);
        }
        return destination;
    }

    /*
     * WARNING - void declaration
     */
    public static final <K, V> void putAll(@NotNull Map<? super K, ? super V> $this$putAll, @NotNull Pair<? extends K, ? extends V>[] pairs) {
        Intrinsics.checkNotNullParameter($this$putAll, "$this$putAll");
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        for (Pair<K, V> pair : pairs) {
            void key;
            K k = pair.component1();
            V value = pair.component2();
            $this$putAll.put(key, value);
        }
    }

    /*
     * WARNING - void declaration
     */
    public static final <K, V> void putAll(@NotNull Map<? super K, ? super V> $this$putAll, @NotNull Iterable<? extends Pair<? extends K, ? extends V>> pairs) {
        Intrinsics.checkNotNullParameter($this$putAll, "$this$putAll");
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        for (Pair<K, V> pair : pairs) {
            void key;
            K k = pair.component1();
            V value = pair.component2();
            $this$putAll.put(key, value);
        }
    }

    /*
     * WARNING - void declaration
     */
    public static final <K, V> void putAll(@NotNull Map<? super K, ? super V> $this$putAll, @NotNull Sequence<? extends Pair<? extends K, ? extends V>> pairs) {
        Intrinsics.checkNotNullParameter($this$putAll, "$this$putAll");
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        Iterator<Pair<K, V>> iterator2 = pairs.iterator();
        while (iterator2.hasNext()) {
            void key;
            Pair<K, V> pair = iterator2.next();
            K k = pair.component1();
            V value = pair.component2();
            $this$putAll.put(key, value);
        }
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public static final <K, V, R> Map<K, R> mapValues(@NotNull Map<? extends K, ? extends V> $this$mapValues, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> transform) {
        void $this$mapValuesTo$iv;
        int $i$f$mapValues = 0;
        Intrinsics.checkNotNullParameter($this$mapValues, "$this$mapValues");
        Intrinsics.checkNotNullParameter(transform, "transform");
        Map<K, V> map = $this$mapValues;
        Map destination$iv = new LinkedHashMap(MapsKt.mapCapacity($this$mapValues.size()));
        boolean $i$f$mapValuesTo = false;
        Iterable $this$associateByTo$iv$iv = $this$mapValuesTo$iv.entrySet();
        boolean $i$f$associateByTo = false;
        for (Object element$iv$iv : $this$associateByTo$iv$iv) {
            void it$iv;
            Map.Entry entry = (Map.Entry)element$iv$iv;
            Map map2 = destination$iv;
            boolean bl = false;
            Object k = it$iv.getKey();
            map2.put(k, transform.invoke((Map.Entry<K, V>)element$iv$iv));
        }
        return destination$iv;
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public static final <K, V, R> Map<R, V> mapKeys(@NotNull Map<? extends K, ? extends V> $this$mapKeys, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> transform) {
        void $this$mapKeysTo$iv;
        int $i$f$mapKeys = 0;
        Intrinsics.checkNotNullParameter($this$mapKeys, "$this$mapKeys");
        Intrinsics.checkNotNullParameter(transform, "transform");
        Map<K, V> map = $this$mapKeys;
        Map destination$iv = new LinkedHashMap(MapsKt.mapCapacity($this$mapKeys.size()));
        boolean $i$f$mapKeysTo = false;
        Iterable $this$associateByTo$iv$iv = $this$mapKeysTo$iv.entrySet();
        boolean $i$f$associateByTo = false;
        for (Object element$iv$iv : $this$associateByTo$iv$iv) {
            void it$iv;
            Map.Entry entry = (Map.Entry)element$iv$iv;
            R r = transform.invoke((Map.Entry<K, V>)element$iv$iv);
            Map map2 = destination$iv;
            boolean bl = false;
            Object v = it$iv.getValue();
            map2.put(r, v);
        }
        return destination$iv;
    }

    @NotNull
    public static final <K, V> Map<K, V> filterKeys(@NotNull Map<? extends K, ? extends V> $this$filterKeys, @NotNull Function1<? super K, Boolean> predicate) {
        int $i$f$filterKeys = 0;
        Intrinsics.checkNotNullParameter($this$filterKeys, "$this$filterKeys");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        LinkedHashMap<K, V> result = new LinkedHashMap<K, V>();
        Map<K, V> map = $this$filterKeys;
        boolean bl = false;
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (!predicate.invoke(entry.getKey()).booleanValue()) continue;
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    @NotNull
    public static final <K, V> Map<K, V> filterValues(@NotNull Map<? extends K, ? extends V> $this$filterValues, @NotNull Function1<? super V, Boolean> predicate) {
        int $i$f$filterValues = 0;
        Intrinsics.checkNotNullParameter($this$filterValues, "$this$filterValues");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        LinkedHashMap<K, V> result = new LinkedHashMap<K, V>();
        Map<K, V> map = $this$filterValues;
        boolean bl = false;
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (!predicate.invoke(entry.getValue()).booleanValue()) continue;
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M filterTo(@NotNull Map<? extends K, ? extends V> $this$filterTo, @NotNull M destination, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, Boolean> predicate) {
        int $i$f$filterTo = 0;
        Intrinsics.checkNotNullParameter($this$filterTo, "$this$filterTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        Map<K, V> map = $this$filterTo;
        boolean bl = false;
        for (Map.Entry<K, V> element : map.entrySet()) {
            if (!predicate.invoke(element).booleanValue()) continue;
            destination.put(element.getKey(), element.getValue());
        }
        return destination;
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public static final <K, V> Map<K, V> filter(@NotNull Map<? extends K, ? extends V> $this$filter, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, Boolean> predicate) {
        void $this$filterTo$iv;
        int $i$f$filter = 0;
        Intrinsics.checkNotNullParameter($this$filter, "$this$filter");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        Map<? extends K, ? extends V> map = $this$filter;
        Map destination$iv = new LinkedHashMap();
        boolean $i$f$filterTo = false;
        void var6_6 = $this$filterTo$iv;
        boolean bl = false;
        for (Map.Entry element$iv : var6_6.entrySet()) {
            if (!predicate.invoke(element$iv).booleanValue()) continue;
            destination$iv.put(element$iv.getKey(), element$iv.getValue());
        }
        return destination$iv;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M filterNotTo(@NotNull Map<? extends K, ? extends V> $this$filterNotTo, @NotNull M destination, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, Boolean> predicate) {
        int $i$f$filterNotTo = 0;
        Intrinsics.checkNotNullParameter($this$filterNotTo, "$this$filterNotTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        Map<K, V> map = $this$filterNotTo;
        boolean bl = false;
        for (Map.Entry<K, V> element : map.entrySet()) {
            if (predicate.invoke(element).booleanValue()) continue;
            destination.put(element.getKey(), element.getValue());
        }
        return destination;
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public static final <K, V> Map<K, V> filterNot(@NotNull Map<? extends K, ? extends V> $this$filterNot, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, Boolean> predicate) {
        void $this$filterNotTo$iv;
        int $i$f$filterNot = 0;
        Intrinsics.checkNotNullParameter($this$filterNot, "$this$filterNot");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        Map<? extends K, ? extends V> map = $this$filterNot;
        Map destination$iv = new LinkedHashMap();
        boolean $i$f$filterNotTo = false;
        void var6_6 = $this$filterNotTo$iv;
        boolean bl = false;
        for (Map.Entry element$iv : var6_6.entrySet()) {
            if (predicate.invoke(element$iv).booleanValue()) continue;
            destination$iv.put(element$iv.getKey(), element$iv.getValue());
        }
        return destination$iv;
    }

    @NotNull
    public static final <K, V> Map<K, V> toMap(@NotNull Iterable<? extends Pair<? extends K, ? extends V>> $this$toMap) {
        Intrinsics.checkNotNullParameter($this$toMap, "$this$toMap");
        if ($this$toMap instanceof Collection) {
            Map map;
            switch (((Collection)$this$toMap).size()) {
                case 0: {
                    map = MapsKt.emptyMap();
                    break;
                }
                case 1: {
                    map = MapsKt.mapOf($this$toMap instanceof List ? (Pair)((List)$this$toMap).get(0) : $this$toMap.iterator().next());
                    break;
                }
                default: {
                    map = MapsKt.toMap($this$toMap, (Map)new LinkedHashMap(MapsKt.mapCapacity(((Collection)$this$toMap).size())));
                }
            }
            return map;
        }
        return MapsKt.optimizeReadOnlyMap(MapsKt.toMap($this$toMap, (Map)new LinkedHashMap()));
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M toMap(@NotNull Iterable<? extends Pair<? extends K, ? extends V>> $this$toMap, @NotNull M destination) {
        Intrinsics.checkNotNullParameter($this$toMap, "$this$toMap");
        Intrinsics.checkNotNullParameter(destination, "destination");
        M m3 = destination;
        boolean bl = false;
        boolean bl2 = false;
        M $this$apply = m3;
        boolean bl3 = false;
        MapsKt.putAll($this$apply, $this$toMap);
        return m3;
    }

    @NotNull
    public static final <K, V> Map<K, V> toMap(@NotNull Pair<? extends K, ? extends V>[] $this$toMap) {
        Map<Object, Object> map;
        Intrinsics.checkNotNullParameter($this$toMap, "$this$toMap");
        switch ($this$toMap.length) {
            case 0: {
                map = MapsKt.emptyMap();
                break;
            }
            case 1: {
                map = MapsKt.mapOf($this$toMap[0]);
                break;
            }
            default: {
                map = MapsKt.toMap($this$toMap, (Map)new LinkedHashMap(MapsKt.mapCapacity($this$toMap.length)));
            }
        }
        return map;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M toMap(@NotNull Pair<? extends K, ? extends V>[] $this$toMap, @NotNull M destination) {
        Intrinsics.checkNotNullParameter($this$toMap, "$this$toMap");
        Intrinsics.checkNotNullParameter(destination, "destination");
        M m3 = destination;
        boolean bl = false;
        boolean bl2 = false;
        M $this$apply = m3;
        boolean bl3 = false;
        MapsKt.putAll($this$apply, $this$toMap);
        return m3;
    }

    @NotNull
    public static final <K, V> Map<K, V> toMap(@NotNull Sequence<? extends Pair<? extends K, ? extends V>> $this$toMap) {
        Intrinsics.checkNotNullParameter($this$toMap, "$this$toMap");
        return MapsKt.optimizeReadOnlyMap(MapsKt.toMap($this$toMap, (Map)new LinkedHashMap()));
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M toMap(@NotNull Sequence<? extends Pair<? extends K, ? extends V>> $this$toMap, @NotNull M destination) {
        Intrinsics.checkNotNullParameter($this$toMap, "$this$toMap");
        Intrinsics.checkNotNullParameter(destination, "destination");
        M m3 = destination;
        boolean bl = false;
        boolean bl2 = false;
        M $this$apply = m3;
        boolean bl3 = false;
        MapsKt.putAll($this$apply, $this$toMap);
        return m3;
    }

    @SinceKotlin(version="1.1")
    @NotNull
    public static final <K, V> Map<K, V> toMap(@NotNull Map<? extends K, ? extends V> $this$toMap) {
        Map<Object, Object> map;
        Intrinsics.checkNotNullParameter($this$toMap, "$this$toMap");
        switch ($this$toMap.size()) {
            case 0: {
                map = MapsKt.emptyMap();
                break;
            }
            case 1: {
                map = MapsKt.toSingletonMap($this$toMap);
                break;
            }
            default: {
                map = MapsKt.toMutableMap($this$toMap);
            }
        }
        return map;
    }

    @SinceKotlin(version="1.1")
    @NotNull
    public static final <K, V> Map<K, V> toMutableMap(@NotNull Map<? extends K, ? extends V> $this$toMutableMap) {
        Intrinsics.checkNotNullParameter($this$toMutableMap, "$this$toMutableMap");
        return new LinkedHashMap<K, V>($this$toMutableMap);
    }

    @SinceKotlin(version="1.1")
    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M toMap(@NotNull Map<? extends K, ? extends V> $this$toMap, @NotNull M destination) {
        Intrinsics.checkNotNullParameter($this$toMap, "$this$toMap");
        Intrinsics.checkNotNullParameter(destination, "destination");
        M m3 = destination;
        boolean bl = false;
        boolean bl2 = false;
        M $this$apply = m3;
        boolean bl3 = false;
        $this$apply.putAll($this$toMap);
        return m3;
    }

    @NotNull
    public static final <K, V> Map<K, V> plus(@NotNull Map<? extends K, ? extends V> $this$plus, @NotNull Pair<? extends K, ? extends V> pair) {
        Map map;
        Intrinsics.checkNotNullParameter($this$plus, "$this$plus");
        Intrinsics.checkNotNullParameter(pair, "pair");
        if ($this$plus.isEmpty()) {
            map = MapsKt.mapOf(pair);
        } else {
            LinkedHashMap<K, V> linkedHashMap = new LinkedHashMap<K, V>($this$plus);
            boolean bl = false;
            boolean bl2 = false;
            LinkedHashMap<K, V> $this$apply = linkedHashMap;
            boolean bl3 = false;
            $this$apply.put(pair.getFirst(), pair.getSecond());
            map = linkedHashMap;
        }
        return map;
    }

    @NotNull
    public static final <K, V> Map<K, V> plus(@NotNull Map<? extends K, ? extends V> $this$plus, @NotNull Iterable<? extends Pair<? extends K, ? extends V>> pairs) {
        Map map;
        Intrinsics.checkNotNullParameter($this$plus, "$this$plus");
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        if ($this$plus.isEmpty()) {
            map = MapsKt.toMap(pairs);
        } else {
            LinkedHashMap<? extends K, ? extends V> linkedHashMap = new LinkedHashMap<K, V>($this$plus);
            boolean bl = false;
            boolean bl2 = false;
            LinkedHashMap<? extends K, ? extends V> $this$apply = linkedHashMap;
            boolean bl3 = false;
            MapsKt.putAll((Map)$this$apply, pairs);
            map = linkedHashMap;
        }
        return map;
    }

    @NotNull
    public static final <K, V> Map<K, V> plus(@NotNull Map<? extends K, ? extends V> $this$plus, @NotNull Pair<? extends K, ? extends V>[] pairs) {
        Map map;
        Intrinsics.checkNotNullParameter($this$plus, "$this$plus");
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        if ($this$plus.isEmpty()) {
            map = MapsKt.toMap(pairs);
        } else {
            LinkedHashMap<? extends K, ? extends V> linkedHashMap = new LinkedHashMap<K, V>($this$plus);
            boolean bl = false;
            boolean bl2 = false;
            LinkedHashMap<? extends K, ? extends V> $this$apply = linkedHashMap;
            boolean bl3 = false;
            MapsKt.putAll((Map)$this$apply, pairs);
            map = linkedHashMap;
        }
        return map;
    }

    @NotNull
    public static final <K, V> Map<K, V> plus(@NotNull Map<? extends K, ? extends V> $this$plus, @NotNull Sequence<? extends Pair<? extends K, ? extends V>> pairs) {
        Intrinsics.checkNotNullParameter($this$plus, "$this$plus");
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        LinkedHashMap<? extends K, ? extends V> linkedHashMap = new LinkedHashMap<K, V>($this$plus);
        boolean bl = false;
        boolean bl2 = false;
        LinkedHashMap<? extends K, ? extends V> $this$apply = linkedHashMap;
        boolean bl3 = false;
        MapsKt.putAll((Map)$this$apply, pairs);
        return MapsKt.optimizeReadOnlyMap((Map)linkedHashMap);
    }

    @NotNull
    public static final <K, V> Map<K, V> plus(@NotNull Map<? extends K, ? extends V> $this$plus, @NotNull Map<? extends K, ? extends V> map) {
        Intrinsics.checkNotNullParameter($this$plus, "$this$plus");
        Intrinsics.checkNotNullParameter(map, "map");
        LinkedHashMap<? extends K, ? extends V> linkedHashMap = new LinkedHashMap<K, V>($this$plus);
        boolean bl = false;
        boolean bl2 = false;
        LinkedHashMap<? extends K, ? extends V> $this$apply = linkedHashMap;
        boolean bl3 = false;
        $this$apply.putAll(map);
        return linkedHashMap;
    }

    @InlineOnly
    private static final <K, V> void plusAssign(Map<? super K, ? super V> $this$plusAssign, Pair<? extends K, ? extends V> pair) {
        int $i$f$plusAssign = 0;
        Intrinsics.checkNotNullParameter($this$plusAssign, "$this$plusAssign");
        $this$plusAssign.put(pair.getFirst(), pair.getSecond());
    }

    @InlineOnly
    private static final <K, V> void plusAssign(Map<? super K, ? super V> $this$plusAssign, Iterable<? extends Pair<? extends K, ? extends V>> pairs) {
        int $i$f$plusAssign = 0;
        Intrinsics.checkNotNullParameter($this$plusAssign, "$this$plusAssign");
        MapsKt.putAll($this$plusAssign, pairs);
    }

    @InlineOnly
    private static final <K, V> void plusAssign(Map<? super K, ? super V> $this$plusAssign, Pair<? extends K, ? extends V>[] pairs) {
        int $i$f$plusAssign = 0;
        Intrinsics.checkNotNullParameter($this$plusAssign, "$this$plusAssign");
        MapsKt.putAll($this$plusAssign, pairs);
    }

    @InlineOnly
    private static final <K, V> void plusAssign(Map<? super K, ? super V> $this$plusAssign, Sequence<? extends Pair<? extends K, ? extends V>> pairs) {
        int $i$f$plusAssign = 0;
        Intrinsics.checkNotNullParameter($this$plusAssign, "$this$plusAssign");
        MapsKt.putAll($this$plusAssign, pairs);
    }

    @InlineOnly
    private static final <K, V> void plusAssign(Map<? super K, ? super V> $this$plusAssign, Map<K, ? extends V> map) {
        int $i$f$plusAssign = 0;
        Intrinsics.checkNotNullParameter($this$plusAssign, "$this$plusAssign");
        $this$plusAssign.putAll(map);
    }

    @SinceKotlin(version="1.1")
    @NotNull
    public static final <K, V> Map<K, V> minus(@NotNull Map<? extends K, ? extends V> $this$minus, K key) {
        Intrinsics.checkNotNullParameter($this$minus, "$this$minus");
        Map<K, V> map = MapsKt.toMutableMap($this$minus);
        boolean bl = false;
        boolean bl2 = false;
        Map<K, V> $this$apply = map;
        boolean bl3 = false;
        Map<K, V> map2 = $this$apply;
        K k = key;
        boolean bl4 = false;
        map2.remove(k);
        return MapsKt.optimizeReadOnlyMap(map);
    }

    @SinceKotlin(version="1.1")
    @NotNull
    public static final <K, V> Map<K, V> minus(@NotNull Map<? extends K, ? extends V> $this$minus, @NotNull Iterable<? extends K> keys2) {
        Intrinsics.checkNotNullParameter($this$minus, "$this$minus");
        Intrinsics.checkNotNullParameter(keys2, "keys");
        Map<K, V> map = MapsKt.toMutableMap($this$minus);
        boolean bl = false;
        boolean bl2 = false;
        Map<K, V> $this$apply = map;
        boolean bl3 = false;
        Map<K, V> map2 = $this$apply;
        Iterable<? extends K> iterable = keys2;
        boolean bl4 = false;
        CollectionsKt.removeAll((Collection)map2.keySet(), iterable);
        return MapsKt.optimizeReadOnlyMap(map);
    }

    @SinceKotlin(version="1.1")
    @NotNull
    public static final <K, V> Map<K, V> minus(@NotNull Map<? extends K, ? extends V> $this$minus, @NotNull K[] keys2) {
        Intrinsics.checkNotNullParameter($this$minus, "$this$minus");
        Intrinsics.checkNotNullParameter(keys2, "keys");
        Map<K, V> map = MapsKt.toMutableMap($this$minus);
        boolean bl = false;
        boolean bl2 = false;
        Map<K, V> $this$apply = map;
        boolean bl3 = false;
        Map<K, V> map2 = $this$apply;
        K[] KArray = keys2;
        boolean bl4 = false;
        CollectionsKt.removeAll((Collection)map2.keySet(), KArray);
        return MapsKt.optimizeReadOnlyMap(map);
    }

    @SinceKotlin(version="1.1")
    @NotNull
    public static final <K, V> Map<K, V> minus(@NotNull Map<? extends K, ? extends V> $this$minus, @NotNull Sequence<? extends K> keys2) {
        Intrinsics.checkNotNullParameter($this$minus, "$this$minus");
        Intrinsics.checkNotNullParameter(keys2, "keys");
        Map<K, V> map = MapsKt.toMutableMap($this$minus);
        boolean bl = false;
        boolean bl2 = false;
        Map<K, V> $this$apply = map;
        boolean bl3 = false;
        Map<K, V> map2 = $this$apply;
        Sequence<? extends K> sequence = keys2;
        boolean bl4 = false;
        CollectionsKt.removeAll((Collection)map2.keySet(), sequence);
        return MapsKt.optimizeReadOnlyMap(map);
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final <K, V> void minusAssign(Map<K, V> $this$minusAssign, K key) {
        int $i$f$minusAssign = 0;
        Intrinsics.checkNotNullParameter($this$minusAssign, "$this$minusAssign");
        $this$minusAssign.remove(key);
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final <K, V> void minusAssign(Map<K, V> $this$minusAssign, Iterable<? extends K> keys2) {
        int $i$f$minusAssign = 0;
        Intrinsics.checkNotNullParameter($this$minusAssign, "$this$minusAssign");
        CollectionsKt.removeAll((Collection)$this$minusAssign.keySet(), keys2);
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final <K, V> void minusAssign(Map<K, V> $this$minusAssign, K[] keys2) {
        int $i$f$minusAssign = 0;
        Intrinsics.checkNotNullParameter($this$minusAssign, "$this$minusAssign");
        CollectionsKt.removeAll((Collection)$this$minusAssign.keySet(), keys2);
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final <K, V> void minusAssign(Map<K, V> $this$minusAssign, Sequence<? extends K> keys2) {
        int $i$f$minusAssign = 0;
        Intrinsics.checkNotNullParameter($this$minusAssign, "$this$minusAssign");
        CollectionsKt.removeAll((Collection)$this$minusAssign.keySet(), keys2);
    }

    @NotNull
    public static final <K, V> Map<K, V> optimizeReadOnlyMap(@NotNull Map<K, ? extends V> $this$optimizeReadOnlyMap) {
        Map<K, Object> map;
        Intrinsics.checkNotNullParameter($this$optimizeReadOnlyMap, "$this$optimizeReadOnlyMap");
        switch ($this$optimizeReadOnlyMap.size()) {
            case 0: {
                map = MapsKt.emptyMap();
                break;
            }
            case 1: {
                Map<K, V> map2 = $this$optimizeReadOnlyMap;
                boolean bl = false;
                map = MapsKt.toSingletonMap(map2);
                break;
            }
            default: {
                map = $this$optimizeReadOnlyMap;
            }
        }
        return map;
    }
}

