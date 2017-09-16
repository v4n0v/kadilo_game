package com.kadilo.game.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Created by avetc on 14.09.2017.
 */

public class Font extends BitmapFont {
    public Font(String fontFile, String imageFile) {
        super(Gdx.files.internal(fontFile),Gdx.files.internal(imageFile), false, false);
        getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    public void setWorldSize(float worldSize){
        getData().setScale(worldSize/ getCapHeight());
    }
}
