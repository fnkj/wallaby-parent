package com.shaobingmm.wallaby.jnmap;

import com.shaobing.wallaby.common.enums.Platform;
import com.shaobingmm.wallaby.jnmap.exception.JNampExecutionException;

/**
 * @author luyb@servyou.com.cn
 * @version $Id: JNmapUtils.java v 0.1 2017/1/15 22:06 luyb Exp $$
 */
public class JNmapUtils {

    /**
     * 返回NMAP 可执行程序完整路径
     *
     * @return  String
     */
    public static String getFullPath() {
        String nmapPath = System.getenv("NMAP_PATH");
        if (nmapPath == null || nmapPath.length() == 0)
            throw new JNampExecutionException("Nmap环境变量NMAP_PATH不能为空!");
        int index = nmapPath.lastIndexOf("/");
        if (index == -1)
            nmapPath = nmapPath + "/";
        String osName = System.getProperty("os.name").toLowerCase();
        Platform platform = judgeOsPlatorm(osName);
        switch (platform) {
            case LINUX:
                nmapPath += "nmap";
                break;
            case WINDOWS:
                nmapPath += "nmap.exe";
                break;
            case MAC_OS:
                nmapPath += "nmap";
            default:
                throw new JNampExecutionException("当前的操作系统" + platform.getName() + "不支持");

        }

        return nmapPath;
    }

    /**
     * 判断操作系统类型名称
     *
     * @param osName    操作系统中文名
     * @return  Platform
     */
    public static Platform judgeOsPlatorm(String osName) {
        if (osName.indexOf("linux") > -1)
            return Platform.LINUX;
        if (osName.indexOf("windows") > -1)
            return Platform.WINDOWS;
        if (osName.indexOf("mac") > -1) {
            return Platform.MAC_OS;
        }
        return Platform.UNKNOWN;
    }


}
