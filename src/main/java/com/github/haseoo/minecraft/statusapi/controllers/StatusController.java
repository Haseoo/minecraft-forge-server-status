package com.github.haseoo.minecraft.statusapi.controllers;

import com.github.haseoo.minecraft.statusapi.exceptions.MinecraftEntityNotFound;
import com.github.haseoo.minecraft.statusapi.services.ServerStatusService;
import com.github.haseoo.minecraft.statusapi.views.AbstractResponse;
import com.github.haseoo.minecraft.statusapi.views.ErrorView;
import com.github.haseoo.minecraft.statusapi.views.ServerInfoView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import static com.github.haseoo.minecraft.statusapi.utils.Constants.EXCEPTION_LOG_MESSAGE;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class StatusController {
    private final ServerStatusService serverStatusService;
    @Value("${minecraftserver.configuration.host}")
    private String serverName;

    @GetMapping
    public ResponseEntity<AbstractResponse> getServerInfo() {
        return getResponse(false);
    }

    @GetMapping("/fixed")
    public ResponseEntity<AbstractResponse> getServerInfoWithNormalizedFixedModList() {
        return getResponse(true);
    }

    private ResponseEntity<AbstractResponse> getResponse(boolean fix) {
        try {
            return ResponseEntity.ok(ServerInfoView.from(serverName, serverStatusService.getServerStatus(fix)));
        } catch (UnknownHostException | SocketTimeoutException | ConnectException | MinecraftEntityNotFound e) {
            return ResponseEntity.ok(ErrorView.formException(e));
        } catch (IOException e) {
            log.error(EXCEPTION_LOG_MESSAGE, e);
            return ResponseEntity.ok(ErrorView.defaultInstance());
        }
    }
}
