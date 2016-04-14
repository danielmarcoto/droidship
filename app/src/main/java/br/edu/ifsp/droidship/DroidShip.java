package br.edu.ifsp.droidship;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DroidShip extends SurfaceView implements Runnable {
    boolean running = false;
    Thread renderThread = null;
    SurfaceHolder holder;
    Nave nave;

    public DroidShip(Context context) {
        super(context);
        holder = getHolder();
        inicializaElementos();
    }

    private void inicializaElementos() {
        this.nave = new Nave();
    }

    @Override
    public void run() {
        while(running){
           if (!holder.getSurface().isValid())
               continue;

            Canvas canvas = holder.lockCanvas();


            nave.desenhaNave(canvas);


            holder.unlockCanvasAndPost(canvas);

        }

    }

    public void resume(){
        running = true;
        renderThread = new Thread(this);
        renderThread.start();
    }
}
