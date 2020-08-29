package com.github.haseoo.minecraft.statusapi.views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@Getter
@SuperBuilder
public abstract class AbstractResponse {
    private final Boolean online;
    private final String description;
}
