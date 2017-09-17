package com.kadilo.game.screens.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.kadilo.game.common.Background;
import com.kadilo.game.common.HUD;
import com.kadilo.game.engine.Base2DScreen;
import com.kadilo.game.engine.Sprite2DTexture;
import com.kadilo.game.engine.math.Rect;
import com.kadilo.game.engine.ui.ActionListener;

/**
 * Created by avetc on 14.09.2017.
 */

public class GameScreenLevel1 extends Base2DScreen implements ActionListener {

    private enum State {CUT_SCENE, GAME}

    private State state;
    private Background bg;
    private Sprite2DTexture textureBackground;
    private TextureAtlas hudAtlas;
    private TextureAtlas popAtlas;


    private HUD hud;

    public GameScreenLevel1(Game game) {
        super(game);
    }

    private MainCharacter hero;

    @Override
    public void show() {
        super.show();

        textureBackground = new Sprite2DTexture("textures/level_bg.jpg");
        bg = new Background(new TextureRegion(textureBackground));

        hudAtlas = new TextureAtlas("textures/HUD/hudAtlas.tpack");
        hud = new HUD(hudAtlas);

        popAtlas = new TextureAtlas("textures/characters/pop/popAtlas.tpack");
        hero = new MainCharacter(popAtlas, 1, 10, 10, hud, worldBounds);

    }

    @Override
    protected void resize(Rect worldBounds) {

        bg.resize(worldBounds);
        hud.resize(worldBounds);
        hero.resize(worldBounds);
    }

    private void update(float deltaTime) {
        hero.update(deltaTime);
    }

    private void draw() {
        Gdx.gl.glClearColor(0.7f, 0.7f, 0.7f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
            bg.draw(batch);
            hud.draw(batch);
            hero.draw(batch);
        batch.end();
    }

    @Override
    public void render(float delta) {
        update(delta);
        draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        hudAtlas.dispose();
        textureBackground.dispose();
        popAtlas.dispose();
    }

    @Override
    protected void touchDown(Vector2 touch, int pointer) {
//        switch (state) {
//            case PLAYING:
                hero.touchDown(touch, pointer);
           //     break;
//            case GAME_OVER:
//                buttonNewGame.touchDown(touch, pointer);
//                break;
//            default:
//                throw new RuntimeException("Unknown state = " + state);
//        }
    }

    @Override
    protected void touchUp(Vector2 touch, int pointer) {
//        switch (state) {
//            case PLAYING:
                hero.touchUp(touch, pointer);
//                break;
//            case GAME_OVER:
//                buttonNewGame.touchUp(touch, pointer);
//                break;
//            default:
//                throw new RuntimeException("Unknown state = " + state);
//        }
    }

    @Override
    public boolean keyDown(int keycode) {
      hero.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        hero.keyUp(keycode);
        return false;
    }

    @Override
    public void actionPerformed(Object src) {

    }
}
