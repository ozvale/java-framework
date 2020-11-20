package com.ozvale.utils.io.file.copy;

import java.io.File;

/**
 * 文件复制过滤器
 *
 * @author ozvale
 */
@FunctionalInterface
public interface FileCopyFilter {
    /**
     * 返回true时执行复制，否则不复制
     *
     * @param source
     * @param target
     * @return
     */
    boolean accept(File source, File target);
}
