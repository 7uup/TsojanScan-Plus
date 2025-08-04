package burp.ScanFun;

import burp.BurpExtender;
import burp.IBurpExtenderCallbacks;
import burp.IExtensionHelpers;
import burp.IHttpRequestResponse;
import burp.utils.Utils;

import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OssScan {

    private static Set<String> bypassedHosts = new HashSet<>();
    private static String[] perix= new String[]{".css", ".js", ".png", ".jpg", ".gif", ".jpeg", ".svg", ".woff", ".woff2", ".ttf", ".ico", ".iso", ".xlsx", ".docs", ".doc", ".xls", ".ios", ".apk", ".mp3", ".mp4", ".swf", ".otf",".pdf"};
//    private static String[] oss_host=new String[]{"oss-cn-hangzhou.aliyuncs.com","oss-cn-shanghai.aliyuncs.com","oss-cn-beijing.aliyuncs.com","oss-cn-shenzhen.aliyuncs.com","oss-us-west-1.aliyuncs.com","oss-ap-northeast-1.aliyuncs.com","cos.ap-shanghai.myqcloud.com","cos.ap-beijing.myqcloud.com","cos.ap-guangzhou.myqcloud.com","cos.ap-hongkong.myqcloud.com","cos.us-west.myqcloud.com","s3.amazonaws.com","s3.us-west-1.amazonaws.com","s3.us-east-1.amazonaws.com","storage.googleapis.com","storage.cloud.google.com","s3.eu-central-1.wasabisys.com","s3.wasabisys.com","s3.filebase.com","nyc3.digitaloceanspaces.com","sgp1.digitaloceanspaces.com","ams3.digitaloceanspaces.com","b2api.backblazeb2.com"};

    private static String[] oss_host=new String[]{"aliyuncs.com","myqcloud.com","s3.amazonaws.com","s3.us-west-1.amazonaws.com","s3.us-east-1.amazonaws.com","storage.googleapis.com","storage.cloud.google.com","s3.eu-central-1.wasabisys.com","s3.wasabisys.com","s3.filebase.com","nyc3.digitaloceanspaces.com","sgp1.digitaloceanspaces.com","ams3.digitaloceanspaces.com","b2api.backblazeb2.com"};
    public static IHttpRequestResponse OssScan(IHttpRequestResponse baseRequestResponse, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers,int type) throws InterruptedException {
        byte[] body;

        URL url = helpers.analyzeRequest(baseRequestResponse).getUrl();
        String host = url.getHost();
        if (bypassedHosts.contains(host)) {
            BurpExtender.stdout.println("[*] Host " + host + " 已检测绕过，跳过扫描");
            return null;
        }
        BurpExtender.stdout.println("开始oss-listobject扫描");

        List<String> headers = new ArrayList<>(helpers.analyzeRequest(baseRequestResponse).getHeaders());
        List<String> queue = BurpExtender.MakeQueue_v2(url.getHost(), headers.get(0), BurpExtender.scannedDomainURL_Oss);
        ArrayList<String> scannedPath = new ArrayList<>();
        for (String path : queue) {
            Matcher matcher = Pattern.compile("GET (.*) HTTP/1.1").matcher(path);
            if (!matcher.find()) {
                continue;
            }
            String originalPath = matcher.group(1);
            scannedPath.add(path);
            if (type==1){
                for(String host_check:oss_host){
                    if(!host.contains(host_check)){
                        continue;
                    }else {
                        for (String perix_check:perix){
                            if (!originalPath.endsWith(perix_check)){
                                continue;
                            }else {
                                String newPath = AddPoc_oss(path);
                                String newPath1="GET "+newPath+" HTTP/1.1";
                                headers.set(0, newPath1);
                                byte[] request = helpers.buildHttpMessage(headers, null);
                                Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));

                                IHttpRequestResponse response = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), request);

                                if (response.getResponse()!=null && !(new String(response.getResponse()).contains("AccessDenied") || new String(response.getResponse()).contains("NoSuchKey")) && new String(response.getResponse()).contains("<ListBucketResult")){
                                    return response;
                                }else {
                                    return null;
                                }

                            }

                        }
                    }
                }
            }else if (type==2){
                for (String perix_check:perix){
                    if (!originalPath.endsWith(perix_check)){
                        continue;
                    }else {
                        String newPath = AddPoc_oss(path);
                        String newPath1="GET "+newPath+" HTTP/1.1";
                        headers.set(0, newPath1);
                        byte[] request = helpers.buildHttpMessage(headers, null);
                        Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));

                        IHttpRequestResponse response = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), request);

                        if (response.getResponse()!=null && !(new String(response.getResponse()).contains("AccessDenied") || new String(response.getResponse()).contains("NoSuchKey")) && new String(response.getResponse()).contains("<ListBucketResult")){
                            return response;
                        }else {
                            return null;
                        }

                    }

                }
            }
        }
        return null;


    }

    public static String AddPoc_oss(String rawRequestLine) {
        String[] parts = rawRequestLine.split(" ");
        if (parts.length < 2) {
            System.out.println("无法解析请求行: " + rawRequestLine);
            return null;
        }

        String path = parts[1];
        if (!path.startsWith("/")) {
            System.out.println("非法路径: " + path);
            return null;
        }

        String[] pathSegments = path.split("/");

        int nonEmptySegmentCount = 0;
        for (String segment : pathSegments) {
            if (!segment.isEmpty()) {
                nonEmptySegmentCount++;
            }
        }

        if (nonEmptySegmentCount < 1) {
            System.out.println("路径过短，无法处理: " + path);
            return null;
        }

        StringBuilder newPath = new StringBuilder();
        int nonEmptySeen = 0;
        for (String segment : pathSegments) {
            if (!segment.isEmpty()) {
                nonEmptySeen++;
                if (nonEmptySeen == nonEmptySegmentCount) {
                    break;
                }
                newPath.append("/").append(segment);
            }
        }

        return newPath.length() > 0 ? newPath.append("/").toString() : "/";
    }
}
