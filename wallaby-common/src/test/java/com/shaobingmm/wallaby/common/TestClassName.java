package com.shaobingmm.wallaby.common;

import org.junit.Test;

public class TestClassName {

	@Test
	public void test_getResource() {
		System.out.println(TestClassName.class.getResource(""));
		System.out.println(TestClassName.class.getResource("/"));
		System.out.println(ClassLoader.getSystemResource("").getPath());
	}
}
