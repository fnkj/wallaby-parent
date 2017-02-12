package com.shaobing.wallaby.common.utils;

import com.shaobing.wallaby.common.logger.LoggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 网络工具类
 * 
 * @author luyb@servyou.com.cn
 * @version $Id: NetUtils.java v 0.1 2017/1/8 18:36 luyb Exp $$
 */
public class NetUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(NetUtils.class);

    /**
     * 根据主机名返回IP地址
     *
     * InetAddress的getHostAddress的实例方法可以获取该网络地址的IP信息
     *
     * @param hostName  主机名
     * @return ip地址
     */
    public static String getIpByHostName(String hostName) {
        if (hostName == null)
            throw new IllegalArgumentException("主机名hostName不能为空");

        try {
            return InetAddress.getByName(hostName).getHostAddress().toString();
        } catch (UnknownHostException e) {
            LoggerUtils.error(LOGGER, e, "{0}获取IP地址失败", hostName);
        }
        return null;
    }

    /**
     * 根据主机名返回所有IP地址
     *
     * @param hostName  主机名
     * @return String[]
     */
    public static String[] getAllIPByHostName(String hostName) {
        if (hostName == null)
            throw new IllegalArgumentException("主机名hostName不能为空");

        try {
            InetAddress[] addressess = InetAddress.getAllByName(hostName);
            String[] ips = new String[addressess.length];
            for (int i = 0; i < ips.length; i++) {
                ips[i] = addressess[i].getHostAddress();
            }
            return ips;
        } catch (UnknownHostException e) {
            LoggerUtils.error(LOGGER, e, "{0}获取IP地址失败", hostName);
        }
        return null;
    }

    /**
     * 转换成URL地址
     *
     * @param protocol  协议
     * @param host      主机名
     * @param port      端口号
     * @param path      路径
     * @return String
     */
    public static String toURL(String protocol, String host, int port, String path) {
        StringBuilder sb = new StringBuilder();
        sb.append(protocol).append("://");
        sb.append(host).append(':').append(port);
        if (path.charAt(0) != 47) {
            sb.append('/');
        }

        sb.append(path);
        return sb.toString();
    }

}
