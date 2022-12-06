package com.service;

import java.io.IOException;

public interface FileService {
    void zipReader(String path, String filename);

    void jarReader(String path, String filename);

    void folderReader(String path);

    void zipShow(String path) throws IOException;
}
