package com.github.haseoo.minecraft.statusapi.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.haseoo.minecraft.statusapi.models.PingResponse;
import com.github.haseoo.minecraft.statusapi.utils.helpers.ForgePing;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
@AllArgsConstructor
class ServerStatusRepositoryImpl implements ServerStatusRepository {
    private final ForgePing helper;
    private final ObjectMapper mapper;

    public PingResponse fetchServerInfo(String hostName, int port) throws IOException {
        return mapper.readValue(helper.ping(hostName, port), PingResponse.class);
    }
}
