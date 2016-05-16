package br.edu.ifsp.droidship.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import br.edu.ifsp.droidship.R;

/**
 * Created by danielmarcoto on 14/05/16.
 */
public class Explosion extends DrawObject {

    private Bitmap bitmap;
    private float x;
    private float y;
    private boolean hasFinished;

    private final int ROWS = 8;
    private final int COLUMNS = 6;

    private int widthCrop;
    private int heightCrop;

    private int currentWidth;
    private int currentHeight;

    private int currentXCrop;
    private int currentYCrop;

    public Explosion(Context context, float x, float y) {
        super(context);

        this.x = x;
        this.y = y;

        this.bitmap = BitmapFactory.decodeResource(
                context.getResources(), R.drawable.explosion_sm);

        this.hasFinished = false;

        this.widthCrop = bitmap.getWidth() / ROWS;
        this.heightCrop = bitmap.getHeight() / COLUMNS;

        this.currentWidth = 0;
        this.currentHeight = 0;

        this.currentXCrop = 0;
        this.currentYCrop = 0;
    }

    public boolean hasFinished(){
        return this.hasFinished;
    }

    @Override
    public void drawNode(Canvas canvas) {

        if (hasFinished) return;

        Paint paint = new Paint();

        Bitmap bitmapCrop = Bitmap.createBitmap(
                this.bitmap, currentWidth, currentHeight, widthCrop, heightCrop);

        canvas.drawBitmap(bitmapCrop, x, y, paint);

        currentXCrop += 2;
        currentWidth += (widthCrop * 2);

        if (currentXCrop == ROWS){
            currentXCrop = 0;
            currentWidth = 0;

            currentYCrop += 1;
            currentHeight += heightCrop;
        }

        // Encerra a animação
        if (currentYCrop == COLUMNS) {
            this.hasFinished = true;
        }
    }
}
