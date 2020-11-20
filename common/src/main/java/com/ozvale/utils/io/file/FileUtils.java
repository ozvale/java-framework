package com.ozvale.utils.io.file;

import java.io.File;
import java.io.IOException;

/**
 * 文件工具类
 *
 * @author ozvale
 */
public class FileUtils {

    /**
     * 如果文件的父级目录不存在,则创建
     *
     * @param file
     */
    public static boolean createParentDirectoryIfNotExits(File file) {
        File parentFile = file.getParentFile();
        if (parentFile.exists()) return true;
        return parentFile.mkdirs();
    }

    /**
     * 如果文件不存在,则创建,如果父目录不存在,也一并创建
     *
     * @param file
     */
    public static boolean createFileIfNotExists(File file) throws IOException {
        if (file.exists()) return true;
        if (!createParentDirectoryIfNotExits(file)) return false;
        return file.createNewFile();
    }
}
