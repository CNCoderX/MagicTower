package com.cncoderx.game.magictower.utils;

/**
 * Created by admin on 2017/5/23.
 */
public class VPoint extends Point {
    public int v;

    public VPoint() {
    }

    public VPoint(int x, int y) {
        super(x, y);
    }

    public VPoint(int x, int y, int v) {
        super(x, y);
        this.v = v;
    }
}
