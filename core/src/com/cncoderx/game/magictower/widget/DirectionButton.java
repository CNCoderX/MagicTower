package com.cncoderx.game.magictower.widget;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.cncoderx.game.magictower.drawable.DrawableContainer;
import com.cncoderx.game.magictower.utils.Global;

/**
 * Created by admin on 2017/5/25.
 */
public class DirectionButton extends Image {
    private int direction = -1;
    private DirectionButtonListener mDirectionButtonListener;

    public DirectionButton() {
        addListener(mListener);
    }

    public DirectionButton(Drawable drawable) {
        super(drawable);
        addListener(mListener);
    }

    public void setDirectionButtonListener(DirectionButtonListener listener) {
        mDirectionButtonListener = listener;
    }

    boolean isTouchLeft(InputEvent event, float x, float y) {
        float halfWidth = getWidth() / 2;
        float halfHeight = (float) (getHeight() / Math.sqrt(2));
        float xx = x - getOriginX();
        float yy = y - getOriginY();
        double atan = Math.atan2(yy, xx);
        return (xx > -halfWidth && xx < 0 && yy > -halfHeight && yy < halfHeight) &&
                (atan > Math.PI * 3 / 4 && atan < Math.PI || atan > -Math.PI && atan < -Math.PI * 3 / 4);
    }

    boolean isTouchUp(InputEvent event, float x, float y) {
        float halfWidth = (float) (getWidth() / Math.sqrt(2));
        float halfHeight = getHeight() / 2;
        float xx = x - getOriginX();
        float yy = y - getOriginY();
        double atan = Math.atan2(yy, xx);
        return (xx > -halfWidth && xx < halfWidth && yy > 0 && yy < halfHeight) &&
                (atan > Math.PI / 4 && atan < Math.PI * 3 / 4);
    }

    boolean isTouchRight(InputEvent event, float x, float y) {
        float halfWidth = getWidth() / 2;
        float halfHeight = (float) (getHeight() / Math.sqrt(2));
        float xx = x - getOriginX();
        float yy = y - getOriginY();
        double atan = Math.atan2(yy, xx);
        return (xx > 0 && xx < halfWidth && yy > -halfHeight && yy < halfHeight) &&
                (atan > 0 && atan < Math.PI / 4 || atan > -Math.PI / 4 && atan < 0);
    }

    boolean isTouchDown(InputEvent event, float x, float y) {
        float halfWidth = (float) (getWidth() / Math.sqrt(2));
        float halfHeight = getHeight() / 2;
        float xx = x - getOriginX();
        float yy = y - getOriginY();
        double atan = Math.atan2(yy, xx);
        return (xx > -halfWidth && xx < halfWidth && yy > -halfHeight && yy < 0) &&
                (atan > -Math.PI * 3 / 4 && atan < -Math.PI / 4);
    }

    void notifyPressListener() {
        if (mDirectionButtonListener == null)
            return;

        switch (direction) {
            case Global.LEFT:
                mDirectionButtonListener.onPressLeft();
                break;
            case Global.UP:
                mDirectionButtonListener.onPressUp();
                break;
            case Global.RIGHT:
                mDirectionButtonListener.onPressRight();
                break;
            case Global.DOWN:
                mDirectionButtonListener.onPressDown();
                break;
        }
    }

    void notifyReleaseListener() {
        if (mDirectionButtonListener == null)
            return;

        switch (direction) {
            case Global.LEFT:
                mDirectionButtonListener.onReleaseLeft();
                break;
            case Global.UP:
                mDirectionButtonListener.onReleaseUp();
                break;
            case Global.RIGHT:
                mDirectionButtonListener.onReleaseRight();
                break;
            case Global.DOWN:
                mDirectionButtonListener.onReleaseDown();
                break;
        }
    }

    InputListener mListener = new InputListener() {

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            if (isTouchLeft(event, x, y)) {
                direction = Global.LEFT;
                DrawableContainer drawable = (DrawableContainer) getDrawable();
                drawable.setCurrentDrawable(1);
                notifyPressListener();
                return true;
            }
            if (isTouchUp(event, x, y)) {
                direction = Global.UP;
                DrawableContainer drawable = (DrawableContainer) getDrawable();
                drawable.setCurrentDrawable(2);
                notifyPressListener();
                return true;
            }
            if (isTouchRight(event, x, y)) {
                direction = Global.RIGHT;
                DrawableContainer drawable = (DrawableContainer) getDrawable();
                drawable.setCurrentDrawable(3);
                notifyPressListener();
                return true;
            }
            if (isTouchDown(event, x, y)) {
                direction = Global.DOWN;
                DrawableContainer drawable = (DrawableContainer) getDrawable();
                drawable.setCurrentDrawable(4);
                notifyPressListener();
                return true;
            }
            return false;
        }

        @Override
        public void touchDragged(InputEvent event, float x, float y, int pointer) {
            if (isTouchLeft(event, x, y)) {
                if (direction != Global.LEFT) {
                    notifyReleaseListener();
                    direction = Global.LEFT;
                    DrawableContainer drawable = (DrawableContainer) getDrawable();
                    drawable.setCurrentDrawable(1);
                    notifyPressListener();
                }
                return;
            }
            if (isTouchUp(event, x, y)) {
                if (direction != Global.UP) {
                    notifyReleaseListener();
                    direction = Global.UP;
                    DrawableContainer drawable = (DrawableContainer) getDrawable();
                    drawable.setCurrentDrawable(2);
                    notifyPressListener();
                }
                return;
            }
            if (isTouchRight(event, x, y)) {
                if (direction != Global.RIGHT) {
                    notifyReleaseListener();
                    direction = Global.RIGHT;
                    DrawableContainer drawable = (DrawableContainer) getDrawable();
                    drawable.setCurrentDrawable(3);
                    notifyPressListener();
                }
                return;
            }
            if (isTouchDown(event, x, y)) {
                if (direction != Global.DOWN) {
                    notifyReleaseListener();
                    direction = Global.DOWN;
                    DrawableContainer drawable = (DrawableContainer) getDrawable();
                    drawable.setCurrentDrawable(4);
                    notifyPressListener();
                }
                return;
            }
            notifyReleaseListener();
            direction = -1;
            DrawableContainer drawable = (DrawableContainer) getDrawable();
            drawable.setCurrentDrawable(0);
        }

        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            notifyReleaseListener();
            direction = -1;
            DrawableContainer drawable = (DrawableContainer) getDrawable();
            drawable.setCurrentDrawable(0);
        }
    };

    public static interface DirectionButtonListener {
        boolean onPressLeft();
        boolean onPressUp();
        boolean onPressRight();
        boolean onPressDown();
        boolean onReleaseLeft();
        boolean onReleaseUp();
        boolean onReleaseRight();
        boolean onReleaseDown();
    }
}
