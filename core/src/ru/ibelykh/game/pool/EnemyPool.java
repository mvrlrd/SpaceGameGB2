package ru.ibelykh.game.pool;


import ru.ibelykh.game.base.SpritesPool;
import ru.ibelykh.game.math.Rect;
import ru.ibelykh.game.sprite.Enemy;
import ru.ibelykh.game.sprite.Ship;

public class EnemyPool extends SpritesPool<Enemy> {

private BulletPool bulletPool;
private Ship ship;
private Rect worldBounds;
private ExplosionPool explosionPool;


    public EnemyPool(BulletPool bulletPool,ExplosionPool explosionPool, Ship ship,Rect worldBounds) {
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        this.ship = ship;
        this.worldBounds=worldBounds;

    }

    @Override
    protected Enemy newObject() {
        return new Enemy(bulletPool,explosionPool,ship,worldBounds);
    }
}
