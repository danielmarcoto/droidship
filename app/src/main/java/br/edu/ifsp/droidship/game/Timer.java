package br.edu.ifsp.droidship.game;

/**
 * Created by danielmarcoto on 01/05/16.
 */
public class Timer {
    private double timer;
    private boolean isRunning;

    public Timer(){
        timer = 0;
        isRunning = true;
    }

    public void incrementIfAble(){
        if (isRunning)
            timer += 0.1;
    }

    public double getTimer(){
        return timer;
    }

    public void stop(){
        isRunning = false;
    }
}
