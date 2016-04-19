package br.edu.ifsp.droidship.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by danielmarcoto on 13/04/16.
 */
public class Spaceship extends DrawObject {

    public static float RADIUS = 100;

    public Spaceship(float x, float y){
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);

        canvas.drawCircle(x, y, RADIUS, paint);
    }
}
