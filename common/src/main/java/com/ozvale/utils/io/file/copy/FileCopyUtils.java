package com.ozvale.utils.io.file.copy;

import com.ozvale.utils.io.ByteStreamUtils;
import com.ozvale.utils.io.file.FileUtils;
import com.ozvale.utils.io.file.copy.FileCopyFilter;

import java.io.*;
import java.nio.file.Path;

/**
 * 文件复制工具
 *
 * @author ozvale
 */
public class FileCopyUtils {

    /**
     * 复制文件到目标文件，如果目标文件不存在，则自动创建
     *
     * @param source
     * @param target
     * @param filter 为空时默认覆盖已存在的目标文件
     * @throws IOException
     */
    public static void copyFile(File source, File target, FileCopyFilter filter) throws IOException {
        if (!source.isFile() || !source.exists()) throw new FileNotFoundException();
        if (filter != null && !filter.accept(source, target)) return;
        FileUtils.createFileIfNotExists(target);
        ByteStreamUtils.execute(new FileInputStream(source), new FileOutputStream(target), true, true);
    }

    /**
     * 复制文件到目标文件，如果目标文件不存在，则自动创建，如果目标已存在，则覆盖
     *
     * @param source
     * @param target
     * @throws IOException
     */
    public static void copyFile(File source, File target) throws IOException {
        copyFile(source, target, null);
    }

    /**
     * 复制文件到指定目录,沿用相同的文件名，如果目标文件夹不存在，则自动创建
     *
     * @param source
     * @param directory
     * @param filter    为空时默认覆盖已存在的目标文件
     * @return 复制成功的文件
     * @throws IOException
     */
    public static File copyFileToDirectory(File source, Path directory, FileCopyFilter filter) throws IOException {
        File target = directory.resolve(source.getName()).toFile();
        copyFile(source, target, filter);
        return target;
    }

    /**
     * 复制文件到指定目录,沿用相同的文件名
     *
     * @param source
     * @param directory
     * @return
     * @throws IOException
     */
    public static File copyFileToDirectory(File source, Path directory) throws IOException {
        File target = directory.resolve(source.getName()).toFile();
        copyFile(source, target, null);
        return target;
    }
}
