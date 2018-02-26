package com.cncoderx.game.magictower.ui;

import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.cncoderx.game.magictower.GameContext;
import com.cncoderx.game.magictower.OnTouchStateListener;

/**
 * Created by Administrator on 2017/5/28.
 */
public abstract class Scene extends WidgetGroup implements OnTouchStateListener {

    public final void show() {
        onShow();
        GameContext.instance().addTouchStateListener(this);
    }

    public final void hide() {
        onHide();
        GameContext.instance().removeTouchStateListener(this);
    }

    public abstract void onShow();

    public abstract void onHide();

    @Override
    public boolean onBackPressed() {
        return true;
    }

    @Override
    public boolean onPressLeft() {
        return true;
    }

    @Override
    public boolean onPressUp() {
        return true;
    }

    @Override
    public boolean onPressRight() {
        return true;
    }

    @Override
    public boolean onPressDown() {
        return true;
    }

    @Override
    public boolean onReleaseLeft() {
        return true;
    }

    @Override
    public boolean onReleaseUp() {
        return true;
    }

    @Override
    public boolean onReleaseRight() {
        return true;
    }

    @Override
    public boolean onReleaseDown() {
        return true;
    }
}
