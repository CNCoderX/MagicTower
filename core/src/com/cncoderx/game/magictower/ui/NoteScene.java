package com.cncoderx.game.magictower.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.cncoderx.game.magictower.GameContext;
import com.cncoderx.game.magictower.Resources;
import com.cncoderx.game.magictower.drawable.AnimationDrawable;
import com.cncoderx.game.magictower.utils.Global;

/**
 * Created by admin on 2017/5/27.
 */
public class NoteScene extends Scene {
    private Table table;
    private Pool<Image> mImagePool = new Pool<Image>() {
        @Override
        protected Image newObject() {
            return new Image();
        }
    };
    private Pool<Label> mLabelPool = new Pool<Label>() {
        @Override
        protected Label newObject() {
            Resources resources = GameContext.instance().getResources();
            Label.LabelStyle style = new Label.LabelStyle(resources.getBitmapFont("default2.fnt"), Color.WHITE);
            Label label = new Label("", style);
            label.setFontScale(.8f);
            return label;
        }
    };

    private DismissListener mDismissListener;

    public NoteScene() {
        setSize(Global.MAP_WIDTH, Global.MAP_HEIGHT);
        table = new Table();
        addActor(table);
    }

    public void setDismissListener(DismissListener listener) {
        mDismissListener = listener;
    }

    @Override
    public void layout() {
        float prefWidth = table.getPrefWidth();
        float prefHeight = table.getPrefHeight();
        table.setSize(prefWidth, prefHeight);
        table.setPosition(getWidth() / 2, getHeight() - 10, Align.top);
    }

    @Override
    public void onShow() {
        Resources resources = GameContext.instance().getResources();
        {
            Image image = mImagePool.obtain();
            table.add(image).width(60).padBottom(8);
        }
        {
            Label label = mLabelPool.obtain();
            label.setText(resources.getString(12));
            label.setAlignment(Align.center);
            table.add(label).width(60).padBottom(8);
        }
        {
            Label label = mLabelPool.obtain();
            label.setText(resources.getString(13));
            label.setAlignment(Align.center);
            table.add(label).width(60).padBottom(8);
        }
        {
            Label label = mLabelPool.obtain();
            label.setText(resources.getString(14));
            label.setAlignment(Align.center);
            table.add(label).width(60).padBottom(8);
        }
        {
            Label label = mLabelPool.obtain();
            label.setText(resources.getString(17));
            label.setAlignment(Align.center);
            table.add(label).width(60).padBottom(8);
        }
        {
            Label label = mLabelPool.obtain();
            label.setText(resources.getString(16) + "·" + resources.getString(15));
            label.setAlignment(Align.center);
            table.add(label).expandX().fillX().padBottom(8).row();
        }
    }

    public void setData(Array<Item> items) {
        Resources resources = GameContext.instance().getResources();
        for (Item item : items) {
            {
                Image image = mImagePool.obtain();
                image.setDrawable(new AnimationDrawable(resources.getAnimation(
                        Resources.atlas.character, item.drawable, .25f)).setPlayMode(Animation.PlayMode.LOOP));
                table.add(image).padBottom(5);
            }
            {
                Label label = mLabelPool.obtain();
                label.setText(String.valueOf(item.hp));
                table.add(label).align(Align.center).padBottom(5);
            }
            {
                Label label = mLabelPool.obtain();
                label.setText(String.valueOf(item.attack));
                table.add(label).align(Align.center).padBottom(5);
            }
            {
                Label label = mLabelPool.obtain();
                label.setText(String.valueOf(item.defence));
                table.add(label).align(Align.center).padBottom(5);
            }
            {
                Label label = mLabelPool.obtain();
                label.setText(item.lose == Integer.MAX_VALUE ? "???" : String.valueOf(item.lose));
                table.add(label).align(Align.center).padBottom(5);
            }
            {
                Label label = mLabelPool.obtain();
                label.setText(item.exp + "·" + item.coin);
                table.add(label).align(Align.center).padBottom(5).row();
            }
        }
    }

    @Override
    public void onHide() {
        Actor[] actors = table.getChildren().begin();
        for (int i = actors.length - 1; i >= 0; i--) {
            Actor actor = actors[i];
//            table.removeActor(actor);
            if (actor instanceof Image) {
                mImagePool.free((Image) actor);
            }
            if (actor instanceof Label) {
                mLabelPool.free((Label) actor);
            }
        }
        table.clearChildren();
    }

    @Override
    public boolean onBackPressed() {
        if (mDismissListener != null) {
            mDismissListener.onDismiss();
        }
        return true;
    }

    @Override
    public boolean onPressLeft() {
        if (mDismissListener != null) {
            mDismissListener.onDismiss();
        }
        return true;
    }

    @Override
    public boolean onPressUp() {
        if (mDismissListener != null) {
            mDismissListener.onDismiss();
        }
        return true;
    }

    @Override
    public boolean onPressRight() {
        if (mDismissListener != null) {
            mDismissListener.onDismiss();
        }
        return true;
    }

    @Override
    public boolean onPressDown() {
        if (mDismissListener != null) {
            mDismissListener.onDismiss();
        }
        return true;
    }

    public static class Item {
        public int id;
        public int hp;
        public int attack;
        public int defence;
        public int lose;
        public int exp;
        public int coin;
        public String drawable;
    }

    public static interface DismissListener {
        void onDismiss();
    }
}
