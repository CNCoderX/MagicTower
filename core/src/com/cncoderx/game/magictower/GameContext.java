package com.cncoderx.game.magictower;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cncoderx.game.magictower.ui.LoadingScreen;
import com.cncoderx.game.magictower.widget.Toast;

public class GameContext extends Game {
	private Resources mResources;
	private GameListener mGameListener;
	private OnTouchStateListeners mOnTouchStateListeners;
	private BitmapFont mBitmapFont;
	private SpriteBatch mSpriteBatch;
	private boolean enableFPS = false;
	private static GameContext context;

	public static GameContext instance() {
		return context;
	}

	@Override
	public void create () {
		context = this;
		mOnTouchStateListeners = new OnTouchStateListeners();
		mResources = new Resources();
		mSpriteBatch = new SpriteBatch();
		mBitmapFont = new BitmapFont();

		setScreen(new LoadingScreen());
	}

//	public <T extends Screen> T setScreen(Class<T> clazz) {
//		T t = (T) mScreens.get(clazz);
//		setScreen(t);
//		return t;
//	}

	public void success() {
		if (mGameListener != null) {
			mGameListener.success();
		}
	}

	public void failure() {
		if (mGameListener != null) {
			mGameListener.failure();
		}
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
		if (enableFPS) {
			renderFPS();
		}
	}

	private void renderFPS() {
		mSpriteBatch.begin();
		mBitmapFont.draw(mSpriteBatch, "fps: " + Gdx.graphics.getFramesPerSecond(),
				10, Gdx.graphics.getHeight() - 10);
		mSpriteBatch.end();
	}

	public void setEnableFPS(boolean enable) {
		this.enableFPS = enable;
	}

	public Resources getResources() {
		return mResources;
	}

	public void setGameListener(GameListener gameListener) {
		mGameListener = gameListener;
	}

	public void addTouchStateListener(OnTouchStateListener listener) {
		mOnTouchStateListeners.add(listener);
	}

	public void removeTouchStateListener(OnTouchStateListener listener) {
		mOnTouchStateListeners.remove(listener);
	}

	public OnTouchStateListeners getOnTouchStateListeners() {
		return mOnTouchStateListeners;
	}

	@Override
	public void dispose() {
		super.dispose();
		context = null;
		mSpriteBatch.dispose();
		mBitmapFont.dispose();
		mResources.dispose();
		mOnTouchStateListeners.clear();

		Toast.clearBuffer();
	}

	public static interface GameListener {
		void success();
		void failure();
	}
}
