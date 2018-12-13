package ru.ibelykh.game.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.ibelykh.game.base.Sprite;

public class Explosion extends Sprite {
    private  float animateTimer;
    private float animateInterval = 0.017f;
    private Sound explosionSound;

    public Explosion(TextureRegion region, int rows, int cols, int frames, Sound explosionSound) {
        super(region, rows, cols, frames);
this.explosionSound = explosionSound;
    }
    public void  set(float height, Vector2 pos){
        this.pos.set(pos);
        setHeightProportion(height);
        explosionSound.play();
    }

    @Override
    public void update(float delta) {
        animateTimer += delta;
        if (animateTimer>=animateInterval){
            animateTimer = 0f;
            if (++frame == regions.length){
            setDestroyed(true);
            frame = 0;
            }


        }
    }
}
