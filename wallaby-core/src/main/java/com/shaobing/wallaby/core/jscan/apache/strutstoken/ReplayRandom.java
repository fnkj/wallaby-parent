package com.shaobing.wallaby.core.jscan.apache.strutstoken;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 用于重构随机Random
 *
 * @author luyb@servyou.com.cn
 * @version $Id: ReplayRandom.java v 0.1 2016/12/31 20:44 luyb Exp $$
 */
public class ReplayRandom implements Serializable {

    private static final long serialVersionUID = 815880192876473528L;

    /** 线性同余常量A*/
    private static final long multiplier       = 0x5DEECE66DL;

    /** 线性同于常量B */
    private static final long addend           = 0xBL;

    /** 线性同于常量C, 取模(%)操作可以转换成与(&)操作*/
    private static final long mask             = (1L << 48) - 1;

    /** 随机数种子 */
    private final AtomicLong  seed;

    public ReplayRandom(long seed) {
        this.seed = new AtomicLong();
        this.seed.set(seed);
    }

    /**
     *  返回指定{@code bits}位数的随机 {@code int}整数
     *
     * @param bits  random bits
     * @return  the next pseudorandom value from this random number generator's sequence
     */
    protected int next(int bits) {
        long oldseed, nextseed;
        AtomicLong seed = this.seed;
        do {
            oldseed = seed.get();
            nextseed = (oldseed * multiplier + addend) & mask;
        } while (!seed.compareAndSet(oldseed, nextseed));
        return (int) (nextseed >>> (48 - bits));
    }

    /**
     * 返回随机整数
     * 
     * @return  int
     */
    public int nextInt() {
        return next(Integer.SIZE);
    }
}
