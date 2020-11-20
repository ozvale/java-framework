package com.ozvale.utils.io.file.copy;

/**
 * 文件复制过滤器预定义
 *
 * @author ozvale
 */
public class FileCopyFilters {
    /**
     * 如果文件名称和大小都相等,则不复制
     *
     * @return
     */
    public static FileCopyFilter doNotCopyIfNameAndSizeEquals() {
        return (source, target) -> {
            if (!target.exists()) return true;
            if (!source.getName().equals(target.getName())) return true;
            return target.length() != source.length();
        };
    }
}
