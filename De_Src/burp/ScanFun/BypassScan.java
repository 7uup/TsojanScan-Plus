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
    private static final String[] Suffix= new String[]{".json",".css",".js",";.js",";.css",";.png",";.jpg",";.gif",";.jsp",";login",";image",";css",";public",";.html",";html","%20","%09","#","/?error","/%26",";static",";swagger"};
    private static final String[] Prefix= new String[]{"/","/../","/..;/","/;/","/image/..;/","/image/../","/public/..;/","/login/..;/","/static/..;/","/js/..;/","/image/..%20/","/image/..%09/","/swagger/..;/"};
    static PrintWriter stdout;
    private static Set<String> bypassedHosts = new HashSet<>();

//scannedDomainURL_Bypass
public static IHttpRequestResponse ScanBypass(IHttpRequestResponse baseRequestResponse, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers) {
    URL url = helpers.analyzeRequest(baseRequestResponse).getUrl();
    String host = url.getHost();

    // 先判断是否已经绕过过，避免重复扫描
    if (bypassedHosts.contains(host)) {
        stdout.println("[*] Host " + host + " 已检测绕过，跳过扫描");
        return null;
    }
    stdout.println("开始test扫描");
    List<String> headers = new ArrayList<>(helpers.analyzeRequest(baseRequestResponse).getHeaders()); // 拷贝一份，防止修改原始headers
    int oldStatusCode = helpers.analyzeResponse(baseRequestResponse.getResponse()).getStatusCode();

    List<String> queue = BurpExtender.MakeQueue(url.getHost(), headers.get(0), BurpExtender.scannedDomainURL_Bypass);

    ArrayList<String> scannedPath = new ArrayList<>();

    for (String path : queue) {
        // 解析路径
        Matcher matcher = Pattern.compile("GET (.*?) HTTP/1.1").matcher(path);
        if (!matcher.find()) {
            continue;
        }
        String originalPath = matcher.group(1);
        scannedPath.add(path);

        try {
            // 先尝试所有后缀
            for (String pocSuffix : Suffix) {
                String newPath = BypassScan.AddPoc(path, pocSuffix);
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

                // 判断状态码和响应内容过滤掉认证失败等情况
                if (statusCode != 302 && statusCode != 401 && statusCode != 403 && statusCode != 404) {
                    if ((statusCode == oldStatusCode) &&
                            (!responseBody.contains("认证失败") ||
                            !responseBody.contains("未登录") ||
                            !responseBody.contains("请登录") ||
                            !responseBody.contains("token失效")||!responseBody.contains("失效")||!responseBody.contains("未授权"))) {
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

                if (statusCode != 302 && statusCode != 401 && statusCode != 403 && statusCode != 404 && statusCode != 301) {
                    if ((statusCode == oldStatusCode) &&
                            (!responseBody.contains("认证失败") ||
                                    !responseBody.contains("未登录") ||
                                    !responseBody.contains("请登录") ||
                                    !responseBody.contains("token失效")||!responseBody.contains("失效")||!responseBody.contains("未授权"))) {
                        bypassedHosts.add(host);
                        BurpExtender.addScannedURL(url.getHost() + ":" + url.getPort(), scannedPath, BurpExtender.scannedDomainURL_Bypass);
                        return response;
                    }
                }
            }

        } catch (Exception e) {
        }
    }
    stdout.println("结束test扫描");

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
}
