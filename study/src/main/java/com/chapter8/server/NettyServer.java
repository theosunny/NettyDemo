package com.chapter8.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class NettyServer {
    private static final int PORT = 8000;
    public static void main(String[] args){
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        final ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boosGroup,workGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,1024)
                .option(ChannelOption.SO_KEEPALIVE,true)
                .option(ChannelOption.TCP_NODELAY,true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ServerHandler());
                    }
                });
        bind(serverBootstrap, PORT);

    }

    private static void bind(ServerBootstrap serverBootstrap, int port) {
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()){
                System.out.println(new Date() + ": 端口[" + port + "]绑定成功!");
            }else {
                serverBootstrap.config().group().schedule(()->bind(serverBootstrap,port+1), 1,TimeUnit.SECONDS);
            }
        });
    }
}
