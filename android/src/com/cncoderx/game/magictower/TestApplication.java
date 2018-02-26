package com.cncoderx.game.magictower;

import android.os.Environment;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.StreamUtils;
import com.cncoderx.game.magictower.data.DataManager;
import com.cncoderx.game.magictower.data.Floor;
import com.cncoderx.game.magictower.data.Hero;
import com.cncoderx.game.magictower.data.Map;
import com.cncoderx.game.magictower.io.Writer;
import com.cncoderx.game.magictower.utils.Global;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/**
 * Created by admin on 2017/6/7.
 */
public class TestApplication extends ApplicationAdapter {
    @Override
    public void create() {
        super.create();
//        InputStream stream = Gdx.files.local("bin/data.sav").read();
//        try {
//            byte[] buffer = new byte[stream.available()];
//            stream.read(buffer);
//            OutputStream outputStream = new FileOutputStream(Environment.getExternalStorageDirectory() + "/data.sav");
//            outputStream.write(buffer);
//            outputStream.flush();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        Map map = new Map();
        Hero hero = new Hero();
        DataManager dataManager = new DataManager();
        dataManager.load(map, hero);

        for (int i = 0; i < map.getSize(); i++) {
            Floor floor = map.getFloor(i);
            for (int y = Global.ROW_SIZE - 1; y >= 0; y--) {
                for (int x = 0; x < Global.COL_SIZE; x++) {
                    switch (floor.getLayerId(x, y)) {
                        case 70:
                            floor.setLayerId(x, y, 64);
                            break;
                        case 71:
                        case 72:
                            floor.setLayerId(x, y, 65);
                            break;
                        case 68:
                        case 75:
                            floor.setLayerId(x, y, 67);
                            break;
                        case 74:
                        case 76:
                            floor.setLayerId(x, y, 63);
                            break;
                        case 73:
                        case 77:
                            floor.setLayerId(x, y, 69);
                            break;
                    }
                }
            }
        }

        dataManager.save(map, hero);

    }
}
