package com.kadilo.game.engine.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kadilo.game.engine.sprites.Sprite;

/**
 * Created by avetc on 14.09.2017.
 */

public class SignChecker{
    TextureRegion region;
   // ScaledSignedButton button;
    public Sprite leftChecker;
    public Sprite rightChecker;

    public SignChecker(TextureAtlas atlas) {
        //super(atlas.findRegion("checked"));
      //  this.button=button;
        region=new TextureRegion(atlas.findRegion("checked"));
        leftChecker=new Sprite(region);
        rightChecker=new Sprite(region);
    }
    public void hide(){
        leftChecker.setHeightProportion(0f);
        rightChecker.setHeightProportion(0f);
    }

    public void set(ScaledSignedButton button, float height, float margin){
        //this.margin=margin;
        leftChecker.setHeightProportion(height);
        leftChecker.setTop(button.getTop()-button.getHalfHeight()+leftChecker.getHalfHeight());
        leftChecker.setRight(button.getLeft()-margin);

        rightChecker.set(leftChecker);
        rightChecker.setLeft(button.getRight()+margin);

    }

    public void draw(SpriteBatch batch){
        leftChecker.draw(batch);
        rightChecker.draw(batch);
    }

}
