package ru.ibelykh.game.sprite;


import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import ru.ibelykh.game.base.Sprite;
import ru.ibelykh.game.math.Rect;

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
