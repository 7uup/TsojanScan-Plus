/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.admin;

import com.mysql.cj.Constants;
import com.mysql.cj.util.StringUtils;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class ServerController {
    public static final String BASEDIR_KEY = "basedir";
    public static final String DATADIR_KEY = "datadir";
    public static final String DEFAULTS_FILE_KEY = "defaults-file";
    public static final String EXECUTABLE_NAME_KEY = "executable";
    public static final String EXECUTABLE_PATH_KEY = "executablePath";
    private Process serverProcess = null;
    private Properties serverProps = null;

    public ServerController(String baseDir) {
        this.setBaseDir(baseDir);
    }

    public ServerController(String basedir, String datadir) {
    }

    public void setBaseDir(String baseDir) {
        this.getServerProps().setProperty(BASEDIR_KEY, baseDir);
    }

    public void setDataDir(String dataDir) {
        this.getServerProps().setProperty(DATADIR_KEY, dataDir);
    }

    public Process start() throws IOException {
        if (this.serverProcess != null) {
            throw new IllegalArgumentException("Server already started");
        }
        this.serverProcess = Runtime.getRuntime().exec(this.getCommandLine());
        return this.serverProcess;
    }

    public void stop(boolean forceIfNecessary) throws IOException {
        if (this.serverProcess != null) {
            String basedir = this.getServerProps().getProperty(BASEDIR_KEY);
            StringBuilder pathBuf = new StringBuilder(basedir);
            if (!basedir.endsWith(File.separator)) {
                pathBuf.append(File.separator);
            }
            pathBuf.append("bin");
            pathBuf.append(File.separator);
            pathBuf.append("mysqladmin shutdown");
            System.out.println(pathBuf.toString());
            Process mysqladmin = Runtime.getRuntime().exec(pathBuf.toString());
            int exitStatus = -1;
            try {
                exitStatus = mysqladmin.waitFor();
            } catch (InterruptedException interruptedException) {
                // empty catch block
            }
            if (exitStatus != 0 && forceIfNecessary) {
                this.forceStop();
            }
        }
    }

    public void forceStop() {
        if (this.serverProcess != null) {
            this.serverProcess.destroy();
            this.serverProcess = null;
        }
    }

    public synchronized Properties getServerProps() {
        if (this.serverProps == null) {
            this.serverProps = new Properties();
        }
        return this.serverProps;
    }

    private String getCommandLine() {
        StringBuilder commandLine = new StringBuilder(this.getFullExecutablePath());
        commandLine.append(this.buildOptionalCommandLine());
        return commandLine.toString();
    }

    private String getFullExecutablePath() {
        StringBuilder pathBuf = new StringBuilder();
        String optionalExecutablePath = this.getServerProps().getProperty(EXECUTABLE_PATH_KEY);
        if (optionalExecutablePath == null) {
            String basedir = this.getServerProps().getProperty(BASEDIR_KEY);
            pathBuf.append(basedir);
            if (!basedir.endsWith(File.separator)) {
                pathBuf.append(File.separatorChar);
            }
            if (this.runningOnWindows()) {
                pathBuf.append("bin");
            } else {
                pathBuf.append("libexec");
            }
            pathBuf.append(File.separatorChar);
        } else {
            pathBuf.append(optionalExecutablePath);
            if (!optionalExecutablePath.endsWith(File.separator)) {
                pathBuf.append(File.separatorChar);
            }
        }
        String executableName = this.getServerProps().getProperty(EXECUTABLE_NAME_KEY, "mysqld");
        pathBuf.append(executableName);
        return pathBuf.toString();
    }

    private String buildOptionalCommandLine() {
        StringBuilder commandLineBuf = new StringBuilder();
        if (this.serverProps != null) {
            for (String key : this.serverProps.keySet()) {
                String value = this.serverProps.getProperty(key);
                if (this.isNonCommandLineArgument(key)) continue;
                if (value != null && value.length() > 0) {
                    commandLineBuf.append(" \"");
                    commandLineBuf.append("--");
                    commandLineBuf.append(key);
                    commandLineBuf.append("=");
                    commandLineBuf.append(value);
                    commandLineBuf.append("\"");
                    continue;
                }
                commandLineBuf.append(" --");
                commandLineBuf.append(key);
            }
        }
        return commandLineBuf.toString();
    }

    private boolean isNonCommandLineArgument(String propName) {
        return propName.equals(EXECUTABLE_NAME_KEY) || propName.equals(EXECUTABLE_PATH_KEY);
    }

    private boolean runningOnWindows() {
        return StringUtils.indexOfIgnoreCase(Constants.OS_NAME, "WINDOWS") != -1;
    }
}

