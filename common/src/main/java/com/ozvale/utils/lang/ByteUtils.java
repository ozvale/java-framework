package com.ozvale.utils.lang;

import java.text.DecimalFormat;

/**
 * 字节工具类
 *
 * @author ozvale
 */
public class ByteUtils {

    /**
     * 转换为可读的字节大小，入如1B,1KB,1MB,1GB
     *
     * @param size
     * @return
     */
    public static String toReadableText(long size) {
        DecimalFormat df = new DecimalFormat("#.00");
        String text = "";
        if (size <= 0) {
            return "0B";
        } else if (size < 1024) {
            return df.format((double) size) + "B";
        } else if (size < 1048576) {
            return df.format((double) size / 1024) + "KB";
        } else if (size < 1073741824) {
            return df.format((double) size / 1048576) + "MB";
        } else {
            return df.format((double) size / 1073741824) + "GB";
        }
    }
}
