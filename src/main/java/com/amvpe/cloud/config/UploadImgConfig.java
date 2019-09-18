package com.amvpe.cloud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description 静态文件映射配置类
 * @author lidonghai
 * @Date 2019/9/16
 */
@Configuration
public class UploadImgConfig implements WebMvcConfigurer {

    /**
     * 文件映射路径，值来源于application.properties
     */
    @Value("${customize.mappingPath}")
    String mappingPath;

    /**
     * 静态资源路径， 在该路径下访问均为静态资源访问
     * 目前不打算支持自定义
     */
    String staticResourcesPattern = "/img/**";

    /**
     * 用于生成图片访问连接URL，具体见
     */
    public static String resourcesPattern = "/img/";

    /**
     * 配置静态资源访问路径
     * 在 staticResourcesPattern("/img/**")路径访问的均为静态资源,
     * 并映射到文件存储路径 mappingPath.
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(staticResourcesPattern).addResourceLocations(mappingPath);
    }
}
