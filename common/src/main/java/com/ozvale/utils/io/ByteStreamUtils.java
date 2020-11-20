package com.ozvale.utils.io;

import com.ozvale.utils.exception.Try;

import java.io.*;

/**
 * 字节流工具类
 *
 * @author ozvale
 */
public class ByteStreamUtils {


    /**
     * 读取输入流，写入输出流
     *
     * @param inputStream
     * @param outputStream
     * @param closeInputStream
     * @param closeOutputStream
     * @throws IOException
     */
    public static void execute(InputStream inputStream, OutputStream outputStream, boolean closeInputStream, boolean closeOutputStream) throws IOException {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            while (true) {
                int read = bufferedInputStream.read();
                if (read == -1) break;
                bufferedOutputStream.write(read);
            }
            bufferedOutputStream.flush();
        } finally {
            if (closeInputStream) {
                Try.execute(() -> inputStream.close());
            }
            if (closeOutputStream) {
                Try.execute(() -> outputStream.close());
            }
        }
    }
}
