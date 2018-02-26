package com.cncoderx.game.magictower;

/**
 * Created by ll on 2017/5/20.
 */
public interface UIUpdateCallback {
    void getLevel(int value);
    void getHp(int value);
    void getAttack(int value);
    void getDefence(int value);
    void getCoin(int value);
    void getExp(int value);
    void getYellowKey(int value);
    void getBlueKey(int value);
    void getRedKey(int value);
    void getFloor(int value);
    void getNote(boolean value);
    void getCompass(boolean value);
    void showMenu();
}
