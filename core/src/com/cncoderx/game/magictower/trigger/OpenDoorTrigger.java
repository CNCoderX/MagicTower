package com.cncoderx.game.magictower.trigger;

import com.cncoderx.game.magictower.GameContext;
import com.cncoderx.game.magictower.data.Hero;
import com.cncoderx.game.magictower.drawable.LazyAnimationDrawable;
import com.cncoderx.game.magictower.utils.Point;

/**
 * Created by ll on 2017/5/20.
 */
public class OpenDoorTrigger extends TouchTrigger {

    public OpenDoorTrigger(long id) {
        super(id);
    }

    @Override
    protected boolean condition(Point point) {
        Hero hero = getGame().getHero();
        int id = getGame().getMap().getLayerId(point.x, point.y);
        return id == 90 ||
                (id == 93 && hero.getYellowKey() > 0) ||
                (id == 94 && hero.getBlueKey() > 0) ||
                (id == 95 && hero.getRedKey() > 0);
    }

    @Override
    protected void action(Point point) {
        Hero hero = getGame().getHero();
        int id = getGame().getMap().getLayerId(point.x, point.y);
        switch (id) {
            case 90:
                openDoor();
                break;
            case 93:
                hero.putYellowKey(-1);
                getGame().updateUI();
                openDoor();
                break;
            case 94:
                hero.putBlueKey(-1);
                getGame().updateUI();
                openDoor();
                break;
            case 95:
                hero.putRedKey(-1);
                getGame().updateUI();
                openDoor();
                break;
        }

    }

    private void openDoor() {
        final Point point = getPoint();
        LazyAnimationDrawable drawable = (LazyAnimationDrawable) getGame().getWidget(point.x, point.y).getDrawable();
        drawable.setFinishedListener(new LazyAnimationDrawable.FinishedListener() {
            @Override
            public void finish() {
                getGame().getMap().removeLayer(point.x, point.y);
                getGame().updateNode(point.x, point.y);
            }
        });
        drawable.start();

        GameContext.instance().getResources().getSound("access.mp3").play();
    }
}
