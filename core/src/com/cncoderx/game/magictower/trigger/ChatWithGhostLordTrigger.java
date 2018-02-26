package com.cncoderx.game.magictower.trigger;

import com.cncoderx.game.magictower.data.Hero;
import com.cncoderx.game.magictower.data.Map;
import com.cncoderx.game.magictower.ui.ChatDialog;
import com.cncoderx.game.magictower.ui.Dialog;
import com.cncoderx.game.magictower.utils.Point;

/**
 * Created by admin on 2017/5/22.
 */
public class ChatWithGhostLordTrigger extends EnterTrigger {

    public ChatWithGhostLordTrigger(long id) {
        super(id);
    }

    @Override
    protected boolean condition(Point point) {
        Map map = getGame().getMap();
        return map.getCurrent() == 19 && point.x == 5 && point.y == 2;
    }

    @Override
    protected void action(Point point) {
        Hero hero = getGame().getHero();
        if (!hero.withNPC(Hero.TOUCH_WITH_GHOST_LORD_FIRST)) {
            getGame().getWidget().stopMoveDown();
            action1();
        }
//        else if (!hero.withNPC(Hero.TOUCH_WITH_GHOST_LORD_SECOND)) {
//            action2();
//        }
    }

    private void action1() {
        ChatDialog dialog = getGame().getChatDialog();
        dialog.setDismissListener(new Dialog.DismissListener() {
            @Override
            public void onDismiss(Dialog dialog) {
                getGame().getHero().withNPC(Hero.TOUCH_WITH_GHOST_LORD_FIRST, true);
                getGame().getTriggerManager().notifyTrigger(Trigger.ID_TOUCH);
            }
        });
        dialog.setMessage(getGame().getDataManager().getMessage(8));
        dialog.show();
        dialog.nextMessage();
    }

    private void action2() {
        ChatDialog dialog = getGame().getChatDialog();
        dialog.setDismissListener(new Dialog.DismissListener() {
            @Override
            public void onDismiss(Dialog dialog) {
                getGame().getHero().withNPC(Hero.TOUCH_WITH_GHOST_LORD_SECOND, true);
                getGame().getTriggerManager().notifyTrigger(Trigger.ID_TOUCH);
            }
        });
        dialog.setMessage(getGame().getDataManager().getMessage(17));
        dialog.show();
        dialog.nextMessage();
    }
}
