package com.shaobing.wallaby.common.http.httpclient;

import com.shaobing.wallaby.common.http.utils.HttpMethods;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * http报文辅助类
 *  <p>
 *      HttpEntity其实相当于一个消息实体，内容是http传送的报文（这里可以说是html，css等等文件）。
 *      这里只需要知道它是用来表征一个http报文的实体就行了，用来发送或接收
 *  </p>
 *
 *  <p>
 *  按HttpEntity内容的来源，它可以分为三类：
 *  1.streamed（流式）：从一个流传输中获得，一般是来自http连接。特点：使用时产生，不可重复
 *  2.self-contained(自包含)：存储在内存中的，独立于http连接。特点：可以重复
 *  3.wrapping(代理，包装)：从其他HttpEntity中获得。特点：依附于获取的类
 *      HttpEntity类非常简洁，它的方法如下：
 *      InputStreamgetContent()返回的是一个实体内容的流
 *      注：此时这里的流与上面所说的流式实体概念有所不同。上文所说流式实体是指从连接产生具有实时性的流，而这里仅仅只内容流形式，不要混淆。
 *      HeadergetContentEncoding()获取HttpEntity（如果有的话）实体已编码内容的头部信息（具体Header内容可以参见HttpCore帮助文档中Header类）
 *      Long getContentLength()显而易见是获取整个实体内容的长度
 *      BooleanisRepeatable（）判断实体是否具有重现的能力，也就是一中讨论的分类
 *      BooleanisStreaming（）判断实体的内容是不是基于流
 *      void writeTo(OutputStream outstream)将实体写入一个输出流
 *
 *  </p>
 * @author luyb@servyou.com.cn
 * @version $Id: HttpEntityUtil.java v 0.1 2017/1/1 2:04 luyb Exp $$
 */
public class HttpEntityUtil {

    /**
     * 构建请求参数
     *
     * @param url
     * @param params
     * @return
     */
    public static List<NameValuePair> constructParameter(String url, HttpMethods httpMethods,
                                                         Map<String, String> params) {

        List<NameValuePair> formParams = new ArrayList<NameValuePair>();
        int queryPos = url.indexOf("?");
        if (queryPos != -1) {
            String queryStr = url.substring(queryPos + 1, url.length());
            String[] pairStrs = queryStr.split("&");
            for (String pairStr : pairStrs) {
                String[] pair = pairStr.split("=");
                formParams.add(new BasicNameValuePair(pair[0], pair[1]));
            }
        }

        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> param : params.entrySet()) {
                formParams.add(new BasicNameValuePair(param.getKey(), param.getValue()));
            }
        }

        return formParams;

    }
}
