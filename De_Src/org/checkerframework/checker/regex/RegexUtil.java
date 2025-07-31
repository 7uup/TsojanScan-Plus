/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.checker.regex;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.regex.qual.Regex;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.EnsuresQualifierIf;

public final class RegexUtil {
    private RegexUtil() {
        throw new Error("do not instantiate");
    }

    @Pure
    @EnsuresQualifierIf(result=true, expression={"#1"}, qualifier=Regex.class)
    public static boolean isRegex(String s2) {
        return RegexUtil.isRegex(s2, 0);
    }

    @Pure
    @EnsuresQualifierIf(result=true, expression={"#1"}, qualifier=Regex.class)
    public static boolean isRegex(String s2, int groups2) {
        Pattern p;
        try {
            p = Pattern.compile(s2);
        } catch (PatternSyntaxException e) {
            return false;
        }
        return RegexUtil.getGroupCount(p) >= groups2;
    }

    @Pure
    @EnsuresQualifierIf(result=true, expression={"#1"}, qualifier=Regex.class)
    public static boolean isRegex(char c) {
        return RegexUtil.isRegex(Character.toString(c));
    }

    @SideEffectFree
    public static @Nullable String regexError(String s2) {
        return RegexUtil.regexError(s2, 0);
    }

    @SideEffectFree
    public static @Nullable String regexError(String s2, int groups2) {
        try {
            Pattern p = Pattern.compile(s2);
            int actualGroups = RegexUtil.getGroupCount(p);
            if (actualGroups < groups2) {
                return RegexUtil.regexErrorMessage(s2, groups2, actualGroups);
            }
        } catch (PatternSyntaxException e) {
            return e.getMessage();
        }
        return null;
    }

    @SideEffectFree
    public static @Nullable PatternSyntaxException regexException(String s2) {
        return RegexUtil.regexException(s2, 0);
    }

    @SideEffectFree
    public static @Nullable PatternSyntaxException regexException(String s2, int groups2) {
        try {
            Pattern p = Pattern.compile(s2);
            int actualGroups = RegexUtil.getGroupCount(p);
            if (actualGroups < groups2) {
                return new PatternSyntaxException(RegexUtil.regexErrorMessage(s2, groups2, actualGroups), s2, -1);
            }
        } catch (PatternSyntaxException pse) {
            return pse;
        }
        return null;
    }

    @SideEffectFree
    public static @Regex String asRegex(String s2) {
        return RegexUtil.asRegex(s2, 0);
    }

    @SideEffectFree
    public static @Regex String asRegex(String s2, int groups2) {
        try {
            Pattern p = Pattern.compile(s2);
            int actualGroups = RegexUtil.getGroupCount(p);
            if (actualGroups < groups2) {
                throw new Error(RegexUtil.regexErrorMessage(s2, groups2, actualGroups));
            }
            return s2;
        } catch (PatternSyntaxException e) {
            throw new Error(e);
        }
    }

    @SideEffectFree
    private static String regexErrorMessage(String s2, int expectedGroups, int actualGroups) {
        return "regex \"" + s2 + "\" has " + actualGroups + " groups, but " + expectedGroups + " groups are needed.";
    }

    @Pure
    private static int getGroupCount(Pattern p) {
        return p.matcher("").groupCount();
    }

    public static class CheckedPatternSyntaxException
    extends Exception {
        private static final long serialVersionUID = 6266881831979001480L;
        private final PatternSyntaxException pse;

        public CheckedPatternSyntaxException(PatternSyntaxException pse) {
            this.pse = pse;
        }

        public CheckedPatternSyntaxException(String desc, String regex, @GTENegativeOne int index) {
            this(new PatternSyntaxException(desc, regex, index));
        }

        public String getDescription() {
            return this.pse.getDescription();
        }

        public int getIndex() {
            return this.pse.getIndex();
        }

        @Override
        @Pure
        public String getMessage(@GuardSatisfied CheckedPatternSyntaxException this) {
            return this.pse.getMessage();
        }

        public String getPattern() {
            return this.pse.getPattern();
        }
    }
}

