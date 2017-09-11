package com.kadilo.game.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by avetc on 11.09.2017.
 */

public class Sprite2DTexture extends Texture {

    public Sprite2DTexture(String internalPath) {
        this(Gdx.files.internal(internalPath));
    }

    public Sprite2DTexture(FileHandle file) {
        super(file, true);
        setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear);
    }
}