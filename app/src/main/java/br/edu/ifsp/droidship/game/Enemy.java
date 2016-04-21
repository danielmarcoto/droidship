package br.edu.ifsp.droidship.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import java.util.Random;


/**
 * Created by Eduardo on 19/04/2016.
 */
public class Enemy {

    private Random random = new Random();
    private float x;
    private float y;
    private float radius;

    public Enemy(float x, float y, float radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public void drawNode(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);

        canvas.drawCircle(x, y, radius, paint);
    }


    public void falling(){
        this.y += random.nextInt(10 - 2) + 2;
    }


    public float getY() {
        return y;
    }
}
