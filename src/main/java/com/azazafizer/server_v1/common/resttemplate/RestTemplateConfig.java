package com.azazafizer.server_v1.common.resttemplate;

import com.azazafizer.server_v1.common.properties.AppProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;


@Configuration
@RequiredArgsConstructor
public class RestTemplateConfig {
    private final RestTemplateBuilder restTemplateBuilder;
    private final AppProperties properties;

    private RestTemplate restTemplate(final String endpoint) {
        return restTemplateBuilder.rootUri(endpoint)
                .additionalInterceptors(new RestTemplateClientHttpRequestInterceptor())
                .errorHandler(new RestTemplateErrorHandler())
                .setConnectTimeout(Duration.ofMinutes(3))
                .build();
    }

    @Bean
    public RestTemplate kakaoTemplate() {
        return restTemplate(properties.getKakaoServer());
    }

}
