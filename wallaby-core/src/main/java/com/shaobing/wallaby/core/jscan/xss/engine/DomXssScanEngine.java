package com.shaobing.wallaby.core.jscan.xss.engine;

/**
 * http://www.2cto.com/article/201311/255078.html
 * http://netsecurity.51cto.com/art/201603/508122.htm
 * wuhanq
 * http://blog.chinaunix.net/uid-27070210-id-3255695.html
 *
 *  https://xsser.03c8.net/
 *  https://www.zhihu.com/question/26628342/answer/33504799
 *  https://code.google.com/archive/p/ra2-dom-xss-scanner/downloads
 *  https://code.google.com/archive/p/domxsswiki/wikis/FindingDOMXSS.wiki
 *  https://github.com/wisec/domxsswiki/wiki/cookie-sources
 *
 * DOM XSS典型场景分析
 * https://security.tencent.com/index.php/blog/msg/107
 *
 * 常用的防止XSS技术包括：
 * （1）与SQL注入防护的建议一样，假定所有输入都是可疑的，必须对所有输入中的script、iframe等字样进行严格的检查。这里的输入不仅仅是用户可以直接交互的输入接口，也包括HTTP请求中的Cookie中的变量，HTTP请求头部中的变量等。
 * （2）不仅要验证数据的类型，还要验证其格式、长度、范围和内容。
 * （3）不要仅仅在客户端做数据的验证与过滤，关键的过滤步骤在服务端进行。
 * （4）对输出的数据也要检查，数据库里的值有可能会在一个大网站的多处都有输出，即使在输入做了编码等操作，在各处的输出点时也要进行安全检查。
 * （5）在发布应用程序之前测试所有已知的威胁。
 *
 *
 *
 * @author luyb@servyou.com.cn
 * @version $Id: DomXssScanEngine.java v 0.1 2017/1/2 22:53 luyb Exp $$
 */
public class DomXssScanEngine {
}
