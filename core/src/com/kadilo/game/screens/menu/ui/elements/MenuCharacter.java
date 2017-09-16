package com.kadilo.game.screens.menu.ui.elements;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kadilo.game.engine.math.Rect;
import com.kadilo.game.engine.sprites.Sprite;
import com.kadilo.game.engine.utils.Regions;

/**
 * Created by avetc on 13.09.2017.
 */

public class MenuCharacter extends Sprite {

    private int state;
    private int lastState;
    private int[] EYES_STATE = new int[]{0, 1, 2};
    private float type;
    private float lastType;
    //  private final TextureRegion[] regions;
    private final float winkInterval = 2f;
    private float winkTimer;


    public MenuCharacter(TextureAtlas atlas) {
        super(atlas.findRegion("ded"), 1, 3, 3);

        winkTimer = winkInterval;
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight());
        setRight(worldBounds.getRight());
        setBottom(worldBounds.getBottom());
    }

    public void wink() {
        lastState = state;

        do {
            state = randomize();
        }while (lastState==state);

        frame=state;

    }

    private int  randomize(){
        type = (float) Math.random();
        if (type < 0.33f) {
            state = EYES_STATE[0];
        } else if (type < 0.67f) {
            state = EYES_STATE[1];
        } else {
            state = EYES_STATE[2];
        }
        return state;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        winkTimer += deltaTime;
        if (winkTimer >= winkInterval) {
            winkTimer = 0f;
            System.out.println("wink, frame= "+frame );
            wink();
        }
    }
}
