package ru.ibelykh.game.pool;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.ibelykh.game.base.SpritesPool;
import ru.ibelykh.game.sprite.Explosion;

public class ExplosionPool extends SpritesPool<Explosion> {

    private TextureRegion explosionRegion;
    private Sound explosionSound;

    public ExplosionPool(TextureAtlas atlas, Sound explosionSound) {
        explosionRegion = atlas.findRegion("explosion");
        this.explosionSound = explosionSound;
    }

    @Override
    protected Explosion newObject() {
       return new Explosion(explosionRegion, 3, 3, 9, explosionSound);
    }
}
