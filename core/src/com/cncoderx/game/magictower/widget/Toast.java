package com.cncoderx.game.magictower.widget;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.ReflectionPool;
import com.cncoderx.game.magictower.GameContext;
import com.cncoderx.game.magictower.Resources;

/**
 * Created by admin on 2017/5/27.
 */
public class Toast extends Group {
    private Image image;
    private Label label;

    public Toast() {
        setWidth(300);
        Resources resources = GameContext.instance().getResources();

        image = new Image(resources.getRegion(Resources.atlas.ui, "transparent"));
        image.setWidth(300);

//        BitmapFont font = resources.getBitmapFont(18);
        BitmapFont font = resources.getBitmapFont("default.fnt");
        label = new Label("", new Label.LabelStyle(font, Color.WHITE));
        label.setFontScale(.75f);
        label.setWidth(280);
        label.setWrap(true);

        addActor(image);
        addActor(label);
    }

    public void setMessage(String message) {
        label.setText(message);
        float labelHeight = label.getPrefHeight();
        float height = labelHeight + 10;

        setHeight(height);
        image.setHeight(height);
        image.setOrigin(Align.center);
        image.setScaleX(0);
        label.setFontScale(.6f);
        label.setHeight(label.getPrefHeight());
        label.setPosition(150, height / 2, Align.center);
        label.setAlignment(Align.center);

        image.addAction(Actions.sequence(Actions.scaleTo(1, 1, .25f),
                Actions.delay(.5f, Actions.run(new Runnable() {
            @Override
            public void run() {
                float height = getParent().getHeight() / 2;
                if (remove()) {
                    Toast.this.clearActions();
                    toastPool.free(Toast.this);

                    toastArray.removeValue(Toast.this, true);
                    updateToastPosition(height);
                }
            }
        }))));
    }

    static Array<Toast> toastArray = new Array<Toast>();
    static Pool<Toast> toastPool = new ReflectionPool<Toast>(Toast.class);

    public static void show(Group parent, String message) {
        Toast toast = toastPool.obtain();
        toast.setMessage(message);
        toast.setPosition(parent.getWidth() / 2, parent.getHeight() / 2, Align.center);
        parent.addActor(toast);

        toastArray.add(toast);
        updateToastPosition(parent.getHeight() / 2);
    }

    public static void clearBuffer() {
        toastArray.clear();
        toastPool.clear();
    }

    private static void updateToastPosition(float height) {
        float y = height;
        for (int i = 0, s = toastArray.size; i < s; i++) {
            Toast toast = toastArray.get(i);
            toast.setY(y - toast.getHeight() / 2);
            y -= (toast.getHeight() + 5);
        }
    }
}
