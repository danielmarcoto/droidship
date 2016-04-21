package br.edu.ifsp.droidship.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

/**
 * Created by danielmarcoto on 13/04/16.
 */
public class DroidShip extends SurfaceView implements Runnable  {
    private Context context;
    private Nave nave;
    private Enemy enemy;
    private EndlessEnemies endlessEnemies;
    private final SurfaceHolder holder = getHolder();
    private Tela tela;
    private Random random = new Random();

    private boolean isRunning;

    public DroidShip(Context context, AttributeSet attributeSet){
        super(context, attributeSet);

        this.context = context;

        inicializar();
    }

    private void inicializar(){

        tela = new Tela(context);

        float yNave = (tela.getAltura() / 5) * 3;
        float xNave = (tela.getLargura() / 2) - (Nave.RAIO);
        this.nave = new Nave(xNave, yNave);

        float xEnemy = random.nextInt(10);
        float yEnemy = 0;
        float raio = random.nextInt(40 - 10) + 10;
        this.enemy = new Enemy(xEnemy,yEnemy,raio);

        this.endlessEnemies = new EndlessEnemies(xEnemy, yEnemy, raio);
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

            canvas.drawColor(Color.BLACK);

            // TODO: Movimento dos elementos do jogo
            nave.desenhar(canvas);
            endlessEnemies.desenhar(canvas);
            endlessEnemies.falling();



            holder.unlockCanvasAndPost(canvas);
        }
    }
}
