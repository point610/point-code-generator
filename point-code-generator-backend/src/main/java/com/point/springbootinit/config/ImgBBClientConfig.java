package com.point.springbootinit.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 腾讯云对象存储客户端
 */
@Component
@ConfigurationProperties(prefix = "imgbb")
@Data
public class ImgBBClientConfig {

    /**
     * api
     */
    private String api;
}