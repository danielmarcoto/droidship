package br.edu.ifsp.droidship.game;

/**
 * Created by danielmarcoto on 01/05/16.
 */
public class Timer {
    private double timer;

    public Timer(){
        timer = 0;
    }

    public void increment(){
        timer += 0.1;
    }

    public double getTimer(){
        return timer;
    }
}
