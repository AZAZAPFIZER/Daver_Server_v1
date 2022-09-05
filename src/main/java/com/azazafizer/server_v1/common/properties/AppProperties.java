package com.azazafizer.server_v1.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private String secret;
    private String refreshSecret;
    private long JWT_ACCESS_EXPIRE;
    private long JWT_REFRESH_EXPIRE;
}
