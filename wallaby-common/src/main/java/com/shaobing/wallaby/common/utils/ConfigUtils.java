package com.shaobing.wallaby.common.utils;

import com.shaobing.wallaby.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

/**
 * 配置文件功能类,用于加载properties文件等
 *
 * @author luyb@servyou.com.cn
 * @version 2016-09-10 13:15
 */
public class ConfigUtils {
    private static final Logger logger = LoggerFactory.getLogger(ConfigUtils.class);

    /**
     * 配置文件
     */
    private static volatile Properties PROPERTIES;

    /**
     * 返回属性值
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getPropertyValue(String key, String defaultValue) {
        if (key == null)
            throw new NullPointerException("key == null");

        Properties properties = getProperties();
        String value = properties.getProperty(key);
        return value == null ? defaultValue : value;
    }

    /**
     * 加载配置文件
     *
     * @return
     */
    public static Properties getProperties() {
        if (PROPERTIES == null) {
            synchronized (ConfigUtils.class) {
                if (PROPERTIES == null) {
                    String path = System.getProperty(Constants.WALLABY_PROPERTIES_KEY);
                    if (path == null || path.length() == 0) {
                        path = System.getenv(Constants.WALLABY_PROPERTIES_KEY);
                        if (path == null || path.length() == 0) {
                            path = Constants.DEFAULT_WALLABY_PROPERTIES;
                        }
                    }
                    PROPERTIES = loadProperties(path);
                }
            }
        }
        return PROPERTIES;
    }

    public static Properties loadProperties(String fileName) {
        Properties properties = new Properties();
        InputStream inputStream = null;
        boolean resovled = true;

        try {
            inputStream = new FileInputStream(fileName);
            properties.load(inputStream);
        } catch (FileNotFoundException e1) {
            resovled = false;
            if (logger.isWarnEnabled()) {
                logger.warn("unable to find file " + fileName, e1);
            }
        } catch (IOException e2) {
            resovled = false;
            if (logger.isWarnEnabled()) {
                logger.warn("unable to load file " + fileName, e2);
            }
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    if (logger.isErrorEnabled()) {
                        logger.error("关闭" + fileName + "失败", e3);
                    }
                }
            }
        }

        //解析未成功
        if (!resovled) {
            properties = loadProperties();
        }

        return properties;
    }

    public static Properties loadProperties() {
        Properties properties = new Properties();
        InputStream in = ConfigUtils.class.getClassLoader().getResourceAsStream(Constants.DEFAULT_WALLABY_PROPERTIES);
        try {
            properties.load(in);
            return properties;
        } catch (Throwable e) {
            if (logger.isErrorEnabled())
                logger.error("unable to load default file " + Constants.DEFAULT_WALLABY_PROPERTIES, e);
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
                if (logger.isErrorEnabled())
                    logger.error("unable to load default file " + Constants.DEFAULT_WALLABY_PROPERTIES, e);
            }
        }

        return properties;
    }


    public static void test2() {
        Properties properties = System.getProperties();
        if (properties != null && !properties.isEmpty()) {
            Enumeration<?> enumeration = properties.propertyNames();
            while (enumeration.hasMoreElements()) {
                System.out.println(enumeration.nextElement());
            }
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                System.out.println(entry.getKey() + "=" + entry.getValue());
            }
        }

        //环境变量
        Map<String, String> env = System.getenv();
        for (Map.Entry<String, String> entry : env.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }

        System.out.println(ConfigUtils.getPropertyValue("wallaby", "default"));
    }

    public static void main(String[] args) {
        test2();
    }
}
