package br.edu.ifsp.droidship.game;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import br.edu.ifsp.droidship.R;

/**
 * Created by danielmarcoto on 15/05/16.
 */
public class Sound {
    private SoundPool soundPool;

    public static int SPACESHIP_EXPLODE;
    public static int NEW_ENEMY;
    public static int BACKGROUND_GAME;

    public Sound(Context context, SoundPool.OnLoadCompleteListener delegate){
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        soundPool.setOnLoadCompleteListener(delegate);

        BACKGROUND_GAME = this.soundPool.load(context, R.raw.music2, 2);

        SPACESHIP_EXPLODE = soundPool.load(context, R.raw.explosion2, 2);
        NEW_ENEMY = soundPool.load(context, R.raw.new_enemy, 1);

        //soundPool.play(BACKGROUND_GAME, 1, 1, 0, 1, 1);
        //soundPool.pause(BACKGROUND_GAME);
    }

    public void playEffect(int soundType){
        if (soundType == BACKGROUND_GAME) return;

        this.soundPool.play(soundType, 1, 1, 1, 0, 1);
    }

    public void playBackgroundSound(){
        soundPool.resume(BACKGROUND_GAME);
    }

    public void pauseBackgroundSound() {
        soundPool.pause(BACKGROUND_GAME);
    }
}
