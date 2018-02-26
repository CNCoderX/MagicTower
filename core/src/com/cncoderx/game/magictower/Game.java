package com.cncoderx.game.magictower;

import com.cncoderx.game.magictower.data.DataManager;
import com.cncoderx.game.magictower.data.Hero;
import com.cncoderx.game.magictower.data.Map;
import com.cncoderx.game.magictower.trigger.TriggerManager;
import com.cncoderx.game.magictower.ui.BattleDialog;
import com.cncoderx.game.magictower.ui.ChatDialog;
import com.cncoderx.game.magictower.ui.ShopDialog;
import com.cncoderx.game.magictower.widget.HeroWidget;
import com.cncoderx.game.magictower.widget.NodeWidget;

/**
 * Created by admin on 2017/5/18.
 */
public interface Game {
    Hero getHero();
    Map getMap();
    HeroWidget getWidget();
    NodeWidget getWidget(int x, int y);
    TriggerManager getTriggerManager();
    ChatDialog getChatDialog();
    ShopDialog getShopDialog();
    BattleDialog getBattleDialog();
    DataManager getDataManager();
    void showToast(String message);
    void updateNode(int x, int y);
    void switchFloor(int floor, int n);
    void updateUI();
    void updateMap();
}
