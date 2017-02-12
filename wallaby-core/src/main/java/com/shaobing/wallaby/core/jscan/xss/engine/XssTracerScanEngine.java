package com.shaobing.wallaby.core.jscan.xss.engine;

/**
 *
 * http trace是让我们的web服务器端将客户端的所有请求信息返回给客户端的方法，该方法多见于debug的需求
 *
 *
 * Cross-site tracing
 *
 *  XST攻击描述：
 *  攻击者将恶意代码嵌入一台已经被控制的主机上的web文件，当访问者浏览时恶意代码在浏览器中执行，
 *  然后访问者的cookie、http基本验证以及ntlm验证信息将被发送到已经被控制的主机，
 *  同时传送Trace请求给目标主机，导致cookie欺骗或者是中间人攻击。
 *  XST攻击条件：
 *  1、需要目标web服务器允许Trace参数；
 *  2、需要一个用来插入XST代码的地方；
 *  3、目标站点存在跨域漏洞。
 *  XST与XSS的比较:
 *  相同点：都具有很大的欺骗性，可以对受害主机产生危害，而且这种攻击是多平台多技术的，
 *  我们还可以利用Active控件、Flash、Java等来进行XST和XSS攻击。
 *  优点：可以绕过一般的http验证以及NTLM验证
 *
 * @author luyb@servyou.com.cn
 * @version $Id: XssTracerScanEngine.java v 0.1 2017/1/3 11:44 luyb Exp $$
 */
public class XssTracerScanEngine {
}
