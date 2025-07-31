/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.util.concurrent.Service;

@GwtIncompatible
interface ServiceManagerBridge {
    public ImmutableMultimap<Service.State, Service> servicesByState();
}

