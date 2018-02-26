package com.cncoderx.game.magictower.io;

/**
 * Created by admin on 2017/5/25.
 */
public interface Serializable {
    void read(Reader reader);
    void write(Writer writer);
}
