package com.service.impl;

import com.entity.FileOrFolder;
import com.frame.MainFrame;
import com.frame.center.right.text.ViewTextPane;
import com.service.FileService;
import com.util.FIlePathUtil;
import com.util.FileJSONUtil;
import com.util.UnzipUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class FileServiceImpl implements FileService {
    List<FileOrFolder> fileList;

    @Override
    public String zipReader(String path, String filename) {
        String unzipPath = UnzipUtil.unzip(path, filename);
//        JavaDocUtil.createDoc(Paths.get(FIlePathUtil.UNZIP_PATH, filename).toString());
        FileJSONUtil.getJsonByPath(unzipPath, filename);
        return unzipPath;
    }

    @Override
    public String jarReader(String path, String filename) {
        String unJarPath = UnzipUtil.unJar(path, String.valueOf(Paths.get(FIlePathUtil.UNZIP_PATH, filename)));
        FileJSONUtil.getJsonByPath(unJarPath, filename);
        return unJarPath;
    }

    @Override
    public void folderReader(String path) {
        FileJSONUtil.getJsonByPath(path, "folder");
    }

    @Override
    public void zipShow(String path) throws IOException {
        String s = UnzipUtil.ZIPShow(path);
    }

    @Override
    public void javaShow(String path) {
        String filePath = Paths.get(FIlePathUtil.UNZIP_PATH, path).toString();
        String docPath = Paths.get(FIlePathUtil.JAVADOC_PATH, path).toString();
        // 文件加载时动画
        MainFrame.createWait("正在加载文件，请稍后...");
        MainFrame.startWait();
        try {
            Runtime.getRuntime().exec("javadoc -d " + docPath + " -encoding utf-8 -charset utf-8 " + filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            File file = Paths.get(docPath, "index.html").toFile();
            while (!file.exists()) {
                file = Paths.get(docPath, "index.html").toFile();
            }
            String absolutePath = "file:" + file.getAbsolutePath();
            try {
                ViewTextPane.getViewTextPane().setPage(absolutePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        MainFrame.stopWait();
    }

    @Override
    public void classShow(String path) {
        String filePath = Paths.get("unzip", path).toString();
        filePath = filePath.replaceAll("\\\\", ".");
        filePath = filePath.substring(0, filePath.lastIndexOf("."));
        System.out.println(filePath);
        try {
            Class<?> aClass = Class.forName(filePath);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("未找到Class文件");
        }
    }
}
