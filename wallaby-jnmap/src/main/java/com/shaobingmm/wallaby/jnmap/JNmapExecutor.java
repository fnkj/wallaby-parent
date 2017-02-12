package com.shaobingmm.wallaby.jnmap;

/**
 *
 * Java封装Namp
 *
 *
 * @author luyb@servyou.com.cn
 * @version $Id: JNmapExecutor.java v 0.1 2017/1/15 21:46 luyb Exp $$
 */
public interface JNmapExecutor {

    /**
     * 执行JNamp命令
     *
     * @param command  JNamp命令对象
     * @return  JNmapResult
     */
    JNmapResult exexute(JNmapCommand command);

}
