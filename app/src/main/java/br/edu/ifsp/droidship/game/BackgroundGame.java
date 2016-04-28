package br.edu.ifsp.droidship.game;

import android.content.Context;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by danielmarcoto on 28/04/16.
 */
public class BackgroundGame extends DrawObject {

    private static final int TOTAL = 20;

    private ScreenHelper screenHelper;
    private List<Star> stars;
    private Context context;

    public BackgroundGame(Context context, ScreenHelper screenHelper) {
        super(context);

        this.context = context;
        this.screenHelper = screenHelper;
        this.stars = new ArrayList<>();

        for (int i = 0; i < TOTAL; i++){
            stars.add(new Star(context, screenHelper));
        }
    }

    public void move(){
        ListIterator<Star> iterator = stars.listIterator();

        while (iterator.hasNext()){
            Star star = iterator.next();

            star.move();

            if (star.isOutOfScreen()){
                iterator.remove();
                iterator.add(new Star(context, screenHelper));
            }
        }
    }

    @Override
    public void drawNode(Canvas canvas) {
        for (Star star : stars){
            star.drawNode(canvas);
        }
    }
}
