package com.ozvale.utils.io.file.directory;

import com.ozvale.utils.io.file.directory.DirectoryUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirectoryUtilsTest {


    @Test
    public void clear() throws Exception {
        File directory = prepareDirectory();
        Assert.assertTrue(directory.exists());
        Assert.assertTrue(!DirectoryUtils.isEmpty(directory));

        DirectoryUtils.clear(directory, false);
        Assert.assertTrue(DirectoryUtils.isEmpty(directory));

        DirectoryUtils.clear(directory, true);
        Assert.assertTrue(!directory.exists());

    }

    @Test
    public void delete() throws Exception {
        File directory = prepareDirectory();
        Assert.assertTrue(directory.exists());
        Assert.assertTrue(!DirectoryUtils.isEmpty(directory));

        DirectoryUtils.delete(directory);
        Assert.assertTrue(!directory.exists());
    }


    public File prepareDirectory() throws Exception {
        Path directory = Files.createTempDirectory(null);
        directory.resolve("f1").toFile().createNewFile();
        Path d1 = directory.resolve("d1");
        d1.toFile().mkdirs();
        d1.resolve("d1f1").toFile().createNewFile();
        return directory.toFile();
    }
}