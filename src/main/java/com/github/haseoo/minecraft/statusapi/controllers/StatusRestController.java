package com.github.haseoo.minecraft.statusapi.controllers;

import com.github.haseoo.minecraft.statusapi.exceptions.MinecraftEntityNotFound;
import com.github.haseoo.minecraft.statusapi.services.ServerStatusService;
import com.github.haseoo.minecraft.statusapi.views.AbstractResponse;
import com.github.haseoo.minecraft.statusapi.views.ErrorView;
import com.github.haseoo.minecraft.statusapi.views.ServerInfoView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class StatusRestController {
    private final ServerStatusService serverStatusService;

    @GetMapping
    public ResponseEntity<AbstractResponse>
    getServerInfo(@RequestParam(defaultValue = "${minecraftserver.configuration.defaultHost}") String host,
                  @RequestParam(defaultValue = "${minecraftserver.configuration.defaultPort}") int port) throws IOException {
        return getResponse(host, port, false);
    }

    @GetMapping("fixed")
    public ResponseEntity<AbstractResponse>
    getServerInfoWithNormalizedFixedModList(@RequestParam(defaultValue = "${minecraftserver.configuration.defaultHost}") String host,
                                            @RequestParam(defaultValue = "${minecraftserver.configuration.defaultPort}") int port)
            throws IOException {
        return getResponse(host, port, true);
    }

    private ResponseEntity<AbstractResponse> getResponse(String host, int port, boolean fix) throws IOException {
        try {
            return ResponseEntity.ok(ServerInfoView.from(serverStatusService.getServerStatus(host, port, fix)));
        } catch (UnknownHostException | SocketTimeoutException | ConnectException | MinecraftEntityNotFound e) {
            return ResponseEntity.ok(ErrorView.formException(e));
        }
    }
}
