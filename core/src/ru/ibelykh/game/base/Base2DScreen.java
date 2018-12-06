package ru.ibelykh.game.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Base2DScreen implements Screen, InputProcessor {
    protected SpriteBatch batch;
    @Override
    public void show() {
        System.out.println("show");
        batch = new SpriteBatch();
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {
        System.out.println("resize");
    }

    @Override
    public void pause() {
        System.out.println("pause");
    }

    @Override
    public void resume() {
        System.out.println("resume");
    }

    @Override
    public void hide() {
        System.out.println("hide");
dispose();
    }

    @Override
    public void dispose() {
        System.out.println("dispose");
        batch.dispose();
    }


    @Override
    public boolean keyDown(int keycode) {
        System.out.println("keydowm  keycode = "+ keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        System.out.println("keyup keycode = "+ keycode);
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
//        System.out.println("charakter = "+ character);
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("touchdown = "+ screenX+" "+ screenY);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        System.out.println("touchup = "+ screenX+" "+ screenY);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        System.out.println("touchdragged = "+ screenX+" "+ screenY);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
//        System.out.println("keydowm  keycode = "+ keycode);
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
//        System.out.println("keydowm  keycode = "+ keycode);
        return false;
    }
}
