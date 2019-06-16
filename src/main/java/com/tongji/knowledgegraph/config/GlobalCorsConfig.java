package com.tongji.knowledgegraph.config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author MT
 * @version 0.1
 */
@Configuration
@Slf4j
public class GlobalCorsConfig implements WebMvcConfigurer{
    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        System.out.println("[INFO]From MT: " + "跨域访问配置..");
        log.info("跨域访问配置");
        registry.addMapping("/**").allowedHeaders("*").allowedMethods("*").allowedOrigins("*");
    }
}

