/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.publicsuffix;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.IDN;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import okhttp3.internal.Util;
import okio.BufferedSource;
import okio.GzipSource;
import okio.Okio;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001c\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0002J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000f\u001a\u00020\fJ\b\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0011H\u0002J\u0016\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006J\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u000f\u001a\u00020\fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2={"Lokhttp3/internal/publicsuffix/PublicSuffixDatabase;", "", "()V", "listRead", "Ljava/util/concurrent/atomic/AtomicBoolean;", "publicSuffixExceptionListBytes", "", "publicSuffixListBytes", "readCompleteLatch", "Ljava/util/concurrent/CountDownLatch;", "findMatchingRule", "", "", "domainLabels", "getEffectiveTldPlusOne", "domain", "readTheList", "", "readTheListUninterruptibly", "setListBytes", "splitDomain", "Companion", "okhttp"})
public final class PublicSuffixDatabase {
    private final AtomicBoolean listRead = new AtomicBoolean(false);
    private final CountDownLatch readCompleteLatch = new CountDownLatch(1);
    private byte[] publicSuffixListBytes;
    private byte[] publicSuffixExceptionListBytes;
    @NotNull
    public static final String PUBLIC_SUFFIX_RESOURCE = "publicsuffixes.gz";
    private static final byte[] WILDCARD_LABEL;
    private static final List<String> PREVAILING_RULE;
    private static final char EXCEPTION_MARKER = '!';
    private static final PublicSuffixDatabase instance;
    public static final Companion Companion;

    @Nullable
    public final String getEffectiveTldPlusOne(@NotNull String domain) {
        String unicodeDomain;
        Intrinsics.checkNotNullParameter(domain, "domain");
        String string = unicodeDomain = IDN.toUnicode(domain);
        Intrinsics.checkNotNullExpressionValue(string, "unicodeDomain");
        List<String> domainLabels = this.splitDomain(string);
        List<String> rule = this.findMatchingRule(domainLabels);
        if (domainLabels.size() == rule.size() && rule.get(0).charAt(0) != '!') {
            return null;
        }
        int firstLabelOffset = rule.get(0).charAt(0) == '!' ? domainLabels.size() - rule.size() : domainLabels.size() - (rule.size() + 1);
        return SequencesKt.joinToString$default(SequencesKt.drop(CollectionsKt.asSequence((Iterable)this.splitDomain(domain)), firstLabelOffset), ".", null, null, 0, null, null, 62, null);
    }

    private final List<String> splitDomain(String domain) {
        List domainLabels = StringsKt.split$default((CharSequence)domain, new char[]{'.'}, false, 0, 6, null);
        if (Intrinsics.areEqual((String)CollectionsKt.last(domainLabels), "")) {
            return CollectionsKt.dropLast(domainLabels, 1);
        }
        return domainLabels;
    }

    /*
     * WARNING - void declaration
     */
    private final List<String> findMatchingRule(List<String> domainLabels) {
        Object object;
        Object exactRuleLabels;
        String rule;
        int n;
        if (!this.listRead.get() && this.listRead.compareAndSet(false, true)) {
            this.readTheListUninterruptibly();
        } else {
            try {
                this.readCompleteLatch.await();
            } catch (InterruptedException _) {
                Thread.currentThread().interrupt();
            }
        }
        boolean _ = this.publicSuffixListBytes != null;
        int n2 = 0;
        boolean bl = false;
        if (!_) {
            boolean bl2 = false;
            String string = "Unable to load publicsuffixes.gz resource from the classpath.";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        n2 = domainLabels.size();
        byte[][] byArrayArray = new byte[n2][];
        int bl2 = 0;
        while (bl2 < n2) {
            byte[] byArray;
            Charset charset;
            void i;
            int n3 = bl2;
            int n4 = bl2++;
            byte[][] byArrayArray2 = byArrayArray;
            n = 0;
            String string = domainLabels.get((int)i);
            Intrinsics.checkNotNullExpressionValue(StandardCharsets.UTF_8, "UTF_8");
            boolean bl3 = false;
            String string2 = string;
            if (string2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            Intrinsics.checkNotNullExpressionValue(string2.getBytes(charset), "(this as java.lang.String).getBytes(charset)");
            byArrayArray2[n4] = byArray;
        }
        byte[][] domainLabelsUtf8Bytes = byArrayArray;
        String exactMatch = null;
        int n5 = 0;
        bl2 = domainLabelsUtf8Bytes.length;
        while (n5 < bl2) {
            void i;
            String rule2;
            if (this.publicSuffixListBytes == null) {
                Intrinsics.throwUninitializedPropertyAccessException("publicSuffixListBytes");
            }
            if ((rule2 = PublicSuffixDatabase.Companion.binarySearch(this.publicSuffixListBytes, domainLabelsUtf8Bytes, (int)i)) != null) {
                exactMatch = rule2;
                break;
            }
            ++i;
        }
        String wildcardMatch = null;
        if (((Object[])domainLabelsUtf8Bytes).length > 1) {
            byte[][] labelsWithWildcard = (byte[][])((Object[])domainLabelsUtf8Bytes).clone();
            int rule2 = 0;
            n = ((Object[])labelsWithWildcard).length - 1;
            while (rule2 < n) {
                void labelIndex;
                labelsWithWildcard[labelIndex] = WILDCARD_LABEL;
                if (this.publicSuffixListBytes == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("publicSuffixListBytes");
                }
                if ((rule = PublicSuffixDatabase.Companion.binarySearch(this.publicSuffixListBytes, labelsWithWildcard, (int)labelIndex)) != null) {
                    wildcardMatch = rule;
                    break;
                }
                ++labelIndex;
            }
        }
        String exception = null;
        if (wildcardMatch != null) {
            n = ((Object[])domainLabelsUtf8Bytes).length - 1;
            for (int labelIndex = 0; labelIndex < n; ++labelIndex) {
                if (this.publicSuffixExceptionListBytes == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("publicSuffixExceptionListBytes");
                }
                if ((rule = PublicSuffixDatabase.Companion.binarySearch(this.publicSuffixExceptionListBytes, domainLabelsUtf8Bytes, labelIndex)) == null) continue;
                exception = rule;
                break;
            }
        }
        if (exception != null) {
            exception = '!' + exception;
            return StringsKt.split$default((CharSequence)exception, new char[]{'.'}, false, 0, 6, null);
        }
        if (exactMatch == null && wildcardMatch == null) {
            return PREVAILING_RULE;
        }
        Object object2 = exactMatch;
        if (object2 == null || (object2 = StringsKt.split$default((CharSequence)object2, new char[]{'.'}, false, 0, 6, null)) == null) {
            n = 0;
            object2 = exactRuleLabels = CollectionsKt.emptyList();
        }
        if ((object = wildcardMatch) == null || (object = StringsKt.split$default((CharSequence)object, new char[]{'.'}, false, 0, 6, null)) == null) {
            boolean bl4 = false;
            object = CollectionsKt.emptyList();
        }
        Object wildcardRuleLabels = object;
        return exactRuleLabels.size() > wildcardRuleLabels.size() ? exactRuleLabels : wildcardRuleLabels;
    }

    /*
     * Exception decompiling
     */
    private final void readTheListUninterruptibly() {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [4[CATCHBLOCK]], but top level block is 2[TRYBLOCK]
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:538)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         *     at async.DecompilerRunnable.cfrDecompilation(DecompilerRunnable.java:348)
         *     at async.DecompilerRunnable.call(DecompilerRunnable.java:309)
         *     at async.DecompilerRunnable.call(DecompilerRunnable.java:31)
         *     at java.util.concurrent.FutureTask.run(FutureTask.java:266)
         *     at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
         *     at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
         *     at java.lang.Thread.run(Thread.java:750)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void readTheList() throws IOException {
        byte[] publicSuffixListBytes = null;
        byte[] publicSuffixExceptionListBytes = null;
        InputStream inputStream2 = PublicSuffixDatabase.class.getResourceAsStream(PUBLIC_SUFFIX_RESOURCE);
        if (inputStream2 == null) {
            return;
        }
        InputStream resource = inputStream2;
        Object object = Okio.buffer(new GzipSource(Okio.source(resource)));
        boolean bl = false;
        boolean bl2 = false;
        Throwable throwable = null;
        try {
            Object bufferedSource = (BufferedSource)object;
            boolean bl3 = false;
            int totalBytes = bufferedSource.readInt();
            publicSuffixListBytes = bufferedSource.readByteArray(totalBytes);
            int totalExceptionBytes = bufferedSource.readInt();
            publicSuffixExceptionListBytes = bufferedSource.readByteArray(totalExceptionBytes);
            bufferedSource = Unit.INSTANCE;
        } catch (Throwable bufferedSource) {
            throwable = bufferedSource;
            throw bufferedSource;
        } finally {
            CloseableKt.closeFinally((Closeable)object, throwable);
        }
        object = this;
        bl = false;
        boolean bl4 = false;
        synchronized (object) {
            boolean bl5 = false;
            Intrinsics.checkNotNull(publicSuffixListBytes);
            this.publicSuffixListBytes = publicSuffixListBytes;
            Intrinsics.checkNotNull(publicSuffixExceptionListBytes);
            this.publicSuffixExceptionListBytes = publicSuffixExceptionListBytes;
            Unit unit = Unit.INSTANCE;
        }
        this.readCompleteLatch.countDown();
    }

    public final void setListBytes(@NotNull byte[] publicSuffixListBytes, @NotNull byte[] publicSuffixExceptionListBytes) {
        Intrinsics.checkNotNullParameter(publicSuffixListBytes, "publicSuffixListBytes");
        Intrinsics.checkNotNullParameter(publicSuffixExceptionListBytes, "publicSuffixExceptionListBytes");
        this.publicSuffixListBytes = publicSuffixListBytes;
        this.publicSuffixExceptionListBytes = publicSuffixExceptionListBytes;
        this.listRead.set(true);
        this.readCompleteLatch.countDown();
    }

    static {
        Companion = new Companion(null);
        WILDCARD_LABEL = new byte[]{(byte)42};
        PREVAILING_RULE = CollectionsKt.listOf("*");
        instance = new PublicSuffixDatabase();
    }

    public static final /* synthetic */ byte[] access$getPublicSuffixListBytes$p(PublicSuffixDatabase $this) {
        if ($this.publicSuffixListBytes == null) {
            Intrinsics.throwUninitializedPropertyAccessException("publicSuffixListBytes");
        }
        return $this.publicSuffixListBytes;
    }

    public static final /* synthetic */ void access$setPublicSuffixListBytes$p(PublicSuffixDatabase $this, byte[] byArray) {
        $this.publicSuffixListBytes = byArray;
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\f\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\r\u001a\u00020\fJ)\u0010\u000e\u001a\u0004\u0018\u00010\u0007*\u00020\n2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002\u00a2\u0006\u0002\u0010\u0013R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2={"Lokhttp3/internal/publicsuffix/PublicSuffixDatabase$Companion;", "", "()V", "EXCEPTION_MARKER", "", "PREVAILING_RULE", "", "", "PUBLIC_SUFFIX_RESOURCE", "WILDCARD_LABEL", "", "instance", "Lokhttp3/internal/publicsuffix/PublicSuffixDatabase;", "get", "binarySearch", "labels", "", "labelIndex", "", "([B[[BI)Ljava/lang/String;", "okhttp"})
    public static final class Companion {
        @NotNull
        public final PublicSuffixDatabase get() {
            return instance;
        }

        /*
         * WARNING - void declaration
         */
        private final String binarySearch(byte[] $this$binarySearch, byte[][] labels, int labelIndex) {
            int low = 0;
            int high = $this$binarySearch.length;
            String match = null;
            while (low < high) {
                int mid;
                for (mid = (low + high) / 2; mid > -1 && $this$binarySearch[mid] != (byte)10; --mid) {
                }
                ++mid;
                int end = 1;
                while ($this$binarySearch[mid + end] != (byte)10) {
                    ++end;
                }
                int publicSuffixLength = mid + end - mid;
                int compareResult = 0;
                int currentLabelIndex = labelIndex;
                int currentLabelByteIndex = 0;
                int publicSuffixByteIndex = 0;
                boolean expectDot = false;
                while (true) {
                    int byte0 = 0;
                    if (expectDot) {
                        byte0 = 46;
                        expectDot = false;
                    } else {
                        byte0 = Util.and(labels[currentLabelIndex][currentLabelByteIndex], 255);
                    }
                    int byte1 = Util.and($this$binarySearch[mid + publicSuffixByteIndex], 255);
                    compareResult = byte0 - byte1;
                    if (compareResult != 0) break;
                    ++currentLabelByteIndex;
                    if (++publicSuffixByteIndex == publicSuffixLength) break;
                    if (labels[currentLabelIndex].length != currentLabelByteIndex) continue;
                    if (currentLabelIndex == ((Object[])labels).length - 1) break;
                    ++currentLabelIndex;
                    currentLabelByteIndex = -1;
                    expectDot = true;
                }
                if (compareResult < 0) {
                    high = mid - 1;
                    continue;
                }
                if (compareResult > 0) {
                    low = mid + end + 1;
                    continue;
                }
                int publicSuffixBytesLeft = publicSuffixLength - publicSuffixByteIndex;
                int labelBytesLeft = labels[currentLabelIndex].length - currentLabelByteIndex;
                int n = currentLabelIndex + 1;
                int n2 = ((Object[])labels).length;
                while (n < n2) {
                    void i;
                    labelBytesLeft += labels[i].length;
                    ++i;
                }
                if (labelBytesLeft < publicSuffixBytesLeft) {
                    high = mid - 1;
                    continue;
                }
                if (labelBytesLeft > publicSuffixBytesLeft) {
                    low = mid + end + 1;
                    continue;
                }
                Charset charset = StandardCharsets.UTF_8;
                Intrinsics.checkNotNullExpressionValue(charset, "UTF_8");
                Charset charset2 = charset;
                n2 = 0;
                match = new String($this$binarySearch, mid, publicSuffixLength, charset2);
                break;
            }
            return match;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

