package br.edu.ifsp.droidship.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by danielmarcoto on 13/04/16.
 */
public class Spaceship extends DrawObject {

    private static float RADIUS = 80;

    private ScreenHelper screenHelper;

    public Spaceship(Context context, ScreenHelper screenHelper){
        super(context);

        this.screenHelper = screenHelper;

        setY((screenHelper.getHeight() / 5) * 3);
        setX(screenHelper.getWidth() / 2);
    }

    public static float getRADIUS() {
        return RADIUS;
    }

    public static void setRADIUS(float RADIUS) {
        Spaceship.RADIUS = RADIUS;
    }

    @Override
    public void drawNode(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);

        canvas.drawCircle(getX(), getY(), getRADIUS(), paint);
    }

    public boolean isOutOfScreenLeft(){
        return (getX() - RADIUS) <= 0;
    }

    public boolean isOutOfScreenRight(){
        return (getX() + RADIUS) >= screenHelper.getWidth();
    }

    public boolean isOutOfScreen(){
        if ((getX() - RADIUS) <= 0 ||
                (getX() + RADIUS) >= screenHelper.getWidth())
            return true;
        return false;
    }
}
