/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.support.geo;

import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.support.geo.Feature;
import com.alibaba.fastjson.support.geo.Geometry;
import java.util.ArrayList;
import java.util.List;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@JSONType(typeName="FeatureCollection", orders={"type", "bbox", "coordinates"})
public class FeatureCollection
extends Geometry {
    private List<Feature> features = new ArrayList<Feature>();

    public FeatureCollection() {
        super("FeatureCollection");
    }

    public List<Feature> getFeatures() {
        return this.features;
    }
}

