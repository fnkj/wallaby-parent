package com.shaobing.wallaby.core.jscan.apache;

import com.shaobing.wallaby.core.jscan.common.task.ScanTaskAdapter;

/**
 *
 * When using Struts 2 token mechanism for CSRF protection,
 * token check may be bypassed by misusing known session attributes
 *
 * http://struts.apache.org/docs/s2-010.html
 * Affected Software Struts 2.0.0 - Struts 2.3.4
 * 
 * @author luyb@servyou.com.cn
 * @version $Id: ApacheS2010ScanTask.java v 0.1 2017/1/1 3:59 luyb Exp $$
 */
public class ApacheS2010ScanTask extends ScanTaskAdapter {

    /** CVE-2012-4386 */
    public static final String CVE_2012_4386 = "CVE-2012-4386";

    /** 解决方案 */
    public static final String SOLUTION      = "Developers should upgrade to Struts 2.3.4.1";

}
