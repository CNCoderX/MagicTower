package com.cncoderx.game.magictower.utils;

/**
 * Created by admin on 2017/5/16.
 */
public class Point {

    public int x, y;

    public Point() {
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point point) {
        this.x = point.x;
        this.y = point.y;
    }
}
