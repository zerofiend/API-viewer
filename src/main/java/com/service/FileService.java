package com.service;

import java.io.IOException;

public interface FileService {
    void zipReader(String path);

    void jarReader(String path);

    void folderReader(String path);

    void zipShow(String path) throws IOException;
}
