package com.shaobing.wallaby.core.ognl;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Ognl测试类
 *
 * @author luyb@servyou.com.cn
 * @version $$: TestOgnl.java v 0.1 2016/11/29 12:07 luyb Exp $$
 */
public class TestOgnl {
    private static OgnlContext context = new OgnlContext();

    @Test
    public void testGetValue() {
        People people = new People();
        people.setName("luyoubing");

        try {
            Object value = Ognl.getValue("name", people);
            System.out.println(value);
        } catch (OgnlException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testContext() {
        /* 创建一个上下文Context对象，它是用保存多个对象一个环境 对象 */
        Map<String, Object> context = new HashMap<String, Object>();

        People person1 = new People();
        person1.setName("zhangsan");

        People person2 = new People();
        person2.setName("lisi");

        People person3 = new People();
        person3.setName("wangwu");

        /* person4不放入到上下文环境中 */
        People person4 = new People();
        person4.setName("zhaoliu");

        /* 将person1、person2、person3添加到环境中（上下文中） */
        context.put("person1", person1);
        context.put("person2", person2);
        context.put("person3", person3);

        try {
            /* 获取根对象的"name"属性值 */
            Object value = Ognl.getValue("name", context, person2);
            System.out.println("ognl expression \"name\" evaluation is : " + value);

            /* 获取根对象的"name"属性值 */
            Object value2 = Ognl.getValue("#person2.name", context, person2);
            System.out.println("ognl expression \"#person2.name\" evaluation is : " + value2);

            /* 获取person1对象的"name"属性值 */
            Object value3 = Ognl.getValue("#person1.name", context, person2);
            System.out.println("ognl expression \"#person1.name\" evaluation is : " + value3);

            /* 将person4指定为root对象，获取person4对象的"name"属性，注意person4对象不在上下文中 */
            Object value4 = Ognl.getValue("name", context, person4);
            System.out.println("ognl expression \"name\" evaluation is : " + value4);

            /* 将person4指定为root对象，获取person4对象的"name"属性，注意person4对象不在上下文中 */
            Object value5 = Ognl.getValue("#person4.name", context, person4);
            System.out.println("ognl expression \"person4.name\" evaluation is : " + value5);

            /* 获取person4对象的"name"属性，注意person4对象不在上下文中 */
            // Object value6 = Ognl.getValue("#person4.name", context, person2);
            // System.out.println("ognl expression \"#person4.name\" evaluation is : " + value6);

        } catch (OgnlException e) {
            e.printStackTrace();
        }
    }


    /**
     * OGNL:对象图导航语言
     * OgnlContext:上下文对象。存在唯一的叫做根的对象(root)，
     * 可以通过程序设定上下文当中的哪个对象作为根对象。
     * 在OGNL中，如果表达式没有使用#号，那么OGNL会从根对象中寻找该属性对应的get方法，如果寻找不是根对象中的属性，
     * 那么则需要以#号开头，告诉OGNL，去寻找你所制定的特定对象中的属性。
     */
    @Test
    public void testMethod() {
        /* OGNL提供的一个上下文类，它实现了Map接口 */
        OgnlContext context = new OgnlContext();

        People people1 = new People();
        people1.setName("zhangsan");

        People people2 = new People();
        people2.setName("lisi");

        People people3 = new People();
        people3.setName("wangwu");

        context.put("people1", people1);
        context.put("people2", people2);
        context.put("people3", people3);

        context.setRoot(people1);

        try {
            /* 调用 成员方法 */
            Object value = Ognl.getValue("name.length()", context, context.getRoot());
            System.out.println("people1 name length is :" + value);

            Object upperCase = Ognl.getValue("#people2.name.toUpperCase()", context,
                context.getRoot());
            System.out.println("people2 name upperCase is :" + upperCase);

            Object invokeWithArgs = Ognl.getValue("name.charAt(5)", context, context.getRoot());
            System.out.println("people1 name.charAt(5) is :" + invokeWithArgs);

            /* 调用静态方法 */
            Object min = Ognl.getValue("@java.lang.Math@min(4,10)", context, context.getRoot());
            System.out.println("min(4,10) is :" + min);

            /* 调用静态变量 */
            Object e = Ognl.getValue("@java.lang.Math@E", context, context.getRoot());
            System.out.println("E is :" + e);
            Object exec = Ognl.getValue("@java.lang.Runtime@getRuntime().exec(\"calc\")", context, context.getRoot());
        } catch (OgnlException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testBasic() throws OgnlException {
        People people = new People();
        people.setName("luyoubing");

        context.put("people", people);
        context.setRoot(people);

        Object object = Ognl.parseExpression("name");
        System.out.println(object);

        Object object2 = Ognl.getValue(object, context, context.getRoot());
        System.out.println(object2);

        Object object3 = Ognl.parseExpression("#people.name");
        System.out.println(object3);

        Object object4 = Ognl.getValue(object3, context, context.getRoot());
        System.out.println(object4);

    }

    @Test
    public void testSecurity() {
        try {
            People people = new People();
            people.setName("luyoubing");

            context.put("people", people);
            context.setRoot(people);

           Ognl.setValue("name", people, "shaobing");
            System.out.println(people.getName());
        } catch (OgnlException e) {
            e.printStackTrace();
        } finally {
        }
    }

}
