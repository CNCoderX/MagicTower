package com.cncoderx.game.magictower.trigger;

import com.cncoderx.game.magictower.GameContext;
import com.cncoderx.game.magictower.data.Hero;
import com.cncoderx.game.magictower.data.Map;
import com.cncoderx.game.magictower.ui.ChatDialog;
import com.cncoderx.game.magictower.ui.Dialog;
import com.cncoderx.game.magictower.utils.Point;

/**
 * Created by admin on 2017/5/19.
 */
public class TouchWithPrincessTrigger extends TouchTrigger {

    public TouchWithPrincessTrigger(long id) {
        super(id);
    }

    @Override
    protected boolean condition(Point point) {
        int id = getGame().getMap().getLayerId(point.x, point.y);
        return id == 35;
    }

    @Override
    protected void action(Point point) {
        Hero hero = getGame().getHero();
        if (!hero.withNPC(Hero.TOUCH_WITH_PRINCESS)) {
            action1();
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
        dialog.setMessage(getGame().getDataManager().getMessage(7));
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
        dialog.setMessage(getGame().getDataManager().getMessage(16));
        dialog.show();
        dialog.nextMessage();
    }

    private void afterAction1() {
        getGame().getHero().withNPC(Hero.TOUCH_WITH_PRINCESS, true);

        Map map = game.getMap();
        map.setLayerId(10, 0, 91);
        getGame().updateNode(10, 0);
    }

    private void afterAction2() {
        GameContext.instance().success();
    }
}
