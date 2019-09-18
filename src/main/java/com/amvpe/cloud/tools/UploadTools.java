package com.amvpe.cloud.tools;

import com.amvpe.cloud.entity.FileType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 上传文件工具类
 * @author lidonghai
 */
public class UploadTools {

    private static final Logger upLoadToolsLogger = LoggerFactory.getLogger(UploadTools.class);

    /**
     * 对传入文件进行校验，针对篡改文件后缀名上传
     * 获取文件后缀名和实际文件二进制文件头看是否相符合
     * 定义文件类型详见 {@link FileType}
     * @param img 传入文件
     * @return true 校验成功， false 校验失败
     */
    public static boolean verifyImage(MultipartFile img) {

        //如果为传入img空直接返回false
        if (img == null) {
            upLoadToolsLogger.error("Img == null");
            return false;
        }

        // 获取文件后缀
        String contentType = img.getContentType();

        // 从文件二进制编码来获取文件类型
        String realContentType;

        byte[] imgByte = null;

        try {
            imgByte = img.getBytes();
        } catch (IOException e) {
            upLoadToolsLogger.error("img.getBytes() >> IOException");
            return false;
        }

        // 从文件头获取前3字节
        realContentType = "" + Integer.toHexString(imgByte[0] & 0xff) + Integer.toHexString(imgByte[1] & 0xff)
                + Integer.toHexString(imgByte[2] & 0xff);

        upLoadToolsLogger.info("contentType: " + contentType + ", realContentType: " + realContentType);

        // 判断文件类型和文件后缀是否符合
        boolean verified = FileType.fileType.get(contentType)[0].equalsIgnoreCase(realContentType);
        upLoadToolsLogger.info("文件类型匹配情况：" + verified);
        if (verified) {
            return true;
        }

        return false;
    }

    /**
     *
     * @param contentType 传入文件类型 String类型 例如 "image/jpeg"
     * @return 文件后缀名 例如 "jpg"
     */
    public static String fileSuffix(String contentType) {
        return FileType.fileType.get(contentType)[1];
    }

    /**
     * 获取远端客户端地址，ip等等信息
     * @param request
     */
    public static void ipAddress(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        String remoteHost = request.getRemoteHost();
    }
}
