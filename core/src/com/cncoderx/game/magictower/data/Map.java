package com.cncoderx.game.magictower.data;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.StreamUtils;
import com.cncoderx.game.magictower.io.Reader;
import com.cncoderx.game.magictower.io.Serializable;
import com.cncoderx.game.magictower.io.Writer;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * Created by admin on 2017/5/16.
 */
public class Map implements Serializable {
    private int current;
    private int overFloor;
    private int size;
    private Floor[] arrFloor;

    public Map() {
    }

    public Map(FileHandle file) {
        InputStream stream = file.read();
        byte[] bytes = new byte[1024 * 1024];
        try {
            int size = stream.available();
            stream.read(bytes, 0, size);
            read(new Reader(ByteBuffer.wrap(bytes, 0, size)));
        } catch (IOException e) {

        } finally {
            StreamUtils.closeQuietly(stream);
        }
    }

    @Override
    public void read(Reader reader) {
        current = reader.readShort();
        overFloor = reader.readShort();
        size = reader.readInt();
        if (size > 0) {
            arrFloor = new Floor[size];
            for (int i = 0; i < size; i++) {
                arrFloor[i] = new Floor();
                arrFloor[i].read(reader);
            }
        }
    }

    @Override
    public void write(Writer writer) {
        writer.writeShort((short) current);
        writer.writeShort((short) overFloor);
        writer.writeInt(size);
        for (int i = 0; i < size; i++) {
            if (arrFloor[i] != null) {
                arrFloor[i].write(writer);
            }
        }
    }

    public int getTileId(int x, int y) {
        return getFloor().getTileId(x, y);
    }

    public int getLayerId(int x, int y) {
        return getFloor().getLayerId(x, y);
    }

    public boolean removeTile(int x, int y) {
        return setTileId(x, y, -1);
    }

    public boolean removeLayer(int x, int y) {
        return setLayerId(x, y, -1);
    }

    public boolean setTileId(int x, int y, int t) {
        return getFloor().setTileId(x, y, t);
    }

    public boolean setLayerId(int x, int y, int t) {
        return getFloor().setLayerId(x, y, t);
    }

    public Floor getFloor() {
        return getFloor(current);
    }

    public Floor getFloor(int index) {
        if (index < 0 || index > size - 1) {
            throw new IllegalArgumentException();
        }
        return arrFloor[index];
    }

    public int getSize() {
        return size;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getOverFloor() {
        return overFloor;
    }

    public void setOverFloor(int overFloor) {
        this.overFloor = overFloor;
    }
}
