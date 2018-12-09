package ru.ibelykh.game.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.ibelykh.game.base.Sprite;
import ru.ibelykh.game.math.Rect;
import ru.ibelykh.game.math.Rnd;

public class Ship extends Sprite {


//    private Vector2 v=new Vector2(-0.1f,0f);
    private Rect worldBounds;

    public Ship(TextureRegion region) {
        super(region);
    }
    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.13f);
        pos.set(-0.00f,-0.44f);
    }

//    @Override
//    public void update(float delta) {
//        super.update(delta);
//
//        pos.mulAdd(v, delta);
//        checkAndHandleBounds();
//
//    }


//
//    private void checkAndHandleBounds() {
//        if (getRight() < worldBounds.getLeft()) setLeft(worldBounds.getRight());
//        if (getRight() > worldBounds.getRight()) setRight(worldBounds.getLeft());
//        if (getTop() < worldBounds.getBottom()) setBottom(worldBounds.getTop());
////        if (getTop() > worldBounds.getTop()) setTop(worldBounds.getBottom());




    }
