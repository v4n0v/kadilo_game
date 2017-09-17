package com.kadilo.game.common;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.kadilo.game.engine.math.Rect;
import com.kadilo.game.engine.sprites.Sprite;
import com.kadilo.game.engine.utils.Animation;
import com.kadilo.game.engine.utils.Timer;

/**
 * Created by avetc on 15.09.2017.
 */

public class Character extends Sprite {

    protected enum Position {LEFT, RIGHT}

    ;

    protected enum State {STOP, WALKING, TURNING, FIGHTING}

    ;
    protected boolean isFlipped;

    protected State state;
    protected Position position;

    protected final Vector2 v = new Vector2();
    protected Rect worldBounds;
    protected int hp;

    private float stepInterval = 0.1f;


    private int[] stepFrames = {7, 8, 9};
    private boolean[] isFlippedStepFrame = {false, false, false};

    private int[] turnFrames = {1, 2, 3};
    private int[] turnFrames1 = {1, 2, 3};
    private int[] turnFrames2 = {4, 5, 6};
    private boolean[] isFlippedTurnFrame = {false, false, false};

    protected int frameCnt = 0;

    private float turnInterval = 0.1f;


    public Character(TextureRegion region, int cols, int rows, int frames, Rect worldBounds) {
        super(region, cols, rows, frames);
        this.worldBounds = worldBounds;

    }

    @Override
    public void update(float deltaTime) {

        pos.mulAdd(v, deltaTime);
    }

    public void walk(float deltaTime) {

        if (Timer.interval(stepInterval, deltaTime)) {
            frame = stepFrames[frameCnt];
            flipFrame(position, frameCnt, isFlippedStepFrame);
            frameCnt++;
            if (frameCnt == stepFrames.length) {
                frameCnt = 0;

            }

        }
    }

    public void turn(float deltaTime) {

        boolean isTurned = false;
        float type = (float) Math.random();

        if (type < 0.65f) turnFrames = turnFrames1;
        else turnFrames = turnFrames2;
        frameCnt = 0;
        while (!isTurned) {
            if (Timer.interval(turnInterval, deltaTime)) {

                frame = turnFrames[frameCnt];
                System.out.println(frame);
                flipFrame(position, frameCnt, isFlippedTurnFrame);
                frameCnt++;

                if (frameCnt >= turnFrames.length) {

                    frameCnt = 0;
                    for (int i = 0; i < isFlippedStepFrame.length; i++) {
                        isFlippedTurnFrame[i] = false;
                    }
                    state = State.WALKING;
                    isTurned = true;
                }
            }
        }
    }

    public void walkLeft(float deltaTime) {


    }

    private void flipFrames() {

    }

    public void stay() {
        state = State.STOP;
        frame = 0;
        if (position == Position.LEFT) {
            if (!isFlipped) {
                flipHorizontal();
                isFlipped = true;
            }
        }
        if (position == Position.RIGHT) {
            if (isFlipped) {
                flipHorizontal();
                isFlipped = false;
            }
        }

    }

    Vector2 getV() {
        return v;
    }

    public void flipFrame(Position position, int frameCnt, boolean[] condition) {
        if (position == Position.LEFT) {

            if (!condition[frameCnt]) {
                flipHorizontal();
                condition[frameCnt] = true;
            }

        }
        if (position == Position.RIGHT) {

            if (condition[frameCnt]) {
                flipHorizontal();
                condition[frameCnt] = false;
            }

        }
    }


    @Override
    public void resize(Rect worldBounds) {
        //   this.worldBounds=worldBounds;
    }
}
