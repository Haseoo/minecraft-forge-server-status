package com.github.haseoo.minecraft.statusapi.repositories;

import com.github.haseoo.minecraft.statusapi.utils.helper.ForgePing;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

@Repository
public class ServerStatusRepository {
    @Value("${minecraftserver.configuration.timeout}")
    private int timeout;
    @Value("${minecraftserver.configuration.host}")
    private String host;
    @Value("${minecraftserver.configuration.port}")
    private int port;

    public String fetchServerInfo() throws IOException {
        var helper = new ForgePing(new InetSocketAddress(InetAddress.getByName(host), port), timeout);
        return helper.ping();
    }
}
