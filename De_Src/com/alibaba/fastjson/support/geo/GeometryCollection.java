/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.support.geo;

import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.support.geo.Geometry;
import java.util.ArrayList;
import java.util.List;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@JSONType(typeName="GeometryCollection", orders={"type", "bbox", "geometries"})
public class GeometryCollection
extends Geometry {
    private List<Geometry> geometries = new ArrayList<Geometry>();

    public GeometryCollection() {
        super("GeometryCollection");
    }

    public List<Geometry> getGeometries() {
        return this.geometries;
    }
}

