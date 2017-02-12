package com.shaobing.wallaby.core.jscan.xss;

import org.apache.commons.lang3.StringUtils;

/**
 * Xss类型
 *
 * @author luyb@servyou.com.cn
 * @version $Id: XssType.java v 0.1 2017/1/2 21:30 luyb Exp $$
 */
public enum XssType {
    STORED("stored", "存储型"),
    REFLECTED("REFLECTED", "反射型"),
    DOM("DOM", "DOM Based");

    /** xss类型 */
    private final String type;

    /** 描述 */
    private final String msg;

    XssType(String type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    /**
     * 基于类型返回描述
     *
     * @param type  xss类型
     * @return  XssType
     */
   public XssType getByType(String type) {
        if (StringUtils.isEmpty(type))
            return null;

       for (XssType xssType : values()) {
           if (xssType.getType().equals(type))
               return xssType;
       }

       return null;
   }

    /**
     * Getter for property 'type'.
     *
     * @return type Value for property 'type'.
     */
    public String getType() {
        return type;
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
