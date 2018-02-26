package com.cncoderx.game.magictower.io;

import java.nio.ByteBuffer;

/**
 * Created by admin on 2017/5/25.
 */
public class Writer {
    private ByteBuffer mBuffer;

    public Writer(ByteBuffer buffer) {
        mBuffer = buffer;
    }

    public void write(byte value) {
        mBuffer.put(value);
    }

    public void writeChar(char value) {
        mBuffer.putChar(value);
    }

    public void writeShort(short value) {
        mBuffer.putShort(value);
    }

    public void writeInt(int value) {
        mBuffer.putInt(value);
    }

    public void writeLong(long value) {
        mBuffer.putLong(value);
    }

    public void writeFloat(float value) {
        mBuffer.putFloat(value);
    }

    public void writeDouble(double value) {
        mBuffer.putDouble(value);
    }

    public byte[] toByteArray() {
        byte[] bytes;
        mBuffer.flip();
        bytes = new byte[mBuffer.limit()];
        mBuffer.get(bytes);
        return bytes;
    }
}
