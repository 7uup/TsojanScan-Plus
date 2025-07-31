/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONPOJOBuilder;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.util.ASMUtils;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class JavaBeanInfo {
    public final Class<?> clazz;
    public final Class<?> builderClass;
    public final Constructor<?> defaultConstructor;
    public final Constructor<?> creatorConstructor;
    public final Method factoryMethod;
    public final Method buildMethod;
    public final int defaultConstructorParameterSize;
    public final FieldInfo[] fields;
    public final FieldInfo[] sortedFields;
    public final int parserFeatures;
    public final JSONType jsonType;
    public final String typeName;
    public final String typeKey;
    public String[] orders;
    public Type[] creatorConstructorParameterTypes;
    public String[] creatorConstructorParameters;
    public boolean kotlin;
    public Constructor<?> kotlinDefaultConstructor;

    public JavaBeanInfo(Class<?> clazz, Class<?> builderClass, Constructor<?> defaultConstructor, Constructor<?> creatorConstructor, Method factoryMethod, Method buildMethod, JSONType jsonType, List<FieldInfo> fieldList) {
        this.clazz = clazz;
        this.builderClass = builderClass;
        this.defaultConstructor = defaultConstructor;
        this.creatorConstructor = creatorConstructor;
        this.factoryMethod = factoryMethod;
        this.parserFeatures = TypeUtils.getParserFeatures(clazz);
        this.buildMethod = buildMethod;
        this.jsonType = jsonType;
        if (jsonType != null) {
            String typeName = jsonType.typeName();
            String typeKey = jsonType.typeKey();
            this.typeKey = typeKey.length() > 0 ? typeKey : null;
            this.typeName = typeName.length() != 0 ? typeName : clazz.getName();
            Object[] orders = jsonType.orders();
            this.orders = orders.length == 0 ? null : orders;
        } else {
            this.typeName = clazz.getName();
            this.typeKey = null;
            this.orders = null;
        }
        this.fields = new FieldInfo[fieldList.size()];
        fieldList.toArray(this.fields);
        Object[] sortedFields = new FieldInfo[this.fields.length];
        if (this.orders != null) {
            LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>(fieldList.size());
            for (FieldInfo field : this.fields) {
                map.put(field.name, field);
            }
            int i = 0;
            for (String item : this.orders) {
                FieldInfo field = (FieldInfo)map.get(item);
                if (field == null) continue;
                sortedFields[i++] = field;
                map.remove(item);
            }
            for (FieldInfo field : map.values()) {
                sortedFields[i++] = field;
            }
        } else {
            System.arraycopy(this.fields, 0, sortedFields, 0, this.fields.length);
            Arrays.sort(sortedFields);
        }
        if (Arrays.equals(this.fields, sortedFields)) {
            sortedFields = this.fields;
        }
        this.sortedFields = sortedFields;
        this.defaultConstructorParameterSize = defaultConstructor != null ? defaultConstructor.getParameterTypes().length : (factoryMethod != null ? factoryMethod.getParameterTypes().length : 0);
        if (creatorConstructor != null) {
            this.creatorConstructorParameterTypes = creatorConstructor.getParameterTypes();
            this.kotlin = TypeUtils.isKotlin(clazz);
            if (this.kotlin) {
                this.creatorConstructorParameters = TypeUtils.getKoltinConstructorParameters(clazz);
                try {
                    this.kotlinDefaultConstructor = clazz.getConstructor(new Class[0]);
                } catch (Throwable map) {
                    // empty catch block
                }
                Annotation[][] paramAnnotationArrays = TypeUtils.getParameterAnnotations(creatorConstructor);
                for (int i = 0; i < this.creatorConstructorParameters.length && i < paramAnnotationArrays.length; ++i) {
                    String fieldAnnotationName;
                    Annotation[] paramAnnotations = paramAnnotationArrays[i];
                    JSONField fieldAnnotation = null;
                    for (Annotation paramAnnotation : paramAnnotations) {
                        if (!(paramAnnotation instanceof JSONField)) continue;
                        fieldAnnotation = (JSONField)paramAnnotation;
                        break;
                    }
                    if (fieldAnnotation == null || (fieldAnnotationName = fieldAnnotation.name()).length() <= 0) continue;
                    this.creatorConstructorParameters[i] = fieldAnnotationName;
                }
            } else {
                boolean match;
                if (this.creatorConstructorParameterTypes.length != this.fields.length) {
                    match = false;
                } else {
                    match = true;
                    for (int i = 0; i < this.creatorConstructorParameterTypes.length; ++i) {
                        if (this.creatorConstructorParameterTypes[i] == this.fields[i].fieldClass) continue;
                        match = false;
                        break;
                    }
                }
                if (!match) {
                    this.creatorConstructorParameters = ASMUtils.lookupParameterNames(creatorConstructor);
                }
            }
        }
    }

    private static FieldInfo getField(List<FieldInfo> fieldList, String propertyName) {
        for (FieldInfo item : fieldList) {
            if (item.name.equals(propertyName)) {
                return item;
            }
            Field field = item.field;
            if (field == null || item.getAnnotation() == null || !field.getName().equals(propertyName)) continue;
            return item;
        }
        return null;
    }

    static boolean add(List<FieldInfo> fieldList, FieldInfo field) {
        for (int i = fieldList.size() - 1; i >= 0; --i) {
            FieldInfo item = fieldList.get(i);
            if (!item.name.equals(field.name) || item.getOnly && !field.getOnly) continue;
            if (item.fieldClass.isAssignableFrom(field.fieldClass)) {
                fieldList.set(i, field);
                return true;
            }
            int result = item.compareTo(field);
            if (result < 0) {
                fieldList.set(i, field);
                return true;
            }
            return false;
        }
        fieldList.add(field);
        return true;
    }

    public static JavaBeanInfo build(Class<?> clazz, Type type, PropertyNamingStrategy propertyNamingStrategy) {
        return JavaBeanInfo.build(clazz, type, propertyNamingStrategy, false, TypeUtils.compatibleWithJavaBean, false);
    }

    private static Map<TypeVariable, Type> buildGenericInfo(Class<?> clazz) {
        Class<?> childClass = clazz;
        Class<?> currentClass = clazz.getSuperclass();
        if (currentClass == null) {
            return null;
        }
        HashMap typeVarMap = null;
        while (currentClass != null && currentClass != Object.class) {
            if (childClass.getGenericSuperclass() instanceof ParameterizedType) {
                Type[] childGenericParentActualTypeArgs = ((ParameterizedType)childClass.getGenericSuperclass()).getActualTypeArguments();
                TypeVariable<Class<?>>[] currentTypeParameters = currentClass.getTypeParameters();
                for (int i = 0; i < childGenericParentActualTypeArgs.length; ++i) {
                    if (typeVarMap == null) {
                        typeVarMap = new HashMap();
                    }
                    if (typeVarMap.containsKey(childGenericParentActualTypeArgs[i])) {
                        Type actualArg = (Type)typeVarMap.get(childGenericParentActualTypeArgs[i]);
                        typeVarMap.put(currentTypeParameters[i], actualArg);
                        continue;
                    }
                    typeVarMap.put(currentTypeParameters[i], childGenericParentActualTypeArgs[i]);
                }
            }
            childClass = currentClass;
            currentClass = currentClass.getSuperclass();
        }
        return typeVarMap;
    }

    public static JavaBeanInfo build(Class<?> clazz, Type type, PropertyNamingStrategy propertyNamingStrategy, boolean fieldBased, boolean compatibleWithJavaBean) {
        return JavaBeanInfo.build(clazz, type, propertyNamingStrategy, fieldBased, compatibleWithJavaBean, false);
    }

    /*
     * WARNING - void declaration
     */
    public static JavaBeanInfo build(Class<?> clazz, Type type, PropertyNamingStrategy propertyNamingStrategy, boolean fieldBased, boolean compatibleWithJavaBean, boolean jacksonCompatible) {
        boolean isInterfaceOrAbstract;
        PropertyNamingStrategy jsonTypeNaming;
        JSONType jsonType = TypeUtils.getAnnotation(clazz, JSONType.class);
        if (jsonType != null && (jsonTypeNaming = jsonType.naming()) != null && jsonTypeNaming != PropertyNamingStrategy.CamelCase) {
            propertyNamingStrategy = jsonTypeNaming;
        }
        Class<?> builderClass = JavaBeanInfo.getBuilderClass(clazz, jsonType);
        Field[] declaredFields = clazz.getDeclaredFields();
        Method[] methods = clazz.getMethods();
        Map<TypeVariable, Type> genericInfo = JavaBeanInfo.buildGenericInfo(clazz);
        boolean kotlin = TypeUtils.isKotlin(clazz);
        Constructor[] constructors = clazz.getDeclaredConstructors();
        Constructor<?> defaultConstructor = null;
        if (!kotlin || constructors.length == 1) {
            defaultConstructor = builderClass == null ? JavaBeanInfo.getDefaultConstructor(clazz, constructors) : JavaBeanInfo.getDefaultConstructor(builderClass, builderClass.getDeclaredConstructors());
        }
        Annotation[] creatorConstructor = null;
        Method buildMethod = null;
        Method factoryMethod = null;
        ArrayList<FieldInfo> fieldList = new ArrayList<FieldInfo>();
        if (fieldBased) {
            for (Class<?> currentClass = clazz; currentClass != null; currentClass = currentClass.getSuperclass()) {
                Field[] fields = currentClass.getDeclaredFields();
                JavaBeanInfo.computeFields(clazz, type, propertyNamingStrategy, fieldList, fields);
            }
            if (defaultConstructor != null) {
                TypeUtils.setAccessible(defaultConstructor);
            }
            return new JavaBeanInfo(clazz, builderClass, defaultConstructor, null, factoryMethod, buildMethod, jsonType, fieldList);
        }
        boolean bl = isInterfaceOrAbstract = clazz.isInterface() || Modifier.isAbstract(clazz.getModifiers());
        if (defaultConstructor == null && builderClass == null || isInterfaceOrAbstract) {
            Constructor[] mixInConstructors;
            Constructor<?> mixInCreator;
            Type mixInType = JSON.getMixInAnnotations(clazz);
            if (mixInType instanceof Class && (mixInCreator = JavaBeanInfo.getCreatorConstructor(mixInConstructors = ((Class)mixInType).getConstructors())) != null) {
                try {
                    creatorConstructor = clazz.getConstructor(mixInCreator.getParameterTypes());
                } catch (NoSuchMethodException noSuchMethodException) {
                    // empty catch block
                }
            }
            if (creatorConstructor == null) {
                creatorConstructor = JavaBeanInfo.getCreatorConstructor(constructors);
            }
            if (creatorConstructor != null && !isInterfaceOrAbstract) {
                TypeUtils.setAccessible(creatorConstructor);
                Class<?>[] types = creatorConstructor.getParameterTypes();
                String[] lookupParameterNames = null;
                if (types.length > 0) {
                    Annotation[][] paramAnnotationArrays = TypeUtils.getParameterAnnotations(creatorConstructor);
                    for (int i = 0; i < types.length && i < paramAnnotationArrays.length; ++i) {
                        void var25_50;
                        Annotation[] paramAnnotations = paramAnnotationArrays[i];
                        Object var25_51 = null;
                        for (Annotation paramAnnotation : paramAnnotations) {
                            if (!(paramAnnotation instanceof JSONField)) continue;
                            JSONField jSONField = (JSONField)paramAnnotation;
                            break;
                        }
                        Class<?> fieldClass = types[i];
                        Type fieldType = creatorConstructor.getGenericParameterTypes()[i];
                        String fieldName = null;
                        Field field = null;
                        int ordinal = 0;
                        int serialzeFeatures = 0;
                        int parserFeatures = 0;
                        if (var25_50 != null) {
                            field = TypeUtils.getField(clazz, var25_50.name(), declaredFields);
                            ordinal = var25_50.ordinal();
                            serialzeFeatures = SerializerFeature.of(var25_50.serialzeFeatures());
                            parserFeatures = Feature.of(var25_50.parseFeatures());
                            fieldName = var25_50.name();
                        }
                        if (fieldName == null || fieldName.length() == 0) {
                            if (lookupParameterNames == null) {
                                lookupParameterNames = ASMUtils.lookupParameterNames((AccessibleObject)creatorConstructor);
                            }
                            fieldName = lookupParameterNames[i];
                        }
                        if (field == null) {
                            if (lookupParameterNames == null) {
                                lookupParameterNames = kotlin ? TypeUtils.getKoltinConstructorParameters(clazz) : ASMUtils.lookupParameterNames((AccessibleObject)creatorConstructor);
                            }
                            if (lookupParameterNames.length > i) {
                                String parameterName = lookupParameterNames[i];
                                field = TypeUtils.getField(clazz, parameterName, declaredFields);
                            }
                        }
                        FieldInfo fieldInfo = new FieldInfo(fieldName, clazz, fieldClass, fieldType, field, ordinal, serialzeFeatures, parserFeatures);
                        JavaBeanInfo.add(fieldList, fieldInfo);
                    }
                }
            } else {
                factoryMethod = JavaBeanInfo.getFactoryMethod(clazz, methods, jacksonCompatible);
                if (factoryMethod != null) {
                    TypeUtils.setAccessible(factoryMethod);
                    String[] lookupParameterNames = null;
                    Class<?>[] types = factoryMethod.getParameterTypes();
                    if (types.length > 0) {
                        Annotation[][] paramAnnotationArrays = TypeUtils.getParameterAnnotations(factoryMethod);
                        for (int i = 0; i < types.length; ++i) {
                            void var25_53;
                            Annotation[] paramAnnotations = paramAnnotationArrays[i];
                            Object var25_54 = null;
                            for (Annotation paramAnnotation : paramAnnotations) {
                                if (!(paramAnnotation instanceof JSONField)) continue;
                                JSONField jSONField = (JSONField)paramAnnotation;
                                break;
                            }
                            if (!(var25_53 != null || jacksonCompatible && TypeUtils.isJacksonCreator(factoryMethod))) {
                                throw new JSONException("illegal json creator");
                            }
                            String fieldName = null;
                            int ordinal = 0;
                            int serialzeFeatures = 0;
                            int parserFeatures = 0;
                            if (var25_53 != null) {
                                fieldName = var25_53.name();
                                ordinal = var25_53.ordinal();
                                serialzeFeatures = SerializerFeature.of(var25_53.serialzeFeatures());
                                parserFeatures = Feature.of(var25_53.parseFeatures());
                            }
                            if (fieldName == null || fieldName.length() == 0) {
                                if (lookupParameterNames == null) {
                                    lookupParameterNames = ASMUtils.lookupParameterNames(factoryMethod);
                                }
                                fieldName = lookupParameterNames[i];
                            }
                            Class<?> fieldClass = types[i];
                            Type fieldType = factoryMethod.getGenericParameterTypes()[i];
                            Field field = TypeUtils.getField(clazz, fieldName, declaredFields);
                            FieldInfo fieldInfo = new FieldInfo(fieldName, clazz, fieldClass, fieldType, field, ordinal, serialzeFeatures, parserFeatures);
                            JavaBeanInfo.add(fieldList, fieldInfo);
                        }
                        return new JavaBeanInfo(clazz, builderClass, null, null, factoryMethod, null, jsonType, fieldList);
                    }
                } else if (!isInterfaceOrAbstract) {
                    String className = clazz.getName();
                    Object[] paramNames = null;
                    if (kotlin && constructors.length > 0) {
                        paramNames = TypeUtils.getKoltinConstructorParameters(clazz);
                        creatorConstructor = TypeUtils.getKotlinConstructor(constructors, (String[])paramNames);
                        TypeUtils.setAccessible((AccessibleObject)creatorConstructor);
                    } else {
                        for (Annotation[] annotationArray : constructors) {
                            Object[] lookupParameterNames;
                            boolean is_public;
                            Class<?>[] parameterTypes = annotationArray.getParameterTypes();
                            if (className.equals("org.springframework.security.web.authentication.WebAuthenticationDetails")) {
                                if (parameterTypes.length != 2 || parameterTypes[0] != String.class || parameterTypes[1] != String.class) continue;
                                creatorConstructor = annotationArray;
                                creatorConstructor.setAccessible(true);
                                paramNames = ASMUtils.lookupParameterNames((AccessibleObject)annotationArray);
                                break;
                            }
                            if (className.equals("org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken")) {
                                if (parameterTypes.length != 3 || parameterTypes[0] != Object.class || parameterTypes[1] != Object.class || parameterTypes[2] != Collection.class) continue;
                                creatorConstructor = annotationArray;
                                creatorConstructor.setAccessible(true);
                                paramNames = new String[]{"principal", "credentials", "authorities"};
                                break;
                            }
                            if (className.equals("org.springframework.security.core.authority.SimpleGrantedAuthority")) {
                                if (parameterTypes.length != 1 || parameterTypes[0] != String.class) continue;
                                creatorConstructor = annotationArray;
                                paramNames = new String[]{"authority"};
                                break;
                            }
                            boolean bl2 = is_public = (annotationArray.getModifiers() & 1) != 0;
                            if (!is_public || (lookupParameterNames = ASMUtils.lookupParameterNames((AccessibleObject)annotationArray)) == null || lookupParameterNames.length == 0 || creatorConstructor != null && paramNames != null && lookupParameterNames.length <= paramNames.length) continue;
                            paramNames = lookupParameterNames;
                            creatorConstructor = annotationArray;
                        }
                    }
                    Class<?>[] types = null;
                    if (paramNames != null) {
                        types = creatorConstructor.getParameterTypes();
                    }
                    if (paramNames != null && types.length == paramNames.length) {
                        Annotation[][] paramAnnotationArrays = TypeUtils.getParameterAnnotations((Constructor)creatorConstructor);
                        for (int i = 0; i < types.length; ++i) {
                            int parserFeatures;
                            int serialzeFeatures;
                            int ordinal;
                            Annotation[] annotationArray = paramAnnotationArrays[i];
                            Object paramName = paramNames[i];
                            JSONField fieldAnnotation = null;
                            for (Annotation paramAnnotation : annotationArray) {
                                if (!(paramAnnotation instanceof JSONField)) continue;
                                fieldAnnotation = (JSONField)paramAnnotation;
                                break;
                            }
                            Class<?> fieldClass = types[i];
                            Type fieldType = creatorConstructor.getGenericParameterTypes()[i];
                            Field field = TypeUtils.getField(clazz, (String)paramName, declaredFields);
                            if (field != null && fieldAnnotation == null) {
                                fieldAnnotation = TypeUtils.getAnnotation(field, JSONField.class);
                            }
                            if (fieldAnnotation == null) {
                                ordinal = 0;
                                serialzeFeatures = 0;
                                parserFeatures = "org.springframework.security.core.userdetails.User".equals(className) && "password".equals(paramName) ? Feature.InitStringFieldAsEmpty.mask : 0;
                            } else {
                                String nameAnnotated = fieldAnnotation.name();
                                if (nameAnnotated.length() != 0) {
                                    paramName = nameAnnotated;
                                }
                                ordinal = fieldAnnotation.ordinal();
                                serialzeFeatures = SerializerFeature.of(fieldAnnotation.serialzeFeatures());
                                parserFeatures = Feature.of(fieldAnnotation.parseFeatures());
                            }
                            FieldInfo fieldInfo = new FieldInfo((String)paramName, clazz, fieldClass, fieldType, field, ordinal, serialzeFeatures, parserFeatures);
                            JavaBeanInfo.add(fieldList, fieldInfo);
                        }
                        if (!kotlin && !clazz.getName().equals("javax.servlet.http.Cookie")) {
                            return new JavaBeanInfo(clazz, builderClass, null, (Constructor<?>)creatorConstructor, null, null, jsonType, (List<FieldInfo>)fieldList);
                        }
                    } else {
                        throw new JSONException("default constructor not found. " + clazz);
                    }
                }
            }
        }
        if (defaultConstructor != null) {
            TypeUtils.setAccessible(defaultConstructor);
        }
        if (builderClass != null) {
            Object withPrefix = null;
            JSONPOJOBuilder builderAnno = TypeUtils.getAnnotation(builderClass, JSONPOJOBuilder.class);
            if (builderAnno != null) {
                withPrefix = builderAnno.withPrefix();
            }
            if (withPrefix == null) {
                withPrefix = "with";
            }
            for (Method method : builderClass.getMethods()) {
                StringBuilder properNameBuilder;
                String methodName;
                int n;
                if (Modifier.isStatic(method.getModifiers()) || !method.getReturnType().equals(builderClass)) continue;
                boolean bl3 = false;
                int serialzeFeatures = 0;
                int parserFeatures = 0;
                JSONField annotation = TypeUtils.getAnnotation(method, JSONField.class);
                if (annotation == null) {
                    annotation = TypeUtils.getSuperMethodAnnotation(clazz, method);
                }
                if (annotation != null) {
                    if (!annotation.deserialize()) continue;
                    n = annotation.ordinal();
                    serialzeFeatures = SerializerFeature.of(annotation.serialzeFeatures());
                    parserFeatures = Feature.of(annotation.parseFeatures());
                    if (annotation.name().length() != 0) {
                        String propertyName = annotation.name();
                        JavaBeanInfo.add(fieldList, new FieldInfo(propertyName, method, null, clazz, type, n, serialzeFeatures, parserFeatures, annotation, null, null, genericInfo));
                        continue;
                    }
                }
                if ((methodName = method.getName()).startsWith("set") && methodName.length() > 3) {
                    properNameBuilder = new StringBuilder(methodName.substring(3));
                } else if (((String)withPrefix).length() == 0) {
                    properNameBuilder = new StringBuilder(methodName);
                } else {
                    if (!methodName.startsWith((String)withPrefix) || methodName.length() <= ((String)withPrefix).length()) continue;
                    properNameBuilder = new StringBuilder(methodName.substring(((String)withPrefix).length()));
                }
                char c0 = properNameBuilder.charAt(0);
                if (((String)withPrefix).length() != 0 && !Character.isUpperCase(c0)) continue;
                properNameBuilder.setCharAt(0, Character.toLowerCase(c0));
                String propertyName = properNameBuilder.toString();
                JavaBeanInfo.add(fieldList, new FieldInfo(propertyName, method, null, clazz, type, n, serialzeFeatures, parserFeatures, annotation, null, null, genericInfo));
            }
            if (builderClass != null) {
                JSONPOJOBuilder builderAnnotation = TypeUtils.getAnnotation(builderClass, JSONPOJOBuilder.class);
                String buildMethodName = null;
                if (builderAnnotation != null) {
                    buildMethodName = builderAnnotation.buildMethod();
                }
                if (buildMethodName == null || buildMethodName.length() == 0) {
                    buildMethodName = "build";
                }
                try {
                    buildMethod = builderClass.getMethod(buildMethodName, new Class[0]);
                } catch (NoSuchMethodException paramAnnotationArrays) {
                } catch (SecurityException paramAnnotationArrays) {
                    // empty catch block
                }
                if (buildMethod == null) {
                    try {
                        buildMethod = builderClass.getMethod("create", new Class[0]);
                    } catch (NoSuchMethodException paramAnnotationArrays) {
                    } catch (SecurityException paramAnnotationArrays) {
                        // empty catch block
                    }
                }
                if (buildMethod == null) {
                    throw new JSONException("buildMethod not found.");
                }
                TypeUtils.setAccessible(buildMethod);
            }
        }
        for (Method method : methods) {
            int n;
            String propertyName;
            Class<?>[] types;
            Class<?> returnType;
            int ordinal = 0;
            int serialzeFeatures = 0;
            int n2 = 0;
            String methodName = method.getName();
            if (Modifier.isStatic(method.getModifiers()) || !(returnType = method.getReturnType()).equals(Void.TYPE) && !returnType.equals(method.getDeclaringClass()) || method.getDeclaringClass() == Object.class || (types = method.getParameterTypes()).length == 0 || types.length > 2) continue;
            JSONField annotation = TypeUtils.getAnnotation(method, JSONField.class);
            if (annotation != null && types.length == 2 && types[0] == String.class && types[1] == Object.class) {
                JavaBeanInfo.add(fieldList, new FieldInfo("", method, null, clazz, type, ordinal, serialzeFeatures, n2, annotation, null, null, genericInfo));
                continue;
            }
            if (types.length != 1) continue;
            if (annotation == null) {
                annotation = TypeUtils.getSuperMethodAnnotation(clazz, method);
            }
            if (annotation == null && methodName.length() < 4) continue;
            if (annotation != null) {
                if (!annotation.deserialize()) continue;
                ordinal = annotation.ordinal();
                serialzeFeatures = SerializerFeature.of(annotation.serialzeFeatures());
                int n3 = Feature.of(annotation.parseFeatures());
                if (annotation.name().length() != 0) {
                    String propertyName2 = annotation.name();
                    JavaBeanInfo.add(fieldList, new FieldInfo(propertyName2, method, null, clazz, type, ordinal, serialzeFeatures, n3, annotation, null, null, genericInfo));
                    continue;
                }
            }
            if (annotation == null && !methodName.startsWith("set") || builderClass != null) continue;
            char c3 = methodName.charAt(3);
            Field field = null;
            ArrayList<String> getMethodNameList = null;
            if (kotlin) {
                getMethodNameList = new ArrayList<String>();
                for (int i = 0; i < methods.length; ++i) {
                    if (!methods[i].getName().startsWith("get")) continue;
                    getMethodNameList.add(methods[i].getName());
                }
            }
            if (Character.isUpperCase(c3) || c3 > '\u0200') {
                if (kotlin) {
                    String getMethodName = "g" + methodName.substring(1);
                    propertyName = TypeUtils.getPropertyNameByMethodName(getMethodName);
                } else {
                    propertyName = TypeUtils.compatibleWithJavaBean ? TypeUtils.decapitalize(methodName.substring(3)) : TypeUtils.getPropertyNameByMethodName(methodName);
                }
            } else if (c3 == '_') {
                if (kotlin) {
                    String getMethodName = "g" + methodName.substring(1);
                    propertyName = getMethodNameList.contains(getMethodName) ? methodName.substring(3) : "is" + methodName.substring(3);
                    field = TypeUtils.getField(clazz, propertyName, declaredFields);
                } else {
                    propertyName = methodName.substring(4);
                    field = TypeUtils.getField(clazz, propertyName, declaredFields);
                    if (field == null) {
                        String temp = propertyName;
                        propertyName = methodName.substring(3);
                        field = TypeUtils.getField(clazz, propertyName, declaredFields);
                        if (field == null) {
                            propertyName = temp;
                        }
                    }
                }
            } else if (c3 == 'f') {
                propertyName = methodName.substring(3);
            } else if (methodName.length() >= 5 && Character.isUpperCase(methodName.charAt(4))) {
                propertyName = TypeUtils.decapitalize(methodName.substring(3));
            } else {
                propertyName = methodName.substring(3);
                field = TypeUtils.getField(clazz, propertyName, declaredFields);
                if (field == null) continue;
            }
            if (field == null) {
                field = TypeUtils.getField(clazz, propertyName, declaredFields);
            }
            if (field == null && types[0] == Boolean.TYPE) {
                String isFieldName = "is" + Character.toUpperCase(propertyName.charAt(0)) + propertyName.substring(1);
                field = TypeUtils.getField(clazz, isFieldName, declaredFields);
            }
            JSONField fieldAnnotation = null;
            if (field != null && (fieldAnnotation = TypeUtils.getAnnotation(field, JSONField.class)) != null) {
                if (!fieldAnnotation.deserialize()) continue;
                ordinal = fieldAnnotation.ordinal();
                serialzeFeatures = SerializerFeature.of(fieldAnnotation.serialzeFeatures());
                n = Feature.of(fieldAnnotation.parseFeatures());
                if (fieldAnnotation.name().length() != 0) {
                    propertyName = fieldAnnotation.name();
                    JavaBeanInfo.add(fieldList, new FieldInfo(propertyName, method, field, clazz, type, ordinal, serialzeFeatures, n, annotation, fieldAnnotation, null, genericInfo));
                    continue;
                }
            }
            if (propertyNamingStrategy != null) {
                propertyName = propertyNamingStrategy.translate(propertyName);
            }
            JavaBeanInfo.add(fieldList, new FieldInfo(propertyName, method, field, clazz, type, ordinal, serialzeFeatures, n, annotation, fieldAnnotation, null, genericInfo));
        }
        Field[] fields = clazz.getFields();
        JavaBeanInfo.computeFields(clazz, type, propertyNamingStrategy, fieldList, fields);
        for (Method method : clazz.getMethods()) {
            void var25_69;
            FieldInfo fieldInfo;
            String methodName = method.getName();
            if (methodName.length() < 4 || Modifier.isStatic(method.getModifiers()) || builderClass != null || !methodName.startsWith("get") || !Character.isUpperCase(methodName.charAt(3)) || method.getParameterTypes().length != 0 || !Collection.class.isAssignableFrom(method.getReturnType()) && !Map.class.isAssignableFrom(method.getReturnType()) && AtomicBoolean.class != method.getReturnType() && AtomicInteger.class != method.getReturnType() && AtomicLong.class != method.getReturnType()) continue;
            Field collectionField = null;
            JSONField annotation = TypeUtils.getAnnotation(method, JSONField.class);
            if (annotation != null && annotation.deserialize()) continue;
            if (annotation != null && annotation.name().length() > 0) {
                String string = annotation.name();
            } else {
                String string = TypeUtils.getPropertyNameByMethodName(methodName);
                Field field = TypeUtils.getField(clazz, string, declaredFields);
                if (field != null) {
                    JSONField fieldAnnotation = TypeUtils.getAnnotation(field, JSONField.class);
                    if (fieldAnnotation != null && !fieldAnnotation.deserialize()) continue;
                    if (Collection.class.isAssignableFrom(method.getReturnType()) || Map.class.isAssignableFrom(method.getReturnType())) {
                        collectionField = field;
                    }
                }
            }
            if (propertyNamingStrategy != null) {
                void var25_67;
                String string = propertyNamingStrategy.translate((String)var25_67);
            }
            if ((fieldInfo = JavaBeanInfo.getField(fieldList, (String)var25_69)) != null) continue;
            JavaBeanInfo.add(fieldList, new FieldInfo((String)var25_69, method, collectionField, clazz, type, 0, 0, 0, annotation, null, null, genericInfo));
        }
        if (fieldList.size() == 0) {
            if (TypeUtils.isXmlField(clazz)) {
                fieldBased = true;
            }
            if (fieldBased) {
                for (Class<?> currentClass = clazz; currentClass != null; currentClass = currentClass.getSuperclass()) {
                    JavaBeanInfo.computeFields(clazz, type, propertyNamingStrategy, fieldList, declaredFields);
                }
            }
        }
        return new JavaBeanInfo(clazz, builderClass, defaultConstructor, (Constructor<?>)creatorConstructor, factoryMethod, buildMethod, jsonType, (List<FieldInfo>)fieldList);
    }

    private static void computeFields(Class<?> clazz, Type type, PropertyNamingStrategy propertyNamingStrategy, List<FieldInfo> fieldList, Field[] fields) {
        Map<TypeVariable, Type> genericInfo = JavaBeanInfo.buildGenericInfo(clazz);
        for (Field field : fields) {
            int modifiers = field.getModifiers();
            if ((modifiers & 8) != 0) continue;
            if ((modifiers & 0x10) != 0) {
                boolean supportReadOnly;
                Class<?> fieldType = field.getType();
                boolean bl = supportReadOnly = Map.class.isAssignableFrom(fieldType) || Collection.class.isAssignableFrom(fieldType) || AtomicLong.class.equals(fieldType) || AtomicInteger.class.equals(fieldType) || AtomicBoolean.class.equals(fieldType);
                if (!supportReadOnly) continue;
            }
            boolean contains = false;
            for (FieldInfo item : fieldList) {
                if (!item.name.equals(field.getName())) continue;
                contains = true;
                break;
            }
            if (contains) continue;
            int ordinal = 0;
            int serialzeFeatures = 0;
            int parserFeatures = 0;
            String propertyName = field.getName();
            JSONField fieldAnnotation = TypeUtils.getAnnotation(field, JSONField.class);
            if (fieldAnnotation != null) {
                if (!fieldAnnotation.deserialize()) continue;
                ordinal = fieldAnnotation.ordinal();
                serialzeFeatures = SerializerFeature.of(fieldAnnotation.serialzeFeatures());
                parserFeatures = Feature.of(fieldAnnotation.parseFeatures());
                if (fieldAnnotation.name().length() != 0) {
                    propertyName = fieldAnnotation.name();
                }
            }
            if (propertyNamingStrategy != null) {
                propertyName = propertyNamingStrategy.translate(propertyName);
            }
            JavaBeanInfo.add(fieldList, new FieldInfo(propertyName, null, field, clazz, type, ordinal, serialzeFeatures, parserFeatures, null, fieldAnnotation, null, genericInfo));
        }
    }

    static Constructor<?> getDefaultConstructor(Class<?> clazz, Constructor<?>[] constructors) {
        if (Modifier.isAbstract(clazz.getModifiers())) {
            return null;
        }
        Constructor<?> defaultConstructor = null;
        for (Constructor<?> constructor : constructors) {
            if (constructor.getParameterTypes().length != 0) continue;
            defaultConstructor = constructor;
            break;
        }
        if (defaultConstructor == null && clazz.isMemberClass() && !Modifier.isStatic(clazz.getModifiers())) {
            for (Constructor<?> constructor : constructors) {
                Class<?>[] types = constructor.getParameterTypes();
                if (types.length != 1 || !types[0].equals(clazz.getDeclaringClass())) continue;
                defaultConstructor = constructor;
                break;
            }
        }
        return defaultConstructor;
    }

    public static Constructor<?> getCreatorConstructor(Constructor[] constructors) {
        Constructor creatorConstructor = null;
        for (Constructor constructor : constructors) {
            JSONCreator annotation = constructor.getAnnotation(JSONCreator.class);
            if (annotation == null) continue;
            if (creatorConstructor != null) {
                throw new JSONException("multi-JSONCreator");
            }
            creatorConstructor = constructor;
        }
        if (creatorConstructor != null) {
            return creatorConstructor;
        }
        for (Constructor constructor : constructors) {
            Annotation[][] paramAnnotationArrays = TypeUtils.getParameterAnnotations(constructor);
            if (paramAnnotationArrays.length == 0) continue;
            boolean match = true;
            for (Annotation[] paramAnnotationArray : paramAnnotationArrays) {
                boolean paramMatch = false;
                for (Annotation paramAnnotation : paramAnnotationArray) {
                    if (!(paramAnnotation instanceof JSONField)) continue;
                    paramMatch = true;
                    break;
                }
                if (paramMatch) continue;
                match = false;
                break;
            }
            if (!match) continue;
            if (creatorConstructor != null) {
                throw new JSONException("multi-JSONCreator");
            }
            creatorConstructor = constructor;
        }
        return creatorConstructor;
    }

    private static Method getFactoryMethod(Class<?> clazz, Method[] methods, boolean jacksonCompatible) {
        Method factoryMethod = null;
        for (Method method : methods) {
            JSONCreator annotation;
            if (!Modifier.isStatic(method.getModifiers()) || !clazz.isAssignableFrom(method.getReturnType()) || (annotation = TypeUtils.getAnnotation(method, JSONCreator.class)) == null) continue;
            if (factoryMethod != null) {
                throw new JSONException("multi-JSONCreator");
            }
            factoryMethod = method;
        }
        if (factoryMethod == null && jacksonCompatible) {
            for (Method method : methods) {
                if (!TypeUtils.isJacksonCreator(method)) continue;
                factoryMethod = method;
                break;
            }
        }
        return factoryMethod;
    }

    public static Class<?> getBuilderClass(JSONType type) {
        return JavaBeanInfo.getBuilderClass(null, type);
    }

    public static Class<?> getBuilderClass(Class<?> clazz, JSONType type) {
        if (clazz != null && clazz.getName().equals("org.springframework.security.web.savedrequest.DefaultSavedRequest")) {
            return TypeUtils.loadClass("org.springframework.security.web.savedrequest.DefaultSavedRequest$Builder");
        }
        if (type == null) {
            return null;
        }
        Class<?> builderClass = type.builder();
        if (builderClass == Void.class) {
            return null;
        }
        return builderClass;
    }
}

