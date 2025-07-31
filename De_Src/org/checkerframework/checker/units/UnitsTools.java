/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.checker.units;

import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.checkerframework.checker.units.qual.K;
import org.checkerframework.checker.units.qual.cd;
import org.checkerframework.checker.units.qual.degrees;
import org.checkerframework.checker.units.qual.g;
import org.checkerframework.checker.units.qual.h;
import org.checkerframework.checker.units.qual.kg;
import org.checkerframework.checker.units.qual.km;
import org.checkerframework.checker.units.qual.km2;
import org.checkerframework.checker.units.qual.kmPERh;
import org.checkerframework.checker.units.qual.m;
import org.checkerframework.checker.units.qual.m2;
import org.checkerframework.checker.units.qual.mPERs;
import org.checkerframework.checker.units.qual.mPERs2;
import org.checkerframework.checker.units.qual.min;
import org.checkerframework.checker.units.qual.mm;
import org.checkerframework.checker.units.qual.mm2;
import org.checkerframework.checker.units.qual.mol;
import org.checkerframework.checker.units.qual.radians;
import org.checkerframework.checker.units.qual.s;

public class UnitsTools {
    public static final @mPERs2 int mPERs2 = 1;
    public static final @radians double rad = 1.0;
    public static final @degrees double deg = 1.0;
    public static final @mm2 int mm2 = 1;
    public static final @m2 int m2 = 1;
    public static final @km2 int km2 = 1;
    public static final @A int A = 1;
    public static final @cd int cd = 1;
    public static final @mm int mm = 1;
    public static final @m int m = 1;
    public static final @km int km = 1;
    public static final @g int g = 1;
    public static final @kg int kg = 1;
    public static final @mPERs int mPERs = 1;
    public static final @kmPERh int kmPERh = 1;
    public static final @mol int mol = 1;
    public static final @K int K = 1;
    public static final @C int C = 1;
    public static final @s int s = 1;
    public static final @min int min = 1;
    public static final @h int h = 1;

    public static @radians double toRadians(@degrees double angdeg) {
        return Math.toRadians(angdeg);
    }

    public static @degrees double toDegrees(@radians double angrad) {
        return Math.toDegrees(angrad);
    }

    public static @m int fromMilliMeterToMeter(@mm int mm3) {
        return mm3 / 1000;
    }

    public static @mm int fromMeterToMilliMeter(@m int m3) {
        return m3 * 1000;
    }

    public static @km int fromMeterToKiloMeter(@m int m3) {
        return m3 / 1000;
    }

    public static @m int fromKiloMeterToMeter(@km int km3) {
        return km3 * 1000;
    }

    public static @kg int fromGramToKiloGram(@g int g2) {
        return g2 / 1000;
    }

    public static @g int fromKiloGramToGram(@kg int kg2) {
        return kg2 * 1000;
    }

    public static @kmPERh double fromMeterPerSecondToKiloMeterPerHour(@mPERs double mps) {
        return mps * 3.6;
    }

    public static @mPERs double fromKiloMeterPerHourToMeterPerSecond(@kmPERh double kmph) {
        return kmph / 3.6;
    }

    public static @C int fromKelvinToCelsius(@K int k) {
        return k - 273;
    }

    public static @K int fromCelsiusToKelvin(@C int c) {
        return c + 273;
    }

    public static @min int fromSecondToMinute(@s int s2) {
        return s2 / 60;
    }

    public static @s int fromMinuteToSecond(@min int min2) {
        return min2 * 60;
    }

    public static @h int fromMinuteToHour(@min int min2) {
        return min2 / 60;
    }

    public static @min int fromHourToMinute(@h int h2) {
        return h2 * 60;
    }
}

