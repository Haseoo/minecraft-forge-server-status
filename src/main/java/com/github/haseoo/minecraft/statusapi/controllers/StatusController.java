package com.github.haseoo.minecraft.statusapi.controllers;

import com.github.haseoo.minecraft.statusapi.exceptions.MinecraftEntityNotFound;
import com.github.haseoo.minecraft.statusapi.models.PingResponse;
import com.github.haseoo.minecraft.statusapi.services.ServerStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class StatusController {
    private final ServerStatusService serverStatusService;

    @GetMapping
    public ResponseEntity<PingResponse> getServerInfo() throws IOException, MinecraftEntityNotFound {
        return ResponseEntity.ok(serverStatusService.getServerStatus(false));
    }

    @GetMapping("/fixed")
    public ResponseEntity<PingResponse> getServerInfoWithNormalizedFixedModList() throws IOException, MinecraftEntityNotFound {
        return ResponseEntity.ok(serverStatusService.getServerStatus(true));
    }
}
