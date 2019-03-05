package com.chapter12.protocol.message;

import com.chapter12.protocol.Packet;
import com.chapter12.protocol.command.Command;
import lombok.Data;

@Data
public class MessageRequestPacket extends Packet {
    private String message;
    public MessageRequestPacket(String message) {
        this.message = message;
    }
    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
