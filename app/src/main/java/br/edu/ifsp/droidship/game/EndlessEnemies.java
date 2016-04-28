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

    // TODO: Melhorar a forma de fortear a quantidade de inimigos por rodada

    private final Context context;
    private ScreenHelper screenHelper;
    private Random random = new Random();
    private float x;
    private float radius = 30;
    private static float y = 0;
    private int enemiesQuant = random.nextInt(6 - 2) + 2;
    private int enemiesDist = random.nextInt(100 - 50) + 50;
    private final List<Enemy> enemyList = new ArrayList<Enemy>();

    public EndlessEnemies(Context context, ScreenHelper screenHelper){
        this.context = context;
        this.screenHelper = screenHelper;

        for (int i = 0; i < enemiesQuant; i++){
            x += enemiesDist;
            this.enemyList.add(new Enemy(context, x, y, radius));
        }
    }

    public void drawNode(Canvas canvas) {
        for (Enemy enemy : this.enemyList)
           enemy.drawNode(canvas);
    }

    public void falling(){

        ListIterator<Enemy> iterator = this.enemyList.listIterator();

        while (iterator.hasNext()){
            Enemy enemy = iterator.next();
            enemy.falling();

             if (enemy.getY() > screenHelper.getHeight()){
                 iterator.remove();
                 Enemy anotherEnemy = new Enemy(context,
                         random.nextInt(600) + enemiesDist,
                         0,
                         random.nextInt(50 - 20) + 20);
                 iterator.add(anotherEnemy);
             }
        }
    }

    public List<Enemy> getEnemies(){
        return enemyList;
    }
}
