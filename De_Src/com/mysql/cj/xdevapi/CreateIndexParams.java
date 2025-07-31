/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.Messages;
import com.mysql.cj.exceptions.AssertionFailedException;
import com.mysql.cj.xdevapi.DbDoc;
import com.mysql.cj.xdevapi.JsonArray;
import com.mysql.cj.xdevapi.JsonLiteral;
import com.mysql.cj.xdevapi.JsonNumber;
import com.mysql.cj.xdevapi.JsonParser;
import com.mysql.cj.xdevapi.JsonString;
import com.mysql.cj.xdevapi.JsonValue;
import com.mysql.cj.xdevapi.XDevAPIError;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class CreateIndexParams {
    private String indexName;
    private String indexType = "INDEX";
    private List<IndexField> fields = new ArrayList<IndexField>();

    public CreateIndexParams(String indexName, DbDoc indexDefinition) {
        this.init(indexName, indexDefinition);
    }

    public CreateIndexParams(String indexName, String jsonIndexDefinition) {
        if (jsonIndexDefinition == null || jsonIndexDefinition.trim().length() == 0) {
            throw new XDevAPIError(Messages.getString("CreateIndexParams.0", new String[]{"jsonIndexDefinition"}));
        }
        try {
            this.init(indexName, JsonParser.parseDoc(new StringReader(jsonIndexDefinition)));
        } catch (IOException ex) {
            throw AssertionFailedException.shouldNotHappen(ex);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void init(String idxName, DbDoc indexDefinition) {
        if (idxName == null || idxName.trim().length() == 0) {
            throw new XDevAPIError(Messages.getString("CreateIndexParams.0", new String[]{"indexName"}));
        }
        if (indexDefinition == null) {
            throw new XDevAPIError(Messages.getString("CreateIndexParams.0", new String[]{"indexDefinition"}));
        }
        this.indexName = idxName;
        for (String key : indexDefinition.keySet()) {
            if ("type".equals(key) || "fields".equals(key)) continue;
            throw new XDevAPIError("The '" + key + "' field is not allowed in indexDefinition.");
        }
        JsonValue val = (JsonValue)indexDefinition.get("type");
        if (val != null) {
            if (!(val instanceof JsonString)) throw new XDevAPIError("Index type must be a string.");
            String type = ((JsonString)val).getString();
            if (!"INDEX".equalsIgnoreCase(type) && !"SPATIAL".equalsIgnoreCase(type)) throw new XDevAPIError("Wrong index type '" + type + "'. Must be 'INDEX' or 'SPATIAL'.");
            this.indexType = type;
        }
        if ((val = (JsonValue)indexDefinition.get("fields")) == null) throw new XDevAPIError("Index definition does not contain fields.");
        if (!(val instanceof JsonArray)) throw new XDevAPIError("Index definition 'fields' member must be an array of index fields.");
        for (JsonValue field : (JsonArray)val) {
            if (!(field instanceof DbDoc)) throw new XDevAPIError("Index field definition must be a JSON document.");
            this.fields.add(new IndexField((DbDoc)field));
        }
    }

    public String getIndexName() {
        return this.indexName;
    }

    public String getIndexType() {
        return this.indexType;
    }

    public List<IndexField> getFields() {
        return this.fields;
    }

    public static class IndexField {
        private String field;
        private String type;
        private boolean required = false;
        private Integer options = null;
        private Integer srid = null;

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public IndexField(DbDoc indexField) {
            for (String key : indexField.keySet()) {
                if ("type".equals(key) || "field".equals(key) || "required".equals(key) || "options".equals(key) || "srid".equals(key)) continue;
                throw new XDevAPIError("The '" + key + "' field is not allowed in indexField.");
            }
            JsonValue val = (JsonValue)indexField.get("field");
            if (val == null) throw new XDevAPIError("Index field definition has no document path.");
            if (!(val instanceof JsonString)) {
                throw new XDevAPIError("Index field 'field' member must be a string.");
            }
            this.field = ((JsonString)val).getString();
            val = (JsonValue)indexField.get("type");
            if (val == null) throw new XDevAPIError("Index field definition has no field type.");
            if (!(val instanceof JsonString)) {
                throw new XDevAPIError("Index type must be a string.");
            }
            this.type = ((JsonString)val).getString();
            val = (JsonValue)indexField.get("required");
            if (val != null) {
                if (!(val instanceof JsonLiteral) || JsonLiteral.NULL.equals(val)) throw new XDevAPIError("Index field 'required' member must be boolean.");
                this.required = Boolean.valueOf(((JsonLiteral)val).value);
            } else if (this.type.equalsIgnoreCase("GEOJSON")) {
                this.required = true;
            }
            val = (JsonValue)indexField.get("options");
            if (val != null) {
                if (!this.type.equalsIgnoreCase("GEOJSON")) throw new XDevAPIError("Index field 'options' member should not be used for field types other than GEOJSON.");
                if (!(val instanceof JsonNumber)) throw new XDevAPIError("Index field 'options' member must be integer.");
                this.options = ((JsonNumber)val).getInteger();
            }
            if ((val = (JsonValue)indexField.get("srid")) == null) return;
            if (!this.type.equalsIgnoreCase("GEOJSON")) throw new XDevAPIError("Index field 'srid' member should not be used for field types other than GEOJSON.");
            if (!(val instanceof JsonNumber)) throw new XDevAPIError("Index field 'srid' member must be integer.");
            this.srid = ((JsonNumber)val).getInteger();
        }

        public String getField() {
            return this.field;
        }

        public String getType() {
            return this.type;
        }

        public boolean isRequired() {
            return this.required;
        }

        public Integer getOptions() {
            return this.options;
        }

        public Integer getSrid() {
            return this.srid;
        }
    }
}

