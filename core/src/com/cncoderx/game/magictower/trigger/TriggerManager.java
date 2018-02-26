package com.cncoderx.game.magictower.trigger;

import com.cncoderx.game.magictower.Game;

import java.util.ArrayList;

/**
 * Created by admin on 2017/5/19.
 */
public class TriggerManager {
    private Game game;
    private ArrayList<Trigger> mTriggers = new ArrayList<Trigger>();

    public TriggerManager(Game game) {
        this.game = game;
    }

    public void registerTrigger(Trigger trigger) {
        trigger.game = game;
        mTriggers.add(trigger);
    }

    public void unregisterTrigger(Trigger trigger) {
        mTriggers.remove(trigger);
        trigger.game = null;
    }

    public void unregisterTrigger(int id) {
        for (Trigger trigger : mTriggers) {
            if (trigger.getId() == id) {
                unregisterTrigger(trigger);
            }
        }
    }

    public void clearTrigger(Trigger trigger) {
        mTriggers.clear();
    }

    public boolean notifyTrigger(int id) {
        for (Trigger trigger : mTriggers) {
            if (trigger.getId() == id) {
                if (trigger.condition()) {
                    trigger.action();
                    return true;
                }
            }
        }
        return false;
    }

    public void notifyAllTrigger() {
        for (Trigger trigger : mTriggers) {
            if (trigger.condition()) {
                trigger.action();
                break;
            }
        }
    }
}
