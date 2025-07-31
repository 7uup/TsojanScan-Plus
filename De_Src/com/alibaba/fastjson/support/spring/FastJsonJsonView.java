/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  javax.servlet.ServletOutputStream
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  org.springframework.web.servlet.view.AbstractView
 */
package com.alibaba.fastjson.support.spring;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPObject;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.util.IOUtils;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.view.AbstractView;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class FastJsonJsonView
extends AbstractView {
    public static final String DEFAULT_CONTENT_TYPE = "application/json;charset=UTF-8";
    public static final String DEFAULT_JSONP_CONTENT_TYPE = "application/javascript";
    private static final Pattern CALLBACK_PARAM_PATTERN = Pattern.compile("[0-9A-Za-z_\\.]*");
    @Deprecated
    protected Charset charset = Charset.forName("UTF-8");
    @Deprecated
    protected SerializerFeature[] features = new SerializerFeature[0];
    @Deprecated
    protected SerializeFilter[] filters = new SerializeFilter[0];
    @Deprecated
    protected String dateFormat;
    private Set<String> renderedAttributes;
    private boolean disableCaching = true;
    private boolean updateContentLength = true;
    private boolean extractValueFromSingleKeyModel = false;
    private FastJsonConfig fastJsonConfig = new FastJsonConfig();
    private String[] jsonpParameterNames = new String[]{"jsonp", "callback"};

    public FastJsonJsonView() {
        this.setContentType(DEFAULT_CONTENT_TYPE);
        this.setExposePathVariables(false);
    }

    public FastJsonConfig getFastJsonConfig() {
        return this.fastJsonConfig;
    }

    public void setFastJsonConfig(FastJsonConfig fastJsonConfig) {
        this.fastJsonConfig = fastJsonConfig;
    }

    @Deprecated
    public void setSerializerFeature(SerializerFeature ... features) {
        this.fastJsonConfig.setSerializerFeatures(features);
    }

    @Deprecated
    public Charset getCharset() {
        return this.fastJsonConfig.getCharset();
    }

    @Deprecated
    public void setCharset(Charset charset) {
        this.fastJsonConfig.setCharset(charset);
    }

    @Deprecated
    public String getDateFormat() {
        return this.fastJsonConfig.getDateFormat();
    }

    @Deprecated
    public void setDateFormat(String dateFormat) {
        this.fastJsonConfig.setDateFormat(dateFormat);
    }

    @Deprecated
    public SerializerFeature[] getFeatures() {
        return this.fastJsonConfig.getSerializerFeatures();
    }

    @Deprecated
    public void setFeatures(SerializerFeature ... features) {
        this.fastJsonConfig.setSerializerFeatures(features);
    }

    @Deprecated
    public SerializeFilter[] getFilters() {
        return this.fastJsonConfig.getSerializeFilters();
    }

    @Deprecated
    public void setFilters(SerializeFilter ... filters) {
        this.fastJsonConfig.setSerializeFilters(filters);
    }

    public void setRenderedAttributes(Set<String> renderedAttributes) {
        this.renderedAttributes = renderedAttributes;
    }

    public boolean isExtractValueFromSingleKeyModel() {
        return this.extractValueFromSingleKeyModel;
    }

    public void setExtractValueFromSingleKeyModel(boolean extractValueFromSingleKeyModel) {
        this.extractValueFromSingleKeyModel = extractValueFromSingleKeyModel;
    }

    public void setJsonpParameterNames(Set<String> jsonpParameterNames) {
        Assert.notEmpty(jsonpParameterNames, "jsonpParameterName cannot be empty");
        this.jsonpParameterNames = jsonpParameterNames.toArray(new String[jsonpParameterNames.size()]);
    }

    private String getJsonpParameterValue(HttpServletRequest request) {
        if (this.jsonpParameterNames != null) {
            for (String name : this.jsonpParameterNames) {
                String value = request.getParameter(name);
                if (IOUtils.isValidJsonpQueryParam(value)) {
                    return value;
                }
                if (!this.logger.isDebugEnabled()) continue;
                this.logger.debug("Ignoring invalid jsonp parameter value: " + value);
            }
        }
        return null;
    }

    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Object value = this.filterModel(model);
        String jsonpParameterValue = this.getJsonpParameterValue(request);
        if (jsonpParameterValue != null) {
            JSONPObject jsonpObject = new JSONPObject(jsonpParameterValue);
            jsonpObject.addParameter(value);
            value = jsonpObject;
        }
        ByteArrayOutputStream outnew = new ByteArrayOutputStream();
        int len = JSON.writeJSONStringWithFastJsonConfig(outnew, this.fastJsonConfig.getCharset(), value, this.fastJsonConfig.getSerializeConfig(), this.fastJsonConfig.getSerializeFilters(), this.fastJsonConfig.getDateFormat(), JSON.DEFAULT_GENERATE_FEATURE, this.fastJsonConfig.getSerializerFeatures());
        if (this.updateContentLength) {
            response.setContentLength(len);
        }
        ServletOutputStream out = response.getOutputStream();
        outnew.writeTo((OutputStream)out);
        outnew.close();
        out.flush();
    }

    protected void prepareResponse(HttpServletRequest request, HttpServletResponse response) {
        this.setResponseContentType(request, response);
        response.setCharacterEncoding(this.fastJsonConfig.getCharset().name());
        if (this.disableCaching) {
            response.addHeader("Pragma", "no-cache");
            response.addHeader("Cache-Control", "no-cache, no-store, max-age=0");
            response.addDateHeader("Expires", 1L);
        }
    }

    public void setDisableCaching(boolean disableCaching) {
        this.disableCaching = disableCaching;
    }

    public void setUpdateContentLength(boolean updateContentLength) {
        this.updateContentLength = updateContentLength;
    }

    protected Object filterModel(Map<String, Object> model) {
        Map.Entry<String, Object> entry;
        HashMap<String, Object> result = new HashMap<String, Object>(model.size());
        Set<String> renderedAttributes = !CollectionUtils.isEmpty(this.renderedAttributes) ? this.renderedAttributes : model.keySet();
        Iterator<Map.Entry<String, Object>> iterator2 = model.entrySet().iterator();
        while (iterator2.hasNext()) {
            entry = iterator2.next();
            if (entry.getValue() instanceof BindingResult || !renderedAttributes.contains(entry.getKey())) continue;
            result.put(entry.getKey(), entry.getValue());
        }
        if (this.extractValueFromSingleKeyModel && result.size() == 1 && (iterator2 = result.entrySet().iterator()).hasNext()) {
            entry = iterator2.next();
            return entry.getValue();
        }
        return result;
    }

    protected void setResponseContentType(HttpServletRequest request, HttpServletResponse response) {
        if (this.getJsonpParameterValue(request) != null) {
            response.setContentType(DEFAULT_JSONP_CONTENT_TYPE);
        } else {
            super.setResponseContentType(request, response);
        }
    }
}

