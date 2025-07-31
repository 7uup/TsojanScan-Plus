/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh.reflect;

import bsh.ReflectManager;
import java.lang.reflect.AccessibleObject;

public class ReflectManagerImpl
extends ReflectManager {
    public boolean setAccessible(Object obj) {
        if (obj instanceof AccessibleObject) {
            ((AccessibleObject)obj).setAccessible(true);
            return true;
        }
        return false;
    }
}

