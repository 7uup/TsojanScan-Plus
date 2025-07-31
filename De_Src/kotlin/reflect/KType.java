/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.reflect;

import java.util.List;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.reflect.KAnnotatedElement;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KTypeProjection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001R \u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038&X\u00a7\u0004\u00a2\u0006\f\u0012\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\n8&X\u00a7\u0004\u00a2\u0006\f\u0012\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000fX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u0010\u00a8\u0006\u0011"}, d2={"Lkotlin/reflect/KType;", "Lkotlin/reflect/KAnnotatedElement;", "arguments", "", "Lkotlin/reflect/KTypeProjection;", "getArguments$annotations", "()V", "getArguments", "()Ljava/util/List;", "classifier", "Lkotlin/reflect/KClassifier;", "getClassifier$annotations", "getClassifier", "()Lkotlin/reflect/KClassifier;", "isMarkedNullable", "", "()Z", "kotlin-stdlib"})
public interface KType
extends KAnnotatedElement {
    @Nullable
    public KClassifier getClassifier();

    @NotNull
    public List<KTypeProjection> getArguments();

    public boolean isMarkedNullable();

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=3)
    public static final class DefaultImpls {
        @SinceKotlin(version="1.1")
        public static /* synthetic */ void getClassifier$annotations() {
        }

        @SinceKotlin(version="1.1")
        public static /* synthetic */ void getArguments$annotations() {
        }
    }
}

