/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp.ScanFun;

import burp.BurpExtender;
import burp.Common;
import burp.IBurpExtenderCallbacks;
import burp.IExtensionHelpers;
import burp.IHttpRequestResponse;
import java.net.URL;
import java.util.List;

public class AxisScan {

    // 现有服务路径
    private static final String[] poc_axis_services = new String[]{
            "services", "axis/services", "axis2/services", "api/axis",
            "webservice", "webservices", "soap", "Service1.asmx", "Service.asmx"
    };

    private static final String[] poc_axis_dir = new String[]{
            "", "axis/", "axis2/", "webservices/", "soap/", "service/", "services/", "webservice/", "webservices/"
    };

    private static final String[] wsdl_suffixes = new String[]{
            "?wsdl", "?WSDL"
    };

//    private static final String[] graphql_paths = new String[]{
//            "graphql", "api/graphql", "v1/graphql", "gql", "graphiql","graphql.php","graphiql.php"
//    };

    public static IHttpRequestResponse AxisScan(IHttpRequestResponse reqres, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers) {
        String res;
        byte[] body;
        List<String> headers = helpers.analyzeRequest(reqres.getRequest()).getHeaders();
        URL url = helpers.analyzeRequest(reqres).getUrl();

        // Axis path scan
        for (String path : poc_axis_dir) {
            headers.set(0, "GET /" + path + " HTTP/1.1");
            body = helpers.buildHttpMessage(headers, null);
            IHttpRequestResponse axisdirreqres = callbacks.makeHttpRequest(reqres.getHttpService(), body);
            sleepSafe();
            if (axisdirreqres == null || axisdirreqres.getResponse() == null) continue;

            res = Common.getResbody(axisdirreqres.getResponse(), helpers);
            if (res.contains("Validate") && res.contains("Welcome") && res.contains("Axis")
                    && res.contains("deployed") && res.contains("installation") && res.contains("Admin")) {
                BurpExtender.scannedDomainURL_axis.add(url.getHost() + ":" + url.getPort());
                return axisdirreqres;
            }
        }

        // Axis service/wsdl scan
        for (String path : poc_axis_services) {
            headers.set(0, "GET /" + path + " HTTP/1.1");
            body = helpers.buildHttpMessage(headers, null);
            IHttpRequestResponse servicesRes = callbacks.makeHttpRequest(reqres.getHttpService(), body);
            sleepSafe();
            if (matchService(servicesRes, helpers)) {
                BurpExtender.scannedDomainURL_axis.add(url.getHost() + ":" + url.getPort());
                return servicesRes;
            }

            for (String suffix : wsdl_suffixes) {
                headers.set(0, "GET /" + path + suffix + " HTTP/1.1");
                body = helpers.buildHttpMessage(headers, null);
                IHttpRequestResponse wsdlRes = callbacks.makeHttpRequest(reqres.getHttpService(), body);
                sleepSafe();
                if (matchWSDL(wsdlRes, helpers)) {
                    BurpExtender.scannedDomainURL_axis.add(url.getHost() + ":" + url.getPort());
                    return wsdlRes;
                }
            }
        }

        // 新增：GraphQL扫描
//        IHttpRequestResponse graphqlRes = GraphQLScan(reqres, callbacks, helpers);
//        if (graphqlRes != null) {
//            BurpExtender.scannedDomainURL_axis.add(url.getHost() + ":" + url.getPort());
//            return graphqlRes;
//        }

        BurpExtender.scannedDomainURL_axis.add(url.getHost() + ":" + url.getPort());
        return null;
    }

    // GraphQL 扫描方法
//    private static IHttpRequestResponse GraphQLScan(IHttpRequestResponse reqres, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers) {
//        List<String> headers = helpers.analyzeRequest(reqres.getRequest()).getHeaders();
//        URL url = helpers.analyzeRequest(reqres).getUrl();
//
//        for (String path : graphql_paths) {
//            headers.set(0, "POST /" + path + " HTTP/1.1");
//            headers.removeIf(h -> h.startsWith("Content-Length"));
//            headers.removeIf(h -> h.startsWith("Content-Type"));
//            headers.add("Content-Type: application/json");
//
//            String payload = "{\"query\":\"{__typename}\"}";
//            byte[] body = helpers.buildHttpMessage(headers, payload.getBytes());
//
//            IHttpRequestResponse graphqlResp = callbacks.makeHttpRequest(reqres.getHttpService(), body);
//            sleepSafe();
//            if (graphqlResp == null || graphqlResp.getResponse() == null) continue;
//
//            String resBody = Common.getResbody(graphqlResp.getResponse(), helpers);
//            if (resBody.contains("__typename") || resBody.contains("__schema") || resBody.contains("data")) {
//                BurpExtender.stdout.println("[+] GraphQL interface detected: /" + path);
//                return graphqlResp;
//            }
//        }
//
//        return null;
//    }

    private static boolean matchService(IHttpRequestResponse res, IExtensionHelpers helpers) {
        if (res == null || res.getResponse() == null) return false;
        String body = Common.getResbody(res.getResponse(), helpers);
        return body.contains("WSDL") || body.contains("?wsdl") ||
                body.contains("And now... Some Services") ||
                body.contains("Available SOAP services") ||
                body.contains("definitions xmlns:wsdl");
    }

    private static boolean matchWSDL(IHttpRequestResponse res, IExtensionHelpers helpers) {
        if (res == null || res.getResponse() == null) return false;
        String body = Common.getResbody(res.getResponse(), helpers);
        return body.contains("<wsdl:definitions") ||
                body.contains("<definitions") ||
                body.contains("xmlns:wsdl") ||
                body.contains("<soap:binding");
    }

    private static void sleepSafe() {
        try {
            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
        } catch (InterruptedException e) {
            BurpExtender.stdout.println("[!] Sleep error: " + e.getMessage());
        }
    }
}


