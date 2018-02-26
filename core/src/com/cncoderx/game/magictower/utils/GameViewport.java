package com.cncoderx.game.magictower.utils;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

/**
 * Created by admin on 2017/5/24.
 */
public class GameViewport extends ScalingViewport {

    public GameViewport (float worldWidth, float worldHeight) {
        super(Scaling.fit, worldWidth, worldHeight);
    }

    public GameViewport (float worldWidth, float worldHeight, Camera camera) {
        super(Scaling.fit, worldWidth, worldHeight, camera);
    }

    @Override
    public void update (int screenWidth, int screenHeight, boolean centerCamera) {
        Vector2 scaled = getScaling().apply(getWorldWidth(), getWorldHeight(), screenWidth, screenHeight);
        int viewportWidth = Math.round(scaled.x);
        int viewportHeight = Math.round(scaled.y);

        // Top.
        setScreenBounds((screenWidth - viewportWidth) / 2, screenHeight - viewportHeight, viewportWidth, viewportHeight);

        apply(centerCamera);
    }
}
