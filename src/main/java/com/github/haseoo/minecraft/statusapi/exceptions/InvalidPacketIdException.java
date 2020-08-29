package com.github.haseoo.minecraft.statusapi.exceptions;

import java.io.IOException;

import static com.github.haseoo.minecraft.statusapi.exceptions.ExceptionMessages.INVALID_PACKET_ID;

public class InvalidPacketIdException extends IOException {
    public InvalidPacketIdException() {
        super(INVALID_PACKET_ID);
    }
}
