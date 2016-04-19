package br.edu.ifsp.droidship.game;

import android.graphics.Canvas;

/**
 * Created by danielmarcoto on 13/04/16.
 */
public abstract class DrawObject {
    protected float x;
    protected float y;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public abstract void draw(Canvas canvas);
}
