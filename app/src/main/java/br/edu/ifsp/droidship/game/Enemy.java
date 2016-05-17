package br.edu.ifsp.droidship.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by danielmarcoto on 12/05/16.
 */
public abstract class Enemy extends DrawObject {
    private float x;
    private float y;
    private Context context;
    public static final float RADIUS = 35;

    public Enemy(Context context, float x, float y) {
        super(context);

        this.context = context;
        this.setX(x);
        this.setY(y);
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
        return RADIUS;
    }

    public abstract Bitmap getBitmap();

    public abstract void move();

    @Override
    public void drawNode(Canvas canvas) {
        Paint paint = new Paint();

        Bitmap bitmap = Bitmap.createScaledBitmap(getBitmap(),150, 150, false);
        
        canvas.drawBitmap(bitmap, getX(), getY(), paint);
    }
}
