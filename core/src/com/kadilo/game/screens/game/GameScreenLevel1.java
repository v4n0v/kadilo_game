package com.kadilo.game.screens.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kadilo.game.common.Background;
import com.kadilo.game.common.HUD;
import com.kadilo.game.engine.Base2DScreen;
import com.kadilo.game.engine.Sprite2DTexture;
import com.kadilo.game.engine.math.Rect;
import com.kadilo.game.engine.sprites.Sprite;
import com.kadilo.game.engine.ui.ActionListener;

/**
 * Created by avetc on 14.09.2017.
 */

public class GameScreenLevel1 extends Base2DScreen implements ActionListener {

    private enum State {CUT_SCENE, GAME}
    private  State state;
    private Background bg;
    private Sprite2DTexture textureBackground;
    private TextureAtlas atlas;
    private HUD hud;
    public GameScreenLevel1(Game game) {
        super(game);
    }


    @Override
    public void show() {
        super.show();

        atlas=  new TextureAtlas("textures/HUD/hud.tpack");
        textureBackground = new Sprite2DTexture("textures/level_bg.jpg");

        hud=new HUD(atlas);
        bg= new Background(new TextureRegion(textureBackground));
    }

    @Override
    protected void resize(Rect worldBounds) {
         hud.resize(worldBounds);
        bg.resize(worldBounds);
    }

    private void update(float deltaTime){

    }
    private void draw() {
        Gdx.gl.glClearColor(0.7f, 0.7f, 0.7f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
            bg.draw(batch);
            hud.draw(batch);
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
        atlas.dispose();
        textureBackground.dispose();
    }



    @Override
    public void actionPerformed(Object src) {

    }
}
