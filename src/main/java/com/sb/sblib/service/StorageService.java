package com.sb.sblib.service;

import java.io.InputStream;
import java.util.List;

public interface StorageService {
    void upload(String bucket, String key, InputStream stream, long size, String contentType);
    InputStream download(String bucket, String key);
    List<String> listFiles(String bucket, String prefix);
    void delete(String bucket, String key);
}
