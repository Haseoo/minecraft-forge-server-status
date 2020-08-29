package com.github.haseoo.minecraft.statusapi.services;

import com.github.haseoo.minecraft.statusapi.exceptions.MinecraftEntityNotFound;
import com.github.haseoo.minecraft.statusapi.models.PingResponse;

import java.io.IOException;

public interface ServerStatusService {
    PingResponse getServerStatus(boolean normalize) throws IOException, MinecraftEntityNotFound;
}
