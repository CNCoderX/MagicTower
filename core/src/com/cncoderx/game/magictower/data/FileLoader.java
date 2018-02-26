package com.cncoderx.game.magictower.data;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Constructor;
import com.badlogic.gdx.utils.reflect.ReflectionException;

/**
 * Created by admin on 2017/5/25.
 */
public class FileLoader<T extends ILoader> extends AsynchronousAssetLoader<T, FileLoader.FileParameter<T>> {
    private Class<T> clazz;
    private T t;

    public FileLoader(Class<T> clazz, FileHandleResolver resolver) {
        super(resolver);
        this.clazz = clazz;
    }

    protected T getLoadedFile() {
        return t;
    }

    @Override
    public void loadAsync(AssetManager manager, String fileName, FileHandle file, FileParameter parameter) {
        try {
            Constructor constructor = ClassReflection.getDeclaredConstructor(clazz, FileHandle.class);
            t = (T) constructor.newInstance(file);
        } catch (ReflectionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T loadSync(AssetManager manager, String fileName, FileHandle file, FileParameter parameter) {
        T t = this.t;
        this.t = null;
        return t;
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, FileParameter parameter) {
        return null;
    }

    static public class FileParameter<T> extends AssetLoaderParameters<T> {
    }
}
