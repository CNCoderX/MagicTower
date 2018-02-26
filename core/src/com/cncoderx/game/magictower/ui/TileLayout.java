package com.cncoderx.game.magictower.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.cncoderx.game.magictower.GameContext;
import com.cncoderx.game.magictower.Resources;
import com.cncoderx.game.magictower.drawable.DrawableContainer;
import com.cncoderx.game.magictower.utils.Global;
import com.cncoderx.game.magictower.widget.HeroWidget;
import com.cncoderx.game.magictower.widget.NodeWidget;

/**
 * Created by admin on 2017/5/25.
 */
public class TileLayout extends Table {
    private HeroWidget mHeroWidget;
    private NodeWidget[][] mWidgets = new NodeWidget[Global.ROW_SIZE][Global.COL_SIZE];
    private Array<UpdateDrawableListener> mListeners = new Array<UpdateDrawableListener>();

    private int currentFrame;
    private boolean isRunning;
    private Timer timer = new Timer();

    public TileLayout() {
        setSize(Global.MAP_WIDTH, Global.MAP_HEIGHT);
        init();
    }

    private void init() {
        Resources resources = GameContext.instance().getResources();
        Cell<NodeWidget> cell = null;
        for (int y = 0; y < Global.ROW_SIZE; y++) {
            for (int x = 0; x < Global.COL_SIZE; x++) {
                int _y = Global.ROW_SIZE - y - 1;
                NodeWidget widget = new NodeWidget();
                mWidgets[_y][x] = widget;
                addUpdateDrawableListener(widget);
                cell = add(widget);
            }
            cell.row();
        }

        mHeroWidget = new HeroWidget(new DrawableContainer(
                new TextureRegionDrawable(resources.getRegion(Resources.atlas.character, "hero_left", 1)),
                new TextureRegionDrawable(resources.getRegion(Resources.atlas.character, "hero_left", 2)),
                new TextureRegionDrawable(resources.getRegion(Resources.atlas.character, "hero_left", 3)),
                new TextureRegionDrawable(resources.getRegion(Resources.atlas.character, "hero_left", 4)),
                new TextureRegionDrawable(resources.getRegion(Resources.atlas.character, "hero_up", 1)),
                new TextureRegionDrawable(resources.getRegion(Resources.atlas.character, "hero_up", 2)),
                new TextureRegionDrawable(resources.getRegion(Resources.atlas.character, "hero_up", 3)),
                new TextureRegionDrawable(resources.getRegion(Resources.atlas.character, "hero_up", 4)),
                new TextureRegionDrawable(resources.getRegion(Resources.atlas.character, "hero_right", 1)),
                new TextureRegionDrawable(resources.getRegion(Resources.atlas.character, "hero_right", 2)),
                new TextureRegionDrawable(resources.getRegion(Resources.atlas.character, "hero_right", 3)),
                new TextureRegionDrawable(resources.getRegion(Resources.atlas.character, "hero_right", 4)),
                new TextureRegionDrawable(resources.getRegion(Resources.atlas.character, "hero_down", 1)),
                new TextureRegionDrawable(resources.getRegion(Resources.atlas.character, "hero_down", 2)),
                new TextureRegionDrawable(resources.getRegion(Resources.atlas.character, "hero_down", 3)),
                new TextureRegionDrawable(resources.getRegion(Resources.atlas.character, "hero_down", 4))));
        addActor(mHeroWidget);
    }

    public HeroWidget getHeroWidget() {
        return mHeroWidget;
    }

    public NodeWidget getWidget(int x, int y) {
        return mWidgets[y][x];
    }

    public void addUpdateDrawableListener(UpdateDrawableListener listener) {
        mListeners.add(listener);
    }

    public void removeUpdateDrawableListener(UpdateDrawableListener listener) {
        mListeners.removeValue(listener, true);
    }

    public void clearUpdateDrawableListener() {
        mListeners.clear();
    }

    public void startAnimation() {
        if (!isRunning) {
            isRunning = true;
            currentFrame = 0;
            timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    updateDrawableState();
                    countFrame();
                }
            }, .25f);
        }
    }

    private void countFrame() {
        currentFrame++;
        if (currentFrame > 1) {
            currentFrame = 0;
        }
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                updateDrawableState();
                countFrame();
            }
        }, .25f);
    }

    private void updateDrawableState() {
        for (UpdateDrawableListener listener : mListeners) {
            listener.update(currentFrame);
        }
    }

    public void stopAnimation() {
        isRunning = false;
        currentFrame = 0;
        updateDrawableState();
    }

    public static interface UpdateDrawableListener {
        void update(int frame);
    }
}
