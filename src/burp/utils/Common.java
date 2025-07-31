/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp.utils;

import burp.BurpExtender;
import burp.IExtensionHelpers;
import burp.IHttpRequestResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Common {
    public static List<int[]> getMatches(byte[] response, byte[] match, IExtensionHelpers helpers) {
        ArrayList<int[]> matches = new ArrayList<int[]>();
        for (int start = 0; start < response.length && (start = helpers.indexOf(response, match, true, start, response.length)) != -1; start += match.length) {
            matches.add(new int[]{start, start + match.length});
        }
        return matches;
    }

    public static boolean isJSON(String str) {
        boolean result;
        try {
            JSONObject jsonObject = JSONObject.parseObject(str);
            result = jsonObject.size() > 0;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    public static String getResbody(byte[] res, IExtensionHelpers helpers) {
        int bodyOffset = helpers.analyzeResponse(res).getBodyOffset();
        String response = new String(res);
        String body = response.substring(bodyOffset);
        return body;
    }

    public static List<String> ParamAddPocGet(String params, String poc) throws UnsupportedEncodingException {
        poc = URLEncoder.encode(poc, "utf-8");
        ArrayList<String> param = new ArrayList<String>();
        if (params.contains("?")) {
            String paramsbefore = params.split("\\?")[0] + "?";
            params = params.split("\\?")[1];
            String ext = " HTTP/" + params.split("/")[1];
            params = params.split("/")[0].split(" HTTP")[0];
            String fstr = "";
            if (params.contains("&")) {
                String[] strs = params.split("&");
                for (int i = 0; i < strs.length; ++i) {
                    fstr = "";
                    String[] strs2 = params.split("&");
                    strs2[i] = strs2[i].split("=")[0] + "=" + poc;
                    for (int j = 0; j < strs.length; ++j) {
                        fstr = j == i ? fstr + strs2[j] + "&" : fstr + strs[j] + "&";
                    }
                    param.add(paramsbefore + fstr.substring(0, fstr.length() - 1) + ext);
                }
            } else {
                param.add(paramsbefore + params.split("=")[0] + "=" + poc + ext);
            }
        }
        return param;
    }

    public static List<String> ParamAddPocGetFJson(String params, String poc) throws UnsupportedEncodingException {
        poc = URLEncoder.encode(poc, "utf-8");
        ArrayList<String> param = new ArrayList<String>();
        if (params.contains("?")) {
            String paramsbefore = params.split("\\?", 2)[0] + "?";
            params = params.split("\\?", 2)[1];
            String httpVersion = " HTTP/" + params.split("/")[1];
            if ((params = params.split("/")[0].split(" HTTP")[0]).contains("&")) {
                String[] strs = params.split("&");
                for (int i = 0; i < strs.length; ++i) {
                    String[] strs2 = params.split("&");
                    if (!Common.isJSON(strs2[i].split("=", 2)[1]) && !Common.isJSON(URLDecoder.decode(strs2[i].split("=", 2)[1]))) continue;
                    String tmp = strs2[i].split("=", 2)[0] + "=" + poc;
                    param.add(params.replace(strs[i], tmp));
                }
            } else if (Common.isJSON(params.split("=", 2)[1]) || Common.isJSON(URLDecoder.decode(params.split("=", 2)[1]))) {
                param.add(paramsbefore + params.split("=", 2)[0] + "=" + poc + httpVersion);
            }
        }
        return param;
    }

    public static List<String> ParamAddPocGetNoreplace(String params, String poc) throws UnsupportedEncodingException {
        poc = URLEncoder.encode(poc, "utf-8");
        ArrayList<String> param = new ArrayList<String>();
        System.out.println(params);
        if (params.contains("?")) {
            String paramsbefore = params.split("\\?")[0] + "?";
            params = params.split("\\?")[1];
            String ext = " HTTP/" + params.split("/")[1];
            params = params.split("/")[0].split(" HTTP")[0];
            System.out.println(params);
            String fstr = "";
            if (params.contains("&")) {
                String[] strs = params.split("&");
                for (int i = 0; i < strs.length; ++i) {
                    fstr = "";
                    String[] strs2 = params.split("&");
                    strs2[i] = strs2[i] + poc;
                    for (int j = 0; j < strs.length; ++j) {
                        fstr = j == i ? fstr + strs2[j] + "&" : fstr + strs[j] + "&";
                    }
                    param.add(paramsbefore + fstr.substring(0, fstr.length() - 1) + ext);
                }
            } else {
                param.add(paramsbefore + params + poc + ext);
            }
        }
        return param;
    }

    public static List<String> ParamAddPocPostFJson(String params, String poc) {
        ArrayList<String> param = new ArrayList<String>();
        if (params.contains("&")) {
            String[] strs = params.split("&");
            for (int i = 0; i < strs.length; ++i) {
                String[] strs2 = params.split("&");
                if (!Common.isJSON(strs2[i].split("=", 2)[1]) && !Common.isJSON(URLDecoder.decode(strs2[i].split("=", 2)[1]))) continue;
                String tmp = strs2[i].split("=", 2)[0] + "=" + poc;
                param.add(params.replace(strs[i], tmp));
            }
        } else if (Common.isJSON(params.split("=", 2)[1]) || Common.isJSON(URLDecoder.decode(params.split("=", 2)[1]))) {
            param.add(params.split("=", 2)[0] + "=" + poc);
        }
        return param;
    }

    public static List<String> ParamAddPocPost(String params, String poc) {
        ArrayList<String> param = new ArrayList<String>();
        String fstr = "";
        if (params.contains("&")) {
            String[] strs = params.split("&");
            for (int i = 0; i < strs.length; ++i) {
                fstr = "";
                String[] strs2 = params.split("&");
                strs2[i] = strs2[i].split("=")[0] + "=" + poc;
                for (int j = 0; j < strs.length; ++j) {
                    fstr = j == i ? fstr + strs2[j] + "&" : fstr + strs[j] + "&";
                }
                param.add(fstr.substring(0, fstr.length() - 1));
            }
        } else {
            param.add(params.split("=")[0] + "=" + poc);
        }
        return param;
    }

    public static List<String> ParamAddPocPostNoreplace(String params, String poc) {
        ArrayList<String> param = new ArrayList<String>();
        String fstr = "";
        if (params.contains("&")) {
            String[] strs = params.split("&");
            for (int i = 0; i < strs.length; ++i) {
                fstr = "";
                String[] strs2 = params.split("&");
                strs2[i] = strs2[i] + poc;
                for (int j = 0; j < strs.length; ++j) {
                    fstr = j == i ? fstr + strs2[j] + "&" : fstr + strs[j] + "&";
                }
                param.add(fstr.substring(0, fstr.length() - 1));
            }
        } else {
            param.add(params + poc);
        }
        return param;
    }

    public static List<String> ParamAddPocPostJson(String json, String poc) {
        ArrayList<String> target = new ArrayList<String>();
        try {
            JSONObject jsonObject = JSONObject.parseObject(json);
            HashMap param = new HashMap();
            for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
                String object = jsonObject.getString(String.valueOf(entry.getKey()));
                Object origin = jsonObject.get(entry.getKey());
                jsonObject.put(entry.getKey(), (Object)poc);
                target.add(jsonObject.toString());
                jsonObject.put(entry.getKey(), origin);
            }
        } catch (Exception e) {
            BurpExtender.stdout.println(json + "json \u7c7b\u578b\u8f6c\u6362\u51fa\u73b0\u4e86\u95ee\u9898");
        }
        return target;
    }

    public static List<String> ParamAddPocPostJsonNoreplace(String json, String poc) {
        ArrayList<String> target = new ArrayList<String>();
        try {
            JSONObject jsonObject = JSONObject.parseObject(json);
            HashMap param = new HashMap();
            for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
                String object = jsonObject.getString(String.valueOf(entry.getKey()));
                Object origin = jsonObject.get(entry.getKey());
                jsonObject.put(entry.getKey(), (Object)(origin + poc));
                target.add(jsonObject.toString());
                jsonObject.put(entry.getKey(), origin);
            }
        } catch (Exception e) {
            BurpExtender.stdout.println(json + "json \u7c7b\u578b\u8f6c\u6362\u51fa\u73b0\u4e86\u95ee\u9898");
        }
        return target;
    }

    public static JSONObject readerMethod(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        InputStreamReader reader = new InputStreamReader((InputStream)new FileInputStream(file), "Utf-8");
        int ch = 0;
        StringBuffer sb = new StringBuffer();
        while ((ch = ((Reader)reader).read()) != -1) {
            sb.append((char)ch);
        }
        fileReader.close();
        ((Reader)reader).close();
        String jsonStr = sb.toString();
        return JSON.parseObject(jsonStr);
    }

    public static byte[] getpostParams(int start, byte[] srcbody) {
        byte[] reqbody = new byte[srcbody.length - start];
        System.arraycopy(srcbody, start, reqbody, 0, srcbody.length - start);
        return reqbody;
    }

    public static boolean SimpleJudgeJava1(List<String> headers) {
        for (String head : headers) {
            if (!head.contains("ASP.NET") && !head.contains("PHP/") && !head.contains("AspNet") && !head.contains("Microsoft-IIS") && !head.contains("ThinkPHP")) continue;
            return false;
        }
        return true;
    }

    public static boolean SimpleJudgeJava2(String head) {
        return !(head = head.toLowerCase(Locale.ROOT)).endsWith(".phtml") && !head.endsWith(".asp") && !head.endsWith(".php") && !head.endsWith(".ashx");
    }

    public static boolean SimpleJudgePhp(String head) {
        return (head = head.toLowerCase(Locale.ROOT)).endsWith(".phtml") || head.endsWith(".php");
    }

    public static boolean SimpleJudgePhp2(List<String> headers) {
        for (String head : headers) {
            if (!head.contains("PHP/") && !head.contains("ThinkPHP")) continue;
            return true;
        }
        return false;
    }

    public static boolean SimpleJudgeJava(String head) {
        return !(head = head.toLowerCase(Locale.ROOT)).contains(".phtml") && !head.contains(".php") && !head.contains(".asp") && !head.contains(".aspx") && !head.contains(".ashx");
    }

    public static boolean SimpleJudgeJava2(List<String> headers) {
        for (String head : headers) {
            if (!head.contains("ASP.NET") && !head.contains("PHP/") && !head.contains("AspNet") && !head.contains("Microsoft-IIS") && !head.contains("ThinkPHP")) continue;
            return false;
        }
        return true;
    }

    public static boolean IsHaveParams(IHttpRequestResponse base, IExtensionHelpers helpers) {
        List<String> headers = helpers.analyzeRequest(base.getRequest()).getHeaders();
        String reqMethod = helpers.analyzeRequest(base.getRequest()).getMethod();
        if (reqMethod.toLowerCase(Locale.ROOT).equals("get") && headers.get(0).contains("?")) {
            return true;
        }
        return reqMethod.toLowerCase(Locale.ROOT).equals("post") && (Common.getResbody(base.getRequest(), helpers).contains("=") || Common.getResbody(base.getRequest(), helpers).contains(":"));
    }
}

