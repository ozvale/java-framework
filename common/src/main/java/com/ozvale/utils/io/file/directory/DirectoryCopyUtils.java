package com.ozvale.utils.io.file.directory;

import com.ozvale.utils.io.file.copy.FileCopyFilter;
import com.ozvale.utils.io.file.copy.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * 文件目录复制工具类
 *
 * @author ozvale
 */
public class DirectoryCopyUtils {

    /**
     * 复制源文件目录下的所有文件和目录至目标目录下
     *
     * @param sourceDirectory
     * @param targetDirectory
     * @param fileCopyFilter  文件复制过滤器，为空时复制所有文件并覆盖已存在的文件
     * @throws IOException
     */
    public static void copyAllFilesTo(Path sourceDirectory, Path targetDirectory, FileCopyFilter fileCopyFilter) throws IOException {
        File sourceDirectoryFile = sourceDirectory.toFile();
        if (!sourceDirectoryFile.exists()) return;
        if (!sourceDirectoryFile.isDirectory()) return;
        File targetDirectoryFile = targetDirectory.toFile();
        if (!targetDirectoryFile.exists()) targetDirectoryFile.mkdirs();
        if (!targetDirectoryFile.isDirectory())
            throw new RuntimeException("copy target is not a directory:" + targetDirectory.toString());
        File[] files = sourceDirectoryFile.listFiles();
        if (files == null) return;
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if (file.isFile()) {
                FileCopyUtils.copyFileToDirectory(file, targetDirectory, fileCopyFilter);
                continue;
            }
            if (file.isDirectory()) {
                copyAllFilesTo(file.toPath(), targetDirectory.resolve(file.getName()), fileCopyFilter);
            }
        }
    }

    /**
     * 复制源文件目录至目标目录下
     *
     * @param sourceDirectory
     * @param targetDirectory
     * @param fileCopyFilter  文件复制过滤器，为空时复制所有文件并覆盖已存在的文件
     * @return 复制成功的文件夹
     * @throws IOException
     */
    public static Path copyTo(Path sourceDirectory, Path targetDirectory, FileCopyFilter fileCopyFilter) throws IOException {
        Path target = targetDirectory.resolve(sourceDirectory.getFileName());
        copyAllFilesTo(sourceDirectory, target, fileCopyFilter);
        return target;
    }
}
