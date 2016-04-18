package br.edu.ifsp.droidship.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.FrameLayout;

/**
 * Created by danielmarcoto on 13/04/16.
 */
public class DroidShip extends SurfaceView implements Runnable  {
    private Context context;
    private Nave nave;
    private final SurfaceHolder holder = getHolder();
    private Tela tela;

    private boolean isRunning;

    public DroidShip(Context context, AttributeSet attributeSet){
        super(context, attributeSet);

        this.context = context;

        inicializar();
    }

    private void inicializar(){

        tela = new Tela(context);
        // TODO: Mover o cálculo para algum local

        float yNave = (tela.getAltura() / 5) * 3;
        float xNave = (tela.getLargura() / 2) - (Nave.RAIO);

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
            if (!holder.getSurface().isValid()) continue;

            Canvas canvas = holder.lockCanvas();

            // TODO: Movimento dos elementos do jogo
            nave.desenhar(canvas);


            holder.unlockCanvasAndPost(canvas);
        }
    }
}
