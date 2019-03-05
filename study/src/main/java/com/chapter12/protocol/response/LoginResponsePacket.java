package com.chapter12.protocol.response;

import com.chapter12.protocol.Packet;
import com.chapter12.protocol.command.Command;
import lombok.Data;

@Data
public class LoginResponsePacket extends Packet {
    private boolean success;
    private String reason;

    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }

}
