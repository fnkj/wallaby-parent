package com.shaobing.wallaby.core.http.j2ee;

import com.shaobing.wallaby.core.jscan.apache.ApacheS2023ScanTask;
import com.shaobing.wallaby.core.jscan.common.task.ScanTaskAdapter;
import com.shaobing.wallaby.core.jscan.apache.strutstoken.ByteUtils;
import com.shaobing.wallaby.core.jscan.common.PreambleInfo;
import com.shaobing.wallaby.core.jscan.common.ScanIssue;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import java.math.BigInteger;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;

/**
 * @author luyb@servyou.com.cn
 * @version $Id: TestApacheS2023.java v 0.1 2016/12/31 23:02 luyb Exp $$
 */
public class TestApacheS2023 {

    @Test
    public void testCsrfToken() {
        String url = "http://localhost:8080/s3/test/hello.action";
        ScanTaskAdapter scanTask = new ApacheS2023ScanTask();
        PreambleInfo preambleInfo = new PreambleInfo();
        List<ScanIssue> scanIssues = scanTask.execute(url, preambleInfo);
        System.out.println(
            ToStringBuilder.reflectionToString(scanIssues, ToStringStyle.SHORT_PREFIX_STYLE));
    }

    public void testMain() {
        String page = "<input type=\"hidden\" name=\"struts.token\" value=\"GPBGEE9TFVW1BQRD0NHJAFE98V4KFB6A\" />";
        Matcher matcher = ApacheS2023ScanTask.TOKEN_FIELD_PATTERN.matcher(page);
        if (matcher.find()) {
            System.out.println(matcher.group(0));
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
        }

        System.out.println(1L << 48 - 1);
        System.out.println(0x5DEECE66DL);

        //按位异或。比如二进制     1001 ^ 1100 = 0101
        System.out.println(8682522807148012L ^ System.nanoTime());
        //100 = 64 + 36 = 64 + 32 + 4 = 1100100
        //200 = 128 + 72 = 128 + 64 + 8 = 11001000
        //01100100
        //11001000
        //10101100 = 128 + 32 + 8 + 4 = 172
        System.out.println(100 ^ 200);

        //下面两个公式相等 12 % 8 = 1100 >> 3 = 1.100
        System.out.println(23L % (1L << 48));
        System.out.println(23L & ((1L << 48) - 1));

        //           11011001 10111011 11110011 11001001
        // (1) << 24
        long value = 94798787259797L;

        System.out.println(Long.toBinaryString(value));
        int reverseValue = 0x00000000;
        //Many
        System.out.println("value左移24位结果：" + Long.toBinaryString(value << 24));
        reverseValue |= value << 24 & 0xFF000000;
        System.out.println(Integer.toBinaryString(reverseValue));
        reverseValue |= value << 8 & 0xFF0000;
        System.out.println(Integer.toBinaryString(reverseValue));
        reverseValue |= value >> 8 & 0xFF00;
        System.out.println(Integer.toBinaryString(reverseValue));
        reverseValue |= value >> 24 & 0xFF;
        System.out.println(Integer.toBinaryString(reverseValue));

        //System.out.println(Long.toBinaryString(value));
        //value <<= 2;
        //System.out.println(Long.toBinaryString(value));

        Random random = new Random();
        BigInteger bigInteger = new BigInteger(165, random);
        System.out.println(bigInteger.bitLength());

        System.out.println(100 >> 2);
        System.out.println(-100 >> 2);

        //00000000 000000000 0000000 10000001
        int x = 129; //100000001 在byte中表示负数 取补加1 = -127
        System.out.println((byte) x);

        byte[] bytes = new byte[] { 14, -20, 99, 107, 30, 40, 30, 40 };
        System.out.println(ByteUtils.byte4ToInt(bytes, 0));

        int[] ints = ByteUtils.bytesToInt(bytes);
        System.out.println(ints);
    }

}
