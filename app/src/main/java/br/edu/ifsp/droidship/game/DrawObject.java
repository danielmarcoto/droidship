package br.edu.ifsp.droidship.game;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

/**
 * Created by danielmarcoto on 13/04/16.
 */
public abstract class DrawObject extends View {

    public DrawObject(Context context) {
        super(context);
    }

    public abstract void drawNode(Canvas canvas);
}
