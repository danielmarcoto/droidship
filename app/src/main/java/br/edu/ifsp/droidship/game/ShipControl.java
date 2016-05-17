package br.edu.ifsp.droidship.game;

import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by danielmarcoto on 25/04/16.
 */
public class ShipControl extends GestureDetector.SimpleOnGestureListener {

    private Spaceship spaceship;

    public ShipControl(Spaceship spaceship) {
        this.spaceship = spaceship;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        float newX = spaceship.getX() - (distanceX) * 3;
        float newY = spaceship.getY() - (distanceY) * 3;

        if (!spaceship.isOutOfScreenLeft() && spaceship.getX() > newX)
            spaceship.setX(newX);

        if (!spaceship.isOutOfScreenRight() && spaceship.getX() < newX)
            spaceship.setX(newX);

        if (!spaceship.isOutOfScreenTop() && spaceship.getY() > newY)
            spaceship.setY(newY);

        if (!spaceship.isOutOfScreenBottom() && spaceship.getY() < newY)
            spaceship.setY(newY);

        return super.onScroll(e1, e2, distanceX, distanceY);
    }
}
