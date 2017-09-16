package com.kadilo.game.common;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kadilo.game.engine.math.Rect;
import com.kadilo.game.engine.sprites.Sprite;

/**
 * Created by avetc on 13.09.2017.
 */

public class Background extends Sprite{

    public Background(TextureRegion region) {
        super(region);
    }

    @Override
    public void resize(Rect worldBounds) {
        setWidthProportion(worldBounds.getWidth());
       // setWidthProportion(worldBounds.getWidth());
        pos.set(worldBounds.pos);
    }
}
