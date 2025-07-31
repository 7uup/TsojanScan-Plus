/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp;

import burp.IHttpHeader;
import burp.IHttpRequestResponse;
import burp.IHttpService;
import burp.IParameter;
import burp.IRequestInfo;
import burp.IResponseInfo;
import burp.IResponseKeywords;
import burp.IResponseVariations;
import burp.IScannerInsertionPoint;
import java.net.URL;
import java.util.List;

public interface IExtensionHelpers {
    public IRequestInfo analyzeRequest(IHttpRequestResponse var1);

    public IRequestInfo analyzeRequest(IHttpService var1, byte[] var2);

    public IRequestInfo analyzeRequest(byte[] var1);

    public IResponseInfo analyzeResponse(byte[] var1);

    public IParameter getRequestParameter(byte[] var1, String var2);

    public String urlDecode(String var1);

    public String urlEncode(String var1);

    public byte[] urlDecode(byte[] var1);

    public byte[] urlEncode(byte[] var1);

    public byte[] base64Decode(String var1);

    public byte[] base64Decode(byte[] var1);

    public String base64Encode(String var1);

    public String base64Encode(byte[] var1);

    public byte[] stringToBytes(String var1);

    public String bytesToString(byte[] var1);

    public int indexOf(byte[] var1, byte[] var2, boolean var3, int var4, int var5);

    public byte[] buildHttpMessage(List<String> var1, byte[] var2);

    public byte[] buildHttpRequest(URL var1);

    public byte[] addParameter(byte[] var1, IParameter var2);

    public byte[] removeParameter(byte[] var1, IParameter var2);

    public byte[] updateParameter(byte[] var1, IParameter var2);

    public byte[] toggleRequestMethod(byte[] var1);

    public IHttpService buildHttpService(String var1, int var2, String var3);

    public IHttpService buildHttpService(String var1, int var2, boolean var3);

    public IParameter buildParameter(String var1, String var2, byte var3);

    public IHttpHeader buildHeader(String var1, String var2);

    public IScannerInsertionPoint makeScannerInsertionPoint(String var1, byte[] var2, int var3, int var4);

    public IResponseVariations analyzeResponseVariations(byte[] ... var1);

    public IResponseKeywords analyzeResponseKeywords(List<String> var1, byte[] ... var2);
}

