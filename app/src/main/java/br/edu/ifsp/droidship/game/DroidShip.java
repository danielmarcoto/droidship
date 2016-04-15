package br.edu.ifsp.droidship.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by danielmarcoto on 13/04/16.
 */
public class DroidShip extends SurfaceView implements Runnable {
    private Context context;
    private Nave nave;

    private boolean isRunning;

    public DroidShip(Context context) {
        super(context);

        this.context = context;

        inicializar();

        // TODO: Criar uma classe Fundo
        setBackgroundColor(Color.BLACK);
    }

    private void inicializar(){
        // TODO: Mover o cálculo para algum local
        float yNave = (getY() / 5) * 3;
        float xNave = (getX() / 2) - (Nave.RAIO);

        this.nave = new Nave(xNave, yNave);
    }

    public void pausar(){
        isRunning = false;
    }

    public void retomar(){
        isRunning = true;
    }

    @Override
    public void run() {
        while(isRunning){
            SurfaceHolder holder = this.getHolder();
            if (!holder.getSurface().isValid())
                continue;

            Canvas canvas = holder.lockCanvas();

            // TODO: Movimento dos elementos do jogo
            nave.desenhar(canvas);

            holder.unlockCanvasAndPost(canvas);
        }
    }
}
