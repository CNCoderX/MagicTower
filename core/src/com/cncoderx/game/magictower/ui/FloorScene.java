package com.cncoderx.game.magictower.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.cncoderx.game.magictower.GameContext;
import com.cncoderx.game.magictower.Resources;
import com.cncoderx.game.magictower.utils.Global;

/**
 * Created by Administrator on 2017/5/28.
 */
public class FloorScene extends Scene {
    private Table table;
    private int current, over;
    private Label[] labels = new Label[20];
    private Listener mListener;

    public FloorScene() {
        setSize(Global.MAP_WIDTH, Global.MAP_HEIGHT);

        table = new Table();
        Resources resources = GameContext.instance().getResources();
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = resources.getBitmapFont("default.fnt");
        style.fontColor = Color.WHITE;
        style.background = new NinePatchDrawable(new NinePatch(
                resources.getRegion(Resources.atlas.ui, "frame2"), 5, 5, 5, 5));
        Cell<Label> cell = null;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                int index = j * 5 + i;
                Label label = new Label(resources.getString(19, index+1), style);
                label.setFontScale(.6f);
                label.setAlignment(Align.center);
                cell = table.add(label).size(80, 30).padLeft(5).padRight(5).padBottom(8);
                labels[index] = label;
            }
            cell.row();
        }
        float prefWidth = table.getPrefWidth();
        float prefHeight = table.getPrefHeight();
        table.setSize(prefWidth, prefHeight);
        table.setPosition(getWidth() / 2, getHeight() / 2, Align.center);
        addActor(table);

        BitmapFont font2 = resources.getBitmapFont("default2.fnt");
        Label label = new Label(resources.getString(26), new Label.LabelStyle(font2, Color.WHITE));
        label.setFontScale(.8f);
        label.setAlignment(Align.bottom);
        label.setSize(Global.MAP_WIDTH, 20);
        label.setPosition(10, 10);
        label.setWrap(true);
        addActor(label);
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    public void setCurrentFloor(int current) {
        this.current = Math.min(Math.max(1, current), 20);
    }

    public void setOverFloor(int over) {
        this.over = Math.min(labels.length, over);
    }

    private void updateCurrentFloor(int current) {
        if (this.current != current) {
            int old = this.current;
            if (current > 0 && current <= labels.length) {
                labels[current - 1].setColor(Color.GREEN);
            }
            if (old > 0 && old <= labels.length) {
                labels[old - 1].setColor(Color.WHITE);
            }
            this.current = current;
            GameContext.instance().getResources().getSound("switch.mp3").play();
        }
    }

    @Override
    public void onShow() {
        for (int i = 0; i < labels.length; i++) {
            if (i < over) {
                labels[i].setColor(Color.WHITE);
            } else {
                labels[i].setColor(Color.GRAY);
            }
        }
        if (current > 0 && current <= labels.length) {
            labels[current - 1].setColor(Color.GREEN);
        }
    }

    @Override
    public void onHide() {

    }

    @Override
    public boolean onBackPressed() {
        if (mListener != null) {
            mListener.cancel();
        }
        return true;
    }

    @Override
    public boolean onPressLeft() {
        if (mListener != null) {
            mListener.cancel();
        }
        return true;
    }

    @Override
    public boolean onPressRight() {
        if (mListener != null) {
            mListener.select(current);
        }
        return true;
    }

    @Override
    public boolean onPressUp() {
        updateCurrentFloor(current <= 1 ? over : current - 1);
        return true;
    }

    @Override
    public boolean onPressDown() {
        updateCurrentFloor(current >= over ? 1 : current + 1);
        return true;
    }

    public static interface Listener {
        void cancel();
        void select(int floor);
    }
}
