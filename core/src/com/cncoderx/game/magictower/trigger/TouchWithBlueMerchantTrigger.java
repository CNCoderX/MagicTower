package com.cncoderx.game.magictower.trigger;

import com.cncoderx.game.magictower.GameContext;
import com.cncoderx.game.magictower.Resources;
import com.cncoderx.game.magictower.data.Hero;
import com.cncoderx.game.magictower.ui.ChatDialog;
import com.cncoderx.game.magictower.ui.Dialog;
import com.cncoderx.game.magictower.ui.ShopDialog;
import com.cncoderx.game.magictower.utils.Point;

/**
 * Created by admin on 2017/5/22.
 */
public class TouchWithBlueMerchantTrigger extends TouchTrigger {

    public TouchWithBlueMerchantTrigger(long id) {
        super(id);
    }

    @Override
    protected boolean condition(Point point) {
        int id = getGame().getMap().getLayerId(point.x, point.y);
        return id == 36;
    }

    @Override
    protected void action(Point point) {
        int floor = getGame().getMap().getCurrent();
        switch (floor) {
            case 2:
                action1();
                break;
            case 5:
                action2();
                break;
            case 13:
                action3();
                break;
            case 15:
                action4();
                break;
            case 16:
                action5();
                break;
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
        dialog.setMessage(getGame().getDataManager().getMessage(4));
        dialog.show();
        dialog.nextMessage();
    }

    private void action2() {
        Resources resources = GameContext.instance().getResources();
        ShopDialog dialog = getGame().getShopDialog();
        dialog.setIntro(resources.getString(69));
        dialog.setItemText1(resources.getString(70, 100));
        dialog.setItemText2(resources.getString(72, 5, 30));
        dialog.setItemText3(resources.getString(73, 5, 30));
        dialog.setOnSelectedListener(new ShopDialog.OnSelectedListener() {
            @Override
            public void select(int index) {
                afterAction2(index);
            }
        });
        dialog.show();
    }

    private void action3() {
        Resources resources = GameContext.instance().getResources();
        ShopDialog dialog = getGame().getShopDialog();
        dialog.setIntro(resources.getString(69));
        dialog.setItemText1(resources.getString(71, 270));
        dialog.setItemText2(resources.getString(72, 17, 95));
        dialog.setItemText3(resources.getString(73, 17, 95));
        dialog.setOnSelectedListener(new ShopDialog.OnSelectedListener() {
            @Override
            public void select(int index) {
                afterAction3(index);
            }
        });
        dialog.show();
    }

    private void action4() {
        if (getGame().getHero().getExp() < 500) {
            ChatDialog dialog = getGame().getChatDialog();
            dialog.setMessage(getGame().getDataManager().getMessage(5));
            dialog.show();
            dialog.nextMessage();
        } else {
            ChatDialog dialog = getGame().getChatDialog();
            dialog.setDismissListener(new Dialog.DismissListener() {
                @Override
                public void onDismiss(Dialog dialog) {
                    afterAction4();
                }
            });
            dialog.setMessage(getGame().getDataManager().getMessage(14));
            dialog.show();
            dialog.nextMessage();
        }
    }

    private void action5() {
        ChatDialog dialog = getGame().getChatDialog();
        dialog.setDismissListener(new Dialog.DismissListener() {
            @Override
            public void onDismiss(Dialog dialog) {
                afterAction5();
            }
        });
        dialog.setMessage(getGame().getDataManager().getMessage(20));
        dialog.show();
        dialog.nextMessage();
    }

    private void afterAction1() {
//        getGame().getHero().putAttack(40);
        getGame().getHero().putAttack(70);
        getGame().updateUI();

        Point point = getPoint();
        getGame().getMap().removeLayer(point.x, point.y);
        getGame().updateNode(point.x, point.y);
        Resources resources = GameContext.instance().getResources();
        getGame().showToast(resources.getString(34));
        resources.getSound("reward.mp3").play();
    }

    private void afterAction2(int index) {
        switch (index) {
            case 0:
                buyLevel();
                break;
            case 1:
                buyAttack();
                break;
            case 2:
                buyDefence();
                break;
        }
    }

    private void buyLevel() {
        Resources resources = GameContext.instance().getResources();
        Hero hero = getGame().getHero();
        if (hero.getExp() < 100) {
            getGame().showToast(resources.getString(63));
            resources.getSound("negative.mp3").play();
        } else {
            hero.putLevel(1);
            hero.putHp(1000);
            hero.putAttack(7);
            hero.putDefence(7);
            hero.putExp(-100);
            getGame().updateUI();
            getGame().showToast(resources.getString(74));
            resources.getSound("positive.mp3").play();
        }
    }

    private void buyAttack() {
        Resources resources = GameContext.instance().getResources();
        Hero hero = getGame().getHero();
        if (hero.getExp() < 30) {
            getGame().showToast(resources.getString(63));
            resources.getSound("negative.mp3").play();
        } else {
            hero.putAttack(5);
            hero.putExp(-30);
            getGame().updateUI();
            getGame().showToast(resources.getString(65, 5));
            resources.getSound("positive.mp3").play();
        }
    }

    private void buyDefence() {
        Resources resources = GameContext.instance().getResources();
        Hero hero = getGame().getHero();
        if (hero.getExp() < 30) {
            getGame().showToast(resources.getString(63));
            resources.getSound("negative.mp3").play();
        } else {
            hero.putDefence(5);
            hero.putExp(-30);
            getGame().updateUI();
            getGame().showToast(resources.getString(66, 5));
            resources.getSound("positive.mp3").play();
        }
    }

    private void afterAction3(int index) {
        switch (index) {
            case 0:
                buyLevel2();
                break;
            case 1:
                buyAttack2();
                break;
            case 2:
                buyDefence2();
                break;
        }
    }

    private void buyLevel2() {
        Resources resources = GameContext.instance().getResources();
        Hero hero = getGame().getHero();
        if (hero.getExp() < 270) {
            getGame().showToast(resources.getString(63));
            resources.getSound("negative.mp3").play();
        } else {
            hero.putLevel(3);
            hero.putHp(3000);
            hero.putAttack(20);
            hero.putDefence(20);
            hero.putExp(-270);
            getGame().updateUI();
            getGame().showToast(resources.getString(75));
            resources.getSound("positive.mp3").play();
        }
    }

    private void buyAttack2() {
        Resources resources = GameContext.instance().getResources();
        Hero hero = getGame().getHero();
        if (hero.getExp() < 95) {
            getGame().showToast(resources.getString(63));
            resources.getSound("negative.mp3").play();
        } else {
            hero.putAttack(17);
            hero.putExp(-95);
            getGame().updateUI();
            getGame().showToast(resources.getString(65, 17));
            resources.getSound("positive.mp3").play();
        }
    }

    private void buyDefence2() {
        Resources resources = GameContext.instance().getResources();
        Hero hero = getGame().getHero();
        if (hero.getExp() < 95) {
            getGame().showToast(resources.getString(63));
            resources.getSound("negative.mp3").play();
        } else {
            hero.putDefence(17);
            hero.putExp(-95);
            getGame().updateUI();
            getGame().showToast(resources.getString(66, 17));
            resources.getSound("positive.mp3").play();
        }
    }

    private void afterAction4() {
        Hero hero = getGame().getHero();
        hero.putAttack(120);
        hero.putExp(-500);
        getGame().updateUI();

        Point point = getPoint();
        getGame().getMap().removeLayer(point.x, point.y);
        getGame().updateNode(point.x, point.y);
        Resources resources = GameContext.instance().getResources();
        getGame().showToast(resources.getString(35));
        resources.getSound("reward.mp3").play();
    }

    private void afterAction5() {
        Point point = getPoint();
        getGame().getMap().removeLayer(point.x, point.y);
        getGame().updateNode(point.x, point.y);
        Resources resources = GameContext.instance().getResources();
        getGame().getHero().setProps(Hero.PROPS_Y_SCEPTRE, true);
        getGame().showToast(resources.getString(76));
        resources.getSound("collect.mp3").play();
    }
}
