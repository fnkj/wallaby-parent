/**
 *
 *
 *
 * (1)Http头中的Referer字段
 * (2)User-Agent字段
 * (3)Cookie
 * (4)表单（包括隐藏表单）
 * (5)URL参数
 * (6)URL末尾，如 www.example.com/<script>alert(1)</script>
 * (7)跳转型XSS
 * (8)针对AJAX动态生成的页面(可以通过Phantomjs等无头浏览器进行渲染)
 *
 * XSS学习资料汇总:
 * (1)
 *
 * (2) 练习平台
 *      https://pentesterlab.com/exercises/web_for_pentester
 *      web for pentester是国外安全研究者开发的的一款渗透测试平台，通过该平台你可以了解到常见的Web漏洞检测技术。
 *      XSS跨站脚本攻击 SQL注入 目录遍历 命令注入 代码注入 XML攻击 LDAP攻击  文件上传
 *      修改密码: sudo passwd root
 *
 *
 * (3)开源代码
 *   xsscrapy： https://github.com/DanMcInerney/xsscrapy/tree/master/xsscrapy  https://my.oschina.net/Listening/blog/796932
 *   XSS漏洞扫描器 – Xelenium
 *   https://code.google.com/archive/p/ra2-dom-xss-scanner/downloads
 *   https://github.com/yaph/domxssscanner
 *
 *
 * (4) XSS文章
 *   http://itindex.net/detail/50997-xss-%E5%8E%9F%E7%90%86-%E5%88%86%E6%9E%90
 *   http://www.freebuf.com/articles/web/40520.html
 *
 *   http://itindex.net/detail/53841-xss-big-brother
 *
 *  https://excess-xss.com/
 *
 * @author luyb@servyou.com.cn
 * @version 2017-01-02 18:32
 */
package com.shaobing.wallaby.core.jscan.xss;