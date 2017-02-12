package com.shaobing.wallaby.common.http.utils;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 猜测页面编码
 *
 * @author luyb@servyou.com.cn
 * @version 2016-09-22 23:12
 */
public class GuessCharset {
    /** 正则表达式 */
    private static final String CHARSET_REGEX   = ".*charset=([^;]*).*";
    /** 正则表达式Pattern */
    private static Pattern      CHARSET_PATTERN = Pattern.compile(CHARSET_REGEX);


    /**
     * 根据页面body获取字符编码,默认编码为UTF-8
     *
     * @param html
     * @return
     */
    public static String getCharSetByBody(String html) {
        return getCharSetByBody(html, "UTF-8");
    }

    /**
     * 根据页面body获取字符编码
     *
     * @param html
     * @param charset
     * @return
     */
    public static String getCharSetByBody(String html, String charset) {
        Document document = Jsoup.parse(html);
        Elements elements = document.select("meta");
        for (Element metaElement : elements) {
            if (metaElement != null && StringUtils.isNotBlank(metaElement.attr("http-equiv"))
                && metaElement.attr("http-equiv").toLowerCase().equals("content-type")) {
                String content = metaElement.attr("content");
                charset = getCharSet(content);
                break;
            }
        }
        return charset;
    }

    /**
     * 正则获取页面chasset编码
     *
     * @param content
     * @return
     */
    private static String getCharSet(String content) {
        Matcher matcher = CHARSET_PATTERN.matcher(content);
        if (matcher.find())
            return matcher.group(1);
        else
            return null;
    }

    public static void main(String[] args) {

    }

}
