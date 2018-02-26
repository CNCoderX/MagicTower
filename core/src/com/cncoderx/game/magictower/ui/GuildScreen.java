package com.cncoderx.game.magictower.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cncoderx.game.magictower.BaseScreen;
import com.cncoderx.game.magictower.GameContext;
import com.cncoderx.game.magictower.Resources;
import com.cncoderx.game.magictower.utils.Global;
import com.cncoderx.game.magictower.widget.FloatTextButton;

/**
 * Created by admin on 2017/5/24.
 */
public class GuildScreen extends BaseScreen {
    private Viewport mViewport;
    private Stage mStage;
    private Music bgm;
    private VerticalGroup group;

    @Override
    public void show() {
        super.show();
        mViewport = new FitViewport(
                Global.SCREEN_WIDTH, Global.SCREEN_HEIGHT);
        mStage = new Stage(mViewport){
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.BACK ||
                        keyCode == Input.Keys.ESCAPE) {
                    Gdx.app.exit();
                    return true;
                }
                return super.keyDown(keyCode);
            }
        };
//        mStage.setDebugAll(true);

        Resources resources = GameContext.instance().getResources();

        group = new VerticalGroup();
        group.space(15);
        {
            Image image = new Image(resources.getTexture("logo.jpg"));
            group.addActor(image);
            image.getColor().a = 0f;
            image.addAction(Actions.delay(.25f, Actions.alpha(1, .25f)));
        }

        BitmapFont font = resources.getBitmapFont("default.fnt");
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(null, null, null, font);
        style.fontColor = Color.WHITE;
        style.disabledFontColor = Color.GRAY;
        final int buttonWidth = 120, buttonHeight = 60;
        {
            FloatTextButton button = new FloatTextButton(resources.getString(1), style);
            button.setPrefSize(buttonWidth, buttonHeight);
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    GameScreen screen = new GameScreen();
                    screen.setBackScreen(GuildScreen.this);
                    GameContext.instance().setScreen(screen);
                    screen.newGame();
                }
            });
            group.addActor(button);
            button.getColor().a = 0;
            button.addAction(Actions.delay(2 * .25f, Actions.alpha(1, .25f)));
        }
        {
            FloatTextButton button = new FloatTextButton(resources.getString(2), style);
            button.setPrefSize(buttonWidth, buttonHeight);
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    GameScreen screen = new GameScreen();
                    screen.setBackScreen(GuildScreen.this);
                    GameContext.instance().setScreen(screen);
                    screen.loadGame();
                }
            });
            if (!GameContext.instance().getResources().getDataManager().hasSaveData()) {
                button.setDisabled(true);
                button.setTouchable(Touchable.disabled);
            }
            group.addActor(button);
            button.getColor().a = 0;
            button.addAction(Actions.delay(3 * .25f, Actions.alpha(1, .25f)));
        }
        {
            FloatTextButton button = new FloatTextButton(resources.getString(3), style);
            button.setPrefSize(buttonWidth, buttonHeight);
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    IntroScreen screen = new IntroScreen();
                    screen.setBackScreen(GuildScreen.this);
                    GameContext.instance().setScreen(screen);
                }
            });
            group.addActor(button);
            button.getColor().a = 0;
            button.addAction(Actions.delay(4 * .25f, Actions.alpha(1, .25f)));
        }
        {
            FloatTextButton button = new FloatTextButton(resources.getString(4), style);
            button.setPrefSize(buttonWidth, buttonHeight);
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Gdx.app.exit();
                }
            });
            group.addActor(button);
            button.getColor().a = 0;
            button.addAction(Actions.delay(5 * .25f, Actions.alpha(1, .25f)));
        }
        group.setSize(group.getPrefWidth(), group.getPrefHeight());
        group.setPosition(Global.SCREEN_WIDTH / 2, Global.SCREEN_HEIGHT / 2, Align.center);
        mStage.addActor(group);

        Gdx.input.setInputProcessor(mStage);
        Gdx.input.setCatchBackKey(true);

        bgm = GameContext.instance().getResources().getMusic("stream.mp3");
        bgm.play();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        mViewport.update(width, height, true);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        mStage.act();
        mStage.draw();
    }

    @Override
    public void hide() {
        super.hide();
        bgm.stop();
        mStage.dispose();
    }
}
