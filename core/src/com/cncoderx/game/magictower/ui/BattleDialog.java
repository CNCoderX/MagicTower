package com.cncoderx.game.magictower.ui;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.cncoderx.game.magictower.GameContext;
import com.cncoderx.game.magictower.Resources;
import com.cncoderx.game.magictower.data.Hero;
import com.cncoderx.game.magictower.data.Sprite;
import com.cncoderx.game.magictower.drawable.AnimationDrawable;
import com.cncoderx.game.magictower.drawable.LazyAnimationDrawable;

/**
 * Created by admin on 2017/5/26.
 */
public class BattleDialog extends Dialog {
    private Drawable mDrawable;
    private Image effect, effect2;
    private Image imgIcon, imgIcon2;
    private Label labName, labName2;
    private Label labHp, labHp2;
    private Label labAttack, labAttack2;
    private Label labDefence, labDefence2;
    private LazyAnimationDrawable anim, anim2;

    private Hero hero;
    private Sprite monster;
    private int currentHeroHp, currentMonsterHp;
    private BattleListener mBattleListener;

    public BattleDialog(Group parent) {
        super(parent);
        setName("BattleDialog");
        setSize(35 * 11 + 6, 35 * 5 + 6);
        Resources resources = GameContext.instance().getResources();
        mDrawable = new TextureRegionDrawable(new TextureRegion(resources.getTexture("battle_bg.jpg")));
        {
            Image image = new Image(resources.getRegion(Resources.atlas.ui, "vs"));
            image.setPosition(getWidth() / 2, getHeight() / 2, Align.center);
            addActor(image);
        }
        {
            Image image = new Image(new NinePatch(resources.getRegion(Resources.atlas.ui, "frame"), 5, 5, 5, 5));
            image.setSize(68, 68);
            image.setPosition(5, getHeight() - 10, Align.topLeft);
            addActor(image);
        }
        {
            Image image = new Image(new NinePatch(resources.getRegion(Resources.atlas.ui, "frame"), 5, 5, 5, 5));
            image.setSize(68, 68);
            image.setPosition(getWidth() - 5, getHeight() - 10, Align.topRight);
            addActor(image);
        }
        {
            Image image = new Image(new AnimationDrawable(resources.getAnimation(
                    Resources.atlas.character, "hero", .25f))
                    .setPlayMode(Animation.PlayMode.LOOP));
            image.setSize(58, 58);
            image.setPosition(10, getHeight() - 15, Align.topLeft);
            addActor(image);
            imgIcon = image;
        }
        {
            Image image = new Image();
            image.setSize(58, 58);
            image.setPosition(getWidth() - 10, getHeight() - 15, Align.topRight);
            addActor(image);
            imgIcon2 = image;
        }
        {
            anim = new LazyAnimationDrawable(resources.getAnimation(Resources.atlas.ui, "damage", .045f));
            anim.setFinishedListener(new LazyAnimationDrawable.FinishedListener() {
                @Override
                public void finish() {
                    effect.remove();
                }
            });
            Image image = new Image(anim);
            image.setSize(68, 68);
            image.setPosition(5, getHeight() - 10, Align.topLeft);
            effect = image;
        }
        {
            anim2 = new LazyAnimationDrawable(resources.getAnimation(Resources.atlas.ui, "damage", .045f));
            anim2.setFinishedListener(new LazyAnimationDrawable.FinishedListener() {
                @Override
                public void finish() {
                    effect2.remove();
                }
            });
            Image image = new Image(anim2);
            image.setSize(68, 68);
            image.setPosition(getWidth() - 5, getHeight() - 10, Align.topRight);
            effect2 = image;
        }
        BitmapFont font = resources.getBitmapFont("default.fnt");
        {
            Label text = new Label(resources.getString(10), new Label.LabelStyle(font, Color.WHITE));
            text.setAlignment(Align.center);
            text.setFontScale(.8f);
            text.setSize(68, text.getPrefHeight());
            text.setPosition(5, getHeight() - 80, Align.topLeft);
            addActor(text);
            labName = text;
        }
        {
            Label text = new Label(resources.getString(11), new Label.LabelStyle(font, Color.WHITE));
            text.setAlignment(Align.center);
            text.setFontScale(.8f);
            text.setSize(68, text.getPrefHeight());
            text.setPosition(getWidth() - 5, getHeight() - 80, Align.topRight);
            addActor(text);
            labName2 = text;
        }
        {
            Label label = new Label(resources.getString(12), new Label.LabelStyle(font, Color.WHITE));
            label.setFontScale(.6f);
            label.setSize(label.getPrefWidth(), label.getPrefHeight());
            label.setPosition(78, getHeight() - 10, Align.topLeft);
            addActor(label);
        }
        {
            Image image = new Image(resources.getRegion(Resources.atlas.ui, "line"));
            image.setPosition(78, getHeight() - 40, Align.topLeft);
            addActor(image);
        }
        {
            Label label = new Label("0", new Label.LabelStyle(font, Color.WHITE));
            label.setFontScale(.6f);
            label.setSize(75, label.getPrefHeight());
            label.setPosition(78, getHeight() - 40, Align.topLeft);
            label.setAlignment(Align.right, Align.right);
            addActor(label);
            labHp = label;
        }
        {
            Label label = new Label(resources.getString(13), new Label.LabelStyle(font, Color.WHITE));
            label.setFontScale(.6f);
            label.setSize(label.getPrefWidth(), label.getPrefHeight());
            label.setPosition(78, getHeight() - 10 - 50, Align.topLeft);
            addActor(label);
        }
        {
            Image image = new Image(resources.getRegion(Resources.atlas.ui, "line"));
            image.setPosition(78, getHeight() - 40 - 50, Align.topLeft);
            addActor(image);
        }
        {
            Label label = new Label("0", new Label.LabelStyle(font, Color.WHITE));
            label.setFontScale(.6f);
            label.setSize(75, label.getPrefHeight());
            label.setPosition(78, getHeight() - 40 - 50, Align.topLeft);
            label.setAlignment(Align.right, Align.right);
            addActor(label);
            labAttack = label;
        }
        {
            Label label = new Label(resources.getString(14), new Label.LabelStyle(font, Color.WHITE));
            label.setFontScale(.6f);
            label.setSize(label.getPrefWidth(), label.getPrefHeight());
            label.setPosition(78, getHeight() - 10 - 50 * 2, Align.topLeft);
            addActor(label);
        }
        {
            Image image = new Image(resources.getRegion(Resources.atlas.ui, "line"));
            image.setPosition(78, getHeight() - 40 - 50 * 2, Align.topLeft);
            addActor(image);
        }
        {
            Label label = new Label("0", new Label.LabelStyle(font, Color.WHITE));
            label.setFontScale(.6f);
            label.setSize(75, label.getPrefHeight());
            label.setPosition(78, getHeight() - 40 - 50 * 2, Align.topLeft);
            label.setAlignment(Align.right, Align.right);
            addActor(label);
            labDefence = label;
        }
        {
            Label label = new Label(resources.getString(12), new Label.LabelStyle(font, Color.WHITE));
            label.setFontScale(.6f);
            label.setSize(label.getPrefWidth(), label.getPrefHeight());
            label.setPosition(getWidth() - 78, getHeight() - 10, Align.topRight);
            addActor(label);
        }
        {
            Image image = new Image(resources.getRegion(Resources.atlas.ui, "line"));
            image.setPosition(getWidth() - 78, getHeight() - 40, Align.topRight);
            addActor(image);
        }
        {
            Label label = new Label("0", new Label.LabelStyle(font, Color.WHITE));
            label.setFontScale(.6f);
            label.setSize(75, label.getPrefHeight());
            label.setPosition(getWidth() - 78, getHeight() - 40, Align.topRight);
            addActor(label);
            labHp2 = label;
        }
        {
            Label label = new Label(resources.getString(13), new Label.LabelStyle(font, Color.WHITE));
            label.setFontScale(.6f);
            label.setSize(label.getPrefWidth(), label.getPrefHeight());
            label.setPosition(getWidth() - 78, getHeight() - 10 - 50, Align.topRight);
            addActor(label);
        }
        {
            Image image = new Image(resources.getRegion(Resources.atlas.ui, "line"));
            image.setPosition(getWidth() - 78, getHeight() - 40 - 50, Align.topRight);
            addActor(image);
        }
        {
            Label label = new Label("0", new Label.LabelStyle(font, Color.WHITE));
            label.setFontScale(.6f);
            label.setSize(75, label.getPrefHeight());
            label.setPosition(getWidth() - 78, getHeight() - 40 - 50, Align.topRight);
            addActor(label);
            labAttack2 = label;
        }
        {
            Label label = new Label(resources.getString(14), new Label.LabelStyle(font, Color.WHITE));
            label.setFontScale(.6f);
            label.setSize(label.getPrefWidth(), label.getPrefHeight());
            label.setPosition(getWidth() - 78, getHeight() - 10 - 50 * 2, Align.topRight);
            addActor(label);
        }
        {
            Image image = new Image(resources.getRegion(Resources.atlas.ui, "line"));
            image.setPosition(getWidth() - 78, getHeight() - 40 - 50 * 2, Align.topRight);
            addActor(image);
        }
        {
            Label label = new Label("0", new Label.LabelStyle(font, Color.WHITE));
            label.setFontScale(.6f);
            label.setSize(75, label.getPrefHeight());
            label.setPosition(getWidth() - 78, getHeight() - 40 - 50 * 2, Align.topRight);
            addActor(label);
            labDefence2 = label;
        }
    }

    public void setBattleListener(BattleListener listener) {
        mBattleListener = listener;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
        labHp.setText(String.valueOf(hero.getHp()));
        labAttack.setText(String.valueOf(hero.getAttack()));
        labDefence.setText(String.valueOf(hero.getDefence()));
    }

    public void setMonster(Sprite monster) {
        this.monster = monster;
        Resources resources = GameContext.instance().getResources();
        imgIcon2.setDrawable(new AnimationDrawable(resources.getAnimation(
                Resources.atlas.character, monster.getDrawable(), .25f))
                .setPlayMode(Animation.PlayMode.LOOP));
        labHp2.setText(String.valueOf(monster.getHp()));
        labAttack2.setText(String.valueOf(monster.getAttack()));
        labDefence2.setText(String.valueOf(monster.getDefence()));
    }

    public void begin() {
        currentHeroHp = hero.getHp();
        currentMonsterHp = monster.getHp();
        switch (monster.getId()) {
            // 麻衣法师
            case 49:
                addAction(Actions.delay(.5f, Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        suckHero(100);
                        GameContext.instance().getResources().getSound("suck01.mp3").play();
                    }
                })));
                break;
            // 红衣法师
            case 56:
                addAction(Actions.delay(.5f, Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        suckHero(300);
                        GameContext.instance().getResources().getSound("suck02.mp3").play();
                    }
                })));
                break;
            // 白衣武士
            case 54:
                addAction(Actions.delay(.5f, Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        suckHero(hero.getHp() / 4);
                        GameContext.instance().getResources().getSound("suck03.mp3").play();
                    }
                })));
                break;
            // 灵法师
            case 63:
                addAction(Actions.delay(.5f, Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        suckHero(hero.getHp() / 3);
                        GameContext.instance().getResources().getSound("suck04.mp3").play();
                    }
                })));
                break;
            default:
                addAction(Actions.delay(.5f, Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        attackMonster();
                    }
                })));
                break;
        }
    }

    private void suckHero(int damage) {
        currentHeroHp -= damage;
        if (currentHeroHp > 0) {
            labHp.setText(String.valueOf(currentHeroHp));
            addAction(Actions.delay(.3f, Actions.run(new Runnable() {
                @Override
                public void run() {
                    attackMonster();
                }
            })));
        } else {
            currentHeroHp = 0;
            labHp.setText("0");
            addAction(Actions.delay(.3f, Actions.run(new Runnable() {
                @Override
                public void run() {
                    failure();
                }
            })));
        }
        playEffect1();
    }

    private void attackMonster() {
        int damage = Math.max(0, hero.getAttack() - monster.getDefence());
        currentMonsterHp -= damage;
        if (currentMonsterHp > 0) {
            labHp2.setText(String.valueOf(currentMonsterHp));
            addAction(Actions.delay(.3f, Actions.run(new Runnable() {
                @Override
                public void run() {
                    attackHero();
                }
            })));
        } else {
            currentMonsterHp = 0;
            labHp2.setText("0");
            addAction(Actions.delay(.3f, Actions.run(new Runnable() {
                @Override
                public void run() {
                    success();
                }
            })));
        }
        playEffect2();
        playAttackSound();
    }

    private void attackHero() {
        int damage = Math.max(0, monster.getAttack() - hero.getDefence());
        currentHeroHp -= damage;
        if (currentHeroHp > 0) {
            labHp.setText(String.valueOf(currentHeroHp));
            addAction(Actions.delay(.3f, Actions.run(new Runnable() {
                @Override
                public void run() {
                    attackMonster();
                }
            })));
        } else {
            currentHeroHp = 0;
            labHp.setText("0");
            addAction(Actions.delay(.3f, Actions.run(new Runnable() {
                @Override
                public void run() {
                    failure();
                }
            })));
        }
        playEffect1();
        playAttackSound();
    }

    public void playEffect1() {
        anim.reset();
        anim.start();
        addActor(effect);
    }

    public void playEffect2() {
        anim2.reset();
        anim2.start();
        addActor(effect2);
    }

    public void playAttackSound() {
        Resources resources = GameContext.instance().getResources();
        Sound sound;
        switch (MathUtils.random(1)) {
            case 0:
                sound = resources.getSound("attack01.mp3");
                break;
            default:
                sound = resources.getSound("attack02.mp3");
                break;
        }
        sound.play();
    }

    private void success() {
        dismiss();
        if (mBattleListener != null) {
            mBattleListener.success(currentHeroHp, currentMonsterHp);
        }
    }

    private void failure() {
        dismiss();
        if (mBattleListener != null) {
            mBattleListener.failure(currentHeroHp, currentMonsterHp);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (mDrawable != null) {
            mDrawable.draw(batch, getX(), getY(), getWidth(), getHeight());
        }
        super.draw(batch, parentAlpha);
    }

    @Override
    public void reset() {
        super.reset();
        setBattleListener(null);
        clearActions();
    }

    public static interface BattleListener {
        void success(int heroHp, int monsterHp);
        void failure(int heroHp, int monsterHp);
    }
}
