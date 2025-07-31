/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.log;

import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.log.Log;
import com.mysql.cj.util.Util;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class LogFactory {
    public static Log getLogger(String className, String instanceName) {
        if (className == null) {
            throw ExceptionFactory.createException(WrongArgumentException.class, "Logger class can not be NULL");
        }
        if (instanceName == null) {
            throw ExceptionFactory.createException(WrongArgumentException.class, "Logger instance name can not be NULL");
        }
        try {
            Class<?> loggerClass = null;
            try {
                loggerClass = Class.forName(className);
            } catch (ClassNotFoundException nfe) {
                loggerClass = Class.forName(Util.getPackageName(LogFactory.class) + "." + className);
            }
            Constructor<?> constructor = loggerClass.getConstructor(String.class);
            return (Log)constructor.newInstance(instanceName);
        } catch (ClassNotFoundException cnfe) {
            throw ExceptionFactory.createException(WrongArgumentException.class, "Unable to load class for logger '" + className + "'", cnfe);
        } catch (NoSuchMethodException nsme) {
            throw ExceptionFactory.createException(WrongArgumentException.class, "Logger class does not have a single-arg constructor that takes an instance name", nsme);
        } catch (InstantiationException inse) {
            throw ExceptionFactory.createException(WrongArgumentException.class, "Unable to instantiate logger class '" + className + "', exception in constructor?", inse);
        } catch (InvocationTargetException ite) {
            throw ExceptionFactory.createException(WrongArgumentException.class, "Unable to instantiate logger class '" + className + "', exception in constructor?", ite);
        } catch (IllegalAccessException iae) {
            throw ExceptionFactory.createException(WrongArgumentException.class, "Unable to instantiate logger class '" + className + "', constructor not public", iae);
        } catch (ClassCastException cce) {
            throw ExceptionFactory.createException(WrongArgumentException.class, "Logger class '" + className + "' does not implement the '" + Log.class.getName() + "' interface", cce);
        }
    }
}

