package ru.ibelykh.game.base;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.ibelykh.game.math.Rect;
import ru.ibelykh.game.utils.Regions;

public abstract class Sprite extends Rect {
    protected float angle;
    protected float scale = 1f;
    protected TextureRegion[] regions;
    protected int frame; //счетчик на каком кадре массива находимся (актуально для взрыва где много кадров)
    private boolean isDestroyed;

    public Sprite() {
            }

    public Sprite(TextureRegion region) {
        regions = new TextureRegion[1];
        regions[0]=region;
    }

    public Sprite(TextureRegion region, int rows, int cols, int frames) {
        regions = Regions.split(region, rows, cols, frames);
    }


    public void draw(SpriteBatch batch){
        batch.draw(regions[frame],/*текущий регион*/
                getLeft(), getBottom(), //точка отрисовки (смещаем на половину ширины и высоты) потому что отрисовывается с левого нижнего угла
                halfWidth,halfHeight, //точка вращения
                getWidth(), getHeight(), //ширина и высота
                scale,scale, //масштаб по Х и по У
                angle // угол вращения
        );
    }


    public void setHeightProportion (float height){
setHeight(height);
float aspect = regions[frame].getRegionWidth() / (float) regions[frame].getRegionHeight();
setWidth(height*aspect);
    }

    public void resize (Rect worldBounds){

    }
    public void update (float delta){

    }

    public boolean touchDown(Vector2 touch, int pointer){
        return  false;
    }
    public boolean touchUp(Vector2 touch, int pointer){
        return  false;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void setDestroyed(boolean destroyed) {
        isDestroyed = destroyed;
    }
}

