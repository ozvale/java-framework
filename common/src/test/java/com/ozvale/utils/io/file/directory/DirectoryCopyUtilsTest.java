package com.ozvale.utils.io.file.directory;

import com.ozvale.utils.io.file.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;

public class DirectoryCopyUtilsTest {
    @Test
    public void copyTo() throws Exception {
        Path directory = Files.createTempDirectory(null);
        Path sourceDirectory = directory.resolve("source");
        sourceDirectory.toFile().mkdirs();
        Path targetDirectory = directory.resolve("target");

        DirectoryCopyUtils.copyTo(sourceDirectory, targetDirectory, null);

        Assert.assertTrue(targetDirectory.resolve("source").toFile().exists());

        DirectoryUtils.delete(directory);
    }

    @Test
    public void copyAllFilesTo() throws Exception {
        Path directory = Files.createTempDirectory(null);
        Path sourceDirectory = directory.resolve("source");
        Path targetDirectory = directory.resolve("target");

        FileUtils.createFileIfNotExists(sourceDirectory.resolve("f1").toFile());
        FileUtils.createFileIfNotExists(sourceDirectory.resolve("d1").resolve("f1").toFile());

        DirectoryCopyUtils.copyAllFilesTo(sourceDirectory, targetDirectory, null);

        Assert.assertEquals(3, DirectoryUtils.listAllFiles(targetDirectory.toFile(), true).size());
        Assert.assertEquals(2, DirectoryUtils.listAllFiles(targetDirectory.toFile(), false).size());

        DirectoryUtils.delete(directory.toFile());
    }

}