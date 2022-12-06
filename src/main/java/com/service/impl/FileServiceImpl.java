package com.service.impl;

import com.service.FileService;
import com.util.FIlePathUtil;
import com.util.FileTreeUtil;
import com.util.UnzipUtil;

import java.io.IOException;
import java.nio.file.Paths;

public class FileServiceImpl implements FileService {

    @Override
    public void zipReader(String path, String filename) {
        String unzipPath = UnzipUtil.unzip(path, String.valueOf(Paths.get(FIlePathUtil.UNZIP_PATH, filename)));
        FileTreeUtil.getJsonByPath(unzipPath, filename);
    }

    @Override
    public void jarReader(String path, String filename) {
        String unJarPath = UnzipUtil.unJar(path, String.valueOf(Paths.get(FIlePathUtil.UNZIP_PATH, filename)));
        FileTreeUtil.getJsonByPath(unJarPath, filename);
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
