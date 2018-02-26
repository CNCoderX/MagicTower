package com.cncoderx.game.magictower.data;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.IntMap;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by admin on 2017/6/8.
 */
public class Strings extends ILoader {
    private IntMap<String> mStringIntMap = new IntMap<String>();
    public static final String SYMBOL_SPLIT = "\\|";

    public Strings(FileHandle file) {
        super(file);
        BufferedReader reader = file.reader(1024);
        String line;
        try {
            while((line = reader.readLine()) != null) {
                if (line.trim().length() == 0)
                    continue;

                String[] ss = line.split(SYMBOL_SPLIT);
                int key = Integer.parseInt(ss[0]);
                String value = ss[1];
                mStringIntMap.put(key, value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void putString(int id, String string) {
        mStringIntMap.put(id, string);
    }

    public String getString(int id) {
        return mStringIntMap.get(id, "");
    }
}
