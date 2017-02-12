package com.shaobing.wallaby.common.http.httpclient;

import com.shaobing.wallaby.common.logger.LoggerUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 暗链提取
 *
 * @author luyb@servyou.com.cn
 * @version 2016-09-18 19:40
 */
public class HideLinksAsyncHandler implements IAsyncHandler {

    private static final Logger logger = LoggerFactory.getLogger(HideLinksAsyncHandler.class);

    @Override
    public void completed(String url, HttpResponse response) {
        HttpEntity httpEntity = response.getEntity();
        Header[] headers = response.getAllHeaders();
        if (httpEntity != null) {
            String body = null;
            try {
                body = EntityUtils.toString(httpEntity, "UTF-8");

                LoggerUtils.debug(logger, " body is {0}", body);
            } catch (IOException e) {
            }
        }
    }

    @Override
    public void failed(Exception ex) {
        System.out.println(ex.toString());
    }

    @Override
    public void cancelled() {
        System.out.println();
    }
}
