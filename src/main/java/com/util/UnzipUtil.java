package com.util;

import com.alibaba.fastjson.JSON;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * @Description TODO UnzipUtil 解压工具类
 * @Author ZFiend
 * @Create 2023.02.05 19:10
 */
public class UnzipUtil {
    // 缓存大小
    private static final int BUFFER_SIZE = 4096;

    /**
     * @description: TODO [unzip] 解压 zip 压缩包
     * @author: ZFiend
     * @date: 2023/2/5 19:16
     * @param: path
     * @param: unzipPath
     * @return: java.lang.String
     */
    public static String unzip(String path, String filename) {
        String unzipPath = String.valueOf(Paths.get(FIlePathUtil.UNZIP_PATH, filename));
        boolean isExist = createFolder(path, unzipPath);
        if (isExist) {
            ZipInputStream zipIn = null;
            ZipEntry entry = null;
            try {
                zipIn = new ZipInputStream(Files.newInputStream(Paths.get(path)));
                entry = zipIn.getNextEntry();        // iterates over entries in the zip file
                while (entry != null) {
                    String filePath = unzipPath + File.separator + entry.getName();
                    if (!entry.isDirectory()) {
                        // if the entry is a file, extracts it
                        File dir = new File(filePath);
                        File parentDir = dir.getParentFile();
                        if (!parentDir.exists()) {
                            parentDir.mkdirs();
                        }
                        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
                        byte[] bytesIn = new byte[BUFFER_SIZE];
                        int read = 0;
                        while ((read = zipIn.read(bytesIn)) != -1) {
                            bos.write(bytesIn, 0, read);
                        }
                        bos.close();
                    } else {
                        // if the entry is a directory, make the directory
                        File dir = new File(filePath);
                        dir.mkdir();
                    }
                    zipIn.closeEntry();
                    entry = zipIn.getNextEntry();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                zipIn.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("文件解压成功");
        return unzipPath;
    }

    /**
     * @description: TODO [unJar] 解压 Jar 压缩包
     * @author: ZFiend
     * @date: 2023/2/5 19:16
     * @param: path
     * @param: unzipPath
     * @return: java.lang.String
     */
    public static String unJar(String path, String unzipPath) {
        File file = new File(path);
        boolean isExist = createFolder(path, unzipPath);
        if (isExist) {
            try {
                JarFile jar = new JarFile(file);
                for (Enumeration<JarEntry> enums = jar.entries(); enums.hasMoreElements(); ) {
                    JarEntry entry = (JarEntry) enums.nextElement();

                    String fileName = unzipPath + File.separator + entry.getName();
                    File f = new File(fileName);
                    if (!f.isDirectory()) {
                        File parentDir = f.getParentFile();
                        if (!parentDir.exists()) {
                            parentDir.mkdirs();
                        }
                    } else {
                        File dir = new File(fileName);
                        dir.mkdir();
                    }
                }
                for (Enumeration<JarEntry> enums = jar.entries(); enums.hasMoreElements(); ) {
                    JarEntry entry = (JarEntry) enums.nextElement();

                    String fileName = unzipPath + File.separator + entry.getName();
                    File f = new File(fileName);

                    if (!fileName.endsWith("/")) {
                        InputStream is = jar.getInputStream(entry);
                        FileOutputStream fos = new FileOutputStream(f);
                        BufferedOutputStream bos = new BufferedOutputStream(fos);
                        byte[] bytesIn = new byte[BUFFER_SIZE];
                        int read = 0;
                        while ((read = is.read(bytesIn)) != -1) {
                            bos.write(bytesIn, 0, read);
                        }
                        bos.close();
                        fos.close();
                        is.close();
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("文件解压成功");
        return unzipPath;
    }

    /**
     * @description: TODO [createFolder] 创建压缩包根目录
     * @author: ZFiend
     * @date: 2023/2/5 19:17
     * @param: path
     * @param: unzipPath
     * @return: boolean
     */
    private static boolean createFolder(String path, String unzipPath) {
        File file = new File(path);
        String name = file.getName();
//        unzipPath = String.valueOf(Paths.get(unzipPath, name));
        File destDir = new File(unzipPath);
        if (!destDir.exists()) {
            destDir.mkdir();
        } else {
            return false;
        }
        return true;
    }

    public static String ZIPShow(String path) throws IOException {
        ZipFile zip = new ZipFile(path);
        InputStream in = new BufferedInputStream(Files.newInputStream(Paths.get(path)));
        Charset gbk = Charset.forName("gbk");
        ZipInputStream zin = new ZipInputStream(in, gbk);
        ZipEntry ze;
        List lists = new ArrayList();
        while ((ze = zin.getNextEntry()) != null) {
//这里是去掉一个空的文件夹
            if (ze.getSize() > 0) {
                String zeToString = ze + "";
//截取掉最后的名字进行展示
                zeToString = zeToString.substring(zeToString.lastIndexOf("/") + 1);
                lists.add(zeToString);
            }
        }
        zin.closeEntry();
        return JSON.toJSONString(lists);
    }
}
