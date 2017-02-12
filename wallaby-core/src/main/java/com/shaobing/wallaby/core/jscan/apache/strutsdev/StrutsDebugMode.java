package com.shaobing.wallaby.core.jscan.apache.strutsdev;

import org.apache.commons.lang3.StringUtils;

/**
 * @author luyb@servyou.com.cn
 * @version $Id: StrutsDebugMode.java v 0.1 2016/12/27 23:55 luyb Exp $$
 */
public enum StrutsDebugMode {

    CONSOLE("console", "console"),
    BROWSER("browser", "browser"),
    XML("xml", "xml");

    private final String code;
    private final String msg;

    StrutsDebugMode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public StrutsDebugMode getByCode(String code) {
        if (StringUtils.isEmpty(code))
            return null;

        for (StrutsDebugMode debugDev : values()) {
            if (code.equalsIgnoreCase(debugDev.getCode()))
                return debugDev;
        }

        return null;
    }

    /**
     * Getter for property 'code'.
     *
     * @return code Value for property 'code'.
     */
    public String getCode() {
        return code;
    }

    /**
     * Getter for property 'msg'.
     *
     * @return msg Value for property 'msg'.
     */
    public String getMsg() {
        return msg;
    }
}
