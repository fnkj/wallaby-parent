package com.shaobingmm.wallaby.crawler.hlinks;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author luyb@servyou.com.cn
 * @version $Id: HStyle.java v 0.1 2017/2/3 11:26 luyb Exp $$
 */
public class HStyle {

    /** 负数正则表达式 */
    private static final Pattern NEGATIVE_PATTERN   = Pattern.compile("\\-([0-9]+)",
        Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);

    /** 属性值正则表达式 */
    private static final Pattern ATTR_VALUE_PATTERN = Pattern.compile("(\\-)?([0-9]+)",
        Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);

    /** 空白正则表达式 */
    private static final Pattern BLANk_PATTERN      = Pattern.compile("\\s+",
        Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);

    /** Html元素样式 */
    private String               style;

    public HStyle(String style) {
        this.style = style;
    }

    private String removeAllBlank(String style) {
        Matcher matcher = BLANk_PATTERN.matcher(style);
        return null;
    }

}
