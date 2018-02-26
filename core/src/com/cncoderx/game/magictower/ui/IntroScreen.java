package com.cncoderx.game.magictower.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.StreamUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cncoderx.game.magictower.BaseScreen;
import com.cncoderx.game.magictower.GameContext;
import com.cncoderx.game.magictower.Resources;
import com.cncoderx.game.magictower.utils.Global;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by admin on 2017/5/31.
 */
public class IntroScreen extends BaseScreen {
    private Viewport mViewport;
    private Stage mStage;

    @Override
    public void show() {
        super.show();
        mViewport = new FitViewport(
                Global.SCREEN_WIDTH, Global.SCREEN_HEIGHT);
        mStage = new Stage(mViewport) {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.BACK ||
                        keyCode == Input.Keys.ESCAPE) {
                    back();
                    return true;
                }
                return super.keyDown(keyCode);
            }
        };

        FileHandle file = Gdx.files.internal("intro.txt");
        BufferedReader reader = file.reader(1024);
        StringBuffer stringBuffer = new StringBuffer();
        String line;
        try {
            while((line = reader.readLine()) != null) {
                if (line.trim().length() > 0) {
                    stringBuffer.append(line.trim());
                }
                stringBuffer.append('\n');
            }
        } catch (IOException e) {

        } finally {
            StreamUtils.closeQuietly(reader);
        }

        Resources resources = GameContext.instance().getResources();
        Label label = new Label(stringBuffer.toString(), new Label.LabelStyle(resources.getBitmapFont("default2.fnt"), Color.WHITE));
        label.setSize(400, 650);
        label.setWrap(true);
        label.setPosition(Global.SCREEN_WIDTH / 2, Global.SCREEN_HEIGHT - 10, Align.top);
        label.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                back();
            }
        });
        mStage.addActor(label);

        Gdx.input.setInputProcessor(mStage);
        Gdx.input.setCatchBackKey(true);
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
        mStage.dispose();
    }
}
