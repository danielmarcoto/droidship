package br.edu.ifsp.droidship.game;

/**
 * Created by danielmarcoto on 26/04/16.
 */
public class CollisionDetector {
    private EndlessEnemies endlessEnemies;
    private Spaceship spaceship;

    public CollisionDetector(EndlessEnemies endlessEnemies, Spaceship spaceship) {
        this.endlessEnemies = endlessEnemies;
        this.spaceship = spaceship;
    }

    public boolean hasHit(){

        for (Enemy enemy : endlessEnemies.getEnemies()){
            //calcula a hipotenusa
            double distance = Math.pow(spaceship.getY() - enemy.getY(), 2) +
                    Math.pow(spaceship.getX() - enemy.getX(), 2);
            distance = Math.sqrt(distance);

            double radiusSum = spaceship.getRADIUS() + enemy.getRadius();

            //Log.i("Debug", String.format("h: %f s: %f", distance, radiusSum));

            //verifica distancia entre os raios
            if (distance <= radiusSum){
                return true;
            }
        }
        return false;
    }

}
