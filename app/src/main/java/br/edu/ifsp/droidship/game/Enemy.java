package br.edu.ifsp.droidship.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import java.util.Random;


/**
 * Created by Eduardo on 19/04/2016.
 */
public class Enemy extends ObjetoDesenho {

    private Random random = new Random();


    public Enemy(float x, float y, float raio){
        this.x = x;
        this.y = y;
        this.raio = raio;
    }


    @Override
    public void desenhar(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);

        canvas.drawCircle(x, y, raio, paint);
    }

    public void falling(){
        this.y += random.nextInt(10 - 2) + 2;
    }


}
