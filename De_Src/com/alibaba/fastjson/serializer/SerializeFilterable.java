/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.AfterFilter;
import com.alibaba.fastjson.serializer.BeanContext;
import com.alibaba.fastjson.serializer.BeforeFilter;
import com.alibaba.fastjson.serializer.ContextValueFilter;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.LabelFilter;
import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class SerializeFilterable {
    protected List<BeforeFilter> beforeFilters = null;
    protected List<AfterFilter> afterFilters = null;
    protected List<PropertyFilter> propertyFilters = null;
    protected List<ValueFilter> valueFilters = null;
    protected List<NameFilter> nameFilters = null;
    protected List<PropertyPreFilter> propertyPreFilters = null;
    protected List<LabelFilter> labelFilters = null;
    protected List<ContextValueFilter> contextValueFilters = null;
    protected boolean writeDirect = true;

    public List<BeforeFilter> getBeforeFilters() {
        if (this.beforeFilters == null) {
            this.beforeFilters = new ArrayList<BeforeFilter>();
            this.writeDirect = false;
        }
        return this.beforeFilters;
    }

    public List<AfterFilter> getAfterFilters() {
        if (this.afterFilters == null) {
            this.afterFilters = new ArrayList<AfterFilter>();
            this.writeDirect = false;
        }
        return this.afterFilters;
    }

    public List<NameFilter> getNameFilters() {
        if (this.nameFilters == null) {
            this.nameFilters = new ArrayList<NameFilter>();
            this.writeDirect = false;
        }
        return this.nameFilters;
    }

    public List<PropertyPreFilter> getPropertyPreFilters() {
        if (this.propertyPreFilters == null) {
            this.propertyPreFilters = new ArrayList<PropertyPreFilter>();
            this.writeDirect = false;
        }
        return this.propertyPreFilters;
    }

    public List<LabelFilter> getLabelFilters() {
        if (this.labelFilters == null) {
            this.labelFilters = new ArrayList<LabelFilter>();
            this.writeDirect = false;
        }
        return this.labelFilters;
    }

    public List<PropertyFilter> getPropertyFilters() {
        if (this.propertyFilters == null) {
            this.propertyFilters = new ArrayList<PropertyFilter>();
            this.writeDirect = false;
        }
        return this.propertyFilters;
    }

    public List<ContextValueFilter> getContextValueFilters() {
        if (this.contextValueFilters == null) {
            this.contextValueFilters = new ArrayList<ContextValueFilter>();
            this.writeDirect = false;
        }
        return this.contextValueFilters;
    }

    public List<ValueFilter> getValueFilters() {
        if (this.valueFilters == null) {
            this.valueFilters = new ArrayList<ValueFilter>();
            this.writeDirect = false;
        }
        return this.valueFilters;
    }

    public void addFilter(SerializeFilter filter) {
        if (filter == null) {
            return;
        }
        if (filter instanceof PropertyPreFilter) {
            this.getPropertyPreFilters().add((PropertyPreFilter)filter);
        }
        if (filter instanceof NameFilter) {
            this.getNameFilters().add((NameFilter)filter);
        }
        if (filter instanceof ValueFilter) {
            this.getValueFilters().add((ValueFilter)filter);
        }
        if (filter instanceof ContextValueFilter) {
            this.getContextValueFilters().add((ContextValueFilter)filter);
        }
        if (filter instanceof PropertyFilter) {
            this.getPropertyFilters().add((PropertyFilter)filter);
        }
        if (filter instanceof BeforeFilter) {
            this.getBeforeFilters().add((BeforeFilter)filter);
        }
        if (filter instanceof AfterFilter) {
            this.getAfterFilters().add((AfterFilter)filter);
        }
        if (filter instanceof LabelFilter) {
            this.getLabelFilters().add((LabelFilter)filter);
        }
    }

    public boolean applyName(JSONSerializer jsonBeanDeser, Object object, String key) {
        if (jsonBeanDeser.propertyPreFilters != null) {
            for (PropertyPreFilter filter : jsonBeanDeser.propertyPreFilters) {
                if (filter.apply(jsonBeanDeser, object, key)) continue;
                return false;
            }
        }
        if (this.propertyPreFilters != null) {
            for (PropertyPreFilter filter : this.propertyPreFilters) {
                if (filter.apply(jsonBeanDeser, object, key)) continue;
                return false;
            }
        }
        return true;
    }

    public boolean apply(JSONSerializer jsonBeanDeser, Object object, String key, Object propertyValue) {
        if (jsonBeanDeser.propertyFilters != null) {
            for (PropertyFilter propertyFilter : jsonBeanDeser.propertyFilters) {
                if (propertyFilter.apply(object, key, propertyValue)) continue;
                return false;
            }
        }
        if (this.propertyFilters != null) {
            for (PropertyFilter propertyFilter : this.propertyFilters) {
                if (propertyFilter.apply(object, key, propertyValue)) continue;
                return false;
            }
        }
        return true;
    }

    protected String processKey(JSONSerializer jsonBeanDeser, Object object, String key, Object propertyValue) {
        if (jsonBeanDeser.nameFilters != null) {
            for (NameFilter nameFilter : jsonBeanDeser.nameFilters) {
                key = nameFilter.process(object, key, propertyValue);
            }
        }
        if (this.nameFilters != null) {
            for (NameFilter nameFilter : this.nameFilters) {
                key = nameFilter.process(object, key, propertyValue);
            }
        }
        return key;
    }

    protected Object processValue(JSONSerializer jsonBeanDeser, BeanContext beanContext, Object object, String key, Object propertyValue) {
        return this.processValue(jsonBeanDeser, beanContext, object, key, propertyValue, 0);
    }

    protected Object processValue(JSONSerializer jsonBeanDeser, BeanContext beanContext, Object object, String key, Object propertyValue, int features) {
        List<ValueFilter> valueFilters;
        if (propertyValue != null) {
            if ((SerializerFeature.isEnabled(jsonBeanDeser.out.features, features, SerializerFeature.WriteNonStringValueAsString) || beanContext != null && (beanContext.getFeatures() & SerializerFeature.WriteNonStringValueAsString.mask) != 0) && (propertyValue instanceof Number || propertyValue instanceof Boolean)) {
                String format = null;
                if (propertyValue instanceof Number && beanContext != null) {
                    format = beanContext.getFormat();
                }
                propertyValue = format != null ? new DecimalFormat(format).format(propertyValue) : propertyValue.toString();
            } else if (beanContext != null && beanContext.isJsonDirect()) {
                String jsonStr = (String)propertyValue;
                propertyValue = JSON.parse(jsonStr);
            }
        }
        if (jsonBeanDeser.valueFilters != null) {
            for (ValueFilter valueFilter : jsonBeanDeser.valueFilters) {
                propertyValue = valueFilter.process(object, key, propertyValue);
            }
        }
        if ((valueFilters = this.valueFilters) != null) {
            for (ValueFilter valueFilter : valueFilters) {
                propertyValue = valueFilter.process(object, key, propertyValue);
            }
        }
        if (jsonBeanDeser.contextValueFilters != null) {
            for (ContextValueFilter contextValueFilter : jsonBeanDeser.contextValueFilters) {
                propertyValue = contextValueFilter.process(beanContext, object, key, propertyValue);
            }
        }
        if (this.contextValueFilters != null) {
            for (ContextValueFilter contextValueFilter : this.contextValueFilters) {
                propertyValue = contextValueFilter.process(beanContext, object, key, propertyValue);
            }
        }
        return propertyValue;
    }

    protected boolean writeDirect(JSONSerializer jsonBeanDeser) {
        return jsonBeanDeser.out.writeDirect && this.writeDirect && jsonBeanDeser.writeDirect;
    }
}

