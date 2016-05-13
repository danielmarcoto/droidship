package br.edu.ifsp.droidship.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import br.edu.ifsp.droidship.R;

/**
 * Created by danielmarcoto on 12/05/16.
 */
public class AsteroidEnemy extends Enemy {

    private float speed;
    private Context context;
    private ScreenHelper screenHelper;
    private boolean isGoingRight = true;

    public AsteroidEnemy(Context context, float x, float y, float speed, ScreenHelper screenHelper) {
        super(context, x, y);

        this.context = context;
        this.speed = speed;
        this.screenHelper = screenHelper;
    }

    @Override
    public Bitmap getBitmap() {
        return BitmapFactory.decodeResource(context.getResources(), R.drawable.asteroid);
    }

    @Override
    public void move() {
        float x = getX();
        float y = getY();

        setY(y + speed);

        if (isGoingRight){
            setX(x + speed);
        } else {
            setX(x - speed);
        }

        // Alterar a direção do movimento
        if ((x + getRadius()) >= screenHelper.getWidth()) {
            isGoingRight = false;
        } else if (x < 0){
            isGoingRight = true;
        }
    }
}
