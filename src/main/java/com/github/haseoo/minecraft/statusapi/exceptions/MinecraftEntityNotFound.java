package com.github.haseoo.minecraft.statusapi.exceptions;

import static com.github.haseoo.minecraft.statusapi.exceptions.ExceptionMessages.MINECRAFT_ENTITY_NOT_FOUND;

public class MinecraftEntityNotFound extends Exception {
    public MinecraftEntityNotFound() {
        super(MINECRAFT_ENTITY_NOT_FOUND);
    }
}
