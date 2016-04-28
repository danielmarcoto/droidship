package br.edu.ifsp.droidship.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;


/**
 * Created by Eduardo on 19/04/2016.
 */
public class Enemy extends DrawObject {

    private Random random = new Random();
    private float x;
    private float y;
    private float radius;
    private Context context;

    public Enemy(Context context, float x, float y, float radius) {
        super(context);

        this.context = context;
        this.setX(x);
        this.setY(y);
        this.setRadius(radius);
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

    @Override
    public void drawNode(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);

        canvas.drawCircle(getX(), getY(), getRadius(), paint);
    }
}
