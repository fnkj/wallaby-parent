package com.shaobing.wallaby.core.jscan.tomcat;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Tomcat的认证比较弱，Base64(用户名:密码)编码，请求：/manager/html
 * 如果响应码不是<code>401未经授权：访问由于凭据无效被拒绝</code>（即登录成功。
 *
 * @author luyb@servyou.com.cn
 * @version $$: WeakPassword.java v 0.1 2016/11/27 21:45 luyb Exp $$
 */
public class WeakPassword {

    public static final List<Map.Entry<String, String>> credentials = new ArrayList<Map.Entry<String, String>>() {
        private static final long serialVersionUID = 3296893375611534646L;
        {
            add(new AbstractMap.SimpleEntry<>("tomcat", "tomcat"));
            add(new AbstractMap.SimpleEntry<>("tomcat", "manager"));
            add(new AbstractMap.SimpleEntry<>("tomcat", "jboss"));
            add(new AbstractMap.SimpleEntry<>("tomcat", "password"));
            add(new AbstractMap.SimpleEntry<>("tomcat", ""));
            add(new AbstractMap.SimpleEntry<>("both", "manager"));
            add(new AbstractMap.SimpleEntry<>("both", "tomcat"));
            add(new AbstractMap.SimpleEntry<>("admin", "password"));
            add(new AbstractMap.SimpleEntry<>("admin", "tomcat"));
            add(new AbstractMap.SimpleEntry<>("admin", "manager"));
            add(new AbstractMap.SimpleEntry<>("manager", "manager"));
            add(new AbstractMap.SimpleEntry<>("manager", "tomcat"));
            add(new AbstractMap.SimpleEntry<>("role1", "role1"));
            add(new AbstractMap.SimpleEntry<>("role1", "tomcat"));
            add(new AbstractMap.SimpleEntry<>("role", "changethis"));
            add(new AbstractMap.SimpleEntry<>("root", "changethis"));
            add(new AbstractMap.SimpleEntry<>("tomcat", "changethis"));
            add(new AbstractMap.SimpleEntry<>("admin", "j5Brn9")); // Sun Solaris       
            add(new AbstractMap.SimpleEntry<>("admin", "admin"));
            add(new AbstractMap.SimpleEntry<>("admin", "root"));
            add(new AbstractMap.SimpleEntry<>("admin", "password"));
            add(new AbstractMap.SimpleEntry<>("admin", ""));
            add(new AbstractMap.SimpleEntry<>("admin", "1234"));
            add(new AbstractMap.SimpleEntry<>("admin", "axis2"));
            add(new AbstractMap.SimpleEntry<>("test", "test"));
            add(new AbstractMap.SimpleEntry<>("monitor", "monitor"));
            add(new AbstractMap.SimpleEntry<>("guest", "guest"));
            add(new AbstractMap.SimpleEntry<>("root", ""));
            add(new AbstractMap.SimpleEntry<>("root", "root"));
            add(new AbstractMap.SimpleEntry<>("root", "admin"));
            add(new AbstractMap.SimpleEntry<>("root", "password"));
            add(new AbstractMap.SimpleEntry<>("weblogic", "weblogic"));
            add(new AbstractMap.SimpleEntry<>("weblogic", "weblogic1"));
            add(new AbstractMap.SimpleEntry<>("weblogic", "welcome1"));
            add(new AbstractMap.SimpleEntry<>("orbeonadmin", "xforms"));
        }
    };
}
