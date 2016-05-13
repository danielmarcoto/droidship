package br.edu.ifsp.droidship.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import br.edu.ifsp.droidship.R;

/**
 * Created by danielmarcoto on 12/05/16.
 */
public class MeteorEnemy extends Enemy {

    private float speed;
    private Context context;

    public MeteorEnemy(Context context, float x, float y, float speed) {
        super(context, x, y);

        this.context = context;
        this.speed = speed;
    }

    @Override
    public Bitmap getBitmap() {
        return BitmapFactory.decodeResource(context.getResources(), R.drawable.meteor);
    }

    @Override
    public void move() {
        setY(getY() + speed);
    }

}
