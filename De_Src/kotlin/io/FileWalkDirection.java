/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.io;

import kotlin.Metadata;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004\u00a8\u0006\u0005"}, d2={"Lkotlin/io/FileWalkDirection;", "", "(Ljava/lang/String;I)V", "TOP_DOWN", "BOTTOM_UP", "kotlin-stdlib"})
public final class FileWalkDirection
extends Enum<FileWalkDirection> {
    public static final /* enum */ FileWalkDirection TOP_DOWN;
    public static final /* enum */ FileWalkDirection BOTTOM_UP;
    private static final /* synthetic */ FileWalkDirection[] $VALUES;

    static {
        FileWalkDirection[] fileWalkDirectionArray = new FileWalkDirection[2];
        FileWalkDirection[] fileWalkDirectionArray2 = fileWalkDirectionArray;
        fileWalkDirectionArray[0] = TOP_DOWN = new FileWalkDirection();
        fileWalkDirectionArray[1] = BOTTOM_UP = new FileWalkDirection();
        $VALUES = fileWalkDirectionArray;
    }

    public static FileWalkDirection[] values() {
        return (FileWalkDirection[])$VALUES.clone();
    }

    public static FileWalkDirection valueOf(String string) {
        return Enum.valueOf(FileWalkDirection.class, string);
    }
}

