package com.shaobing.wallaby.common.enums;

/**
 * 系统平台
 *
 * @author luyb@servyou.com.cn
 * @version $$: Platform.java v 0.1 2016/12/1 12:48 luyb Exp $$
 */
public enum Platform {
    WINDOWS("windows"),
    LINUX("linux"),
    MAC_OS("Mac OS"),
    MAC_OS_X("Mac OS X"),
    AIX("AIX"),
    FREEBSD("FreeBSD"),
    UNKNOWN("unkonown");

    /** 操作系统名称 */
    private final String name;

    Platform(String name) {
        this.name = name;
    }

    /**
     * 基于名称返回操作系统类型
     *
     * @param name  操作系统名称
     * @return
     */
    public Platform getByName(String name) {
        if (name == null || name.length() == 0)
            return null;

        for (Platform platform : values()) {
            if (platform.getName().equals(name))
                return platform;
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
