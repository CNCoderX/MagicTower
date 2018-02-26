package com.cncoderx.game.magictower.drawable;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable;
import com.badlogic.gdx.utils.Array;

/**
 * Created by admin on 2017/5/25.
 */
public class LazyAnimationDrawable extends AnimationDrawable {
    private boolean isRunning = false;
    private FinishedListener mFinishedListener;

    public LazyAnimationDrawable(Animation<Drawable> animation) {
        super(animation);
    }

    public LazyAnimationDrawable(float frameDuration, Array<Drawable> keyFrames) {
        super(frameDuration, keyFrames);
    }

    public LazyAnimationDrawable(float frameDuration, Drawable... keyFrames) {
        super(frameDuration, keyFrames);
    }

    public void setFinishedListener(FinishedListener finishedListener) {
        mFinishedListener = finishedListener;
    }

    @Override
    public void draw(Batch batch, float x, float y, float width, float height) {
        if (isRunning) {
            super.draw(batch, x, y, width, height);
            if (isAnimationFinished()) {
                isRunning = false;
                if (mFinishedListener != null) {
                    mFinishedListener.finish();
                }
            }
        } else {
            Drawable drawable = getCurrentDrawable();
            if (drawable != null) {
                drawable.draw(batch, x, y, width, height);
            }
        }
    }

    @Override
    public void draw(Batch batch, float x, float y, float originX, float originY, float width, float height, float scaleX, float scaleY, float rotation) {
        if (isRunning) {
            super.draw(batch, x, y, originX, originY, width, height, scaleX, scaleY, rotation);
            if (isAnimationFinished()) {
                isRunning = false;
                reset();
                if (mFinishedListener != null) {
                    mFinishedListener.finish();
                }
            }
        } else {
            Drawable drawable = getCurrentDrawable();
            if (drawable != null && drawable instanceof TransformDrawable) {
                ((TransformDrawable) drawable).draw(batch, x, y, originX, originY, width, height, scaleX, scaleY, rotation);
            }
        }
    }

    @Deprecated
    @Override
    public LazyAnimationDrawable setPlayMode(Animation.PlayMode playMode) {
        return this;
    }

    public void start() {
        isRunning = true;
    }

    public static interface FinishedListener {
        void finish();
    }
}
