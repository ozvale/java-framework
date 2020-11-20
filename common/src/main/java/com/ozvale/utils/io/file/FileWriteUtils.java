package com.ozvale.utils.io.file;

import com.ozvale.utils.io.ByteStreamUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 文件写入工具类
 *
 * @author ozvale
 */
public class FileWriteUtils {

    public static final String DEFAULT_ENCODING = "utf-8";
    public static final Charset DEFAULT_CHARSET = Charset.forName(DEFAULT_ENCODING);

    /**
     * 写入字符串到文件，如果文件不存在，则自动创建
     *
     * @param file
     * @param content
     * @param charset
     * @throws IOException
     */
    public static void writeAsString(File file, String content, Charset charset) throws IOException {
        FileUtils.createFileIfNotExists(file);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(content.getBytes(charset));
        ByteStreamUtils.execute(byteArrayInputStream, new FileOutputStream(file), true, true);
    }

    /**
     * 以默认编码写入字符串到文件
     *
     * @param file
     * @param content
     * @throws IOException
     * @see DEFAULT_CHARSET
     */
    public static void writeAsString(File file, String content) throws IOException {
        writeAsString(file, content, DEFAULT_CHARSET);
    }


}
