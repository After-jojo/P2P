package com.wangc.p2p.oss.service;

import java.io.InputStream;

/**
 * @author After拂晓
 * @date 2022年05月20日 21:01
 */
public interface FileService {
    //上传文件至阿里云
    String upload(InputStream inputStream, String module, String fileName);


    void removeFile(String url);
}
