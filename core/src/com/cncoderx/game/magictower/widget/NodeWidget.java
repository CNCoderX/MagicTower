package com.cncoderx.game.magictower.widget;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.cncoderx.game.magictower.drawable.DrawableContainer;
import com.cncoderx.game.magictower.ui.TileLayout;
import com.cncoderx.game.magictower.utils.Global;

/**
 * Created by admin on 2017/5/24.
 */
public class NodeWidget extends Actor implements TileLayout.UpdateDrawableListener {
    private Drawable mDrawable;
    private Drawable mBackground;

    public NodeWidget() {
        setSize(Global.TILE_WIDTH, Global.TILE_HEIGHT);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);

        float x = getX();
        float y = getY();

        if (mBackground != null)
            mBackground.draw(batch, x, y, getWidth(), getHeight());

        if (mDrawable != null)
            mDrawable.draw(batch, x, y, getWidth(), getHeight());
    }

    public Drawable getDrawable() {
        return mDrawable;
    }

    public void setDrawable(Drawable drawable) {
        mDrawable = drawable;
    }

    public Drawable getBackground() {
        return mBackground;
    }

    public void setBackground(Drawable background) {
        mBackground = background;
    }

    @Override
    public void update(int frame) {
        selectDrawable(mDrawable, frame);
        selectDrawable(mBackground, frame);
    }

    private void selectDrawable(Drawable drawable, int index) {
        if (drawable != null && drawable instanceof DrawableContainer) {
            DrawableContainer drawable2 = (DrawableContainer) drawable;
            drawable2.setCurrentDrawable(index % drawable2.getSize());
        }
    }
}
