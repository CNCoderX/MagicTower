package com.cncoderx.game.magictower.utils;

/**
 * Created by admin on 2017/5/16.
 */
public class Global {
    public static final int SCREEN_WIDTH = 416;
    public static final int SCREEN_HEIGHT = 672;
    public static final int FLOOR_SIZE = 28;
    public static final int COL_SIZE = 11;
    public static final int ROW_SIZE = 11;
    public static final int TILE_WIDTH = 35;
    public static final int TILE_HEIGHT = 35;
    public static final int MAP_WIDTH = TILE_WIDTH * COL_SIZE;
    public static final int MAP_HEIGHT = TILE_HEIGHT * ROW_SIZE;

    // v
    public static final int LEFT = 0;
    public static final int UP = 1;
    public static final int RIGHT = 2;
    public static final int DOWN = 3;

    // layer types

    public static final int LAYER_TYPE_PROPS = 1;
    public static final int LAYER_TYPE_NPC = 2;
    public static final int LAYER_TYPE_MONSTER = 3;
    public static final int LAYER_TYPE_OTHER = 4;
    public static final int LAYER_TYPE_STAIR = 5;
}
