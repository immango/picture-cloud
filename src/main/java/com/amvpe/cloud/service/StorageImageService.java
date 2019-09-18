package com.amvpe.cloud.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author lidonghai
 */
public interface StorageImageService {

    /**
     * 将上传的图片存储到本地
     * @param img 传入的图片
     * @param filePath 存储路径
     * @return 存储成功返回文件名，不成功返回null
     */
    String storageImage(MultipartFile img, String filePath);
}
