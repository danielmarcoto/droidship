package br.edu.ifsp.droidship.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by danielmarcoto on 13/04/16.
 */
public class Nave extends ObjetoDesenho {

    public static float RAIO = 100;

    public Nave(float x, float y){
        this.x = x;
        this.y = y;
    }

    @Override
    public void desenhar(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);

        canvas.drawCircle(x, y, RAIO, paint);
    }

    public void moverParaDireita(){
        this.x += 5;
    }

    public void moverParaEsquerda(){
        this.x -= 5;
    }
}
