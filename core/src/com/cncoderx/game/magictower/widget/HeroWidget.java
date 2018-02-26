package com.cncoderx.game.magictower.widget;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.cncoderx.game.magictower.drawable.DrawableContainer;
import com.cncoderx.game.magictower.utils.Global;
import com.cncoderx.game.magictower.utils.VPoint;

/**
 * Created by admin on 2017/5/25.
 */
public class HeroWidget extends Actor {
    private VPoint mPoint;
    private int currentFrame;
    private int vector = -1;

    private boolean isIdle = true;

    private DrawableContainer mDrawable;
    private PositionChangedListener mPositionChangedListener;

    public HeroWidget(DrawableContainer drawable) {
        mDrawable = drawable;
        setSize(Global.TILE_WIDTH, Global.TILE_HEIGHT);
    }

    public void setPositionChangedListener(PositionChangedListener listener) {
        mPositionChangedListener = listener;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (mDrawable != null) {
            mDrawable.draw(batch, getX(), getY(), getWidth(), getHeight());
        }
    }

    public VPoint getPoint() {
        return mPoint;
    }

    public void setPoint(VPoint point) {
        mPoint = point;
    }

    public void faceLeft() {
        if (mPoint.v != Global.LEFT) {
            currentFrame = 0;
            mPoint.v = Global.LEFT;
            updateDrawable();
        }
    }

    public void faceUp() {
        if (mPoint.v != Global.UP) {
            currentFrame = 0;
            mPoint.v = Global.UP;
            updateDrawable();
        }
    }

    public void faceRight() {
        if (mPoint.v != Global.RIGHT) {
            currentFrame = 0;
            mPoint.v = Global.RIGHT;
            updateDrawable();
        }
    }

    public void faceDown() {
        if (mPoint.v != Global.DOWN) {
            currentFrame = 0;
            mPoint.v = Global.DOWN;
            updateDrawable();
        }
    }

    public void moveLeft() {
        if (isIdle) {
            isIdle = false;
            currentFrame = 0;
            vector = Global.LEFT;
            computeMoveLeft();
        }
    }

    private void computeMoveLeft() {
        currentFrame++;

        float width = getWidth();
        setX(mPoint.x * width - width * currentFrame / 4);
        updateDrawable();
        if (currentFrame == 4) {
            isIdle = true;
            mPoint.x--;
            currentFrame = 0;
            setX(mPoint.x * width);
            if (mPositionChangedListener != null) {
                mPositionChangedListener.positionChanged(this);
            }
        } else {
            addAction(Actions.delay(.035f, Actions.run(new Runnable() {
                @Override
                public void run() {
                    computeMoveLeft();
                }
            })));
        }
    }

    public void moveUp() {
        if (isIdle) {
            isIdle = false;
            currentFrame = 0;
            vector = Global.UP;
            computeMoveUp();
        }
    }

    private void computeMoveUp() {
        currentFrame++;

        float height = getHeight();
        setY(mPoint.y * height + height * currentFrame / 4);
        updateDrawable();
        if (currentFrame == 4) {
            isIdle = true;
            mPoint.y++;
            currentFrame = 0;
            setY(mPoint.y * height);
            if (mPositionChangedListener != null) {
                mPositionChangedListener.positionChanged(this);
            }
        } else {
            addAction(Actions.delay(.035f, Actions.run(new Runnable() {
                @Override
                public void run() {
                    computeMoveUp();
                }
            })));
        }
    }

    public void moveRight() {
        if (isIdle) {
            isIdle = false;
            currentFrame = 0;
            vector = Global.RIGHT;
            computeMoveRight();
        }
    }

    private void computeMoveRight() {
        currentFrame++;

        float width = getWidth();
        setX(mPoint.x * width + width * currentFrame / 4);
        updateDrawable();
        if (currentFrame == 4) {
            isIdle = true;
            mPoint.x++;
            currentFrame = 0;
            setX(mPoint.x * width);
            if (mPositionChangedListener != null) {
                mPositionChangedListener.positionChanged(this);
            }
        } else {
            addAction(Actions.delay(.035f, Actions.run(new Runnable() {
                @Override
                public void run() {
                    computeMoveRight();
                }
            })));
        }
    }

    public void moveDown() {
        if (isIdle) {
            isIdle = false;
            currentFrame = 0;
            vector = Global.DOWN;
            computeMoveDown();
        }
    }

    private void computeMoveDown() {
        currentFrame++;

        float height = getHeight();
        setY(mPoint.y * height - height * currentFrame / 4);
        updateDrawable();
        if (currentFrame == 4) {
            isIdle = true;
            mPoint.y--;
            currentFrame = 0;
            setY(mPoint.y * height);
            if (mPositionChangedListener != null) {
                mPositionChangedListener.positionChanged(this);
            }
        } else {
            addAction(Actions.delay(.035f, Actions.run(new Runnable() {
                @Override
                public void run() {
                    computeMoveDown();
                }
            })));
        }
    }

    public void update() {
        setX(mPoint.x * getWidth());
        setY(mPoint.y * getHeight());
        updateDrawable();
    }

    private void updateDrawable() {
        int index = ( currentFrame >= 4 ) ? 0 : currentFrame;
        switch (mPoint.v) {
            case Global.LEFT:
                mDrawable.setCurrentDrawable(index);
                break;
            case Global.UP:
                mDrawable.setCurrentDrawable(4 + index);
                break;
            case Global.RIGHT:
                mDrawable.setCurrentDrawable(8 + index);
                break;
            case Global.DOWN:
                mDrawable.setCurrentDrawable(12 + index);
                break;
        }
    }

    public void stopMoveLeft() {
        vector = -1;
    }

    public void stopMoveUp() {
        vector = -1;
    }

    public void stopMoveRight() {
        vector = -1;
    }

    public void stopMoveDown() {
        vector = -1;
    }

    public boolean isIdle() {
        return isIdle;
    }

    public int getDirection() {
        return mPoint.v;
    }

    public int getVector() {
        return vector;
    }

    public static interface PositionChangedListener {
        void positionChanged(HeroWidget widget);
    }
}
