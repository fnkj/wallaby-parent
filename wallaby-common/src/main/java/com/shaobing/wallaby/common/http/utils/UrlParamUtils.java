package com.shaobing.wallaby.common.http.utils;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 请求参数帮助
 *
 * @author luyb@servyou.com.cn
 * @version $Id: UrlParamUtils.java v 0.1 2017/2/12 20:57 luyb Exp $$
 */
public class UrlParamUtils {

    /**
     * 检查URL的参数并构建<t>List<NameValuePair> </t>
     *
     * @return List<NameValuePair>
     */
    public static String extractUrlParams(String url, List<NameValuePair> nvps) {
        if (url.contains("?")) {
            int i = url.indexOf("?");
            if (i != -1) {
                Map<String, String> params = buildParams(url.substring(i + 1));
                params2nvps(nvps, params);
            }
            url = url.substring(0, i);
        }

        return url;
    }


    public static void params2nvps(List<NameValuePair> nvps, Map<String, String> params) {
        for (Map.Entry<String, String> param : params.entrySet()) {
            nvps.add(new BasicNameValuePair(param.getKey(), param.getValue()));
        }
    }

    private static Map<String, String> buildParams(String urlParams) {
        Map<String, String> paramKV = new HashMap<>();
        String[] kvs = urlParams.split("&");
        for (String kv : kvs) {
            String[] entry = kv.split("=");
            paramKV.put(entry[0], entry[1]);
        }
        return paramKV;
    }
}
