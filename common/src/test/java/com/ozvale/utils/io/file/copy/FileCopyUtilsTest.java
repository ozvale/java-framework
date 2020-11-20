package com.ozvale.utils.io.file.copy;

import com.ozvale.utils.io.file.copy.FileCopyFilters;
import com.ozvale.utils.io.file.copy.FileCopyUtils;
import com.ozvale.utils.io.file.directory.DirectoryUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileCopyUtilsTest {
    @Test
    public void copyFile() throws Exception {
        File source = Files.createTempFile(null, null).toFile();
        Path directory = Files.createTempDirectory(null);
        File target = directory.resolve("abc").resolve("123").toFile();

        FileCopyUtils.copyFile(source, target);

        Assert.assertTrue(target.exists());
        Assert.assertEquals(target.length(), source.length());

        source.delete();
        DirectoryUtils.delete(directory.toFile());
    }

    @Test
    public void copyFileToDirectory() throws Exception {
        File source = Files.createTempFile(null, null).toFile();
        Path directory = Files.createTempDirectory(null);

        File target = FileCopyUtils.copyFileToDirectory(source, directory, FileCopyFilters.doNotCopyIfNameAndSizeEquals());
        long lastModified = target.lastModified();

        Assert.assertTrue(target.exists());
        Assert.assertEquals(target.length(), source.length());

        Thread.sleep(1);
        target = FileCopyUtils.copyFileToDirectory(source, directory, FileCopyFilters.doNotCopyIfNameAndSizeEquals());
        Assert.assertEquals(lastModified, target.lastModified());


        Thread.sleep(1);
        target = FileCopyUtils.copyFileToDirectory(source, directory);
        Assert.assertNotEquals(lastModified, target.lastModified());


        source.delete();
        DirectoryUtils.delete(directory.toFile());
    }


}