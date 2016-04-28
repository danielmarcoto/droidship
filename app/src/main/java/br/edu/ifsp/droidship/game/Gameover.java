package br.edu.ifsp.droidship.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by danielmarcoto on 28/04/16.
 */
public class Gameover extends DrawObject {

    private ScreenHelper screenHelper;

    public Gameover(Context context, ScreenHelper screenHelper) {
        super(context);
        this.screenHelper = screenHelper;
    }

    @Override
    public void drawNode(Canvas canvas) {

        float x = (screenHelper.getWidth() / 2) - 70;
        float y = (screenHelper.getHeight() / 2) - 10;

        Paint paintText = new Paint();
        paintText.setTextSize(50);
        paintText.setShadowLayer(2, 2, 2, Color.GRAY);
        paintText.setColor(Color.WHITE);

        canvas.drawText("Game Over", x, y, paintText);
    }
}
