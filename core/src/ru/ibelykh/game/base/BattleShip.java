package ru.ibelykh.game.base;


import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.ibelykh.game.math.Rect;
import ru.ibelykh.game.pool.BulletPool;
import ru.ibelykh.game.sprite.Bullet;

public class BattleShip extends Sprite {

    protected Vector2 v=new Vector2();

    protected BulletPool bulletPool;
    protected TextureRegion bulletRegion;
    protected  Vector2 bulletV = new Vector2();
    protected float bulletHeight;
    protected int bulletDamage;
    protected Rect worldBounds;
    protected int hp;
    protected float reloadInterval;
    protected float reloadTimer;
    protected Sound shootSound;


    public BattleShip(TextureRegion region, int rows, int cols, int frames, Sound shootSound) {
        super(region, rows, cols, frames);
        this.shootSound=shootSound;
    }

    public BattleShip() {
        super();
    }

    public void shoot(){
        Bullet bullet = bulletPool.obtain();
        bullet.set(this,bulletRegion,pos,bulletV,bulletHeight,worldBounds,bulletDamage);
        shootSound.play(0.1f);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        this.worldBounds=worldBounds;
    }
}
