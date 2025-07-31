/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.result;

import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.result.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class DefaultColumnDefinition
implements ColumnDefinition {
    protected Field[] fields;
    private Map<String, Integer> columnLabelToIndex = null;
    private Map<String, Integer> columnToIndexCache = new HashMap<String, Integer>();
    private Map<String, Integer> fullColumnNameToIndex = null;
    private Map<String, Integer> columnNameToIndex = null;
    private boolean builtIndexMapping = false;

    public DefaultColumnDefinition() {
    }

    public DefaultColumnDefinition(Field[] fields) {
        this.fields = fields;
    }

    @Override
    public Field[] getFields() {
        return this.fields;
    }

    @Override
    public void setFields(Field[] fields) {
        this.fields = fields;
    }

    @Override
    public void buildIndexMapping() {
        int numFields = this.fields.length;
        this.columnLabelToIndex = new TreeMap<String, Integer>(String.CASE_INSENSITIVE_ORDER);
        this.fullColumnNameToIndex = new TreeMap<String, Integer>(String.CASE_INSENSITIVE_ORDER);
        this.columnNameToIndex = new TreeMap<String, Integer>(String.CASE_INSENSITIVE_ORDER);
        for (int i = numFields - 1; i >= 0; --i) {
            Integer index = i;
            String columnName = this.fields[i].getOriginalName();
            String columnLabel = this.fields[i].getName();
            String fullColumnName = this.fields[i].getFullName();
            if (columnLabel != null) {
                this.columnLabelToIndex.put(columnLabel, index);
            }
            if (fullColumnName != null) {
                this.fullColumnNameToIndex.put(fullColumnName, index);
            }
            if (columnName == null) continue;
            this.columnNameToIndex.put(columnName, index);
        }
        this.builtIndexMapping = true;
    }

    @Override
    public boolean hasBuiltIndexMapping() {
        return this.builtIndexMapping;
    }

    @Override
    public Map<String, Integer> getColumnLabelToIndex() {
        return this.columnLabelToIndex;
    }

    @Override
    public void setColumnLabelToIndex(Map<String, Integer> columnLabelToIndex) {
        this.columnLabelToIndex = columnLabelToIndex;
    }

    @Override
    public Map<String, Integer> getFullColumnNameToIndex() {
        return this.fullColumnNameToIndex;
    }

    @Override
    public void setFullColumnNameToIndex(Map<String, Integer> fullColNameToIndex) {
        this.fullColumnNameToIndex = fullColNameToIndex;
    }

    @Override
    public Map<String, Integer> getColumnNameToIndex() {
        return this.columnNameToIndex;
    }

    @Override
    public void setColumnNameToIndex(Map<String, Integer> colNameToIndex) {
        this.columnNameToIndex = colNameToIndex;
    }

    @Override
    public Map<String, Integer> getColumnToIndexCache() {
        return this.columnToIndexCache;
    }

    @Override
    public void setColumnToIndexCache(Map<String, Integer> columnToIndexCache) {
        this.columnToIndexCache = columnToIndexCache;
    }

    @Override
    public void initializeFrom(ColumnDefinition columnDefinition) {
        this.fields = columnDefinition.getFields();
        this.columnLabelToIndex = columnDefinition.getColumnNameToIndex();
        this.fullColumnNameToIndex = columnDefinition.getFullColumnNameToIndex();
        this.builtIndexMapping = true;
    }

    @Override
    public void exportTo(ColumnDefinition columnDefinition) {
        columnDefinition.setFields(this.fields);
        columnDefinition.setColumnNameToIndex(this.columnLabelToIndex);
        columnDefinition.setFullColumnNameToIndex(this.fullColumnNameToIndex);
    }

    @Override
    public int findColumn(String columnName, boolean useColumnNamesInFindColumn, int indexBase) {
        Integer index;
        if (!this.hasBuiltIndexMapping()) {
            this.buildIndexMapping();
        }
        if ((index = this.columnToIndexCache.get(columnName)) != null) {
            return index + indexBase;
        }
        index = this.columnLabelToIndex.get(columnName);
        if (index == null && useColumnNamesInFindColumn) {
            index = this.columnNameToIndex.get(columnName);
        }
        if (index == null) {
            index = this.fullColumnNameToIndex.get(columnName);
        }
        if (index != null) {
            this.columnToIndexCache.put(columnName, index);
            return index + indexBase;
        }
        for (int i = 0; i < this.fields.length; ++i) {
            if (this.fields[i].getName().equalsIgnoreCase(columnName)) {
                return i + indexBase;
            }
            if (!this.fields[i].getFullName().equalsIgnoreCase(columnName)) continue;
            return i + indexBase;
        }
        return -1;
    }

    @Override
    public boolean hasLargeFields() {
        if (this.fields != null) {
            for (int i = 0; i < this.fields.length; ++i) {
                switch (this.fields[i].getMysqlType()) {
                    case BLOB: 
                    case MEDIUMBLOB: 
                    case LONGBLOB: 
                    case TEXT: 
                    case MEDIUMTEXT: 
                    case LONGTEXT: 
                    case JSON: {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

