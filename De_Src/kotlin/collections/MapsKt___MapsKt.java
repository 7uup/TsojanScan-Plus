/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.Metadata;
import kotlin.OverloadResolutionByLambdaReturnType;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.internal.HidesMembers;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=5, xi=1, d1={"\u0000\u0080\u0001\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010&\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u001f\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000f\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u001aJ\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\u0005\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u00010\u0006H\u0086\b\u00f8\u0001\u0000\u001a$\u0010\b\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004\u001aJ\u0010\b\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\u0005\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u00010\u0006H\u0086\b\u00f8\u0001\u0000\u001a9\u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070\n\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004H\u0087\b\u001a6\u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070\f\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004\u001a'\u0010\r\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004H\u0087\b\u001aJ\u0010\r\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\u0005\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u00010\u0006H\u0086\b\u00f8\u0001\u0000\u001a\\\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0011*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042$\u0010\u0012\u001a \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\n0\u0006H\u0086\b\u00f8\u0001\u0000\u001aa\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0011*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042$\u0010\u0012\u001a \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\f0\u0006H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\b\u0013\u001au\u0010\u0014\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0011\"\u0010\b\u0003\u0010\u0015*\n\u0012\u0006\b\u0000\u0012\u0002H\u00110\u0016*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u0017\u001a\u0002H\u00152$\u0010\u0012\u001a \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\n0\u0006H\u0086\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018\u001aw\u0010\u0014\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0011\"\u0010\b\u0003\u0010\u0015*\n\u0012\u0006\b\u0000\u0012\u0002H\u00110\u0016*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u0017\u001a\u0002H\u00152$\u0010\u0012\u001a \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\f0\u0006H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0019\u0010\u0018\u001aJ\u0010\u001a\u001a\u00020\u001b\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\u001c\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u001b0\u0006H\u0087\b\u00f8\u0001\u0000\u001aV\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0011*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\u0012\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00110\u0006H\u0086\b\u00f8\u0001\u0000\u001a\\\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\b\b\u0002\u0010\u0011*\u00020\u001f*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042 \u0010\u0012\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0006\u0012\u0004\u0018\u0001H\u00110\u0006H\u0086\b\u00f8\u0001\u0000\u001au\u0010 \u001a\u0002H\u0015\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\b\b\u0002\u0010\u0011*\u00020\u001f\"\u0010\b\u0003\u0010\u0015*\n\u0012\u0006\b\u0000\u0012\u0002H\u00110\u0016*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u0017\u001a\u0002H\u00152 \u0010\u0012\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0006\u0012\u0004\u0018\u0001H\u00110\u0006H\u0086\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018\u001ao\u0010!\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0011\"\u0010\b\u0003\u0010\u0015*\n\u0012\u0006\b\u0000\u0012\u0002H\u00110\u0016*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u0017\u001a\u0002H\u00152\u001e\u0010\u0012\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00110\u0006H\u0086\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018\u001ah\u0010\"\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0011*\b\u0012\u0004\u0012\u0002H\u00110#*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010$\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00110\u0006H\u0087\b\u00f8\u0001\u0000\u001ah\u0010%\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0011*\b\u0012\u0004\u0012\u0002H\u00110#*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010$\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00110\u0006H\u0087\b\u00f8\u0001\u0000\u001a_\u0010&\u001a\u0002H\u0011\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0011*\b\u0012\u0004\u0012\u0002H\u00110#*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010$\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00110\u0006H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010'\u001aJ\u0010&\u001a\u00020(\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010$\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020(0\u0006H\u0087\b\u00f8\u0001\u0000\u001aJ\u0010&\u001a\u00020)\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010$\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020)0\u0006H\u0087\b\u00f8\u0001\u0000\u001aa\u0010*\u001a\u0004\u0018\u0001H\u0011\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0011*\b\u0012\u0004\u0012\u0002H\u00110#*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010$\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00110\u0006H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010'\u001aQ\u0010*\u001a\u0004\u0018\u00010(\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010$\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020(0\u0006H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010+\u001aQ\u0010*\u001a\u0004\u0018\u00010)\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010$\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020)0\u0006H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010,\u001aq\u0010-\u001a\u0002H\u0011\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0011*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001a\u0010.\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00110/j\n\u0012\u0006\b\u0000\u0012\u0002H\u0011`02\u001e\u0010$\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00110\u0006H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u00101\u001as\u00102\u001a\u0004\u0018\u0001H\u0011\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0011*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001a\u0010.\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00110/j\n\u0012\u0006\b\u0000\u0012\u0002H\u0011`02\u001e\u0010$\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00110\u0006H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u00101\u001ai\u00103\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000422\u0010.\u001a.\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070/j\u0016\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007`0H\u0087\b\u001ai\u00104\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000422\u0010.\u001a.\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070/j\u0016\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007`0H\u0087\b\u001ah\u00105\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0011*\b\u0012\u0004\u0012\u0002H\u00110#*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010$\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00110\u0006H\u0087\b\u00f8\u0001\u0000\u001ah\u00106\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0011*\b\u0012\u0004\u0012\u0002H\u00110#*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010$\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00110\u0006H\u0087\b\u00f8\u0001\u0000\u001a_\u00107\u001a\u0002H\u0011\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0011*\b\u0012\u0004\u0012\u0002H\u00110#*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010$\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00110\u0006H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010'\u001aJ\u00107\u001a\u00020(\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010$\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020(0\u0006H\u0087\b\u00f8\u0001\u0000\u001aJ\u00107\u001a\u00020)\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010$\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020)0\u0006H\u0087\b\u00f8\u0001\u0000\u001aa\u00108\u001a\u0004\u0018\u0001H\u0011\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0011*\b\u0012\u0004\u0012\u0002H\u00110#*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010$\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00110\u0006H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010'\u001aQ\u00108\u001a\u0004\u0018\u00010(\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010$\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020(0\u0006H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010+\u001aQ\u00108\u001a\u0004\u0018\u00010)\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010$\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020)0\u0006H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010,\u001aq\u00109\u001a\u0002H\u0011\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0011*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001a\u0010.\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00110/j\n\u0012\u0006\b\u0000\u0012\u0002H\u0011`02\u001e\u0010$\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00110\u0006H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u00101\u001as\u0010:\u001a\u0004\u0018\u0001H\u0011\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0011*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001a\u0010.\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00110/j\n\u0012\u0006\b\u0000\u0012\u0002H\u0011`02\u001e\u0010$\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00110\u0006H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u00101\u001ah\u0010;\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000422\u0010.\u001a.\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070/j\u0016\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007`0H\u0007\u001ai\u0010<\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000422\u0010.\u001a.\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070/j\u0016\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007`0H\u0087\b\u001a$\u0010=\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004\u001aJ\u0010=\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\u0005\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u00010\u0006H\u0086\b\u00f8\u0001\u0000\u001aY\u0010>\u001a\u0002H?\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0016\b\u0002\u0010?*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004*\u0002H?2\u001e\u0010\u001c\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u001b0\u0006H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010@\u001an\u0010A\u001a\u0002H?\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0016\b\u0002\u0010?*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004*\u0002H?23\u0010\u001c\u001a/\u0012\u0013\u0012\u00110\u000e\u00a2\u0006\f\bC\u0012\b\bD\u0012\u0004\b\b(E\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u001b0BH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010F\u001a6\u0010G\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030H0\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00a8\u0006I"}, d2={"all", "", "K", "V", "", "predicate", "Lkotlin/Function1;", "", "any", "asIterable", "", "asSequence", "Lkotlin/sequences/Sequence;", "count", "", "flatMap", "", "R", "transform", "flatMapSequence", "flatMapTo", "C", "", "destination", "(Ljava/util/Map;Ljava/util/Collection;Lkotlin/jvm/functions/Function1;)Ljava/util/Collection;", "flatMapSequenceTo", "forEach", "", "action", "map", "mapNotNull", "", "mapNotNullTo", "mapTo", "maxBy", "", "selector", "maxByOrNull", "maxOf", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/lang/Comparable;", "", "", "maxOfOrNull", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/lang/Double;", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/lang/Float;", "maxOfWith", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Ljava/util/Map;Ljava/util/Comparator;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "maxOfWithOrNull", "maxWith", "maxWithOrNull", "minBy", "minByOrNull", "minOf", "minOfOrNull", "minOfWith", "minOfWithOrNull", "minWith", "minWithOrNull", "none", "onEach", "M", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/util/Map;", "onEachIndexed", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "index", "(Ljava/util/Map;Lkotlin/jvm/functions/Function2;)Ljava/util/Map;", "toList", "Lkotlin/Pair;", "kotlin-stdlib"}, xs="kotlin/collections/MapsKt")
class MapsKt___MapsKt
extends MapsKt__MapsKt {
    @NotNull
    public static final <K, V> List<Pair<K, V>> toList(@NotNull Map<? extends K, ? extends V> $this$toList) {
        Intrinsics.checkNotNullParameter($this$toList, "$this$toList");
        if ($this$toList.size() == 0) {
            return CollectionsKt.emptyList();
        }
        Iterator<Map.Entry<K, V>> iterator2 = $this$toList.entrySet().iterator();
        if (!iterator2.hasNext()) {
            return CollectionsKt.emptyList();
        }
        Map.Entry<K, V> first = iterator2.next();
        if (!iterator2.hasNext()) {
            Map.Entry<K, V> entry = first;
            boolean bl = false;
            return CollectionsKt.listOf(new Pair<K, V>(entry.getKey(), entry.getValue()));
        }
        ArrayList<Pair<K, V>> result = new ArrayList<Pair<K, V>>($this$toList.size());
        Map.Entry<K, V> entry = first;
        boolean bl = false;
        result.add(new Pair<K, V>(entry.getKey(), entry.getValue()));
        do {
            entry = iterator2.next();
            bl = false;
            result.add(new Pair<K, V>(entry.getKey(), entry.getValue()));
        } while (iterator2.hasNext());
        return result;
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public static final <K, V, R> List<R> flatMap(@NotNull Map<? extends K, ? extends V> $this$flatMap, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, ? extends Iterable<? extends R>> transform) {
        void $this$flatMapTo$iv;
        int $i$f$flatMap = 0;
        Intrinsics.checkNotNullParameter($this$flatMap, "$this$flatMap");
        Intrinsics.checkNotNullParameter(transform, "transform");
        Map<? extends K, ? extends V> map = $this$flatMap;
        Collection destination$iv = new ArrayList();
        boolean $i$f$flatMapTo = false;
        void var6_6 = $this$flatMapTo$iv;
        boolean bl = false;
        for (Map.Entry element$iv : var6_6.entrySet()) {
            Iterable<? extends R> list$iv = transform.invoke(element$iv);
            CollectionsKt.addAll(destination$iv, list$iv);
        }
        return (List)destination$iv;
    }

    /*
     * WARNING - void declaration
     */
    @SinceKotlin(version="1.4")
    @OverloadResolutionByLambdaReturnType
    @JvmName(name="flatMapSequence")
    @NotNull
    public static final <K, V, R> List<R> flatMapSequence(@NotNull Map<? extends K, ? extends V> $this$flatMap, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, ? extends Sequence<? extends R>> transform) {
        void $this$flatMapTo$iv;
        int $i$f$flatMapSequence = 0;
        Intrinsics.checkNotNullParameter($this$flatMap, "$this$flatMap");
        Intrinsics.checkNotNullParameter(transform, "transform");
        Map<? extends K, ? extends V> map = $this$flatMap;
        Collection destination$iv = new ArrayList();
        boolean $i$f$flatMapSequenceTo = false;
        void var6_6 = $this$flatMapTo$iv;
        boolean bl = false;
        for (Map.Entry element$iv : var6_6.entrySet()) {
            Sequence<? extends R> list$iv = transform.invoke(element$iv);
            CollectionsKt.addAll(destination$iv, list$iv);
        }
        return (List)destination$iv;
    }

    @NotNull
    public static final <K, V, R, C extends Collection<? super R>> C flatMapTo(@NotNull Map<? extends K, ? extends V> $this$flatMapTo, @NotNull C destination, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, ? extends Iterable<? extends R>> transform) {
        int $i$f$flatMapTo = 0;
        Intrinsics.checkNotNullParameter($this$flatMapTo, "$this$flatMapTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        Map<K, V> map = $this$flatMapTo;
        boolean bl = false;
        for (Map.Entry<K, V> entry : map.entrySet()) {
            Iterable<R> list = transform.invoke(entry);
            CollectionsKt.addAll(destination, list);
        }
        return destination;
    }

    @SinceKotlin(version="1.4")
    @OverloadResolutionByLambdaReturnType
    @JvmName(name="flatMapSequenceTo")
    @NotNull
    public static final <K, V, R, C extends Collection<? super R>> C flatMapSequenceTo(@NotNull Map<? extends K, ? extends V> $this$flatMapTo, @NotNull C destination, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, ? extends Sequence<? extends R>> transform) {
        int $i$f$flatMapSequenceTo = 0;
        Intrinsics.checkNotNullParameter($this$flatMapTo, "$this$flatMapTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        Map<K, V> map = $this$flatMapTo;
        boolean bl = false;
        for (Map.Entry<K, V> entry : map.entrySet()) {
            Sequence<R> list = transform.invoke(entry);
            CollectionsKt.addAll(destination, list);
        }
        return destination;
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public static final <K, V, R> List<R> map(@NotNull Map<? extends K, ? extends V> $this$map, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> transform) {
        void $this$mapTo$iv;
        int $i$f$map = 0;
        Intrinsics.checkNotNullParameter($this$map, "$this$map");
        Intrinsics.checkNotNullParameter(transform, "transform");
        Map<K, V> map = $this$map;
        Collection destination$iv = new ArrayList($this$map.size());
        boolean $i$f$mapTo = false;
        void var6_6 = $this$mapTo$iv;
        boolean bl = false;
        for (Map.Entry item$iv : var6_6.entrySet()) {
            destination$iv.add(transform.invoke(item$iv));
        }
        return (List)destination$iv;
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public static final <K, V, R> List<R> mapNotNull(@NotNull Map<? extends K, ? extends V> $this$mapNotNull, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> transform) {
        void $this$mapNotNullTo$iv;
        int $i$f$mapNotNull = 0;
        Intrinsics.checkNotNullParameter($this$mapNotNull, "$this$mapNotNull");
        Intrinsics.checkNotNullParameter(transform, "transform");
        Map<? extends K, ? extends V> map = $this$mapNotNull;
        Collection destination$iv = new ArrayList();
        boolean $i$f$mapNotNullTo = false;
        void $this$forEach$iv$iv = $this$mapNotNullTo$iv;
        boolean $i$f$forEach = false;
        void var8_8 = $this$forEach$iv$iv;
        boolean bl = false;
        Iterator iterator2 = var8_8.entrySet().iterator();
        while (iterator2.hasNext()) {
            R r;
            Map.Entry element$iv$iv;
            Map.Entry element$iv = element$iv$iv = iterator2.next();
            boolean bl2 = false;
            if (transform.invoke(element$iv) == null) continue;
            boolean bl3 = false;
            boolean bl4 = false;
            R it$iv = r;
            boolean bl5 = false;
            destination$iv.add(it$iv);
        }
        return (List)destination$iv;
    }

    @NotNull
    public static final <K, V, R, C extends Collection<? super R>> C mapNotNullTo(@NotNull Map<? extends K, ? extends V> $this$mapNotNullTo, @NotNull C destination, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> transform) {
        int $i$f$mapNotNullTo = 0;
        Intrinsics.checkNotNullParameter($this$mapNotNullTo, "$this$mapNotNullTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        Map<K, V> $this$forEach$iv = $this$mapNotNullTo;
        boolean $i$f$forEach = false;
        Map<K, V> map = $this$forEach$iv;
        boolean bl = false;
        Iterator<Map.Entry<K, V>> iterator2 = map.entrySet().iterator();
        while (iterator2.hasNext()) {
            R r;
            Map.Entry<? extends K, ? extends V> element$iv;
            Map.Entry<? extends K, ? extends V> element = element$iv = iterator2.next();
            boolean bl2 = false;
            if (transform.invoke(element) == null) continue;
            boolean bl3 = false;
            boolean bl4 = false;
            R it = r;
            boolean bl5 = false;
            destination.add(it);
        }
        return destination;
    }

    @NotNull
    public static final <K, V, R, C extends Collection<? super R>> C mapTo(@NotNull Map<? extends K, ? extends V> $this$mapTo, @NotNull C destination, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> transform) {
        int $i$f$mapTo = 0;
        Intrinsics.checkNotNullParameter($this$mapTo, "$this$mapTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        Map<K, V> map = $this$mapTo;
        boolean bl = false;
        for (Map.Entry<K, V> entry : map.entrySet()) {
            destination.add(transform.invoke(entry));
        }
        return destination;
    }

    public static final <K, V> boolean all(@NotNull Map<? extends K, ? extends V> $this$all, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, Boolean> predicate) {
        int $i$f$all = 0;
        Intrinsics.checkNotNullParameter($this$all, "$this$all");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        if ($this$all.isEmpty()) {
            return true;
        }
        Map<K, V> map = $this$all;
        boolean bl = false;
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (predicate.invoke(entry).booleanValue()) continue;
            return false;
        }
        return true;
    }

    public static final <K, V> boolean any(@NotNull Map<? extends K, ? extends V> $this$any) {
        Intrinsics.checkNotNullParameter($this$any, "$this$any");
        return !$this$any.isEmpty();
    }

    public static final <K, V> boolean any(@NotNull Map<? extends K, ? extends V> $this$any, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, Boolean> predicate) {
        int $i$f$any = 0;
        Intrinsics.checkNotNullParameter($this$any, "$this$any");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        if ($this$any.isEmpty()) {
            return false;
        }
        Map<K, V> map = $this$any;
        boolean bl = false;
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (!predicate.invoke(entry).booleanValue()) continue;
            return true;
        }
        return false;
    }

    @InlineOnly
    private static final <K, V> int count(Map<? extends K, ? extends V> $this$count) {
        int $i$f$count = 0;
        return $this$count.size();
    }

    public static final <K, V> int count(@NotNull Map<? extends K, ? extends V> $this$count, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, Boolean> predicate) {
        int $i$f$count = 0;
        Intrinsics.checkNotNullParameter($this$count, "$this$count");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        if ($this$count.isEmpty()) {
            return 0;
        }
        int count = 0;
        Map<K, V> map = $this$count;
        boolean bl = false;
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (!predicate.invoke(entry).booleanValue()) continue;
            ++count;
        }
        return count;
    }

    @HidesMembers
    public static final <K, V> void forEach(@NotNull Map<? extends K, ? extends V> $this$forEach, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, Unit> action) {
        int $i$f$forEach = 0;
        Intrinsics.checkNotNullParameter($this$forEach, "$this$forEach");
        Intrinsics.checkNotNullParameter(action, "action");
        Map<K, V> map = $this$forEach;
        boolean bl = false;
        for (Map.Entry<K, V> entry : map.entrySet()) {
            action.invoke(entry);
        }
    }

    @Deprecated(message="Use maxByOrNull instead.", replaceWith=@ReplaceWith(imports={}, expression="maxByOrNull(selector)"))
    @DeprecatedSinceKotlin(warningSince="1.4")
    @InlineOnly
    private static final <K, V, R extends Comparable<? super R>> Map.Entry<K, V> maxBy(Map<? extends K, ? extends V> $this$maxBy, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> selector) {
        Object v0;
        int $i$f$maxBy = 0;
        Map<K, V> map = $this$maxBy;
        boolean bl = false;
        Iterable iterable = map.entrySet();
        boolean bl2 = false;
        Iterator iterator2 = iterable.iterator();
        if (!iterator2.hasNext()) {
            v0 = null;
        } else {
            Object t = iterator2.next();
            if (!iterator2.hasNext()) {
                v0 = t;
            } else {
                Comparable comparable = (Comparable)selector.invoke((Map.Entry<K, V>)t);
                do {
                    Object t2;
                    Comparable comparable2;
                    if (comparable.compareTo(comparable2 = (Comparable)selector.invoke((Map.Entry<K, V>)(t2 = iterator2.next()))) >= 0) continue;
                    t = t2;
                    comparable = comparable2;
                } while (iterator2.hasNext());
                v0 = t;
            }
        }
        return v0;
    }

    @SinceKotlin(version="1.4")
    @InlineOnly
    private static final <K, V, R extends Comparable<? super R>> Map.Entry<K, V> maxByOrNull(Map<? extends K, ? extends V> $this$maxByOrNull, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> selector) {
        Object v0;
        int $i$f$maxByOrNull = 0;
        Iterable $this$maxByOrNull$iv = $this$maxByOrNull.entrySet();
        boolean $i$f$maxByOrNull2 = false;
        Iterator iterator$iv = $this$maxByOrNull$iv.iterator();
        if (!iterator$iv.hasNext()) {
            v0 = null;
        } else {
            Object maxElem$iv = iterator$iv.next();
            if (!iterator$iv.hasNext()) {
                v0 = maxElem$iv;
            } else {
                Comparable maxValue$iv = (Comparable)selector.invoke((Map.Entry<K, V>)maxElem$iv);
                do {
                    Object e$iv;
                    Comparable v$iv;
                    if (maxValue$iv.compareTo(v$iv = (Comparable)selector.invoke((Map.Entry<K, V>)(e$iv = iterator$iv.next()))) >= 0) continue;
                    maxElem$iv = e$iv;
                    maxValue$iv = v$iv;
                } while (iterator$iv.hasNext());
                v0 = maxElem$iv;
            }
        }
        return v0;
    }

    @SinceKotlin(version="1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <K, V> double maxOf(Map<? extends K, ? extends V> $this$maxOf, Function1<? super Map.Entry<? extends K, ? extends V>, Double> selector) {
        int $i$f$maxOf = 0;
        Iterable iterable = $this$maxOf.entrySet();
        boolean bl = false;
        Iterator iterator2 = iterable.iterator();
        if (!iterator2.hasNext()) {
            throw (Throwable)new NoSuchElementException();
        }
        double d = ((Number)selector.invoke((Map.Entry<K, V>)iterator2.next())).doubleValue();
        while (iterator2.hasNext()) {
            double d2 = ((Number)selector.invoke((Map.Entry<K, V>)iterator2.next())).doubleValue();
            boolean bl2 = false;
            d = Math.max(d, d2);
        }
        return d;
    }

    @SinceKotlin(version="1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <K, V> float maxOf(Map<? extends K, ? extends V> $this$maxOf, Function1<? super Map.Entry<? extends K, ? extends V>, Float> selector) {
        int $i$f$maxOf = 0;
        Iterable iterable = $this$maxOf.entrySet();
        boolean bl = false;
        Iterator iterator2 = iterable.iterator();
        if (!iterator2.hasNext()) {
            throw (Throwable)new NoSuchElementException();
        }
        float f = ((Number)selector.invoke((Map.Entry<K, V>)iterator2.next())).floatValue();
        while (iterator2.hasNext()) {
            float f2 = ((Number)selector.invoke((Map.Entry<K, V>)iterator2.next())).floatValue();
            boolean bl2 = false;
            f = Math.max(f, f2);
        }
        return f;
    }

    @SinceKotlin(version="1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <K, V, R extends Comparable<? super R>> R maxOf(Map<? extends K, ? extends V> $this$maxOf, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> selector) {
        int $i$f$maxOf = 0;
        Iterable iterable = $this$maxOf.entrySet();
        boolean bl = false;
        Iterator iterator2 = iterable.iterator();
        if (!iterator2.hasNext()) {
            throw (Throwable)new NoSuchElementException();
        }
        Comparable comparable = (Comparable)selector.invoke((Map.Entry<K, V>)iterator2.next());
        while (iterator2.hasNext()) {
            Comparable comparable2 = (Comparable)selector.invoke((Map.Entry<K, V>)iterator2.next());
            if (comparable.compareTo(comparable2) >= 0) continue;
            comparable = comparable2;
        }
        return (R)comparable;
    }

    @SinceKotlin(version="1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <K, V> Double maxOfOrNull(Map<? extends K, ? extends V> $this$maxOfOrNull, Function1<? super Map.Entry<? extends K, ? extends V>, Double> selector) {
        Double d;
        int $i$f$maxOfOrNull = 0;
        Iterable iterable = $this$maxOfOrNull.entrySet();
        boolean bl = false;
        Iterator iterator2 = iterable.iterator();
        if (!iterator2.hasNext()) {
            d = null;
        } else {
            double d2 = ((Number)selector.invoke((Map.Entry<K, V>)iterator2.next())).doubleValue();
            while (iterator2.hasNext()) {
                double d3 = ((Number)selector.invoke((Map.Entry<K, V>)iterator2.next())).doubleValue();
                boolean bl2 = false;
                d2 = Math.max(d2, d3);
            }
            d = d2;
        }
        return d;
    }

    @SinceKotlin(version="1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <K, V> Float maxOfOrNull(Map<? extends K, ? extends V> $this$maxOfOrNull, Function1<? super Map.Entry<? extends K, ? extends V>, Float> selector) {
        Float f;
        int $i$f$maxOfOrNull = 0;
        Iterable iterable = $this$maxOfOrNull.entrySet();
        boolean bl = false;
        Iterator iterator2 = iterable.iterator();
        if (!iterator2.hasNext()) {
            f = null;
        } else {
            float f2 = ((Number)selector.invoke((Map.Entry<K, V>)iterator2.next())).floatValue();
            while (iterator2.hasNext()) {
                float f3 = ((Number)selector.invoke((Map.Entry<K, V>)iterator2.next())).floatValue();
                boolean bl2 = false;
                f2 = Math.max(f2, f3);
            }
            f = Float.valueOf(f2);
        }
        return f;
    }

    @SinceKotlin(version="1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <K, V, R extends Comparable<? super R>> R maxOfOrNull(Map<? extends K, ? extends V> $this$maxOfOrNull, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> selector) {
        Comparable comparable;
        int $i$f$maxOfOrNull = 0;
        Iterable iterable = $this$maxOfOrNull.entrySet();
        boolean bl = false;
        Iterator iterator2 = iterable.iterator();
        if (!iterator2.hasNext()) {
            comparable = null;
        } else {
            Comparable comparable2 = (Comparable)selector.invoke((Map.Entry<K, V>)iterator2.next());
            while (iterator2.hasNext()) {
                Comparable comparable3 = (Comparable)selector.invoke((Map.Entry<K, V>)iterator2.next());
                if (comparable2.compareTo(comparable3) >= 0) continue;
                comparable2 = comparable3;
            }
            comparable = comparable2;
        }
        return (R)comparable;
    }

    @SinceKotlin(version="1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <K, V, R> R maxOfWith(Map<? extends K, ? extends V> $this$maxOfWith, Comparator<? super R> comparator, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> selector) {
        int $i$f$maxOfWith = 0;
        Iterable iterable = $this$maxOfWith.entrySet();
        boolean bl = false;
        Iterator iterator2 = iterable.iterator();
        if (!iterator2.hasNext()) {
            throw (Throwable)new NoSuchElementException();
        }
        R r = selector.invoke((Map.Entry<K, V>)iterator2.next());
        while (iterator2.hasNext()) {
            R r2 = selector.invoke((Map.Entry<K, V>)iterator2.next());
            if (comparator.compare(r, r2) >= 0) continue;
            r = r2;
        }
        return r;
    }

    @SinceKotlin(version="1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <K, V, R> R maxOfWithOrNull(Map<? extends K, ? extends V> $this$maxOfWithOrNull, Comparator<? super R> comparator, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> selector) {
        R r;
        int $i$f$maxOfWithOrNull = 0;
        Iterable iterable = $this$maxOfWithOrNull.entrySet();
        boolean bl = false;
        Iterator iterator2 = iterable.iterator();
        if (!iterator2.hasNext()) {
            r = null;
        } else {
            R r2 = selector.invoke((Map.Entry<K, V>)iterator2.next());
            while (iterator2.hasNext()) {
                R r3 = selector.invoke((Map.Entry<K, V>)iterator2.next());
                if (comparator.compare(r2, r3) >= 0) continue;
                r2 = r3;
            }
            r = r2;
        }
        return r;
    }

    @Deprecated(message="Use maxWithOrNull instead.", replaceWith=@ReplaceWith(imports={}, expression="maxWithOrNull(comparator)"))
    @DeprecatedSinceKotlin(warningSince="1.4")
    @InlineOnly
    private static final <K, V> Map.Entry<K, V> maxWith(Map<? extends K, ? extends V> $this$maxWith, Comparator<? super Map.Entry<? extends K, ? extends V>> comparator) {
        int $i$f$maxWith = 0;
        Map<K, V> map = $this$maxWith;
        boolean bl = false;
        return CollectionsKt.maxWithOrNull((Iterable)map.entrySet(), comparator);
    }

    @SinceKotlin(version="1.4")
    @InlineOnly
    private static final <K, V> Map.Entry<K, V> maxWithOrNull(Map<? extends K, ? extends V> $this$maxWithOrNull, Comparator<? super Map.Entry<? extends K, ? extends V>> comparator) {
        int $i$f$maxWithOrNull = 0;
        return CollectionsKt.maxWithOrNull((Iterable)$this$maxWithOrNull.entrySet(), comparator);
    }

    @Deprecated(message="Use minByOrNull instead.", replaceWith=@ReplaceWith(imports={}, expression="minByOrNull(selector)"))
    @DeprecatedSinceKotlin(warningSince="1.4")
    @Nullable
    public static final <K, V, R extends Comparable<? super R>> Map.Entry<K, V> minBy(@NotNull Map<? extends K, ? extends V> $this$minBy, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> selector) {
        Object v0;
        int $i$f$minBy = 0;
        Intrinsics.checkNotNullParameter($this$minBy, "$this$minBy");
        Intrinsics.checkNotNullParameter(selector, "selector");
        Map<K, V> map = $this$minBy;
        boolean bl = false;
        Iterable iterable = map.entrySet();
        boolean bl2 = false;
        Iterator iterator2 = iterable.iterator();
        if (!iterator2.hasNext()) {
            v0 = null;
        } else {
            Object t = iterator2.next();
            if (!iterator2.hasNext()) {
                v0 = t;
            } else {
                Comparable comparable = (Comparable)selector.invoke((Map.Entry<K, V>)t);
                do {
                    Object t2;
                    Comparable comparable2;
                    if (comparable.compareTo(comparable2 = (Comparable)selector.invoke((Map.Entry<K, V>)(t2 = iterator2.next()))) <= 0) continue;
                    t = t2;
                    comparable = comparable2;
                } while (iterator2.hasNext());
                v0 = t;
            }
        }
        return v0;
    }

    @SinceKotlin(version="1.4")
    @InlineOnly
    private static final <K, V, R extends Comparable<? super R>> Map.Entry<K, V> minByOrNull(Map<? extends K, ? extends V> $this$minByOrNull, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> selector) {
        Object v0;
        int $i$f$minByOrNull = 0;
        Iterable $this$minByOrNull$iv = $this$minByOrNull.entrySet();
        boolean $i$f$minByOrNull2 = false;
        Iterator iterator$iv = $this$minByOrNull$iv.iterator();
        if (!iterator$iv.hasNext()) {
            v0 = null;
        } else {
            Object minElem$iv = iterator$iv.next();
            if (!iterator$iv.hasNext()) {
                v0 = minElem$iv;
            } else {
                Comparable minValue$iv = (Comparable)selector.invoke((Map.Entry<K, V>)minElem$iv);
                do {
                    Object e$iv;
                    Comparable v$iv;
                    if (minValue$iv.compareTo(v$iv = (Comparable)selector.invoke((Map.Entry<K, V>)(e$iv = iterator$iv.next()))) <= 0) continue;
                    minElem$iv = e$iv;
                    minValue$iv = v$iv;
                } while (iterator$iv.hasNext());
                v0 = minElem$iv;
            }
        }
        return v0;
    }

    @SinceKotlin(version="1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <K, V> double minOf(Map<? extends K, ? extends V> $this$minOf, Function1<? super Map.Entry<? extends K, ? extends V>, Double> selector) {
        int $i$f$minOf = 0;
        Iterable iterable = $this$minOf.entrySet();
        boolean bl = false;
        Iterator iterator2 = iterable.iterator();
        if (!iterator2.hasNext()) {
            throw (Throwable)new NoSuchElementException();
        }
        double d = ((Number)selector.invoke((Map.Entry<K, V>)iterator2.next())).doubleValue();
        while (iterator2.hasNext()) {
            double d2 = ((Number)selector.invoke((Map.Entry<K, V>)iterator2.next())).doubleValue();
            boolean bl2 = false;
            d = Math.min(d, d2);
        }
        return d;
    }

    @SinceKotlin(version="1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <K, V> float minOf(Map<? extends K, ? extends V> $this$minOf, Function1<? super Map.Entry<? extends K, ? extends V>, Float> selector) {
        int $i$f$minOf = 0;
        Iterable iterable = $this$minOf.entrySet();
        boolean bl = false;
        Iterator iterator2 = iterable.iterator();
        if (!iterator2.hasNext()) {
            throw (Throwable)new NoSuchElementException();
        }
        float f = ((Number)selector.invoke((Map.Entry<K, V>)iterator2.next())).floatValue();
        while (iterator2.hasNext()) {
            float f2 = ((Number)selector.invoke((Map.Entry<K, V>)iterator2.next())).floatValue();
            boolean bl2 = false;
            f = Math.min(f, f2);
        }
        return f;
    }

    @SinceKotlin(version="1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <K, V, R extends Comparable<? super R>> R minOf(Map<? extends K, ? extends V> $this$minOf, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> selector) {
        int $i$f$minOf = 0;
        Iterable iterable = $this$minOf.entrySet();
        boolean bl = false;
        Iterator iterator2 = iterable.iterator();
        if (!iterator2.hasNext()) {
            throw (Throwable)new NoSuchElementException();
        }
        Comparable comparable = (Comparable)selector.invoke((Map.Entry<K, V>)iterator2.next());
        while (iterator2.hasNext()) {
            Comparable comparable2 = (Comparable)selector.invoke((Map.Entry<K, V>)iterator2.next());
            if (comparable.compareTo(comparable2) <= 0) continue;
            comparable = comparable2;
        }
        return (R)comparable;
    }

    @SinceKotlin(version="1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <K, V> Double minOfOrNull(Map<? extends K, ? extends V> $this$minOfOrNull, Function1<? super Map.Entry<? extends K, ? extends V>, Double> selector) {
        Double d;
        int $i$f$minOfOrNull = 0;
        Iterable iterable = $this$minOfOrNull.entrySet();
        boolean bl = false;
        Iterator iterator2 = iterable.iterator();
        if (!iterator2.hasNext()) {
            d = null;
        } else {
            double d2 = ((Number)selector.invoke((Map.Entry<K, V>)iterator2.next())).doubleValue();
            while (iterator2.hasNext()) {
                double d3 = ((Number)selector.invoke((Map.Entry<K, V>)iterator2.next())).doubleValue();
                boolean bl2 = false;
                d2 = Math.min(d2, d3);
            }
            d = d2;
        }
        return d;
    }

    @SinceKotlin(version="1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <K, V> Float minOfOrNull(Map<? extends K, ? extends V> $this$minOfOrNull, Function1<? super Map.Entry<? extends K, ? extends V>, Float> selector) {
        Float f;
        int $i$f$minOfOrNull = 0;
        Iterable iterable = $this$minOfOrNull.entrySet();
        boolean bl = false;
        Iterator iterator2 = iterable.iterator();
        if (!iterator2.hasNext()) {
            f = null;
        } else {
            float f2 = ((Number)selector.invoke((Map.Entry<K, V>)iterator2.next())).floatValue();
            while (iterator2.hasNext()) {
                float f3 = ((Number)selector.invoke((Map.Entry<K, V>)iterator2.next())).floatValue();
                boolean bl2 = false;
                f2 = Math.min(f2, f3);
            }
            f = Float.valueOf(f2);
        }
        return f;
    }

    @SinceKotlin(version="1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <K, V, R extends Comparable<? super R>> R minOfOrNull(Map<? extends K, ? extends V> $this$minOfOrNull, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> selector) {
        Comparable comparable;
        int $i$f$minOfOrNull = 0;
        Iterable iterable = $this$minOfOrNull.entrySet();
        boolean bl = false;
        Iterator iterator2 = iterable.iterator();
        if (!iterator2.hasNext()) {
            comparable = null;
        } else {
            Comparable comparable2 = (Comparable)selector.invoke((Map.Entry<K, V>)iterator2.next());
            while (iterator2.hasNext()) {
                Comparable comparable3 = (Comparable)selector.invoke((Map.Entry<K, V>)iterator2.next());
                if (comparable2.compareTo(comparable3) <= 0) continue;
                comparable2 = comparable3;
            }
            comparable = comparable2;
        }
        return (R)comparable;
    }

    @SinceKotlin(version="1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <K, V, R> R minOfWith(Map<? extends K, ? extends V> $this$minOfWith, Comparator<? super R> comparator, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> selector) {
        int $i$f$minOfWith = 0;
        Iterable iterable = $this$minOfWith.entrySet();
        boolean bl = false;
        Iterator iterator2 = iterable.iterator();
        if (!iterator2.hasNext()) {
            throw (Throwable)new NoSuchElementException();
        }
        R r = selector.invoke((Map.Entry<K, V>)iterator2.next());
        while (iterator2.hasNext()) {
            R r2 = selector.invoke((Map.Entry<K, V>)iterator2.next());
            if (comparator.compare(r, r2) <= 0) continue;
            r = r2;
        }
        return r;
    }

    @SinceKotlin(version="1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <K, V, R> R minOfWithOrNull(Map<? extends K, ? extends V> $this$minOfWithOrNull, Comparator<? super R> comparator, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> selector) {
        R r;
        int $i$f$minOfWithOrNull = 0;
        Iterable iterable = $this$minOfWithOrNull.entrySet();
        boolean bl = false;
        Iterator iterator2 = iterable.iterator();
        if (!iterator2.hasNext()) {
            r = null;
        } else {
            R r2 = selector.invoke((Map.Entry<K, V>)iterator2.next());
            while (iterator2.hasNext()) {
                R r3 = selector.invoke((Map.Entry<K, V>)iterator2.next());
                if (comparator.compare(r2, r3) <= 0) continue;
                r2 = r3;
            }
            r = r2;
        }
        return r;
    }

    @Deprecated(message="Use minWithOrNull instead.", replaceWith=@ReplaceWith(imports={}, expression="minWithOrNull(comparator)"))
    @DeprecatedSinceKotlin(warningSince="1.4")
    @Nullable
    public static final <K, V> Map.Entry<K, V> minWith(@NotNull Map<? extends K, ? extends V> $this$minWith, @NotNull Comparator<? super Map.Entry<? extends K, ? extends V>> comparator) {
        Intrinsics.checkNotNullParameter($this$minWith, "$this$minWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Map<K, V> map = $this$minWith;
        boolean bl = false;
        return CollectionsKt.minWithOrNull((Iterable)map.entrySet(), comparator);
    }

    @SinceKotlin(version="1.4")
    @InlineOnly
    private static final <K, V> Map.Entry<K, V> minWithOrNull(Map<? extends K, ? extends V> $this$minWithOrNull, Comparator<? super Map.Entry<? extends K, ? extends V>> comparator) {
        int $i$f$minWithOrNull = 0;
        return CollectionsKt.minWithOrNull((Iterable)$this$minWithOrNull.entrySet(), comparator);
    }

    public static final <K, V> boolean none(@NotNull Map<? extends K, ? extends V> $this$none) {
        Intrinsics.checkNotNullParameter($this$none, "$this$none");
        return $this$none.isEmpty();
    }

    public static final <K, V> boolean none(@NotNull Map<? extends K, ? extends V> $this$none, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, Boolean> predicate) {
        int $i$f$none = 0;
        Intrinsics.checkNotNullParameter($this$none, "$this$none");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        if ($this$none.isEmpty()) {
            return true;
        }
        Map<K, V> map = $this$none;
        boolean bl = false;
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (!predicate.invoke(entry).booleanValue()) continue;
            return false;
        }
        return true;
    }

    @SinceKotlin(version="1.1")
    @NotNull
    public static final <K, V, M extends Map<? extends K, ? extends V>> M onEach(@NotNull M $this$onEach, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, Unit> action) {
        int $i$f$onEach = 0;
        Intrinsics.checkNotNullParameter($this$onEach, "$this$onEach");
        Intrinsics.checkNotNullParameter(action, "action");
        M m3 = $this$onEach;
        boolean bl = false;
        boolean bl2 = false;
        M $this$apply = m3;
        boolean bl3 = false;
        M m4 = $this$apply;
        boolean bl4 = false;
        for (Map.Entry<? extends K, ? extends V> entry : m4.entrySet()) {
            action.invoke(entry);
        }
        return m3;
    }

    @SinceKotlin(version="1.4")
    @NotNull
    public static final <K, V, M extends Map<? extends K, ? extends V>> M onEachIndexed(@NotNull M $this$onEachIndexed, @NotNull Function2<? super Integer, ? super Map.Entry<? extends K, ? extends V>, Unit> action) {
        int $i$f$onEachIndexed = 0;
        Intrinsics.checkNotNullParameter($this$onEachIndexed, "$this$onEachIndexed");
        Intrinsics.checkNotNullParameter(action, "action");
        M m3 = $this$onEachIndexed;
        boolean bl = false;
        boolean bl2 = false;
        M $this$apply = m3;
        boolean bl3 = false;
        Iterable $this$forEachIndexed$iv = $this$apply.entrySet();
        boolean $i$f$forEachIndexed = false;
        int index$iv = 0;
        for (Object item$iv : $this$forEachIndexed$iv) {
            int n = index$iv++;
            boolean bl4 = false;
            if (n < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            action.invoke((Integer)((Integer)Integer.valueOf(n)), (Map.Entry<K, V>)item$iv);
        }
        return m3;
    }

    @InlineOnly
    private static final <K, V> Iterable<Map.Entry<K, V>> asIterable(Map<? extends K, ? extends V> $this$asIterable) {
        int $i$f$asIterable = 0;
        return $this$asIterable.entrySet();
    }

    @NotNull
    public static final <K, V> Sequence<Map.Entry<K, V>> asSequence(@NotNull Map<? extends K, ? extends V> $this$asSequence) {
        Intrinsics.checkNotNullParameter($this$asSequence, "$this$asSequence");
        return CollectionsKt.asSequence((Iterable)$this$asSequence.entrySet());
    }
}

