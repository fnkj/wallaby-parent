package com.shaobing.wallaby.core.jscan.xss.engine;

/**
 * 跨框架脚本 <tt>Cross-Frame Scripting</tt>
 *
 *  <p>
 *     (1)利用浏览器允许框架(frame)跨站包含其它页面的漏洞，
 *      在主框架的代码中加入scirpt，监视、盗取用户输入
 *     (2)一个恶意的站点可以通过用框架包含真的网银或在线支付网站，获取用户账号和密码。
 *     (3) X-Frame-Options响应头
 *      <ul>
 *          X-Frame-Options 有三个可选的值：
 *      <li>DENY：浏览器拒绝当前页面加载任何Frame页面</li>
 *     <li>SAMEORIGIN：frame页面的地址只能为同源域名下的页面</li>
 *     <li>ALLOW-FROM：允许frame加载的页面地址</li>
 *
 *     java代码:
 *          response.addHeader("x-frame-options","SAMEORIGIN");
 *     Nginx配置:
 *          add_header X-Frame-Options SAMEORIGIN
 *     Apache配置:
 *          Header always append X-Frame-Options SAMEORIGIN
 *      </ul>
 *
 *  </p>
 *
 *
 * @author luyb@servyou.com.cn
 * @version $Id: CFSScanEngine.java v 0.1 2017/1/3 11:47 luyb Exp $$
 */
public class CFSScanEngine implements XssScanEngine{

}
