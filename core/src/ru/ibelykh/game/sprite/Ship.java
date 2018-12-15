package ru.ibelykh.game.sprite;

import com.badlogic.gdx.Input;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import ru.ibelykh.game.base.BattleShip;
import ru.ibelykh.game.math.Rect;
import ru.ibelykh.game.pool.BulletPool;
import ru.ibelykh.game.pool.ExplosionPool;

public class Ship extends BattleShip {

    private Vector2 v0 = new Vector2(0.5f, 0);
    private boolean pressedLeft;
    private boolean pressedRight;
    private static final int INVALID_POINTER = -1;
    private int leftPointer = INVALID_POINTER;
    private int rightPointer = INVALID_POINTER;


    public Ship(TextureAtlas atlas, BulletPool bulletPool, ExplosionPool explosionPool,Rect worldBounds,  Sound sound) {
        super(atlas.findRegion("ship"),1,1,1, sound);
        setHeightProportion(0.15f);
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        this.reloadInterval = 0.25f;
        this.bulletRegion = atlas.findRegion("kaktus");
        this.bulletHeight=0.05f;
        this.bulletV.set(0,0.5f);
        this.bulletDamage = 1;
        this.worldBounds = worldBounds;
        setToNewGame();


    }
    public void setToNewGame(){
        stop();
        pos.x = worldBounds.pos.x;
        this.hp = 7; //кол-во жизней

        setDestroyed(false);
    }

    @Override
    public void resize(Rect worldBounds) {
super.resize(worldBounds);
       setBottom(worldBounds.getBottom()+0.01f);
    }

    public boolean keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.W:
                shoot();
                break;
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
         super.touchDown(touch, pointer);
        if (touch.x<worldBounds.pos.x){
            if(leftPointer!=INVALID_POINTER)return  false;
            leftPointer = pointer;
            moveLeft();
        }
        if (touch.x>worldBounds.pos.x){
            if(rightPointer!=INVALID_POINTER)return  false;
            rightPointer = pointer;
            moveRight();
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {

          super.touchUp(touch, pointer);
          if (pointer==leftPointer) {
              leftPointer = INVALID_POINTER;
              if (rightPointer != INVALID_POINTER) {
                  moveRight();
              } else {
                  stop();
              }
          }
          else if(pointer==rightPointer){
           rightPointer = INVALID_POINTER;
           if (leftPointer!=INVALID_POINTER){
               moveLeft();
           }else {
               stop();
           }
        }
          return false;
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

    public boolean isBulletCollision(Rect bullet){
        return !(bullet.getRight() < getLeft()
                || bullet.getLeft() > getRight()
                || bullet.getBottom() > pos.y
                || bullet.getTop() < getBottom());
    }



        @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v, delta);
        reloadTimer+=delta;
        if (reloadTimer>=reloadInterval){
            reloadTimer=-0.05f;
            shoot();
        }
        checkAndHandleBounds();
    }

        private void checkAndHandleBounds() {
        if (getLeft() < worldBounds.getLeft()) {
            setLeft(worldBounds.getLeft());
            stop();
        }
        if (getRight() > worldBounds.getRight()) {
            setRight(worldBounds.getRight());
            stop();
        }
    }


    }
