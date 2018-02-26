package com.cncoderx.game.magictower.trigger;

import com.cncoderx.game.magictower.Game;

/**
 * Created by admin on 2017/5/18.
 */
public abstract class Trigger {
    private long id;
    Game game;

    public Trigger(long id) {
        this.id = id;
    }

    protected abstract boolean condition();

    protected abstract void action();

    public long getId() {
        return id;
    }

    public Game getGame() {
        return game;
    }

    public static final int ID_ENTER = 1000;
    public static final int ID_LEAVE = 1001;
    public static final int ID_TOUCH = 1002;
}
