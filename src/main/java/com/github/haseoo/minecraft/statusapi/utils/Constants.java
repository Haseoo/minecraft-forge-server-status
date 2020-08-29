package com.github.haseoo.minecraft.statusapi.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {
    public final int HANDSHAKE_PACKET_ID = 0;
    public final int HANDSHAKE_PROTOCOL_VERSION = 4;
    public final int HANDSHAKE_STATE = 1;
    public final int PREMATURE_END_OF_STREAM_RESPONSE_VALUE = -1;
    public final int POSTJOB_PACKET_ID = 1;
    public final int VALID_PING_RESPONSE = 0;
    public final int POSTJOB_PACKET_SIZE = 9;
    public final int PING_PACKET_ID = 0;
    public final int PING_PACKET_SIZE = 1;

    public static final String MINECRAFT_ENTITY_ID = "minecraft";
    public static final String NO_MOD_VERSION_STRING_INDICATOR_1 = "version";
    public static final String NO_MOD_VERSION_STRING_INDICATOR_2 = "ANY";
    public static final String FIX_MOD_VERSION_FORMAT = "For minecraft version %s";

    public static final String DEFAULT_ERROR_MESSAGE = "Internal api server error, see logs";
    public static final String EXCEPTION_LOG_MESSAGE = "An exception has occurred";
}
