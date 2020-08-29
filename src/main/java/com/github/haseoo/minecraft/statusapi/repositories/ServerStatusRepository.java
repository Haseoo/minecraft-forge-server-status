package com.github.haseoo.minecraft.statusapi.repositories;

import com.github.haseoo.minecraft.statusapi.utils.helpers.ForgePing;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
@AllArgsConstructor
public class ServerStatusRepository {
    private final ForgePing helper;

    public String fetchServerInfo() throws IOException {
        return helper.ping();
    }
}
