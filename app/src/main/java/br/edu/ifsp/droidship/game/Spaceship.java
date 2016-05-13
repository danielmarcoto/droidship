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

    private static float RADIUS = 80;

    private ScreenHelper screenHelper;
    private Context context;

    public Spaceship(Context context, ScreenHelper screenHelper){
        super(context);

        this.context = context;
        this.screenHelper = screenHelper;

        setY(((screenHelper.getHeight() / 5) * 3) + 100);
        setX(screenHelper.getWidth() / 2);
    }

    public static float getRADIUS() {
        return RADIUS;
    }

    @Override
    public void drawNode(Canvas canvas) {
        Paint paint = new Paint();

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
}
