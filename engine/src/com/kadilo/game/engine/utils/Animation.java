package com.kadilo.game.engine.utils;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by avetc on 17.09.2017.
 */

public class Animation {
    private enum Position {LEFT, RIGHT}


    public static void animateRegiions( ){

    }

    public static void animateReigonsFlipped(){

    }

    public static void flipFrame(Position position, int frameCnt, boolean[] condition) {
        if (position == Position.LEFT) {

            if (!condition[frameCnt]) {
             //   flipHorizontal();
                condition[frameCnt] = true;
            }

        }
        if (position == Position.RIGHT) {

            if (condition[frameCnt]) {
               // flipHorizontal();
                condition[frameCnt] = false;
            }

        }
    }
}
