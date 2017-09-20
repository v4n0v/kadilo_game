package com.kadilo.game.screens.menu.ui.buttons;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.kadilo.game.engine.ui.*;
import com.kadilo.game.engine.ui.SignChecker;

/**
 * Created by avetc on 13.09.2017.
 */

public class ButtonNewGame extends ScaledSignedButton {
   // private Sprite orientir;
    private float height;
    public ButtonNewGame(com.badlogic.gdx.graphics.g2d.Sprite region, ActionListener listener, TextureAtlas atlas, float height, SignChecker checker) {
        super(region, listener, atlas, height, checker );

        this.height=height;
    }


    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        System.out.println("new game touch");

        return super.touchDown(touch, pointer);
    }
}
