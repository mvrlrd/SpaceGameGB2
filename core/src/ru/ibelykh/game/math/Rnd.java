package ru.ibelykh.game.math;

import java.util.Random;

public class Rnd {
    private static final Random random = new Random();

    public Rnd() {
    }

    public static float nextFloat(float min, float max) {
        return random.nextFloat() * (max - min) + min;
    }

//    public  static String randomEnemy (String a, String b, String c){
//
//    }

}
