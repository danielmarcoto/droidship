package br.edu.ifsp.droidship.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by danielmarcoto on 13/04/16.
 */
public class Control extends DrawObject
{
    private ScreenHelper screenHelper;
    private Context context;

    public Control(Context context, ScreenHelper screenHelper){
        super(context);

        this.context = context;
        this.screenHelper = screenHelper;

        setY((screenHelper.getHeight() / 5) * 4);
    }

    @Override
    public void drawNode(Canvas canvas) {

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAlpha(8);

        canvas.drawRect(0, getY(),
                screenHelper.getWidth(),
                screenHelper.getHeight(), paint);
    }
}
