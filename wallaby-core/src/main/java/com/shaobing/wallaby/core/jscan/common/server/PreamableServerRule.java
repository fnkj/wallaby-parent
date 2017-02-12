package com.shaobing.wallaby.core.jscan.common.server;

import com.shaobing.wallaby.common.http.utils.ResponseWrapper;
import com.shaobing.wallaby.common.http.utils.URL;
import com.shaobing.wallaby.core.jscan.common.PreambleInfo;
import com.shaobing.wallaby.core.jscan.common.WebServer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Make an HTTP request and observe the "Server" response header.
 *
 * @author luyb@servyou.com.cn
 * @version $Id: PreamableServerRule.java v 0.1 2017/1/8 19:28 luyb Exp $$
 */
public class PreamableServerRule implements ServerRule<ResponseWrapper, PreambleInfo> {

    /** web*/
    private final Map<WebServer, WebServerStrategy> webStrategy = new ConcurrentHashMap<WebServer, WebServerStrategy>() {
        {
            put(WebServer.TOMCAT, new TomcatServerStrategy());
            put(WebServer.JETTY, new JettyServerStrategy());
            put(WebServer.NGINX, new NginxServerStrategy());
        }
    };

    @Override
    public PreambleInfo detectServer(final URL url, final ResponseWrapper responseWrapper) {
        if (url == null)
            throw new IllegalArgumentException("参数url不能为空!");

        if (responseWrapper == null)
            throw new IllegalArgumentException("参数ResponseWrapper不能为空!");

        PreambleInfo preambleInfo = new PreambleInfo();
        for (Map.Entry<WebServer, WebServerStrategy> webStrategyEntry : webStrategy.entrySet()) {
            WebServer webServer = webStrategyEntry.getKey();
            WebServerStrategy serverStrategy = webStrategyEntry.getValue();
            if (serverStrategy.checkWebServer(url, responseWrapper))
                preambleInfo.setWebServer(webServer);
        }


        return preambleInfo;
    }



    interface WebServerStrategy {
        boolean checkWebServer(URL url, ResponseWrapper responseWrapper);
    }

    static class TomcatServerStrategy implements WebServerStrategy {

        /** tomcat server Banner */
        private static final Pattern TOMCAT_SERVER_PATTERN = Pattern
            .compile("Apache-Coyote/(\\d.\\d)");

        @Override
        public boolean checkWebServer(URL url, ResponseWrapper responseWrapper) {
            //TODO // FIXME: 2017/1/8
            final String server = responseWrapper.getHeaderValue("server");
            Matcher matcher = TOMCAT_SERVER_PATTERN.matcher(server);
            boolean serverFlag = matcher.find();
            return serverFlag;
        }
    }

    static class JettyServerStrategy implements WebServerStrategy {

        private static final Pattern JETTRY_SEVER_PATTERN = Pattern.compile("Jetty/([\\d\\.]+)",
            Pattern.DOTALL | Pattern.CASE_INSENSITIVE);

        @Override
        public boolean checkWebServer(URL url, ResponseWrapper responseWrapper) {
            // TODO: 2017/1/10
            final String server = responseWrapper.getHeaderValue("server");
            Matcher matcher = JETTRY_SEVER_PATTERN.matcher(server);
            boolean serverFlag = matcher.find();
            return false;
        }
    }

    static class NginxServerStrategy implements WebServerStrategy {

        @Override
        public boolean checkWebServer(URL url, ResponseWrapper responseWrapper) {
            return false;
        }
    }
}
