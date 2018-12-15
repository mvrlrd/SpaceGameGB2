package ru.ibelykh.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.ibelykh.game.base.ScaledButton;
import ru.ibelykh.game.base.Sprite;
import ru.ibelykh.game.screen.GameScreen;

public class StartNewGameButton extends ScaledButton {

private  GameScreen gameScreen;

    public StartNewGameButton(TextureAtlas atlas, GameScreen gameScreen) {
        super(atlas.findRegion("btplay"));
        setHeightProportion(0.3f);
        setTop(-0.15f);
        this.gameScreen = gameScreen;
    }

    @Override
    public void actionPerformed() {
gameScreen.startNewGame();
    }
}
