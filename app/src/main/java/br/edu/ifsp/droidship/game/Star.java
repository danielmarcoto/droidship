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

    private float radius;
    private float x;
    private float y;
    private float speed;

    public Star(Context context, float x, float y, float radius) {
        super(context);

        Random random = new Random();

        this.x = x;
        this.y = y;
        this.radius = radius;
        this.speed = random.nextInt(3) + 1;
    }

    public void move(){
        y += speed;
    }

    @Override
    public void drawNode(Canvas canvas) {

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);

        canvas.drawCircle(x, y, radius, paint);
    }
}
