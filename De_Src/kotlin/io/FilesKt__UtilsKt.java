/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.io;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FileAlreadyExistsException;
import kotlin.io.FilePathComponents;
import kotlin.io.FileSystemException;
import kotlin.io.FilesKt;
import kotlin.io.FilesKt__FileTreeWalkKt;
import kotlin.io.FilesKt__UtilsKt;
import kotlin.io.NoSuchFileException;
import kotlin.io.OnErrorAction;
import kotlin.io.TerminateException;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=5, xi=1, d1={"\u0000<\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u001a(\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0002\u001a(\u0010\r\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0002\u001a8\u0010\u000e\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\u001a\b\u0002\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0013\u001a&\u0010\u0016\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\b\b\u0002\u0010\u0017\u001a\u00020\u0018\u001a\n\u0010\u0019\u001a\u00020\u000f*\u00020\u0002\u001a\u0012\u0010\u001a\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0002\u001a\u0012\u0010\u001a\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0001\u001a\n\u0010\u001c\u001a\u00020\u0002*\u00020\u0002\u001a\u001d\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00020\u001d*\b\u0012\u0004\u0012\u00020\u00020\u001dH\u0002\u00a2\u0006\u0002\b\u001e\u001a\u0011\u0010\u001c\u001a\u00020\u001f*\u00020\u001fH\u0002\u00a2\u0006\u0002\b\u001e\u001a\u0012\u0010 \u001a\u00020\u0002*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u0014\u0010\"\u001a\u0004\u0018\u00010\u0002*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u0012\u0010#\u001a\u00020\u0002*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u0012\u0010$\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0002\u001a\u0012\u0010$\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0001\u001a\u0012\u0010&\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0002\u001a\u0012\u0010&\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0001\u001a\u0012\u0010'\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0002\u001a\u0012\u0010'\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0001\u001a\u0012\u0010(\u001a\u00020\u0001*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u001b\u0010)\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002H\u0002\u00a2\u0006\u0002\b*\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F\u00a2\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00028F\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004\"\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u00028F\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\u0004\u00a8\u0006+"}, d2={"extension", "", "Ljava/io/File;", "getExtension", "(Ljava/io/File;)Ljava/lang/String;", "invariantSeparatorsPath", "getInvariantSeparatorsPath", "nameWithoutExtension", "getNameWithoutExtension", "createTempDir", "prefix", "suffix", "directory", "createTempFile", "copyRecursively", "", "target", "overwrite", "onError", "Lkotlin/Function2;", "Ljava/io/IOException;", "Lkotlin/io/OnErrorAction;", "copyTo", "bufferSize", "", "deleteRecursively", "endsWith", "other", "normalize", "", "normalize$FilesKt__UtilsKt", "Lkotlin/io/FilePathComponents;", "relativeTo", "base", "relativeToOrNull", "relativeToOrSelf", "resolve", "relative", "resolveSibling", "startsWith", "toRelativeString", "toRelativeStringOrNull", "toRelativeStringOrNull$FilesKt__UtilsKt", "kotlin-stdlib"}, xs="kotlin/io/FilesKt")
class FilesKt__UtilsKt
extends FilesKt__FileTreeWalkKt {
    @NotNull
    public static final File createTempDir(@NotNull String prefix, @Nullable String suffix, @Nullable File directory) {
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        File dir2 = File.createTempFile(prefix, suffix, directory);
        dir2.delete();
        if (dir2.mkdir()) {
            File file = dir2;
            Intrinsics.checkNotNullExpressionValue(file, "dir");
            return file;
        }
        throw (Throwable)new IOException("Unable to create temporary directory " + dir2 + '.');
    }

    public static /* synthetic */ File createTempDir$default(String string, String string2, File file, int n, Object object) {
        if ((n & 1) != 0) {
            string = "tmp";
        }
        if ((n & 2) != 0) {
            string2 = null;
        }
        if ((n & 4) != 0) {
            file = null;
        }
        return FilesKt.createTempDir(string, string2, file);
    }

    @NotNull
    public static final File createTempFile(@NotNull String prefix, @Nullable String suffix, @Nullable File directory) {
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        File file = File.createTempFile(prefix, suffix, directory);
        Intrinsics.checkNotNullExpressionValue(file, "File.createTempFile(prefix, suffix, directory)");
        return file;
    }

    public static /* synthetic */ File createTempFile$default(String string, String string2, File file, int n, Object object) {
        if ((n & 1) != 0) {
            string = "tmp";
        }
        if ((n & 2) != 0) {
            string2 = null;
        }
        if ((n & 4) != 0) {
            file = null;
        }
        return FilesKt.createTempFile(string, string2, file);
    }

    @NotNull
    public static final String getExtension(@NotNull File $this$extension) {
        Intrinsics.checkNotNullParameter($this$extension, "$this$extension");
        String string = $this$extension.getName();
        Intrinsics.checkNotNullExpressionValue(string, "name");
        return StringsKt.substringAfterLast(string, '.', "");
    }

    @NotNull
    public static final String getInvariantSeparatorsPath(@NotNull File $this$invariantSeparatorsPath) {
        String string;
        Intrinsics.checkNotNullParameter($this$invariantSeparatorsPath, "$this$invariantSeparatorsPath");
        if (File.separatorChar != '/') {
            String string2 = $this$invariantSeparatorsPath.getPath();
            Intrinsics.checkNotNullExpressionValue(string2, "path");
            string = StringsKt.replace$default(string2, File.separatorChar, '/', false, 4, null);
        } else {
            String string3 = $this$invariantSeparatorsPath.getPath();
            string = string3;
            Intrinsics.checkNotNullExpressionValue(string3, "path");
        }
        return string;
    }

    @NotNull
    public static final String getNameWithoutExtension(@NotNull File $this$nameWithoutExtension) {
        Intrinsics.checkNotNullParameter($this$nameWithoutExtension, "$this$nameWithoutExtension");
        String string = $this$nameWithoutExtension.getName();
        Intrinsics.checkNotNullExpressionValue(string, "name");
        return StringsKt.substringBeforeLast$default(string, ".", null, 2, null);
    }

    @NotNull
    public static final String toRelativeString(@NotNull File $this$toRelativeString, @NotNull File base) {
        Intrinsics.checkNotNullParameter($this$toRelativeString, "$this$toRelativeString");
        Intrinsics.checkNotNullParameter(base, "base");
        String string = FilesKt__UtilsKt.toRelativeStringOrNull$FilesKt__UtilsKt($this$toRelativeString, base);
        if (string == null) {
            throw (Throwable)new IllegalArgumentException("this and base files have different roots: " + $this$toRelativeString + " and " + base + '.');
        }
        return string;
    }

    @NotNull
    public static final File relativeTo(@NotNull File $this$relativeTo, @NotNull File base) {
        Intrinsics.checkNotNullParameter($this$relativeTo, "$this$relativeTo");
        Intrinsics.checkNotNullParameter(base, "base");
        return new File(FilesKt.toRelativeString($this$relativeTo, base));
    }

    @NotNull
    public static final File relativeToOrSelf(@NotNull File $this$relativeToOrSelf, @NotNull File base) {
        File file;
        Intrinsics.checkNotNullParameter($this$relativeToOrSelf, "$this$relativeToOrSelf");
        Intrinsics.checkNotNullParameter(base, "base");
        String string = FilesKt__UtilsKt.toRelativeStringOrNull$FilesKt__UtilsKt($this$relativeToOrSelf, base);
        if (string != null) {
            String string2 = string;
            boolean bl = false;
            boolean bl2 = false;
            String p1 = string2;
            boolean bl3 = false;
            file = new File(p1);
        } else {
            file = $this$relativeToOrSelf;
        }
        return file;
    }

    @Nullable
    public static final File relativeToOrNull(@NotNull File $this$relativeToOrNull, @NotNull File base) {
        File file;
        Intrinsics.checkNotNullParameter($this$relativeToOrNull, "$this$relativeToOrNull");
        Intrinsics.checkNotNullParameter(base, "base");
        String string = FilesKt__UtilsKt.toRelativeStringOrNull$FilesKt__UtilsKt($this$relativeToOrNull, base);
        if (string != null) {
            String string2 = string;
            boolean bl = false;
            boolean bl2 = false;
            String p1 = string2;
            boolean bl3 = false;
            file = new File(p1);
        } else {
            file = null;
        }
        return file;
    }

    /*
     * WARNING - void declaration
     */
    private static final String toRelativeStringOrNull$FilesKt__UtilsKt(File $this$toRelativeStringOrNull, File base) {
        int i;
        FilePathComponents thisComponents = FilesKt__UtilsKt.normalize$FilesKt__UtilsKt(FilesKt.toComponents($this$toRelativeStringOrNull));
        FilePathComponents baseComponents = FilesKt__UtilsKt.normalize$FilesKt__UtilsKt(FilesKt.toComponents(base));
        if (Intrinsics.areEqual(thisComponents.getRoot(), baseComponents.getRoot()) ^ true) {
            return null;
        }
        int baseCount = baseComponents.getSize();
        int thisCount = thisComponents.getSize();
        File file = $this$toRelativeStringOrNull;
        int n = 0;
        int n2 = 0;
        File $this$countSame = file;
        boolean bl = false;
        int n3 = thisCount;
        int n4 = baseCount;
        boolean bl2 = false;
        int maxSameCount = Math.min(n3, n4);
        for (i = 0; i < maxSameCount && Intrinsics.areEqual(thisComponents.getSegments().get(i), baseComponents.getSegments().get(i)); ++i) {
        }
        int sameCount = i;
        StringBuilder res = new StringBuilder();
        n = baseCount - 1;
        n2 = sameCount;
        if (n >= n2) {
            while (true) {
                void i2;
                if (Intrinsics.areEqual(baseComponents.getSegments().get((int)i2).getName(), "..")) {
                    return null;
                }
                res.append("..");
                if (i2 != sameCount) {
                    res.append(File.separatorChar);
                }
                if (i2 == n2) break;
                --i2;
            }
        }
        if (sameCount < thisCount) {
            if (sameCount < baseCount) {
                res.append(File.separatorChar);
            }
            Iterable iterable = CollectionsKt.drop((Iterable)thisComponents.getSegments(), sameCount);
            Appendable appendable = res;
            String string = File.separator;
            Intrinsics.checkNotNullExpressionValue(string, "File.separator");
            CollectionsKt.joinTo$default(iterable, appendable, string, null, null, 0, null, null, 124, null);
        }
        return res.toString();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @NotNull
    public static final File copyTo(@NotNull File $this$copyTo, @NotNull File target, boolean overwrite, int bufferSize) {
        Intrinsics.checkNotNullParameter($this$copyTo, "$this$copyTo");
        Intrinsics.checkNotNullParameter(target, "target");
        if (!$this$copyTo.exists()) {
            throw (Throwable)new NoSuchFileException($this$copyTo, null, "The source file doesn't exist.", 2, null);
        }
        if (target.exists()) {
            if (!overwrite) {
                throw (Throwable)new FileAlreadyExistsException($this$copyTo, target, "The destination file already exists.");
            }
            if (!target.delete()) {
                throw (Throwable)new FileAlreadyExistsException($this$copyTo, target, "Tried to overwrite the destination, but failed to delete it.");
            }
        }
        if ($this$copyTo.isDirectory()) {
            if (!target.mkdirs()) {
                throw (Throwable)new FileSystemException($this$copyTo, target, "Failed to create target directory.");
            }
        } else {
            File file = target.getParentFile();
            if (file != null) {
                file.mkdirs();
            }
            Object object = $this$copyTo;
            boolean bl = false;
            object = new FileInputStream((File)object);
            bl = false;
            boolean bl2 = false;
            Throwable throwable = null;
            try {
                long l;
                FileInputStream input = (FileInputStream)object;
                boolean bl3 = false;
                Object object2 = target;
                boolean bl4 = false;
                object2 = new FileOutputStream((File)object2);
                bl4 = false;
                boolean bl5 = false;
                Throwable throwable2 = null;
                try {
                    FileOutputStream output = (FileOutputStream)object2;
                    boolean bl6 = false;
                    l = ByteStreamsKt.copyTo(input, output, bufferSize);
                } catch (Throwable throwable3) {
                    throwable2 = throwable3;
                    throw throwable3;
                } finally {
                    CloseableKt.closeFinally((Closeable)object2, throwable2);
                }
                long l2 = l;
            } catch (Throwable throwable4) {
                throwable = throwable4;
                throw throwable4;
            } finally {
                CloseableKt.closeFinally((Closeable)object, throwable);
            }
        }
        return target;
    }

    public static /* synthetic */ File copyTo$default(File file, File file2, boolean bl, int n, int n2, Object object) {
        if ((n2 & 2) != 0) {
            bl = false;
        }
        if ((n2 & 4) != 0) {
            n = 8192;
        }
        return FilesKt.copyTo(file, file2, bl, n);
    }

    public static final boolean copyRecursively(@NotNull File $this$copyRecursively, @NotNull File target, boolean overwrite, @NotNull Function2<? super File, ? super IOException, ? extends OnErrorAction> onError) {
        Intrinsics.checkNotNullParameter($this$copyRecursively, "$this$copyRecursively");
        Intrinsics.checkNotNullParameter(target, "target");
        Intrinsics.checkNotNullParameter(onError, "onError");
        if (!$this$copyRecursively.exists()) {
            return onError.invoke($this$copyRecursively, new NoSuchFileException($this$copyRecursively, null, "The source file doesn't exist.", 2, null)) != OnErrorAction.TERMINATE;
        }
        try {
            Iterator<File> iterator2 = FilesKt.walkTopDown($this$copyRecursively).onFail((Function2<? super File, ? super IOException, Unit>)new Function2<File, IOException, Unit>(onError){
                final /* synthetic */ Function2 $onError;

                public final void invoke(@NotNull File f, @NotNull IOException e) {
                    Intrinsics.checkNotNullParameter(f, "f");
                    Intrinsics.checkNotNullParameter(e, "e");
                    if ((OnErrorAction)((Object)this.$onError.invoke(f, e)) == OnErrorAction.TERMINATE) {
                        throw (Throwable)new TerminateException(f);
                    }
                }
                {
                    this.$onError = function2;
                    super(2);
                }
            }).iterator();
            while (iterator2.hasNext()) {
                File src = iterator2.next();
                if (!src.exists()) {
                    if (onError.invoke(src, new NoSuchFileException(src, null, "The source file doesn't exist.", 2, null)) != OnErrorAction.TERMINATE) continue;
                    return false;
                }
                String relPath = FilesKt.toRelativeString(src, $this$copyRecursively);
                File dstFile = new File(target, relPath);
                if (!(!dstFile.exists() || src.isDirectory() && dstFile.isDirectory())) {
                    boolean stillExists;
                    boolean bl = !overwrite ? true : (dstFile.isDirectory() ? !FilesKt.deleteRecursively(dstFile) : (stillExists = !dstFile.delete()));
                    if (stillExists) {
                        if (onError.invoke(dstFile, new FileAlreadyExistsException(src, dstFile, "The destination file already exists.")) != OnErrorAction.TERMINATE) continue;
                        return false;
                    }
                }
                if (src.isDirectory()) {
                    dstFile.mkdirs();
                    continue;
                }
                if (FilesKt.copyTo$default(src, dstFile, overwrite, 0, 4, null).length() == src.length() || onError.invoke(src, new IOException("Source file wasn't copied completely, length of destination file differs.")) != OnErrorAction.TERMINATE) continue;
                return false;
            }
            return true;
        } catch (TerminateException e) {
            return false;
        }
    }

    public static /* synthetic */ boolean copyRecursively$default(File file, File file2, boolean bl, Function2 function2, int n, Object object) {
        if ((n & 2) != 0) {
            bl = false;
        }
        if ((n & 4) != 0) {
            function2 = copyRecursively.1.INSTANCE;
        }
        return FilesKt.copyRecursively(file, file2, bl, function2);
    }

    /*
     * WARNING - void declaration
     */
    public static final boolean deleteRecursively(@NotNull File $this$deleteRecursively) {
        void $this$fold$iv;
        Intrinsics.checkNotNullParameter($this$deleteRecursively, "$this$deleteRecursively");
        Sequence sequence = FilesKt.walkBottomUp($this$deleteRecursively);
        boolean initial$iv = true;
        boolean $i$f$fold = false;
        boolean accumulator$iv = initial$iv;
        for (Object element$iv : $this$fold$iv) {
            void it;
            File file = (File)element$iv;
            boolean res = accumulator$iv;
            boolean bl = false;
            accumulator$iv = (it.delete() || !it.exists()) && res;
        }
        return accumulator$iv;
    }

    public static final boolean startsWith(@NotNull File $this$startsWith, @NotNull File other) {
        Intrinsics.checkNotNullParameter($this$startsWith, "$this$startsWith");
        Intrinsics.checkNotNullParameter(other, "other");
        FilePathComponents components = FilesKt.toComponents($this$startsWith);
        FilePathComponents otherComponents = FilesKt.toComponents(other);
        if (Intrinsics.areEqual(components.getRoot(), otherComponents.getRoot()) ^ true) {
            return false;
        }
        return components.getSize() < otherComponents.getSize() ? false : ((Object)components.getSegments().subList(0, otherComponents.getSize())).equals(otherComponents.getSegments());
    }

    public static final boolean startsWith(@NotNull File $this$startsWith, @NotNull String other) {
        Intrinsics.checkNotNullParameter($this$startsWith, "$this$startsWith");
        Intrinsics.checkNotNullParameter(other, "other");
        return FilesKt.startsWith($this$startsWith, new File(other));
    }

    public static final boolean endsWith(@NotNull File $this$endsWith, @NotNull File other) {
        Intrinsics.checkNotNullParameter($this$endsWith, "$this$endsWith");
        Intrinsics.checkNotNullParameter(other, "other");
        FilePathComponents components = FilesKt.toComponents($this$endsWith);
        FilePathComponents otherComponents = FilesKt.toComponents(other);
        if (otherComponents.isRooted()) {
            return Intrinsics.areEqual($this$endsWith, other);
        }
        int shift = components.getSize() - otherComponents.getSize();
        return shift < 0 ? false : ((Object)components.getSegments().subList(shift, components.getSize())).equals(otherComponents.getSegments());
    }

    public static final boolean endsWith(@NotNull File $this$endsWith, @NotNull String other) {
        Intrinsics.checkNotNullParameter($this$endsWith, "$this$endsWith");
        Intrinsics.checkNotNullParameter(other, "other");
        return FilesKt.endsWith($this$endsWith, new File(other));
    }

    @NotNull
    public static final File normalize(@NotNull File $this$normalize) {
        Intrinsics.checkNotNullParameter($this$normalize, "$this$normalize");
        FilePathComponents filePathComponents = FilesKt.toComponents($this$normalize);
        boolean bl = false;
        boolean bl2 = false;
        FilePathComponents $this$with = filePathComponents;
        boolean bl3 = false;
        File file = $this$with.getRoot();
        Iterable iterable = FilesKt__UtilsKt.normalize$FilesKt__UtilsKt($this$with.getSegments());
        String string = File.separator;
        Intrinsics.checkNotNullExpressionValue(string, "File.separator");
        return FilesKt.resolve(file, CollectionsKt.joinToString$default(iterable, string, null, null, 0, null, null, 62, null));
    }

    private static final FilePathComponents normalize$FilesKt__UtilsKt(FilePathComponents $this$normalize) {
        return new FilePathComponents($this$normalize.getRoot(), FilesKt__UtilsKt.normalize$FilesKt__UtilsKt($this$normalize.getSegments()));
    }

    /*
     * Could not resolve type clashes
     * Unable to fully structure code
     */
    private static final List<File> normalize$FilesKt__UtilsKt(List<? extends File> $this$normalize) {
        list = new ArrayList<E>($this$normalize.size());
        for (File file : $this$normalize) {
            if (file.getName() == null) ** GOTO lbl-1000
            tmp = -1;
            switch (var4_4.hashCode()) {
                case 1472: {
                    if (!var4_4.equals("..")) break;
                    tmp = 1;
                    break;
                }
                case 46: {
                    if (!var4_4.equals(".")) break;
                    tmp = 2;
                    break;
                }
            }
            switch (tmp) {
                case 1: {
                    if (!list.isEmpty() && Intrinsics.areEqual(((File)CollectionsKt.last(list)).getName(), "..") ^ true) {
                        list.remove(list.size() - 1);
                        break;
                    }
                    list.add(file);
                    break;
                }
                default: lbl-1000:
                // 2 sources

                {
                    list.add(file);
                }
                case 2: 
            }
        }
        return list;
    }

    @NotNull
    public static final File resolve(@NotNull File $this$resolve, @NotNull File relative) {
        Intrinsics.checkNotNullParameter($this$resolve, "$this$resolve");
        Intrinsics.checkNotNullParameter(relative, "relative");
        if (FilesKt.isRooted(relative)) {
            return relative;
        }
        String string = $this$resolve.toString();
        Intrinsics.checkNotNullExpressionValue(string, "this.toString()");
        String baseName = string;
        CharSequence charSequence = baseName;
        boolean bl = false;
        return charSequence.length() == 0 || StringsKt.endsWith$default((CharSequence)baseName, File.separatorChar, false, 2, null) ? new File(baseName + relative) : new File(baseName + File.separatorChar + relative);
    }

    @NotNull
    public static final File resolve(@NotNull File $this$resolve, @NotNull String relative) {
        Intrinsics.checkNotNullParameter($this$resolve, "$this$resolve");
        Intrinsics.checkNotNullParameter(relative, "relative");
        return FilesKt.resolve($this$resolve, new File(relative));
    }

    @NotNull
    public static final File resolveSibling(@NotNull File $this$resolveSibling, @NotNull File relative) {
        Intrinsics.checkNotNullParameter($this$resolveSibling, "$this$resolveSibling");
        Intrinsics.checkNotNullParameter(relative, "relative");
        FilePathComponents components = FilesKt.toComponents($this$resolveSibling);
        File parentSubPath = components.getSize() == 0 ? new File("..") : components.subPath(0, components.getSize() - 1);
        return FilesKt.resolve(FilesKt.resolve(components.getRoot(), parentSubPath), relative);
    }

    @NotNull
    public static final File resolveSibling(@NotNull File $this$resolveSibling, @NotNull String relative) {
        Intrinsics.checkNotNullParameter($this$resolveSibling, "$this$resolveSibling");
        Intrinsics.checkNotNullParameter(relative, "relative");
        return FilesKt.resolveSibling($this$resolveSibling, new File(relative));
    }
}

