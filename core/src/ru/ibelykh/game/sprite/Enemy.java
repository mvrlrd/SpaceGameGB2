package ru.ibelykh.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.ibelykh.game.base.BattleShip;
import ru.ibelykh.game.math.Rect;
import ru.ibelykh.game.pool.BulletPool;

public class Enemy extends BattleShip {

private  Ship ship;  //ссылка на кораблю игрока
    private Vector2 v0=new Vector2();


    public Enemy(BulletPool bulletPool, Ship ship, Rect worldBounds) {
       this.bulletPool = bulletPool;
       this.ship=ship;
       this.v.set(v0);
       this.worldBounds = worldBounds;

    }



    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v,delta);
        reloadTimer += delta;
        if (reloadTimer >= reloadInterval){
            reloadTimer = 0f;
            shoot();
        }
        if (getBottom()<=worldBounds.getBottom()){
            this.setDestroyed(true);
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
        v.set(v0);
    }
}
