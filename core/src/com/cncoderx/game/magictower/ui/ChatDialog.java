package com.cncoderx.game.magictower.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.cncoderx.game.magictower.GameContext;
import com.cncoderx.game.magictower.Resources;
import com.cncoderx.game.magictower.data.Message;
import com.cncoderx.game.magictower.drawable.AnimationDrawable;
import com.cncoderx.game.magictower.widget.XLabel;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by admin on 2017/5/26.
 */
public class ChatDialog extends Dialog {
    private Drawable mDrawable;

    private Image image;
    private XLabel label;

    private Queue<Message.Item> messageQueue = new ArrayDeque<Message.Item>();
    private Message.Item mItem;

    public ChatDialog(Group parent) {
        super(parent);
        setName("ChatDialog");
        setSize(300, 100);
        Resources resources = GameContext.instance().getResources();
        mDrawable = new TextureRegionDrawable(resources.getRegion(Resources.atlas.ui, "transparent"));
        {
            image = new Image();
            image.setSize(40, 40);
            image.setPosition(5, getHeight() - 5, Align.topLeft);
            addActor(image);
        }
        {
            BitmapFont font = resources.getBitmapFont("default2.fnt");
            label = new XLabel("", new Label.LabelStyle(font, Color.WHITE));
            label.setAlignment(Align.topLeft);
            label.setWrap(true);
            label.setSize(240, 85);
            label.setPosition(50, 10);
            label.setBounds(50, 5, 240, 90);
            label.setFontScale(.8f);
            addActor(label);
        }
    }

    public void setMessage(Message message) {
        messageQueue.clear();
        for (Message.Item item : message.getItems()) {
            messageQueue.offer(item);
        }
    }

    @Override
    public boolean remove() {
        if (super.remove()) {
            image.setDrawable(null);
            label.setText("");
        }
        return false;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (mDrawable != null) {
            mDrawable.draw(batch, getX(), getY(), getWidth(), getHeight());
        }
        super.draw(batch, parentAlpha);
    }

    public void nextMessage() {
        Message.Item item = messageQueue.poll();
        mItem = item;
        if (item == null) {
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    dismiss();
                }
            });
        } else {
            Resources resources = GameContext.instance().getResources();
            Drawable drawable = new AnimationDrawable(resources.getAnimation(
                    Resources.atlas.character, item.getDrawable(), .25f))
                    .setPlayMode(Animation.PlayMode.LOOP);
            image.setDrawable(drawable);
            label.showTextGradually(item.getContent());

            GameContext.instance().getResources().getSound("flip_over.mp3").play();
        }
    }

    @Override
    public boolean onPressLeft() {
        if (label.isRunning()) {
            label.showTextImmediately(mItem.getContent());
        } else {
            nextMessage();
        }
        return super.onPressLeft();
    }

    @Override
    public boolean onPressUp() {
        if (label.isRunning()) {
            label.showTextImmediately(mItem.getContent());
        } else {
            nextMessage();
        }
        return super.onPressLeft();
    }

    @Override
    public boolean onPressRight() {
        if (label.isRunning()) {
            label.showTextImmediately(mItem.getContent());
        } else {
            nextMessage();
        }
        return super.onPressLeft();
    }

    @Override
    public boolean onPressDown() {
        if (label.isRunning()) {
            label.showTextImmediately(mItem.getContent());
        } else {
            nextMessage();
        }
        return super.onPressLeft();
    }

    @Override
    public void reset() {
        super.reset();
        messageQueue.clear();
        mItem = null;
    }
}
