package com.shaobingmm.wallaby.crawler.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 检查Url是否已经存在Map中
 *
 * @author luyb@servyou.com.cn
 * @version 2016-09-13 1:22
 */
public class UrlCheckerMap extends ConcurrentHashMap<String, Boolean>  {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrlCheckerMap.class);

}
