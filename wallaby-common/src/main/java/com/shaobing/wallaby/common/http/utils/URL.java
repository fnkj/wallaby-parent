package com.shaobing.wallaby.common.http.utils;

import com.shaobing.wallaby.common.annotation.ThreadSafe;

import java.io.Serializable;
import java.util.Map;

/**
 * 自定义URL - Uniform Resource Locator (Immutable, ThreadSafe)
 *
 *
 * @see java.net.URL
 * @see java.net.URI
 * @author luyb@servyou.com.cn
 * @version $Id: URL.java v 0.1 2017/1/8 18:16 luyb Exp $$
 */
@ThreadSafe
public class URL implements Serializable, Cloneable {

    private static final long         serialVersionUID = -3070946069137396178L;

    /** The protocol to use (ftp, http, nntp, ... etc.) */
    private final String              protocol;

    /** 主机地址 */
    private final String              host;

    /** 端口号 */
    private final int                 port;

    /** 请求路径 */
    private final String              path;

    /** 请求参数 */
    private final Map<String, Object> parameters;

    /**
     * 构造函数
     *
     * @param protocol 协议
     * @param host  主机名
     * @param port  端口号
     * @param path  路径
     * @param parameters    请求参数
     */
    public URL(String protocol, String host, int port, String path,
               Map<String, Object> parameters) {
        this.protocol = protocol;
        this.host = host;
        this.port = port;
        this.path = path;
        this.parameters = parameters;
    }

    /**
     * Getter for property 'protocol'.
     *
     * @return protocol Value for property 'protocol'.
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * Getter for property 'host'.
     *
     * @return host Value for property 'host'.
     */
    public String getHost() {
        return host;
    }

    /**
     * Getter for property 'port'.
     *
     * @return port Value for property 'port'.
     */
    public int getPort() {
        return port;
    }

    /**
     * Getter for property 'path'.
     *
     * @return path Value for property 'path'.
     */
    public String getPath() {
        return path;
    }

    /**
     * Getter for property 'parameters'.
     *
     * @return parameters Value for property 'parameters'.
     */
    public Map<String, Object> getParameters() {
        return parameters;
    }

    /**
     * Clone URL
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
