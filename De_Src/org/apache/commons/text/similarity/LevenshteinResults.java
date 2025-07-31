/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text.similarity;

import java.util.Objects;

public class LevenshteinResults {
    private final Integer distance;
    private final Integer insertCount;
    private final Integer deleteCount;
    private final Integer substituteCount;

    public LevenshteinResults(Integer distance, Integer insertCount, Integer deleteCount, Integer substituteCount) {
        this.distance = distance;
        this.insertCount = insertCount;
        this.deleteCount = deleteCount;
        this.substituteCount = substituteCount;
    }

    public Integer getDistance() {
        return this.distance;
    }

    public Integer getInsertCount() {
        return this.insertCount;
    }

    public Integer getDeleteCount() {
        return this.deleteCount;
    }

    public Integer getSubstituteCount() {
        return this.substituteCount;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        LevenshteinResults result = (LevenshteinResults)o;
        return Objects.equals(this.distance, result.distance) && Objects.equals(this.insertCount, result.insertCount) && Objects.equals(this.deleteCount, result.deleteCount) && Objects.equals(this.substituteCount, result.substituteCount);
    }

    public int hashCode() {
        return Objects.hash(this.distance, this.insertCount, this.deleteCount, this.substituteCount);
    }

    public String toString() {
        return "Distance: " + this.distance + ", Insert: " + this.insertCount + ", Delete: " + this.deleteCount + ", Substitute: " + this.substituteCount;
    }
}

