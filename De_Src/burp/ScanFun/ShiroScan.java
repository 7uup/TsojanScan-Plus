/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp.ScanFun;

import burp.IBurpExtenderCallbacks;
import burp.IExtensionHelpers;
import burp.IHttpRequestResponse;
import burp.IRequestInfo;
import java.util.List;

public class ShiroScan {
    public static IHttpRequestResponse ShiroScan(IHttpRequestResponse baseRequestResponse, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers) {
        IRequestInfo analyzeRequest = helpers.analyzeRequest(baseRequestResponse.getRequest());
        List<String> headers = analyzeRequest.getHeaders();
        String headerst = headers.get(headers.size() - 1);
        headers.set(headers.size() - 1, "Cookie: rememberMe=1");
        headers.add(headerst);
        int bodyOffset = analyzeRequest.getBodyOffset();
        byte[] byte_Request = baseRequestResponse.getRequest();
        String request = new String(byte_Request);
        String body = request.substring(bodyOffset);
        byte[] byte_body = body.getBytes();
        byte[] new_Request = helpers.buildHttpMessage(headers, byte_body);
        IHttpRequestResponse requestResponse = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), new_Request);
        List<String> resheaders = helpers.analyzeResponse(requestResponse.getResponse()).getHeaders();
        for (String reshead : resheaders) {
            if (!reshead.contains("rememberMe=deleteMe")) continue;
            return requestResponse;
        }
        return null;
    }
}

