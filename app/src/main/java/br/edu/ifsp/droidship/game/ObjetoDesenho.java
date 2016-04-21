package br.edu.ifsp.droidship.game;

import android.graphics.Canvas;

/**
 * Created by danielmarcoto on 13/04/16.
 */
public abstract class ObjetoDesenho {
    protected float x;
    protected float y;
    protected float raio;

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

    public float getRaio() { return raio; }

    public void setRaio(float raio) { this.raio = raio; }

    public abstract void desenhar(Canvas canvas);
}
