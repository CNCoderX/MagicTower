package com.cncoderx.game.magictower.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.cncoderx.game.magictower.GameContext;
import com.cncoderx.game.magictower.OnTouchStateListener;
import com.cncoderx.game.magictower.Resources;
import com.cncoderx.game.magictower.utils.Global;
import com.cncoderx.game.magictower.widget.FloatTextButton;

/**
 * Created by Administrator on 2017/5/29.
 */
public class MenuDialog extends Container<Table> implements OnTouchStateListener {
    private Listener listener;
    private FloatTextButton loadButton;

    public MenuDialog() {
        setSize(Global.SCREEN_WIDTH, Global.SCREEN_HEIGHT);
        Resources resources = GameContext.instance().getResources();
        setBackground(new TextureRegionDrawable(resources.getRegion(Resources.atlas.ui, "transparent")));

        Table table = new Table();
        table.setBackground(new NinePatchDrawable(new NinePatch(
                resources.getRegion(Resources.atlas.ui, "frame2"), 5, 5, 5, 5)));

        BitmapFont font = resources.getBitmapFont("default.fnt");
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(null, null, null, font);
        style.fontColor = Color.WHITE;
        style.disabledFontColor = Color.GRAY;
        {
            FloatTextButton button = new FloatTextButton(resources.getString(5), style);
            table.add(button).size(280, 48).padLeft(10).padRight(10).padTop(10).padBottom(5).row();
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    hide();
                    if (listener != null) {
                        listener.resume();
                    }
                }
            });
        }
        {
            FloatTextButton button = new FloatTextButton(resources.getString(6), style);
            table.add(button).size(280, 48).padLeft(10).padRight(10).padTop(5).padBottom(5).row();
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    hide();
                    if (listener != null) {
                        listener.load();
                    }
                }
            });
            loadButton = button;
        }
        {
            FloatTextButton button = new FloatTextButton(resources.getString(7), style);
            table.add(button).size(280, 48).padLeft(10).padRight(10).padTop(5).padBottom(5).row();
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    hide();
                    if (listener != null) {
                        listener.save();
                    }
                }
            });
        }
        {
            FloatTextButton button = new FloatTextButton(resources.getString(8), style);
            table.add(button).size(280, 48).padLeft(10).padRight(10).padTop(5).padBottom(5).row();
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    hide();
                    if (listener != null) {
                        listener.restart();
                    }
                }
            });
        }
        {
            FloatTextButton button = new FloatTextButton(resources.getString(9), style);
            table.add(button).size(280, 48).padLeft(10).padRight(10).padTop(5).padBottom(10);
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    hide();
                    if (listener != null) {
                        listener.exit();
                    }
                }
            });
        }

        float prefWidth = table.getPrefWidth();
        float prefHeight = table.getPrefHeight();
        table.setSize(prefWidth, prefHeight);
        setActor(table);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void show(Stage stage) {
        if (GameContext.instance().getResources().getDataManager().hasSaveData()) {
            loadButton.setDisabled(false);
            loadButton.setTouchable(Touchable.enabled);
        } else {
            loadButton.setDisabled(true);
            loadButton.setTouchable(Touchable.disabled);
        }
        GameContext.instance().addTouchStateListener(this);
        stage.addActor(this);
    }

    public void hide() {
        remove();
        GameContext.instance().removeTouchStateListener(this);
    }

    @Override
    public boolean onBackPressed() {
        hide();
        return true;
    }

    @Override
    public boolean onPressLeft() {
        return true;
    }

    @Override
    public boolean onPressUp() {
        return true;
    }

    @Override
    public boolean onPressRight() {
        return true;
    }

    @Override
    public boolean onPressDown() {
        return true;
    }

    @Override
    public boolean onReleaseLeft() {
        return true;
    }

    @Override
    public boolean onReleaseUp() {
        return true;
    }

    @Override
    public boolean onReleaseRight() {
        return true;
    }

    @Override
    public boolean onReleaseDown() {
        return true;
    }

    public static interface Listener {
        void resume();
        void load();
        void save();
        void restart();
        void exit();
    }
}
