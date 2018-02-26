package com.cncoderx.game.magictower.trigger;

import com.cncoderx.game.magictower.utils.Point;

/**
 * Created by ll on 2017/5/20.
 */
public abstract class EnterTrigger extends Trigger {
    private Point point;

    public EnterTrigger(long id) {
        super(id);
    }

    @Override
    protected final boolean condition() {
        point = new Point(game.getHero().getPoint());
        return condition(point);
    }

    @Override
    protected final void action() {
        action(point);
    }

    protected abstract boolean condition(Point point);

    protected abstract void action(Point point);

    public Point getPoint() {
        return point;
    }
}
