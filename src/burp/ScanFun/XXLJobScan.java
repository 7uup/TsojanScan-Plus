package burp.ScanFun;

import burp.BurpExtender;
import burp.IBurpExtenderCallbacks;
import burp.IExtensionHelpers;
import burp.IHttpRequestResponse;
import burp.utils.Common;
import burp.utils.Config;

import java.net.URL;
import java.util.List;

public class XXLJobScan {
    private static final String[] poc_xxljob_user_pass = new String[]{"admin:admin","admin:123456","test:test","test:123456"};

    public static IHttpRequestResponse xxl_job_FingerScan(IHttpRequestResponse reqres, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers) {
        List<String> headers = helpers.analyzeRequest(reqres.getRequest()).getHeaders();
        URL url = helpers.analyzeRequest(reqres).getUrl();
        headers.set(0, "GET "  + "/xxl-job-admin/toLogin  HTTP/1.1");
        byte[] body = helpers.buildHttpMessage(headers, null);
        IHttpRequestResponse xxlreqres = callbacks.makeHttpRequest(reqres.getHttpService(), body);
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(Config.get("enabled_sleep_value", "500")));
        } catch (InterruptedException e) {
            BurpExtender.stdout.println(e.getMessage());
        }
        if (xxlreqres != null || xxlreqres.getResponse() != null){
            String res = Common.getResbody(xxlreqres.getResponse(), helpers);
            if (res.contains("xxl-job-admin/static")||res.contains("任务调度中心")) {
                BurpExtender.scannedDomainURL_xxljob.add(url.getHost() + ":" + url.getPort());
                return xxlreqres;
            }

        };
        return null;
    }




    public static IHttpRequestResponse xxl_job_exec_FingerScan(IHttpRequestResponse reqres, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers) {
        List<String> headers = helpers.analyzeRequest(reqres.getRequest()).getHeaders();
        URL url = helpers.analyzeRequest(reqres).getUrl();
        headers.set(0, "GET "  + "/run  HTTP/1.1");
        headers.add("XXL-JOB-ACCESS-TOKEN:default_token");
        byte[] body = helpers.buildHttpMessage(headers, null);
        IHttpRequestResponse xxlreqres = callbacks.makeHttpRequest(reqres.getHttpService(), body);
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(Config.get("enabled_sleep_value", "500")));
        } catch (InterruptedException e) {
            BurpExtender.stdout.println(e.getMessage());
        }
        if (xxlreqres != null || xxlreqres.getResponse() != null){
            String res = Common.getResbody(xxlreqres.getResponse(), helpers);
            if (res.contains("invalid request, HttpMethod not support")) {
                BurpExtender.scannedDomainURL_xxljob.add(url.getHost() + ":" + url.getPort());
                return xxlreqres;
            }

        };
        return null;
    }

    public static IHttpRequestResponse xxl_job_weak_password(IHttpRequestResponse reqres, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers) {
        List<String> headers = helpers.analyzeRequest(reqres.getRequest()).getHeaders();
        URL url = helpers.analyzeRequest(reqres).getUrl();
        headers.set(0, "POST "  + "/xxl-job-admin/login  HTTP/1.1");
        for (String user_pass : poc_xxljob_user_pass){
            String[] user_pass_split = user_pass.split(":");
            headers.add("Content-Type: application/x-www-form-urlencoded");
            String poc="userName=" + user_pass_split[0]+"&password="+user_pass_split[1];
            byte[] body = helpers.buildHttpMessage(headers,poc.getBytes());
            IHttpRequestResponse xxlreqres = callbacks.makeHttpRequest(reqres.getHttpService(), body);
            try {
                Thread.currentThread();
                Thread.sleep(Integer.parseInt(Config.get("enabled_sleep_value", "500")));
                if (xxlreqres != null || xxlreqres.getResponse() != null){
                    String res = Common.getResbody(xxlreqres.getResponse(), helpers);
                    if (!(res.contains("账号或密码错误")||res.contains("账号或密码为空"))) {
                        BurpExtender.scannedDomainURL_xxljob.add(url.getHost() + ":" + url.getPort());
                        return xxlreqres;
                    }else {
                        return null;
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

        return null;

    }

    public static IHttpRequestResponse xxl_job_exec_Scan(IHttpRequestResponse reqres, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers) {
        List<String> headers = helpers.analyzeRequest(reqres.getRequest()).getHeaders();
        URL url = helpers.analyzeRequest(reqres).getUrl();
        headers.set(0, "POST "  + "/run  HTTP/1.1");
        headers.add("X-Requested-With: XMLHttpRequest");
        headers.add("XXL-JOB-ACCESS-TOKEN:default_token");
        headers.add("Content-Type: application/json");

        byte[] body = helpers.buildHttpMessage(headers,"{}".getBytes());
        IHttpRequestResponse xxlreqres = callbacks.makeHttpRequest(reqres.getHttpService(), body);
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(Config.get("enabled_sleep_value", "500")));
            if (xxlreqres != null || xxlreqres.getResponse() != null){
                if (!new String(xxlreqres.getResponse()).contains("The access token is wrong")){
                    BurpExtender.scannedDomainURL_xxljob.add(url.getHost() + ":" + url.getPort());
                    return xxlreqres;
                }
            }
        }catch (Exception e){

        }
        return null;

    }

    public static IHttpRequestResponse doxxlJob(IHttpRequestResponse reqres, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers){
        URL url = helpers.analyzeRequest(reqres).getUrl();
        IHttpRequestResponse xxl_job_FingerScan = xxl_job_FingerScan(reqres, callbacks, helpers);
        if (xxl_job_FingerScan!=null && xxl_job_FingerScan.getResponse()!=null){
            IHttpRequestResponse xxl_job_weak_password=xxl_job_weak_password(reqres, callbacks, helpers);
            if (xxl_job_weak_password!=null && xxl_job_weak_password.getResponse()!=null){
                BurpExtender.scannedDomainURL_xxljob.add(url.getHost() + ":" + url.getPort());
                return xxl_job_weak_password;
            }
        }

        IHttpRequestResponse xxl_job_exec_FingerScan = xxl_job_exec_FingerScan(reqres, callbacks, helpers);
        if (xxl_job_exec_FingerScan!=null && xxl_job_exec_FingerScan.getResponse()!=null){
            IHttpRequestResponse xxl_job_exec_Scan = xxl_job_exec_Scan(reqres, callbacks, helpers);
            if (xxl_job_exec_Scan!=null && xxl_job_exec_Scan.getResponse()!=null){
                BurpExtender.scannedDomainURL_xxljob.add(url.getHost() + ":" + url.getPort());
                return xxl_job_exec_Scan;
            }

        }

        return null;
    }



}
