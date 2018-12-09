package ru.ibelykh.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.ibelykh.game.base.Base2DScreen;
import ru.ibelykh.game.math.Rect;
import ru.ibelykh.game.sprite.Background;
import ru.ibelykh.game.sprite.ButtonExit;
import ru.ibelykh.game.sprite.Ship;
import ru.ibelykh.game.sprite.Star;

public class GameScreen extends Base2DScreen {


    private static final int STAR_COUNT = 128;
    private TextureAtlas textureAtlas;
    private Star[] star;
    //BACKGROUND
    private Background background;
    private TextureAtlas bg;
    //SHIP
    private Ship ship;
    private Texture shp;

    //Buttons
    private ButtonExit buttonExit;
    private  TextureAtlas btAtlas;






    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        bg = new TextureAtlas("backgrounds/backgrounds.atlas");
        background = new Background(bg,"lvl1bg");

        //STAR_SHOW
        textureAtlas = new TextureAtlas("star.atlas");
        star = new Star[STAR_COUNT];
        for (int i = 0; i <star.length ; i++) {
            star[i]= new Star(textureAtlas);
        }

        //SHIP
        shp = new Texture("da.jpg");
        ship = new Ship(new TextureRegion(shp));

//        btAtlas = new TextureAtlas("buttons/menubuttons.atlas");
//        buttonExit = new ButtonExit(btAtlas);

    }

    @Override
    public void render(float delta) {
        update(delta);
        chechCollisions();
        deleteAllDestroyed();
        draw();
    }


    public void update(float delta){
        //STAR UPDATE
        for (int i = 0; i <star.length ; i++) {
            star[i].update(delta);
        }

        //SHIP UPDATE
        ship.update(delta);
    }

    public void chechCollisions(){

    }

    public void deleteAllDestroyed(){

    }

    public void draw(){
        Gdx.gl.glClearColor(0.1f,0.1f,1f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();


        background.draw(batch);

        //SHIP DRAW
        ship.draw(batch);

        //STAR DRAW
        for (int i = 0; i <star.length ; i++) {  //Star 5
            star[i].draw(batch);
        }


        // Buttons
//        buttonExit.draw(batch);
//        buttonNewGame.draw(batch);
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

//        buttonExit.resize(worldBounds);
//        buttonNewGame.resize(worldBounds);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        return super.touchUp(touch, pointer);
    }

    @Override
    public void dispose() {
        super.dispose();
        shp.dispose();
        bg.dispose();
        textureAtlas.dispose(); //star

    }
}
