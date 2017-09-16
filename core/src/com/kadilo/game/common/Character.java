package com.kadilo.game.common;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.kadilo.game.engine.math.Rect;
import com.kadilo.game.engine.sprites.Sprite;

/**
 * Created by avetc on 15.09.2017.
 */

public class Character extends Sprite {

    private enum Position {LEFT, RIGHT};
    private Position position;

    protected final Vector2 v = new Vector2();
    protected final Rect worldBounds;
    protected TextureRegion bulletRegion;
    protected int hp;



    public Character(TextureRegion region, int rows, int cols, int frames, Rect worlbounds){
        this.worldBounds=worlbounds;

    }

    @Override
    public void update(float deltaTime) {
        pos.mulAdd(v, deltaTime);
    }


}
