package com.util;

import java.io.File;
import java.io.IOException;

/**
 * @Description TODO JavaDocUtil 生成Javadoc工具类
 * @Author ZFiend
 * @Create 2023.02.12 22:10
 */
public class JavaDocUtil {
    public static void createDoc(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file2 : files) {
                createDoc(file2.getPath());
            }
        } else {
            String docPath = path.replace(FIlePathUtil.UNZIP_PATH, FIlePathUtil.JAVADOC_PATH);
            try {
                Runtime.getRuntime().exec("javadoc -d " + docPath + " -encoding utf-8 -charset utf-8 " + path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
