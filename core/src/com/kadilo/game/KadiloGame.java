package com.kadilo.game;

import com.badlogic.gdx.Game;
import com.kadilo.game.screens.game.GameScreenLevel1;
import com.kadilo.game.screens.menu.MenuScreen;
import com.kadilo.game.screens.tmp.TmpScreen;

/**
 * Created by avetc on 11.09.2017.
 */

public class KadiloGame extends Game{
    @Override
    public void create() {
      setScreen(new MenuScreen(this));
      //  setScreen(new GameScreenLevel1(this));

    }
}
