package com.shaobing.wallaby.core.ognl.collections;

import ognl.OgnlException;
import org.junit.Test;

import ognl.Ognl;
import ognl.OgnlContext;

/**
 * @author luyb@servyou.com.cn
 * @version $$: TestCollections.java v 0.1 2016/11/29 17:22 luyb Exp $$
 */
public class TestCollections {
    @Test
    public void testCollections() throws OgnlException {
        OgnlContext context = new OgnlContext();

        Humen humen = new Humen();
        humen.setName("qiuyi");
        humen.setSex("n");
        humen.setAge(22);
        humen.getFriends().add(new Humen("zhangsan", "n", 22));
        humen.getFriends().add(new Humen("lisi", "f", 21));
        humen.getFriends().add(new Humen("wangwu", "n", 23));
        humen.getFriends().add(new Humen("zhaoliu", "n", 22));
        humen.getFriends().add(new Humen("qianqi", "n", 22));
        humen.getFriends().add(new Humen("sunba", "f", 20));
        humen.getFriends().add(new Humen("yangqiu", "f", 25));

        context.put("humen", humen);
        context.setRoot(humen);

        /* OGNL过滤集合的语法为：collection.{? expression} */
        Object filterCollection = Ognl.getValue("friends.{? #this.name.length() > 7}", context,
            context.getRoot());
        System.out.println("filterCollection is :" + filterCollection);

        System.out.println("--------------------------飘逸的分割线--------------------------");

        /* OGNL投影集合的语法为：collection.{expression} */
        Object projectionCollection = Ognl.getValue("friends.{name}", context, context.getRoot());
        System.out.println("projectionCollection is :" + projectionCollection);

    }
}
