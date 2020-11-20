package com.ozvale.utils.io.file.directory;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Consumer;

/**
 * 文件目录工具类
 *
 * @author ozvale
 */
public class DirectoryUtils {

    /**
     * 清空文件夹下的所有文件
     *
     * @param directory           要清空的文件夹
     * @param deleteRootDirectory 是否删除根目录
     */
    public static void clear(File directory, boolean deleteRootDirectory) {
        if (directory == null) return;
        if (!directory.exists()) return;
        if (!directory.isDirectory()) return;
        File[] files = directory.listFiles();
        if (files == null) return;
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if (file.isFile()) {
                file.delete();
                continue;
            }
            if (file.isDirectory()) {
                clear(file, true);
            }
        }
        if (deleteRootDirectory) {
            directory.delete();
        }
    }

    /**
     * 删除文件夹及子目录和文件
     *
     * @param directory
     */
    public static void delete(File directory) {
        clear(directory, true);
    }

    /**
     * 删除文件夹及子目录和文件
     *
     * @param directory
     */
    public static void delete(Path directory) {
        clear(directory.toFile(), true);
    }

    /**
     * 是否是空目录
     *
     * @param directory
     * @return
     */
    public static boolean isEmpty(File directory) {
        File[] files = directory.listFiles();
        return files == null || files.length == 0;
    }

    /**
     * 遍历文件夹下的所有文件(包含子目录)
     *
     * @param directory
     * @param consumeDirectory 如果是目录时，是否调用回调函数
     * @param consumer         回调函数
     */
    public static void foreachAllFiles(File directory, boolean consumeDirectory, Consumer<File> consumer) {
        if (directory == null) return;
        if (!directory.exists()) return;
        if (!directory.isDirectory()) return;
        File[] files = directory.listFiles();
        if (files == null) return;
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if (file.isFile()) {
                consumer.accept(file);
                continue;
            }
            if (file.isDirectory()) {
                if (consumeDirectory) {
                    consumer.accept(file);
                }
                foreachAllFiles(file, consumeDirectory, consumer);
            }
        }
    }

    /**
     * 列出指定目录下的所有文件
     *
     * @param directory
     * @param includeDirectory 是否也列出文件夹
     * @return
     */
    public static List<File> listAllFiles(File directory, boolean includeDirectory) {
        List<File> list = new ArrayList<>();
        foreachAllFiles(directory, includeDirectory, (file -> list.add(file)));
        return list;
    }

    /**
     * 获取指定目录的空间大小(Byte)
     *
     * @param directory
     * @return
     */
    public static long size(File directory) {
        LongAdder longAdder = new LongAdder();
        foreachAllFiles(directory, false, file -> {
            longAdder.add(file.length());
        });
        return longAdder.sum();
    }
}
