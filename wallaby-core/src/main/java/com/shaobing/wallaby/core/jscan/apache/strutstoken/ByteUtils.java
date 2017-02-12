package com.shaobing.wallaby.core.jscan.apache.strutstoken;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

/**
 * 字节工具
 *
 * @author luyb@servyou.com.cn
 * @version $Id: ByteUtils.java v 0.1 2016/12/31 21:24 luyb Exp $$
 */
public class ByteUtils {

    /**
     * 字节数组转换成整数
     *
     * @param bytes 字节数组
     * @param off   字节数组偏移量
     * @return int
     */
    public static int byte4ToInt(byte[] bytes, int off) {
        int b0 = bytes[off] & 0xFF;
        int b1 = bytes[off + 1] & 0xFF;
        int b2 = bytes[off + 2] & 0xFF;
        int b3 = bytes[off + 3] & 0xFF;
        return (b0 << 24) | (b1 << 16) | (b2 << 8) | b3;
    }

    /**
     * 字节数组转换成整数数组
     *
     * @param bytes 字节数组
     * @return  int[]
     */
    public static int[] bytesToInt(byte[] bytes) {
        IntBuffer intBuf = ByteBuffer.wrap(bytes).order(ByteOrder.BIG_ENDIAN).asIntBuffer();
        int[] array = new int[intBuf.remaining()];
        intBuf.get(array);
        return array;
    }


    /**
     * 指定进制的字符串 转换成byte数组
     *
     * @param hexValue  值
     * @param radix 进制
     * @return byte[]
     */
    public static byte[] bigIntToByte(String hexValue, int radix) {
        return new BigInteger(hexValue, 36).toByteArray();
    }



}
