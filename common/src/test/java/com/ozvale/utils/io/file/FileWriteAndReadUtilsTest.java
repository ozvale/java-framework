package com.ozvale.utils.io.file;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;

public class FileWriteAndReadUtilsTest {
    @Test
    public void writeStringThenRead() throws Exception {
        File file = Files.createTempFile(null, null).toFile();
        String content = "中文en123';";
        FileWriteUtils.writeAsString(file, content);
        Assert.assertEquals(content, FileReadUtils.readAsString(file));
        FileWriteUtils.writeAsString(file, content);
        Assert.assertEquals(content, FileReadUtils.readAsString(file));
    }
}