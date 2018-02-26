package com.cncoderx.game.magictower.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.cncoderx.game.magictower.BaseScreen;
import com.cncoderx.game.magictower.GameContext;
import com.cncoderx.game.magictower.Resources;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by admin on 2017/6/6.
 */
public class LoadingScreen extends BaseScreen {
    private ShapeRenderer mRenderer;
    private int progWidth, progHeight;
    private Resources resources;
    private AtomicBoolean resourceIsLoaded = new AtomicBoolean(false);

    @Override
    public void show() {
        mRenderer = new ShapeRenderer();
        progWidth = Gdx.graphics.getWidth() - 300;
        progHeight = 15;

        resources = GameContext.instance().getResources();
        resources.load();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        float progress = resources.update();

        mRenderer.setColor(Color.WHITE);
        mRenderer.begin(ShapeRenderer.ShapeType.Line);
        mRenderer.rect(150, 150, progWidth, progHeight);
        mRenderer.end();

        mRenderer.begin(ShapeRenderer.ShapeType.Filled);
        mRenderer.rect(150, 150, progWidth * progress, progHeight);
        mRenderer.end();

        if (progress == 1) {
            if (resourceIsLoaded.compareAndSet(false, true)) {
                GameContext.instance().setScreen(new GuildScreen());
            }
        }
    }

    @Override
    public void hide() {
        mRenderer.dispose();
    }
}
