package com.shaobing.wallaby.core.http.lang;

import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Test;

/**
 * @author luyb@servyou.com.cn
 * @version $Id: TestString.java v 0.1 2017/1/1 17:31 luyb Exp $$
 */
public class TestString {

    @Test
    public void escapeHtml4() {
        String input = "pez1420@163.com []{}'' <script></script>";
        String s = StringEscapeUtils.escapeHtml4(input);
        System.out.println(s);
    }

    @Test
    public void escapeEcmaScript() {
        String input = "pez1420@163.com []{}'' <script></script>";
        String s = StringEscapeUtils.escapeEcmaScript(StringEscapeUtils.escapeHtml4(input));
        System.out.println(s);
    }
}
