package com.cncoderx.game.magictower.trigger;

import com.cncoderx.game.magictower.GameContext;
import com.cncoderx.game.magictower.Resources;
import com.cncoderx.game.magictower.data.Hero;
import com.cncoderx.game.magictower.data.Map;
import com.cncoderx.game.magictower.ui.ChatDialog;
import com.cncoderx.game.magictower.ui.Dialog;
import com.cncoderx.game.magictower.utils.Point;

/**
 * Created by admin on 2017/5/19.
 */
public class TouchWithAngleTrigger extends TouchTrigger {

    public TouchWithAngleTrigger(long id) {
        super(id);
    }

    @Override
    protected boolean condition(Point point) {
        int id = getGame().getMap().getLayerId(point.x, point.y);
        return id == 33;
    }

    @Override
    protected void action(Point point) {
        Hero hero = getGame().getHero();
        int floor = getGame().getMap().getCurrent();
        if (floor == 0) {
            if (!hero.withNPC(Hero.TOUCH_WITH_ANGLE)) {
                action1();
            } else if (!hero.withNPC(Hero.TOUCH_WITH_ANGLE_WITH_CROSS) && hero.hasProps(Hero.PROPS_CROSS)) {
                action2();
            } else if (!hero.withNPC(Hero.TOUCH_WITH_ANGLE_WITH_Y_SCEPTRE) && hero.hasProps(Hero.PROPS_Y_SCEPTRE)) {
                action3();
            }
        } else if (floor == 22) {
            if (!hero.withNPC(Hero.TOUCH_WITH_ANGLE_WITH_ALL_SCEPTRE) && hero.hasProps(Hero.PROPS_Y_SCEPTRE)) {
                if (hero.hasProps(Hero.PROPS_B_SCEPTRE) && hero.hasProps(Hero.PROPS_R_SCEPTRE)) {
                    action4();
                } else {
                    action5();
                }
            }
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
        dialog.setMessage(getGame().getDataManager().getMessage(1));
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
        dialog.setMessage(getGame().getDataManager().getMessage(10));
        dialog.show();
        dialog.nextMessage();
    }

    private void action3() {
        ChatDialog dialog = getGame().getChatDialog();
        dialog.setDismissListener(new Dialog.DismissListener() {
            @Override
            public void onDismiss(Dialog dialog) {
                getGame().getHero().withNPC(Hero.TOUCH_WITH_ANGLE_WITH_Y_SCEPTRE, true);
            }
        });
        dialog.setMessage(getGame().getDataManager().getMessage(21));
        dialog.show();
        dialog.nextMessage();
    }

    private void action4() {
        ChatDialog dialog = getGame().getChatDialog();
        dialog.setDismissListener(new Dialog.DismissListener() {
            @Override
            public void onDismiss(Dialog dialog) {
                getGame().getHero().withNPC(Hero.TOUCH_WITH_ANGLE_WITH_ALL_SCEPTRE, true);
                Point point = getPoint();
                getGame().getMap().removeLayer(point.x, point.y);
                getGame().updateNode(point.x, point.y);
                getGame().getMap().getFloor(25).getUpstair(2).v = 26;
            }
        });
        dialog.setMessage(getGame().getDataManager().getMessage(23));
        dialog.show();
        dialog.nextMessage();
    }

    private void action5() {
        ChatDialog dialog = getGame().getChatDialog();
        dialog.setMessage(getGame().getDataManager().getMessage(22));
        dialog.show();
        dialog.nextMessage();
    }

    private void afterAction1() {
        Hero hero = getGame().getHero();
        hero.putYellowKey(1);
        hero.putBlueKey(1);
        hero.putRedKey(1);
        hero.withNPC(Hero.TOUCH_WITH_ANGLE, true);
        getGame().updateUI();
        Resources resources = GameContext.instance().getResources();
        getGame().showToast(resources.getString(67));
        resources.getSound("reward.mp3").play();

        Point point = getPoint();
        Map map = getGame().getMap();
        map.removeLayer(point.x, point.y);
        map.setLayerId(point.x - 1, point.y, 33);

        getGame().updateNode(point.x, point.y);
        getGame().updateNode(point.x - 1, point.y);
    }

    private void afterAction2() {
        Hero hero = getGame().getHero();
        int hp = hero.getHp() / 3;
        int attack = hero.getAttack() / 3;
        int defence = hero.getDefence() / 3;
        hero.putHp(hp);
        hero.putAttack(attack);
        hero.putDefence(defence);
        hero.setProps(Hero.PROPS_CROSS, false);
        hero.withNPC(Hero.TOUCH_WITH_ANGLE_WITH_CROSS, true);
        getGame().updateUI();
        Resources resources = GameContext.instance().getResources();
        getGame().showToast(resources.getString(68, hp, attack, defence));
        resources.getSound("reward.mp3").play();

        Map map = getGame().getMap();
        map.getFloor(20).setLayerId(5, 3, 91);

        Point point = getPoint();
        map.removeLayer(point.x, point.y);
        getGame().updateNode(point.x, point.y);
    }
}
