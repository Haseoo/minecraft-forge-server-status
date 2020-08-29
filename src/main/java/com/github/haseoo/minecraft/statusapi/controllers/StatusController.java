package com.github.haseoo.minecraft.statusapi.controllers;

import com.github.haseoo.minecraft.statusapi.exceptions.MinecraftEntityNotFound;
import com.github.haseoo.minecraft.statusapi.services.ServerStatusService;
import com.github.haseoo.minecraft.statusapi.views.ServerInfoView;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${minecraftserver.configuration.host}")
    private String serverName;

    @GetMapping
    public ResponseEntity<ServerInfoView> getServerInfo() throws IOException, MinecraftEntityNotFound {
        return ResponseEntity.ok(ServerInfoView.from(serverName, serverStatusService.getServerStatus(false)));
    }

    @GetMapping("/fixed")
    public ResponseEntity<ServerInfoView> getServerInfoWithNormalizedFixedModList() throws IOException, MinecraftEntityNotFound {
        return ResponseEntity.ok(ServerInfoView.from(serverName, serverStatusService.getServerStatus(true)));
    }
}
