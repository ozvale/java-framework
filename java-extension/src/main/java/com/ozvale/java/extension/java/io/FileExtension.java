/**
 *
 */
package com.ozvale.java.extension.java.io;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * FileExtension
 * <p>
 * <Strong>Date: </Strong> 2015年4月10日 下午5:12:35
 *
 * @author Spartacus
 */
public class FileExtension {

    public static final String defaultEncoding = "utf-8";
    private File file;

    /**
     * FileExtension constructor
     *
     * @param pathname
     */
    public FileExtension(String path) {
        this.file = new File(path);
    }

    /**
     * FileExtension constructor
     *
     * @param uri
     */
    public FileExtension(File file) {
        this.file = file;
    }

    /**
     * 以utf-8编码读取文本
     * <p>
     * <h1>created by Spartacus at 2015年4月10日 下午6:17:18</h1>
     * <p>
     *
     * @return
     * @throws IOException
     */
    public static String readAll(File file) throws IOException {
        return readAll(file, defaultEncoding);
    }

    /**
     * 以utf-8编码读取文本
     * <p>
     * <h1>created by Spartacus at 2015年4月10日 下午6:17:18</h1>
     * <p>
     *
     * @return
     * @throws IOException
     */
    public static String readAll(String path) throws IOException {
        return readAll(path, defaultEncoding);
    }

    /**
     * 读取文本。使用指定编码格式
     * <p>
     * <h1>created by Spartacus at 2015年4月10日 下午6:17:18</h1>
     * <p>
     *
     * @return
     * @throws IOException
     */
    public static String readAll(String path, String encoding) throws IOException {
        return readAll(new File(path), encoding);
    }

    /**
     * 读取文本。使用指定编码格式
     * <p>
     * <h1>created by Spartacus at 2015年4月10日 下午6:17:18</h1>
     * <p>
     *
     * @return
     * @throws IOException
     */
    public static String readAll(File file, String encoding) throws IOException {
        FileInputStream fis = null;
        BufferedReader br = null;
        String fileContent = "";
        try {
            fis = new FileInputStream(file);
            br = new BufferedReader(new InputStreamReader(fis, encoding));
            String temp = "";
            while ((temp = br.readLine()) != null) {
                fileContent = fileContent + temp;
            }
        } catch (IOException e) {
            throw e;
        } finally {
            if (br != null) {
                br.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return fileContent;
    }

    /**
     * 写文件。使用默认的utf-8编码
     * <p>
     * <h1>created by Spartacus at 2015年4月30日 上午9:41:17</h1>
     * <p>
     *
     * @param file
     * @param content
     * @param append
     * @throws IOException
     */
    public static void write(File file, String content, boolean append) throws IOException {
        write(file, content, defaultEncoding, append);
    }

    /**
     * 写文件。使用指定编码
     * <p>
     * <h1>created by Spartacus at 2015年4月30日 上午9:41:17</h1>
     * <p>
     *
     * @param file
     * @param content
     * @param append
     * @throws IOException
     */
    public static void write(File file, String content, String charsetName, boolean append) throws IOException {
        FileOutputStream fos = null;
        BufferedWriter bw = null;
        try {
            if (!file.exists()) {
                createFile(file);
            }
            fos = new FileOutputStream(file, append);
            bw = new BufferedWriter(new OutputStreamWriter(fos, charsetName));
            bw.write(content);
            bw.flush();
        } catch (IOException e) {
            throw e;
        } finally {
            if (bw != null) {
                bw.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
    }

    public static void write(File file, InputStream inputStream) throws IOException {
        BufferedOutputStream bufferedOutputStream = null;
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            int read = 0;
            while ((read = bufferedInputStream.read()) != -1) {
                bufferedOutputStream.write(read);
            }
            bufferedOutputStream.close();
        } catch (IOException e) {
            bufferedOutputStream.close();
            throw e;
        }
    }

    /**
     * 根据路径创建文件
     * <p>
     * <h1>created by Spartacus at 2014年10月30日 上午9:28:29</h1>
     * <p>
     *
     * @param filePath
     * @throws IOException
     */
    public static boolean createFile(String filePath) throws IOException {
        File file = new File(filePath);
        return createFile(file);
    }

    /**
     * 根据路径创建文件
     * <p>
     * <h1>created by Spartacus at 2014年10月30日 上午9:28:29</h1>
     * <p>
     *
     * @param filePath
     * @throws IOException
     */
    public static boolean createFile(File file) throws IOException {
        if (file.exists() && file.isDirectory()) {
            throw new IOException("已存在同名的文件夹" + file.getName());
        }
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
        return file.createNewFile();
    }

    /**
     * 根据路径创建目录
     * <p>
     * <h1>created by Spartacus at 2014年10月30日 上午9:28:19</h1>
     * <p>
     *
     * @param directory
     */
    public static boolean createDirectory(String directory) {
        return createDirectory(new File(directory));
    }

    /**
     * 根据路径创建目录
     * <p>
     * <h1>created by Spartacus at 2014年10月30日 上午9:28:19</h1>
     * <p>
     *
     * @param directoryPath
     */
    public static boolean createDirectory(File directory) {
        if (directory.exists()) {
            if (directory.isFile())
                throw new RuntimeException("已存在同名的文件" + directory.getName());
            else
                return true;
        } else {
            return directory.mkdirs();
        }
    }

    /**
     * 删除文件或文件夹
     * <p>
     * <h1>created by Spartacus at 2014年10月20日 上午10:18:44</h1>
     * <p>
     *
     * @param path
     * @throws IOException
     */
    public static void delete(File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    File subFile = files[i];
                    delete(subFile.getAbsoluteFile());
                }
                file.delete();
            } else {
                file.delete();
            }
        }
    }

    /**
     * 根据拓展名读取文件夹下所有文件
     * <p>
     * <h1>created by Spartacus at 2015年4月10日 下午5:29:46</h1>
     * <p>
     *
     * @param directoryPath 文件夹
     * @param extension     拓展名(文件结尾)
     * @param deepFind      是否深度查找(子文件下的文件)
     * @return
     */
    public static List<File> getFilesByExtension(String directoryPath, final String extension, final boolean deepFind) {
        List<File> files = new ArrayList<File>();
        File _file = new File(directoryPath);
        FileFilter filefilter = new FileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.isDirectory()) {
                    return deepFind;
                } else {
                    return file.getName().endsWith(extension);
                }
            }
        };
        File[] _files = _file.listFiles(filefilter);
        for (int i = 0; i < _files.length; i++) {
            if (_files[i].isDirectory()) {
                files.addAll(getFilesByExtension(_files[i].getPath(), extension, deepFind));
            } else {
                files.add(_files[i]);
            }
        }
        return files;
    }

    /**
     * 复制文件
     * <p>
     * <h1>created by Spartacus at 2015年7月17日 上午10:14:38</h1>
     * <p>
     *
     * @param source
     * @param target
     * @throws IOException
     */
    public static void copyFile(File source, File target) throws IOException {
        // 保证目标是空文件
        if (target.exists()) {
            target.delete();
        }
        createFile(target);
        FileInputStream fi = null;
        FileOutputStream fo = null;
        try {
            fi = new FileInputStream(source);
            fo = new FileOutputStream(target);
            FileChannel in = fi.getChannel();// 得到对应的文件通道
            FileChannel out = fo.getChannel();// 得到对应的文件通道
            in.transferTo(0, in.size(), out);// 连接两个通道，并且从in通道读取，然后写入out通道
        } catch (IOException e) {
            throw e;
        } finally {
            if (fi != null) {
                fi.close();
            }
            if (fo != null) {
                fo.close();
            }
        }
    }

    /**
     * 复制目录。连同文件一起
     * <p>
     * <h1>created by Spartacus at 2015年7月17日 上午10:20:13</h1>
     * <p>
     *
     * @param source
     * @param target
     * @throws IOException
     */
    public static void copyDirectiory(String source, String target) throws IOException {
        // 新建目标目录
        createDirectory(target);
        // 获取源文件夹当下的文件或目录
        File[] file = (new File(source)).listFiles();
        for (int i = 0; i < file.length; i++) {
            if (file[i].isFile()) {
                // 源文件
                File sourceFile = file[i];
                // 目标文件
                File targetFile = new File(new File(target).getAbsolutePath() + File.separator + file[i].getName());
                copyFile(sourceFile, targetFile);
            }
            if (file[i].isDirectory()) {
                // 准备复制的源文件夹
                String dir1 = source + File.separator + file[i].getName();
                // 准备复制的目标文件夹
                String dir2 = target + File.separator + file[i].getName();
                copyDirectiory(dir1, dir2);
            }
        }
    }

    /**
     * 重命名文件
     * <p>
     * <h1>created by Spartacus at 2015年7月29日 下午7:36:31</h1>
     * <p>
     *
     * @param file 要重命名的文件
     * @param name 新的文件名(不是路径)
     * @return 命名后的文件
     */
    public static File rename(File file, String name) {
        if (file.getName().equalsIgnoreCase(name)) {
            return file;
        }
        if (file == null || !file.exists()) {
            throw new RuntimeException("要重命名的文件不存在.");
        }
        // 目标文件
        File target = file.toPath().getParent().resolve(name).toFile();
        if (target.exists()) {
            throw new RuntimeException("指定的文件名(" + name + ")已存在");
        }
        if (file.renameTo(target)) {
            return target;
        } else {
            throw new RuntimeException("文件重命名失败.");
        }
    }

    public static void main(String[] args) {
        FileExtension.rename(new File("e:\\test\\1.zip"), "");
    }

    /**
     * @return the file
     */
    public File getFile() {
        return file;
    }

    /**
     * 读取文件内容
     * <p>
     * <h1>created by Spartacus at 2014年10月20日 上午9:14:13</h1>
     * <p>
     *
     * @param filePath
     * @return
     * @throws Exception
     */
    public String read(String encoding) throws IOException {
        return readAll(file, encoding);
    }

    /**
     * 以utf-8编码读取文本
     * <p>
     * <h1>created by Spartacus at 2015年4月10日 下午6:17:18</h1>
     * <p>
     *
     * @return
     * @throws IOException
     */
    public String read() throws IOException {
        return readAll(file);
    }

    /**
     * 写文件
     * <p>
     * <h1>created by Spartacus at 2014年10月20日 上午9:57:55</h1>
     * <p>
     *
     * @param outputFile
     * @param content
     * @param encoding
     * @param isApend
     * @throws IOException
     */
    public void write(String content, String encoding, boolean append) throws IOException {
        write(file, content, encoding, append);
    }

    /**
     * 以utf-8编码写入文件
     * <p>
     * <h1>created by Spartacus at 2015年4月10日 下午6:33:43</h1>
     * <p>
     *
     * @param content
     * @param append
     * @throws IOException
     */
    public void write(String content, boolean append) throws IOException {
        write(file, content, append);

    }

    /**
     * 创建新文件.如果目录不存在则自动创建
     * <p>
     * <h1>created by Spartacus at 2014年10月20日 上午9:50:51</h1>
     * <p>
     *
     * @param file
     * @throws IOException
     */
    public FileExtension createFile() throws IOException {
        createFile(file);
        return this;
    }

    /**
     * 文件重命名
     * <p>
     * <h1>created by Spartacus at 2015年7月29日 下午7:42:38</h1>
     * <p>
     *
     * @param name
     * @return
     */
    public FileExtension rename(String name) {
        file = rename(file, name);
        return this;
    }

    /**
     * 创建目录
     * <p>
     * <h1>created by Spartacus at 2014年10月30日 上午9:24:56</h1>
     * <p>
     *
     * @param file
     */
    public boolean createDirectory() {
        return createDirectory(this.file);
    }
}
