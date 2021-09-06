package com.github.haseoo.minecraft.statusapi.controllers;

import com.github.haseoo.minecraft.statusapi.services.ServerStatusService;
import com.github.haseoo.minecraft.statusapi.views.ErrorView;
import com.github.haseoo.minecraft.statusapi.views.ServerInfoView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class StatusController {

    private final ServerStatusService serverStatusService;

    @GetMapping
    public ModelAndView getStatus(@RequestParam(defaultValue = "${minecraftserver.configuration.defaultHost}") String host,
                                  @RequestParam(defaultValue = "${minecraftserver.configuration.defaultPort}") int port,
                                  @RequestParam(defaultValue = "${minecraftserver.configuration.defaultName}") String serverName) {
        var model = new ModelAndView("status");
        model.addObject("serverName", serverName);
        try {
            var status = ServerInfoView.from(serverStatusService.getServerStatus(host, port, true));
            model.addObject("status", status);
        } catch (Exception e) {
            model.addObject("error", ErrorView.formException(e));
        }
        return model;
    }
}
