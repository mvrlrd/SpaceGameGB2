package ru.ibelykh.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.ibelykh.game.base.Base2DScreen;

public class MenuScreen extends Base2DScreen {

    private Texture img, car , lollypoppy;
    private Vector2 pos, pos2, pos3, v, touch, ed,v2, where;



    @Override
    public void show() {
        super.show();
//        ed= new Vector2(0,0);
        img = new Texture("un.jpg");
        pos = new Vector2(0,0);

        lollypoppy = new Texture("lollipop2.png");
        pos3 = new Vector2();

       car = new Texture("da.jpg") ;
       pos2 = new Vector2(400,20);

        v = new Vector2(0f,0);
//        touch = new Vector2();
//        v2 =new Vector2();
        where = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(img, pos.x, pos.y);
        batch.draw(car, pos2.x, pos.y);

        batch.draw(lollypoppy,pos3.x,pos3.y);

            pos2.add(v);

if ((pos2.x<=-4)||(pos2.x>=811)){
    v.set(0,0);
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

            if ((keycode == 29)&&(pos2.x>-4)){


                v.set(-10f, 0);
                System.out.println(pos2.add(v));

            }
            if ((keycode == 32)&&(pos2.x<811)) {

                    v.set(10f, 0);
                System.out.println(pos2.add(v));

        } return false;
    }

    @Override
    public boolean keyUp(int keycode) {
         super.keyUp(keycode);

        if (keycode==29) {
            v.set(0, 0);
        }
        if (keycode==32){

            v.set(0,0);
        }


        return false;
    }
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
