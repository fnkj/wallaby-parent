package com.shaobing.wallaby.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射类辅助功能
 *
 * @author luyb@servyou.com.cn
 * @version 2016-09-12 19:31
 */
public class ClassUtils {

    public static void setBeanValue(Object object, String attName, String attValue) {
        Class<?> clazz = object.getClass();
        Field[] field = clazz.getDeclaredFields();
        try {
            for (int j = 0; j < field.length; j++) {
                String name = field[j].getName();
                if (name.equals(attName)) {
                    name = name.substring(0, 1).toUpperCase() + name.substring(1);
                    String type = field[j].getGenericType().toString(); // 获取属性的类型
                    if (type.equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
                        Method m = clazz.getMethod("get" + name);
                        String value = (String) m.invoke(object); // 调用getter方法获取属性值
                        if (value == null) {
                            m = clazz.getMethod("set" + name, String.class);
                            m.invoke(object, attValue);
                        }
                    }
                    if (type.equals("class java.lang.Integer")) {
                        Method m = clazz.getMethod("get" + name);
                        Integer value = (Integer) m.invoke(object);
                        if (value == null) {
                            m = clazz.getMethod("set" + name, Integer.class);
                            m.invoke(object, Integer.valueOf(attValue));
                        }
                    }

                    break;
                }

            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
