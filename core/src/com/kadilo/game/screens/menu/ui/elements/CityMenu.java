package com.kadilo.game.screens.menu.ui.elements;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kadilo.game.engine.math.Rect;
import com.kadilo.game.engine.sprites.Sprite;

/**
 * Created by avetc on 13.09.2017.
 */

public class CityMenu extends Sprite {

    public CityMenu(TextureRegion region) {
        super(region);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight());
        pos.set(worldBounds.pos);
        setRight(worldBounds.getRight());
    }
}
