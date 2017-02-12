package com.shaobing.wallaby.core.jscan.common;

import org.apache.commons.lang3.StringUtils;

/**
 *   JAVA WebServer类型
 *  参考地址:http://www.open-open.com/23.htm
 *
 * <ul>
 *     <li>
 *         glassfish是Sun公司推出的Java EE服务器(Java EE容器)，一个比较活跃的开源社区，不断的通过社区的反馈来提高其的可用性，经过glassfish v1 glassfish v2 到今天的glassfish v3 ,它已经走向成熟。Glassfish是一个免费、开放源代码的应用服务，它实现了Java EE 5,Java EE 5 平台包括了以下最新技术：EJB 3.0、JSF 1.2、Servlet 2.5、JSP 2.1、JAX-WS 2.0、JAXB 2.0、 Java Persistence 1.0、Common Annonations 1.0、StAX 1.0等。
 *     </li>
 *     <li>
 *         目前最为流行的Tomcat服务器是Apache-Jarkarta开源项目中的一个子项目，是一个小型、轻量级的支持JSP和Servlet 技术的Web服务器，也是初学者学习开发JSP应用的首选。
 *     </li>
 *     <li>
 *         Jetty是一个开放源码的HTTP服务器和Java serverlet容器。
 *     </li>
 *     <li>
 *         Resin是Caucho公司的产品，是一个非常流行的支持Servlet和JSP的服务器，速度非常快。Resin本身包含了一个支持HTML的Web服务器，这使它不仅可以显示动态内容，而且显示静态内容的能力也毫不逊色，因此许多网站都是使用Resin服务器构建。
 *     </li>
 *     <li>
 *         WebSphere是IBM公司的产品，可进一步细分为 WebSphere Performance Pack、Cache Manager 和WebSphere Application Server等系列，其中WebSphere Application Server 是基于Java 的应用环境，可以运行于 Sun Solaris、Windows NT 等多种操作系统平台，用于建立、部署和管理Internet和Intranet Web应用程序
 *     </li>
 *     <li>
 *         WebLogic 是BEA公司的产品，可进一步细分为 WebLogic Server、WebLogic Enterprise 和 WebLogic Portal 等系列，其中 WebLogic Server 的功能特别强大。WebLogic 支持企业级的、多层次的和完全分布式的Web应用，并且服务器的配置简单、界面友好。对于那些正在寻求能够提供Java平台所拥有的一切应用服务器的用户来说，WebLogic是一个十分理想的选择
 *     </li>
 * </ul>
 *
 * @author luyb@servyou.com.cn
 * @version $Id: WebServer.java v 0.1 2017/1/8 17:34 luyb Exp $$
 */
public enum WebServer {
                       GLASSFISH("glassfish"), JBOSS("jboss"), JETTY("jetty"), TOMCAT("tomcat"), NGINX("nginx"), RESIN("resin"), WEBLOGIC("weblogic"), WEBSPHERE("websphere");

    /** 服务器名字*/
    private final String name;

    WebServer(String name) {
        this.name = name;
    }

    /**
     * 根据名字返回服务器类型
     *
     * @param name 服务器名字
     * @return WebServer
     */
    public WebServer getByName(String name) {
        if (StringUtils.isEmpty(name))
            return null;

        for (WebServer webServer : values()) {
            if (webServer.getName().equals(name))
                return webServer;
        }

        return null;
    }

    /**
     * Getter for property 'name'.
     *
     * @return name Value for property 'name'.
     */
    public String getName() {
        return name;
    }
}
