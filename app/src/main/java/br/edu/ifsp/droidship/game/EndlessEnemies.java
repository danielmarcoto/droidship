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
public class EndlessEnemies extends ObjetoDesenho {

    private Random random = new Random();
    private Enemy enemy = new Enemy(x, y, raio);
    private int enemiesQuant = random.nextInt(6 - 2) + 2;
    private int enemiesDist = random.nextInt(100 - 50) + 50;
    private final List<Enemy> enemyList = new ArrayList<Enemy>();

    public EndlessEnemies(float x, float y, float raio){
        this.x = x;
        this.y = y;
        this.raio = raio;

        for (int i = 0; i < enemiesQuant; i++){
            x += enemiesDist;
            this.enemyList.add(new Enemy(x, y, raio));
        }
    }

    @Override
    public void desenhar(Canvas canvas) {
        for (Enemy enemy : this.enemyList)
           enemy.desenhar(canvas);
    }

    public void falling(){

        ListIterator<Enemy> iterator = this.enemyList.listIterator();

        while (iterator.hasNext()){
            Enemy enemy = (Enemy) iterator.next();
            enemy.falling();

             if (enemy.getY() > 900){       //> tela.getAltura()){ deu problema
               iterator.remove();
               Enemy anotherEnemy = new Enemy(random.nextInt(400) + enemiesDist, 0, random.nextInt(50 - 10) + 10);
               iterator.add(anotherEnemy);
             }
        }

    }

}
