package com.shaobing.wallaby.core.jscan.apache;

import com.shaobing.wallaby.core.jscan.common.PreambleInfo;
import com.shaobing.wallaby.core.jscan.common.ScanIssue;
import com.shaobing.wallaby.core.jscan.common.exception.ScanTaskRuntimeException;
import com.shaobing.wallaby.core.jscan.common.task.ScanTaskAdapter;

import java.util.List;

/**
 * http://blog.csdn.net/bawcwchen/article/details/43681413
 *
 * 检测Struts 信息泄露
 *
 * (1)在任何一下请求地址后面加上debug=browser,
 * 如http://localhost:8080/eshop/userManage/userQueryAction?debug=browser，
 * 就可以以表格的形式看到该action的所有参数信息
 * 首先把struts2包中自带的struts2-config-browser-plugin-2.3.20.jar加到classpath路径下
 *
 * (2) debug=console
 *
 *
 *
 * @author luyb@servyou.com.cn
 * @version $Id: ApacheStrustsDevMode.java v 0.1 2016/12/27 23:52 luyb Exp $$
 */
public class ApacheStrustsDevMode extends ScanTaskAdapter {

    @Override
    public List<ScanIssue> execute(String url, PreambleInfo preambleInfo) throws ScanTaskRuntimeException {


        return null;
    }
}

/**
 *  Request URL:http://localhost:8080/s3/test/hello.action?debug=console
 Request Method:GET
 Status Code:200 OK
 Remote Address:[::1]:8080

 * <html>
     <head>
     <script type="text/javascript">
     var baseUrl = "/s3/struts";
     window.open(baseUrl+"/webconsole.html", 'OGNL Console','width=500,height=450,'+
     'status=no,toolbar=no,menubar=no');
     </script>
     </head>
     <body>
 *
 * */
