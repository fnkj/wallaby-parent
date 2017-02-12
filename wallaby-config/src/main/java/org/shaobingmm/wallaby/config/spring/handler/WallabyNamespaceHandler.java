package org.shaobingmm.wallaby.config.spring.handler;

import org.shaobingmm.wallaby.config.RequestConfig;
import org.shaobingmm.wallaby.config.spring.parser.WallabyBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class WallabyNamespaceHandler extends NamespaceHandlerSupport {

	@Override
	public void init() {
		//配置标签解析器
		registerBeanDefinitionParser("request", new WallabyBeanDefinitionParser(RequestConfig.class));
	}

}
