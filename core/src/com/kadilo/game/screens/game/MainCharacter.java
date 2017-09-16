package com.kadilo.game.screens.game;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kadilo.game.common.Character;
import com.kadilo.game.engine.math.Rect;
import com.kadilo.game.engine.sprites.Sprite;

/**
 * Created by avetc on 16.09.2017.
 */

public class MainCharacter extends Character{

    private Sprite HUD;

    public MainCharacter(TextureAtlas atlas, int rows, int cols, int frames, Rect worlbounds, Sprite HUD) {
        super(atlas.findRegion("pop"), rows, cols, frames, worlbounds);
    }

    @Override
    public void resize(Rect worldBounds) {
        setBottom(worldBounds.getBottom()+HUD.getHeight());
    }
}
