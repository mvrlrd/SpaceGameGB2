package ru.ibelykh.game.screen;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import ru.ibelykh.game.base.Base2DScreen;
import ru.ibelykh.game.math.Rect;
import ru.ibelykh.game.sprite.Background;
import ru.ibelykh.game.sprite.ButtonExit;
import ru.ibelykh.game.sprite.ButtonNewGame;
import ru.ibelykh.game.sprite.Star;

public class MenuScreen extends Base2DScreen  {
    //STAR
    private static final int STAR_COUNT = 512;
    private TextureAtlas textureAtlas;
    private Star[] star;
    //BACKGROUND
    private Background background;
    private TextureAtlas bg;
    //Buttons
    private ButtonExit buttonExit;
    private  TextureAtlas btAtlas;

    private ButtonNewGame buttonNewGame;

    public MenuScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();

        //BACKGROUND SHOW
        bg = new TextureAtlas("backgrounds/backgrounds.atlas");
        background = new Background(bg,"menubg");

        //STAR_SHOW
        textureAtlas = new TextureAtlas("star.atlas");
        star = new Star[STAR_COUNT];
        for (int i = 0; i <star.length ; i++) {
            star[i]= new Star(textureAtlas);
        }
        btAtlas = new TextureAtlas("buttons/menubuttons.atlas");
        buttonExit = new ButtonExit(btAtlas);
        buttonNewGame = new ButtonNewGame(btAtlas,game);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    public void update(float delta) {
        //STAR UPDATE
        for (int i = 0; i <star.length ; i++) {
            star[i].update(delta);
        }
    }

    public void draw() {
        batch.begin();

        Gdx.gl.glClearColor(0.1f,0.1f,1f,2);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //BACKGROUND DRAW
        background.draw(batch);
        //STAR DRAW
        for (int i = 0; i <star.length ; i++) {  //Star 5
            star[i].draw(batch);
        }
        // Buttons
        buttonExit.draw(batch);
        buttonNewGame.draw(batch);

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
        buttonExit.resize(worldBounds);
        buttonNewGame.resize(worldBounds);
    }

    @Override
    public void dispose() {
        bg.dispose();
        textureAtlas.dispose(); //star
        btAtlas.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        buttonExit.touchDown(touch,pointer);
        buttonNewGame.touchDown(touch,pointer);
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        buttonExit.touchUp(touch,pointer);
        buttonNewGame.touchUp(touch,pointer);
        return super.touchUp(touch, pointer);
    }
}
