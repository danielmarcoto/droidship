package br.edu.ifsp.droidship;

import android.content.Context;
import android.view.SurfaceView;

public class DroidShip extends SurfaceView implements Runnable {
    boolean running = false;
    Thread renderThread = null;

    public DroidShip(Context context){
        super(context);
    }

    @Override
    public void run() {
        while(running){
           System.out.println("DroidShip Voando!!!");

        }

    }

    public void resume(){
        running = true;
        renderThread = new Thread(this);
        renderThread.start();
    }
}
