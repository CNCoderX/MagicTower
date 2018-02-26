package com.cncoderx.game.magictower.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.cncoderx.game.magictower.GameContext;
import com.cncoderx.game.magictower.Resources;
import com.cncoderx.game.magictower.utils.Global;
import com.cncoderx.game.magictower.widget.XLabel;

/**
 * Created by admin on 2017/5/31.
 */
public class EndingScene extends Scene {
    private DismissListener mDismissListener;

    @Override
    public void onShow() {
        setSize(Global.MAP_WIDTH, Global.MAP_HEIGHT);

        Resources resources = GameContext.instance().getResources();
        BitmapFont font = resources.getBitmapFont("default.fnt");
        String text = Gdx.files.internal("ending.txt").readString();
        XLabel label = new XLabel("", new Label.LabelStyle(font, Color.WHITE));
        label.setFontScale(.7f);
        label.setWrap(true);
        label.setWidth(300);
        label.setDeltaTime(.1f);
        label.showTextGradually(text);
        label.setAlignment(Align.left);
        label.setPosition(42, Global.MAP_HEIGHT / 2);
        addActor(label);

        label.addAction(Actions.sequence(Actions.delay(15f, Actions.alpha(0, 3f)),
                Actions.delay(1f, Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        if (mDismissListener != null) {
                            mDismissListener.onDismiss();
                        }
                    }
                }))));
    }

    public void setDismissListener(DismissListener listener) {
        mDismissListener = listener;
    }

    @Override
    public void onHide() {

    }

    public static interface DismissListener {
        void onDismiss();
    }
}
