package com.cncoderx.game.magictower.data;

import com.cncoderx.game.magictower.io.Reader;
import com.cncoderx.game.magictower.io.Serializable;
import com.cncoderx.game.magictower.io.Writer;
import com.cncoderx.game.magictower.utils.Global;
import com.cncoderx.game.magictower.utils.VPoint;

/**
 * Created by admin on 2017/5/16.
 */
public class Floor implements Serializable {
    private int index;
    private VPoint[] downstairs = new VPoint[3];
    private VPoint[] upstairs = new VPoint[3];
    private int[][] typeTiles = new int[Global.ROW_SIZE][Global.COL_SIZE];
    private int[][] typeLayers = new int[Global.ROW_SIZE][Global.COL_SIZE];

    public Floor() {
    }

    @Override
    public void read(Reader reader) {
        index = reader.readInt();
        upstairs[0] = readPoint(reader);
        upstairs[1] = readPoint(reader);
        upstairs[2] = readPoint(reader);
        downstairs[0] = readPoint(reader);
        downstairs[1] = readPoint(reader);
        downstairs[2] = readPoint(reader);
        readTypeTiles(reader);
        readTypeLayers(reader);
    }

    private void readTypeTiles(Reader reader) {
        for (int y = Global.ROW_SIZE - 1; y >= 0; y--) {
            for (int x = 0; x < Global.COL_SIZE; x++) {
                typeTiles[y][x] = reader.readShort();
            }
        }
    }

    private void readTypeLayers(Reader reader) {
        for (int y = Global.ROW_SIZE - 1; y >= 0; y--) {
            for (int x = 0; x < Global.COL_SIZE; x++) {
                typeLayers[y][x] = reader.readShort();
            }
        }
    }

    private VPoint readPoint(Reader reader) {
        int x = reader.readShort();
        int y = Global.ROW_SIZE - reader.readShort() - 1;
        int v = reader.readShort();
        return new VPoint(x, y, v);
    }

    @Override
    public void write(Writer writer) {
        writer.writeInt(index);
        writePoint(writer, upstairs[0]);
        writePoint(writer, upstairs[1]);
        writePoint(writer, upstairs[2]);
        writePoint(writer, downstairs[0]);
        writePoint(writer, downstairs[1]);
        writePoint(writer, downstairs[2]);
        writeTypeTiles(writer);
        writeTypeLayers(writer);
    }

    private void writeTypeTiles(Writer writer) {
        for (int y = Global.ROW_SIZE - 1; y >= 0; y--) {
            for (int x = 0; x < Global.COL_SIZE; x++) {
                writer.writeShort((short) typeTiles[y][x]);
            }
        }
    }

    private void writeTypeLayers(Writer writer) {
        for (int y = Global.ROW_SIZE - 1; y >= 0; y--) {
            for (int x = 0; x < Global.COL_SIZE; x++) {
                writer.writeShort((short) typeLayers[y][x]);
            }
        }
    }

    private void writePoint(Writer writer, VPoint point) {
        writer.writeShort((short) point.x);
        writer.writeShort((short) (Global.ROW_SIZE - point.y - 1));
        writer.writeShort((short) point.v);
    }

    public int getTileId(int x, int y) {
        if (x < 0 || x >= Global.COL_SIZE) {
            return -1;
        }
        if (y < 0 || y >= Global.ROW_SIZE) {
            return -1;
        }
        return typeTiles[y][x];
    }

    public int getLayerId(int x, int y) {
        if (x < 0 || x >= Global.COL_SIZE) {
            return -1;
        }
        if (y < 0 || y >= Global.ROW_SIZE) {
            return -1;
        }
        return typeLayers[y][x];
    }

    public boolean removeTile(int x, int y) {
        return setTileId(x, y, -1);
    }

    public boolean removeLayer(int x, int y) {
        return setLayerId(x, y, -1);
    }

    public boolean setTileId(int x, int y, int t) {
        if (x < 0 || x >= Global.COL_SIZE) {
            return false;
        }
        if (y < 0 || y >= Global.ROW_SIZE) {
            return false;
        }
        typeTiles[y][x] = t;
        return true;
    }

    public boolean setLayerId(int x, int y, int t) {
        if (x < 0 || x >= Global.COL_SIZE) {
            return false;
        }
        if (y < 0 || y >= Global.ROW_SIZE) {
            return false;
        }
        typeLayers[y][x] = t;
        return true;
    }

    public int getIndex() {
        return index;
    }

    public VPoint getUpstair(int i) {
        return upstairs[i];
    }

    public VPoint getDownstair(int i) {
        return downstairs[i];
    }
}
