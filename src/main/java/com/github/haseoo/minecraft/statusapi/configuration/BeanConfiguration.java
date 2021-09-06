package com.github.haseoo.minecraft.statusapi.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.haseoo.minecraft.statusapi.utils.helpers.ForgePing;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConfigurationProperties(prefix = "minecraftserver.configuration")
@Setter
public class BeanConfiguration {
    private int timeout;
    private String allowedOrigin;

    @Bean
    public ForgePing forgePing() {
        return new ForgePing(timeout);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/*").allowedOrigins(allowedOrigin);
            }
        };
    }
}
