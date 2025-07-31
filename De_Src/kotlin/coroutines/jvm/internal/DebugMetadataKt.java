/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.coroutines.jvm.internal;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.ModuleNameRetriever;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u00000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0002\u001a\u000e\u0010\u0006\u001a\u0004\u0018\u00010\u0007*\u00020\bH\u0002\u001a\f\u0010\t\u001a\u00020\u0001*\u00020\bH\u0002\u001a\u0019\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b*\u00020\bH\u0001\u00a2\u0006\u0002\u0010\r\u001a\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u00020\bH\u0001\u00a2\u0006\u0002\b\u0010\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2={"COROUTINES_DEBUG_METADATA_VERSION", "", "checkDebugMetadataVersion", "", "expected", "actual", "getDebugMetadataAnnotation", "Lkotlin/coroutines/jvm/internal/DebugMetadata;", "Lkotlin/coroutines/jvm/internal/BaseContinuationImpl;", "getLabel", "getSpilledVariableFieldMapping", "", "", "(Lkotlin/coroutines/jvm/internal/BaseContinuationImpl;)[Ljava/lang/String;", "getStackTraceElementImpl", "Ljava/lang/StackTraceElement;", "getStackTraceElement", "kotlin-stdlib"})
public final class DebugMetadataKt {
    private static final int COROUTINES_DEBUG_METADATA_VERSION = 1;

    @SinceKotlin(version="1.3")
    @JvmName(name="getStackTraceElement")
    @Nullable
    public static final StackTraceElement getStackTraceElement(@NotNull BaseContinuationImpl $this$getStackTraceElementImpl) {
        Intrinsics.checkNotNullParameter($this$getStackTraceElementImpl, "$this$getStackTraceElementImpl");
        DebugMetadata debugMetadata = DebugMetadataKt.getDebugMetadataAnnotation($this$getStackTraceElementImpl);
        if (debugMetadata == null) {
            return null;
        }
        DebugMetadata debugMetadata2 = debugMetadata;
        DebugMetadataKt.checkDebugMetadataVersion(1, debugMetadata2.v());
        int label = DebugMetadataKt.getLabel($this$getStackTraceElementImpl);
        int lineNumber = label < 0 ? -1 : debugMetadata2.l()[label];
        String moduleName = ModuleNameRetriever.INSTANCE.getModuleName($this$getStackTraceElementImpl);
        String moduleAndClass = moduleName == null ? debugMetadata2.c() : moduleName + '/' + debugMetadata2.c();
        return new StackTraceElement(moduleAndClass, debugMetadata2.m(), debugMetadata2.f(), lineNumber);
    }

    private static final DebugMetadata getDebugMetadataAnnotation(BaseContinuationImpl $this$getDebugMetadataAnnotation) {
        return $this$getDebugMetadataAnnotation.getClass().getAnnotation(DebugMetadata.class);
    }

    private static final int getLabel(BaseContinuationImpl $this$getLabel) {
        int n;
        try {
            Field field;
            Field field2 = field = $this$getLabel.getClass().getDeclaredField("label");
            Intrinsics.checkNotNullExpressionValue(field2, "field");
            field2.setAccessible(true);
            Object object = field.get($this$getLabel);
            if (!(object instanceof Integer)) {
                object = null;
            }
            Integer n2 = (Integer)object;
            n = (n2 != null ? n2 : 0) - 1;
        } catch (Exception e) {
            n = -1;
        }
        return n;
    }

    private static final void checkDebugMetadataVersion(int expected, int actual) {
        if (actual > expected) {
            String string = "Debug metadata version mismatch. Expected: " + expected + ", got " + actual + ". Please update the Kotlin standard library.";
            boolean bl = false;
            throw (Throwable)new IllegalStateException(string.toString());
        }
    }

    /*
     * WARNING - void declaration
     */
    @SinceKotlin(version="1.3")
    @JvmName(name="getSpilledVariableFieldMapping")
    @Nullable
    public static final String[] getSpilledVariableFieldMapping(@NotNull BaseContinuationImpl $this$getSpilledVariableFieldMapping) {
        Intrinsics.checkNotNullParameter($this$getSpilledVariableFieldMapping, "$this$getSpilledVariableFieldMapping");
        DebugMetadata debugMetadata = DebugMetadataKt.getDebugMetadataAnnotation($this$getSpilledVariableFieldMapping);
        if (debugMetadata == null) {
            return null;
        }
        DebugMetadata debugMetadata2 = debugMetadata;
        DebugMetadataKt.checkDebugMetadataVersion(1, debugMetadata2.v());
        boolean bl = false;
        ArrayList<String> res = new ArrayList<String>();
        int label = DebugMetadataKt.getLabel($this$getSpilledVariableFieldMapping);
        int[] nArray = debugMetadata2.i();
        int n = nArray.length;
        int n2 = 0;
        while (n2 < n) {
            void i;
            int labelOfIndex = nArray[n2];
            if (labelOfIndex == label) {
                res.add(debugMetadata2.s()[i]);
                res.add(debugMetadata2.n()[i]);
            }
            ++i;
        }
        Collection $this$toTypedArray$iv = res;
        boolean $i$f$toTypedArray = false;
        Collection thisCollection$iv = $this$toTypedArray$iv;
        String[] stringArray = thisCollection$iv.toArray(new String[0]);
        if (stringArray == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        return stringArray;
    }
}

