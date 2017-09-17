package com.kadilo.game.common;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kadilo.game.engine.math.Rect;
import com.kadilo.game.engine.sprites.Sprite;

/**
 * Created by avetc on 16.09.2017.
 */

public class HUD {
    public Sprite panel;
    private TextureAtlas atlas;
    private int hp;
    private Sprite hpSprite;
    private int score;
    private int level;
    TextureRegion region;
    public HUD(TextureAtlas atlas){
        region=new TextureRegion(atlas.findRegion("panel"));
        panel=new Sprite(region);
    }

    public void resize(Rect worldBounds){
        panel.setWidth(worldBounds.getWidth());
        panel.setHeight(worldBounds.getHalfHeight()/3);
        panel.setBottom(worldBounds.getBottom());
    }

    public void draw(SpriteBatch batch){
        panel.draw(batch);
    }
}
