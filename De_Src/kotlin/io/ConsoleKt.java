/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.io;

import java.io.InputStream;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.io.LineReader;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0005\n\u0002\u0010\f\n\u0002\u0010\u0019\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u001a\u0013\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0087\b\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0004H\u0087\b\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0005H\u0087\b\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0007H\u0087\b\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\bH\u0087\b\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\tH\u0087\b\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\nH\u0087\b\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000bH\u0087\b\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\fH\u0087\b\u001a\t\u0010\r\u001a\u00020\u0001H\u0087\b\u001a\u0013\u0010\r\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0087\b\u001a\u0011\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0004H\u0087\b\u001a\u0011\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0005H\u0087\b\u001a\u0011\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0007H\u0087\b\u001a\u0011\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\bH\u0087\b\u001a\u0011\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\tH\u0087\b\u001a\u0011\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\nH\u0087\b\u001a\u0011\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000bH\u0087\b\u001a\u0011\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\fH\u0087\b\u001a\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u00a8\u0006\u0010"}, d2={"print", "", "message", "", "", "", "", "", "", "", "", "", "", "println", "readLine", "", "kotlin-stdlib"})
@JvmName(name="ConsoleKt")
public final class ConsoleKt {
    @InlineOnly
    private static final void print(Object message) {
        int $i$f$print = 0;
        System.out.print(message);
    }

    @InlineOnly
    private static final void print(int message) {
        int $i$f$print = 0;
        System.out.print(message);
    }

    @InlineOnly
    private static final void print(long message) {
        int $i$f$print = 0;
        System.out.print(message);
    }

    @InlineOnly
    private static final void print(byte message) {
        int $i$f$print = 0;
        System.out.print((Object)message);
    }

    @InlineOnly
    private static final void print(short message) {
        int $i$f$print = 0;
        System.out.print((Object)message);
    }

    @InlineOnly
    private static final void print(char message) {
        int $i$f$print = 0;
        System.out.print(message);
    }

    @InlineOnly
    private static final void print(boolean message) {
        int $i$f$print = 0;
        System.out.print(message);
    }

    @InlineOnly
    private static final void print(float message) {
        int $i$f$print = 0;
        System.out.print(message);
    }

    @InlineOnly
    private static final void print(double message) {
        int $i$f$print = 0;
        System.out.print(message);
    }

    @InlineOnly
    private static final void print(char[] message) {
        int $i$f$print = 0;
        System.out.print(message);
    }

    @InlineOnly
    private static final void println(Object message) {
        int $i$f$println = 0;
        System.out.println(message);
    }

    @InlineOnly
    private static final void println(int message) {
        int $i$f$println = 0;
        System.out.println(message);
    }

    @InlineOnly
    private static final void println(long message) {
        int $i$f$println = 0;
        System.out.println(message);
    }

    @InlineOnly
    private static final void println(byte message) {
        int $i$f$println = 0;
        System.out.println((Object)message);
    }

    @InlineOnly
    private static final void println(short message) {
        int $i$f$println = 0;
        System.out.println((Object)message);
    }

    @InlineOnly
    private static final void println(char message) {
        int $i$f$println = 0;
        System.out.println(message);
    }

    @InlineOnly
    private static final void println(boolean message) {
        int $i$f$println = 0;
        System.out.println(message);
    }

    @InlineOnly
    private static final void println(float message) {
        int $i$f$println = 0;
        System.out.println(message);
    }

    @InlineOnly
    private static final void println(double message) {
        int $i$f$println = 0;
        System.out.println(message);
    }

    @InlineOnly
    private static final void println(char[] message) {
        int $i$f$println = 0;
        System.out.println(message);
    }

    @InlineOnly
    private static final void println() {
        int $i$f$println = 0;
        System.out.println();
    }

    @Nullable
    public static final String readLine() {
        InputStream inputStream2 = System.in;
        Intrinsics.checkNotNullExpressionValue(inputStream2, "System.`in`");
        Charset charset = Charset.defaultCharset();
        Intrinsics.checkNotNullExpressionValue(charset, "Charset.defaultCharset()");
        return LineReader.INSTANCE.readLine(inputStream2, charset);
    }
}

