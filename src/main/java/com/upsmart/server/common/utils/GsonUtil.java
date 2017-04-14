package com.upsmart.server.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * Created by yuhang on 17-4-14.
 *
 * 如果要加ignore,可以继承此类修改gson类型
 */
public class GsonUtil {

    protected Gson gson;

    public GsonUtil(){
        gson = new GsonBuilder().disableHtmlEscaping().create();
    }

    private void serializeInternal(Object obj, OutputStreamWriter writer) {
        try {
            gson.toJson(obj, writer);
        } finally {
            if(writer != null) try {
                writer.close();
            } catch(IOException e) {
            }
        }
    }
    public void serialize(Object obj, OutputStream os) {
        serializeInternal(obj, new OutputStreamWriter(os));
    }
    public void serialize(Object obj, OutputStream os, Charset charset) {
        serializeInternal(obj, new OutputStreamWriter(os, charset));
    }
    public void serialize(Object obj, OutputStream os, CharsetEncoder charsetEncoder) {
        serializeInternal(obj, new OutputStreamWriter(os, charsetEncoder));
    }
    public void serialize(Object obj, OutputStream os, String charsetName) throws UnsupportedEncodingException {
        serializeInternal(obj, new OutputStreamWriter(os, charsetName));
    }

    public String serialize(Object obj) {
        return gson.toJson(obj);
    }

    private <E> E deserializeInternal(Reader reader, Class<E> clazz) {
        try {
            return gson.fromJson(reader, clazz);
        } finally {
            if(reader != null) try {
                reader.close();
            } catch(IOException e) {
            }
        }
    }
    public <E> E deserialize(InputStream is, Class<E> clazz) {
        return deserializeInternal(new InputStreamReader(is), clazz);
    }
    public <E> E deserialize(InputStream is, Class<E> clazz, Charset charset) {
        return deserializeInternal(new InputStreamReader(is, charset), clazz);
    }
    public <E> E deserialize(InputStream is, Class<E> clazz, CharsetDecoder charsetDecoder) {
        return deserializeInternal(new InputStreamReader(is, charsetDecoder), clazz);
    }
    public <E> E deserialize(InputStream is, Class<E> clazz, String charsetName) throws UnsupportedEncodingException {
        return deserializeInternal(new InputStreamReader(is, charsetName), clazz);
    }
    public <E> E deserialize(String jsonString, Class<E> clazz) {
        return gson.fromJson(jsonString, clazz);
    }
}
