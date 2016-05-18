package br.edu.ifsp.droidship.game;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import br.edu.ifsp.droidship.R;

/**
 * Created by danielmarcoto on 15/05/16.
 */
public class Sound {
    private SoundPool soundPool;
    private MediaPlayer mediaPlayer;

    public static int SPACESHIP_EXPLODE;
    public static int NEW_ENEMY;

    public Sound(Context context, SoundPool.OnLoadCompleteListener delegate){
        soundPool = new SoundPool(30, AudioManager.STREAM_MUSIC, 0);
        soundPool.setOnLoadCompleteListener(delegate);

        // Efeitos sonoros do jogo
        SPACESHIP_EXPLODE = soundPool.load(context, R.raw.explosion2, 2);
        NEW_ENEMY = soundPool.load(context, R.raw.new_enemy, 1);

        // Música de fundo
        mediaPlayer = MediaPlayer.create(context, R.raw.music2);
        mediaPlayer.setVolume(0.3f, 0.3f);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    public void playEffect(int soundType){
        this.soundPool.play(soundType, 1, 1, 1, 0, 1);
    }

    public void playBackgroundSound(){
        mediaPlayer.start();
    }

    public void pauseBackgroundSound() {
        mediaPlayer.pause();
    }
}
