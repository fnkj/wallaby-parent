package com.shaobingmm.wallaby.jnmap;

import com.shaobing.wallaby.common.logger.LoggerUtils;
import com.shaobingmm.wallaby.jnmap.exception.JNampExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * JNmap执行器
 *
 * @author luyb@servyou.com.cn
 * @version $Id: JNampProcessExecutor.java v 0.1 2017/1/15 21:58 luyb Exp $$
 */
public class JNampProcessExecutor implements JNmapExecutor {

    public static final Logger LOGGER = LoggerFactory
        .getLogger(JNampProcessExecutor.class);

    @Override
    public JNmapResult exexute(JNmapCommand nmapCommand) {
        if (nmapCommand == null)
            throw new IllegalArgumentException("Empty command");
        try {
            JNmapResult result = new JNmapResult();
            String fullCommand = nmapCommand.getFullCommand();
            result.setCommand(fullCommand);

            Process process = Runtime.getRuntime().exec(fullCommand);
            result.setResult(convertStream(process.getInputStream()));
            result.setErrors(convertStream(process.getErrorStream()));

            return result;
        } catch (IOException e) {
            throw new JNampExecutionException("执行NMAP命令发生错误,command=", e);
        }
    }

    private String convertStream(InputStream inputStream) throws IOException {
        return convertStream(inputStream, false);
    }

    /**
     *  输入的<code>java.lang.Process</code>的输入流转换成结果字符串
     *
     * @param inputStream   输入流
     * @param isPrettify    是否增加换行符
     * @return  String
     * @throws IOException
     */
    private String convertStream(InputStream inputStream, boolean isPrettify) throws IOException {
        StringBuilder resultBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            String line = null;
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = bufferedReader.readLine()) != null) {
                if (!isPrettify) {
                    resultBuilder.append(line);
                } else {
                    resultBuilder.append(line).append(System.getProperty("line.separator"));
                }
            }

        } catch (IOException e) {
            LoggerUtils.error(LOGGER, e, "读取Process流时失败!");
            throw e;
        } finally {
            if (bufferedReader != null)
                try {
                    //只需要调用最终被装饰出的对象的 close()方法即可，因为它最终会调用真正数据源对象的 close()方法
                    bufferedReader.close();
                } catch (IOException e) {
                    LoggerUtils.warn(LOGGER, e, "关闭流失败!");
                }
        }

        return resultBuilder.toString();
    }
}
