package ru.ibelykh.game.sprite;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.ibelykh.game.base.Sprite;
import ru.ibelykh.game.math.Rect;
import ru.ibelykh.game.math.Rnd;
import ru.ibelykh.game.pool.BulletPool;

public class Ship extends Sprite {

    private Vector2 v0 = new Vector2(0.5f, 0);
    private Vector2 v=new Vector2();
    private Rect worldBounds;
    private boolean pressedLeft;
    private boolean pressedRight;

    private BulletPool bulletPool;
    private TextureAtlas atlas;



    public Ship(TextureRegion region, BulletPool bulletPool, TextureAtlas atlas) {
        super(region);
        setHeightProportion(0.13f);
        this.bulletPool = bulletPool;
        this.atlas = atlas;
    }

    @Override
    public void resize(Rect worldBounds) {
this.worldBounds=worldBounds;
       setBottom(worldBounds.getBottom()+0.01f);
    }



    public boolean keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = true;
                moveLeft();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = true;
                moveRight();
                break;
            case Input.Keys.W:
               shoot();
            break;
        }
        return false;
    }


    public boolean keyUp(int keycode) {
        switch (keycode){
            case Input.Keys.A:
            case Input.Keys.LEFT:
pressedLeft =false;
if (pressedRight){
    moveRight();
}
else {
    stop();
}
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight =false;
                if (pressedLeft){
                    moveLeft();
                }
                else {
                    stop();
                }
                break;

        }
        return false;
    }



    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        return super.touchUp(touch, pointer);
    }

       private void moveRight(){
        v.set(v0);
    }

    private void moveLeft(){
        v.set(v0).rotate(180);
    }

    private void stop(){
        v.setZero();
    }



        @Override
    public void update(float delta) {
        super.update(delta);

        pos.mulAdd(v, delta);
//        checkAndHandleBounds();

    }

    public void shoot(){
        Bullet bullet = bulletPool.obtain();
        bullet.set(this,atlas.findRegion("bullet2"),pos,new Vector2(0,0.5f),0.02f,worldBounds,1);

    }

    //    private void checkAndHandleBounds() {
//        if (getRight() < worldBounds.getLeft()) setLeft(worldBounds.getRight());
//        if (getRight() > worldBounds.getRight()) setRight(worldBounds.getLeft());
//        if (getTop() < worldBounds.getBottom()) setBottom(worldBounds.getTop());
////        if (getTop() > worldBounds.getTop()) setTop(worldBounds.getBottom());
//
//
//    }
    }
