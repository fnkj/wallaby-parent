package com.shaobingmm.wallaby.main;

import com.shaobing.wallaby.common.logger.LoggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * JAVA全栈监测入口
 *
 * @author pez1420@gmail.com
 * @version $Id: WallabyMain.java v 0.1 2016/08/10 00:07 pez1420 Exp $$
 */
public class WallabyMain {

    private static final Logger     logger  = LoggerFactory.getLogger(WallabyMain.class);

    private static volatile boolean running = true;

    public static void main(String[] args) {
        LoggerUtils.info(logger, "#{0} start!", WallabyMain.class.getSimpleName());

        final WallabyStartup startup = WallabyStartup.getInstance();
        startup.start();

        //退出应用程序之前 回调jvm线程进行资源清理
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            private volatile boolean hasShutdown   = false;
            private AtomicInteger    shutdownTimes = new AtomicInteger(0);

            @Override
            public void run() {
                synchronized (this) {
                    LoggerUtils.info(logger,
                        "shutdown hook was invoked, " + this.shutdownTimes.incrementAndGet());

                    if (!this.hasShutdown) {
                        this.hasShutdown = true;
                        long begineTime = System.currentTimeMillis();
                        startup.stop();
                        long consumingTimeTotal = System.currentTimeMillis() - begineTime;
                        LoggerUtils.info(logger,
                            "shutdown hook over, consuming time total(ms): " + consumingTimeTotal);
                    }
                }
            }

        }, "ShutdownHook"));

        synchronized (WallabyMain.class) {
            while (running) {
                try {
                    WallabyMain.class.wait();
                } catch (Throwable e) {
                }
            }
        }
    }

}
