package com.github.haseoo.minecraft.statusapi.controllers;

import com.github.haseoo.minecraft.statusapi.models.PingResponse;
import com.github.haseoo.minecraft.statusapi.repositories.ServerStatusRepositoryImpl;
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
    private final ServerStatusRepositoryImpl serverStatusRepository;

    @GetMapping
    public ResponseEntity<PingResponse> test() throws IOException {
        return ResponseEntity.ok(serverStatusRepository.fetchServerInfo());
    }
}
