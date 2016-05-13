package br.edu.ifsp.droidship.game;

import android.content.Context;
import android.graphics.Canvas;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/**
 * Created by Eduardo on 19/04/2016.
 */
public class EndlessEnemies {

    // TODO: Melhorar a forma de sortear a quantidade de inimigos por rodada
    private static float y = 0;
    private final Context context;
    private ScreenHelper screenHelper;
    private Random random = new Random();

    private final List<Enemy> enemyList;

    public EndlessEnemies(Context context, ScreenHelper screenHelper){
        this.context = context;
        this.screenHelper = screenHelper;
        this.enemyList = new ArrayList<>();
    }

    public void drawNode(Canvas canvas) {
        for (Enemy enemy : this.enemyList)
           enemy.drawNode(canvas);
    }

    public void randomCreateEnemy(Timer timer){
        int score = (int)timer.getTimer();

        if (random.nextInt(50 - score) == 0){

            int enemyType = random.nextInt(2);

            float x = random.nextInt(((int)Enemy.RADIUS) +
                    screenHelper.getWidth() - ((int)Enemy.RADIUS) * 2);

            float speed = score + 1;

            Enemy enemy;

            if (enemyType == 0){
                enemy = new AsteroidEnemy(context, x, 1, speed, screenHelper);
            } else {
                enemy = new MeteorEnemy(context, x, 1, speed);
            }
            enemyList.add(enemy);
        }
    }

    public void falling(){

        ListIterator<Enemy> iterator = this.enemyList.listIterator();

        while (iterator.hasNext()){
            Enemy enemy = iterator.next();
            enemy.move();

             if (enemy.getY() > screenHelper.getHeight()){
                 iterator.remove();
             }
        }
    }

    public List<Enemy> getEnemies(){
        return enemyList;
    }
}
