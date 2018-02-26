package com.cncoderx.game.magictower.trigger;

import com.cncoderx.game.magictower.GameContext;
import com.cncoderx.game.magictower.Resources;
import com.cncoderx.game.magictower.data.Hero;
import com.cncoderx.game.magictower.ui.ShopDialog;
import com.cncoderx.game.magictower.utils.Point;

/**
 * Created by ll on 2017/5/21.
 */
public class ShoppingTrigger extends TouchTrigger {

    public ShoppingTrigger(long id) {
        super(id);
    }

    @Override
    protected boolean condition(Point point) {
        int id = getGame().getMap().getLayerId(point.x, point.y);
        return id == 88;
    }

    @Override
    protected void action(Point point) {
        int floor = getGame().getMap().getCurrent();
        switch (floor) {
            case 3:
                action1();
                break;
            case 11:
                action2();
                break;
        }
    }

    private void action1() {
        Resources resources = GameContext.instance().getResources();
        ShopDialog dialog = getGame().getShopDialog();
        dialog.setIntro(resources.getString(58, 25));
        dialog.setItemText1(resources.getString(59, 800));
        dialog.setItemText2(resources.getString(60, 4));
        dialog.setItemText3(resources.getString(61, 4));
        dialog.setOnSelectedListener(new ShopDialog.OnSelectedListener() {
            @Override
            public void select(int index) {
                afterAction1(index);
            }
        });
        dialog.show();
    }

    private void action2() {
        Resources resources = GameContext.instance().getResources();
        ShopDialog dialog = getGame().getShopDialog();
        dialog.setIntro(resources.getString(58, 100));
        dialog.setItemText1(resources.getString(59, 4000));
        dialog.setItemText2(resources.getString(60, 20));
        dialog.setItemText3(resources.getString(61, 20));
        dialog.setOnSelectedListener(new ShopDialog.OnSelectedListener() {
            @Override
            public void select(int index) {
                afterAction2(index);
            }
        });
        dialog.show();
    }

    private void afterAction1(int index) {
        switch (index) {
            case 0:
                buyHp();
                break;
            case 1:
                buyAttack();
                break;
            case 2:
                buyDefence();
                break;
        }
    }

    private void buyHp() {
        Resources resources = GameContext.instance().getResources();
        Hero hero = getGame().getHero();
        if (hero.getMoney() < 25) {
            getGame().showToast(resources.getString(62));
            resources.getSound("negative.mp3").play();
        } else {
            hero.putHp(800);
            hero.putMoney(-25);
            getGame().updateUI();
            getGame().showToast(resources.getString(64, 800));
            resources.getSound("positive.mp3").play();
        }
    }

    private void buyAttack() {
        Resources resources = GameContext.instance().getResources();
        Hero hero = getGame().getHero();
        if (hero.getMoney() < 25) {
            getGame().showToast(resources.getString(62));
            resources.getSound("negative.mp3").play();
        } else {
            hero.putAttack(4);
            hero.putMoney(-25);
            getGame().updateUI();
            getGame().showToast(resources.getString(65, 4));
            resources.getSound("positive.mp3").play();
        }
    }

    private void buyDefence() {
        Resources resources = GameContext.instance().getResources();
        Hero hero = getGame().getHero();
        if (hero.getMoney() < 25) {
            getGame().showToast(resources.getString(62));
            resources.getSound("negative.mp3").play();
        } else {
            hero.putDefence(4);
            hero.putMoney(-25);
            getGame().updateUI();
            getGame().showToast(resources.getString(66, 4));
            resources.getSound("positive.mp3").play();
        }
    }

    private void afterAction2(int index) {
        switch (index) {
            case 0:
                buyHp2();
                break;
            case 1:
                buyAttack2();
                break;
            case 2:
                buyDefence2();
                break;
        }
    }

    private void buyHp2() {
        Resources resources = GameContext.instance().getResources();
        Hero hero = getGame().getHero();
        if (hero.getMoney() < 100) {
            getGame().showToast(resources.getString(62));
            resources.getSound("negative.mp3").play();
        } else {
            hero.putHp(4000);
            hero.putMoney(-100);
            getGame().updateUI();
            getGame().showToast(resources.getString(64, 4000));
            resources.getSound("positive.mp3").play();
        }
    }

    private void buyAttack2() {
        Resources resources = GameContext.instance().getResources();
        Hero hero = getGame().getHero();
        if (hero.getMoney() < 100) {
            getGame().showToast(resources.getString(62));
            resources.getSound("negative.mp3").play();
        } else {
            hero.putAttack(20);
            hero.putMoney(-100);
            getGame().updateUI();
            getGame().showToast(resources.getString(65, 20));
            resources.getSound("positive.mp3").play();
        }
    }

    private void buyDefence2() {
        Resources resources = GameContext.instance().getResources();
        Hero hero = getGame().getHero();
        if (hero.getMoney() < 100) {
            getGame().showToast(resources.getString(62));
            resources.getSound("negative.mp3").play();
        } else {
            hero.putDefence(20);
            hero.putMoney(-100);
            getGame().updateUI();
            getGame().showToast(resources.getString(66, 20));
            resources.getSound("positive.mp3").play();
        }
    }
}
