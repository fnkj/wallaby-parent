package com.shaobing.wallaby.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 下划线与驼峰转换
 *
 * @author luyb@servyou.com.cn
 * @version $Id: UnderlinCamel.java v 0.1 2017/1/23 17:14 luyb Exp $$
 */
public class UnderlinCamel {

    /** 下划线 */
    public static final Pattern UNDERLINE_PATTERN = Pattern.compile("([A-Za-z\\d]+)(_)?");

    /** 驼峰 */
    public static final Pattern CAMEL_PATTERN     = Pattern.compile("[A-Z]([a-z\\d]+)?");

    /**
     * 下划线转驼峰法
     * 
     * @param line 源字符串
     * @param smallCamel 大小驼峰,是否为小驼峰
     * @return 转换后的字符串
     */
    public static String underline2Camel(String line, boolean smallCamel) {
        if (line == null || "".equals(line)) {
            return "";
        }

        StringBuffer sb = new StringBuffer();
        Matcher matcher = UNDERLINE_PATTERN.matcher(line);
        while (matcher.find()) {
            String word = matcher.group();
            sb.append(smallCamel && matcher.start() == 0 ? Character.toLowerCase(word.charAt(0))
                : Character.toUpperCase(word.charAt(0)));
            int index = word.lastIndexOf('_');
            if (index > 0) {
                sb.append(word.substring(1, index).toLowerCase());
            } else {
                sb.append(word.substring(1).toLowerCase());
            }
        }
        return sb.toString();
    }

    /**
     * 驼峰法转下划线
     *
     * @param line 源字符串
     * @return 转换后的字符串
     */
    public static String camel2Underline(String line) {
        if (line == null || "".equals(line)) {
            return "";
        }

        line = String.valueOf(line.charAt(0)).toUpperCase().concat(line.substring(1));
        StringBuffer sb = new StringBuffer();
        Matcher matcher = CAMEL_PATTERN.matcher(line);
        while (matcher.find()) {
            String word = matcher.group();
            sb.append(word.toUpperCase());
            sb.append(matcher.end() == line.length() ? "" : "_");
        }
        return sb.toString();
    }

}
