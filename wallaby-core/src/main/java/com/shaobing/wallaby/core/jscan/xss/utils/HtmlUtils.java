package com.shaobing.wallaby.core.jscan.xss.utils;

/**
 * XSS防御一些代码,主要参考自springwebmvc
 * 转义字符串（Escape Sequence），即字符实体（Character Entity）分成三部分：
 *      第一部分是一个&符号，英文叫ampersand；
 *      第二部分是实体（Entity）名字或者是#加上实体（Entity）编号；
 *      第三部分是一个分号。
 * 比如，要显示小于号（<），就可以写 &lt; 或者 < 。
 * 用实体（Entity）名字的好处是比较好理解，一看lt，大概就猜出是less than的意思，
 * 但是其劣势在于并不是所有的浏览器都支持最新的Entity名字。而实体(Entity)编号，各种浏览器都能处理。
 * 提示：实体名称（Entity）是区分大小写的。
 * 备注：同一个符号，可以用“实体名称”和“实体编号”两种方式引用，“实体名称”的优势在于便于记忆，
 * 但不能保证所有的浏览器都能顺利识别它，而“实体编号”则没有这种担忧，但它实在不方便记忆。
 *
 *
 * @author luyb@servyou.com.cn
 * @version $Id: HtmlUtils.java v 0.1 2017/1/6 0:28 luyb Exp $$
 */
public class HtmlUtils {
}
