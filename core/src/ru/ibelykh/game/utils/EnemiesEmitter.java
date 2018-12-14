package ru.ibelykh.game.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.ibelykh.game.math.Rect;
import ru.ibelykh.game.math.Rnd;
import ru.ibelykh.game.pool.EnemyPool;
import ru.ibelykh.game.sprite.Enemy;

public class EnemiesEmitter {
    private static final float ENEMY_SMALL_HEIGHT = 0.1f;
    private static final float ENEMY_SMALL_BULLET_HEIGHT = 0.01f;
    private static final float ENEMY_SMALL_BULLET_VY = -0.5f;
    private static final int ENEMY_SMALL_BULLET_DAMAGE = 1;
    private static final float ENEMY_SMALL_RELOAD_INTERVAL = 1f;
    private static final int ENEMY_SMALL_HP = 3;

    private static final float ENEMY_MEDIUM_HEIGHT = 0.2f;
    private static final float ENEMY_MEDIUM_BULLET_HEIGHT = 0.02f;
    private static final float ENEMY_MEDIUM_BULLET_VY = -0.5f;
    private static final int ENEMY_MEDIUM_BULLET_DAMAGE = 5;
    private static final float ENEMY_MEDIUM_RELOAD_INTERVAL = 3f;
    private static final int ENEMY_MEDIUM_HP = 10;

    private static final float ENEMY_BIG_HEIGHT = 0.25f;
    private static final float ENEMY_BIG_BULLET_HEIGHT = 0.025f;
    private static final float ENEMY_BIG_BULLET_VY = -0.4f;
    private static final int ENEMY_BIG_BULLET_DAMAGE = 10;
    private static final float ENEMY_BIG_RELOAD_INTERVAL = 5f;
    private static final int ENEMY_BIG_HP = 15;

    private Rect worldBounds;
    private float generateInterval = 4f;
    private float generateTimer;

    private TextureRegion[] enemySmallRegion;
    private TextureRegion[] enemyMediumRegion;
    private TextureRegion[] enemyBigRegion;

    private  final Vector2 enemySmallV = new Vector2(0f, -0.2f);
    private  final Vector2 enemyMediumV = new Vector2(0f, -0.05f);
    private  final Vector2 enemyBigV = new Vector2(0f, -0.06f);

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

        this.bulletRegion = atlas.findRegion("sale");
    }

    public void generate(float delta) {
        generateTimer += delta;
        if (generateTimer >= generateInterval) {
            generateTimer = 0f;
            Enemy enemy = enemyPool.obtain();

            float type = (float) Math.random();
            if (type<0.7f) {
                enemy.set(
                        enemySmallRegion,
                        enemySmallV,
                        bulletRegion,
                        ENEMY_SMALL_BULLET_HEIGHT,
                        ENEMY_SMALL_BULLET_VY,
                        ENEMY_SMALL_BULLET_DAMAGE,
                        ENEMY_SMALL_RELOAD_INTERVAL,
                        ENEMY_SMALL_HEIGHT,
                        ENEMY_SMALL_HP

                );
            } else if (type<0.9f){
                enemy.set(
                        enemyMediumRegion,
                        enemyMediumV,
                        bulletRegion,
                        ENEMY_MEDIUM_BULLET_HEIGHT,
                        ENEMY_MEDIUM_BULLET_VY,
                        ENEMY_MEDIUM_BULLET_DAMAGE,
                        ENEMY_MEDIUM_RELOAD_INTERVAL,
                        ENEMY_MEDIUM_HEIGHT,
                        ENEMY_MEDIUM_HP

                );
            } else {
                enemy.set(
                        enemyBigRegion,
                        enemyBigV,
                        bulletRegion,
                        ENEMY_BIG_BULLET_HEIGHT,
                        ENEMY_BIG_BULLET_VY,
                        ENEMY_BIG_BULLET_DAMAGE,
                        ENEMY_BIG_RELOAD_INTERVAL,
                        ENEMY_BIG_HEIGHT,
                        ENEMY_BIG_HP

                );
            }

            enemy.pos.x = Rnd.nextFloat(worldBounds.getLeft() + enemy.getHalfWidth(), worldBounds.getRight() - enemy.getHalfWidth());
            enemy.setBottom(worldBounds.getTop());
        }
    }
}
