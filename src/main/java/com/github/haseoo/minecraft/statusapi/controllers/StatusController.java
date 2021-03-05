package com.github.haseoo.minecraft.statusapi.controllers;

import com.github.haseoo.minecraft.statusapi.exceptions.MinecraftEntityNotFound;
import com.github.haseoo.minecraft.statusapi.services.ServerStatusService;
import com.github.haseoo.minecraft.statusapi.views.ErrorView;
import com.github.haseoo.minecraft.statusapi.views.ServerInfoView;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class StatusController {

    private final ServerStatusService serverStatusService;
    @Value("${minecraftserver.configuration.name}")
    private String serverName;

    @GetMapping
    public String getStatus(Model model) throws IOException {
        model.addAttribute("serverName", serverName);
        try {
            var status = ServerInfoView.from(serverName, serverStatusService.getServerStatus(true));
            model.addAttribute("status", status);
        } catch (UnknownHostException | SocketTimeoutException | ConnectException | MinecraftEntityNotFound e) {
            model.addAttribute("error", ErrorView.formException(e));
        }
        return "status";
    }
}
