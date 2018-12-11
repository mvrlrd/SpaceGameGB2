package ru.ibelykh.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundTrack  implements   Runnable{

    private Sound sound;
    private Music music;

    public SoundTrack(String fileName) {
         this.sound = Gdx.audio.newSound(Gdx.files.internal(fileName));
    }

    public SoundTrack(Music music) {
        this.music = music;
    }

    public void playSoundTrack(float vol){
        sound.play(vol);
    }

    public void playSoundTrack(){
        music.play();
    }

    @Override
    public void run() {
    playSoundTrack();
        System.out.println(Thread.currentThread().getStackTrace().length);
    }
}
