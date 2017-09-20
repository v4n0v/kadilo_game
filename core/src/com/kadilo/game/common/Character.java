package com.kadilo.game.common;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.kadilo.game.engine.math.Rect;
import com.kadilo.game.engine.sprites.Sprite;
import com.kadilo.game.engine.utils.Timer;

import java.util.Arrays;

/**
 * Created by avetc on 15.09.2017.
 */

public class Character extends Sprite {

    protected enum Position {LEFT, RIGHT}

    protected enum State {STOP, WALKING, TURNING, FIGHTING}

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
    private int[] turnFrames1 = {1, 2, 3 };
    private int[] turnFrames2 = {4, 5, 6};
    private boolean[] isFlippedTurnFrame = {false, false, false};



    protected int frameCnt = 0;
    protected int frameTurnCnt = 0;

    private float turnInterval = 0.025f;

    public Character(TextureRegion region, int cols, int rows, int frames, Rect worldBounds) {
        super(region, cols, rows, frames);
        this.worldBounds = worldBounds;

    }

    @Override
    public void update(float deltaTime) {

        pos.mulAdd(v, deltaTime);
    }


    // анимация ходьбы персонажа
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

    // совершаем поворот персонажа в противоположную сторону
    public void turn(float deltaTime) {

        float type = (float) Math.random();

        // на случай если счетчик перескочил длину массива, его надо бнулить
        if (frameTurnCnt >= turnFrames.length) {
            System.out.println("frameCnt reset0");
            frameCnt = 0;
        }

        // если счетчик обнулем, выбираем напрвление поворота
        if (frameTurnCnt == 0) {
            if (type < 0.65f) {
                turnFrames = turnFrames1;
            } else turnFrames = turnFrames2;
        }

        // делаем смену кадра из turnFrames в единицу времени turnInterval
        if (Timer.interval(turnInterval, deltaTime)) {

            // если поворот налево, тогда кадры считаются справа налево
            if (position==Position.LEFT)
               frame= turnFrames[turnFrames.length-1-frameTurnCnt];
            else frame = turnFrames[frameTurnCnt];

            System.out.println("frame=" + frame + ", frameCnt=" + frameTurnCnt + ", []frames=" + Arrays.toString(turnFrames ));

            frameTurnCnt++;

            // при достижениее поледнего кадра меняем состояние на WALKING
            // и завершаем работу метода

            if (frameTurnCnt == turnFrames.length) {
                System.out.println("frameCnt reset");
                frameTurnCnt = 0;
                state = State.WALKING;
                return;

            }

        }
    }

    // метод меняющий состояние на остановку (STOP) и переводим кадр 0
    // если позиция LEFT, зеркально перерворачиваем спрайт
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

    // возвращаем скорость
    Vector2 getV() {
        return v;
    }

    // переворачиваем спрайт анимации, взависимости от позиции
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


}
