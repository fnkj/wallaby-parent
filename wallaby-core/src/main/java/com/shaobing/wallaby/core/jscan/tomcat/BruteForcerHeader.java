package com.shaobing.wallaby.core.jscan.tomcat;

import com.shaobing.wallaby.common.annotation.CNotNull;
import com.shaobing.wallaby.common.http.exception.HttpHeaderException;
import com.shaobing.wallaby.common.http.utils.CustomHttpHeader;

/**
 * tomcat爆破自定义请求头
 *
 * @author luyb@servyou.com.cn
 * @version $$: BruteForcerHeader.java v 0.1 2016/11/28 0:13 luyb Exp $$
 */
public class BruteForcerHeader implements CustomHttpHeader {

    @CNotNull
    private String authorization;

    @Override
    public void checkField() throws HttpHeaderException {
    }

    /**
     * Getter for property 'authorization'.
     *
     * @return authorization Value for property 'authorization'.
     */
    public String getAuthorization() {
        return authorization;
    }

    /**
     * Setter for property 'authorization'.
     *
     * @param authorization Value to set for property 'authorization'.
     */
    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }
}
