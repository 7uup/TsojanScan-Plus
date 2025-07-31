/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.io;

import java.io.File;
import kotlin.Metadata;
import kotlin.io.FileSystemException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u0007\u00a8\u0006\b"}, d2={"Lkotlin/io/NoSuchFileException;", "Lkotlin/io/FileSystemException;", "file", "Ljava/io/File;", "other", "reason", "", "(Ljava/io/File;Ljava/io/File;Ljava/lang/String;)V", "kotlin-stdlib"})
public final class NoSuchFileException
extends FileSystemException {
    public NoSuchFileException(@NotNull File file, @Nullable File other, @Nullable String reason) {
        Intrinsics.checkNotNullParameter(file, "file");
        super(file, other, reason);
    }

    public /* synthetic */ NoSuchFileException(File file, File file2, String string, int n, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n & 2) != 0) {
            file2 = null;
        }
        if ((n & 4) != 0) {
            string = null;
        }
        this(file, file2, string);
    }
}

