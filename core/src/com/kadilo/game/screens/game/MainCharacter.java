package com.kadilo.game.screens.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.kadilo.game.common.Character;
import com.kadilo.game.common.HUD;
import com.kadilo.game.engine.math.Rect;

/**
 * Created by avetc on 16.09.2017.
 */

public class MainCharacter extends Character{

    private HUD hud;
    private TextureRegion[] rotateRegions;
    private TextureRegion[] runRegions;
    private final Vector2 v0 = new Vector2(0.7f, 0f);


    public MainCharacter(TextureAtlas atlas, int cols, int rows, int frames, HUD hud, Rect worldBounds) {
        super(atlas.findRegion("popFull"), cols , rows, frames, worldBounds);
//        this.runRegions= Regions.split(runRegion, 1 , 4, 4);
//        this.rotateRegions= Regions.split(rotateRegion, 1 , 8, 8);
        this.hud=hud;
        setHeightProportion(0.35f);
        position=Position.RIGHT;
        state=State.STOP;
        stay();
    }

    @Override
    public void resize(Rect worldBounds) {

        setBottom(worldBounds.getBottom()+hud.panel.getHeight());
    }

    private static final int INVALID_POINTER = -1;
    private int leftPointer = INVALID_POINTER;
    private int rightPointer = INVALID_POINTER;

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if(touch.x < worldBounds.pos.x) {
            if(leftPointer != INVALID_POINTER) return false;
            leftPointer = pointer;
            moveLeft();
        } else {
            if(rightPointer != INVALID_POINTER) return false;
            rightPointer = pointer;
            moveRight();
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        if(pointer == leftPointer) {
            leftPointer = INVALID_POINTER;
            if(rightPointer != INVALID_POINTER) moveRight(); else stop();
        } else if(pointer == rightPointer) {
            rightPointer = INVALID_POINTER;
            if(leftPointer != INVALID_POINTER) moveLeft(); else stop();
        }
        return false;
    }

    private boolean pressedLeft;
    private boolean pressedRight;

    void keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = true;
                moveLeft();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = true;
                moveRight();
                break;
            case Input.Keys.UP:
                frame = 0;

                //shoot();
                break;
        }
    }

    void keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = false;
                if(pressedRight) {
                    //flipHorizontal();
                    moveRight();
                }
                else {
                    stop();
                 //   state=State.STOP;
                }
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = false;
                if(pressedLeft){

                    moveLeft();
                 //   flipHorizontal();
                }else {
                    stop();
                //    state=State.STOP;
                }
                break;
            case Input.Keys.UP:

                break;
        }
    }

    private void moveRight() {
        System.out.println("moveR");

        if (position!=Position.RIGHT) {
            state=State.TURNING;
            position = Position.RIGHT;
        } else state=State.WALKING;

//        if (state==State.WALKING)
           v.set(v0);
    }

    private void moveLeft() {
        System.out.println("moveL");

        if (position!=Position.LEFT) {
            state=State.TURNING;
            position = Position.LEFT;
        } else state=State.WALKING;

//        if (state==State.WALKING)
            v.set(v0).rotate(180);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (state==State.WALKING){
            walk(deltaTime);
        } else if (state==State.STOP){
            stay();
        } else if (state==State.TURNING){
            turn(deltaTime);

        } else  {
            throw new RuntimeException("Unckown character State = " + state);
        }
/*        if(getRight() > worldBounds.getRight()) {
            setRight(worldBounds.getRight());
            stop();
        }
        if(getLeft() < worldBounds.getLeft()) {
            setLeft(worldBounds.getLeft());
            stop();
        }*/
    }

    private void stop() {
        v.setZero();
        stay();
    }
}
