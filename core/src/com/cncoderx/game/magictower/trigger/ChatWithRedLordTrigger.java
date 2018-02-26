package com.cncoderx.game.magictower.trigger;

import com.cncoderx.game.magictower.data.Hero;
import com.cncoderx.game.magictower.data.Map;
import com.cncoderx.game.magictower.ui.ChatDialog;
import com.cncoderx.game.magictower.ui.Dialog;
import com.cncoderx.game.magictower.utils.Point;

/**
 * Created by admin on 2017/5/19.
 */
public class ChatWithRedLordTrigger extends EnterTrigger {

    public ChatWithRedLordTrigger(long id) {
        super(id);
    }

    @Override
    protected boolean condition(Point point) {
        Map map = getGame().getMap();
        return map.getCurrent() == 16 && point.x == 5 && point.y == 7;
    }

    @Override
    protected void action(Point point) {
        Hero hero = getGame().getHero();
        if (!hero.withNPC(Hero.TOUCH_WITH_RED_LORD)) {
            getGame().getWidget().stopMoveDown();
            action1();
        }
    }

    private void action1() {
        ChatDialog dialog = getGame().getChatDialog();
        dialog.setDismissListener(new Dialog.DismissListener() {
            @Override
            public void onDismiss(Dialog dialog) {
                getGame().getHero().withNPC(Hero.TOUCH_WITH_RED_LORD, true);
            }
        });
        dialog.setMessage(getGame().getDataManager().getMessage(19));
        dialog.show();
        dialog.nextMessage();
    }
}
