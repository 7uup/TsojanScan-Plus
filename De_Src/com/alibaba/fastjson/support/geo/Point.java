/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.support.geo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.support.geo.Geometry;

@JSONType(typeName="Point", orders={"type", "bbox", "coordinates"})
public class Point
extends Geometry {
    private double longitude;
    private double latitude;

    public Point() {
        super("Point");
    }

    public double[] getCoordinates() {
        return new double[]{this.longitude, this.latitude};
    }

    public void setCoordinates(double[] coordinates) {
        if (coordinates == null || coordinates.length == 0) {
            this.longitude = 0.0;
            this.latitude = 0.0;
            return;
        }
        if (coordinates.length == 1) {
            this.longitude = coordinates[0];
            return;
        }
        this.longitude = coordinates[0];
        this.latitude = coordinates[1];
    }

    @JSONField(serialize=false)
    public double getLongitude() {
        return this.longitude;
    }

    @JSONField(serialize=false)
    public double getLatitude() {
        return this.latitude;
    }

    @JSONField(deserialize=false)
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @JSONField(deserialize=false)
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}

