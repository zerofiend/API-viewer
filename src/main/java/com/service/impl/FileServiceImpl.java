package com.service.impl;

import com.service.FileService;
import com.util.FileTreeUtil;
import com.util.UnzipUtil;

import java.io.IOException;

public class FileServiceImpl implements FileService {
    private final String unzipPath = "src/main/resources/unzip";

    @Override
    public void zipReader(String path) {
        String unzipPath = UnzipUtil.unzip(path, this.unzipPath);
        FileTreeUtil.getJsonByPath(unzipPath, "zip");
    }

    @Override
    public void jarReader(String path) {
        String unJarPath = UnzipUtil.unJar(path, this.unzipPath);
        FileTreeUtil.getJsonByPath(unJarPath, "jar");
    }

    @Override
    public void folderReader(String path) {
        FileTreeUtil.getJsonByPath(path, "folder");
    }

    @Override
    public void zipShow(String path) throws IOException {
        String s = UnzipUtil.ZIPShow(path);
        System.out.println(s);
    }
}
