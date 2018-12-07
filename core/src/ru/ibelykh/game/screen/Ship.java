package ru.ibelykh.game.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Ship {
    private static Ship instance;

    private Texture  ship;
    private Vector2  posShip, vShip;

    public Texture getShip() {
        return ship;
    }

    public Vector2 getPosShip() {
        return posShip;
    }

    public Vector2 getvShip() {
        return vShip;
    }

    public void setShip(Texture ship) {
        this.ship = ship;
    }

    public void setPosShip(Vector2 posShip) {
        this.posShip = posShip;
    }

    public void setvShip(Vector2 vShip) {
        this.vShip = vShip;
    }

    private Ship(){

    }
    public  static synchronized  Ship getInstance(){
        if (instance==null){
            instance=new Ship();
        }
        return instance;
    }

}
