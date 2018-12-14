//package ru.ibelykh.game.sprite;
//
//import com.badlogic.gdx.math.Vector2;
//
//import ru.ibelykh.game.base.Sprite;
//import ru.ibelykh.game.math.Rect;
//
//public class DropBox extends Sprite {
//
//    private Rect worldBounds;
//    private Vector2 v = new Vector2();
//    private int health;
//
//
//
//
//
//    @Override
//    public void update(float delta) {
//        super.update(delta);
//        pos.mulAdd(v,delta);
//        if (isOutside(worldBounds)){
//            setDestroyed(true);
//
//        }
//    }
//}
