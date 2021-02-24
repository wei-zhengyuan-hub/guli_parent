package com.atguigu.oss.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    /**
     * 文件上传至阿里云
     * @param file 上传文件
     * @return 返回访问地址
     */
    String upload(MultipartFile file);

}
