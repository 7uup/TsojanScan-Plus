/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp;

import burp.IBurpCollaboratorClientContext;
import burp.IContextMenuFactory;
import burp.ICookie;
import burp.IExtensionHelpers;
import burp.IExtensionStateListener;
import burp.IHttpHeader;
import burp.IHttpListener;
import burp.IHttpRequestResponse;
import burp.IHttpRequestResponsePersisted;
import burp.IHttpRequestResponseWithMarkers;
import burp.IHttpService;
import burp.IIntruderPayloadGeneratorFactory;
import burp.IIntruderPayloadProcessor;
import burp.IMenuItemHandler;
import burp.IMessageEditor;
import burp.IMessageEditorController;
import burp.IMessageEditorTabFactory;
import burp.IProxyListener;
import burp.IScanIssue;
import burp.IScanQueueItem;
import burp.IScannerCheck;
import burp.IScannerInsertionPointProvider;
import burp.IScannerListener;
import burp.IScopeChangeListener;
import burp.ISessionHandlingAction;
import burp.ITab;
import burp.ITempFile;
import burp.ITextEditor;
import java.awt.Component;
import java.io.File;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public interface IBurpExtenderCallbacks {
    public static final int TOOL_SUITE = 1;
    public static final int TOOL_TARGET = 2;
    public static final int TOOL_PROXY = 4;
    public static final int TOOL_SPIDER = 8;
    public static final int TOOL_SCANNER = 16;
    public static final int TOOL_INTRUDER = 32;
    public static final int TOOL_REPEATER = 64;
    public static final int TOOL_SEQUENCER = 128;
    public static final int TOOL_DECODER = 256;
    public static final int TOOL_COMPARER = 512;
    public static final int TOOL_EXTENDER = 1024;

    public void setExtensionName(String var1);

    public IExtensionHelpers getHelpers();

    public OutputStream getStdout();

    public OutputStream getStderr();

    public void printOutput(String var1);

    public void printError(String var1);

    public void registerExtensionStateListener(IExtensionStateListener var1);

    public List<IExtensionStateListener> getExtensionStateListeners();

    public void removeExtensionStateListener(IExtensionStateListener var1);

    public void registerHttpListener(IHttpListener var1);

    public List<IHttpListener> getHttpListeners();

    public void removeHttpListener(IHttpListener var1);

    public void registerProxyListener(IProxyListener var1);

    public List<IProxyListener> getProxyListeners();

    public void removeProxyListener(IProxyListener var1);

    public void registerScannerListener(IScannerListener var1);

    public List<IScannerListener> getScannerListeners();

    public void removeScannerListener(IScannerListener var1);

    public void registerScopeChangeListener(IScopeChangeListener var1);

    public List<IScopeChangeListener> getScopeChangeListeners();

    public void removeScopeChangeListener(IScopeChangeListener var1);

    public void registerContextMenuFactory(IContextMenuFactory var1);

    public List<IContextMenuFactory> getContextMenuFactories();

    public void removeContextMenuFactory(IContextMenuFactory var1);

    public void registerMessageEditorTabFactory(IMessageEditorTabFactory var1);

    public List<IMessageEditorTabFactory> getMessageEditorTabFactories();

    public void removeMessageEditorTabFactory(IMessageEditorTabFactory var1);

    public void registerScannerInsertionPointProvider(IScannerInsertionPointProvider var1);

    public List<IScannerInsertionPointProvider> getScannerInsertionPointProviders();

    public void removeScannerInsertionPointProvider(IScannerInsertionPointProvider var1);

    public void registerScannerCheck(IScannerCheck var1);

    public List<IScannerCheck> getScannerChecks();

    public void removeScannerCheck(IScannerCheck var1);

    public void registerIntruderPayloadGeneratorFactory(IIntruderPayloadGeneratorFactory var1);

    public List<IIntruderPayloadGeneratorFactory> getIntruderPayloadGeneratorFactories();

    public void removeIntruderPayloadGeneratorFactory(IIntruderPayloadGeneratorFactory var1);

    public void registerIntruderPayloadProcessor(IIntruderPayloadProcessor var1);

    public List<IIntruderPayloadProcessor> getIntruderPayloadProcessors();

    public void removeIntruderPayloadProcessor(IIntruderPayloadProcessor var1);

    public void registerSessionHandlingAction(ISessionHandlingAction var1);

    public List<ISessionHandlingAction> getSessionHandlingActions();

    public void removeSessionHandlingAction(ISessionHandlingAction var1);

    public void unloadExtension();

    public void addSuiteTab(ITab var1);

    public void removeSuiteTab(ITab var1);

    public void customizeUiComponent(Component var1);

    public IMessageEditor createMessageEditor(IMessageEditorController var1, boolean var2);

    public String[] getCommandLineArguments();

    public void saveExtensionSetting(String var1, String var2);

    public String loadExtensionSetting(String var1);

    public ITextEditor createTextEditor();

    public void sendToRepeater(String var1, int var2, boolean var3, byte[] var4, String var5);

    public void sendToIntruder(String var1, int var2, boolean var3, byte[] var4);

    public void sendToIntruder(String var1, int var2, boolean var3, byte[] var4, List<int[]> var5);

    public void sendToComparer(byte[] var1);

    public void sendToSpider(URL var1);

    public IScanQueueItem doActiveScan(String var1, int var2, boolean var3, byte[] var4);

    public IScanQueueItem doActiveScan(String var1, int var2, boolean var3, byte[] var4, List<int[]> var5);

    public void doPassiveScan(String var1, int var2, boolean var3, byte[] var4, byte[] var5);

    public IHttpRequestResponse makeHttpRequest(IHttpService var1, byte[] var2);

    public IHttpRequestResponse makeHttpRequest(IHttpService var1, byte[] var2, boolean var3);

    public byte[] makeHttpRequest(String var1, int var2, boolean var3, byte[] var4);

    public byte[] makeHttpRequest(String var1, int var2, boolean var3, byte[] var4, boolean var5);

    public byte[] makeHttp2Request(IHttpService var1, List<IHttpHeader> var2, byte[] var3);

    public byte[] makeHttp2Request(IHttpService var1, List<IHttpHeader> var2, byte[] var3, boolean var4);

    public byte[] makeHttp2Request(IHttpService var1, List<IHttpHeader> var2, byte[] var3, boolean var4, String var5);

    public boolean isInScope(URL var1);

    public void includeInScope(URL var1);

    public void excludeFromScope(URL var1);

    public void issueAlert(String var1);

    public IHttpRequestResponse[] getProxyHistory();

    public IHttpRequestResponse[] getSiteMap(String var1);

    public IScanIssue[] getScanIssues(String var1);

    public void generateScanReport(String var1, IScanIssue[] var2, File var3);

    public List<ICookie> getCookieJarContents();

    public void updateCookieJar(ICookie var1);

    public void addToSiteMap(IHttpRequestResponse var1);

    @Deprecated
    public void restoreState(File var1);

    @Deprecated
    public void saveState(File var1);

    @Deprecated
    public Map<String, String> saveConfig();

    @Deprecated
    public void loadConfig(Map<String, String> var1);

    public String saveConfigAsJson(String ... var1);

    public void loadConfigFromJson(String var1);

    public void setProxyInterceptionEnabled(boolean var1);

    public String[] getBurpVersion();

    public String getExtensionFilename();

    public boolean isExtensionBapp();

    public void exitSuite(boolean var1);

    public ITempFile saveToTempFile(byte[] var1);

    public IHttpRequestResponsePersisted saveBuffersToTempFiles(IHttpRequestResponse var1);

    public IHttpRequestResponseWithMarkers applyMarkers(IHttpRequestResponse var1, List<int[]> var2, List<int[]> var3);

    public String getToolName(int var1);

    public void addScanIssue(IScanIssue var1);

    public IBurpCollaboratorClientContext createBurpCollaboratorClientContext();

    @Deprecated
    public String[][] getParameters(byte[] var1);

    @Deprecated
    public String[] getHeaders(byte[] var1);

    @Deprecated
    public void registerMenuItem(String var1, IMenuItemHandler var2);
}

