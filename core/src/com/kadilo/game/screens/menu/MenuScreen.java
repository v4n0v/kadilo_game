package com.kadilo.game.screens.menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.kadilo.game.common.Background;
import com.kadilo.game.engine.Base2DScreen;
import com.kadilo.game.engine.Font;
import com.kadilo.game.engine.Sprite2DTexture;
import com.kadilo.game.engine.math.Rect;
import com.kadilo.game.engine.sprites.Sprite;
import com.kadilo.game.engine.ui.ActionListener;
import com.kadilo.game.engine.ui.ScaledSignedButton;
import com.kadilo.game.engine.ui.SignChecker;
import com.kadilo.game.screens.game.GameScreenLevel1;
import com.kadilo.game.screens.menu.ui.elements.MenuCharacter;

/**
 * Created by avetc on 13.09.2017.
 */

public class MenuScreen extends Base2DScreen implements ActionListener{

    private enum State {Main, Options, Credits, Exit}

    private Sprite2DTexture textureBackground;
    private TextureAtlas atlas;
    private Background background;

    private float aspect = worldBounds.getWidth()*worldBounds.getHeight();
    private final float  LOGO_LEFT_MARGIN = 0.08f;
    private final float  LOGO_TOP_MARGIN = 0.05f;
    private final float BUTTON_HEIGHT = 0.05f;
    private final float  VERTICAL_MARGIN = BUTTON_HEIGHT*1.1f;
    private static final float FONT_SIZE = 0.02f;

    private ScaledSignedButton btnNewGame;
    private ScaledSignedButton btnOptions;
    private ScaledSignedButton btnCredits;
    private ScaledSignedButton btnExit;

    private Background bg;
    private Sprite bgBottomLine;
    private Sprite logoHeader;
    private Sprite logoTxt;

    private Rectangle bgRext;
    private MenuCharacter menuCharacter;
    private SignChecker checker;

    Font font;

    public MenuScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        atlas = new TextureAtlas("textures/menu/menuAtlas.tpack");

         font=new Font("fonts/font.fnt", "fonts/font.png");
        font.setWorldSize(FONT_SIZE);

        menuCharacter=new MenuCharacter(atlas);
        textureBackground = new Sprite2DTexture("textures/bg.jpg");
        bg= new Background(new TextureRegion(textureBackground));
        bgBottomLine = new Sprite(atlas.findRegion("bg"));
        logoHeader =  new Sprite(atlas.findRegion("kdilla"));
        logoTxt= new Sprite(atlas.findRegion("weapon"));
        checker= new SignChecker(atlas);

      //  btnNewGame.setHeightProportion(BUTTON_HEIGHT);
        btnNewGame=new ScaledSignedButton(atlas.findRegion("newGame"), this, atlas, BUTTON_HEIGHT, checker);
        btnOptions =new ScaledSignedButton(atlas.findRegion("options"), this, atlas,BUTTON_HEIGHT*1.25f, checker);
        btnCredits=new ScaledSignedButton(atlas.findRegion("credits"), this, atlas,BUTTON_HEIGHT, checker);
        btnExit=new ScaledSignedButton(atlas.findRegion("exit"), this, atlas,BUTTON_HEIGHT, checker);
      //  btnExit.setHeightProportion(BUTTON_HEIGHT);


    }

    @Override
    protected void resize(Rect worldBounds) {
        bg.resize(worldBounds);
        bg.setTop(worldBounds.getTop());

        bgBottomLine.setWidth(worldBounds.getWidth());
        bgBottomLine.setHeight(bg.getBottom()-worldBounds.getBottom());
        bgBottomLine.setBottom(worldBounds.getBottom());

        // левая панель экрана составляет 46% от общего экрана
        logoHeader.setWidthProportion((worldBounds.getHalfWidth()-worldBounds.getHalfWidth()*0.04f)*0.65f);
        logoHeader.setLeft(worldBounds.getLeft()+(((worldBounds.getHalfWidth()-worldBounds.getHalfWidth()*0.04f)-logoHeader.getWidth())/2));
        logoHeader.setTop(worldBounds.getTop()-LOGO_TOP_MARGIN);

        logoTxt.set(logoHeader);
        logoTxt.setWidthProportion(logoHeader.getWidth());
        logoTxt.setTop(logoHeader.getBottom()-VERTICAL_MARGIN);
        //logoTxt.setLeft(worldBounds.getLeft()+LOGO_LEFT_MARGIN);
        menuCharacter.resize(worldBounds);


        btnNewGame.set(logoTxt, VERTICAL_MARGIN*3);
        btnOptions.set(btnNewGame, VERTICAL_MARGIN );
        btnCredits.set(btnOptions, VERTICAL_MARGIN );
        btnExit.set(btnCredits, VERTICAL_MARGIN );
      //  btnNewGame.resize(worldBounds);



    }

    @Override
    public void render(float delta) {
        update(delta);
        draw();
    }
    private void update(float deltaTime) {
        menuCharacter.update(deltaTime);
        btnNewGame.update(deltaTime);
        btnExit.update(deltaTime);
        btnOptions.update(deltaTime);
        btnCredits.update(deltaTime);
    }
    private void draw() {
        Gdx.gl.glClearColor(0.7f, 0.7f, 0.7f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

            bg.draw(batch);
            bgBottomLine.draw(batch);
            menuCharacter.draw(batch);
            logoHeader.draw(batch);
            logoTxt.draw(batch);

            btnNewGame.draw(batch);
            btnCredits.draw(batch);
            btnExit.draw(batch);
            btnOptions.draw(batch);

        font.draw(batch, "H", worldBounds.getLeft(), worldBounds.getTop());

            checker.draw(batch);

        batch.end();
    }

    @Override
    public void dispose() {
        textureBackground.dispose();
        atlas.dispose();
       font.dispose();
    }

    @Override
    protected void touchDown(Vector2 touch, int pointer) {

        btnExit.touchDown(touch, pointer);
        btnNewGame.touchDown(touch, pointer);
        btnOptions.touchDown(touch, pointer);
        btnCredits.touchDown(touch,pointer);
       // checker.set(btnExit, 0.025f,0.025f);
    }

    @Override
    protected void touchUp(Vector2 touch, int pointer) {

        btnExit.touchUp(touch,pointer);
        btnNewGame.touchUp(touch, pointer);
        btnOptions.touchUp(touch, pointer);
        btnCredits.touchUp(touch,pointer);
    }


    @Override
    public void actionPerformed(Object src) {
        if(src == btnExit) {
            Gdx.app.exit();
        } else if(src == btnNewGame) {
            game.setScreen(new GameScreenLevel1(game));
//        } else {
//            throw new RuntimeException("Unknown src = " + src);
        }
    }
}
