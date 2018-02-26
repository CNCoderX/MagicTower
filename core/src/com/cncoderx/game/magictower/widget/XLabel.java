package com.cncoderx.game.magictower.widget;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by admin on 2017/5/26.
 */
public class XLabel extends Label implements Runnable {
    private Queue<CharSequence> mTextQueue = new ArrayDeque<CharSequence>();
    private boolean isRunning = false;
    private Rectangle localBounds;
    private Rectangle scissorBounds;
    private float deltaTime = .05f;

    public XLabel(CharSequence text, LabelStyle style) {
        super(text, style);
    }

    public void setBounds(float x, float y, float width, float height) {
        localBounds = new Rectangle();
        scissorBounds = new Rectangle();
        localBounds.x = x;
        localBounds.y = y;
        localBounds.width = width;
        localBounds.height = height;
    }

    public void setDeltaTime(float deltaTime) {
        this.deltaTime = deltaTime;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (localBounds == null) {
            super.draw(batch, parentAlpha);
        } else {
            getStage().calculateScissors(localBounds, scissorBounds);
            batch.flush();
            if (ScissorStack.pushScissors(scissorBounds)) {
                super.draw(batch, parentAlpha);
                batch.flush();
                ScissorStack.popScissors();
            }
        }
    }

    public void append (CharSequence text) {
        if (text == null || text.length() == 0)
            return;

        getText().append(text);
        invalidateHierarchy();
    }

    public void showTextGradually(String text) {
        if (text == null)
            return;

        String trimedText = text.trim().replaceAll("<br>", "\n");
        if (text.length() == 0) {
            return;
        }
        mTextQueue.clear();
        for (int i = 0; i < trimedText.length(); i++) {
            mTextQueue.offer(String.valueOf(trimedText.charAt(i)));
        }

        isRunning = true;
        offsetY = 0;
        if (localBounds != null)
            setY(localBounds.y);
        setText("");

        addAction(Actions.delay(deltaTime, Actions.run(this)));
    }

    public void showTextImmediately(String text) {
        isRunning = false;
        offsetY = 0;
        if (localBounds != null)
            setY(localBounds.y);
        mTextQueue.clear();
        setText(text.trim().replaceAll("<br>", "\n"));
    }

    @Override
    public void setText(CharSequence newText) {
        super.setText(newText);
        offsetY = 0;
        if (localBounds != null)
            setY(localBounds.y);
    }

    public boolean isRunning() {
        return isRunning;
    }

    private float offsetY = 0f;

    @Override
    public void run() {
        if (isRunning) {
            CharSequence str = mTextQueue.poll();
            if (str == null) {
                isRunning = false;
            } else {
                append(str);
                if (localBounds != null) {
                    if (getGlyphLayout().height - localBounds.height > offsetY) {
                        float lineHeight = getBitmapFontCache().getFont().getLineHeight();
                        addAction(Actions.moveBy(0, lineHeight, 0.2f));
                        offsetY += lineHeight;
                    }
                }
                addAction(Actions.delay(deltaTime, Actions.run(this)));
            }
        }
    }
}
