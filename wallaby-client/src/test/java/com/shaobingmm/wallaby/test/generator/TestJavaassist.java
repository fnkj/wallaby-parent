package com.shaobingmm.wallaby.test.generator;

import org.junit.Assert;
import org.junit.Test;

//https://jboss-javassist.github.io/javassist/tutorial/tutorial.html
//http://blog.csdn.net/yyywyr/article/details/16968633

public class TestJavaassist {

	@Test
	public void test_isAssignableFrom() {
		Assert.assertTrue(Object.class.isAssignableFrom(String.class));
		Assert.assertTrue(Object.class.isAssignableFrom(Object.class));
		Assert.assertTrue(String.class.isAssignableFrom(Object.class));
	}

	@Test
	public void test_instanceof() {
		final String name = "123";
		Assert.assertTrue(name instanceof Object);
		Assert.assertTrue(name instanceof String);
		Assert.assertTrue(TestJavaassist.class.isInterface());
	}

	@Test
	public void test_package() {
		System.out.println(TestJavaassist.class.getName());
		System.out.println(TestJavaassist.class.getCanonicalName());
		System.out.println(TestJavaassist.class.getSimpleName());
		System.out.println(TestJavaassist.class.getPackage().getName());
		// 结果为[B ([B为class文件的表示方式,要了解Java字节码文件Class文件的格式) 和 byte[]
		System.out.println(byte[].class.getName());
		System.out.println(byte[].class.getCanonicalName());
	}

	@Test
	public void test_getClass() {
		class Art {
			Art() {
				System.out.println("Art");
				System.out.println(getClass().getName());
			}
		}
		class Drawing extends Art {
			Drawing() {
				System.out.println("Drawing");
				System.out.println(getClass().getName());
			}
		}
		class Cartoon extends Drawing {
			Cartoon() {
				System.out.println("Cartoon");
				System.out.println(getClass().getName());
			}
		}
		//当调用getClass()时，返回这个对象真实的Class对象
		//从3个继承对象相等的情况和输出可知，这三个对象有相同的this指针，即内存地址一致。
		//而getClass()返回的就是this指针所代表的最真实的Class的对象，也即最上层的子类。  
		Cartoon x = new Cartoon();

		Drawing one = (Drawing) x;
		Art two = (Art) x;
		if (one == two) {
			System.out.println("==");
		} else {
			System.out.println("!=");
		}
		System.out.println(x.toString());
		System.out.println(one.toString());
		System.out.println(two.toString());
	}

	@Test
	public void test_Object() {
		class A {
			public void test() {
				System.out.println("A");
			}
		}
		
		A a = new A();
		Object b = (Object)a;
		A c = (A)b;
		
		if (a == b) {
			System.out.println("a==b");
		} else {
			System.out.println("a!=b");
		}
		
		if (b == c) {
			System.out.println("b==c");
		} else {
			System.out.println("b!=c");
		}
		
		c.test();
	}
}
