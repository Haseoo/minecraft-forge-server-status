package com.github.haseoo.minecraft.statusapi.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.haseoo.minecraft.statusapi.utils.helpers.ForgePing;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

@Configuration
@ConfigurationProperties(prefix = "minecraftserver.configuration")
@Setter
public class BeanConfiguration {
    private int timeout;
    private String host;
    private int port;
    private String allowedOrigin;

    @Bean
    public ForgePing forgePing() throws UnknownHostException {
        return new ForgePing(new InetSocketAddress(InetAddress.getByName(host), port), timeout);
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
