package com.cncoderx.game.magictower;

import com.badlogic.gdx.utils.Array;

/**
 * Created by ll on 2017/5/21.
 */
public class OnTouchStateListeners implements OnTouchStateListener {
    private Array<OnTouchStateListener> mListeners = new Array<OnTouchStateListener>();

    public void add(OnTouchStateListener listener) {
        mListeners.add(listener);
    }

    public void remove(OnTouchStateListener listener) {
        mListeners.removeValue(listener, true);
    }

    public void clear() {
        mListeners.clear();
    }

    @Override
    public boolean onPressLeft() {
        for (int i = mListeners.size - 1; i >= 0; i--) {
            if (mListeners.get(i).onPressLeft()) return true;
        }
        return false;
    }

    @Override
    public boolean onPressUp() {
        for (int i = mListeners.size - 1; i >= 0; i--) {
            if (mListeners.get(i).onPressUp()) return true;
        }
        return false;
    }

    @Override
    public boolean onPressRight() {
        for (int i = mListeners.size - 1; i >= 0; i--) {
            if (mListeners.get(i).onPressRight()) return true;
        }
        return false;
    }

    @Override
    public boolean onPressDown() {
        for (int i = mListeners.size - 1; i >= 0; i--) {
            if (mListeners.get(i).onPressDown()) return true;
        }
        return false;
    }

    @Override
    public boolean onReleaseLeft() {
        for (int i = mListeners.size - 1; i >= 0; i--) {
            if (mListeners.get(i).onReleaseLeft()) return true;
        }
        return false;
    }

    @Override
    public boolean onReleaseUp() {
        for (int i = mListeners.size - 1; i >= 0; i--) {
            if (mListeners.get(i).onReleaseUp()) return true;
        }
        return false;
    }

    @Override
    public boolean onReleaseRight() {
        for (int i = mListeners.size - 1; i >= 0; i--) {
            if (mListeners.get(i).onReleaseRight()) return true;
        }
        return false;
    }

    @Override
    public boolean onReleaseDown() {
        for (int i = mListeners.size - 1; i >= 0; i--) {
            if (mListeners.get(i).onReleaseDown()) return true;
        }
        return false;
    }

    @Override
    public boolean onBackPressed() {
        for (int i = mListeners.size - 1; i >= 0; i--) {
            if (mListeners.get(i).onBackPressed()) return true;
        }
        return false;
    }
}
