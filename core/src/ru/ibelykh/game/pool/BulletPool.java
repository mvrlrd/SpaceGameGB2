package ru.ibelykh.game.pool;

import ru.ibelykh.game.base.Sprite;
import ru.ibelykh.game.base.SpritesPool;
import ru.ibelykh.game.sprite.Bullet;

public class BulletPool extends SpritesPool<Bullet> {



    @Override
    protected Bullet newObject() {
        return new Bullet();
    }
}
