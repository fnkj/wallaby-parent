package com.shaobing.wallaby.core.http;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Main
 *
 * @author luyb@servyou.com.cn
 * @version 2016-09-10 20:18
 */

class Instance {

}

public class TestMain {
    private static class InnerClass {
        public static Instance instance = new Instance();
    }

    public static Instance getInstance() {
        return InnerClass.instance;
    }

    public static void main(String[] args) {

        try {
            Method method = Runtime.class.getMethod("getRuntime", new Class[0]);
            Runtime runtime = (Runtime)method.invoke(null, new Object[0]);

            String[] cmd = new String[3];
            cmd[0] = "cmd.exe";
            cmd[1] = "/C";
            cmd[2] = "javac.exe";
            Process proc = runtime.exec(cmd);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
