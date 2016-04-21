package br.edu.ifsp.droidship.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by danielmarcoto on 13/04/16.
 */
public class Spaceship extends DrawObject {

    public static float RADIUS = 100;

    public Spaceship(Context context, ScreenHelper screenHelper){
        super(context);

        setY((screenHelper.getHeight() / 5) * 3);
        setX(screenHelper.getWidth() / 2);
    }

    @Override
    public void drawNode(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);

        canvas.drawCircle(getX(), getY(), RADIUS, paint);
    }
}
