/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.support.geo;

import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.support.geo.Feature;
import com.alibaba.fastjson.support.geo.FeatureCollection;
import com.alibaba.fastjson.support.geo.GeometryCollection;
import com.alibaba.fastjson.support.geo.LineString;
import com.alibaba.fastjson.support.geo.MultiLineString;
import com.alibaba.fastjson.support.geo.MultiPoint;
import com.alibaba.fastjson.support.geo.MultiPolygon;
import com.alibaba.fastjson.support.geo.Point;
import com.alibaba.fastjson.support.geo.Polygon;

@JSONType(seeAlso={GeometryCollection.class, LineString.class, MultiLineString.class, Point.class, MultiPoint.class, Polygon.class, MultiPolygon.class, Feature.class, FeatureCollection.class}, typeKey="type")
public abstract class Geometry {
    private final String type;
    private double[] bbox;

    protected Geometry(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public double[] getBbox() {
        return this.bbox;
    }

    public void setBbox(double[] bbox) {
        this.bbox = bbox;
    }
}

