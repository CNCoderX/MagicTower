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
public class TouchWithRedMerchantTrigger extends TouchTrigger {

    public TouchWithRedMerchantTrigger(long id) {
        super(id);
    }

    @Override
    protected boolean condition(Point point) {
        int id = getGame().getMap().getLayerId(point.x, point.y);
        return id == 37;
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
            case 12:
                action3();
                break;
            case 15:
                action4();
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
        dialog.setMessage(getGame().getDataManager().getMessage(3));
        dialog.show();
        dialog.nextMessage();
    }

    private void action2() {
        Resources resources = GameContext.instance().getResources();
        ShopDialog dialog = getGame().getShopDialog();
        dialog.setIntro(resources.getString(77));
        dialog.setItemText1(resources.getString(79, 1, 10));
        dialog.setItemText2(resources.getString(80, 1, 50));
        dialog.setItemText3(resources.getString(81, 1, 100));
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
        dialog.setIntro(resources.getString(78));
        dialog.setItemText1(resources.getString(82, 1, 7));
        dialog.setItemText2(resources.getString(83, 1, 35));
        dialog.setItemText3(resources.getString(84, 1, 70));
        dialog.setOnSelectedListener(new ShopDialog.OnSelectedListener() {
            @Override
            public void select(int index) {
                afterAction3(index);
            }
        });
        dialog.show();
    }

    private void action4() {
        if (getGame().getHero().getMoney() < 500) {
            ChatDialog dialog = getGame().getChatDialog();
            dialog.setMessage(getGame().getDataManager().getMessage(6));
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
            dialog.setMessage(getGame().getDataManager().getMessage(15));
            dialog.show();
            dialog.nextMessage();
        }
    }

    private void afterAction1() {
        getGame().getHero().putDefence(30);
        getGame().updateUI();

        Point point = getPoint();
        getGame().getMap().removeLayer(point.x, point.y);
        getGame().updateNode(point.x, point.y);
        Resources resources = GameContext.instance().getResources();
        getGame().showToast(resources.getString(38));
        resources.getSound("reward.mp3").play();
    }

    private void afterAction2(int index) {
        switch (index) {
            case 0:
                buyYellowKey();
                break;
            case 1:
                buyBlueKey();
                break;
            case 2:
                buyRedKey();
                break;
        }
    }

    private void buyYellowKey() {
        Resources resources = GameContext.instance().getResources();
        Hero hero = getGame().getHero();
        if (hero.getMoney() < 10) {
            getGame().showToast(resources.getString(62));
            resources.getSound("negative.mp3").play();
        } else {
            hero.putYellowKey(1);
            hero.putMoney(-10);
            getGame().updateUI();
            getGame().showToast(resources.getString(85, 1));
            resources.getSound("positive.mp3").play();
        }
    }

    private void buyBlueKey() {
        Resources resources = GameContext.instance().getResources();
        Hero hero = getGame().getHero();
        if (hero.getMoney() < 50) {
            getGame().showToast(resources.getString(62));
            resources.getSound("negative.mp3").play();
        } else {
            hero.putBlueKey(1);
            hero.putMoney(-50);
            getGame().updateUI();
            getGame().showToast(resources.getString(86, 1));
            resources.getSound("positive.mp3").play();
        }
    }

    private void buyRedKey() {
        Resources resources = GameContext.instance().getResources();
        Hero hero = getGame().getHero();
        if (hero.getMoney() < 100) {
            getGame().showToast(resources.getString(62));
            resources.getSound("negative.mp3").play();
        } else {
            hero.putRedKey(1);
            hero.putMoney(-100);
            getGame().updateUI();
            getGame().showToast(resources.getString(87, 1));
            resources.getSound("positive.mp3").play();
        }
    }

    private void afterAction3(int index) {
        switch (index) {
            case 0:
                saleYellowKey();
                break;
            case 1:
                saleBlueKey();
                break;
            case 2:
                saleRedKey();
                break;
        }
    }

    private void saleYellowKey() {
        Resources resources = GameContext.instance().getResources();
        Hero hero = getGame().getHero();
        if (hero.getYellowKey() <= 0) {
            getGame().showToast(resources.getString(88));
            resources.getSound("negative.mp3").play();
        } else {
            hero.putYellowKey(-1);
            hero.putMoney(7);
            getGame().updateUI();
            getGame().showToast(resources.getString(82, 1, 7));
            resources.getSound("positive.mp3").play();
        }
    }

    private void saleBlueKey() {
        Resources resources = GameContext.instance().getResources();
        Hero hero = getGame().getHero();
        if (hero.getBlueKey() <= 0) {
            getGame().showToast(resources.getString(89));
            resources.getSound("negative.mp3").play();
        } else {
            hero.putBlueKey(-1);
            hero.putMoney(35);
            getGame().updateUI();
            getGame().showToast(resources.getString(83, 1, 35));
            resources.getSound("positive.mp3").play();
        }
    }

    private void saleRedKey() {
        Resources resources = GameContext.instance().getResources();
        Hero hero = getGame().getHero();
        if (hero.getRedKey() <= 0) {
            getGame().showToast(resources.getString(90));
            resources.getSound("negative.mp3").play();
        } else {
            hero.putRedKey(-1);
            hero.putMoney(70);
            getGame().updateUI();
            getGame().showToast(resources.getString(84, 1, 70));
            resources.getSound("positive.mp3").play();
        }
    }

    private void afterAction4() {
        Hero hero = getGame().getHero();
        hero.putDefence(120);
        hero.putMoney(-500);
        getGame().updateUI();

        Point point = getPoint();
        getGame().getMap().removeLayer(point.x, point.y);
        getGame().updateNode(point.x, point.y);
        Resources resources = GameContext.instance().getResources();
        getGame().showToast(resources.getString(40));
        resources.getSound("reward.mp3").play();
    }
}
