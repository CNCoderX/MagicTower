package com.cncoderx.game.magictower.desktop;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ETC1;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FillViewport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by admin on 2017/6/7.
 */
public class TestApplication extends ApplicationAdapter {
    private Stage stage;

    @Override
    public void create() {
//        compressJPG("battle_bg.jpg", "battle_bg.etc1");
//        compressJPG("bg.jpg", "bg.etc1");


//        output2();

//        stage = new Stage(new FillViewport(400, 600));
//        stage.setDebugAll(true);
//        {
//            Label label = new Label("\u751f\u547d \u653b\u51fb \u9632\u5fa1", new Label.LabelStyle(
//                    new BitmapFont(Gdx.files.internal("default.fnt")), Color.WHITE));
//            label.setPosition(100, 100, Align.topLeft);
//            label.setFontScale(.6f);
//            label.setSize(label.getPrefWidth(), label.getPrefHeight() - 8);
//            stage.addActor(label);
//        }
//        {
//            Label label = new Label("\u884c\u6977123abcABC", new Label.LabelStyle(
//                    new BitmapFont(Gdx.files.internal("xk.fnt")), Color.WHITE));
//            label.setPosition(100, 200, Align.topLeft);
//            label.setFontScale(.6f);
//            label.setSize(label.getPrefWidth(), label.getPrefHeight());
//            stage.addActor(label);
//        }
    }

    private void compressJPG(String input, String output) {
        Pixmap pixmap = new Pixmap(Gdx.files.internal(input));
        ETC1.encodeImagePKM(pixmap).write(Gdx.files.internal(output));
    }

    private void output1() {
        Set<String> set = new HashSet<String>();
        FileHandle file = Gdx.files.internal("strings");
        BufferedReader reader = file.reader(10 * 1024);
        String line;
        try {
            while((line = reader.readLine()) != null) {
                if (line.trim().length() == 0) continue;
                String[] array = line.trim().split("\\|");
                for (int i = 0, s = array[1].length(); i < s; i++) {
                    char c = array[1].charAt(i);
                    set.add(String.valueOf(c));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        file = Gdx.files.internal("ending.txt");
        String ending = file.readString();
        for (int i = 0, s = ending.length(); i < s; i++) {
            char c = ending.charAt(i);
            set.add(String.valueOf(c));
        }
        String[] chars = new String[set.size()];
        set.toArray(chars);
        Arrays.sort(chars);

        Writer writer = Gdx.files.local("bin/strings_out").writer(false, "UTF-8");
        for (String c : chars) {
            try {
                writer.write(c);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void output2() {
        Set<String> set = new HashSet<String>();
        FileHandle file = Gdx.files.internal("message");
        BufferedReader reader = file.reader(10 * 1024);
        String line;
        try {
            while((line = reader.readLine()) != null) {
                if (line.trim().length() == 0) continue;
                String[] array = line.trim().split("\\|");
                for (int i = 0, s = array[2].length(); i < s; i++) {
                    char c = array[2].charAt(i);
                    set.add(String.valueOf(c));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        file = Gdx.files.internal("intro.txt");
        String ending = file.readString();
        for (int i = 0, s = ending.length(); i < s; i++) {
            char c = ending.charAt(i);
            set.add(String.valueOf(c));
        }
        String[] chars = new String[set.size()];
        set.toArray(chars);
        Arrays.sort(chars);

        Writer writer = Gdx.files.local("bin/message_out").writer(false, "UTF-8");
        for (String c : chars) {
            try {
                writer.write(c);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (stage != null) {
            stage.act();
            stage.draw();
        }
    }
}
