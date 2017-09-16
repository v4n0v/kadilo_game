package com.kadilo.game.engine.ui;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.kadilo.game.engine.math.Rect;
import com.kadilo.game.engine.sprites.Sprite;

/**
 * Created by avetc on 13.09.2017.
 */

public class ScaledSignedButton extends Sprite {

    float singSize=0.025f;
    private int pointer;
    private boolean pressed;
    private final ActionListener listener;
    float height;
    private SignChecker checker;

    public ScaledSignedButton(TextureRegion region, ActionListener listener, TextureAtlas atlas, float height, SignChecker checker) {
            super(region);
            this.listener = listener;
            this.height=height;
        this. checker = checker;
        }

        @Override
        public boolean touchDown(Vector2 touch, int pointer) {
            if(pressed || !isMe(touch)) return false;
            this.pointer = pointer;
            scale=getScale()*0.9f;

            checker.set(this, 0.025f,0.025f);
            pressed = true;
            return true;
        }


    public void set(Sprite orientir, float margin){
        pos.set(orientir.pos);
        setHeightProportion(height);
        setTop(orientir.getBottom()-margin);
    }

    @Override
        public boolean touchUp(Vector2 touch, int pointer) {
            if(this.pointer != pointer || !pressed) return false;
            pressed = false;
            if(isMe(touch)) {
                listener.actionPerformed(this);
                checker.hide();
                scale = 1f;
                return true;
            }
            return false;
        }


}
