package ru.ibelykh.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import ru.ibelykh.game.base.Base2DScreen;

public class MenuScreen extends Base2DScreen {

    private Texture img, ship;
    private Vector2 pos, posShip, vShip;

    @Override
    public void show() {
        super.show();

        img = new Texture("un.jpg");
        pos = new Vector2(0,0);

        ship = new Texture("da.jpg") ;
        posShip = new Vector2(400,20);
        vShip = new Vector2(0f,0);

    }


    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(img, pos.x, pos.y);
        batch.draw(ship, posShip.x, posShip.y);
            posShip.add(vShip);
                if ((posShip.x<=-4)||(posShip.x>=811)){
                    vShip.set(0,0);
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
            if ((keycode == 29)&&(posShip.x>-4)){
                vShip.set(-10f, 0);
            }
            if ((keycode == 32)&&(posShip.x<811)) {
                vShip.set(10f, 0);
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
