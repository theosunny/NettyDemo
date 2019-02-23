package com.chapter8.protocol.request;

import com.chapter8.protocol.Packet;
import com.chapter8.protocol.command.Command;
import lombok.Data;

@Data
public class LoginRequestPacket extends Packet {
    private String userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST ;
    }
}
