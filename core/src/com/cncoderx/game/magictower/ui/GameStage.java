package com.cncoderx.game.magictower.ui;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.cncoderx.game.magictower.GameContext;
import com.cncoderx.game.magictower.OnTouchStateListener;
import com.cncoderx.game.magictower.utils.GameViewport;

/**
 * Created by admin on 2017/5/27.
 */
public class GameStage extends Stage {

    public GameStage(GameViewport viewport) {
        super(viewport);
    }

    @Override
    public boolean keyDown(int keyCode) {
        OnTouchStateListener listener = GameContext.instance().getOnTouchStateListeners();
        switch (keyCode) {
            case Input.Keys.LEFT:
                return listener.onPressLeft();
            case Input.Keys.UP:
                return listener.onPressUp();
            case Input.Keys.RIGHT:
                return listener.onPressRight();
            case Input.Keys.DOWN:
                return listener.onPressDown();
            case Input.Keys.BACK:
            case Input.Keys.ESCAPE:
                return listener.onBackPressed();
        }
        return super.keyDown(keyCode);
    }

    @Override
    public boolean keyUp(int keyCode) {
        OnTouchStateListener listener = GameContext.instance().getOnTouchStateListeners();
        switch (keyCode) {
            case Input.Keys.LEFT:
                return listener.onReleaseLeft();
            case Input.Keys.UP:
                return listener.onReleaseUp();
            case Input.Keys.RIGHT:
                return listener.onReleaseRight();
            case Input.Keys.DOWN:
                return listener.onReleaseDown();
        }
        return super.keyUp(keyCode);
    }
}
