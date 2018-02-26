package com.cncoderx.game.magictower.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.Pool;
import com.cncoderx.game.magictower.Game;
import com.cncoderx.game.magictower.GameContext;
import com.cncoderx.game.magictower.Resources;
import com.cncoderx.game.magictower.UIUpdateCallback;
import com.cncoderx.game.magictower.data.DataManager;
import com.cncoderx.game.magictower.data.Hero;
import com.cncoderx.game.magictower.data.Layer;
import com.cncoderx.game.magictower.data.Map;
import com.cncoderx.game.magictower.data.Sprite;
import com.cncoderx.game.magictower.data.Tile;
import com.cncoderx.game.magictower.drawable.LazyAnimationDrawable;
import com.cncoderx.game.magictower.trigger.BattleTrigger;
import com.cncoderx.game.magictower.trigger.ChatWithGhostLordTrigger;
import com.cncoderx.game.magictower.trigger.ChatWithRedLordTrigger;
import com.cncoderx.game.magictower.trigger.EnterStairTrigger;
import com.cncoderx.game.magictower.trigger.OpenDoorTrigger;
import com.cncoderx.game.magictower.trigger.PickPropsTrigger;
import com.cncoderx.game.magictower.trigger.ShoppingTrigger;
import com.cncoderx.game.magictower.trigger.TouchWithAngleTrigger;
import com.cncoderx.game.magictower.trigger.TouchWithBlueMerchantTrigger;
import com.cncoderx.game.magictower.trigger.TouchWithPrincessTrigger;
import com.cncoderx.game.magictower.trigger.TouchWithRedMerchantTrigger;
import com.cncoderx.game.magictower.trigger.TouchWithThiefTrigger;
import com.cncoderx.game.magictower.trigger.Trigger;
import com.cncoderx.game.magictower.trigger.TriggerManager;
import com.cncoderx.game.magictower.utils.Global;
import com.cncoderx.game.magictower.utils.Point;
import com.cncoderx.game.magictower.widget.HeroWidget;
import com.cncoderx.game.magictower.widget.NodeWidget;
import com.cncoderx.game.magictower.widget.Toast;

/**
 * Created by admin on 2017/5/24.
 */
public class ContentScene extends Scene implements Game, HeroWidget.PositionChangedListener  {
    public static final Logger logger = new Logger("MagicTower");

    private Map map;
    private Hero hero;
    private Music bgm;

    private TileLayout mTileLayout;

    private boolean touchable = true;
    private TriggerManager mTriggerManager;
    private UIUpdateCallback mUIUpdateCallback;

    Pool<ChatDialog> mChatDialogPool = new Pool<ChatDialog>() {
        @Override
        protected ChatDialog newObject() {
            ChatDialog dialog = new ChatDialog(ContentScene.this);
            dialog.setPosition(getWidth() / 2, 20, Align.bottom);
            return dialog;
        }
    };

    Pool<ShopDialog> mShopDialogPool = new Pool<ShopDialog>() {
        @Override
        protected ShopDialog newObject() {
            ShopDialog dialog = new ShopDialog(ContentScene.this);
            dialog.setPosition(getWidth() / 2, getHeight() / 2, Align.center);
            return dialog;
        }
    };

    Pool<BattleDialog> mBattleDialogPool = new Pool<BattleDialog>() {
        @Override
        protected BattleDialog newObject() {
            BattleDialog dialog = new BattleDialog(ContentScene.this);
            dialog.setPosition(getWidth() / 2, getHeight() / 2, Align.center);
            return dialog;
        }
    };

    public ContentScene() {
        setSize(Global.MAP_WIDTH, Global.MAP_HEIGHT);
        initWidget();
        registerTriggers();
    }

    private void initWidget() {
        mTileLayout = new TileLayout();
        addActor(mTileLayout);
    }

    private void registerTriggers() {
        mTriggerManager = new TriggerManager(this);
        mTriggerManager.registerTrigger(new TouchWithAngleTrigger(Trigger.ID_TOUCH));
        mTriggerManager.registerTrigger(new TouchWithRedMerchantTrigger(Trigger.ID_TOUCH));
        mTriggerManager.registerTrigger(new TouchWithBlueMerchantTrigger(Trigger.ID_TOUCH));
        mTriggerManager.registerTrigger(new TouchWithPrincessTrigger(Trigger.ID_TOUCH));
        mTriggerManager.registerTrigger(new TouchWithThiefTrigger(Trigger.ID_TOUCH));
        mTriggerManager.registerTrigger(new OpenDoorTrigger(Trigger.ID_TOUCH));
        mTriggerManager.registerTrigger(new BattleTrigger(Trigger.ID_TOUCH));
        mTriggerManager.registerTrigger(new ShoppingTrigger(Trigger.ID_TOUCH));
        mTriggerManager.registerTrigger(new EnterStairTrigger(Trigger.ID_ENTER));
        mTriggerManager.registerTrigger(new PickPropsTrigger(Trigger.ID_ENTER));
        mTriggerManager.registerTrigger(new ChatWithRedLordTrigger(Trigger.ID_ENTER));
        mTriggerManager.registerTrigger(new ChatWithGhostLordTrigger(Trigger.ID_ENTER));
    }

    public void setUIUpdateCallback(UIUpdateCallback Callback) {
        mUIUpdateCallback = Callback;
    }

    @Override
    public void onShow() {

    }

    @Override
    public void onHide() {

    }

    @Override
    public Hero getHero() {
        return hero;
    }

    @Override
    public Map getMap() {
        return map;
    }

    @Override
    public HeroWidget getWidget() {
        return mTileLayout.getHeroWidget();
    }

    @Override
    public NodeWidget getWidget(int x, int y) {
        return mTileLayout.getWidget(x, y);
    }

    @Override
    public TriggerManager getTriggerManager() {
        return mTriggerManager;
    }

    @Override
    public ChatDialog getChatDialog() {
        return mChatDialogPool.obtain();
    }

    @Override
    public ShopDialog getShopDialog() {
        return mShopDialogPool.obtain();
    }

    @Override
    public BattleDialog getBattleDialog() {
        return mBattleDialogPool.obtain();
    }

    @Override
    public DataManager getDataManager() {
        return GameContext.instance().getResources().getDataManager();
    }

    @Override
    public void showToast(String message) {
        Toast.show(this, message);
    }

    @Override
    public void updateNode(int x, int y) {
        Resources resources = GameContext.instance().getResources();
        int tileId = map.getTileId(x, y);
        int layerId = map.getLayerId(x, y);

        NodeWidget widget = mTileLayout.getWidget(x, y);
        widget.setDrawable(null);
        widget.setBackground(null);
        Tile tile = getDataManager().getTile(tileId);
        if (tile != null) {
            Drawable drawable = resources.getDrawable(
                    Resources.atlas.character, tile.getDrawable());
            widget.setBackground(drawable);
        }
        Layer layer = getDataManager().getLayer(layerId);
        if (layer == null) {
            layer = getDataManager().getSprite(layerId);
        }
        if (layer != null) {
            Drawable drawable = (layerId == 93 || layerId == 94 || layerId == 95 || layerId == 96 || layerId == 90 || layerId == 97) ?
                    new LazyAnimationDrawable(resources.getAnimation(Resources.atlas.character, layer.getDrawable(), .025f)) :
                    resources.getDrawable(Resources.atlas.character, layer.getDrawable());
            widget.setDrawable(drawable);
        }
    }

    @Override
    public void switchFloor(final int floor, final int n) {
        touchable = false;
        mTileLayout.getHeroWidget().stopMoveDown();
        mTileLayout.addAction(Actions.sequence(Actions.alpha(0, 0.3f), Actions.run(new Runnable() {
            @Override
            public void run() {
                setCurrentFloor(floor, n);
            }
        }), Actions.alpha(1, 0.3f), Actions.run(new Runnable() {
            @Override
            public void run() {
                touchable = true;
            }
        })));

        GameContext.instance().getResources().getSound("access.mp3").play();
    }

    public void setCurrentFloor(int floor, int n) {
        int curFloor = map.getCurrent();
        int overFloor = map.getOverFloor();
        if (floor > overFloor) {
            map.setOverFloor(floor);
        }
        map.setCurrent(floor);
        updateUI();
        updateMap();
        Point point;
        if (floor >= curFloor) {
            point = map.getFloor().getDownstair(n);
        } else {
            point = map.getFloor().getUpstair(n);
        }
        hero.setPoint(point.x, point.y, hero.getPoint().v);
        mTileLayout.getHeroWidget().update();
    }

    @Override
    public void updateUI() {
        if (mUIUpdateCallback != null) {
            mUIUpdateCallback.getLevel(hero.getLevel());
            mUIUpdateCallback.getHp(hero.getHp());
            mUIUpdateCallback.getAttack(hero.getAttack());
            mUIUpdateCallback.getDefence(hero.getDefence());
            mUIUpdateCallback.getCoin(hero.getMoney());
            mUIUpdateCallback.getExp(hero.getExp());
            mUIUpdateCallback.getYellowKey(hero.getYellowKey());
            mUIUpdateCallback.getBlueKey(hero.getBlueKey());
            mUIUpdateCallback.getRedKey(hero.getRedKey());
            mUIUpdateCallback.getNote(hero.hasProps(Hero.PROPS_NOTE));
            mUIUpdateCallback.getCompass(hero.hasProps(Hero.PROPS_COMPASS));
            mUIUpdateCallback.getFloor(map.getFloor().getIndex());
        }
    }

    @Override
    public void updateMap() {
        for (int y = 0; y < Global.ROW_SIZE; y++) {
            for (int x = 0; x < Global.COL_SIZE; x++) {
                updateNode(x, y);
            }
        }
        resetBGM();
    }

    public void resetBGM() {
        int index = map.getFloor().getIndex();
        Resources resources = GameContext.instance().getResources();
        Music music = null;
        if (index == 0) {
            music = resources.getMusic("bgm02.mp3");
        } else if (index <= 7) {
            music = resources.getMusic("bgm03.mp3");
        } else if (index <= 14) {
            music = resources.getMusic("bgm04.mp3");
        } else if (index <= 18) {
            music = resources.getMusic("bgm05.mp3");
        } else if (index <= 24) {
            music = resources.getMusic("bgm01.mp3");
        }
        if (music != bgm) {
            if (bgm != null) {
                bgm.stop();
            }
            if (music != null) {
                music.setLooping(true);
                music.play();
                bgm = music;
            }
        }
    }

    public Music getBgm() {
        return bgm;
    }

    public void updateHero() {
        HeroWidget widget = mTileLayout.getHeroWidget();
        widget.setPoint(hero.getPoint());
        widget.setPositionChangedListener(this);
        widget.update();
        updateUI();
    }

    @Override
    public boolean onPressLeft() {
        logger.info("onPressLeft");
        if (!touchable) return false;

        moveHeroLeft(true);
        return false;
    }

    @Override
    public boolean onPressUp() {
        logger.info("onPressUp");
        if (!touchable) return false;

        moveHeroUp(true);
        return false;
    }

    @Override
    public boolean onPressRight() {
        logger.info("onPressRight");
        if (!touchable) return false;

        moveHeroRight(true);
        return false;
    }

    @Override
    public boolean onPressDown() {
        logger.info("onPressDown");
        if (!touchable) return false;

        moveHeroDown(true);
        return false;
    }

    public void moveHeroLeft(boolean notify) {
        HeroWidget widget = mTileLayout.getHeroWidget();
        if (widget == null) return;
        int x = widget.getPoint().x;
        int y = widget.getPoint().y;
        widget.faceLeft();
        int result = checkCollision(x - 1, y);
        if (result == 0) {
            widget.moveLeft();
        } else if (result == 1 && notify) {
            mTriggerManager.notifyTrigger(Trigger.ID_TOUCH);
        }
    }

    public void moveHeroUp(boolean notify) {
        HeroWidget widget = mTileLayout.getHeroWidget();
        if (widget == null) return;
        int x = widget.getPoint().x;
        int y = widget.getPoint().y;
        widget.faceUp();
        int result = checkCollision(x, y + 1);
        if (result == 0) {
            widget.moveUp();
        } else if (result == 1 && notify) {
            mTriggerManager.notifyTrigger(Trigger.ID_TOUCH);
        }
    }

    public void moveHeroRight(boolean notify) {
        HeroWidget widget = mTileLayout.getHeroWidget();
        if (widget == null) return;
        int x = widget.getPoint().x;
        int y = widget.getPoint().y;
        widget.faceRight();
        int result = checkCollision(x + 1, y);
        if (result == 0) {
            widget.moveRight();
        } else if (result == 1 && notify) {
            mTriggerManager.notifyTrigger(Trigger.ID_TOUCH);
        }
    }

    public void moveHeroDown(boolean notify) {
        HeroWidget widget = mTileLayout.getHeroWidget();
        if (widget == null) return;
        int x = widget.getPoint().x;
        int y = widget.getPoint().y;
        widget.faceDown();
        int result = checkCollision(x, y - 1);
        if (result == 0) {
            widget.moveDown();
        } else if (result == 1 & notify) {
            mTriggerManager.notifyTrigger(Trigger.ID_TOUCH);
        }
    }

    public int checkCollision(int x, int y) {
        if (x < 0 || x > Global.COL_SIZE - 1) {
            return -1;
        }
        if (y < 0 || y > Global.ROW_SIZE - 1) {
            return -1;
        }
        int tileId = map.getTileId(x, y);
        if (tileId != 1) {
            return 1;
        }
        int layerId = map.getLayerId(x, y);
        if (layerId == -1 ||
                layerId == 91 ||
                layerId == 92 ||
                layerId >= 0 && layerId <= 28)
            return 0;

        return 1;
    }

    @Override
    public boolean onReleaseLeft() {
        logger.info("onReleaseLeft");
        if (!touchable) return false;

        mTileLayout.getHeroWidget().stopMoveLeft();
        return false;
    }

    @Override
    public boolean onReleaseUp() {
        logger.info("onReleaseUp");
        if (!touchable) return false;

        mTileLayout.getHeroWidget().stopMoveUp();
        return false;
    }

    @Override
    public boolean onReleaseRight() {
        logger.info("onReleaseRight");
        if (!touchable) return false;

        mTileLayout.getHeroWidget().stopMoveRight();
        return false;
    }

    @Override
    public boolean onReleaseDown() {
        logger.info("onReleaseDown");
        if (!touchable) return false;

        mTileLayout.getHeroWidget().stopMoveDown();
        return false;
    }

    @Override
    public boolean onBackPressed() {
        logger.info("onBackPressed");
        if (!touchable) return false;

        if (mUIUpdateCallback != null) {
            mUIUpdateCallback.showMenu();
        }
        return true;
    }

    @Override
    public void positionChanged(HeroWidget widget) {
        mTriggerManager.notifyTrigger(Trigger.ID_ENTER);
        switch (widget.getVector()) {
            case Global.LEFT:
                moveHeroLeft(false);
                break;
            case Global.UP:
                moveHeroUp(false);
                break;
            case Global.RIGHT:
                moveHeroRight(false);
                break;
            case Global.DOWN:
                moveHeroDown(false);
                break;
        }
    }

    public void newState() {
        map = new Map(Gdx.files.internal("map"));
        Sprite sprite = getDataManager().getSprite(80);
        hero = new Hero();
        hero.setHp(sprite.getHp());
        hero.setAttack(sprite.getAttack());
        hero.setDefence(sprite.getDefence());
        hero.setMoney(sprite.getMoney());
        hero.setExp(sprite.getExp());

//        hero.setAttack(100000);
//        hero.setDefence(100000);
//        hero.putYellowKey(1000);
//        hero.putBlueKey(1000);
//        hero.putRedKey(1000);

        Point point = map.getFloor().getDownstair(0);
        hero.setPoint(point.x, point.y, Global.UP);

        getDataManager().setSpriteLevel(0);
        getDataManager().setLordLevel(0);
        if (hero.withNPC(Hero.KILLED_RED_LORD)) {
            getDataManager().setSpriteLevel(1);
        }
        if (hero.withNPC(Hero.KILLED_GHOST_LORD_FIRST)) {
            getDataManager().setLordLevel(1);
        }
        if (hero.withNPC(Hero.KILLED_GHOST_LORD_SECOND)) {
            getDataManager().setLordLevel(2);
            getDataManager().setSpriteLevel(2);
        }
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                updateMap();
                updateHero();
                mTileLayout.startAnimation();
            }
        });
    }

    public void loadState() {
        DataManager dm = getDataManager();
        if (map == null) {
            map = new Map();
        }
        if (hero == null) {
            Sprite sprite = getDataManager().getSprite(80);
            hero = new Hero();
            hero.setHp(sprite.getHp());
            hero.setAttack(sprite.getAttack());
            hero.setDefence(sprite.getDefence());
            hero.setMoney(sprite.getMoney());
            hero.setExp(sprite.getExp());
        }
        if (dm.load(map, hero)) {
            getDataManager().setSpriteLevel(0);
            getDataManager().setLordLevel(0);
            if (hero.withNPC(Hero.KILLED_RED_LORD)) {
                getDataManager().setSpriteLevel(1);
            }
            if (hero.withNPC(Hero.KILLED_GHOST_LORD_FIRST)) {
                getDataManager().setLordLevel(1);
            }
            if (hero.withNPC(Hero.KILLED_GHOST_LORD_SECOND)) {
                getDataManager().setLordLevel(2);
                getDataManager().setSpriteLevel(2);
            }
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    updateMap();
                    updateHero();
                    mTileLayout.startAnimation();
                    showToast(GameContext.instance().getResources().getString(21));
                }
            });
        } else {
            newState();
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    showToast(GameContext.instance().getResources().getString(22));
                }
            });
        }
    }

    public void saveState() {
        DataManager dm = getDataManager();
        if (map == null || hero == null)
            return;

        if (dm.save(map, hero)) {
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    showToast(GameContext.instance().getResources().getString(23));
                }
            });
        } else {
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    showToast(GameContext.instance().getResources().getString(24));
                }
            });
        }
    }
}
