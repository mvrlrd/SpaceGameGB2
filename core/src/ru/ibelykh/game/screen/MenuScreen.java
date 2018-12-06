package ru.ibelykh.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.ibelykh.game.base.Base2DScreen;

public class MenuScreen extends Base2DScreen {

    private Texture img;
    private Vector2 pos, v, touch, ed,v2, where;



    @Override
    public void show() {
        super.show();
        ed= new Vector2(0,0);
        img = new Texture("car.jpg");
        pos = new Vector2(0,0);
        v = new Vector2(1.6f,1.6f);
        touch = new Vector2();
        v2 =new Vector2();
        where = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.7f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
//		batch.setColor(0.8f,0.44f,0.6f,0.23f);
        batch.draw(img, pos.x, pos.y);
        batch.end();
        v2.set(ed.x*v.x,ed.y*v.y);
        pos.add(v2);

    }

    @Override
    public void dispose() {
        img.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
         super.touchDown(screenX, screenY, pointer, button);
         touch.set(screenX,Gdx.graphics.getHeight() - screenY);
        System.out.println("touch x = "+ touch.x+" "+ touch.y);

where = touch.sub(pos);



ed = where.nor();
        System.out.println("ed vector touch = "+ ed);

        return false;
    }
}
