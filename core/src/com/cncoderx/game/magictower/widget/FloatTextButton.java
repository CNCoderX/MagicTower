package com.cncoderx.game.magictower.widget;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Created by admin on 2017/5/24.
 */
public class FloatTextButton extends TextButton {
    private int prefWidth, prefHeight;

    public FloatTextButton(String text, Skin skin) {
        super(text, skin);
    }

    public FloatTextButton(String text, Skin skin, String styleName) {
        super(text, skin, styleName);
    }

    public FloatTextButton(String text, TextButtonStyle style) {
        super(text, style);
    }

    public void setPrefWidth(int prefWidth) {
        this.prefWidth = prefWidth;
    }

    public void setPrefHeight(int prefHeight) {
        this.prefHeight = prefHeight;
    }

    public void setPrefSize(int prefWidth, int prefHeight) {
        this.prefWidth = prefWidth;
        this.prefHeight = prefHeight;
    }

    @Override
    public float getPrefWidth() {
        return prefWidth;
    }

    @Override
    public float getPrefHeight() {
        return prefHeight;
    }
}
