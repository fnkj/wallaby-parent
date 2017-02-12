package com.shaobingmm.wallaby.crawler.parser;

/**
 * 将原始杂乱的HTML转换成整洁的HTML
 *
 * @author luyb@servyou.com.cn
 * @version $Id: HtmlTransform.java v 0.1 2017/1/31 0:22 luyb Exp $$
 */
public class HtmlTransformer implements Transformer<String, String> {

    @Override
    public String transform(String input) {
        return input;
    }
}
