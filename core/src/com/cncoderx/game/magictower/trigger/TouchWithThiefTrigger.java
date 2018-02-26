package com.cncoderx.game.magictower.trigger;

import com.cncoderx.game.magictower.data.Hero;
import com.cncoderx.game.magictower.data.Map;
import com.cncoderx.game.magictower.ui.ChatDialog;
import com.cncoderx.game.magictower.ui.Dialog;
import com.cncoderx.game.magictower.utils.Point;

/**
 * Created by admin on 2017/5/19.
 */
public class TouchWithThiefTrigger extends TouchTrigger {

    public TouchWithThiefTrigger(long id) {
        super(id);
    }

    @Override
    protected boolean condition(Point point) {
        int id = getGame().getMap().getLayerId(point.x, point.y);
        return id == 34;
    }

    @Override
    protected void action(Point point) {
        Hero hero = getGame().getHero();
        if (!hero.withNPC(Hero.TOUCH_WITH_THIEF)) {
            action1();
        } else if (!hero.withNPC(Hero.TOUCH_WITH_THIEF_WITH_AXE) && hero.hasProps(Hero.PROPS_AXE)){
            action2();
        }
    }

    private void action1() {
        ChatDialog dialog = getGame().getChatDialog();
        dialog.setDismissListener(new Dialog.DismissListener() {
            @Override
            public void onDismiss(Dialog dialog) {
                afterAction1();
            }
        });
        dialog.setMessage(getGame().getDataManager().getMessage(2));
        dialog.show();
        dialog.nextMessage();
    }

    private void action2() {
        ChatDialog dialog = getGame().getChatDialog();
        dialog.setDismissListener(new Dialog.DismissListener() {
            @Override
            public void onDismiss(Dialog dialog) {
                afterAction2();
            }
        });
        dialog.setMessage(getGame().getDataManager().getMessage(11));
        dialog.show();
        dialog.nextMessage();
    }

    private void afterAction1() {
        getGame().getHero().withNPC(Hero.TOUCH_WITH_THIEF, true);

        Map map = game.getMap();
        map.getFloor(2).removeLayer(1, 4);
    }

    private void afterAction2() {
        getGame().getHero().setProps(Hero.PROPS_AXE, false);
        getGame().getHero().withNPC(Hero.TOUCH_WITH_THIEF_WITH_AXE, true);

        Map map = game.getMap();
        map.getFloor(18).setTileId(5, 1, 1);
        map.getFloor(18).setTileId(5, 2, 1);

        Point point = getPoint();
        map.removeLayer(point.x, point.y);
        getGame().updateNode(point.x, point.y);
    }
}
