package com.cncoderx.game.magictower.ui;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Pool;
import com.cncoderx.game.magictower.GameContext;
import com.cncoderx.game.magictower.OnTouchStateListener;

/**
 * Created by admin on 2017/5/27.
 */
public class Dialog extends WidgetGroup implements Pool.Poolable, OnTouchStateListener {
    final Group parent;
    private boolean isShowing;
    private DismissListener mDismissListener;

    public Dialog(Group parent) {
        if (parent == null) {
            throw new IllegalArgumentException();
        }
        this.parent = parent;
    }

    public void setDismissListener(DismissListener listener) {
        mDismissListener = listener;
    }

    public final void show() {
        float x = parent.getWidth() / 2;
        float y = parent.getHeight() / 2;
        if (!isShowing) {
            isShowing = true;
            setPosition(x, y, Align.center);
            parent.addActor(this);
            GameContext.instance().addTouchStateListener(this);
        }
    }

    public final void dismiss() {
        if (isShowing) {
            isShowing = false;
            remove();
            GameContext.instance().removeTouchStateListener(this);
            if (mDismissListener != null) {
                mDismissListener.onDismiss(this);
            }
        }
    }

    public boolean isShowing() {
        return isShowing;
    }

    @Override
    public Group getParent() {
        return parent;
    }

    @Override
    public boolean onBackPressed() {
        dismiss();
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

    @Override
    public void reset() {
        setDismissListener(null);
    }

    public static interface DismissListener {
        void onDismiss(Dialog dialog);
    }
}
