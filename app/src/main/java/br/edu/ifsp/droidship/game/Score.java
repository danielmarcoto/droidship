package br.edu.ifsp.droidship.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Eduardo on 23/04/2016.
 */
public class Score {
    public int score;

    public Score() {

    }

    public void addScore(int points){
        score += points;
    }

    public void drawScore (Canvas canvas){
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTextSize(50);
        canvas.drawText(String.valueOf(this.score), 50, 1000, paint);
    }
}
