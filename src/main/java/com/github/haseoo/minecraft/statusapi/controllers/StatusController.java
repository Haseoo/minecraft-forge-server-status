package com.github.haseoo.minecraft.statusapi.controllers;

import com.github.haseoo.minecraft.statusapi.repositories.ServerStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class StatusController {
    private final ServerStatusRepository serverStatusRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String test() throws IOException {
        return serverStatusRepository.fetchServerInfo();
    }
}
