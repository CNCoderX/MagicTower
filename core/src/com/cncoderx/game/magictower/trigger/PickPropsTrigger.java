package com.cncoderx.game.magictower.trigger;

import com.cncoderx.game.magictower.Game;
import com.cncoderx.game.magictower.GameContext;
import com.cncoderx.game.magictower.Resources;
import com.cncoderx.game.magictower.data.Hero;
import com.cncoderx.game.magictower.utils.Point;

/**
 * Created by ll on 2017/5/20.
 */
public class PickPropsTrigger extends EnterTrigger {

    public PickPropsTrigger(long id) {
        super(id);
    }

    @Override
    protected boolean condition(Point point) {
        int id = getGame().getMap().getLayerId(point.x, point.y);
        return id >= 0 && id <= 28;
    }

    @Override
    protected void action(Point point) {
        Game game = getGame();
        int id = game.getMap().getLayerId(point.x, point.y);
        game.getMap().removeLayer(point.x, point.y);
        game.updateNode(point.x, point.y);
        Resources resources = GameContext.instance().getResources();
        switch (id) {
            case 0:
                game.getHero().putHp(200);
                game.updateUI();
                game.showToast(resources.getString(28));
                resources.getSound("collect.mp3").play();
                break;
            case 1:
                game.getHero().putHp(500);
                game.updateUI();
                game.showToast(resources.getString(29));
                resources.getSound("collect.mp3").play();
                break;
            case 2:
                game.getHero().putAttack(3);
                game.updateUI();
                game.showToast(resources.getString(30));
                resources.getSound("collect.mp3").play();
                break;
            case 3:
                game.getHero().putDefence(3);
                game.updateUI();
                game.showToast(resources.getString(31));
                resources.getSound("collect.mp3").play();
                break;
            case 4:
                game.getHero().putAttack(10);
                game.updateUI();
                game.showToast(resources.getString(32));
                resources.getSound("collect.mp3").play();
                break;
            case 5:
                game.getHero().putAttack(40);
                game.updateUI();
                game.showToast(resources.getString(33));
                resources.getSound("collect.mp3").play();
                break;
            case 6:
                game.getHero().putAttack(70);
                game.updateUI();
                game.showToast(resources.getString(34));
                resources.getSound("collect.mp3").play();
                break;
            case 7:
                game.getHero().putAttack(120);
                game.updateUI();
                game.showToast(resources.getString(35));
                resources.getSound("collect.mp3").play();
                break;
            case 8:
                game.getHero().putAttack(150);
                game.updateUI();
                game.showToast(resources.getString(36));
                resources.getSound("collect.mp3").play();
                break;
            case 9:
                game.getHero().putDefence(10);
                game.updateUI();
                game.showToast(resources.getString(37));
                resources.getSound("collect.mp3").play();
                break;
            case 10:
                game.getHero().putDefence(30);
                game.updateUI();
                game.showToast(resources.getString(38));
                resources.getSound("collect.mp3").play();
                break;
            case 11:
                game.getHero().putDefence(85);
                game.updateUI();
                game.showToast(resources.getString(39));
                resources.getSound("collect.mp3").play();
                break;
            case 12:
                game.getHero().putDefence(120);
                game.updateUI();
                game.showToast(resources.getString(40));
                resources.getSound("collect.mp3").play();
                break;
            case 13:
                game.getHero().putDefence(190);
                game.updateUI();
                game.showToast(resources.getString(41));
                resources.getSound("collect.mp3").play();
                break;
            case 14:
                game.getHero().setProps(Hero.PROPS_NOTE, true);
                game.updateUI();
                game.showToast(resources.getString(42));
               resources.getSound("collect.mp3").play();
                break;
            case 15:
                game.getHero().setProps(Hero.PROPS_COMPASS, true);
                game.updateUI();
                game.showToast(resources.getString(43));
                resources.getSound("collect.mp3").play();
                break;
            case 16:
                game.getHero().putLevel(1);
                game.getHero().putHp(1000);
                game.getHero().putAttack(10);
                game.getHero().putDefence(10);
                game.updateUI();
                game.showToast(resources.getString(44));
                resources.getSound("collect.mp3").play();
                break;
            case 17:
                game.getHero().putLevel(3);
                game.getHero().putHp(3000);
                game.getHero().putAttack(30);
                game.getHero().putDefence(30);
                game.updateUI();
                game.showToast(resources.getString(45));
                resources.getSound("collect.mp3").play();
                break;
            case 18:
                game.getHero().putMoney(300);
                game.updateUI();
                game.showToast(resources.getString(46));
                resources.getSound("collect.mp3").play();
                break;
            case 19:
                int hp = game.getHero().getHp();
                game.getHero().putHp(hp);
                game.updateUI();
                game.showToast(resources.getString(47));
                resources.getSound("collect.mp3").play();
                break;
            case 20:
                game.getHero().setProps(Hero.PROPS_CROSS, true);
                game.showToast(resources.getString(48));
                resources.getSound("collect.mp3").play();
                break;
            case 21:
                game.getHero().setProps(Hero.PROPS_AXE, true);
                game.showToast(resources.getString(49));
                resources.getSound("collect.mp3").play();
                break;
            case 22:
                game.getHero().putYellowKey(1);
                game.updateUI();
                game.showToast(resources.getString(50, 1));
                resources.getSound("collect.mp3").play();
                break;
            case 23:
                game.getHero().putBlueKey(1);
                game.updateUI();
                game.showToast(resources.getString(51, 1));
                resources.getSound("collect.mp3").play();
                break;
            case 24:
                game.getHero().putRedKey(1);
                game.updateUI();
                game.showToast(resources.getString(52, 1));
                resources.getSound("collect.mp3").play();
                break;
            case 25:
                game.getHero().putYellowKey(1);
                game.getHero().putBlueKey(1);
                game.getHero().putRedKey(1);
                game.updateUI();
                game.showToast(resources.getString(53));
                resources.getSound("collect.mp3").play();
                break;
            case 26:
                game.getHero().setProps(Hero.PROPS_Y_SCEPTRE, true);
                game.showToast(resources.getString(54));
                resources.getSound("collect.mp3").play();
                break;
            case 27:
                game.getHero().setProps(Hero.PROPS_B_SCEPTRE, true);
                game.showToast(resources.getString(55));
                resources.getSound("collect.mp3").play();
                break;
            case 28:
                game.getHero().setProps(Hero.PROPS_R_SCEPTRE, true);
                game.showToast(resources.getString(56));
                resources.getSound("collect.mp3").play();
                break;
        }
    }
}
