package com.shaobing.wallaby.core.http.j2ee.xss;

import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Test;




/**
 * @author luyb@servyou.com.cn
 * @version $Id: TestXss.java v 0.1 2017/1/7 19:01 luyb Exp $$
 */
public class TestXss {

    @Test
    public void testAlert() {
        String xssString = "<script>alert(1)</script>";
        System.out.println(StringEscapeUtils.escapeHtml4(xssString));

    }
}
