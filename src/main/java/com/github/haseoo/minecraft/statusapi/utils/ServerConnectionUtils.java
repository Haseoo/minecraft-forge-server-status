package com.github.haseoo.minecraft.statusapi.utils;

import lombok.experimental.UtilityClass;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import static com.github.haseoo.minecraft.statusapi.exceptions.ExceptionMessages.INVALID_INT_LENGTH;

@UtilityClass
public class ServerConnectionUtils {
    public static int readIntegerValue(DataInputStream in) throws IOException {
        var returnValue = 0;
        var byteCount = 0;
        int intBuffer;
        do {
            intBuffer = in.readByte();
            returnValue |= (intBuffer & Byte.MAX_VALUE) << byteCount++ * (Byte.SIZE - 1);
            if (byteCount > Byte.SIZE - 2) {
                throw new IllegalStateException(INVALID_INT_LENGTH);
            }
        } while ((intBuffer & Byte.MAX_VALUE + 1) == Byte.MAX_VALUE + 1);
        return returnValue;
    }

    public static void writeIntValue(DataOutputStream out, int value) throws IOException {
        while ((value & Byte.MIN_VALUE) != 0) {
            out.writeByte(value & Byte.MAX_VALUE | Byte.MAX_VALUE + 1);
            value >>>= Byte.SIZE - 1;
        }
        out.writeByte(value);
    }
}
