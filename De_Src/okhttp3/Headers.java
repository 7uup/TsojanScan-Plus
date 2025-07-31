/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
 */
package okhttp3;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import okhttp3.internal.Util;
import okhttp3.internal.http.DatesKt;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010 \n\u0002\b\u0006\u0018\u0000 '2\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00020\u0001:\u0002&'B\u0015\b\u0002\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u000b\u001a\u00020\fJ\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0096\u0002J\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0012\u001a\u00020\u0003H\u0086\u0002J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0012\u001a\u00020\u0003J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0012\u001a\u00020\u0003H\u0007J\b\u0010\u0017\u001a\u00020\tH\u0016J\u001b\u0010\u0018\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00020\u0019H\u0096\u0002J\u000e\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\tJ\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00030\u001cJ\u0006\u0010\u001d\u001a\u00020\u001eJ\r\u0010\b\u001a\u00020\tH\u0007\u00a2\u0006\u0002\b\u001fJ\u0018\u0010 \u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\"0!J\b\u0010#\u001a\u00020\u0003H\u0016J\u000e\u0010$\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\tJ\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00030\"2\u0006\u0010\u0012\u001a\u00020\u0003R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0007R\u0011\u0010\b\u001a\u00020\t8G\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\n\u00a8\u0006("}, d2={"Lokhttp3/Headers;", "", "Lkotlin/Pair;", "", "namesAndValues", "", "([Ljava/lang/String;)V", "[Ljava/lang/String;", "size", "", "()I", "byteCount", "", "equals", "", "other", "", "get", "name", "getDate", "Ljava/util/Date;", "getInstant", "Ljava/time/Instant;", "hashCode", "iterator", "", "index", "names", "", "newBuilder", "Lokhttp3/Headers$Builder;", "-deprecated_size", "toMultimap", "", "", "toString", "value", "values", "Builder", "Companion", "okhttp"})
public final class Headers
implements Iterable<Pair<? extends String, ? extends String>>,
KMappedMarker {
    private final String[] namesAndValues;
    public static final Companion Companion = new Companion(null);

    @Nullable
    public final String get(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return Headers.Companion.get(this.namesAndValues, name);
    }

    @Nullable
    public final Date getDate(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        String string = this.get(name);
        return string != null ? DatesKt.toHttpDateOrNull(string) : null;
    }

    @IgnoreJRERequirement
    @Nullable
    public final Instant getInstant(@NotNull String name) {
        Date value;
        Intrinsics.checkNotNullParameter(name, "name");
        Date date = value = this.getDate(name);
        return date != null ? date.toInstant() : null;
    }

    @JvmName(name="size")
    public final int size() {
        return this.namesAndValues.length / 2;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="size"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_size")
    public final int -deprecated_size() {
        return this.size();
    }

    @NotNull
    public final String name(int index) {
        return this.namesAndValues[index * 2];
    }

    @NotNull
    public final String value(int index) {
        return this.namesAndValues[index * 2 + 1];
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public final Set<String> names() {
        TreeSet<String> result = new TreeSet<String>(StringsKt.getCASE_INSENSITIVE_ORDER(StringCompanionObject.INSTANCE));
        int n = 0;
        int n2 = this.size();
        while (n < n2) {
            void i;
            result.add(this.name((int)i));
            ++i;
        }
        Set<String> set = Collections.unmodifiableSet((Set)result);
        Intrinsics.checkNotNullExpressionValue(set, "Collections.unmodifiableSet(result)");
        return set;
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public final List<String> values(@NotNull String name) {
        List<String> list;
        Intrinsics.checkNotNullParameter(name, "name");
        List result = null;
        int n = 0;
        int n2 = this.size();
        while (n < n2) {
            void i;
            if (StringsKt.equals(name, this.name((int)i), true)) {
                if (result == null) {
                    result = new ArrayList(2);
                }
                result.add(this.value((int)i));
            }
            ++i;
        }
        if (result != null) {
            List list2 = Collections.unmodifiableList(result);
            list = list2;
            Intrinsics.checkNotNullExpressionValue(list2, "Collections.unmodifiableList(result)");
        } else {
            list = CollectionsKt.emptyList();
        }
        return list;
    }

    /*
     * WARNING - void declaration
     */
    public final long byteCount() {
        long result = this.namesAndValues.length * 2;
        int n = 0;
        int n2 = this.namesAndValues.length;
        while (n < n2) {
            void i;
            result += (long)this.namesAndValues[i].length();
            ++i;
        }
        return result;
    }

    /*
     * WARNING - void declaration
     */
    @Override
    @NotNull
    public Iterator<Pair<String, String>> iterator() {
        int n = this.size();
        Pair[] pairArray = new Pair[n];
        int n2 = 0;
        while (n2 < n) {
            void it;
            Pair<String, String> pair;
            int n3 = n2;
            int n4 = n2++;
            Pair[] pairArray2 = pairArray;
            boolean bl = false;
            pairArray2[n4] = pair = TuplesKt.to(this.name((int)it), this.value((int)it));
        }
        return ArrayIteratorKt.iterator(pairArray);
    }

    @NotNull
    public final Builder newBuilder() {
        Builder result = new Builder();
        Collection collection = result.getNamesAndValues$okhttp();
        String[] stringArray = this.namesAndValues;
        boolean bl = false;
        CollectionsKt.addAll(collection, stringArray);
        return result;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean equals(@Nullable Object other) {
        if (!(other instanceof Headers)) return false;
        Object[] objectArray = this.namesAndValues;
        Object[] objectArray2 = ((Headers)other).namesAndValues;
        boolean bl = false;
        if (!Arrays.equals(objectArray, objectArray2)) return false;
        return true;
    }

    public int hashCode() {
        Object[] objectArray = this.namesAndValues;
        boolean bl = false;
        return Arrays.hashCode(objectArray);
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public String toString() {
        boolean bl = false;
        boolean bl2 = false;
        StringBuilder stringBuilder = new StringBuilder();
        boolean bl3 = false;
        boolean bl4 = false;
        StringBuilder $this$buildString = stringBuilder;
        boolean bl5 = false;
        int n = 0;
        int n2 = this.size();
        while (n < n2) {
            void i;
            String name = this.name((int)i);
            String value = this.value((int)i);
            $this$buildString.append(name);
            $this$buildString.append(": ");
            $this$buildString.append(Util.isSensitiveHeader(name) ? "\u2588\u2588" : value);
            $this$buildString.append("\n");
            ++i;
        }
        String string = stringBuilder.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public final Map<String, List<String>> toMultimap() {
        TreeMap result = new TreeMap(StringsKt.getCASE_INSENSITIVE_ORDER(StringCompanionObject.INSTANCE));
        int n = 0;
        int n2 = this.size();
        while (n < n2) {
            String name;
            Locale locale;
            void i;
            String string = this.name((int)i);
            Intrinsics.checkNotNullExpressionValue(Locale.US, "Locale.US");
            boolean bl = false;
            String string2 = string;
            if (string2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            Intrinsics.checkNotNullExpressionValue(string2.toLowerCase(locale), "(this as java.lang.String).toLowerCase(locale)");
            List values2 = (List)result.get(name);
            if (values2 == null) {
                values2 = new ArrayList(2);
                ((Map)result).put(name, values2);
            }
            values2.add(this.value((int)i));
            ++i;
        }
        return result;
    }

    private Headers(String[] namesAndValues) {
        this.namesAndValues = namesAndValues;
    }

    public /* synthetic */ Headers(String[] namesAndValues, DefaultConstructorMarker $constructor_marker) {
        this(namesAndValues);
    }

    @JvmStatic
    @JvmName(name="of")
    @NotNull
    public static final Headers of(@NotNull String ... namesAndValues) {
        return Companion.of(namesAndValues);
    }

    @JvmStatic
    @JvmName(name="of")
    @NotNull
    public static final Headers of(@NotNull Map<String, String> $this$toHeaders) {
        return Companion.of($this$toHeaders);
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0005J\u0018\u0010\b\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0016\u0010\b\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\rJ\u0016\u0010\b\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005J\u000e\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0010J\u0015\u0010\u0011\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0005H\u0000\u00a2\u0006\u0002\b\u0012J\u001d\u0010\u0011\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0000\u00a2\u0006\u0002\b\u0012J\u0016\u0010\u0013\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005J\u0006\u0010\u0014\u001a\u00020\u0010J\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u00052\u0006\u0010\n\u001a\u00020\u0005H\u0086\u0002J\u000e\u0010\u0016\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0005J\u0019\u0010\u0017\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0087\u0002J\u0019\u0010\u0017\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\rH\u0086\u0002J\u0019\u0010\u0017\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0086\u0002R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0018"}, d2={"Lokhttp3/Headers$Builder;", "", "()V", "namesAndValues", "", "", "getNamesAndValues$okhttp", "()Ljava/util/List;", "add", "line", "name", "value", "Ljava/time/Instant;", "Ljava/util/Date;", "addAll", "headers", "Lokhttp3/Headers;", "addLenient", "addLenient$okhttp", "addUnsafeNonAscii", "build", "get", "removeAll", "set", "okhttp"})
    public static final class Builder {
        @NotNull
        private final List<String> namesAndValues = new ArrayList(20);

        @NotNull
        public final List<String> getNamesAndValues$okhttp() {
            return this.namesAndValues;
        }

        @NotNull
        public final Builder addLenient$okhttp(@NotNull String line) {
            Intrinsics.checkNotNullParameter(line, "line");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            int index = StringsKt.indexOf$default((CharSequence)line, ':', 1, false, 4, null);
            if (index != -1) {
                String string = line;
                int n = 0;
                boolean bl4 = false;
                String string2 = string.substring(n, index);
                Intrinsics.checkNotNullExpressionValue(string2, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                string = line;
                n = index + 1;
                bl4 = false;
                String string3 = string.substring(n);
                Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.String).substring(startIndex)");
                $this$apply.addLenient$okhttp(string2, string3);
            } else if (line.charAt(0) == ':') {
                String string = line;
                int n = 1;
                boolean bl5 = false;
                String string4 = string.substring(n);
                Intrinsics.checkNotNullExpressionValue(string4, "(this as java.lang.String).substring(startIndex)");
                $this$apply.addLenient$okhttp("", string4);
            } else {
                $this$apply.addLenient$okhttp("", line);
            }
            return builder;
        }

        @NotNull
        public final Builder add(@NotNull String line) {
            Intrinsics.checkNotNullParameter(line, "line");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            int index = StringsKt.indexOf$default((CharSequence)line, ':', 0, false, 6, null);
            boolean bl4 = index != -1;
            int n = 0;
            boolean bl5 = false;
            if (!bl4) {
                boolean bl6 = false;
                String string = "Unexpected header: " + line;
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            String string = line;
            n = 0;
            bl5 = false;
            String string2 = string.substring(n, index);
            Intrinsics.checkNotNullExpressionValue(string2, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
            string = string2;
            n = 0;
            String string3 = string;
            if (string3 == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
            }
            String string4 = ((Object)StringsKt.trim((CharSequence)string3)).toString();
            string = line;
            n = index + 1;
            bl5 = false;
            String string5 = string.substring(n);
            Intrinsics.checkNotNullExpressionValue(string5, "(this as java.lang.String).substring(startIndex)");
            $this$apply.add(string4, string5);
            return builder;
        }

        @NotNull
        public final Builder add(@NotNull String name, @NotNull String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            Companion.checkName(name);
            Companion.checkValue(value, name);
            $this$apply.addLenient$okhttp(name, value);
            return builder;
        }

        @NotNull
        public final Builder addUnsafeNonAscii(@NotNull String name, @NotNull String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            Companion.checkName(name);
            $this$apply.addLenient$okhttp(name, value);
            return builder;
        }

        /*
         * WARNING - void declaration
         */
        @NotNull
        public final Builder addAll(@NotNull Headers headers) {
            Intrinsics.checkNotNullParameter(headers, "headers");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            int n = 0;
            int n2 = headers.size();
            while (n < n2) {
                void i;
                $this$apply.addLenient$okhttp(headers.name((int)i), headers.value((int)i));
                ++i;
            }
            return builder;
        }

        @NotNull
        public final Builder add(@NotNull String name, @NotNull Date value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.add(name, DatesKt.toHttpDateString(value));
            return builder;
        }

        @IgnoreJRERequirement
        @NotNull
        public final Builder add(@NotNull String name, @NotNull Instant value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.add(name, new Date(value.toEpochMilli()));
            return builder;
        }

        @NotNull
        public final Builder set(@NotNull String name, @NotNull Date value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.set(name, DatesKt.toHttpDateString(value));
            return builder;
        }

        @IgnoreJRERequirement
        @NotNull
        public final Builder set(@NotNull String name, @NotNull Instant value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            return $this$apply.set(name, new Date(value.toEpochMilli()));
        }

        @NotNull
        public final Builder addLenient$okhttp(@NotNull String name, @NotNull String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.namesAndValues.add(name);
            String string = value;
            boolean bl4 = false;
            $this$apply.namesAndValues.add(((Object)StringsKt.trim((CharSequence)string)).toString());
            return builder;
        }

        @NotNull
        public final Builder removeAll(@NotNull String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            for (int i = 0; i < $this$apply.namesAndValues.size(); i += 2) {
                if (!StringsKt.equals(name, $this$apply.namesAndValues.get(i), true)) continue;
                $this$apply.namesAndValues.remove(i);
                $this$apply.namesAndValues.remove(i);
                i -= 2;
            }
            return builder;
        }

        @NotNull
        public final Builder set(@NotNull String name, @NotNull String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            Companion.checkName(name);
            Companion.checkValue(value, name);
            $this$apply.removeAll(name);
            $this$apply.addLenient$okhttp(name, value);
            return builder;
        }

        /*
         * WARNING - void declaration
         */
        @Nullable
        public final String get(@NotNull String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            IntProgression intProgression = RangesKt.step(RangesKt.downTo(this.namesAndValues.size() - 2, 0), 2);
            int n = intProgression.getFirst();
            int n2 = intProgression.getLast();
            int n3 = intProgression.getStep();
            int n4 = n;
            int n5 = n2;
            if (n3 >= 0 ? n4 <= n5 : n4 >= n5) {
                while (true) {
                    void i;
                    if (StringsKt.equals(name, this.namesAndValues.get((int)i), true)) {
                        return this.namesAndValues.get((int)(i + true));
                    }
                    if (i == n2) break;
                    n = i + n3;
                }
            }
            return null;
        }

        @NotNull
        public final Headers build() {
            Collection $this$toTypedArray$iv = this.namesAndValues;
            boolean $i$f$toTypedArray = false;
            Collection thisCollection$iv = $this$toTypedArray$iv;
            String[] stringArray = thisCollection$iv.toArray(new String[0]);
            if (stringArray == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
            }
            return new Headers(stringArray, null);
        }
    }

    /*
     * Illegal identifiers - consider using --renameillegalidents true
     */
    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0018\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J%\u0010\t\u001a\u0004\u0018\u00010\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u000b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002\u00a2\u0006\u0002\u0010\fJ#\u0010\r\u001a\u00020\u000e2\u0012\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u000b\"\u00020\u0006H\u0007\u00a2\u0006\u0004\b\u000f\u0010\u0010J#\u0010\u000f\u001a\u00020\u000e2\u0012\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u000b\"\u00020\u0006H\u0007\u00a2\u0006\u0004\b\u0011\u0010\u0010J!\u0010\u000f\u001a\u00020\u000e2\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0013H\u0007\u00a2\u0006\u0002\b\u0011J\u001d\u0010\u0014\u001a\u00020\u000e*\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0013H\u0007\u00a2\u0006\u0002\b\u000f\u00a8\u0006\u0015"}, d2={"Lokhttp3/Headers$Companion;", "", "()V", "checkName", "", "name", "", "checkValue", "value", "get", "namesAndValues", "", "([Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "headersOf", "Lokhttp3/Headers;", "of", "([Ljava/lang/String;)Lokhttp3/Headers;", "-deprecated_of", "headers", "", "toHeaders", "okhttp"})
    public static final class Companion {
        /*
         * WARNING - void declaration
         */
        private final String get(String[] namesAndValues, String name) {
            IntProgression intProgression = RangesKt.step(RangesKt.downTo(namesAndValues.length - 2, 0), 2);
            int n = intProgression.getFirst();
            int n2 = intProgression.getLast();
            int n3 = intProgression.getStep();
            int n4 = n;
            int n5 = n2;
            if (n3 >= 0 ? n4 <= n5 : n4 >= n5) {
                while (true) {
                    void i;
                    if (StringsKt.equals(name, namesAndValues[i], true)) {
                        return namesAndValues[i + true];
                    }
                    if (i == n2) break;
                    n = i + n3;
                }
            }
            return null;
        }

        @JvmStatic
        @JvmName(name="of")
        @NotNull
        public final Headers of(@NotNull String ... namesAndValues) {
            int i;
            Intrinsics.checkNotNullParameter(namesAndValues, "namesAndValues");
            boolean bl = namesAndValues.length % 2 == 0;
            int n = 0;
            int n2 = 0;
            if (!bl) {
                boolean bl2 = false;
                String string = "Expected alternating header names and values";
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            Object object = namesAndValues.clone();
            if (object == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.String>");
            }
            String[] namesAndValues2 = (String[])object;
            n = 0;
            n2 = namesAndValues2.length;
            while (n < n2) {
                boolean bl3 = namesAndValues2[i] != null;
                boolean bl4 = false;
                boolean bl5 = false;
                if (!bl3) {
                    boolean bl6 = false;
                    String string = "Headers cannot be null";
                    throw (Throwable)new IllegalArgumentException(string.toString());
                }
                String string = namesAndValues2[i];
                bl4 = false;
                String string2 = string;
                if (string2 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
                }
                namesAndValues2[i] = ((Object)StringsKt.trim((CharSequence)string2)).toString();
                ++i;
            }
            IntProgression intProgression = RangesKt.step(ArraysKt.getIndices(namesAndValues2), 2);
            i = intProgression.getFirst();
            n2 = intProgression.getLast();
            int n3 = intProgression.getStep();
            int n4 = i;
            int n5 = n2;
            if (n3 >= 0 ? n4 <= n5 : n4 >= n5) {
                while (true) {
                    String name = namesAndValues2[i];
                    String value = namesAndValues2[i + 1];
                    this.checkName(name);
                    this.checkValue(value, name);
                    if (i == n2) break;
                    n = i + n3;
                }
            }
            return new Headers(namesAndValues2, null);
        }

        @Deprecated(message="function name changed", replaceWith=@ReplaceWith(imports={}, expression="headersOf(*namesAndValues)"), level=DeprecationLevel.ERROR)
        @JvmName(name="-deprecated_of")
        @NotNull
        public final Headers -deprecated_of(@NotNull String ... namesAndValues) {
            Intrinsics.checkNotNullParameter(namesAndValues, "namesAndValues");
            return this.of(Arrays.copyOf(namesAndValues, namesAndValues.length));
        }

        /*
         * WARNING - void declaration
         */
        @JvmStatic
        @JvmName(name="of")
        @NotNull
        public final Headers of(@NotNull Map<String, String> $this$toHeaders) {
            Intrinsics.checkNotNullParameter($this$toHeaders, "$this$toHeaders");
            String[] namesAndValues = new String[$this$toHeaders.size() * 2];
            int i = 0;
            Object object = $this$toHeaders;
            boolean bl = false;
            Iterator<Map.Entry<String, String>> iterator2 = object.entrySet().iterator();
            while (iterator2.hasNext()) {
                void k;
                Map.Entry<String, String> entry;
                Map.Entry<String, String> entry2 = entry = iterator2.next();
                boolean bl2 = false;
                object = entry2.getKey();
                entry2 = entry;
                bl2 = false;
                String v = entry2.getValue();
                void var9_10 = k;
                boolean bl3 = false;
                void v0 = var9_10;
                if (v0 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
                }
                String name = ((Object)StringsKt.trim((CharSequence)v0)).toString();
                String string = v;
                boolean bl4 = false;
                String string2 = string;
                if (string2 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
                }
                String value = ((Object)StringsKt.trim((CharSequence)string2)).toString();
                this.checkName(name);
                this.checkValue(value, name);
                namesAndValues[i] = name;
                namesAndValues[i + 1] = value;
                i += 2;
            }
            return new Headers(namesAndValues, null);
        }

        @Deprecated(message="function moved to extension", replaceWith=@ReplaceWith(imports={}, expression="headers.toHeaders()"), level=DeprecationLevel.ERROR)
        @JvmName(name="-deprecated_of")
        @NotNull
        public final Headers -deprecated_of(@NotNull Map<String, String> headers) {
            Intrinsics.checkNotNullParameter(headers, "headers");
            return this.of(headers);
        }

        /*
         * WARNING - void declaration
         */
        private final void checkName(String name) {
            CharSequence charSequence = name;
            int n = 0;
            int n2 = charSequence.length() > 0 ? 1 : 0;
            n = 0;
            boolean bl = false;
            if (n2 == 0) {
                boolean bl2 = false;
                String string = "name is empty";
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            n2 = 0;
            n = ((CharSequence)name).length();
            while (n2 < n) {
                void i;
                char c = name.charAt((int)i);
                char c2 = c;
                c2 = '!' <= c2 && '~' >= c2 ? (char)'\u0001' : '\u0000';
                boolean bl3 = false;
                boolean bl4 = false;
                if (c2 == '\u0000') {
                    boolean bl5 = false;
                    String string = Util.format("Unexpected char %#04x at %d in header name: %s", c, (int)i, name);
                    throw (Throwable)new IllegalArgumentException(string.toString());
                }
                ++i;
            }
        }

        /*
         * WARNING - void declaration
         */
        private final void checkValue(String value, String name) {
            int n = 0;
            int n2 = ((CharSequence)value).length();
            while (n < n2) {
                char c;
                void i;
                char c2 = value.charAt((int)i);
                c = c2 == '\t' || ' ' <= (c = c2) && '~' >= c ? (char)'\u0001' : '\u0000';
                boolean bl = false;
                boolean bl2 = false;
                if (c == '\u0000') {
                    boolean bl3 = false;
                    String string = Util.format("Unexpected char %#04x at %d in %s value", c2, (int)i, name) + (Util.isSensitiveHeader(name) ? "" : ": " + value);
                    throw (Throwable)new IllegalArgumentException(string.toString());
                }
                ++i;
            }
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

