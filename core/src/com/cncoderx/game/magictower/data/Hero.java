package com.cncoderx.game.magictower.data;

import com.cncoderx.game.magictower.io.Reader;
import com.cncoderx.game.magictower.io.Serializable;
import com.cncoderx.game.magictower.io.Writer;
import com.cncoderx.game.magictower.utils.VPoint;

/**
 * Created by admin on 2017/5/19.
 */
public class Hero extends Sprite implements Serializable {
    VPoint point = new VPoint();
    int level = 1;
    int yellowKey = 0;
    int blueKey = 0;
    int redKey = 0;
    int greenKey = 0;
    long props = 0;
    long status = 0;

    public static final int PROPS_AXE              = 1;
    public static final int PROPS_CROSS            = 1 << 1;
    public static final int PROPS_COMPASS          = 1 << 2;
    public static final int PROPS_NOTE             = 1 << 3;
    public static final int PROPS_Y_SCEPTRE        = 1 << 4;
    public static final int PROPS_B_SCEPTRE        = 1 << 5;
    public static final int PROPS_R_SCEPTRE        = 1 << 6;

    public static final int TOUCH_WITH_ANGLE                   = 1;            // 第一次和蜻蜓谈话
    public static final int TOUCH_WITH_ANGLE_WITH_CROSS        = 1 << 1;       // 拿到十字架之后和蜻蜓谈话
    public static final int TOUCH_WITH_ANGLE_WITH_Y_SCEPTRE    = 1 << 2;       // 拿到心之权杖后和蜻蜓谈话
    public static final int TOUCH_WITH_ANGLE_WITH_ALL_SCEPTRE  = 1 << 3;       // 收集到所有权杖后和蜻蜓谈话
    public static final int TOUCH_WITH_PRINCESS                = 1 << 4;
    public static final int TOUCH_WITH_THIEF                   = 1 << 5;       // 第一次和盗贼谈话
    public static final int TOUCH_WITH_THIEF_WITH_AXE          = 1 << 6;       // 拿到铁镐之后和盗贼谈话
    public static final int TOUCH_WITH_RED_LORD                = 1 << 7;
    public static final int TOUCH_WITH_GHOST_LORD_FIRST        = 1 << 8;
    public static final int TOUCH_WITH_GHOST_LORD_SECOND       = 1 << 9;
    public static final int KILLED_RED_LORD                    = 1 << 10;
    public static final int KILLED_GHOST_LORD_FIRST            = 1 << 11;
    public static final int KILLED_GHOST_LORD_SECOND           = 1 << 12;

    public VPoint getPoint() {
        return point;
    }

    public void setPoint(int x, int y, int direction) {
        this.point.x = x;
        this.point.y = y;
        this.point.v = direction;
    }

    public void setPoint(int x, int y) {
        this.point.x = x;
        this.point.y = y;
    }

    @Override
    public void read(Reader reader) {
        point.x = reader.readShort();
        point.y = reader.readShort();
        point.v = reader.readShort();
        hp = reader.readInt();
        level = reader.readInt();
        attack = reader.readInt();
        defence = reader.readInt();
        money = reader.readInt();
        exp = reader.readInt();
        yellowKey = reader.readInt();
        blueKey = reader.readInt();
        redKey = reader.readInt();
        greenKey = reader.readInt();
        props = reader.readLong();
        status = reader.readLong();
    }

    @Override
    public void write(Writer writer) {
        writer.writeShort((short) point.x);
        writer.writeShort((short) point.y);
        writer.writeShort((short) point.v);
        writer.writeInt(hp);
        writer.writeInt(level);
        writer.writeInt(attack);
        writer.writeInt(defence);
        writer.writeInt(money);
        writer.writeInt(exp);
        writer.writeInt(yellowKey);
        writer.writeInt(blueKey);
        writer.writeInt(redKey);
        writer.writeInt(greenKey);
        writer.writeLong(props);
        writer.writeLong(status);
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getYellowKey() {
        return yellowKey;
    }

    public void setYellowKey(int yellowKey) {
        this.yellowKey = yellowKey;
    }

    public int getBlueKey() {
        return blueKey;
    }

    public void setBlueKey(int blueKey) {
        this.blueKey = blueKey;
    }

    public int getRedKey() {
        return redKey;
    }

    public void setRedKey(int redKey) {
        this.redKey = redKey;
    }

    public int getGreenKey() {
        return greenKey;
    }

    public void setGreenKey(int greenKey) {
        this.greenKey = greenKey;
    }

    public void putYellowKey(int amount) {
        this.yellowKey += amount;
    }

    public void putBlueKey(int amount) {
        this.blueKey += amount;
    }

    public void putRedKey(int amount) {
        this.redKey += amount;
    }

    public void putGreenKey(int amount) {
        this.greenKey += amount;
    }

    public void putHp(int amount) {
        this.hp += amount;
    }

    public void putLevel(int amount) {
        this.level += amount;
    }

    public void putAttack(int amount) {
        this.attack += amount;
    }

    public void putDefence(int amount) {
        this.defence += amount;
    }

    public void putMoney(int amount) {
        this.money += amount;
    }

    public void putExp(int amount) {
        this.exp += amount;
    }

    public boolean hasProps(int propsID) {
        return (props & propsID) != 0;
    }

    public void setProps(int propsID, boolean bool) {
        if (bool)
            props |= propsID;
        else
            props &= ~propsID;
    }

    public boolean withNPC(int id) {
        return (status & id) != 0;
    }

    public void withNPC(int id, boolean bool) {
        if (bool)
            status |= id;
        else
            status &= ~id;
    }
}
