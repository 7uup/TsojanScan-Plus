package burp.ScanFun;

import burp.*;
import burp.utils.Utils;

import java.net.URL;
import java.util.List;

public class JbossScan {

    private static final String[] Jboss_console_uri=new String[]{"/admin-console/index.seam", "/web-console/","/jmx-console/","/console/","/jbossws/",};


    public static IHttpRequestResponse Jboss_CVE_2017_12149_Scan(IHttpRequestResponse reqres, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers, String custompath) throws InterruptedException {
        List<String> headers = helpers.analyzeRequest(reqres.getRequest()).getHeaders();
        URL url = helpers.analyzeRequest(reqres).getUrl();
        headers.set(0, "GET " + custompath + "/invoker/readonly HTTP/1.1");
        byte[] body = helpers.buildHttpMessage(headers, null);
        IHttpRequestResponse weblogicreqres = callbacks.makeHttpRequest(reqres.getHttpService(), body);
        if (weblogicreqres!=null && weblogicreqres.getResponse()!=null && (helpers.analyzeResponse(weblogicreqres.getResponse()).getStatusCode()==500) && new String(weblogicreqres.getResponse()).contains("java.io.EOFException")){
            BurpExtender.scannedDomainURL_Jboss_rce.add(url.getHost() + ":" + url.getPort());
            return weblogicreqres;
        }
        return null;
    }

    public static IHttpRequestResponse Jboss_Console(IHttpRequestResponse reqres, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers){
        List<String> headers = helpers.analyzeRequest(reqres.getRequest()).getHeaders();
        for(String uri:Jboss_console_uri){
            headers.set(0, "GET " + uri + " HTTP/1.1");
            byte[] body2 = helpers.buildHttpMessage(headers, null);
            IHttpRequestResponse JbossFin = callbacks.makeHttpRequest(reqres.getHttpService(), body2);
            if (JbossFin!=null && JbossFin.getResponse()!=null && helpers.analyzeResponse(JbossFin.getResponse()).getStatusCode()!=404 && helpers.analyzeResponse(JbossFin.getResponse()).getStatusCode()!=302 && new String(JbossFin.getResponse()).contains("Jboss")){
                BurpExtender.scannedDomainURL_Jboss_rce.add(helpers.analyzeRequest(reqres).getUrl().getHost() + ":" + helpers.analyzeRequest(reqres).getUrl().getPort());
                return JbossFin;
            }

        }
        return null;
    }


    public static IHttpRequestResponse Jboss_CVE_2017_7504_Scan(IHttpRequestResponse reqres, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers, String custompath) throws InterruptedException {
        List<String> headers = helpers.analyzeRequest(reqres.getRequest()).getHeaders();
        URL url = helpers.analyzeRequest(reqres).getUrl();
        headers.set(0, "GET " + custompath + "/jbossmq-httpil/HTTPServerILServlet HTTP/1.1");
        byte[] body = helpers.buildHttpMessage(headers, null);
        IHttpRequestResponse weblogicreqres = callbacks.makeHttpRequest(reqres.getHttpService(), body);
        if (weblogicreqres!=null && weblogicreqres.getResponse()!=null && (helpers.analyzeResponse(weblogicreqres.getResponse()).getStatusCode()==200) && new String(weblogicreqres.getResponse()).contains("JBossMQ HTTP-IL")){
            BurpExtender.scannedDomainURL_Jboss_rce.add(url.getHost() + ":" + url.getPort());
            return weblogicreqres;
        }
        return null;
    }
}
