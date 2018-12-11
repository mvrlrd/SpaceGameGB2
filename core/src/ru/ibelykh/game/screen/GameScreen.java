package ru.ibelykh.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import ru.ibelykh.game.base.Base2DScreen;
import ru.ibelykh.game.math.Rect;
import ru.ibelykh.game.pool.BulletPool;
import ru.ibelykh.game.sprite.Background;
import ru.ibelykh.game.sprite.Ship;
import ru.ibelykh.game.sprite.Star;

public class GameScreen extends Base2DScreen{

    private static final int STAR_COUNT = 128;
    private TextureAtlas textureAtlas;
    private Star[] star;
    //BACKGROUND
    private Background background;
    private TextureAtlas bg;
    //SHIP
    private Ship ship;
    private Texture shp;
    private TextureAtlas atl;

    private BulletPool bulletPool;
    //SOUND
    private Thread  soundThread;
    private SoundTrack soundTrack;
    private Music music;


    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();

        //BACKGROUND
        bg = new TextureAtlas("backgrounds/backgrounds.atlas");
        background = new Background(bg,"lvl1bg");

        //STAR_SHOW
        textureAtlas = new TextureAtlas("star.atlas");
        star = new Star[STAR_COUNT];
        for (int i = 0; i <star.length ; i++) {
            star[i]= new Star(textureAtlas);
        }
        bulletPool = new BulletPool();

        //SHIP
        atl = new TextureAtlas("images/shipsAndBullets.atlas");
        ship = new Ship(atl,bulletPool);

        //SOUND     Попытка реализовать музыку в другом потоке
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/fighttheme.mp3"));
        soundTrack = new SoundTrack(music);
        soundThread = new Thread(soundTrack);
        soundThread.start();
    }

    @Override
    public void render(float delta) {
        update(delta);
        checkCollisions();
        deleteAllDestroyed();
        draw();
    }


    public void update(float delta){

        //STAR UPDATE
        for (int i = 0; i <star.length ; i++) {
            star[i].update(delta);
        }
        bulletPool.updateActiveSprites(delta);

        //SHIP UPDATE
        ship.update(delta);
    }

    public void checkCollisions(){
    }

    public void deleteAllDestroyed(){
        bulletPool.freeAllDestroyedActiveSprites();
    }

    public void draw(){
        Gdx.gl.glClearColor(0.1f,0.1f,1f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        background.draw(batch);
        ship.draw(batch);
        //STAR DRAW
        for (int i = 0; i <star.length ; i++) {  //Star 5
            star[i].draw(batch);
        }
        bulletPool.drawActiveSprites(batch);

        batch.end();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        //STAR RESIZE
        for (int i = 0; i <star.length ; i++) {  //Star 6
            star[i].resize(worldBounds);
        }
        //BACKGROUND RESIZE
        background.resize(worldBounds);
        //SHIP RESIZE
        ship.resize(worldBounds);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        ship.touchDown(touch,pointer);
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        ship.touchUp(touch,pointer);
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
        shp.dispose();
        bulletPool.dispose();
        bg.dispose();
        soundThread.stop();
        textureAtlas.dispose(); //star

    }
}
