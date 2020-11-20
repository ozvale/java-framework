package com.ozvale.utils.io.file;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;

public class FileUtilsTest {
    @Test
    public void createParentDirectoryIfNotExits() throws Exception {
        File file = Files.createTempDirectory(null).resolve("123").resolve("a.txt").toFile();
        FileUtils.createParentDirectoryIfNotExits(file);
        Assert.assertTrue(file.getParentFile().exists());
        file.getParentFile().delete();
        file.getParentFile().getParentFile().delete();
    }

    @Test
    public void createFileIfNotExists() throws Exception {
        File file = Files.createTempDirectory(null).resolve("123").resolve("a.txt").toFile();
        FileUtils.createFileIfNotExists(file);
        Assert.assertTrue(file.exists());
        file.delete();
        file.getParentFile().delete();
        file.getParentFile().getParentFile().delete();
    }

}