package com.cncoderx.game.magictower.data;

/**
 * Created by admin on 2017/5/25.
 */
public class Sprite extends Layer {
    int hp;
    int attack;
    int defence;
    int money;
    int exp;

    public int calcHeroLostHp(Hero hero) {
        if (hero.getAttack() <= defence) {
            return Integer.MAX_VALUE;
        }
        int result;
        if (attack <= hero.getDefence()) {
            result = 0;
        } else {
            result = (hp / (hero.getAttack() - defence)) * (attack - hero.getDefence());
        }
        switch (id) {
            case 49:
                result += 100;
                break;
            case 56:
                result += 300;
                break;
            case 54:
                result += hero.getHp() / 4;
                break;
            case 63:
                result += hero.getHp() / 3;
                break;
        }
        return result;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
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
}
