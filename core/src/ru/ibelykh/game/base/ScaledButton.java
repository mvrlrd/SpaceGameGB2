package ru.ibelykh.game.base;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class ScaledButton extends Sprite {

    private  static final float PRESS_SCALE = 0.9f;
    private int pointer;
    private boolean pressed;



    public ScaledButton(TextureRegion region) {
        super(region);

    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if (pressed || !isMe(touch)){
            System.out.println("not TOUCHED");
            return false;
        }
        else {
            pressed = true;
            this.pointer = pointer;
            scale = PRESS_SCALE;
            System.out.println("Pressedddddd");
        }
        return false;

    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        if (this.pointer!=pointer || !pressed){
            return false;
        }
        if (isMe(touch)){
        actionPerformed();
        }
        pressed = false;
        scale = 1f;
        return false;
    }

    public abstract void actionPerformed();

 }
