package com.github.haseoo.minecraft.statusapi.repositories;

import com.github.haseoo.minecraft.statusapi.models.PingResponse;

import java.io.IOException;

public interface ServerStatusRepository {
    PingResponse fetchServerInfo() throws IOException;
}
