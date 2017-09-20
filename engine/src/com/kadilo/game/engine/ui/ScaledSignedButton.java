package com.kadilo.game.engine.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    float alpha;

    SpriteBatch batch;
    public ScaledSignedButton(TextureRegion region, ActionListener listener, TextureAtlas atlas, float height, SignChecker checker) {
            super(region, 2, 1, 2);
            this.listener = listener;
            this.height=height;
             this. checker = checker;
        frame=1;
        }

        @Override
        public boolean touchDown(Vector2 touch, int pointer) {
            if(pressed || !isMe(touch)) return false;
            this.pointer = pointer;
            scale=getScale()*0.9f;
            frame=0;
            checker.set(this, 0.025f,0.025f);
            pressed = true;
            return true;
        }


    public void set(Sprite orientir, float margin){
        alpha=0;
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
                frame=0;
                return true;
            }
            return false;
        }

//    @Override
//    public void draw(SpriteBatch batch) {
//
//        batch.begin();
//        batch.setColor(1, 1, 1, alpha);
//        batch.end();
//    }
}
