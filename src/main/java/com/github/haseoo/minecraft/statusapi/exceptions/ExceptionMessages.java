package com.github.haseoo.minecraft.statusapi.exceptions;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ExceptionMessages {
    public static final String INVALID_INT_LENGTH = "Integer value is more that 4 bytes long!";
    public static final String INVALID_PACKET_ID = "Invalid packetID";
    public static final String INVALID_JSON_STRING_LENGTH = "JSON string length is zero, so response was empty!";
    public static final String MINECRAFT_ENTITY_NOT_FOUND = "Could not fix the mod list. The minecraft mod entity not found on the list!";
}
