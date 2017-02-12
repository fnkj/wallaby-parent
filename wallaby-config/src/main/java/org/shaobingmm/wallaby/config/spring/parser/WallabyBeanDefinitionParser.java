package org.shaobingmm.wallaby.config.spring.parser;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;  

public class WallabyBeanDefinitionParser implements BeanDefinitionParser {
	
	private final Class<?> beanClass;
	
	public WallabyBeanDefinitionParser(Class<?> beanClass) {
		this.beanClass = beanClass;
	}

	@Override
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		RootBeanDefinition beanDefinition = new RootBeanDefinition();
		beanDefinition.setBeanClass(beanClass);
		beanDefinition.setLazyInit(false);
		
		return beanDefinition;
	}

}
