package com.cncoderx.game.magictower.drawable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable;
import com.badlogic.gdx.utils.Array;

/**
 * Created by admin on 2017/4/10.
 */
public class AnimationDrawable implements TransformDrawable {
    private float stateTime = 0f;
    private Animation<Drawable> animation;

    public AnimationDrawable(Animation<Drawable> animation) {
        this.animation = animation;
    }

    public AnimationDrawable(float frameDuration, Array<Drawable> keyFrames) {
        this(new Animation<Drawable>(frameDuration, keyFrames));
    }

    public AnimationDrawable(float frameDuration, Drawable... keyFrames) {
        this(new Animation<Drawable>(frameDuration, keyFrames));
    }

    public AnimationDrawable setPlayMode(Animation.PlayMode playMode) {
        animation.setPlayMode(playMode);
        return this;
    }

    @Override
    public void draw(Batch batch, float x, float y, float width, float height) {
        stateTime += Gdx.graphics.getDeltaTime();
        Drawable drawable = getCurrentDrawable();
        if (drawable != null) {
            drawable.draw(batch, x, y, width, height);
        }
    }

    @Override
    public void draw(Batch batch, float x, float y, float originX, float originY, float width, float height, float scaleX, float scaleY, float rotation) {
        stateTime += Gdx.graphics.getDeltaTime();
        Drawable drawable = getCurrentDrawable();
        if (drawable != null && drawable instanceof TransformDrawable) {
            ((TransformDrawable) drawable).draw(batch, x, y, originX, originY, width, height, scaleX, scaleY, rotation);
        }
    }

    public void reset() {
        this.stateTime = 0;
    }

    public Drawable getCurrentDrawable() {
        return animation.getKeyFrame(stateTime);
    }

    public Animation<Drawable> getAnimation() {
        return animation;
    }

    public boolean isAnimationFinished() {
        return animation.isAnimationFinished(stateTime);
    }

    @Override
    public float getLeftWidth() {
        Drawable drawable = getCurrentDrawable();
        return drawable == null ? 0 : drawable.getLeftWidth();
    }

    @Override
    public void setLeftWidth(float leftWidth) {
        Drawable drawable = getCurrentDrawable();
        if (drawable != null) {
            drawable.setLeftWidth(leftWidth);
        }
    }

    @Override
    public float getRightWidth() {
        Drawable drawable = getCurrentDrawable();
        return drawable == null ? 0 : drawable.getRightWidth();
    }

    @Override
    public void setRightWidth(float rightWidth) {
        Drawable drawable = getCurrentDrawable();
        if (drawable != null) {
            drawable.setRightWidth(rightWidth);
        }
    }

    @Override
    public float getTopHeight() {
        Drawable drawable = getCurrentDrawable();
        return drawable == null ? 0 : drawable.getTopHeight();
    }

    @Override
    public void setTopHeight(float topHeight) {
        Drawable drawable = getCurrentDrawable();
        if (drawable != null) {
            drawable.setTopHeight(topHeight);
        }
    }

    @Override
    public float getBottomHeight() {
        Drawable drawable = getCurrentDrawable();
        return drawable == null ? 0 : drawable.getBottomHeight();
    }

    @Override
    public void setBottomHeight(float bottomHeight) {
        Drawable drawable = getCurrentDrawable();
        if (drawable != null) {
            drawable.setBottomHeight(bottomHeight);
        }
    }

    @Override
    public float getMinWidth() {
        Drawable drawable = getCurrentDrawable();
        return drawable == null ? 0 : drawable.getMinWidth();
    }

    @Override
    public void setMinWidth(float minWidth) {
        Drawable drawable = getCurrentDrawable();
        if (drawable != null) {
            drawable.setMinHeight(minWidth);
        }
    }

    @Override
    public float getMinHeight() {
        Drawable drawable = getCurrentDrawable();
        return drawable == null ? 0 : drawable.getMinHeight();
    }

    @Override
    public void setMinHeight(float minHeight) {
        Drawable drawable = getCurrentDrawable();
        if (drawable != null) {
            drawable.setMinHeight(minHeight);
        }
    }
}
