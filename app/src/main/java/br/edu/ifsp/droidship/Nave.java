package br.edu.ifsp.droidship;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Nave {

    public void desenhaNave (Canvas canvas){
        Paint paint;
        paint = new Paint();
        paint.setColor((Color.GREEN));
        canvas.drawCircle(150, 400, 20, paint);
    }

}
