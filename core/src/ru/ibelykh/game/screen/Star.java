package ru.ibelykh.game.screen;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.ibelykh.game.base.Sprite;
import ru.ibelykh.game.math.Rect;
import ru.ibelykh.game.math.Rnd;

public class Star extends Sprite {

    private Rect worldBounds;
    private Vector2 v = new Vector2();

    public Star(TextureAtlas atlas) {
        super(atlas.findRegion("star"));
        setHeightProportion(0.01f);
        v.set(Rnd.nextFloat(-0.005f,0.005f),Rnd.nextFloat(-0.5f,-0.1f));
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        this.worldBounds = worldBounds;
        float posX = Rnd.nextFloat(worldBounds.getLeft(), worldBounds.getRight());
        float posY = Rnd.nextFloat(worldBounds.getBottom(), worldBounds.getTop());
        pos.set(posX,posY);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v,delta);
        checkAndHandleBounds();
    }
    private void checkAndHandleBounds(){
        if (getRight() < worldBounds.getLeft()) setLeft(worldBounds.getRight());
        if (getRight() > worldBounds.getRight()) setRight(worldBounds.getLeft());
        if (getTop() < worldBounds.getBottom()) setBottom(worldBounds.getTop());
        if (getTop() > worldBounds.getTop()) setTop(worldBounds.getBottom());


    }
}
