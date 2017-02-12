package com.shaobingmm.wallaby.crawler.parser;

/**
 * 转换接口
 *
 * @author luyb@servyou.com.cn
 * @version $Id: Transformer.java v 0.1 2017/1/31 12:59 luyb Exp $$
 */
public interface Transformer<I, O> {

    /**
     *  讲输入对象I转换为输出对象O
     *
     * @param input
     * @return O
     */
    O transform(I input);
}
