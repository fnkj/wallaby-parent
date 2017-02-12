package com.shaobing.wallaby.core.jscan.tomcat;

import com.shaobing.wallaby.common.http.httpclient.AsyncHandlerAdapter;
import com.shaobing.wallaby.common.http.httpclient.HttpAsyncClientUtil;
import com.shaobing.wallaby.common.http.utils.HttpConfigContext;
import com.shaobing.wallaby.common.http.utils.HttpHeaderConfig;
import com.shaobing.wallaby.common.http.utils.UserAgents;
import com.shaobing.wallaby.common.logger.LoggerUtils;
import com.shaobing.wallaby.core.utils.codec.Base64;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * 如口令异步回调函数
 *
 * @author luyb@servyou.com.cn
 * @version $$: WeakPasswordAsyncHandler.java v 0.1 2016/11/27 23:19 luyb Exp $$
 */
public class WeakPasswordAsyncHandler extends AsyncHandlerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeakPasswordAsyncHandler.class);

    @Override
    public void completed(String url, HttpResponse response) {
        final StatusLine statusLine = response.getStatusLine();
        LoggerUtils.debug(LOGGER, "url={0},状态码={1}", url,
            String.valueOf(statusLine.getStatusCode()));

        if (statusLine.getStatusCode() == 401) {
            final List<Map.Entry<String, String>> credentials = WeakPassword.credentials;
            for (Map.Entry<String, String> credential : credentials) {
                String auth = "Basic " + Base64
                    .encodeToString((credential.getKey() + ":" + credential.getValue()).getBytes());
                final String username = credential.getKey();
                final String password = credential.getValue();
                LoggerUtils.debug(LOGGER, "authorization={0}, username={1},password={2}", auth,
                    username, password);

                Header[] headers = HttpHeaderConfig.create().userAgent(UserAgents.CHROME_USERAGENTS)
                    .connection("keep-alive").pragma("no-cache").authorization(auth).build();

                HttpConfigContext httpConfigContext = HttpConfigContext.create().url(url)
                    .headers(headers).asyncHandler(new AsyncHandlerAdapter() {
                        @Override
                        public void completed(String url, HttpResponse response) {
                            StatusLine statusLine = response.getStatusLine();
                            if (statusLine.getStatusCode() == 200) {
                                LoggerUtils.debug(LOGGER,
                                    "[!] Weak Credentils " + username + ":" + password);
                            }
                        }
                    });

                HttpAsyncClientUtil.get(httpConfigContext);
            }
        }
    }
}
