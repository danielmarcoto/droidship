package br.edu.ifsp.droidship.game;

import android.graphics.Canvas;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/**
 * Created by Eduardo on 19/04/2016.
 */
public class EndlessEnemies {

    private ScreenHelper screenHelper;
    private Random random = new Random();
    private float x;
    private float radius;
    private static float y = 0;
    private int enemiesQuant = random.nextInt(6 - 2) + 2;
    private int enemiesDist = random.nextInt(100 - 50) + 50;
    private final List<Enemy> enemyList = new ArrayList<Enemy>();

    public EndlessEnemies(ScreenHelper screenHelper, float x, float y, float radius){
        this.screenHelper = screenHelper;
        this.x = x;
        this.y = y;
        this.radius = radius;

        for (int i = 0; i < enemiesQuant; i++){
            x += enemiesDist;
            this.enemyList.add(new Enemy(x, y, radius));
        }
    }

    public static float getY() {
        return y;
    }

    public void drawNode(Canvas canvas) {
        for (Enemy enemy : this.enemyList)
           enemy.drawEnemy(canvas);
    }


    public void falling(){

        ListIterator<Enemy> iterator = this.enemyList.listIterator();

        while (iterator.hasNext()){
            Enemy enemy = iterator.next();
            enemy.falling();

             if (enemy.getY() > screenHelper.getHeight()){
                 iterator.remove();
                 Enemy anotherEnemy = new Enemy(random.nextInt(600) + enemiesDist, 0, random.nextInt(50 - 20) + 20);
                 iterator.add(anotherEnemy);
             }
        }

    }

    public List<Enemy> getEnemies(){
        return enemyList;
    }

    public float getX() {
        return x;
    }

    public float getRadius() {
        return radius;
    }
}
