package com.cncoderx.game.magictower;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.cncoderx.game.magictower.data.DataManager;
import com.cncoderx.game.magictower.data.FileLoader;
import com.cncoderx.game.magictower.data.Strings;
import com.cncoderx.game.magictower.drawable.DrawableContainer;

/**
 * Created by admin on 2017/4/7.
 */
public class Resources implements Disposable {
    private boolean isLoaded = false;
    private AssetManager assetManager;

    Resources() {
        assetManager = new AssetManager();
        assetManager.setLoader(Strings.class, new FileLoader<Strings>(
                Strings.class, assetManager.getFileHandleResolver()));
        assetManager.setLoader(DataManager.class, new FileLoader<DataManager>(
                DataManager.class, assetManager.getFileHandleResolver()));
    }

    public void load() {
        if (!isLoaded) {
            assetManager.load("logo.jpg", Texture.class);
            assetManager.load("bg.jpg", Texture.class);
            assetManager.load("battle_bg.jpg", Texture.class);
            assetManager.load("default.fnt", BitmapFont.class);
            assetManager.load("default2.fnt", BitmapFont.class);
            assetManager.load("ui.atlas", TextureAtlas.class);
            assetManager.load("characters.atlas", TextureAtlas.class);
            assetManager.load("strings", Strings.class);
            assetManager.load("data", DataManager.class);
            assetManager.load("stream.mp3", Music.class);
            assetManager.load("bgm01.mp3", Music.class);
            assetManager.load("bgm02.mp3", Music.class);
            assetManager.load("bgm03.mp3", Music.class);
            assetManager.load("bgm04.mp3", Music.class);
            assetManager.load("bgm05.mp3", Music.class);
            assetManager.load("access.mp3", Sound.class);
            assetManager.load("attack01.mp3", Sound.class);
            assetManager.load("attack02.mp3", Sound.class);
            assetManager.load("collect.mp3", Sound.class);
            assetManager.load("flip_over.mp3", Sound.class);
            assetManager.load("negative.mp3", Sound.class);
            assetManager.load("positive.mp3", Sound.class);
            assetManager.load("reward.mp3", Sound.class);
            assetManager.load("suck01.mp3", Sound.class);
            assetManager.load("suck02.mp3", Sound.class);
            assetManager.load("suck03.mp3", Sound.class);
            assetManager.load("suck04.mp3", Sound.class);
            assetManager.load("switch.mp3", Sound.class);
            isLoaded = true;
        }
    }

    public void unload(String fileName) {
        assetManager.unload(fileName);
    }

    public float update() {
        return isLoaded ? (assetManager.update() ? 1f : assetManager.getProgress()) : 0f;
    }

    public Music getMusic(String fileName) {
        Music music = null;
        if (isLoaded && assetManager.isLoaded(fileName)) {
            music = assetManager.get(fileName, Music.class);
        }
        return music;
    }

    public Sound getSound(String fileName) {
        Sound sound = null;
        if (isLoaded && assetManager.isLoaded(fileName)) {
            sound = assetManager.get(fileName, Sound.class);
        }
        return sound;
    }

    public BitmapFont getBitmapFont(String fileName) {
        BitmapFont font = null;
        if (isLoaded && assetManager.isLoaded(fileName)) {
            font = assetManager.get(fileName, BitmapFont.class);
            font.getRegion().getTexture().setFilter(
                    Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
        return font;
    }

    public BitmapFont getBitmapFont(String fileName, float scale) {
        BitmapFont font = null;
        if (isLoaded && assetManager.isLoaded(fileName)) {
            font = assetManager.get(fileName, BitmapFont.class);
            font.getData().scale(scale);
            font.getRegion().getTexture().setFilter(
                    Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
        return font;
    }

    public Texture getTexture(String fileName) {
        Texture texture = null;
        if (isLoaded && assetManager.isLoaded(fileName)) {
            texture = assetManager.get(fileName, Texture.class);
            texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
        return texture;
    }

    public TextureRegion getRegion(String atlas, String fileName) {
        TextureRegion textureRegion = null;
        TextureAtlas textureAtlas = null;
        if (isLoaded && assetManager.isLoaded(atlas)) {
            textureAtlas = assetManager.get(atlas, TextureAtlas.class);
            textureRegion = textureAtlas.findRegion(fileName);
        }
        return textureRegion;
    }

    public TextureRegion getRegion(String atlas, String fileName, int index) {
        TextureRegion textureRegion = null;
        TextureAtlas textureAtlas = null;
        if (isLoaded && assetManager.isLoaded(atlas)) {
            textureAtlas = assetManager.get(atlas, TextureAtlas.class);
            textureRegion = textureAtlas.findRegion(fileName, index);
        }
        return textureRegion;
    }

    public Drawable getDrawable(String atlas, String fileName) {
        Drawable drawable = null;
        TextureAtlas textureAtlas = null;
        if (isLoaded && assetManager.isLoaded(atlas)) {
            textureAtlas = assetManager.get(atlas, TextureAtlas.class);
            Array<TextureAtlas.AtlasRegion> regions = textureAtlas.findRegions(fileName);
            int size = regions.size;
            if (size == 1) {
                drawable = new TextureRegionDrawable(regions.get(0));
            } else if (size > 1) {
                Array<Drawable> drawables = new Array<Drawable>(size);
                for (TextureAtlas.AtlasRegion region : regions) {
                    drawables.add(new TextureRegionDrawable(region));
                }
                drawable = new DrawableContainer(drawables);
            }
        }
        return drawable;
    }

    public Animation<Drawable> getAnimation(String atlas, String fileName, float frameDuration) {
        Animation<Drawable> animation = null;
        TextureAtlas textureAtlas = null;
        if (isLoaded && assetManager.isLoaded(atlas)) {
            textureAtlas = assetManager.get(atlas, TextureAtlas.class);
            Array<Drawable> drawables = new Array<Drawable>();
            Array<TextureAtlas.AtlasRegion> regions = textureAtlas.findRegions(fileName);
            for (TextureAtlas.AtlasRegion region : regions) {
                drawables.add(new TextureRegionDrawable(region));
            }
            animation = new Animation<Drawable>(frameDuration, drawables);
        }
        return animation;
    }

    public String getString(int id) {
        if (isLoaded && assetManager.isLoaded("strings")) {
            Strings strings = assetManager.get("strings", Strings.class);
            return strings.getString(id);
        }
        return "";
    }

    public String getString(int id, Object... args) {
        if (isLoaded && assetManager.isLoaded("strings")) {
            Strings strings = assetManager.get("strings", Strings.class);
            return String.format(strings.getString(id), args);
        }
        return "";
    }

    public DataManager getDataManager() {
        DataManager dataManager = null;
        if (isLoaded && assetManager.isLoaded("data")) {
            dataManager = assetManager.get("data", DataManager.class);
        }
        return dataManager;
    }

    @Override
    public void dispose() {
        if (isLoaded) {
            isLoaded = false;
            assetManager.dispose();
        }
    }

    public static interface atlas {
        String ui = "ui.atlas";
        String character = "characters.atlas";
    }
}
