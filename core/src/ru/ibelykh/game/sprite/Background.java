package ru.ibelykh.game.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.ibelykh.game.base.Sprite;
import ru.ibelykh.game.math.Rect;
import ru.ibelykh.game.math.Rnd;

public class Background extends Sprite {

    public Background(TextureAtlas atlas,String nameOfPic) {
        super(atlas.findRegion(nameOfPic));
    }

    @Override
    public void resize(Rect worldBounds) {
       setHeightProportion(worldBounds.getHeight());
       pos.set(worldBounds.pos);
    }
}
