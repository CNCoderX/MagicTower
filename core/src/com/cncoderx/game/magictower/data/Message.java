package com.cncoderx.game.magictower.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/6/7.
 */
public class Message {
    int id;
    List<Item> items = new ArrayList<Item>();

    public static class Item {
        String drawable;
        String content;

        public String getDrawable() {
            return drawable;
        }

        public String getContent() {
            return content;
        }
    }

    public int getId() {
        return id;
    }

    public List<Item> getItems() {
        return items;
    }

    public String getDrawable(int index) {
        return items.get(index).getDrawable();
    }

    public String getContent(int index) {
        return items.get(index).getContent();
    }
}
