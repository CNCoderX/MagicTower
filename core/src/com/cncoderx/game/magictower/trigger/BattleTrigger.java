package com.cncoderx.game.magictower.trigger;

import com.cncoderx.game.magictower.GameContext;
import com.cncoderx.game.magictower.data.Hero;
import com.cncoderx.game.magictower.data.Sprite;
import com.cncoderx.game.magictower.ui.BattleDialog;
import com.cncoderx.game.magictower.ui.ChatDialog;
import com.cncoderx.game.magictower.utils.Point;

/**
 * Created by ll on 2017/5/20.
 */
public class BattleTrigger extends TouchTrigger implements BattleDialog.BattleListener {
    BattleDialog dialog;
    Sprite sprite;

    public BattleTrigger(long id) {
        super(id);
    }

    @Override
    protected boolean condition(Point point) {
        int id = getGame().getMap().getLayerId(point.x, point.y);
        sprite = getGame().getDataManager().getSprite(id);
        return sprite != null;
    }

    @Override
    protected void action(Point point) {
        Hero hero = getGame().getHero();
        if (hero.getHp() > sprite.calcHeroLostHp(hero)) {
            dialog = getGame().getBattleDialog();
            dialog.setHero(hero);
            dialog.setMonster(sprite);
            dialog.setBattleListener(this);
            dialog.show();
            dialog.begin();
        }
    }

    @Override
    public void success(int heroHp, int monsterHp) {
        dialog.dismiss();

        Hero hero = getGame().getHero();
        int money = sprite.getMoney();
        int exp = sprite.getExp();
        hero.setHp(heroHp);
        hero.putMoney(money);
        hero.putExp(exp);
        getGame().updateUI();
        getGame().showToast(GameContext.instance().getResources().getString(57, money, exp));
        GameContext.instance().getResources().getSound("reward.mp3").play();

        Point point = getPoint();
        getGame().getMap().removeLayer(point.x, point.y);
        getGame().updateNode(point.x, point.y);

        if (sprite.getId() == 67) {
            if (!hero.withNPC(Hero.KILLED_RED_LORD)) {
                hero.withNPC(Hero.KILLED_RED_LORD, true);
                getGame().getDataManager().setSpriteLevel(1);
            }
        } else if (sprite.getId() == 69) {
            if (!hero.withNPC(Hero.KILLED_GHOST_LORD_FIRST)) {
                ChatDialog dialog = getGame().getChatDialog();
                dialog.setMessage(getGame().getDataManager().getMessage(9));
                dialog.show();
                dialog.nextMessage();

                hero.withNPC(Hero.KILLED_GHOST_LORD_FIRST, true);
                getGame().getDataManager().setLordLevel(1);
            } else if (!hero.withNPC(Hero.KILLED_GHOST_LORD_SECOND)) {
                ChatDialog dialog = getGame().getChatDialog();
                dialog.setMessage(getGame().getDataManager().getMessage(18));
                dialog.show();
                dialog.nextMessage();

                getGame().getMap().setTileId(5, 4, 1);
                getGame().updateNode(5, 4);

                getGame().getMap().setLayerId(5, 9, 91);
                getGame().updateNode(5, 9);

                getGame().getMap().getFloor().getDownstair(0).x = 5;
                getGame().getMap().getFloor().getDownstair(0).y = 4;

                hero.withNPC(Hero.KILLED_GHOST_LORD_SECOND, true);
                getGame().getDataManager().setLordLevel(2);
                getGame().getDataManager().setSpriteLevel(2);
            }
        } else if (sprite.getId() == 108 || sprite.getId() == 117) {
            getGame().getMap().removeLayer(point.x - 1, point.y);
            getGame().updateNode(point.x - 1, point.y);

            getGame().getMap().removeLayer(point.x + 1, point.y);
            getGame().updateNode(point.x + 1, point.y);

            getGame().getMap().removeLayer(point.x - 1, point.y + 1);
            getGame().updateNode(point.x - 1, point.y + 1);

            getGame().getMap().removeLayer(point.x, point.y + 1);
            getGame().updateNode(point.x, point.y + 1);

            getGame().getMap().removeLayer(point.x + 1, point.y + 1);
            getGame().updateNode(point.x + 1, point.y + 1);

            getGame().getMap().removeLayer(point.x - 1, point.y + 2);
            getGame().updateNode(point.x - 1, point.y + 2);

            getGame().getMap().removeLayer(point.x, point.y + 2);
            getGame().updateNode(point.x, point.y + 2);

            getGame().getMap().removeLayer(point.x + 1, point.y + 2);
            getGame().updateNode(point.x + 1, point.y + 2);

            GameContext.instance().success();
        }
    }

    @Override
    public void failure(int heroHp, int monsterHp) {
        dialog.dismiss();
        GameContext.instance().failure();
    }
}
