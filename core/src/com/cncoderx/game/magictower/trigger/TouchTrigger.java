package com.cncoderx.game.magictower.trigger;

import com.cncoderx.game.magictower.utils.Global;
import com.cncoderx.game.magictower.utils.Point;
import com.cncoderx.game.magictower.utils.VPoint;

/**
 * Created by admin on 2017/5/19.
 */
public abstract class TouchTrigger extends Trigger {
    private Point point;

    public TouchTrigger(long id) {
        super(id);
    }

    @Override
    protected final boolean condition() {
        VPoint p = game.getHero().getPoint();
        switch (p.v) {
            case Global.LEFT:
                point = new Point(p.x - 1, p.y);
                break;
            case Global.UP:
                point = new Point(p.x, p.y + 1);
                break;
            case Global.RIGHT:
                point = new Point(p.x + 1, p.y);
                break;
            case Global.DOWN:
                point = new Point(p.x, p.y - 1);
                break;
        }
        return condition(point);
    }

    @Override
    protected final void action() {
        action(point);
    }

    public Point getPoint() {
        return point;
    }

    protected abstract boolean condition(Point point);

    protected abstract void action(Point point);
}
