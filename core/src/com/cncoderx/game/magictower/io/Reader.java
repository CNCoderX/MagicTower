package com.cncoderx.game.magictower.io;

import java.nio.ByteBuffer;

/**
 * Created by admin on 2017/5/25.
 */
public class Reader {
    private ByteBuffer mBuffer;

    public Reader(ByteBuffer buffer) {
        mBuffer = buffer;
    }

    public byte read() {
        return mBuffer.get();
    }

    public short readShort() {
        return mBuffer.getShort();
    }

    public int readInt() {
        return mBuffer.getInt();
    }

    public long readLong() {
        return mBuffer.getLong();
    }

    public float readFloat() {
        return mBuffer.getFloat();
    }

    public double readDouble() {
        return mBuffer.getDouble();
    }

    public char readChar() {
        return mBuffer.getChar();
    }
}
