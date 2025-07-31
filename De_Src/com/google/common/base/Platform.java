/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.CharMatcher;
import com.google.common.base.CommonPattern;
import com.google.common.base.Enums;
import com.google.common.base.JdkPattern;
import com.google.common.base.Optional;
import com.google.common.base.PatternCompiler;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import java.lang.ref.WeakReference;
import java.util.Locale;
import java.util.ServiceConfigurationError;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(emulated=true)
final class Platform {
    private static final Logger logger = Logger.getLogger(Platform.class.getName());
    private static final PatternCompiler patternCompiler = Platform.loadPatternCompiler();

    private Platform() {
    }

    static long systemNanoTime() {
        return System.nanoTime();
    }

    static CharMatcher precomputeCharMatcher(CharMatcher matcher) {
        return matcher.precomputedInternal();
    }

    static <T extends Enum<T>> Optional<T> getEnumIfPresent(Class<T> enumClass, String value) {
        WeakReference<Enum<?>> ref = Enums.getEnumConstants(enumClass).get(value);
        return ref == null ? Optional.absent() : Optional.of(enumClass.cast(ref.get()));
    }

    static String formatCompact4Digits(double value) {
        return String.format(Locale.ROOT, "%.4g", value);
    }

    static boolean stringIsNullOrEmpty(@Nullable String string) {
        return string == null || string.isEmpty();
    }

    static String nullToEmpty(@Nullable String string) {
        return string == null ? "" : string;
    }

    static String emptyToNull(@Nullable String string) {
        return Platform.stringIsNullOrEmpty(string) ? null : string;
    }

    static CommonPattern compilePattern(String pattern) {
        Preconditions.checkNotNull(pattern);
        return patternCompiler.compile(pattern);
    }

    static boolean patternCompilerIsPcreLike() {
        return patternCompiler.isPcreLike();
    }

    private static PatternCompiler loadPatternCompiler() {
        return new JdkPatternCompiler();
    }

    private static void logPatternCompilerError(ServiceConfigurationError e) {
        logger.log(Level.WARNING, "Error loading regex compiler, falling back to next option", e);
    }

    static void checkGwtRpcEnabled() {
        String propertyName = "guava.gwt.emergency_reenable_rpc";
        if (!Boolean.parseBoolean(System.getProperty(propertyName, "false"))) {
            throw new UnsupportedOperationException(Strings.lenientFormat("We are removing GWT-RPC support for Guava types. You can temporarily reenable support by setting the system property %s to true. For more about system properties, see %s. For more about Guava's GWT-RPC support, see %s.", propertyName, "https://stackoverflow.com/q/5189914/28465", "https://groups.google.com/d/msg/guava-announce/zHZTFg7YF3o/rQNnwdHeEwAJ"));
        }
        logger.log(Level.WARNING, "Later in 2020, we will remove GWT-RPC support for Guava types. You are seeing this warning because you are sending a Guava type over GWT-RPC, which will break. You can identify which type by looking at the class name in the attached stack trace.", new Throwable());
    }

    private static final class JdkPatternCompiler
    implements PatternCompiler {
        private JdkPatternCompiler() {
        }

        @Override
        public CommonPattern compile(String pattern) {
            return new JdkPattern(Pattern.compile(pattern));
        }

        @Override
        public boolean isPcreLike() {
            return true;
        }
    }
}

