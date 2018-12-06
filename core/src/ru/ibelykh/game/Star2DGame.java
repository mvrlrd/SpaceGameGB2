package ru.ibelykh.game;

import com.badlogic.gdx.Game;

import ru.ibelykh.game.screen.MenuScreen;

public class Star2DGame extends Game {
    @Override
    public void create() {
setScreen(new MenuScreen());
    }
}
