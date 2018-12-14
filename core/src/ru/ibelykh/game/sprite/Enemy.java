package ru.ibelykh.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.ibelykh.game.base.BattleShip;
import ru.ibelykh.game.math.Rect;
import ru.ibelykh.game.pool.BulletPool;
import ru.ibelykh.game.pool.ExplosionPool;

public class Enemy extends BattleShip {

    private  Ship ship;  //ссылка на корабл игрока
    private enum  State {DESCENT,FIGHT}  //два состояния корабля
    private  State state;
    private Vector2 v0=new Vector2();
    private  Vector2 descentV = new Vector2(0,-0.15f); //чтобы корабль быстро появился на экране



    public Enemy(BulletPool bulletPool, ExplosionPool explosionPool, Ship ship, Rect worldBounds) {
       this.bulletPool = bulletPool;
       this.ship=ship;
       this.v.set(v0);
       this.worldBounds = worldBounds;
       this.explosionPool = explosionPool;

    }



    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v,delta);
//враги начинают стрелять когда выползут на экран на половину своей высоты
        switch (state){
            case DESCENT:
                if (getTop() <= worldBounds.getTop()+getHalfHeight()){
                    v.set(v0);
                    state = State.FIGHT;
                }
                break;
            case FIGHT:
                reloadTimer += delta;
                if (reloadTimer >= reloadInterval){
                    reloadTimer = 0f;
                    shoot();
                }
                if (getBottom()<=worldBounds.getBottom()){
                    this.setDestroyed(true);
                    boom();
                }

        }
    }


    public void set(
            TextureRegion[]regions,
            Vector2 v0,
            TextureRegion bulletRegion,
            float bulletHeight,
            float bulletVY,
            int bulletDamage,
            float reloadInterval,
            float height,
            int hp
    ){
        this.regions = regions;
        this.v0.set(v0);
        this.bulletRegion = bulletRegion;
        this.bulletHeight = bulletHeight;
        this.bulletV.set(0f,bulletVY);
        this.bulletDamage = bulletDamage;
        this.reloadInterval = reloadInterval;
        this.hp = hp;
        setHeightProportion(height);
        reloadTimer = reloadInterval;
        v.set(descentV);
        state = State.DESCENT;
    }

    public boolean isBulletCollision(Rect bullet){
        return !(bullet.getRight() < getLeft()
                || bullet.getLeft() > getRight()
                || bullet.getBottom() > getTop()
                || bullet.getTop() <pos.y);
    }
}
