package br.edu.ifsp.droidship.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

/**
 * Created by danielmarcoto on 28/04/16.
 */
public class Star extends DrawObject {

    private ScreenHelper screenHelper;
    private float radius;
    private float x;
    private float y;
    private float speed;

    public Star(Context context, ScreenHelper screenHelper) {
        super(context);

        this.screenHelper = screenHelper;

        Random random = new Random();

        this.radius = random.nextInt(8) + 3;
        this.x = random.nextInt(screenHelper.getWidth() - (int)radius) + radius;
        this.speed = random.nextInt(6) + 1;
    }

    public void move(){
        y += speed;
    }

    public boolean isOutOfScreen(){
        return (y + radius) > screenHelper.getHeight();
    }

    @Override
    public void drawNode(Canvas canvas) {

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);

        canvas.drawCircle(x, y, radius, paint);
    }
}
