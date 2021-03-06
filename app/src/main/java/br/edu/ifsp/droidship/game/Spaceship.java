package br.edu.ifsp.droidship.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import br.edu.ifsp.droidship.R;

/**
 * Created by danielmarcoto on 13/04/16.
 */
public class Spaceship extends DrawObject {

    private static float RADIUS = 70;

    private ScreenHelper screenHelper;
    private Context context;
    private int alpha;

    public Spaceship(Context context, ScreenHelper screenHelper){
        super(context);

        this.context = context;
        this.screenHelper = screenHelper;

        setY(((screenHelper.getHeight() / 5) * 3) + 100);
        setX(screenHelper.getWidth() / 2);
        this.alpha = 200;
    }

    public static float getRADIUS() {
        return RADIUS;
    }

    public void setAlpha(int alpha){
        this.alpha = alpha;
    }

    @Override
    public void drawNode(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAlpha(alpha);

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.spaceship);
        bitmap = Bitmap.createScaledBitmap(bitmap, 150, 150, false);
        canvas.drawBitmap(bitmap, getX(), getY(), paint);
    }

    public boolean isOutOfScreenLeft(){
        return (getX() - RADIUS) <= 0;
    }

    public boolean isOutOfScreenRight(){
        return (getX() + RADIUS) >= screenHelper.getWidth();
    }

    public boolean isOutOfScreenTop(){
        return (getY() - RADIUS) <= 0;
    }

    public boolean isOutOfScreenBottom(){
        return (getY() + (RADIUS * 2)) >= screenHelper.getHeight();
    }
}
