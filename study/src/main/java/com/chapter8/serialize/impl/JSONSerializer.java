package com.chapter8.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.chapter8.serialize.Serializer;
import com.chapter8.serialize.SerializerAlgorithm;

public class JSONSerializer implements Serializer {
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    public <T> T deserialize(Class<T> classz, byte[] bytes) {
        return JSON.parseObject(bytes,classz);
    }
}
