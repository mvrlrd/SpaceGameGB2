package ru.ibelykh.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import java.util.List;
import ru.ibelykh.game.base.Base2DScreen;
import ru.ibelykh.game.math.Rect;
import ru.ibelykh.game.pool.BulletPool;
import ru.ibelykh.game.pool.EnemyPool;
import ru.ibelykh.game.pool.ExplosionPool;
import ru.ibelykh.game.sprite.Background;
import ru.ibelykh.game.sprite.Bullet;
import ru.ibelykh.game.sprite.ButtonExit;
import ru.ibelykh.game.sprite.ButtonNewGame;
import ru.ibelykh.game.sprite.Enemy;
import ru.ibelykh.game.sprite.Ship;
import ru.ibelykh.game.sprite.Star;
import ru.ibelykh.game.sprite.Title;
import ru.ibelykh.game.utils.EnemiesEmitter;

public class GameScreen extends Base2DScreen{

    //Star
    private static final int STAR_COUNT = 128;
    private TextureAtlas textureAtlas;
    private Star[] star;

    //BACKGROUND
    private Background background;
    private TextureAtlas bg;

    //SHIP
    private Ship ship;
    private TextureAtlas atl;

    //Pool'ы
    private BulletPool bulletPool;
    private ExplosionPool explosionPool;
    private EnemyPool enemyPool;

    //SOUND
    private Music music;
    private Sound shipShootSound;
    private Sound explosionSound;

    private EnemiesEmitter enemiesEmitter;

    //GameOver
    private Title title;
    private TextureAtlas titleAtlas;

    //Buttons
    private ButtonNewGame buttonNewGame;
    private ButtonExit buttonExit;
    private  TextureAtlas btAtlas;


    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();

        //Buttons
        btAtlas = new TextureAtlas("buttons/menubuttons.atlas");
        buttonExit = new ButtonExit(btAtlas);
        buttonNewGame = new ButtonNewGame(btAtlas,game);

        //GameOver Title
        titleAtlas = new TextureAtlas("images/gameOver.atlas");
        title = new Title(titleAtlas);

        //Music
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/fighttheme.mp3"));
        music.setLooping(true);
        music.setVolume(0.2f);
        music.play();

        explosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/boom.wav"));
        shipShootSound = Gdx.audio.newSound(Gdx.files.internal("sounds/pau.wav"));

        //BACKGROUND
        bg = new TextureAtlas("backgrounds/backgrounds.atlas");
        background = new Background(bg,"lvl1bg");

        //STAR
        textureAtlas = new TextureAtlas("star.atlas");
        star = new Star[STAR_COUNT];
        for (int i = 0; i <star.length ; i++) {
            star[i]= new Star(textureAtlas);
        }

        //Pools
        bulletPool = new BulletPool();
        explosionPool = new ExplosionPool(new TextureAtlas("images/explosion.atlas"),explosionSound);
        enemyPool = new EnemyPool(bulletPool,explosionPool,ship,worldBounds);



        //Ship
        atl = new TextureAtlas("images/shipsAndBullets.atlas");
        ship = new Ship(atl,bulletPool,explosionPool,shipShootSound);

        enemiesEmitter = new EnemiesEmitter(worldBounds, enemyPool, atl);
     }

    @Override
    public void render(float delta) {
        update(delta);
        checkCollisions();
        deleteAllDestroyed();
        draw();
    }

    public void update(float delta){
        //STAR
        for (int i = 0; i <star.length ; i++) {
            star[i].update(delta);
        }

        //Pools
        bulletPool.updateActiveSprites(delta);
        enemyPool.updateActiveSprites(delta);
        enemiesEmitter.generate(delta);
        explosionPool.updateActiveSprites(delta);

        //SHIP UPDATE
        if (!ship.isDestroyed()) {
            ship.update(delta);
        }
    }

    public void checkCollisions(){
        List<Enemy> enemyList = enemyPool.getActiveObjects();
            for (Enemy enemy : enemyList){
                if (enemy.isDestroyed()){
                    continue;
                }
                float minDist = enemy.getHalfWidth() + ship.getHalfWidth();

                if (enemy.pos.dst2(ship.pos)<minDist*minDist){
                    enemy.setDestroyed(true);
                    enemy.boom();

                    ship.damage(enemy.getDmg());
                    System.out.println("HP  "+ship.getHp());
                    return;
                }
            }

        List<Bullet> bulletList = bulletPool.getActiveObjects();
            for (Enemy enemy : enemyList){
                if (enemy.isDestroyed()){
                    continue;
                }
                for (Bullet bullet : bulletList){
                    if (bullet.getOwner() != ship || bullet.isDestroyed()){
                        continue;
                    }
                    if (enemy.isBulletCollision(bullet)){
                    enemy.damage(bullet.getDamage());
                    bullet.setDestroyed(true);
                    }
                }
            }
        for (Bullet bullet : bulletList){
            if (bullet.isDestroyed() || bullet.getOwner() == ship) {
                continue;
            }
            if (ship.isBulletCollision(bullet)){
                bullet.setDestroyed(true);
                ship.damage(bullet.getDamage());
                System.out.println("HP  "+ship.getHp());
            }
        }
    }

    public void deleteAllDestroyed(){
        bulletPool.freeAllDestroyedActiveSprites();
        enemyPool.freeAllDestroyedActiveSprites();
        explosionPool.freeAllDestroyedActiveSprites();
    }

    public void draw(){
        Gdx.gl.glClearColor(0.1f,0.1f,1f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        //STAR DRAW
        for (int i = 0; i <star.length ; i++) {  //Star 5
            star[i].draw(batch);
        }
        bulletPool.drawActiveSprites(batch);
        enemyPool.drawActiveSprites(batch);
        explosionPool.drawActiveSprites(batch);

        if (!ship.isDestroyed()) {
            ship.draw(batch);
        }

        if (ship.isDestroyed()){    //Если корабль игрока разрушен
            title.draw(batch);          // выводим Гэйм Овер
            enemyPool.getActiveObjects().removeAll(enemyPool.getActiveObjects()); //удаляем все корабли врагов из активных объектов
            buttonExit.draw(batch);  // рисуем кнопки нью гэйм и выход
            buttonNewGame.draw(batch);
            music.setVolume(0.1f); // приглушаем музыку
        }

        batch.end();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        //STAR
        for (int i = 0; i <star.length ; i++) {  //Star 6
            star[i].resize(worldBounds);
        }
        //BACKGROUND
        background.resize(worldBounds);
        //SHIP
        ship.resize(worldBounds);
        //Buttons
        buttonExit.resize(worldBounds);
        buttonNewGame.resize(worldBounds);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        ship.touchDown(touch,pointer);
        if (ship.isDestroyed()) {
            buttonExit.touchDown(touch, pointer);
            buttonNewGame.touchDown(touch, pointer);
        }
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        ship.touchUp(touch,pointer);
        if (ship.isDestroyed()) {
            buttonExit.touchUp(touch, pointer);
            buttonNewGame.touchUp(touch, pointer);
        }
        return super.touchUp(touch, pointer);
    }

    @Override
    public boolean keyDown(int keycode) {
        ship.keyDown(keycode);
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        ship.keyUp(keycode);
        return super.keyUp(keycode);
    }

    @Override
    public void dispose() {
        super.dispose();
        bulletPool.dispose();
        bg.dispose();
        music.dispose();
        shipShootSound.dispose();
        explosionSound.dispose();
        btAtlas.dispose();
        enemyPool.dispose();
        titleAtlas.dispose();
        textureAtlas.dispose();
        explosionPool.dispose();
    }
}
