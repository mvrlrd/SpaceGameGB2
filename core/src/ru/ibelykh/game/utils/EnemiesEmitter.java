package ru.ibelykh.game.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.ibelykh.game.math.Rect;
import ru.ibelykh.game.math.Rnd;
import ru.ibelykh.game.pool.EnemyPool;
import ru.ibelykh.game.sprite.Enemy;

public class EnemiesEmitter {
    private static final float ENEMY_SMALL_HEIGHT = 0.05f;
    private static final float ENEMY_SMALL_BULLET_HEIGHT = 0.01f;
    private static final float ENEMY_SMALL_BULLET_VY = -0.3f;
    private static final int ENEMY_SMALL_BULLET_DAMAGE = 1;
    private static final float ENEMY_SMALL_RELOAD_INTERVAL = 2f;
    private static final int ENEMY_SMALL_HP = 1;
    private static final int ENEMY_SMALL_Damage = 3;

    private static final float ENEMY_MEDIUM_HEIGHT = 0.1f;
    private static final float ENEMY_MEDIUM_BULLET_HEIGHT = 0.01f;
    private static final float ENEMY_MEDIUM_BULLET_VY = -0.3f;
    private static final int ENEMY_MEDIUM_BULLET_DAMAGE = 5;
    private static final float ENEMY_MEDIUM_RELOAD_INTERVAL = 3f;
    private static final int ENEMY_MEDIUM_HP = 2;
    private static final int ENEMY_MEDIUM_Damage = 10;

    private static final float ENEMY_BIG_HEIGHT = 0.125f;
    private static final float ENEMY_BIG_BULLET_HEIGHT = 0.015f;
    private static final float ENEMY_BIG_BULLET_VY = -0.3f;
    private static final int ENEMY_BIG_BULLET_DAMAGE = 10;
    private static final float ENEMY_BIG_RELOAD_INTERVAL = 5f;
    private static final int ENEMY_BIG_HP = 3;
    private static final int ENEMY_BIG_Damage = 15;

    private static final float DROP_BOX_HEIGHT = 0.07f;
    private static final float DROP_BOX_BULLET_HEIGHT = 0.0f;
    private static final float DROP_BOX_BULLET_VY = -0.0f;
    private static final int DROP_BOX_BULLET_DAMAGE = 0;
    private static final float DROP_BOX_RELOAD_INTERVAL = 100f;
    private static final int DROP_BOX__HP = 5;
    private static final int DROP_BOX__Damage = -5;


    private Rect worldBounds;
    private float generateInterval = 3f;
    private float generateTimer;

    private TextureRegion[] enemySmallRegion;
    private TextureRegion[] enemyMediumRegion;
    private TextureRegion[] enemyBigRegion;
    private TextureRegion[] dropBoxRegion;


    private  final Vector2 enemySmallV = new Vector2(0f, -0.15f);
    private  final Vector2 enemyMediumV = new Vector2(0f, -0.04f);
    private  final Vector2 enemyBigV = new Vector2(0f, -0.02f);
    private  final Vector2 dropBoxV = new Vector2(0f, -0.5f);

    private int level;

    private TextureRegion bulletRegion;

    private EnemyPool enemyPool;


    public EnemiesEmitter(Rect worldBounds, EnemyPool enemyPool, TextureAtlas atlas) {
        this.worldBounds = worldBounds;
        this.enemyPool = enemyPool;

        TextureRegion textureRegion0 = atlas.findRegion("evelship");
        this.enemySmallRegion = Regions.split(textureRegion0,1,1,1); //ПОСКОЛЬКУ ДВА ВАРИАНТА ВРАГОВ

        TextureRegion textureRegion1 = atlas.findRegion("starwars");
        this.enemyMediumRegion = Regions.split(textureRegion1,1,1,1); //ПОСКОЛЬКУ ДВА ВАРИАНТА ВРАГОВ

        TextureRegion textureRegion2 = atlas.findRegion("cat");
        this.enemyBigRegion = Regions.split(textureRegion2,1,1,1); //ПОСКОЛЬКУ ДВА ВАРИАНТА ВРАГОВ

        TextureRegion textureRegion3 = atlas.findRegion("dropBox");
        this.dropBoxRegion = Regions.split(textureRegion3,1,1,1); //ПОСКОЛЬКУ ДВА ВАРИАНТА ВРАГОВ


        this.bulletRegion = atlas.findRegion("sale");
    }

    public void generate(float delta, int frags) {
        level = frags / 10+1;
if (level<=20) {
    generateInterval = 2f - ((float) level / 10) + 0.2f;
}
if ((level>20)&&(level<=40)){
    generateInterval = 2f - ((float) level / 20) + 0.2f;

}
if (level>40){
    generateInterval = 2f;
}

        generateTimer += delta;
        if (generateTimer >= generateInterval) {
            generateTimer = 0f;
            Enemy enemy = enemyPool.obtain();

            float type = (float) Math.random();
            if (type<0.6f) {
                enemy.set(
                        enemySmallRegion,
                        enemySmallV,
                        bulletRegion,
                        ENEMY_SMALL_BULLET_HEIGHT,
                        ENEMY_SMALL_BULLET_VY,
                        ENEMY_SMALL_BULLET_DAMAGE,
                        ENEMY_SMALL_RELOAD_INTERVAL,
                        ENEMY_SMALL_HEIGHT,
                        ENEMY_SMALL_HP,
                        ENEMY_SMALL_Damage

                );
            } else if ((type>=0.6f)&&(type<0.8)){
                enemy.set(
                        enemyMediumRegion,
                        enemyMediumV,
                        bulletRegion,
                        ENEMY_MEDIUM_BULLET_HEIGHT,
                        ENEMY_MEDIUM_BULLET_VY,
                        ENEMY_MEDIUM_BULLET_DAMAGE,
                        ENEMY_MEDIUM_RELOAD_INTERVAL,
                        ENEMY_MEDIUM_HEIGHT,
                        ENEMY_MEDIUM_HP,
                        ENEMY_MEDIUM_Damage

                );
            } else if ((type>=0.8f)&&(type<0.9)){
                enemy.set(
                        enemyBigRegion,
                        enemyBigV,
                        bulletRegion,
                        ENEMY_BIG_BULLET_HEIGHT,
                        ENEMY_BIG_BULLET_VY,
                        ENEMY_BIG_BULLET_DAMAGE,
                        ENEMY_BIG_RELOAD_INTERVAL,
                        ENEMY_BIG_HEIGHT,
                        ENEMY_BIG_HP,
                        ENEMY_BIG_Damage

                );
            }else {
                enemy.set(
                        dropBoxRegion,
                        dropBoxV,
                        bulletRegion,
                        DROP_BOX_BULLET_HEIGHT,
                        DROP_BOX_BULLET_VY,
                        DROP_BOX_BULLET_DAMAGE,
                        DROP_BOX_RELOAD_INTERVAL,
                        DROP_BOX_HEIGHT,
                        DROP_BOX__HP,
                        DROP_BOX__Damage

                );
            }


            enemy.pos.x = Rnd.nextFloat(worldBounds.getLeft() + enemy.getHalfWidth(), worldBounds.getRight() - enemy.getHalfWidth());
            enemy.setBottom(worldBounds.getTop());
        }
    }

    public int getLevel() {
        return level;
    }
public void setToNewGame(){
        level = 1;
        generateInterval=3f;
}

}
