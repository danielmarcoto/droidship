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

    private Random random = new Random();
    private float x;
    private float radius;
    public static float y = 0;
    private int enemiesQuant = random.nextInt(6 - 2) + 2;
    private int enemiesDist = random.nextInt(100 - 50) + 50;
    private final List<Enemy> enemyList = new ArrayList<Enemy>();

    public EndlessEnemies(float x, float y, float radius){
        this.x = x;
        this.y = y;
        this.radius = radius;

        for (int i = 0; i < enemiesQuant; i++){
            x += enemiesDist;
            this.enemyList.add(new Enemy(x, y, radius));
        }
    }

    public void drawNode(Canvas canvas) {
        for (Enemy enemy : this.enemyList)
           enemy.drawNode(canvas);
    }


    public void falling(){

        ListIterator<Enemy> iterator = this.enemyList.listIterator();

        while (iterator.hasNext()){
            Enemy enemy = (Enemy) iterator.next();
            enemy.falling();

             if (enemy.getY() > 900){       // TODO pegar o tamanho da tela
                 iterator.remove();
                 Enemy anotherEnemy = new Enemy(random.nextInt(600) + enemiesDist, 0, random.nextInt(50 - 20) + 20);
                 iterator.add(anotherEnemy);
             }
        }

    }

}
