package com.shaobing.wallaby.common.http.httpclient;

import com.shaobing.wallaby.common.http.exception.WallabyHttpException;
import com.shaobing.wallaby.common.logger.LoggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 原生http请求方法
 *
 * @author luyb@servyou.com.cn
 * @version $Id: HttpUtils.java v 0.1 2016/12/25 16:33 luyb Exp $$
 */
public class HttpUtils {

    private static final Logger logger         = LoggerFactory.getLogger(HttpUtils.class);

    /** 行分隔符 */
    public static final String  LINE_SEPARATOR = System.getProperty("line.separator");

    public static String get(String urlStr) {
        return get(urlStr, null, "UTF-8");
    }

    public static String get(String urlStr, HashMap<String, String> params, String charset) {
        BufferedReader reader = null;
        try {
            String queryStr = "";
            if (params != null) {
                for (Map.Entry<String, String> param : params.entrySet()) {
                    queryStr = queryStr.concat(
                        "&" + param.getKey() + "=" + URLEncoder.encode(param.getValue(), charset));
                }
                urlStr = urlStr.concat(queryStr);
            }
            URL url = new URL(urlStr);

            LoggerUtils.info(logger, "请求的Url地址为{0}", urlStr);

            URLConnection urlConn = url.openConnection();
            reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), charset));

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append(LINE_SEPARATOR);
            }

            return sb.toString().trim();
        } catch (Exception ex) {
            System.out.println("doGet异常:" + ex);
            throw new WallabyHttpException("发生异常" + ex.getMessage(), ex);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }
    }

}
