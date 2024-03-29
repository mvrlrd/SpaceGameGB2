package ru.ibelykh.game.base;


import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.ibelykh.game.math.Rect;
import ru.ibelykh.game.pool.BulletPool;
import ru.ibelykh.game.pool.ExplosionPool;
import ru.ibelykh.game.sprite.Bullet;
import ru.ibelykh.game.sprite.Explosion;

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
    protected ExplosionPool explosionPool;
    protected int dmg;


    private float damageAnimatedInterval = 0.1f;
    private float damageAnimatedTimer = damageAnimatedInterval;


    @Override
    public void update(float delta) {
        super.update(delta);
        damageAnimatedTimer += delta;
        if (damageAnimatedTimer >= damageAnimatedInterval){
            frame = 0;
        }
    }

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
//        shootSound.play(0.1f);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        this.worldBounds=worldBounds;
    }
    public void boom(){
        Explosion explosion = explosionPool.obtain();
        explosion.set(getHeight(),pos);
    }

    public void damage(int damage){
        hp -=damage;
        if (hp<=0){
            setDestroyed(true);
            boom();
        }
        damageAnimatedTimer = 0f;
//        frame = 1;

    }



    public int getHp() {
        return hp;
    }

    public int getDmg() {
        return dmg;
    }
}
