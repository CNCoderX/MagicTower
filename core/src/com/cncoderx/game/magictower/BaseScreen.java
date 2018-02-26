package com.cncoderx.game.magictower;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;

/**
 * Created by admin on 2017/5/24.
 */
public class BaseScreen extends ScreenAdapter {
    GameContext mContext;
    Resources mResources;
    private Screen backScreen;

    public void setBackScreen(Screen backScreen) {
        this.backScreen = backScreen;
    }

    public void back() {
        if (backScreen != null) {
            GameContext.instance().setScreen(backScreen);
        }
    }

    public Resources getResources() {
        return mResources;
    }

    public void setScreen(Screen screen) {
        if (mContext != null) {
            mContext.setScreen(screen);
        }
    }
}
