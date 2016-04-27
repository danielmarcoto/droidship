package br.edu.ifsp.droidship.game;

import android.content.Context;
import android.graphics.Canvas;

/**
 * Created by danielmarcoto on 26/04/16.
 */
public class CollisionDetector {
    private EndlessEnemies endlessEnemies;
    private Spaceship spaceship;
    private double distance;
    public boolean gameover;
    private Context context;
    private ScreenHelper screenHelper;
    private float x ;
    private float y ;
    private float radius;

    public CollisionDetector(EndlessEnemies endlessEnemies, Spaceship spaceship) {
        this.endlessEnemies = endlessEnemies;
        this.spaceship = spaceship;
    }

    // TODO: Criar método que irá detectar colisão entre objetos na tela

    public void collision(Canvas canvas){

        spaceship = new Spaceship(context, screenHelper);
        endlessEnemies = new EndlessEnemies(screenHelper, x, y, radius);

        //calcula a hipotenusa
        distance = Math.pow(spaceship.getY() - endlessEnemies.getY(), 2) + Math.pow(spaceship.getX() - endlessEnemies.getX(), 2);
        distance = Math.sqrt(distance);

        //verifica distancia entre os raios
        if (distance <= spaceship.getRADIUS() + endlessEnemies.getRadius()){
            gameover = true;
        }
    }

}
