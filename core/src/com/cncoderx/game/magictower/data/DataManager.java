package com.cncoderx.game.magictower.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.StreamUtils;
import com.cncoderx.game.magictower.io.Reader;
import com.cncoderx.game.magictower.io.Serializable;
import com.cncoderx.game.magictower.io.Writer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/**
 * Created by admin on 2017/5/23.
 */
public class DataManager extends ILoader {
    private int capacity = 1024 * 1024;
    private IntMap<Tile> mTileIntMap = new IntMap<Tile>();
    private IntMap<Layer> mLayerIntMap = new IntMap<Layer>();
    private IntMap<Sprite> mSpriteIntMap = new IntMap<Sprite>();
    private IntMap<Message> mMessageIntMap = new IntMap<Message>();

    public static final String DATA_FILE = "data";
    public static final String SAVED_FILE = "bin/data.sav";
    public static final String SYMBOL_TYPE = "#";
    public static final String SYMBOL_SPLIT = "\\|";

    public DataManager() {
        super(null);
    }

    public DataManager(FileHandle file) {
        super(file);
        BufferedReader reader = Gdx.files.internal(DATA_FILE).reader(capacity);
        String line;
        try {
            int cursor = 0;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(SYMBOL_TYPE)) {
                    if (line.equals("#Tiles")) {
                        cursor = 1;
                    } else if (line.equals("#Layers")) {
                        cursor = 2;
                    } else if (line.equals("#Sprites")) {
                        cursor = 3;
                    } else if (line.equals("#Messages")) {
                        cursor = 4;
                    } else {
                        cursor = 0;
                    }
                    continue;
                }
                switch (cursor) {
                    case 1:
                        parseTiles(line.split(SYMBOL_SPLIT));
                        break;
                    case 2:
                        parseLayers(line.split(SYMBOL_SPLIT));
                        break;
                    case 3:
                        parseSprites(line.split(SYMBOL_SPLIT));
                        break;
                    case 4:
                        parseMessages(line.split(SYMBOL_SPLIT));
                        break;
                }
            }
        } catch (IOException e) {

        } finally {
            StreamUtils.closeQuietly(reader);
        }
    }

    private void parseTiles(String... args) {
        Tile tile = new Tile();
        tile.id = Integer.parseInt(args[0]);
        tile.drawable = args[1];
        mTileIntMap.put(tile.id, tile);
    }

    private void parseLayers(String... args) {
        Layer layer = new Layer();
        layer.id = Integer.parseInt(args[0]);
        layer.name = args[1];
        layer.drawable = args[2];
        mLayerIntMap.put(layer.id, layer);
    }

    private void parseSprites(String... args) {
        Sprite sprite = new Sprite();
        sprite.id = Integer.parseInt(args[0]);
        sprite.name = args[1];
        sprite.drawable = args[2];
        sprite.hp = Integer.parseInt(args[3]);
        sprite.attack = Integer.parseInt(args[4]);
        sprite.defence = Integer.parseInt(args[5]);
        sprite.money = Integer.parseInt(args[6]);
        sprite.exp = Integer.parseInt(args[7]);
        mSpriteIntMap.put(sprite.id, sprite);
    }

    private void parseMessages(String... args) {
        int id = Integer.parseInt(args[0]);
        String drawable = args[1];
        String content = args[2];

        Message message;
        if (mMessageIntMap.containsKey(id)) {
            message = mMessageIntMap.get(id);
        } else {
            message = new Message();
            message.id = id;
            mMessageIntMap.put(id, message);
        }

        Message.Item item = new Message.Item();
        item.drawable = drawable;
        item.content = content;
        message.items.add(item);
    }

    public Tile getTile(int id) {
        return mTileIntMap.get(id);
    }

    public Layer getLayer(int id) {
        return mLayerIntMap.get(id);
    }

    public Sprite getSprite(int id) {
        return mSpriteIntMap.get(id);
    }

    public Message getMessage(int id) {
        return mMessageIntMap.get(id);
    }

    public boolean hasSaveData() {
        return Gdx.files.local(SAVED_FILE).exists();
    }

    public boolean save(Serializable... serializes) {
        if (serializes == null) {
            throw new IllegalArgumentException("Null Serializable Objects!");
        }

        Writer writer = new Writer(ByteBuffer.allocate(capacity));
        for (Serializable serialize : serializes) {
            serialize.write(writer);
        }

        byte[] bytes = writer.toByteArray();
        FileHandle file = Gdx.files.local(SAVED_FILE);
        OutputStream stream = file.write(false);
        try {
            stream.write(bytes);
            stream.flush();
            return true;
        } catch (IOException e) {

        } finally {
            StreamUtils.closeQuietly(stream);
        }
        return false;
    }

    public boolean load(Serializable... serializes) {
        if (serializes == null) {
            throw new IllegalArgumentException("Null Serializable Objects!");
        }

        byte[] bytes = new byte[capacity];
        FileHandle file = Gdx.files.local(SAVED_FILE);
        if (!file.exists()) {
            return false;
        }

        Reader reader;
        InputStream stream = file.read();
        try {
            int size = stream.available();
            stream.read(bytes, 0, size);
            reader = new Reader(ByteBuffer.wrap(bytes, 0, size));
            for (Serializable serialize : serializes) {
                serialize.read(reader);
            }
            return true;
        } catch (IOException e) {

        } finally {
            StreamUtils.closeQuietly(stream);
        }
        return false;
    }

    public void setSpriteLevel(int level) {
        switch (level) {
            case 0:
                {
                    Sprite sprite = getSprite(63);
                    sprite.setHp(1500);
                    sprite.setAttack(830);
                    sprite.setDefence(730);
                    sprite.setMoney(80);
                    sprite.setExp(70);
                }
                {
                    Sprite sprite = getSprite(64);
                    sprite.setHp(2500);
                    sprite.setAttack(900);
                    sprite.setDefence(850);
                    sprite.setMoney(84);
                    sprite.setExp(75);
                }
                {
                    Sprite sprite = getSprite(65);
                    sprite.setHp(1200);
                    sprite.setAttack(980);
                    sprite.setDefence(900);
                    sprite.setMoney(88);
                    sprite.setExp(75);
                }
                {
                    Sprite sprite = getSprite(67);
                    sprite.setHp(15000);
                    sprite.setAttack(1000);
                    sprite.setDefence(1000);
                    sprite.setMoney(100);
                    sprite.setExp(100);
                }
                break;
            case 1:
                {
                    Sprite sprite = getSprite(63);
                    sprite.setHp(2000);
                    sprite.setAttack(1106);
                    sprite.setDefence(973);
                    sprite.setMoney(106);
                    sprite.setExp(93);
                }
                {
                    Sprite sprite = getSprite(64);
                    sprite.setHp(3333);
                    sprite.setAttack(1200);
                    sprite.setDefence(1133);
                    sprite.setMoney(112);
                    sprite.setExp(100);
                }
                {
                    Sprite sprite = getSprite(65);
                    sprite.setHp(1600);
                    sprite.setAttack(1306);
                    sprite.setDefence(1200);
                    sprite.setMoney(117);
                    sprite.setExp(100);
                }
                {
                    Sprite sprite = getSprite(67);
                    sprite.setHp(20000);
                    sprite.setAttack(1333);
                    sprite.setDefence(1333);
                    sprite.setMoney(133);
                    sprite.setExp(133);
                }
                break;
            case 2:
                {
                    Sprite sprite = getSprite(63);
                    sprite.setHp(3000);
                    sprite.setAttack(2212);
                    sprite.setDefence(1946);
                    sprite.setMoney(132);
                    sprite.setExp(116);
                }
                {
                    Sprite sprite = getSprite(64);
                    sprite.setHp(4999);
                    sprite.setAttack(2400);
                    sprite.setDefence(2266);
                    sprite.setMoney(140);
                    sprite.setExp(125);
                }
                {
                    Sprite sprite = getSprite(65);
                    sprite.setHp(2400);
                    sprite.setAttack(2612);
                    sprite.setDefence(2400);
                    sprite.setMoney(146);
                    sprite.setExp(125);
                }
                {
                    Sprite sprite = getSprite(67);
                    sprite.setHp(30000);
                    sprite.setAttack(2666);
                    sprite.setDefence(2666);
                    sprite.setMoney(166);
                    sprite.setExp(166);
                }
                break;
        }
    }

    public void setLordLevel(int level) {
        switch (level) {
            case 0:
            {
                Sprite sprite = getSprite(69);
                sprite.setHp(30000);
                sprite.setAttack(1700);
                sprite.setDefence(1500);
                sprite.setMoney(250);
                sprite.setExp(220);
            }
            break;
            case 1:
            {
                Sprite sprite = getSprite(69);
                sprite.setHp(45000);
                sprite.setAttack(2550);
                sprite.setDefence(2250);
                sprite.setMoney(312);
                sprite.setExp(275);
            }
            break;
            case 2:
            {
                Sprite sprite = getSprite(69);
                sprite.setHp(60000);
                sprite.setAttack(3400);
                sprite.setDefence(3000);
                sprite.setMoney(390);
                sprite.setExp(343);
            }
            break;
        }
    }
}
