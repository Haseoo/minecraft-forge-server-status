package com.github.haseoo.minecraft.statusapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class StatusapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(StatusapiApplication.class, args);
    }

}
