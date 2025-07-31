/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.Okio;
import okio.Sink;
import okio.Source;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0005H&J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0005H&J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0015"}, d2={"Lokhttp3/internal/io/FileSystem;", "", "appendingSink", "Lokio/Sink;", "file", "Ljava/io/File;", "delete", "", "deleteContents", "directory", "exists", "", "rename", "from", "to", "sink", "size", "", "source", "Lokio/Source;", "Companion", "okhttp"})
public interface FileSystem {
    @JvmField
    @NotNull
    public static final FileSystem SYSTEM;
    public static final Companion Companion;

    @NotNull
    public Source source(@NotNull File var1) throws FileNotFoundException;

    @NotNull
    public Sink sink(@NotNull File var1) throws FileNotFoundException;

    @NotNull
    public Sink appendingSink(@NotNull File var1) throws FileNotFoundException;

    public void delete(@NotNull File var1) throws IOException;

    public boolean exists(@NotNull File var1);

    public long size(@NotNull File var1);

    public void rename(@NotNull File var1, @NotNull File var2) throws IOException;

    public void deleteContents(@NotNull File var1) throws IOException;

    static {
        Companion = new Companion(null);
        SYSTEM = new Companion.SystemFileSystem();
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\u0005B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0001\u0082\u0002\u0007\n\u0005\b\u0091F0\u0001\u00a8\u0006\u0006"}, d2={"Lokhttp3/internal/io/FileSystem$Companion;", "", "()V", "SYSTEM", "Lokhttp3/internal/io/FileSystem;", "SystemFileSystem", "okhttp"})
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE;

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u0006H\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0018\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006H\u0016J\u0010\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016\u00a8\u0006\u0017"}, d2={"Lokhttp3/internal/io/FileSystem$Companion$SystemFileSystem;", "Lokhttp3/internal/io/FileSystem;", "()V", "appendingSink", "Lokio/Sink;", "file", "Ljava/io/File;", "delete", "", "deleteContents", "directory", "exists", "", "rename", "from", "to", "sink", "size", "", "source", "Lokio/Source;", "toString", "", "okhttp"})
        private static final class SystemFileSystem
        implements FileSystem {
            @Override
            @NotNull
            public Source source(@NotNull File file) throws FileNotFoundException {
                Intrinsics.checkNotNullParameter(file, "file");
                return Okio.source(file);
            }

            @Override
            @NotNull
            public Sink sink(@NotNull File file) throws FileNotFoundException {
                Sink sink2;
                Intrinsics.checkNotNullParameter(file, "file");
                try {
                    sink2 = Okio.sink$default(file, false, 1, null);
                } catch (FileNotFoundException _) {
                    file.getParentFile().mkdirs();
                    sink2 = Okio.sink$default(file, false, 1, null);
                }
                return sink2;
            }

            @Override
            @NotNull
            public Sink appendingSink(@NotNull File file) throws FileNotFoundException {
                Sink sink2;
                Intrinsics.checkNotNullParameter(file, "file");
                try {
                    sink2 = Okio.appendingSink(file);
                } catch (FileNotFoundException _) {
                    file.getParentFile().mkdirs();
                    sink2 = Okio.appendingSink(file);
                }
                return sink2;
            }

            @Override
            public void delete(@NotNull File file) throws IOException {
                Intrinsics.checkNotNullParameter(file, "file");
                if (!file.delete() && file.exists()) {
                    throw (Throwable)new IOException("failed to delete " + file);
                }
            }

            @Override
            public boolean exists(@NotNull File file) {
                Intrinsics.checkNotNullParameter(file, "file");
                return file.exists();
            }

            @Override
            public long size(@NotNull File file) {
                Intrinsics.checkNotNullParameter(file, "file");
                return file.length();
            }

            @Override
            public void rename(@NotNull File from, @NotNull File to) throws IOException {
                Intrinsics.checkNotNullParameter(from, "from");
                Intrinsics.checkNotNullParameter(to, "to");
                this.delete(to);
                if (!from.renameTo(to)) {
                    throw (Throwable)new IOException("failed to rename " + from + " to " + to);
                }
            }

            @Override
            public void deleteContents(@NotNull File directory) throws IOException {
                File[] files;
                Intrinsics.checkNotNullParameter(directory, "directory");
                File[] fileArray = directory.listFiles();
                if (fileArray == null) {
                    throw (Throwable)new IOException("not a readable directory: " + directory);
                }
                File[] fileArray2 = files = fileArray;
                int n = fileArray2.length;
                for (int i = 0; i < n; ++i) {
                    File file;
                    File file2 = file = fileArray2[i];
                    Intrinsics.checkNotNullExpressionValue(file2, "file");
                    if (file2.isDirectory()) {
                        this.deleteContents(file);
                    }
                    if (file.delete()) continue;
                    throw (Throwable)new IOException("failed to delete " + file);
                }
            }

            @NotNull
            public String toString() {
                return "FileSystem.SYSTEM";
            }
        }
    }
}

