package ru.ibelykh.game.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.ibelykh.game.base.ScaledButton;
import ru.ibelykh.game.math.Rect;

public  class ButtonExit extends ScaledButton {



    public ButtonExit(TextureAtlas atlas) {
        super(atlas.findRegion("btexit"));
        setHeightProportion(0.2f);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setBottom(worldBounds.getBottom()+0.07f);
        setLeft(worldBounds.getLeft()+0.07f);
    }

    @Override
    public void actionPerformed() {
        Gdx.app.exit();
    }
}
