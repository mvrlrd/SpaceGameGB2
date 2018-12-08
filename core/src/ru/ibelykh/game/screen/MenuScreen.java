package ru.ibelykh.game.screen;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;



import ru.ibelykh.game.base.Base2DScreen;
import ru.ibelykh.game.math.Rect;

public class MenuScreen extends Base2DScreen {

    private static final int STAR_COUNT = 256; //Star
    private TextureAtlas textureAtlas; //Star2

    private Star[] star;  //Star 1

    private Texture img;
    private Vector2 pos;



    @Override
    public void show() {
        super.show();
//textureAtlas = new TextureAtlas("textures/menuAtlas.tpack"); // Star 3
        img = new Texture("un.jpg");
        pos = new Vector2(-1f,-1f);

        star = new Star[STAR_COUNT]; //Star 4
        for (int i = 0; i <star.length ; i++) {
            star[i]= new Star(textureAtlas);
        }


        Ship.getInstance().setShip(new Texture("da.jpg"));
        Ship.getInstance().setPosShip(new Vector2(-0.05f,-0.5f)) ;
        Ship.getInstance().setvShip(new Vector2(0f,0));

    }


    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    public void update(float delta) {
        for (int i = 0; i <star.length ; i++) {
            star[i].update(delta);
        }
    }
    public void draw() {
        batch.begin();
        batch.draw(img, pos.x, pos.y,2f,2f);
        for (int i = 0; i <star.length ; i++) {  //Star 5
            star[i].draw(batch);
        }

        batch.draw(Ship.getInstance().getShip(), Ship.getInstance().getPosShip().x, Ship.getInstance().getPosShip().y,0.1f,0.15f);
        Ship.getInstance().getPosShip().add(Ship.getInstance().getvShip());
        if ((Ship.getInstance().getPosShip().x<-getWorldBounds().getWidth()/2)||(Ship.getInstance().getPosShip().x>((getWorldBounds().getWidth()/2)-0.1f))){
            Ship.getInstance().getvShip().set(0f,0f);
        }

//        Gdx.gl.glClearColor(0.2f, 0.2f, 0.7f, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		  batch.setColor(0.8f,0.44f,0.6f,0.23f);

        batch.end();
    }



    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        for (int i = 0; i <star.length ; i++) {  //Star 6
            star[i].resize(worldBounds);
        }

    }

    @Override
    public void dispose() {
        img.dispose();
        textureAtlas.dispose(); //Star 7
        super.dispose();
    }



    @Override
    public boolean keyDown(int keycode) {
        super.keyDown(keycode);
            if ((keycode == 29)&&(Ship.getInstance().getPosShip().x>-getWorldBounds().getWidth()/2)){
                Ship.getInstance().getvShip().set(-0.01f, 0);
            }
            if ((keycode == 32)&&(Ship.getInstance().getPosShip().x<(getWorldBounds().getWidth()/2)-0.1f)) {
                Ship.getInstance().getvShip().set(0.01f, 0);
            }
    return false;
    }



//    @Override
//    public boolean keyUp(int keycode) {
//         super.keyUp(keycode);
//
//    return false;
//    }


    //    @Override
//    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//         super.touchDown(screenX, screenY, pointer, button);
//         touch.set(screenX,Gdx.graphics.getHeight() - screenY);
//        System.out.println("touch x = "+ touch.x+" "+ touch.y);

//where = touch.sub(pos);


//
//ed = where.nor();
//        System.out.println("ed vector touch = "+ ed);

//        return false;
//    }
}
