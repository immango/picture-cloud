package com.amvpe.cloud.service.impl;

import com.amvpe.cloud.service.StorageImageService;
import com.amvpe.cloud.tools.UploadTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 图片存储接口实现
 * @author lidonghai
 */
@Service
public class StorageImageServiceImpl implements StorageImageService {
    private static final Logger storageImageServiceImplLogger = LoggerFactory.getLogger(StorageImageService.class);

    /**
     * 存储图片到路径， 图片重命名为UUID
     * @param img 传入的图片
     * @param filePath 存储路径
     * @return
     */
    @Override
    public String storageImage(MultipartFile img, String filePath) {

        //生成UUID图片名称
        String contentType = img.getContentType();
        String imgSuffix = UploadTools.fileSuffix(contentType);
        UUID imgUUID = UUID.randomUUID();
        String imgUUIDName = imgUUID + "." + imgSuffix;
        String imgPath = filePath + imgUUIDName;

        //转储图片
        try {
            img.transferTo(
                    new File(imgPath)
            );
        } catch (IOException e) {
            storageImageServiceImplLogger.error("img.transferTo 发生 IOException");
            return null;
        }
        storageImageServiceImplLogger.info("img重命名为: " + imgUUIDName);
        return imgUUIDName;
    }
}
