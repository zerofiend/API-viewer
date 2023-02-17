package com.service;

import java.io.IOException;

public interface FileService {
    String zipReader(String path, String filename);

    String jarReader(String path, String filename);

    void folderReader(String path);

    void zipShow(String path) throws IOException;

    String javaShow(String path);

    String classShow(String path);
}
