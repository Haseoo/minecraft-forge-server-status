package com.github.haseoo.minecraft.statusapi.utils.helpers;

import com.github.haseoo.minecraft.statusapi.exceptions.InvalidJsonStringLength;
import com.github.haseoo.minecraft.statusapi.exceptions.InvalidPacketIdException;
import lombok.RequiredArgsConstructor;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

import static com.github.haseoo.minecraft.statusapi.utils.Constants.*;
import static com.github.haseoo.minecraft.statusapi.utils.ServerConnectionUtils.readIntegerValue;
import static com.github.haseoo.minecraft.statusapi.utils.ServerConnectionUtils.writeIntValue;

@RequiredArgsConstructor
public class ForgePing {

    private final InetSocketAddress host;
    private final int timeout;


    public String ping() throws IOException {
        try (Socket socket = new Socket()) {
            socket.connect(host, timeout);
            try (
                    var outputStream = socket.getOutputStream();
                    var dataOutputStream = new DataOutputStream(outputStream);
                    var inputStream = socket.getInputStream();
                    var dataInputStream = new DataInputStream(inputStream)
            ) {
                doHandshake(dataOutputStream);

                int pingResponseId = doPing(dataOutputStream, dataInputStream);
                verifyPingResponse(pingResponseId);
                String pingResponse = getPingResponse(dataInputStream);

                int postJobResponseId = doPostJob(dataOutputStream, dataInputStream);
                verifyPostJobResponse(postJobResponseId);

                return pingResponse;
            }
        }
    }

    private void doHandshake(DataOutputStream dataOutputStream) throws IOException {
        var handshake = prepareHandshakePacket();
        writeIntValue(dataOutputStream, handshake.length);
        dataOutputStream.write(handshake);
    }

    private byte[] prepareHandshakePacket() throws IOException {
        var handshakeByteBuffer = new ByteArrayOutputStream();
        var handshakeData = new DataOutputStream(handshakeByteBuffer);
        handshakeData.writeByte(HANDSHAKE_PACKET_ID);

        writeIntValue(handshakeData, HANDSHAKE_PROTOCOL_VERSION);

        /*put hostname*/
        writeIntValue(handshakeData, host.getHostString().length());
        handshakeData.writeBytes(host.getHostString());

        handshakeData.writeShort(host.getPort());

        writeIntValue(handshakeData, HANDSHAKE_STATE);
        return handshakeByteBuffer.toByteArray();
    }

    private int doPing(DataOutputStream dataOutputStream, DataInputStream dataInputStream) throws IOException {
        dataOutputStream.writeByte(PING_PACKET_SIZE);
        dataOutputStream.writeByte(PING_PACKET_ID);
        readIntegerValue(dataInputStream); //ignore the value- known packet size
        return readIntegerValue(dataInputStream);
    }

    private void verifyPingResponse(int responseId) throws IOException {
        if (responseId == PREMATURE_END_OF_STREAM_RESPONSE_VALUE) {
            throw new EOFException();
        }
        if (responseId != VALID_PING_RESPONSE) {
            throw new InvalidPacketIdException();
        }
    }

    private String getPingResponse(DataInputStream dataInputStream) throws IOException {
        int responseLength = getJsonResponseLength(dataInputStream);
        verifyJsonResponseLength(responseLength);
        return readJsonResponse(dataInputStream, responseLength);
    }

    private int getJsonResponseLength(DataInputStream dataInputStream) throws IOException {
        return readIntegerValue(dataInputStream);
    }

    private void verifyJsonResponseLength(int responseLength) throws IOException {
        if (responseLength == PREMATURE_END_OF_STREAM_RESPONSE_VALUE) {
            throw new EOFException();
        }
        if (isResponseJsonStringEmpty(responseLength)) {
            throw new InvalidJsonStringLength();
        }
    }

    private boolean isResponseJsonStringEmpty(int length) {
        return length == 0;
    }

    private String readJsonResponse(DataInputStream dataInputStream, int responseLength) throws IOException {
        var responseBytes = new byte[responseLength];
        dataInputStream.readFully(responseBytes);
        return new String(responseBytes);
    }

    private int doPostJob(DataOutputStream dataOutputStream, DataInputStream dataInputStream) throws IOException {
        dataOutputStream.writeByte(POSTJOB_PACKET_SIZE);
        dataOutputStream.writeByte(POSTJOB_PACKET_ID);
        dataOutputStream.writeLong(System.currentTimeMillis());
        readIntegerValue(dataInputStream); //ignore the value- known packet size
        return readIntegerValue(dataInputStream);
    }

    private void verifyPostJobResponse(int postJobResponse) throws IOException {
        if (postJobResponse == PREMATURE_END_OF_STREAM_RESPONSE_VALUE) {
            throw new EOFException();
        }

        if (postJobResponse != POSTJOB_PACKET_ID) {
            throw new IOException();
        }
    }
}