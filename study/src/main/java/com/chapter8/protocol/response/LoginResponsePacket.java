package com.chapter8.protocol.response;

import com.chapter8.protocol.command.Command;
import com.chapter8.protocol.Packet;
import lombok.Data;

@Data
public class LoginResponsePacket extends Packet {
    private boolean success;
    private String reason;

    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }

}
