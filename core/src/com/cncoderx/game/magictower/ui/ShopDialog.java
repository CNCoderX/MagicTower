package com.cncoderx.game.magictower.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.cncoderx.game.magictower.GameContext;
import com.cncoderx.game.magictower.Resources;

/**
 * Created by admin on 2017/5/27.
 */
public class ShopDialog extends Dialog {
    private Label labIntro;
    private Label[] labItems = new Label[3];

    private int cursor;
    private Drawable mDrawable;
    private OnSelectedListener onSelectedListener;

    public ShopDialog(Group parent) {
        super(parent);
        setName("ShopDialog");
        setSize(260, 240);
        Resources resources = GameContext.instance().getResources();
        mDrawable = new NinePatchDrawable(new NinePatch(
                resources.getRegion(Resources.atlas.ui, "frame2"), 5, 5, 5, 5));
        BitmapFont font = resources.getBitmapFont("default.fnt");
        BitmapFont font2 = resources.getBitmapFont("default2.fnt");
        {
            Label label = new Label("", new Label.LabelStyle(font2, Color.WHITE));
            label.setAlignment(Align.topLeft);
            label.setSize(240, 80);
            label.setPosition(10, 150);
            label.setWrap(true);
            addActor(label);
            labIntro = label;
        }
        {
            Label label = new Label("", new Label.LabelStyle(font, Color.WHITE));
            label.setAlignment(Align.center);
            label.setFontScale(.7f);
            label.setSize(240, 30);
            label.setPosition(10, 120);
            addActor(label);
            labItems[0] = label;
        }
        {
            Label label = new Label("", new Label.LabelStyle(font, Color.WHITE));
            label.setAlignment(Align.center);
            label.setFontScale(.7f);
            label.setSize(240, 30);
            label.setPosition(10, 80);
            addActor(label);
            labItems[1] = label;
        }
        {
            Label label = new Label("", new Label.LabelStyle(font, Color.WHITE));
            label.setAlignment(Align.center);
            label.setFontScale(.7f);
            label.setSize(240, 30);
            label.setPosition(10, 40);
            addActor(label);
            labItems[2] = label;
        }
        {
            Label label = new Label(resources.getString(27), new Label.LabelStyle(font2, Color.WHITE));
            label.setAlignment(Align.bottom);
            label.setFontScale(.8f);
            label.setSize(240, 20);
            label.setPosition(10, 10);
            label.setWrap(true);
            addActor(label);
        }
        labItems[cursor].setColor(Color.GREEN);
    }

    public void setOnSelectedListener(OnSelectedListener listener) {
        this.onSelectedListener = listener;
    }

    public void setIntro(String text) {
        labIntro.setText(text);
    }

    public void setItemText1(String text) {
        labItems[0].setText(text);
    }

    public void setItemText2(String text) {
        labItems[1].setText(text);
    }

    public void setItemText3(String text) {
        labItems[2].setText(text);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (mDrawable != null) {
            mDrawable.draw(batch, getX(), getY(), getWidth(), getHeight());
        }
        super.draw(batch, parentAlpha);
    }

    @Override
    public boolean remove() {
        if (super.remove()) {
            labIntro.setText("");
            labItems[0].setText("");
            labItems[1].setText("");
            labItems[2].setText("");
            return true;
        }
        return false;
    }

    @Override
    public boolean onPressLeft() {
        dismiss();
        return true;
    }

    @Override
    public boolean onPressUp() {
        int oldCursor = cursor;
        if (cursor == 0) {
            cursor = 2;
        } else {
            cursor--;
        }
        labItems[oldCursor].setColor(Color.WHITE);
        labItems[cursor].setColor(Color.GREEN);
        GameContext.instance().getResources().getSound("switch.mp3").play();
        return super.onPressUp();
    }

    @Override
    public boolean onPressRight() {
        if (onSelectedListener != null) {
            onSelectedListener.select(cursor);
        }
        return super.onPressRight();
    }

    @Override
    public boolean onPressDown() {
        int oldCursor = cursor;
        if (cursor == 2) {
            cursor = 0;
        } else {
            cursor++;
        }
        labItems[oldCursor].setColor(Color.WHITE);
        labItems[cursor].setColor(Color.GREEN);
        GameContext.instance().getResources().getSound("switch.mp3").play();
        return super.onPressDown();
    }

    @Override
    public void reset() {
        super.reset();
        cursor = 0;
        setOnSelectedListener(null);
    }

    public static interface OnSelectedListener {
        void select(int index);
    }
}
