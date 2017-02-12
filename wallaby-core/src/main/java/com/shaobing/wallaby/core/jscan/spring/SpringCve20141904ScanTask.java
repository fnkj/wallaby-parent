package com.shaobing.wallaby.core.jscan.spring;

import com.shaobing.wallaby.core.jscan.common.PreambleInfo;
import com.shaobing.wallaby.core.jscan.common.ScanIssue;
import com.shaobing.wallaby.core.jscan.common.exception.ScanTaskRuntimeException;
import com.shaobing.wallaby.core.jscan.common.task.ScanTaskAdapter;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * SpringMVC XSS 漏洞
 * <p>
 * Versions Affected：
 * Spring MVC 3.0.0 to 3.2.7
 * Spring MVC 4.0.0 to 4.0.1
 * Earlier unsupported versions may be affected
 * </p>
 *
 * When a programmer does not specify the action on the Spring form,
 * Spring automatically populates the action field with the requested uri.
 * An attacker can use this to inject malicious content into the form.
 * 当用户未在Spring form中指定 action地址
 * Spring自动填充Action属性值为 requested uri
 * 攻击者可以通过该方法往表单中注入恶意内容
 *
 *  Spring Framework 3.2.7及之前版本和4.0.1及之前版本的
 *  Spring MVC中的web/servlet/tags/form/FormTag.java文件中存在跨站脚本漏洞。
 *  远程攻击者可利用该漏洞注入任意Web脚本或HTML。
 *
 *  Spring Framework (Spring MVC) is prone to a cross-site scripting vulnerability because it fails to sufficiently
 *  sanitize user-supplied input.
 *
 *  An attacker may leverage this issue to execute arbitrary script code in the browser
 *  of an unsuspecting user in the context of the affected site.
 *  This may allow the attacker to steal cookie-based authentication credentials
 *  and launch other attacks.
 *
 *
 *  <p>
 *      URL编码全解: http://www.ruanyifeng.com/blog/2010/02/url_encoding.html
 *  </p>
 *
 *  http://shh.thathost.com/secadv/spring-form-xss/index.txt
 *
 *  http://www.acunetix.com/blog/articles/xss-http-headers/
 *
 *
 * @author luyb@servyou.com.cn
 * @version $Id: SpringCve20141904ScanTask.java v 0.1 2017/1/5 18:13 luyb Exp $$
 */
public class SpringCve20141904ScanTask extends ScanTaskAdapter {

    /** CVE编号 */
    public static final String CVE_2014_1904 = "CVE-2014-1904";

    /** 漏洞描述 */
    public static final String DESCRIPTION   = "When a programmer does not specify the action on the Spring form, Spring automatically populates the action field with the requested uri. An attacker can use this to inject malicious content into the form.";

    @Override
    public List<ScanIssue> execute(String url,
                                   PreambleInfo preambleInfo) throws ScanTaskRuntimeException {

        return super.execute(url, preambleInfo);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String actionUrl = "/mvc/login/login.jhtml?%3Cscript%3Ealert(1)&amp;%3C%2Fscript%3E";
        System.out.println(URLDecoder.decode(actionUrl, "UTF-8"));
    }
}
