package com.shaobing.wallaby.common.http.utils;

import com.shaobing.wallaby.common.annotation.CNotNull;
import com.shaobing.wallaby.common.http.exception.HttpHeaderException;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Http请求头构造
 *
 * @author luyb@servyou.com.cn
 * @version 2016-09-11 1:31
 */
public class HttpHeaderConfig {
    /** 日志记录器 */
    private static final Logger  logger               = LoggerFactory
        .getLogger(HttpHeaderConfig.class);

    /** 默认的页面请求头*/
    public static final Header[] DEFAULT_HTTP_HEADERS = HttpHeaderConfig.create()
        .cacheControl(HeaderConstants.DEFAULT_CACHE_CONTROL).accept(HeaderConstants.DEFAULT_ACCEPT)
        .userAgent(HeaderConstants.DEFAULT_USER_AGENT).build();

    /** 请求头 */
    private Map<String, String>  headerMap            = new HashMap<String, String>();

    /** 自定义请求头 */
    private CustomHttpHeader     extraHttpHeader;

    private HttpHeaderConfig() {
    }

    /**
     * Creates HttpHeaderConfig object for construction of custom
     */
    public static HttpHeaderConfig create() {
        return new HttpHeaderConfig();
    }

    public HttpHeaderConfig accept(String accept) {
        headerMap.put(HeaderConstants.ACCEPT, accept);
        return this;
    }

    public HttpHeaderConfig acceptCharset(String acceptCharset) {
        headerMap.put(HeaderConstants.ACCEPT_CHARSET, acceptCharset);
        return this;
    }

    public HttpHeaderConfig acceptEncoding(String acceptEncoding) {
        headerMap.put(HeaderConstants.ACCEPT_ENCODING, acceptEncoding);
        return this;
    }

    public HttpHeaderConfig acceptLanguage(String acceptLanguage) {
        headerMap.put(HeaderConstants.ACCEPT_LANGUAGE, acceptLanguage);
        return this;
    }

    public HttpHeaderConfig authorization(String authorization) {
        headerMap.put(HeaderConstants.AUTHORIZATION, authorization);
        return this;
    }

    public HttpHeaderConfig cacheControl(String cacheControl) {
        headerMap.put(HeaderConstants.CACHE_CONTROL, cacheControl);
        return this;
    }

    public HttpHeaderConfig connection(String connection) {
        headerMap.put(HeaderConstants.CONNECTION, connection);
        return this;
    }

    public HttpHeaderConfig cookie(String cookie) {
        headerMap.put(HeaderConstants.COOKIE, cookie);
        return this;
    }

    public HttpHeaderConfig contentLength(String contentLength) {
        headerMap.put(HeaderConstants.CONTENT_LENGTH, contentLength);
        return this;
    }

    public HttpHeaderConfig contentType(String contentType) {
        headerMap.put(HeaderConstants.CONTENT_TYPE, contentType);
        return this;
    }

    public HttpHeaderConfig date(String date) {
        headerMap.put(HeaderConstants.DATE, date);
        return this;
    }

    public HttpHeaderConfig host(String host) {
        headerMap.put(HeaderConstants.HOST, host);
        return this;
    }

    public HttpHeaderConfig referer(String referer) {
        headerMap.put(HeaderConstants.REFERER, referer);
        return this;
    }

    public HttpHeaderConfig pragma(String pragma) {
        headerMap.put(HeaderConstants.PRAGMA, pragma);
        return this;
    }

    public HttpHeaderConfig userAgent(String userAgent) {
        headerMap.put(HeaderConstants.USER_AGENT, userAgent);
        return this;
    }

    public HttpHeaderConfig customHeader(CustomHttpHeader extraHttpHeader) {
        if (extraHttpHeader == null)
            return this;
        this.extraHttpHeader = extraHttpHeader;
        return this;
    }

    public Header[] build() throws HttpHeaderException {
        List<Header> headers = new ArrayList<Header>();

        if (!headerMap.isEmpty()) {
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                headers.add(new BasicHeader(entry.getKey(), entry.getValue()));
            }
        }

        // 额外请求头
        if (extraHttpHeader != null) {
            extraHttpHeader.checkField();
            Field[] fields = extraHttpHeader.getClass().getDeclaredFields();
            if (fields != null) {
                for (Field field : fields) {
                    if (field.isAnnotationPresent(CNotNull.class)) {
                        String value = null;
                        try {
                            if (field.getType().equals(String.class)) {
                                Method m = (Method) extraHttpHeader.getClass()
                                    .getMethod("get" + upperCaseFieldName(field.getName()));
                                value = (String) m.invoke(extraHttpHeader);
                            } else {
                                throw new UnsupportedOperationException();
                            }
                        } catch (IllegalAccessException e) {

                        } catch (InvocationTargetException e) {
                            //ignore
                        } catch (NoSuchMethodException e) {
                            //ignore
                        }

                        if (value == null)
                            throw new HttpHeaderException(
                                field.getClass().getName() + "." + field.getName()
                                                          + " is not allowed to be null!");
                        headers.add(new BasicHeader(field.getName(), value));
                    }
                }
            }
        }

        headerMap.clear();
        headerMap = null;

        return headers.toArray(new Header[0]);
    }

    /**
     * 首字母大写
     *
     * @param fieldName
     * @return
     */
    private static String upperCaseFieldName(String fieldName) {
        byte[] items = fieldName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (headerMap != null) {
            for (Map.Entry<String, String> header : headerMap.entrySet()) {
                builder.append(header.getKey()).append(" : ").append(header.getValue())
                    .append(System.getProperty("line.separator"));
            }
        }

        if (extraHttpHeader != null) {
            builder.append(extraHttpHeader.toString());
        }

        return builder.toString();
    }

    public static void main(String[] args) throws HttpHeaderException {
        WallabyHttpHeader wallabyHttpHeader = new WallabyHttpHeader();
        wallabyHttpHeader.setToken(UUID.randomUUID().toString());

        HttpHeaderConfig headerConfig = HttpHeaderConfig.create();
        Header[] headers = headerConfig.customHeader(wallabyHttpHeader)
            .accept("image/webp,image/*,*/*;q=0.8").acceptEncoding("gzip, deflate, sdch, br")
            .acceptLanguage("zh-CN,zh;q=0.8").cacheControl("no-cache").pragma("no-cache")
            .referer("https://www.google.com.hk/")
            .userAgent(
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36")
            .build();

        System.out.println(headerConfig.toString());
    }

}
