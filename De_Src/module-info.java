/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
module kotlin.stdlib {
    exports kotlin;
    exports kotlin.annotation;
    exports kotlin.collections;
    exports kotlin.collections.unsigned;
    exports kotlin.comparisons;
    exports kotlin.concurrent;
    exports kotlin.contracts;
    exports kotlin.coroutines;
    exports kotlin.coroutines.intrinsics;
    exports kotlin.coroutines.jvm.internal;
    exports kotlin.io;
    exports kotlin.jvm;
    exports kotlin.jvm.functions;
    exports kotlin.math;
    exports kotlin.properties;
    exports kotlin.random;
    exports kotlin.ranges;
    exports kotlin.reflect;
    exports kotlin.sequences;
    exports kotlin.system;
    exports kotlin.text;
    exports kotlin.time;
    exports kotlin.experimental;
    exports kotlin.internal;
    exports kotlin.jvm.internal;
    exports kotlin.jvm.internal.markers;

    opens kotlin to kotlin.reflect;
    opens kotlin.annotation to kotlin.reflect;
    opens kotlin.collections to kotlin.reflect;
    opens kotlin.coroutines to kotlin.reflect;
    opens kotlin.internal to kotlin.reflect;
    opens kotlin.ranges to kotlin.reflect;
    opens kotlin.reflect to kotlin.reflect;

}

