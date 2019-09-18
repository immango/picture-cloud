package com.amvpe.cloud.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * 标识文件类型以及文件后缀
 * @author lidonghai
 */

public class FileType {

    /**
     * 创建并初始化文件类型
     * Map第一个参数是文件类型
     * Map第二个参数是一个字符数组：
     * 第一个值为对应文件类型的文件头前三个字节
     * 第二个值为文件类型对应的文件后缀
     */
    public static final Map<String,String[]> fileType;

    static {
        fileType = new HashMap<String, String[]>();

        // 标识 image/jpeg 文件类型； jpeg文件类型文件头前三字节为 FFD8FF，文件后缀标识为jpg. 其余类型类似
        fileType.put("image/jpeg", new String[]{"FFD8FF", "jpg"});

        // png文件类型
        fileType.put("image/png", new String[]{"89504E", "png"});

        // gif文件类型
        fileType.put("image/gif", new String[]{"474946", "gif"});
    }
}
