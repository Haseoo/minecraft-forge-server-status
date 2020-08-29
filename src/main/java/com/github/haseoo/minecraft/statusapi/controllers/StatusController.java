package com.github.haseoo.minecraft.statusapi.controllers;

import com.github.haseoo.minecraft.statusapi.exceptions.MinecraftEntityNotFound;
import com.github.haseoo.minecraft.statusapi.services.ServerStatusService;
import com.github.haseoo.minecraft.statusapi.views.AbstractResponse;
import com.github.haseoo.minecraft.statusapi.views.ErrorView;
import com.github.haseoo.minecraft.statusapi.views.ServerInfoView;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class StatusController {
    private final ServerStatusService serverStatusService;
    @Value("${minecraftserver.configuration.host}")
    private String serverName;

    @GetMapping
    public ResponseEntity<AbstractResponse> getServerInfo() throws IOException {
        return getResponse(false);
    }

    @GetMapping("/fixed")
    public ResponseEntity<AbstractResponse> getServerInfoWithNormalizedFixedModList() throws IOException {
        return getResponse(true);
    }

    private ResponseEntity<AbstractResponse> getResponse(boolean fix) throws IOException {
        try {
            return ResponseEntity.ok(ServerInfoView.from(serverName, serverStatusService.getServerStatus(fix)));
        } catch (UnknownHostException | SocketTimeoutException | ConnectException | MinecraftEntityNotFound e) {
            return ResponseEntity.ok(ErrorView.formException(e));
        }
    }
}
