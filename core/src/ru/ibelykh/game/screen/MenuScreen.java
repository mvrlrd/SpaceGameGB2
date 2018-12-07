package ru.ibelykh.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;



import ru.ibelykh.game.base.Base2DScreen;

public class MenuScreen extends Base2DScreen {

    private Texture img, ship;
    private Vector2 pos;

//    posShip, vShip;

//    public void setPosShip(Vector2 posShip) {
//        this.posShip = posShip;
//    }

    @Override
    public void show() {
        super.show();

        img = new Texture("un.jpg");
        pos = new Vector2(-1f,-1f);


        Ship.getInstance().setShip(new Texture("da.jpg"));
        Ship.getInstance().setPosShip(new Vector2(0f,-0.5f)) ;
        Ship.getInstance().setvShip(new Vector2(0f,0));

    }


    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(img, pos.x, pos.y,2f,2f);
        batch.draw(Ship.getInstance().getShip(), Ship.getInstance().getPosShip().x, Ship.getInstance().getPosShip().y,0.1f,0.15f);
        Ship.getInstance().getPosShip().add(Ship.getInstance().getvShip());
                if ((Ship.getInstance().getPosShip().x<-getWorldBounds().getWidth()/2)||(Ship.getInstance().getPosShip().x>((getWorldBounds().getWidth()/2)-0.1f))){
                    Ship.getInstance().getvShip().set(0f,0f);
                }

//        System.out.println(pos2.add(v));
        //        Gdx.gl.glClearColor(0.2f, 0.2f, 0.7f, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		batch.setColor(0.8f,0.44f,0.6f,0.23f);

//        v2.set(ed.x*v.x,ed.y*v.y);
//        pos.add(v2);

        batch.end();
    }


    @Override
    public void dispose() {
        img.dispose();
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
