package com.amvpe.cloud.tools;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author 80261307
 * Host工具类，用于初始化来自于application.properties配置文件的host值，防止host值为空
 * 初始化取值均可这种方法
 */
@Component
public class Init {
    private String host;

    /**
     * 初始化来自于配置文件的Host值
     * @param host
     */
    public Init(@Value("${customize.host}") String host) {
        this.host = host;
    }

    public String getHost() {
        return host;
    }
}
