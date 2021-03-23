package com.malefeng.mychat.util;

import java.security.MessageDigest;

/**
 * @ClassName: StringUtil.java
 * @Description: string处理工具类
 * @author paradise
 * @date 2019年8月30日
 */
public class StringUtil {
    /**
     * 生成 MD5
     *
     * @param data 待处理数据
     * @return MD5结果
     */
    public static String MD5(String data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }
}
