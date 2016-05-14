package br.edu.ifsp.droidship.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Eduardo on 23/04/2016.
 */
public class Score {
    private int score;
    public Timer timer;

    public Score(Timer timer) {
        this.timer = timer;
    }

    public void drawScore (Canvas canvas){

        setScore((int)(timer.getTimer() * 10));

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTextSize(50);
        canvas.drawText(String.valueOf(getScore()), 50, 100, paint);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
