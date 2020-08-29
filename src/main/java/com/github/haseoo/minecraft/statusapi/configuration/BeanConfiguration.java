package com.github.haseoo.minecraft.statusapi.configuration;

import com.github.haseoo.minecraft.statusapi.utils.helpers.ForgePing;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

@Configuration
public class BeanConfiguration {
    @Value("${minecraftserver.configuration.timeout}")
    private int timeout;
    @Value("${minecraftserver.configuration.host}")
    private String host;
    @Value("${minecraftserver.configuration.port}")
    private int port;

    @Bean
    public ForgePing forgePing() throws UnknownHostException {
        return new ForgePing(new InetSocketAddress(InetAddress.getByName(host), port), timeout);
    }
}
