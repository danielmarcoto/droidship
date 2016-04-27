package br.edu.ifsp.droidship.game;

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
        this.setX(x);
        this.setY(y);
        this.setRadius(radius);
    }

    public void drawEnemy(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);

        canvas.drawCircle(getX(), getY(), getRadius(), paint);
    }


    public void falling(){
        this.setY(this.getY() + random.nextInt(10 - 2) + 2);
    }


    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}
