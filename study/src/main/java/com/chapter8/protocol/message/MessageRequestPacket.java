package com.chapter8.protocol.message;

import com.chapter8.protocol.Packet;
import com.chapter8.protocol.command.Command;
import lombok.Data;

@Data
public class MessageRequestPacket extends Packet {
    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
