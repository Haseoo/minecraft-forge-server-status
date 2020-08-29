package com.github.haseoo.minecraft.statusapi.exceptions;

import java.io.IOException;

import static com.github.haseoo.minecraft.statusapi.exceptions.ExceptionMessages.INVALID_JSON_STRING_LENGTH;

public class InvalidJsonStringLength extends IOException {
    public InvalidJsonStringLength() {
        super(INVALID_JSON_STRING_LENGTH);
    }
}
