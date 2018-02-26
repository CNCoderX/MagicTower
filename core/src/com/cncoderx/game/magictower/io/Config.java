package com.cncoderx.game.magictower.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by admin on 2017/5/18.
 */
public class Config {
    private IniEditor mIniEditor;

    public Config(InputStream stream) {
        mIniEditor = new IniEditor();
        try {
            mIniEditor.load(stream);
        } catch (IOException e) {

        }
    }

    public List<String> getOptions(String section) {
        return mIniEditor.optionNames(section);
    }

    public String getString(String section, String option) {
        String value =  mIniEditor.get(section, option);
        return value == null ? "" : value;
    }

    public boolean getBool(String section, String option) {
        String value = mIniEditor.get(section, option);
        return Boolean.parseBoolean(value);
    }

    public int getInt(String section, String option) {
        String value = mIniEditor.get(section, option);
        if (value != null) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
            }
        }
        return 0;
    }

    public long getLong(String section, String option) {
        String value = mIniEditor.get(section, option);
        if (value != null) {
            try {
                return Long.parseLong(value);
            } catch (NumberFormatException e) {
            }
        }
        return 0;
    }

    public float getFloat(String section, String option) {
        String value = mIniEditor.get(section, option);
        if (value != null) {
            try {
                return Float.parseFloat(value);
            } catch (NumberFormatException e) {
            }
        }
        return 0;
    }

    public double getDouble(String section, String option) {
        String value = mIniEditor.get(section, option);
        if (value != null) {
            try {
                return Double.parseDouble(value);
            } catch (NumberFormatException e) {
            }
        }
        return 0;
    }
}
