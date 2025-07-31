/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.cache;

import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Flushable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.internal.Util;
import okhttp3.internal.cache.DiskLruCache;
import okhttp3.internal.cache.FaultHidingSink;
import okhttp3.internal.concurrent.Task;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.io.FileSystem;
import okhttp3.internal.platform.Platform;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000y\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010)\n\u0002\b\u0007*\u0001\u0014\u0018\u0000 [2\u00020\u00012\u00020\u0002:\u0004[\\]^B7\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\b\u00108\u001a\u000209H\u0002J\b\u0010:\u001a\u000209H\u0016J!\u0010;\u001a\u0002092\n\u0010<\u001a\u00060=R\u00020\u00002\u0006\u0010>\u001a\u00020\u0010H\u0000\u00a2\u0006\u0002\b?J\u0006\u0010@\u001a\u000209J \u0010A\u001a\b\u0018\u00010=R\u00020\u00002\u0006\u0010B\u001a\u00020(2\b\b\u0002\u0010C\u001a\u00020\u000bH\u0007J\u0006\u0010D\u001a\u000209J\b\u0010E\u001a\u000209H\u0016J\u0017\u0010F\u001a\b\u0018\u00010GR\u00020\u00002\u0006\u0010B\u001a\u00020(H\u0086\u0002J\u0006\u0010H\u001a\u000209J\u0006\u0010I\u001a\u00020\u0010J\b\u0010J\u001a\u00020\u0010H\u0002J\b\u0010K\u001a\u00020%H\u0002J\b\u0010L\u001a\u000209H\u0002J\b\u0010M\u001a\u000209H\u0002J\u0010\u0010N\u001a\u0002092\u0006\u0010O\u001a\u00020(H\u0002J\r\u0010P\u001a\u000209H\u0000\u00a2\u0006\u0002\bQJ\u000e\u0010R\u001a\u00020\u00102\u0006\u0010B\u001a\u00020(J\u0019\u0010S\u001a\u00020\u00102\n\u0010T\u001a\u00060)R\u00020\u0000H\u0000\u00a2\u0006\u0002\bUJ\b\u0010V\u001a\u00020\u0010H\u0002J\u0006\u00105\u001a\u00020\u000bJ\u0010\u0010W\u001a\f\u0012\b\u0012\u00060GR\u00020\u00000XJ\u0006\u0010Y\u001a\u000209J\u0010\u0010Z\u001a\u0002092\u0006\u0010B\u001a\u00020(H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0010X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010\u0003\u001a\u00020\u0004X\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u000e\u0010\u001f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e\u00a2\u0006\u0002\n\u0000R$\u0010&\u001a\u0012\u0012\u0004\u0012\u00020(\u0012\b\u0012\u00060)R\u00020\u00000'X\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010+R&\u0010\n\u001a\u00020\u000b2\u0006\u0010,\u001a\u00020\u000b8F@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u000e\u00101\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\bX\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b6\u00107\u00a8\u0006_"}, d2={"Lokhttp3/internal/cache/DiskLruCache;", "Ljava/io/Closeable;", "Ljava/io/Flushable;", "fileSystem", "Lokhttp3/internal/io/FileSystem;", "directory", "Ljava/io/File;", "appVersion", "", "valueCount", "maxSize", "", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "(Lokhttp3/internal/io/FileSystem;Ljava/io/File;IIJLokhttp3/internal/concurrent/TaskRunner;)V", "civilizedFileSystem", "", "cleanupQueue", "Lokhttp3/internal/concurrent/TaskQueue;", "cleanupTask", "okhttp3/internal/cache/DiskLruCache$cleanupTask$1", "Lokhttp3/internal/cache/DiskLruCache$cleanupTask$1;", "closed", "getClosed$okhttp", "()Z", "setClosed$okhttp", "(Z)V", "getDirectory", "()Ljava/io/File;", "getFileSystem$okhttp", "()Lokhttp3/internal/io/FileSystem;", "hasJournalErrors", "initialized", "journalFile", "journalFileBackup", "journalFileTmp", "journalWriter", "Lokio/BufferedSink;", "lruEntries", "Ljava/util/LinkedHashMap;", "", "Lokhttp3/internal/cache/DiskLruCache$Entry;", "getLruEntries$okhttp", "()Ljava/util/LinkedHashMap;", "value", "getMaxSize", "()J", "setMaxSize", "(J)V", "mostRecentRebuildFailed", "mostRecentTrimFailed", "nextSequenceNumber", "redundantOpCount", "size", "getValueCount$okhttp", "()I", "checkNotClosed", "", "close", "completeEdit", "editor", "Lokhttp3/internal/cache/DiskLruCache$Editor;", "success", "completeEdit$okhttp", "delete", "edit", "key", "expectedSequenceNumber", "evictAll", "flush", "get", "Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "initialize", "isClosed", "journalRebuildRequired", "newJournalWriter", "processJournal", "readJournal", "readJournalLine", "line", "rebuildJournal", "rebuildJournal$okhttp", "remove", "removeEntry", "entry", "removeEntry$okhttp", "removeOldestEntry", "snapshots", "", "trimToSize", "validateKey", "Companion", "Editor", "Entry", "Snapshot", "okhttp"})
public final class DiskLruCache
implements Closeable,
Flushable {
    private long maxSize;
    private final File journalFile;
    private final File journalFileTmp;
    private final File journalFileBackup;
    private long size;
    private BufferedSink journalWriter;
    @NotNull
    private final LinkedHashMap<String, Entry> lruEntries;
    private int redundantOpCount;
    private boolean hasJournalErrors;
    private boolean civilizedFileSystem;
    private boolean initialized;
    private boolean closed;
    private boolean mostRecentTrimFailed;
    private boolean mostRecentRebuildFailed;
    private long nextSequenceNumber;
    private final TaskQueue cleanupQueue;
    private final cleanupTask.1 cleanupTask;
    @NotNull
    private final FileSystem fileSystem;
    @NotNull
    private final File directory;
    private final int appVersion;
    private final int valueCount;
    @JvmField
    @NotNull
    public static final String JOURNAL_FILE;
    @JvmField
    @NotNull
    public static final String JOURNAL_FILE_TEMP;
    @JvmField
    @NotNull
    public static final String JOURNAL_FILE_BACKUP;
    @JvmField
    @NotNull
    public static final String MAGIC;
    @JvmField
    @NotNull
    public static final String VERSION_1;
    @JvmField
    public static final long ANY_SEQUENCE_NUMBER;
    @JvmField
    @NotNull
    public static final Regex LEGAL_KEY_PATTERN;
    @JvmField
    @NotNull
    public static final String CLEAN;
    @JvmField
    @NotNull
    public static final String DIRTY;
    @JvmField
    @NotNull
    public static final String REMOVE;
    @JvmField
    @NotNull
    public static final String READ;
    public static final Companion Companion;

    public final synchronized long getMaxSize() {
        return this.maxSize;
    }

    public final synchronized void setMaxSize(long value) {
        this.maxSize = value;
        if (this.initialized) {
            TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0L, 2, null);
        }
    }

    @NotNull
    public final LinkedHashMap<String, Entry> getLruEntries$okhttp() {
        return this.lruEntries;
    }

    public final boolean getClosed$okhttp() {
        return this.closed;
    }

    public final void setClosed$okhttp(boolean bl) {
        this.closed = bl;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final synchronized void initialize() throws IOException {
        DiskLruCache $this$assertThreadHoldsLock$iv = this;
        boolean $i$f$assertThreadHoldsLock = false;
        if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv)) {
            StringBuilder stringBuilder = new StringBuilder().append("Thread ");
            Thread thread2 = Thread.currentThread();
            Intrinsics.checkNotNullExpressionValue(thread2, "Thread.currentThread()");
            throw (Throwable)((Object)new AssertionError((Object)stringBuilder.append(thread2.getName()).append(" MUST hold lock on ").append($this$assertThreadHoldsLock$iv).toString()));
        }
        if (this.initialized) {
            return;
        }
        if (this.fileSystem.exists(this.journalFileBackup)) {
            if (this.fileSystem.exists(this.journalFile)) {
                this.fileSystem.delete(this.journalFileBackup);
            } else {
                this.fileSystem.rename(this.journalFileBackup, this.journalFile);
            }
        }
        this.civilizedFileSystem = Util.isCivilized(this.fileSystem, this.journalFileBackup);
        if (this.fileSystem.exists(this.journalFile)) {
            try {
                this.readJournal();
                this.processJournal();
                this.initialized = true;
                return;
            } catch (IOException journalIsCorrupt) {
                Platform.Companion.get().log("DiskLruCache " + this.directory + " is corrupt: " + journalIsCorrupt.getMessage() + ", removing", 5, journalIsCorrupt);
                try {
                    this.delete();
                } finally {
                    this.closed = false;
                }
            }
        }
        this.rebuildJournal$okhttp();
        this.initialized = true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private final void readJournal() throws IOException {
        Closeable closeable = Okio.buffer(this.fileSystem.source(this.journalFile));
        boolean bl = false;
        boolean bl2 = false;
        Throwable throwable = null;
        try {
            BufferedSource source2 = (BufferedSource)closeable;
            boolean bl3 = false;
            String magic = source2.readUtf8LineStrict();
            String version = source2.readUtf8LineStrict();
            String appVersionString = source2.readUtf8LineStrict();
            String valueCountString = source2.readUtf8LineStrict();
            String blank = source2.readUtf8LineStrict();
            if (Intrinsics.areEqual(MAGIC, magic) ^ true) throw (Throwable)new IOException("unexpected journal header: [" + magic + ", " + version + ", " + valueCountString + ", " + blank + ']');
            if (Intrinsics.areEqual(VERSION_1, version) ^ true) throw (Throwable)new IOException("unexpected journal header: [" + magic + ", " + version + ", " + valueCountString + ", " + blank + ']');
            if (Intrinsics.areEqual(String.valueOf(this.appVersion), appVersionString) ^ true) throw (Throwable)new IOException("unexpected journal header: [" + magic + ", " + version + ", " + valueCountString + ", " + blank + ']');
            if (Intrinsics.areEqual(String.valueOf(this.valueCount), valueCountString) ^ true) throw (Throwable)new IOException("unexpected journal header: [" + magic + ", " + version + ", " + valueCountString + ", " + blank + ']');
            CharSequence charSequence = blank;
            boolean bl4 = false;
            if (charSequence.length() > 0) {
                throw (Throwable)new IOException("unexpected journal header: [" + magic + ", " + version + ", " + valueCountString + ", " + blank + ']');
            }
            int lineCount = 0;
            try {
                while (true) {
                    this.readJournalLine(source2.readUtf8LineStrict());
                    ++lineCount;
                }
            } catch (EOFException _) {
                this.redundantOpCount = lineCount - this.lruEntries.size();
                if (!source2.exhausted()) {
                    this.rebuildJournal$okhttp();
                } else {
                    this.journalWriter = this.newJournalWriter();
                }
                Unit unit = Unit.INSTANCE;
                return;
            }
        } catch (Throwable throwable2) {
            throwable = throwable2;
            throw throwable2;
        } finally {
            CloseableKt.closeFinally(closeable, throwable);
        }
    }

    private final BufferedSink newJournalWriter() throws FileNotFoundException {
        Sink fileSink = this.fileSystem.appendingSink(this.journalFile);
        FaultHidingSink faultHidingSink2 = new FaultHidingSink(fileSink, (Function1<? super IOException, Unit>)new Function1<IOException, Unit>(this){
            final /* synthetic */ DiskLruCache this$0;

            public final void invoke(@NotNull IOException it) {
                Intrinsics.checkNotNullParameter(it, "it");
                DiskLruCache $this$assertThreadHoldsLock$iv = this.this$0;
                boolean $i$f$assertThreadHoldsLock = false;
                if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv)) {
                    StringBuilder stringBuilder = new StringBuilder().append("Thread ");
                    Thread thread2 = Thread.currentThread();
                    Intrinsics.checkNotNullExpressionValue(thread2, "Thread.currentThread()");
                    throw (Throwable)((Object)new AssertionError((Object)stringBuilder.append(thread2.getName()).append(" MUST hold lock on ").append($this$assertThreadHoldsLock$iv).toString()));
                }
                DiskLruCache.access$setHasJournalErrors$p(this.this$0, true);
            }
            {
                this.this$0 = diskLruCache;
                super(1);
            }
        });
        return Okio.buffer(faultHidingSink2);
    }

    private final void readJournalLine(String line) throws IOException {
        Entry entry;
        boolean bl;
        String string;
        int firstSpace = StringsKt.indexOf$default((CharSequence)line, ' ', 0, false, 6, null);
        if (firstSpace == -1) {
            throw (Throwable)new IOException("unexpected journal line: " + line);
        }
        int keyBegin = firstSpace + 1;
        int secondSpace = StringsKt.indexOf$default((CharSequence)line, ' ', keyBegin, false, 4, null);
        String key = null;
        if (secondSpace == -1) {
            string = line;
            bl = false;
            String string2 = string;
            if (string2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            String string3 = string2.substring(keyBegin);
            Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.String).substring(startIndex)");
            key = string3;
            if (firstSpace == REMOVE.length() && StringsKt.startsWith$default(line, REMOVE, false, 2, null)) {
                this.lruEntries.remove(key);
                return;
            }
        } else {
            string = line;
            bl = false;
            String string4 = string;
            if (string4 == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            String string5 = string4.substring(keyBegin, secondSpace);
            Intrinsics.checkNotNullExpressionValue(string5, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
            key = string5;
        }
        if ((entry = this.lruEntries.get(key)) == null) {
            entry = new Entry(key);
            ((Map)this.lruEntries).put(key, entry);
        }
        if (secondSpace != -1 && firstSpace == CLEAN.length() && StringsKt.startsWith$default(line, CLEAN, false, 2, null)) {
            String string6 = line;
            int n = secondSpace + 1;
            boolean bl2 = false;
            String string7 = string6;
            if (string7 == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            String string8 = string7.substring(n);
            Intrinsics.checkNotNullExpressionValue(string8, "(this as java.lang.String).substring(startIndex)");
            List parts = StringsKt.split$default((CharSequence)string8, new char[]{' '}, false, 0, 6, null);
            entry.setReadable$okhttp(true);
            entry.setCurrentEditor$okhttp(null);
            entry.setLengths$okhttp(parts);
        } else if (secondSpace == -1 && firstSpace == DIRTY.length() && StringsKt.startsWith$default(line, DIRTY, false, 2, null)) {
            entry.setCurrentEditor$okhttp(new Editor(entry));
        } else if (secondSpace != -1 || firstSpace != READ.length() || !StringsKt.startsWith$default(line, READ, false, 2, null)) {
            throw (Throwable)new IOException("unexpected journal line: " + line);
        }
    }

    private final void processJournal() throws IOException {
        this.fileSystem.delete(this.journalFileTmp);
        Iterator<Entry> i = this.lruEntries.values().iterator();
        while (i.hasNext()) {
            int t;
            int n;
            Entry entry;
            Intrinsics.checkNotNullExpressionValue(i.next(), "i.next()");
            if (entry.getCurrentEditor$okhttp() == null) {
                int n2 = 0;
                n = this.valueCount;
                while (n2 < n) {
                    this.size += entry.getLengths$okhttp()[t];
                    ++t;
                }
                continue;
            }
            entry.setCurrentEditor$okhttp(null);
            n = this.valueCount;
            for (t = 0; t < n; ++t) {
                this.fileSystem.delete(entry.getCleanFiles$okhttp().get(t));
                this.fileSystem.delete(entry.getDirtyFiles$okhttp().get(t));
            }
            i.remove();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final synchronized void rebuildJournal$okhttp() throws IOException {
        BufferedSink bufferedSink = this.journalWriter;
        if (bufferedSink != null) {
            bufferedSink.close();
        }
        Closeable closeable = Okio.buffer(this.fileSystem.sink(this.journalFileTmp));
        boolean bl = false;
        boolean bl2 = false;
        Throwable throwable = null;
        try {
            BufferedSink sink2 = (BufferedSink)closeable;
            boolean bl3 = false;
            sink2.writeUtf8(MAGIC).writeByte(10);
            sink2.writeUtf8(VERSION_1).writeByte(10);
            sink2.writeDecimalLong(this.appVersion).writeByte(10);
            sink2.writeDecimalLong(this.valueCount).writeByte(10);
            sink2.writeByte(10);
            for (Entry entry : this.lruEntries.values()) {
                if (entry.getCurrentEditor$okhttp() != null) {
                    sink2.writeUtf8(DIRTY).writeByte(32);
                    sink2.writeUtf8(entry.getKey$okhttp());
                    sink2.writeByte(10);
                    continue;
                }
                sink2.writeUtf8(CLEAN).writeByte(32);
                sink2.writeUtf8(entry.getKey$okhttp());
                entry.writeLengths$okhttp(sink2);
                sink2.writeByte(10);
            }
            Unit unit = Unit.INSTANCE;
        } catch (Throwable throwable2) {
            throwable = throwable2;
            throw throwable2;
        } finally {
            CloseableKt.closeFinally(closeable, throwable);
        }
        if (this.fileSystem.exists(this.journalFile)) {
            this.fileSystem.rename(this.journalFile, this.journalFileBackup);
        }
        this.fileSystem.rename(this.journalFileTmp, this.journalFile);
        this.fileSystem.delete(this.journalFileBackup);
        this.journalWriter = this.newJournalWriter();
        this.hasJournalErrors = false;
        this.mostRecentRebuildFailed = false;
    }

    @Nullable
    public final synchronized Snapshot get(@NotNull String key) throws IOException {
        Intrinsics.checkNotNullParameter(key, "key");
        this.initialize();
        this.checkNotClosed();
        this.validateKey(key);
        Entry entry = this.lruEntries.get(key);
        if (entry == null) {
            return null;
        }
        Intrinsics.checkNotNullExpressionValue(entry, "lruEntries[key] ?: return null");
        Entry entry2 = entry;
        Snapshot snapshot = entry2.snapshot$okhttp();
        if (snapshot == null) {
            return null;
        }
        Snapshot snapshot2 = snapshot;
        int n = this.redundantOpCount;
        this.redundantOpCount = n + 1;
        BufferedSink bufferedSink = this.journalWriter;
        Intrinsics.checkNotNull(bufferedSink);
        bufferedSink.writeUtf8(READ).writeByte(32).writeUtf8(key).writeByte(10);
        if (this.journalRebuildRequired()) {
            TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0L, 2, null);
        }
        return snapshot2;
    }

    @JvmOverloads
    @Nullable
    public final synchronized Editor edit(@NotNull String key, long expectedSequenceNumber) throws IOException {
        Intrinsics.checkNotNullParameter(key, "key");
        this.initialize();
        this.checkNotClosed();
        this.validateKey(key);
        Entry entry = this.lruEntries.get(key);
        if (expectedSequenceNumber != ANY_SEQUENCE_NUMBER && (entry == null || entry.getSequenceNumber$okhttp() != expectedSequenceNumber)) {
            return null;
        }
        Entry entry2 = entry;
        if ((entry2 != null ? entry2.getCurrentEditor$okhttp() : null) != null) {
            return null;
        }
        if (entry != null && entry.getLockingSourceCount$okhttp() != 0) {
            return null;
        }
        if (this.mostRecentTrimFailed || this.mostRecentRebuildFailed) {
            TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0L, 2, null);
            return null;
        }
        BufferedSink bufferedSink = this.journalWriter;
        Intrinsics.checkNotNull(bufferedSink);
        BufferedSink journalWriter = bufferedSink;
        journalWriter.writeUtf8(DIRTY).writeByte(32).writeUtf8(key).writeByte(10);
        journalWriter.flush();
        if (this.hasJournalErrors) {
            return null;
        }
        if (entry == null) {
            entry = new Entry(key);
            ((Map)this.lruEntries).put(key, entry);
        }
        Editor editor = new Editor(entry);
        entry.setCurrentEditor$okhttp(editor);
        return editor;
    }

    public static /* synthetic */ Editor edit$default(DiskLruCache diskLruCache, String string, long l, int n, Object object) throws IOException {
        if ((n & 2) != 0) {
            l = ANY_SEQUENCE_NUMBER;
        }
        return diskLruCache.edit(string, l);
    }

    @JvmOverloads
    @Nullable
    public final Editor edit(@NotNull String key) throws IOException {
        return DiskLruCache.edit$default(this, key, 0L, 2, null);
    }

    public final synchronized long size() throws IOException {
        this.initialize();
        return this.size;
    }

    public final synchronized void completeEdit$okhttp(@NotNull Editor editor, boolean success) throws IOException {
        int i;
        Intrinsics.checkNotNullParameter(editor, "editor");
        Entry entry = editor.getEntry$okhttp();
        int n = Intrinsics.areEqual(entry.getCurrentEditor$okhttp(), editor);
        int n2 = 0;
        boolean bl = false;
        bl = false;
        boolean bl2 = false;
        if (n == 0) {
            boolean bl3 = false;
            String string = "Check failed.";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        if (success && !entry.getReadable$okhttp()) {
            n = 0;
            n2 = this.valueCount;
            while (n < n2) {
                boolean[] blArray = editor.getWritten$okhttp();
                Intrinsics.checkNotNull(blArray);
                if (!blArray[i]) {
                    editor.abort();
                    throw (Throwable)new IllegalStateException("Newly created entry didn't create value for index " + i);
                }
                if (!this.fileSystem.exists(entry.getDirtyFiles$okhttp().get(i))) {
                    editor.abort();
                    return;
                }
                ++i;
            }
        }
        n2 = this.valueCount;
        for (i = 0; i < n2; ++i) {
            File dirty = entry.getDirtyFiles$okhttp().get(i);
            if (success && !entry.getZombie$okhttp()) {
                long newLength;
                if (!this.fileSystem.exists(dirty)) continue;
                File clean = entry.getCleanFiles$okhttp().get(i);
                this.fileSystem.rename(dirty, clean);
                long oldLength = entry.getLengths$okhttp()[i];
                entry.getLengths$okhttp()[i] = newLength = this.fileSystem.size(clean);
                this.size = this.size - oldLength + newLength;
                continue;
            }
            this.fileSystem.delete(dirty);
        }
        entry.setCurrentEditor$okhttp(null);
        if (entry.getZombie$okhttp()) {
            this.removeEntry$okhttp(entry);
            return;
        }
        n = this.redundantOpCount;
        this.redundantOpCount = n + 1;
        BufferedSink bufferedSink = this.journalWriter;
        Intrinsics.checkNotNull(bufferedSink);
        BufferedSink bufferedSink2 = bufferedSink;
        n2 = 0;
        bl = false;
        BufferedSink $this$apply = bufferedSink2;
        boolean bl4 = false;
        if (entry.getReadable$okhttp() || success) {
            entry.setReadable$okhttp(true);
            $this$apply.writeUtf8(CLEAN).writeByte(32);
            $this$apply.writeUtf8(entry.getKey$okhttp());
            entry.writeLengths$okhttp($this$apply);
            $this$apply.writeByte(10);
            if (success) {
                long l = this.nextSequenceNumber;
                this.nextSequenceNumber = l + 1L;
                entry.setSequenceNumber$okhttp(l);
            }
        } else {
            this.lruEntries.remove(entry.getKey$okhttp());
            $this$apply.writeUtf8(REMOVE).writeByte(32);
            $this$apply.writeUtf8(entry.getKey$okhttp());
            $this$apply.writeByte(10);
        }
        $this$apply.flush();
        if (this.size > this.maxSize || this.journalRebuildRequired()) {
            TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0L, 2, null);
        }
    }

    private final boolean journalRebuildRequired() {
        int redundantOpCompactThreshold = 2000;
        return this.redundantOpCount >= redundantOpCompactThreshold && this.redundantOpCount >= this.lruEntries.size();
    }

    public final synchronized boolean remove(@NotNull String key) throws IOException {
        Intrinsics.checkNotNullParameter(key, "key");
        this.initialize();
        this.checkNotClosed();
        this.validateKey(key);
        Entry entry = this.lruEntries.get(key);
        if (entry == null) {
            return false;
        }
        Intrinsics.checkNotNullExpressionValue(entry, "lruEntries[key] ?: return false");
        Entry entry2 = entry;
        boolean removed = this.removeEntry$okhttp(entry2);
        if (removed && this.size <= this.maxSize) {
            this.mostRecentTrimFailed = false;
        }
        return removed;
    }

    /*
     * WARNING - void declaration
     */
    public final boolean removeEntry$okhttp(@NotNull Entry entry) throws IOException {
        BufferedSink it;
        boolean bl;
        int n;
        Intrinsics.checkNotNullParameter(entry, "entry");
        if (!this.civilizedFileSystem) {
            if (entry.getLockingSourceCount$okhttp() > 0) {
                BufferedSink bufferedSink = this.journalWriter;
                if (bufferedSink != null) {
                    BufferedSink bufferedSink2 = bufferedSink;
                    n = 0;
                    bl = false;
                    it = bufferedSink2;
                    boolean bl2 = false;
                    it.writeUtf8(DIRTY);
                    it.writeByte(32);
                    it.writeUtf8(entry.getKey$okhttp());
                    it.writeByte(10);
                    it.flush();
                }
            }
            if (entry.getLockingSourceCount$okhttp() > 0 || entry.getCurrentEditor$okhttp() != null) {
                entry.setZombie$okhttp(true);
                return true;
            }
        }
        Editor editor = entry.getCurrentEditor$okhttp();
        if (editor != null) {
            editor.detach$okhttp();
        }
        int n2 = 0;
        n = this.valueCount;
        while (n2 < n) {
            void i;
            this.fileSystem.delete(entry.getCleanFiles$okhttp().get((int)i));
            this.size -= entry.getLengths$okhttp()[i];
            entry.getLengths$okhttp()[i] = 0L;
            ++i;
        }
        n2 = this.redundantOpCount;
        this.redundantOpCount = n2 + 1;
        BufferedSink bufferedSink = this.journalWriter;
        if (bufferedSink != null) {
            BufferedSink bufferedSink3 = bufferedSink;
            n = 0;
            bl = false;
            it = bufferedSink3;
            boolean bl3 = false;
            it.writeUtf8(REMOVE);
            it.writeByte(32);
            it.writeUtf8(entry.getKey$okhttp());
            it.writeByte(10);
        }
        this.lruEntries.remove(entry.getKey$okhttp());
        if (this.journalRebuildRequired()) {
            TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0L, 2, null);
        }
        return true;
    }

    private final synchronized void checkNotClosed() {
        boolean bl = !this.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "cache is closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
    }

    @Override
    public synchronized void flush() throws IOException {
        if (!this.initialized) {
            return;
        }
        this.checkNotClosed();
        this.trimToSize();
        BufferedSink bufferedSink = this.journalWriter;
        Intrinsics.checkNotNull(bufferedSink);
        bufferedSink.flush();
    }

    public final synchronized boolean isClosed() {
        return this.closed;
    }

    @Override
    public synchronized void close() throws IOException {
        if (!this.initialized || this.closed) {
            this.closed = true;
            return;
        }
        Collection<Entry> collection = this.lruEntries.values();
        Intrinsics.checkNotNullExpressionValue(collection, "lruEntries.values");
        Collection<Entry> $this$toTypedArray$iv = collection;
        boolean $i$f$toTypedArray = false;
        Collection<Entry> thisCollection$iv = $this$toTypedArray$iv;
        Entry[] entryArray = thisCollection$iv.toArray(new Entry[0]);
        if (entryArray == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        for (Entry entry : entryArray) {
            if (entry.getCurrentEditor$okhttp() == null) continue;
            Editor editor = entry.getCurrentEditor$okhttp();
            if (editor == null) continue;
            editor.detach$okhttp();
        }
        this.trimToSize();
        BufferedSink bufferedSink = this.journalWriter;
        Intrinsics.checkNotNull(bufferedSink);
        bufferedSink.close();
        this.journalWriter = null;
        this.closed = true;
    }

    public final void trimToSize() throws IOException {
        while (this.size > this.maxSize) {
            if (this.removeOldestEntry()) continue;
            return;
        }
        this.mostRecentTrimFailed = false;
    }

    private final boolean removeOldestEntry() {
        for (Entry toEvict : this.lruEntries.values()) {
            if (toEvict.getZombie$okhttp()) continue;
            Entry entry = toEvict;
            Intrinsics.checkNotNullExpressionValue(entry, "toEvict");
            this.removeEntry$okhttp(entry);
            return true;
        }
        return false;
    }

    public final void delete() throws IOException {
        this.close();
        this.fileSystem.deleteContents(this.directory);
    }

    public final synchronized void evictAll() throws IOException {
        this.initialize();
        Collection<Entry> collection = this.lruEntries.values();
        Intrinsics.checkNotNullExpressionValue(collection, "lruEntries.values");
        Collection<Entry> $this$toTypedArray$iv = collection;
        boolean $i$f$toTypedArray = false;
        Collection<Entry> thisCollection$iv = $this$toTypedArray$iv;
        Entry[] entryArray = thisCollection$iv.toArray(new Entry[0]);
        if (entryArray == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        Entry[] entryArray2 = entryArray;
        int n = entryArray2.length;
        for (int i = 0; i < n; ++i) {
            Entry entry;
            Entry entry2 = entry = entryArray2[i];
            Intrinsics.checkNotNullExpressionValue(entry2, "entry");
            this.removeEntry$okhttp(entry2);
        }
        this.mostRecentTrimFailed = false;
    }

    private final void validateKey(String key) {
        boolean bl = LEGAL_KEY_PATTERN.matches(key);
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "keys must match regex [a-z0-9_-]{1,120}: \"" + key + '\"';
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
    }

    @NotNull
    public final synchronized Iterator<Snapshot> snapshots() throws IOException {
        this.initialize();
        return new Iterator<Snapshot>(this){
            private final Iterator<Entry> delegate;
            private Snapshot nextSnapshot;
            private Snapshot removeSnapshot;
            final /* synthetic */ DiskLruCache this$0;

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            public boolean hasNext() {
                if (this.nextSnapshot != null) {
                    return true;
                }
                DiskLruCache diskLruCache = this.this$0;
                boolean bl = false;
                boolean bl2 = false;
                synchronized (diskLruCache) {
                    block8: {
                        boolean bl3 = false;
                        if (!this.this$0.getClosed$okhttp()) break block8;
                        boolean bl4 = false;
                        return bl4;
                    }
                    while (this.delegate.hasNext()) {
                        Object object = this.delegate.next();
                        if (object == null || (object = ((Entry)object).snapshot$okhttp()) == null) {
                            continue;
                        }
                        this.nextSnapshot = object;
                        boolean bl5 = true;
                        return bl5;
                    }
                    Unit unit = Unit.INSTANCE;
                }
                return false;
            }

            @NotNull
            public Snapshot next() {
                if (!this.hasNext()) {
                    throw (Throwable)new NoSuchElementException();
                }
                this.removeSnapshot = this.nextSnapshot;
                this.nextSnapshot = null;
                Snapshot snapshot = this.removeSnapshot;
                Intrinsics.checkNotNull(snapshot);
                return snapshot;
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            public void remove() {
                Snapshot removeSnapshot = this.removeSnapshot;
                boolean bl = false;
                boolean bl2 = false;
                if (removeSnapshot == null) {
                    boolean bl3 = false;
                    String string = "remove() before next()";
                    throw (Throwable)new IllegalStateException(string.toString());
                }
                try {
                    this.this$0.remove(removeSnapshot.key());
                } catch (IOException iOException) {
                } finally {
                    this.removeSnapshot = null;
                }
            }
            {
                this.this$0 = this$0;
                Iterator<Entry> iterator2 = new ArrayList<Entry>(this$0.getLruEntries$okhttp().values()).iterator();
                Intrinsics.checkNotNullExpressionValue(iterator2, "ArrayList(lruEntries.values).iterator()");
                this.delegate = iterator2;
            }
        };
    }

    @NotNull
    public final FileSystem getFileSystem$okhttp() {
        return this.fileSystem;
    }

    @NotNull
    public final File getDirectory() {
        return this.directory;
    }

    public final int getValueCount$okhttp() {
        return this.valueCount;
    }

    public DiskLruCache(@NotNull FileSystem fileSystem, @NotNull File directory, int appVersion, int valueCount, long maxSize, @NotNull TaskRunner taskRunner) {
        Intrinsics.checkNotNullParameter(fileSystem, "fileSystem");
        Intrinsics.checkNotNullParameter(directory, "directory");
        Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
        this.fileSystem = fileSystem;
        this.directory = directory;
        this.appVersion = appVersion;
        this.valueCount = valueCount;
        this.maxSize = maxSize;
        this.lruEntries = new LinkedHashMap(0, 0.75f, true);
        this.cleanupQueue = taskRunner.newQueue();
        this.cleanupTask = new Task(this, Util.okHttpName + " Cache"){
            final /* synthetic */ DiskLruCache this$0;

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            public long runOnce() {
                DiskLruCache diskLruCache = this.this$0;
                boolean bl = false;
                boolean bl2 = false;
                synchronized (diskLruCache) {
                    block9: {
                        boolean bl3 = false;
                        if (DiskLruCache.access$getInitialized$p(this.this$0) && !this.this$0.getClosed$okhttp()) break block9;
                        long l = -1L;
                        return l;
                    }
                    try {
                        this.this$0.trimToSize();
                    } catch (IOException _) {
                        DiskLruCache.access$setMostRecentTrimFailed$p(this.this$0, true);
                    }
                    try {
                        if (DiskLruCache.access$journalRebuildRequired(this.this$0)) {
                            this.this$0.rebuildJournal$okhttp();
                            DiskLruCache.access$setRedundantOpCount$p(this.this$0, 0);
                        }
                    } catch (IOException _) {
                        DiskLruCache.access$setMostRecentRebuildFailed$p(this.this$0, true);
                        DiskLruCache.access$setJournalWriter$p(this.this$0, Okio.buffer(Okio.blackhole()));
                    }
                    long l = -1L;
                    return l;
                }
            }
            {
                this.this$0 = this$0;
                super($super_call_param$1, false, 2, null);
            }
        };
        boolean bl = maxSize > 0L;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "maxSize <= 0";
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        bl = this.valueCount > 0;
        bl2 = false;
        bl3 = false;
        if (!bl) {
            boolean bl5 = false;
            String string = "valueCount <= 0";
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        this.journalFile = new File(this.directory, JOURNAL_FILE);
        this.journalFileTmp = new File(this.directory, JOURNAL_FILE_TEMP);
        this.journalFileBackup = new File(this.directory, JOURNAL_FILE_BACKUP);
    }

    static {
        Companion = new Companion(null);
        JOURNAL_FILE = "journal";
        JOURNAL_FILE_TEMP = "journal.tmp";
        JOURNAL_FILE_BACKUP = "journal.bkp";
        MAGIC = "libcore.io.DiskLruCache";
        VERSION_1 = "1";
        ANY_SEQUENCE_NUMBER = -1L;
        String string = "[a-z0-9_-]{1,120}";
        boolean bl = false;
        LEGAL_KEY_PATTERN = new Regex(string);
        CLEAN = "CLEAN";
        DIRTY = "DIRTY";
        REMOVE = "REMOVE";
        READ = "READ";
    }

    public static final /* synthetic */ void access$setCivilizedFileSystem$p(DiskLruCache $this, boolean bl) {
        $this.civilizedFileSystem = bl;
    }

    public static final /* synthetic */ boolean access$getHasJournalErrors$p(DiskLruCache $this) {
        return $this.hasJournalErrors;
    }

    public static final /* synthetic */ void access$setHasJournalErrors$p(DiskLruCache $this, boolean bl) {
        $this.hasJournalErrors = bl;
    }

    public static final /* synthetic */ boolean access$getInitialized$p(DiskLruCache $this) {
        return $this.initialized;
    }

    public static final /* synthetic */ void access$setInitialized$p(DiskLruCache $this, boolean bl) {
        $this.initialized = bl;
    }

    public static final /* synthetic */ boolean access$getMostRecentTrimFailed$p(DiskLruCache $this) {
        return $this.mostRecentTrimFailed;
    }

    public static final /* synthetic */ void access$setMostRecentTrimFailed$p(DiskLruCache $this, boolean bl) {
        $this.mostRecentTrimFailed = bl;
    }

    public static final /* synthetic */ boolean access$journalRebuildRequired(DiskLruCache $this) {
        return $this.journalRebuildRequired();
    }

    public static final /* synthetic */ int access$getRedundantOpCount$p(DiskLruCache $this) {
        return $this.redundantOpCount;
    }

    public static final /* synthetic */ void access$setRedundantOpCount$p(DiskLruCache $this, int n) {
        $this.redundantOpCount = n;
    }

    public static final /* synthetic */ boolean access$getMostRecentRebuildFailed$p(DiskLruCache $this) {
        return $this.mostRecentRebuildFailed;
    }

    public static final /* synthetic */ void access$setMostRecentRebuildFailed$p(DiskLruCache $this, boolean bl) {
        $this.mostRecentRebuildFailed = bl;
    }

    public static final /* synthetic */ BufferedSink access$getJournalWriter$p(DiskLruCache $this) {
        return $this.journalWriter;
    }

    public static final /* synthetic */ void access$setJournalWriter$p(DiskLruCache $this, BufferedSink bufferedSink) {
        $this.journalWriter = bufferedSink;
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0016\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B-\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bJ\b\u0010\f\u001a\u00020\rH\u0016J\f\u0010\u000e\u001a\b\u0018\u00010\u000fR\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0002\u001a\u00020\u0003R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2={"Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "Ljava/io/Closeable;", "key", "", "sequenceNumber", "", "sources", "", "Lokio/Source;", "lengths", "", "(Lokhttp3/internal/cache/DiskLruCache;Ljava/lang/String;JLjava/util/List;[J)V", "close", "", "edit", "Lokhttp3/internal/cache/DiskLruCache$Editor;", "Lokhttp3/internal/cache/DiskLruCache;", "getLength", "index", "", "getSource", "okhttp"})
    public final class Snapshot
    implements Closeable {
        private final String key;
        private final long sequenceNumber;
        private final List<Source> sources;
        private final long[] lengths;

        @NotNull
        public final String key() {
            return this.key;
        }

        @Nullable
        public final Editor edit() throws IOException {
            return DiskLruCache.this.edit(this.key, this.sequenceNumber);
        }

        @NotNull
        public final Source getSource(int index) {
            return this.sources.get(index);
        }

        public final long getLength(int index) {
            return this.lengths[index];
        }

        @Override
        public void close() {
            for (Source source2 : this.sources) {
                Util.closeQuietly(source2);
            }
        }

        public Snapshot(String key, @NotNull long sequenceNumber, @NotNull List<? extends Source> sources, long[] lengths) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(sources, "sources");
            Intrinsics.checkNotNullParameter(lengths, "lengths");
            this.key = key;
            this.sequenceNumber = sequenceNumber;
            this.sources = sources;
            this.lengths = lengths;
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0018\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0013\b\u0000\u0012\n\u0010\u0002\u001a\u00060\u0003R\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u000fJ\r\u0010\u0011\u001a\u00020\u000fH\u0000\u00a2\u0006\u0002\b\u0012J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0015\u001a\u00020\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0018\u0010\u0002\u001a\u00060\u0003R\u00020\u0004X\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0019"}, d2={"Lokhttp3/internal/cache/DiskLruCache$Editor;", "", "entry", "Lokhttp3/internal/cache/DiskLruCache$Entry;", "Lokhttp3/internal/cache/DiskLruCache;", "(Lokhttp3/internal/cache/DiskLruCache;Lokhttp3/internal/cache/DiskLruCache$Entry;)V", "done", "", "getEntry$okhttp", "()Lokhttp3/internal/cache/DiskLruCache$Entry;", "written", "", "getWritten$okhttp", "()[Z", "abort", "", "commit", "detach", "detach$okhttp", "newSink", "Lokio/Sink;", "index", "", "newSource", "Lokio/Source;", "okhttp"})
    public final class Editor {
        @Nullable
        private final boolean[] written;
        private boolean done;
        @NotNull
        private final Entry entry;

        @Nullable
        public final boolean[] getWritten$okhttp() {
            return this.written;
        }

        public final void detach$okhttp() {
            if (Intrinsics.areEqual(this.entry.getCurrentEditor$okhttp(), this)) {
                if (DiskLruCache.this.civilizedFileSystem) {
                    DiskLruCache.this.completeEdit$okhttp(this, false);
                } else {
                    this.entry.setZombie$okhttp(true);
                }
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Nullable
        public final Source newSource(int index) {
            DiskLruCache diskLruCache = DiskLruCache.this;
            boolean bl = false;
            boolean bl2 = false;
            synchronized (diskLruCache) {
                Source source2;
                block7: {
                    boolean bl3 = false;
                    boolean bl4 = !this.done;
                    boolean bl5 = false;
                    boolean bl6 = false;
                    bl6 = false;
                    boolean bl7 = false;
                    if (!bl4) {
                        boolean bl8 = false;
                        String string = "Check failed.";
                        throw (Throwable)new IllegalStateException(string.toString());
                    }
                    if (this.entry.getReadable$okhttp() && !(Intrinsics.areEqual(this.entry.getCurrentEditor$okhttp(), this) ^ true) && !this.entry.getZombie$okhttp()) break block7;
                    Source source3 = null;
                    return source3;
                }
                try {
                    source2 = DiskLruCache.this.getFileSystem$okhttp().source(this.entry.getCleanFiles$okhttp().get(index));
                } catch (FileNotFoundException _) {
                    source2 = null;
                }
                Source source4 = source2;
                return source4;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @NotNull
        public final Sink newSink(int index) {
            DiskLruCache diskLruCache = DiskLruCache.this;
            boolean bl = false;
            boolean bl2 = false;
            synchronized (diskLruCache) {
                block9: {
                    boolean bl3 = false;
                    boolean bl4 = !this.done;
                    boolean bl5 = false;
                    boolean bl6 = false;
                    bl6 = false;
                    boolean bl7 = false;
                    if (!bl4) {
                        boolean bl8 = false;
                        String string = "Check failed.";
                        throw (Throwable)new IllegalStateException(string.toString());
                    }
                    if (!(Intrinsics.areEqual(this.entry.getCurrentEditor$okhttp(), this) ^ true)) break block9;
                    Sink sink2 = Okio.blackhole();
                    return sink2;
                }
                if (!this.entry.getReadable$okhttp()) {
                    Intrinsics.checkNotNull(this.written);
                    this.written[index] = true;
                }
                File dirtyFile = this.entry.getDirtyFiles$okhttp().get(index);
                Sink sink3 = null;
                try {
                    sink3 = DiskLruCache.this.getFileSystem$okhttp().sink(dirtyFile);
                } catch (FileNotFoundException _) {
                    Sink sink4 = Okio.blackhole();
                    return sink4;
                }
                Sink sink5 = new FaultHidingSink(sink3, (Function1<? super IOException, Unit>)new Function1<IOException, Unit>(this, index){
                    final /* synthetic */ Editor this$0;
                    final /* synthetic */ int $index$inlined;
                    {
                        this.this$0 = editor;
                        this.$index$inlined = n;
                        super(1);
                    }

                    /*
                     * WARNING - Removed try catching itself - possible behaviour change.
                     */
                    public final void invoke(@NotNull IOException it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        DiskLruCache diskLruCache = this.this$0.DiskLruCache.this;
                        boolean bl = false;
                        boolean bl2 = false;
                        synchronized (diskLruCache) {
                            boolean bl3 = false;
                            this.this$0.detach$okhttp();
                            Unit unit = Unit.INSTANCE;
                        }
                    }
                });
                return sink5;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        public final void commit() throws IOException {
            DiskLruCache diskLruCache = DiskLruCache.this;
            boolean bl = false;
            boolean bl2 = false;
            synchronized (diskLruCache) {
                boolean bl3 = false;
                boolean bl4 = !this.done;
                boolean bl5 = false;
                boolean bl6 = false;
                bl6 = false;
                boolean bl7 = false;
                if (!bl4) {
                    boolean bl8 = false;
                    String string = "Check failed.";
                    throw (Throwable)new IllegalStateException(string.toString());
                }
                if (Intrinsics.areEqual(this.entry.getCurrentEditor$okhttp(), this)) {
                    DiskLruCache.this.completeEdit$okhttp(this, true);
                }
                this.done = true;
                Unit unit = Unit.INSTANCE;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        public final void abort() throws IOException {
            DiskLruCache diskLruCache = DiskLruCache.this;
            boolean bl = false;
            boolean bl2 = false;
            synchronized (diskLruCache) {
                boolean bl3 = false;
                boolean bl4 = !this.done;
                boolean bl5 = false;
                boolean bl6 = false;
                bl6 = false;
                boolean bl7 = false;
                if (!bl4) {
                    boolean bl8 = false;
                    String string = "Check failed.";
                    throw (Throwable)new IllegalStateException(string.toString());
                }
                if (Intrinsics.areEqual(this.entry.getCurrentEditor$okhttp(), this)) {
                    DiskLruCache.this.completeEdit$okhttp(this, false);
                }
                this.done = true;
                Unit unit = Unit.INSTANCE;
            }
        }

        @NotNull
        public final Entry getEntry$okhttp() {
            return this.entry;
        }

        public Editor(Entry entry) {
            Intrinsics.checkNotNullParameter(entry, "entry");
            this.entry = entry;
            this.written = this.entry.getReadable$okhttp() ? null : new boolean[DiskLruCache.this.getValueCount$okhttp()];
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0016\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0004\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010.\u001a\u00020/2\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u000301H\u0002J\u0010\u00102\u001a\u0002032\u0006\u00104\u001a\u00020\u001aH\u0002J\u001b\u00105\u001a\u0002062\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u000301H\u0000\u00a2\u0006\u0002\b7J\u0013\u00108\u001a\b\u0018\u000109R\u00020\fH\u0000\u00a2\u0006\u0002\b:J\u0015\u0010;\u001a\u0002062\u0006\u0010<\u001a\u00020=H\u0000\u00a2\u0006\u0002\b>R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR \u0010\n\u001a\b\u0018\u00010\u000bR\u00020\fX\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\tR\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u0016X\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u001aX\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u001f\u001a\u00020 X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001a\u0010%\u001a\u00020&X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001a\u0010+\u001a\u00020 X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\"\"\u0004\b-\u0010$\u00a8\u0006?"}, d2={"Lokhttp3/internal/cache/DiskLruCache$Entry;", "", "key", "", "(Lokhttp3/internal/cache/DiskLruCache;Ljava/lang/String;)V", "cleanFiles", "", "Ljava/io/File;", "getCleanFiles$okhttp", "()Ljava/util/List;", "currentEditor", "Lokhttp3/internal/cache/DiskLruCache$Editor;", "Lokhttp3/internal/cache/DiskLruCache;", "getCurrentEditor$okhttp", "()Lokhttp3/internal/cache/DiskLruCache$Editor;", "setCurrentEditor$okhttp", "(Lokhttp3/internal/cache/DiskLruCache$Editor;)V", "dirtyFiles", "getDirtyFiles$okhttp", "getKey$okhttp", "()Ljava/lang/String;", "lengths", "", "getLengths$okhttp", "()[J", "lockingSourceCount", "", "getLockingSourceCount$okhttp", "()I", "setLockingSourceCount$okhttp", "(I)V", "readable", "", "getReadable$okhttp", "()Z", "setReadable$okhttp", "(Z)V", "sequenceNumber", "", "getSequenceNumber$okhttp", "()J", "setSequenceNumber$okhttp", "(J)V", "zombie", "getZombie$okhttp", "setZombie$okhttp", "invalidLengths", "", "strings", "", "newSource", "Lokio/Source;", "index", "setLengths", "", "setLengths$okhttp", "snapshot", "Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "snapshot$okhttp", "writeLengths", "writer", "Lokio/BufferedSink;", "writeLengths$okhttp", "okhttp"})
    public final class Entry {
        @NotNull
        private final long[] lengths;
        @NotNull
        private final List<File> cleanFiles;
        @NotNull
        private final List<File> dirtyFiles;
        private boolean readable;
        private boolean zombie;
        @Nullable
        private Editor currentEditor;
        private int lockingSourceCount;
        private long sequenceNumber;
        @NotNull
        private final String key;

        @NotNull
        public final long[] getLengths$okhttp() {
            return this.lengths;
        }

        @NotNull
        public final List<File> getCleanFiles$okhttp() {
            return this.cleanFiles;
        }

        @NotNull
        public final List<File> getDirtyFiles$okhttp() {
            return this.dirtyFiles;
        }

        public final boolean getReadable$okhttp() {
            return this.readable;
        }

        public final void setReadable$okhttp(boolean bl) {
            this.readable = bl;
        }

        public final boolean getZombie$okhttp() {
            return this.zombie;
        }

        public final void setZombie$okhttp(boolean bl) {
            this.zombie = bl;
        }

        @Nullable
        public final Editor getCurrentEditor$okhttp() {
            return this.currentEditor;
        }

        public final void setCurrentEditor$okhttp(@Nullable Editor editor) {
            this.currentEditor = editor;
        }

        public final int getLockingSourceCount$okhttp() {
            return this.lockingSourceCount;
        }

        public final void setLockingSourceCount$okhttp(int n) {
            this.lockingSourceCount = n;
        }

        public final long getSequenceNumber$okhttp() {
            return this.sequenceNumber;
        }

        public final void setSequenceNumber$okhttp(long l) {
            this.sequenceNumber = l;
        }

        /*
         * WARNING - void declaration
         */
        public final void setLengths$okhttp(@NotNull List<String> strings) throws IOException {
            Intrinsics.checkNotNullParameter(strings, "strings");
            if (strings.size() != DiskLruCache.this.getValueCount$okhttp()) {
                Void void_ = this.invalidLengths(strings);
                throw new KotlinNothingValueException();
            }
            try {
                int n = 0;
                int n2 = ((Collection)strings).size();
                while (n < n2) {
                    void i;
                    String string = strings.get((int)i);
                    boolean bl = false;
                    this.lengths[i] = Long.parseLong(string);
                    ++i;
                }
            } catch (NumberFormatException _) {
                Void void_ = this.invalidLengths(strings);
                throw new KotlinNothingValueException();
            }
        }

        public final void writeLengths$okhttp(@NotNull BufferedSink writer) throws IOException {
            Intrinsics.checkNotNullParameter(writer, "writer");
            for (long length : this.lengths) {
                writer.writeByte(32).writeDecimalLong(length);
            }
        }

        private final Void invalidLengths(List<String> strings) throws IOException {
            throw (Throwable)new IOException("unexpected journal line: " + strings);
        }

        /*
         * WARNING - void declaration
         */
        @Nullable
        public final Snapshot snapshot$okhttp() {
            DiskLruCache $this$assertThreadHoldsLock$iv = DiskLruCache.this;
            boolean $i$f$assertThreadHoldsLock = false;
            if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv)) {
                StringBuilder stringBuilder = new StringBuilder().append("Thread ");
                Thread thread2 = Thread.currentThread();
                Intrinsics.checkNotNullExpressionValue(thread2, "Thread.currentThread()");
                throw (Throwable)((Object)new AssertionError((Object)stringBuilder.append(thread2.getName()).append(" MUST hold lock on ").append($this$assertThreadHoldsLock$iv).toString()));
            }
            if (!this.readable) {
                return null;
            }
            if (!DiskLruCache.this.civilizedFileSystem && (this.currentEditor != null || this.zombie)) {
                return null;
            }
            $i$f$assertThreadHoldsLock = false;
            List sources = new ArrayList();
            long[] lengths = (long[])this.lengths.clone();
            try {
                int n = 0;
                int n2 = DiskLruCache.this.getValueCount$okhttp();
                while (n < n2) {
                    void i;
                    Collection collection = sources;
                    Source source2 = this.newSource((int)i);
                    boolean bl = false;
                    collection.add(source2);
                    ++i;
                }
                return new Snapshot(this.key, this.sequenceNumber, sources, lengths);
            } catch (FileNotFoundException _) {
                for (Source source3 : sources) {
                    Util.closeQuietly(source3);
                }
                try {
                    DiskLruCache.this.removeEntry$okhttp(this);
                } catch (IOException iOException) {
                    // empty catch block
                }
                return null;
            }
        }

        private final Source newSource(int index) {
            Source fileSource = DiskLruCache.this.getFileSystem$okhttp().source(this.cleanFiles.get(index));
            if (DiskLruCache.this.civilizedFileSystem) {
                return fileSource;
            }
            int n = this.lockingSourceCount;
            this.lockingSourceCount = n + 1;
            return new ForwardingSource(this, fileSource, fileSource){
                private boolean closed;
                final /* synthetic */ Entry this$0;
                final /* synthetic */ Source $fileSource;

                /*
                 * WARNING - Removed try catching itself - possible behaviour change.
                 */
                public void close() {
                    super.close();
                    if (!this.closed) {
                        this.closed = true;
                        DiskLruCache diskLruCache = this.this$0.DiskLruCache.this;
                        boolean bl = false;
                        boolean bl2 = false;
                        synchronized (diskLruCache) {
                            boolean bl3 = false;
                            Entry entry = this.this$0;
                            int n = entry.getLockingSourceCount$okhttp();
                            entry.setLockingSourceCount$okhttp(n + -1);
                            if (this.this$0.getLockingSourceCount$okhttp() == 0 && this.this$0.getZombie$okhttp()) {
                                this.this$0.DiskLruCache.this.removeEntry$okhttp(this.this$0);
                            }
                            Unit unit = Unit.INSTANCE;
                        }
                    }
                }
                {
                    this.this$0 = this$0;
                    this.$fileSource = $captured_local_variable$1;
                    super($super_call_param$2);
                }
            };
        }

        @NotNull
        public final String getKey$okhttp() {
            return this.key;
        }

        /*
         * WARNING - void declaration
         */
        public Entry(String key) {
            Intrinsics.checkNotNullParameter(key, "key");
            this.key = key;
            this.lengths = new long[DiskLruCache.this.getValueCount$okhttp()];
            boolean bl = false;
            this.cleanFiles = new ArrayList();
            bl = false;
            this.dirtyFiles = new ArrayList();
            StringBuilder fileBuilder = new StringBuilder(this.key).append('.');
            int truncateTo = fileBuilder.length();
            int n = 0;
            int n2 = DiskLruCache.this.getValueCount$okhttp();
            while (n < n2) {
                void i;
                fileBuilder.append((int)i);
                Collection collection = this.cleanFiles;
                File file = new File(DiskLruCache.this.getDirectory(), fileBuilder.toString());
                boolean bl2 = false;
                collection.add(file);
                fileBuilder.append(".tmp");
                collection = this.dirtyFiles;
                file = new File(DiskLruCache.this.getDirectory(), fileBuilder.toString());
                bl2 = false;
                collection.add(file);
                fileBuilder.setLength(truncateTo);
                ++i;
            }
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087D\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00068\u0006X\u0087D\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00068\u0006X\u0087D\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00068\u0006X\u0087D\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\u00068\u0006X\u0087D\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u00068\u0006X\u0087D\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\f8\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u00068\u0006X\u0087D\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u00068\u0006X\u0087D\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u00068\u0006X\u0087D\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u00068\u0006X\u0087D\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2={"Lokhttp3/internal/cache/DiskLruCache$Companion;", "", "()V", "ANY_SEQUENCE_NUMBER", "", "CLEAN", "", "DIRTY", "JOURNAL_FILE", "JOURNAL_FILE_BACKUP", "JOURNAL_FILE_TEMP", "LEGAL_KEY_PATTERN", "Lkotlin/text/Regex;", "MAGIC", "READ", "REMOVE", "VERSION_1", "okhttp"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

