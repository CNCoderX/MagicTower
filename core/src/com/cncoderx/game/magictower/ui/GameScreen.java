package com.cncoderx.game.magictower.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;
import com.cncoderx.game.magictower.BaseScreen;
import com.cncoderx.game.magictower.GameContext;
import com.cncoderx.game.magictower.Resources;
import com.cncoderx.game.magictower.UIUpdateCallback;
import com.cncoderx.game.magictower.data.Hero;
import com.cncoderx.game.magictower.data.Sprite;
import com.cncoderx.game.magictower.drawable.DrawableContainer;
import com.cncoderx.game.magictower.utils.GameViewport;
import com.cncoderx.game.magictower.utils.Global;
import com.cncoderx.game.magictower.widget.DirectionButton;

import java.util.Comparator;
import java.util.Iterator;

/**
 * Created by admin on 2017/5/24.
 */
public class GameScreen extends BaseScreen implements GameContext.GameListener, UIUpdateCallback {
    private GameViewport mViewport;
    private GameStage mStage;
    private Container mContainer;
    private ContentScene mContentScene;
    private NoteScene mNoteScene;
    private FloorScene mFloorScene;
    private EndingScene mEndingScene;
    private Scene currentScene;
    private MenuDialog menuDialog;

    private Image imgNode, imgCompass;
    private DirectionButton mDirectionButton;

    private Label labHp;
    private Label labAttack;
    private Label labDefence;
    private Label labCoin;
    private Label labExp;
    private Label labFloor;
    private Label labYellowKey;
    private Label labBlueKey;
    private Label labRedKey;

    @Override
    public void show() {
        super.show();
        mViewport = new GameViewport(
                Global.SCREEN_WIDTH, Global.SCREEN_HEIGHT);
        mStage = new GameStage(mViewport);
//        mStage.setDebugAll(true);

        initWidget();
        initScene();

        Gdx.input.setInputProcessor(mStage);
        Gdx.input.setCatchBackKey(true);

        GameContext.instance().setGameListener(this);
    }

    private void initWidget() {
        Resources resources = GameContext.instance().getResources();
        Image background = new Image(resources.getTexture("bg.jpg"));
        mStage.addActor(background);

        mContainer = new Container();
        mContainer.setBackground(new TextureRegionDrawable(
                resources.getRegion(Resources.atlas.ui, "black")));
        mContainer.setSize(Global.MAP_WIDTH, Global.MAP_HEIGHT);
        mContainer.fill();
        mContainer.setPosition(Global.SCREEN_WIDTH / 2,
                Global.SCREEN_HEIGHT - 16, Align.top);

        mStage.addActor(mContainer);

        {
            BitmapFont font = resources.getBitmapFont("default.fnt");
            {
                Table table = new Table();
                table.setSize(5 * 32, 7 * 32 + 16);
                table.setPosition(32, 16);
                {
                    Label label = new Label(resources.getString(12), new Label.LabelStyle(font, Color.WHITE));
                    label.setFontScale(.8f);
                    table.add(label).height(28).padLeft(5).padTop(5);
                }
                {
                    Label label = new Label("1000", new Label.LabelStyle(font, Color.WHITE));
                    label.setFontScale(.65f);
                    table.add(label).height(28).expandX().padTop(5).row();
                    labHp = label;
                }
                {
                    Label label = new Label(resources.getString(13), new Label.LabelStyle(font, Color.WHITE));
                    label.setFontScale(.8f);
                    table.add(label).height(28).padLeft(5);
                }
                {
                    Label label = new Label("10", new Label.LabelStyle(font, Color.WHITE));
                    label.setFontScale(.65f);
                    table.add(label).height(28).expandX().row();
                    labAttack = label;
                }
                {
                    Label label = new Label(resources.getString(14), new Label.LabelStyle(font, Color.WHITE));
                    label.setFontScale(.8f);
                    table.add(label).height(28).padLeft(5);
                }
                {
                    Label label = new Label("10", new Label.LabelStyle(font, Color.WHITE));
                    label.setFontScale(.65f);
                    table.add(label).height(28).expandX().row();
                    labDefence = label;
                }
                {
                    Label label = new Label(resources.getString(15), new Label.LabelStyle(font, Color.WHITE));
                    label.setFontScale(.8f);
                    table.add(label).height(28).padLeft(5);
                }
                {
                    Label label = new Label("0", new Label.LabelStyle(font, Color.WHITE));
                    label.setFontScale(.65f);
                    table.add(label).height(28).expandX().row();
                    labCoin = label;
                }
                {
                    Label label = new Label(resources.getString(16), new Label.LabelStyle(font, Color.WHITE));
                    label.setFontScale(.8f);
                    table.add(label).height(28).padLeft(5).padBottom(10);
                }
                {
                    Label label = new Label("0", new Label.LabelStyle(font, Color.WHITE));
                    label.setFontScale(.65f);
                    table.add(label).height(28).expandX().padBottom(10).row();
                    labExp = label;
                }
                {
                    Image image = new Image(resources.getRegion(Resources.atlas.character, "key_y"));
                    table.add(image).height(28).padLeft(5);
                }
                {
                    Label label = new Label("0", new Label.LabelStyle(font, Color.WHITE));
                    label.setFontScale(.65f);
                    table.add(label).height(28).expandX().row();
                    labYellowKey = label;
                }
                {
                    Image image = new Image(resources.getRegion(Resources.atlas.character, "key_b"));
                    table.add(image).height(28).padLeft(5);
                }
                {
                    Label label = new Label("0", new Label.LabelStyle(font, Color.WHITE));
                    label.setFontScale(.65f);
                    table.add(label).height(28).expandX().row();
                    labBlueKey = label;
                }
                {
                    Image image = new Image(resources.getRegion(Resources.atlas.character, "key_r"));
                    table.add(image).height(28).padLeft(5);
                }
                {
                    Label label = new Label("0", new Label.LabelStyle(font, Color.WHITE));
                    label.setFontScale(.65f);
                    table.add(label).height(28).expandX().row();
                    labRedKey = label;
                }
                mStage.addActor(table);
            }
            {
                Label label = new Label(resources.getString(18), new Label.LabelStyle(font, Color.WHITE));
                label.setAlignment(Align.center);
                label.setFontScale(.8f);
                label.setSize(4 * 32, 32);
                label.setPosition(8 * 32, 7 * 32);
                mStage.addActor(label);
                labFloor = label;
            }
            {
                Image image = new Image(resources.getRegion(Resources.atlas.character, "note"));
                image.setPosition(8 * 32 + 16, 5 * 32 + 8);
                image.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        if (canUsingTools()) {
                            setScene(mNoteScene);
                            mNoteScene.setData(getNoteItemArray());
                        }
                    }
                });
                mStage.addActor(image);
                image.setVisible(false);
                imgNode = image;
            }
            {
                Image image = new Image(resources.getRegion(Resources.atlas.character, "transfer"));
                image.setPosition(10 * 32 + 16, 5 * 32 + 8);
                image.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        if (canUsingTools()) {
                            int index = mContentScene.getMap().getFloor().getIndex();
                            int currentFloor = mContentScene.getMap().getCurrent();
                            int overFloor = mContentScene.getMap().getOverFloor();
                            if (index == 24 || (index == 21 && mContentScene.getMap().getTileId(5, 4) != 1)) {
                                mContentScene.showToast(GameContext.instance().getResources().getString(25));
                                return;
                            }
                            mFloorScene.setCurrentFloor(currentFloor);
                            mFloorScene.setOverFloor(overFloor);
                            setScene(mFloorScene);
                        }
                    }
                });
                mStage.addActor(image);
                image.setVisible(false);
                imgCompass = image;
            }
            {
                DirectionButton button = new DirectionButton(new DrawableContainer(
                        new TextureRegionDrawable(resources.getRegion(Resources.atlas.ui, "direction_normal")),
                        new TextureRegionDrawable(resources.getRegion(Resources.atlas.ui, "direction_left")),
                        new TextureRegionDrawable(resources.getRegion(Resources.atlas.ui, "direction_up")),
                        new TextureRegionDrawable(resources.getRegion(Resources.atlas.ui, "direction_right")),
                        new TextureRegionDrawable(resources.getRegion(Resources.atlas.ui, "direction_down"))));
                button.setPosition(8 * 32, 16);
                button.setSize(4 * 32, 4 * 32);
                button.setOrigin(Align.center);
                button.setDirectionButtonListener(
                        GameContext.instance().getOnTouchStateListeners());
                mStage.addActor(button);
                mDirectionButton = button;
            }
        }
    }

    private boolean canUsingTools() {
        if (currentScene == mContentScene) {
            int lastIdx = mContentScene.getChildren().size - 1;
            if (lastIdx >= 0) {
//                String name = mContentScene.getChildren().get(lastIdx).getName();
//                return name == null || !name.equals("BattleDialog");
                Actor v = mContentScene.getChildren().get(lastIdx);
                return !(v instanceof Dialog);
            }
        }
        return false;
    }

    private void initScene() {
        mContentScene = new ContentScene();
        mContentScene.setUIUpdateCallback(this);

        mNoteScene = new NoteScene();
        mNoteScene.setDismissListener(new NoteScene.DismissListener() {
            @Override
            public void onDismiss() {
                setScene(mContentScene);
            }
        });

        mFloorScene = new FloorScene();
        mFloorScene.setListener(new FloorScene.Listener() {
            @Override
            public void cancel() {
                setScene(mContentScene);
            }

            @Override
            public void select(int floor) {
                setScene(mContentScene);
                mContentScene.switchFloor(floor, 0);
            }
        });

        mEndingScene = new EndingScene();
        mEndingScene.setDismissListener(new EndingScene.DismissListener() {
            @Override
            public void onDismiss() {
                setScene(null);
                back();
            }
        });
    }

    public void newGame() {
        setScene(mContentScene);
        mContentScene.newState();
    }

    public void loadGame() {
        setScene(mContentScene);
        mContentScene.loadState();
    }

    private Array<NoteScene.Item> getNoteItemArray() {
        IntMap<NoteScene.Item> nodes = new IntMap<NoteScene.Item>();
        for (int y = 0; y < Global.ROW_SIZE; y++) {
            for (int x = 0; x < Global.COL_SIZE; x++) {
                int id = mContentScene.getMap().getLayerId(x, y);
                Sprite sprite = mContentScene.getDataManager().getSprite(id);
                if (sprite == null) continue;
                if (nodes.get(id) == null) {
                    nodes.put(id, createNoteItem(sprite));
                }
            }
        }
        Array<NoteScene.Item> items = new Array<NoteScene.Item>();
        Iterator<NoteScene.Item> iterator = nodes.values();
        while (iterator.hasNext()) {
            items.add(iterator.next());
        }
        items.sort(new Comparator<NoteScene.Item>() {
            @Override
            public int compare(NoteScene.Item lhs, NoteScene.Item rhs) {
                return lhs.id - rhs.id;
            }
        });
        nodes.clear();
        return items;
    }

    private NoteScene.Item createNoteItem(Sprite monster) {
        Hero hero = mContentScene.getHero();
        NoteScene.Item item = new NoteScene.Item();
        item.id = monster.getId();
        item.hp = monster.getHp();
        item.attack = monster.getAttack();
        item.defence = monster.getDefence();
        item.exp = monster.getExp();
        item.coin = monster.getMoney();
        item.drawable = monster.getDrawable();
        item.lose = monster.calcHeroLostHp(hero);
        return item;
    }

    public void setScene(Scene scene) {
        if (currentScene != scene) {
            Scene oldScene = currentScene;
            mContainer.setActor(scene);
            if (oldScene != null)
                oldScene.hide();
            if (scene != null)
                scene.show();

            currentScene = scene;
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        mViewport.update(width, height, true);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        mStage.act();
        mStage.draw();
    }

    @Override
    public void hide() {
        super.hide();
        mStage.dispose();
        GameContext.instance().setGameListener(null);
    }

    @Override
    public void getLevel(int value) {
//        labLevel.setText(String.valueOf(value));
    }

    @Override
    public void getHp(int value) {
        labHp.setText(String.valueOf(value));
    }

    @Override
    public void getAttack(int value) {
        labAttack.setText(String.valueOf(value));
    }

    @Override
    public void getDefence(int value) {
        labDefence.setText(String.valueOf(value));
    }

    @Override
    public void getCoin(int value) {
        labCoin.setText(String.valueOf(value));
    }

    @Override
    public void getExp(int value) {
        labExp.setText(String.valueOf(value));
    }

    @Override
    public void getYellowKey(int value) {
        labYellowKey.setText(String.valueOf(value));
    }

    @Override
    public void getBlueKey(int value) {
        labBlueKey.setText(String.valueOf(value));
    }

    @Override
    public void getRedKey(int value) {
        labRedKey.setText(String.valueOf(value));
    }

    @Override
    public void getFloor(int value) {
        switch (value) {
            case 0:
                labFloor.setText(GameContext.instance().getResources().getString(18));
                break;
            case 24:
                labFloor.setText(GameContext.instance().getResources().getString(20));
                break;
            default:
                labFloor.setText(GameContext.instance().getResources().getString(19, value));
                break;
        }
    }

    @Override
    public void getNote(boolean value) {
        imgNode.setVisible(value);
    }

    @Override
    public void getCompass(boolean value) {
        imgCompass.setVisible(value);
    }

    @Override
    public void showMenu() {
        if (menuDialog == null) {
            menuDialog = new MenuDialog();
            menuDialog.setListener(mDialogListener);
        }
        menuDialog.show(mStage);
    }

    @Override
    public void success() {
        imgCompass.setTouchable(Touchable.disabled);
        imgNode.setTouchable(Touchable.disabled);
        mDirectionButton.setTouchable(Touchable.disabled);
        if (mContentScene.getBgm() != null) {
            mContentScene.getBgm().stop();
        }
        if (currentScene == mContentScene) {
            currentScene.addAction(Actions.sequence(Actions.delay(.5f, Actions.alpha(0, 3f)),
                    Actions.run(new Runnable() {
                @Override
                public void run() {
                    setScene(mEndingScene);
                }
            })));
        }
    }

    @Override
    public void failure() {
        imgCompass.setTouchable(Touchable.disabled);
        imgNode.setTouchable(Touchable.disabled);
        mDirectionButton.setTouchable(Touchable.disabled);
        if (mContentScene.getBgm() != null) {
            mContentScene.getBgm().stop();
        }
        if (currentScene == mContentScene) {
            currentScene.addAction(Actions.sequence(Actions.delay(.5f, Actions.alpha(0, 3f)),
                    Actions.run(new Runnable() {
                        @Override
                        public void run() {
                            setScene(null);
                            back();
                        }
                    })));
        }
    }

    MenuDialog.Listener mDialogListener = new MenuDialog.Listener() {

        @Override
        public void resume() {
        }

        @Override
        public void load() {
            setScene(mContentScene);
            mContentScene.loadState();
        }

        @Override
        public void save() {
            setScene(mContentScene);
            mContentScene.saveState();
        }

        @Override
        public void restart() {
            setScene(mContentScene);
            mContentScene.newState();
        }

        @Override
        public void exit() {
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    Gdx.app.exit();
                }
            });
        }
    };
}
