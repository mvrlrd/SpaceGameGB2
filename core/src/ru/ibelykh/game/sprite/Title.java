package ru.ibelykh.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import ru.ibelykh.game.base.Sprite;
import ru.ibelykh.game.math.Rect;


public class Title extends Sprite {

    private Rect worldBounds;


    public Title(TextureAtlas atlas) {
        super(atlas.findRegion("gameOver"));
        setHeightProportion(0.4f);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        this.worldBounds = worldBounds;
        float posX = 0f;
        float posY = 0f;
        pos.set(posX,posY);
    }


}
