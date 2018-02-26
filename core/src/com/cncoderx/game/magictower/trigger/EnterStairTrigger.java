package com.cncoderx.game.magictower.trigger;

import com.cncoderx.game.magictower.data.Floor;
import com.cncoderx.game.magictower.utils.Point;
import com.cncoderx.game.magictower.utils.VPoint;

/**
 * Created by ll on 2017/5/20.
 */
public class EnterStairTrigger extends EnterTrigger {

    public EnterStairTrigger(long id) {
        super(id);
    }

    @Override
    protected boolean condition(Point point) {
        int id = getGame().getMap().getLayerId(point.x, point.y);
        return id == 91 || id == 92;
    }

    @Override
    protected void action(Point point) {
        int id = getGame().getMap().getLayerId(point.x, point.y);
        switch (id) {
            case 91:
                upstair();
                break;
            case 92:
                downstair();
                break;
        }
    }

    private void upstair() {
        Floor floor = getGame().getMap().getFloor();
        Point point = getPoint();
        VPoint vp;
        for (int i = 0; i < 3; i++) {
            vp = floor.getUpstair(i);
            if (Math.abs(point.x - vp.x) + Math.abs(point.y - vp.y) == 1) {
                getGame().switchFloor(vp.v, i);
                break;
            }
        }
    }

    private void downstair() {
        Floor floor = getGame().getMap().getFloor();
        Point point = getPoint();
        VPoint vp;
        for (int i = 0; i < 3; i++) {
            vp = floor.getDownstair(i);
            if (Math.abs(point.x - vp.x) + Math.abs(point.y - vp.y) == 1) {
                getGame().switchFloor(vp.v, i);
                break;
            }
        }
    }
}
