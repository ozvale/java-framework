package com.ozvale.utils.io.file;

import com.ozvale.utils.io.ByteStreamUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 文件读取工具类
 *
 * @author ozvale
 */
public class FileReadUtils {

    public static final String DEFAULT_ENCODING = "utf-8";
    public static final Charset DEFAULT_CHARSET = Charset.forName(DEFAULT_ENCODING);

    /**
     * 以字符串形式读取所有内容
     *
     * @param file
     * @param charset
     * @return
     * @throws IOException
     */
    public static String readAsString(File file, Charset charset) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ByteStreamUtils.execute(new FileInputStream(file), byteArrayOutputStream, true, true);
        return new String(byteArrayOutputStream.toByteArray(), charset);
    }

    /**
     * 以字符串形式读取所有内容
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static String readAsString(File file) throws IOException {
        return readAsString(file, DEFAULT_CHARSET);
    }
}
