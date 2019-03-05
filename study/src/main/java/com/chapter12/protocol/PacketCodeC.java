package com.chapter12.protocol;

import com.chapter12.protocol.message.MessageRequestPacket;
import com.chapter12.protocol.message.MessageResponsePacket;
import com.chapter12.protocol.request.LoginRequestPacket;
import com.chapter12.protocol.response.LoginResponsePacket;
import com.chapter12.serialize.Serializer;
import com.chapter12.serialize.impl.JSONSerializer;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;

import static com.chapter8.protocol.command.Command.*;

/**
 * 编码：封装成二进制的过程
 */
public class PacketCodeC {
    private static final int MAGIC_NUMBER = 0x12345678;
    private  static volatile PacketCodeC INSTANCE;

    private final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private final Map<Byte, Serializer> serializerMap;

    private PacketCodeC(){
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(MESSAGE_REQUEST, MessageRequestPacket.class);
        packetTypeMap.put(MESSAGE_RESPONSE, MessageResponsePacket.class);

        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializerAlgorithm(), serializer);
    }
//    双重检查[推荐用]
    public static PacketCodeC getInstance(){
        if (INSTANCE==null){
            synchronized (PacketCodeC.class){
                if (INSTANCE==null){
                    INSTANCE = new PacketCodeC();
                }
            }
        }
        return INSTANCE;
    }
    //第二个方案
    public static PacketCodeC getInnerClassInstance(){
        return SingletonInstance.INSTANCE;
    }
    private static class SingletonInstance {
        public static final PacketCodeC INSTANCE =  new PacketCodeC();
    }
    /**
     * 编码
     * @param packet
     * @return
     */
    // 更改后的定义
    public void encode(ByteBuf byteBuf, Packet packet) {
        // 1. 序列化 java 对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);
        // 2. 实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
    }

    public Packet decode(ByteBuf byteBuf) {
        // 跳过 magic number
        byteBuf.skipBytes(4);
        // 跳过版本号
        byteBuf.skipBytes(1);
        // 序列化算法标识
        byte serializeAlgorithm = byteBuf.readByte();
        // 指令
        byte command = byteBuf.readByte();
        // 数据包长度
        int length = byteBuf.readInt();
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);
        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);
        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }
        return null;

    }


    private Serializer getSerializer(byte serializeAlgorithm) {

        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends Packet> getRequestType(byte command) {

        return packetTypeMap.get(command);
    }
}
