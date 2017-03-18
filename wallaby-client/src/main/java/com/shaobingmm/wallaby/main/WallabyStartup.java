package com.shaobingmm.wallaby.main;

import com.shaobing.wallaby.common.http.utils.URL;
import com.shaobing.wallaby.common.threadpool.ExecutorUtils;
import com.shaobing.wallaby.common.threadpool.NameableExecutor;
import com.shaobing.wallaby.core.jscan.common.PreambleInfo;
import com.shaobing.wallaby.core.monitor.PreambleService;
import com.shaobingmm.wallaby.task.StartUpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Wallaby启动类
 *
 * @author pez1420@gmail.com
 * @version 2016/8/10 0:07
 */
public class WallabyStartup {

    private static final Logger            logger                       = LoggerFactory
        .getLogger(WallabyStartup.class);

    /** 可用CPU数目 */
    private static final int               AVAILABLE_PROCESSORS         = Runtime.getRuntime()
        .availableProcessors();

    /** countlatchdown */
    private static final int               DEFAULT_COUNTDOWNLATCH_TIMES = AVAILABLE_PROCESSORS;

    /** 启动实例 */
    private volatile static WallabyStartup instance;

    /** JVM进行是否运行 */
    private volatile boolean               isRunning                    = true;

    /** 线程池 */
    private NameableExecutor               workExecutor;

    private WallabyStartup() {
    }

    /**
     * 返回单例<code>WallabyStartup</code>
     *
     * @return WallabyStartup
     */
    public static WallabyStartup getInstance() {
        if (instance == null) {
            synchronized (WallabyStartup.class) {
                if (instance == null)
                    instance = new WallabyStartup();
            }
        }

        return instance;
    }

    /**
     * 开启扫描任务
     * <ul>
     *     <li>检测前置信息</li>
     * </ul>
     */
    public void start() throws StartUpException{
        final CountDownLatch countDownLatch = new CountDownLatch(DEFAULT_COUNTDOWNLATCH_TIMES);
        workExecutor = ExecutorUtils.create("preamble-thread-", 1);
        final URL url = null;
        final Future<PreambleInfo> future = this.workExecutor.submit(new PreambleService(url));
        try {
            PreambleInfo preambleInfo = future.get();
        } catch (InterruptedException e) {
            throw new StartUpException("分析URL" + url + "指纹摘要信息异常!" + e.getMessage());
        } catch (ExecutionException e) {
            throw new StartUpException("分析URL" + url + "指纹摘要信息异常!" + e.getMessage());
        }

    }

    /**
     * 资源清理
     */
    public void stop() {
        if (!isRunning && !workExecutor.isShutdown()) {
            workExecutor.shutdown();
        }
    }

    public final NameableExecutor getWorkExecutor() {
        return workExecutor;
    }

}
