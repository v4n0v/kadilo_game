package com.kadilo.game.engine.pool;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

import com.kadilo.game.engine.sprites.Sprite;

/**
 * Created by avetc on 11.09.2017.
 */

public abstract class SpritesPool<T extends Sprite> {

    protected final ArrayList<T> activeObjects = new ArrayList<T>();
    protected final ArrayList<T> freeObjects = new ArrayList<T>();

    protected abstract T newObject();

    public T obtain() {
        T object;
        if(freeObjects.isEmpty()) {
            object = newObject();
        } else {
            object = freeObjects.remove(freeObjects.size() - 1);
        }
        activeObjects.add(object);
        debugLog();
        return object;
    }

    private void free(T object) {
        if(!activeObjects.remove(object))
            throw new RuntimeException("Попытка удаления несуществующего object = " + object);
        freeObjects.add(object);
        debugLog();
    }

    public void updateActiveSprites(float deltaTime) {
        final int cnt = activeObjects.size();
        for (int i = 0; i < cnt; i++) {
            Sprite sprite = activeObjects.get(i);
            if(sprite.isDestroyed()) throw new RuntimeException("Update destroyed sprite");
            sprite.update(deltaTime);
        }
    }

    public ArrayList<T> getActiveObjects() {
        return activeObjects;
    }

    public void freeAllDestroyedActiveObjects() {
        for (int i = 0; i < activeObjects.size(); i++) {
            T sprite = activeObjects.get(i);
            if(sprite.isDestroyed()) {
                free(sprite);
                i--;
                sprite.flushDestroy();
            }
        }
    }
    //   меня постоянно сбивал с толку метод ресайз, по идее правильно именовать его setPosition, мы же там положение в "пространстве определем"
    public void drawActiveObjects(SpriteBatch batch) {
        final int cnt = activeObjects.size();
        for (int i = 0; i < cnt; i++) {
            Sprite sprite = activeObjects.get(i);
            if(sprite.isDestroyed()) throw new RuntimeException("Draw destroyed sprite");
            sprite.draw(batch);
        }
    }

    protected void debugLog() {
    }

    public void dispose() {
        freeObjects.clear();
        activeObjects.clear();
    }
}
