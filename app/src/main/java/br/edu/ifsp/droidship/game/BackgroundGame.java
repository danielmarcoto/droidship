package br.edu.ifsp.droidship.game;

import android.content.Context;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/**
 * Created by danielmarcoto on 28/04/16.
 */
public class BackgroundGame extends DrawObject {

    private static final int TOTAL = 30;

    private ScreenHelper screenHelper;
    private List<Star> stars;
    private Context context;
    private Random random;

    public BackgroundGame(Context context, ScreenHelper screenHelper) {
        super(context);

        this.context = context;
        this.screenHelper = screenHelper;
        this.stars = new ArrayList<>();
        this.random = new Random();

        for (int i = 0; i < TOTAL; i++){

            float radius = random.nextInt(3) + 1;
            float x = random.nextInt(screenHelper.getWidth() - (int)radius) + radius;
            float y = random.nextInt(screenHelper.getHeight() - (int)radius) + radius;

            stars.add(new Star(context, x, y, radius));
        }
    }

    public void move(){
        ListIterator<Star> iterator = stars.listIterator();

        while (iterator.hasNext()){
            Star star = iterator.next();

            star.move();

            if (star.getY() >= screenHelper.getHeight()){
                iterator.remove();

                float radius = random.nextInt(3) + 1;
                float x = random.nextInt(screenHelper.getWidth() - (int)radius) + radius;

                Star starNew = new Star(context, x, 0, radius);

                iterator.add(starNew);
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
