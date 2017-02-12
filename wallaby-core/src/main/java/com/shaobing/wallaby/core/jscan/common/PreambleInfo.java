package com.shaobing.wallaby.core.jscan.common;

import com.shaobing.wallaby.common.enums.Platform;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 前置信息
 *
 * @author luyb@servyou.com.cn
 * @version $Id: PreambleInfo.java v 0.1 2016/12/25 17:13 luyb Exp $$
 */
public class PreambleInfo {

    /** os */
    private Platform platform;

    /** 服务器类型 */
    private WebServer webServer;

    /** 是否为JAVA应用 */
    private boolean   javaApp;

    /**
     * Getter for property 'platform'.
     *
     * @return platform Value for property 'platform'.
     */
    public Platform getPlatform() {
        return platform;
    }

    /**
     * Setter for property 'platform'.
     *
     * @param platform Value to set for property 'platform'.
     */
    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    /**
     * Getter for property 'webServer'.
     *
     * @return webServer Value for property 'webServer'.
     */
    public WebServer getWebServer() {
        return webServer;
    }

    /**
     * Setter for property 'webServer'.
     *
     * @param webServer Value to set for property 'webServer'.
     */
    public void setWebServer(WebServer webServer) {
        this.webServer = webServer;
    }

    /**
     * Getter for property 'javaApp'.
     *
     * @return webServer Value for property 'javaApp'.
     */
    public boolean getJavaApp() {
        return javaApp;
    }

    /**
     * Setter for property 'javaApp'.
     *
     * @param javaApp Value to set for property 'javaApp'.
     */
    public void setJavaApp(boolean javaApp) {
        this.javaApp = javaApp;
    }

    /**
     *  前置信息转换成Map
     *
     * @return  Map<String, Object>
     */
    public Map<String, Object> convertToMap() {
        HashMap<String, Object> map = new HashMap<>();

        try {
            Field[] fields = this.getClass().getDeclaredFields();
            AccessibleObject.setAccessible(fields, true);

            for (int i = 0; i < fields.length; ++i) {
                Field field = fields[i];
                String fieldName = field.getName();
                try {
                    Object value = field.get(this);
                    map.put(fieldName, value);
                } catch (IllegalAccessException e) {
                }
            }
        } catch (SecurityException e) {
            //ignore
        } catch (Throwable e) {
            //ignore
        } finally {
        }

        return map;
    }

    @Override
    public String toString() {
        return "PreambleInfo{" + "platform=" + platform + ", webServer=" + webServer + ", javaApp="
               + javaApp + '}';
    }
}
