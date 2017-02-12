package com.shaobing.wallaby.common.http.utils;

/**
 * Http请求头常量
 *
 * @author luyb@servyou.com.cn
 * @version 2016-09-11 0:58
 */
public class HeaderConstants {

    /** 指定客户端能够接收的内容类型 */
    public static final String ACCEPT                           = "Accept";

    /** 浏览器可以接受的字符编码集 */
    public static final String ACCEPT_CHARSET                   = "Accept-Charset";

    /** 指定浏览器可以支持的web服务器返回内容压缩编码类型。 */
    public static final String ACCEPT_ENCODING                  = "Accept-Encoding";

    /** 浏览器可接受的语言 */
    public static final String ACCEPT_LANGUAGE                  = "Accept-Language";

    /** 可以请求网页实体的一个或者多个子范围字段 */
    public static final String ACCEPT_RANGES                    = "Accept-Ranges";

    /** 允许自定义的头部，以逗号隔开，大小写不敏感 */
    public static final String ACCESS_CONTROL_ALLOW_HEADERS     = "Access-Control-Allow-Headers";

    /** 允许跨域执行的方法 */
    public static final String ACCESS_CONTROL_ALLOW_METHODS     = "Access-Control-Allow-Methods";

    /** 允许跨域访问的域，可以是一个域的列表，也可以是通配符"*" */
    public static final String ACCESS_CONTROL_ALLOW_ORIGIN      = "Access-Control-Allow-Origin";

    /** 是否允许请求带有验证信息 */
    public static final String ACCESS_CONTROL_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";

    /** 允许脚本访问的返回头，请求成功后，脚本可以在XMLHttpRequest中访问这些头的信息 */
    public static final String ACCESS_CONTROL_EXPOSE_HEADERS    = "Access-Control-Expose-Headers";

    public static final String ACCESS_CONTROL_MAX_AGE           = "Access-Control-Max-Age";

    /** HTTP授权的授权证书 */
    public static final String AUTHORIZATION                    = "Authorization";

    /** 指定请求和响应遵循的缓存机制 */
    public static final String CACHE_CONTROL                    = "Cache-Control";

    /** 表示是否需要持久连接。（HTTP 1.1默认进行持久连接） */
    public static final String CONNECTION                       = "Connection";

    /** 请求发送时，会把保存在该请求域名下的所有cookie值一起发送给web服务器 */
    public static final String COOKIE                           = "Cookie";

    /** 请求的内容长度 */
    public static final String CONTENT_LENGTH                   = "Content-Length";

    /** 请求的与实体对应的MIME信息 */
    public static final String CONTENT_TYPE                     = "Content-Type";

    /** 请求发送的日期和时间 */
    public static final String DATE                             = "Date";

    /** 请求的特定的服务器行为 */
    public static final String EXPECT                           = "Expect";

    /** 发出请求的用户的Email */
    public static final String FROM                             = "From";

    /** 指定请求的服务器的域名和端口号 */
    public static final String HOST                             = "Host";

    /** 只请求实体的一部分，指定范围 */
    public static final String RANGE                            = "Range";

    /** 限制信息通过代理和网关传送的时间 */
    public static final String MAX_FORWARDS                     = "Max-Forwards";

    /** 用来包含实现特定的指令 */
    public static final String PRAGMA                           = "Pragma";
    /** 连接到代理的授权证书 */
    public static final String PROXY_AUTHORIZATION              = "Proxy-Authorization";

    /** 客户端愿意接受的传输编码，并通知服务器接受接受尾加头信息 */
    public static final String TE                               = "TE";

    /** 先前网页的地址，当前请求网页紧随其后,即来路 */
    public static final String REFERER                          = "Referer";

    /** User-Agent的内容包含发出请求的用户信息 */
    public static final String USER_AGENT                       = "User-Agent";

    /** 通知中间网关或代理服务器地址，通信协议 */
    public static final String VIA                              = "Via";

    /** 关于消息实体的警告信息 */
    public static final String WARING                           = "Waring";

    /** XSS保护 */
    public static final String X_XSS_PROTECTION                 = "X-XSS-Protection";

    /** 用来给浏览器 指示 允许一个页面 可否 在 frame , iframe 或者 object 中展现的标记 */
    public static final String X_FRAME_OPTIONS                  = "X-Frame-Options";

    /** 默认userAgent */
    public static final String DEFAULT_USER_AGENT               = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36";

    /** 默认可接受语言 */
    public static final String DEFAULT_ACCEPT                   = "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8";

    /** 默认缓存控制 */
    public static final String DEFAULT_CACHE_CONTROL            = "no-cache";

}
