package br.edu.ifsp.droidship.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.Random;

import android.view.View;

/**
 * Created by danielmarcoto on 13/04/16.
 */
public class DroidShip extends SurfaceView implements Runnable, View.OnTouchListener {

    private GestureDetector gestureDetector;
    private Enemy enemy;
    private EndlessEnemies endlessEnemies;
    private Random random = new Random();
    private float x ;
    private float y ;
    private float radius;
    private final SurfaceHolder holder = getHolder();
    private Context context;
    private Spaceship spaceship;
    private Control control;
    private ScreenHelper screenHelper;
    private CollisionDetector collisionDetector;

    private boolean isRunning;

    public DroidShip(Context context, AttributeSet attributeSet){
        super(context, attributeSet);

        this.context = context;

        setFocusable(true);
        setClickable(true);

        setOnTouchListener(this);

        initialize();
    }

    private void initialize(){

        screenHelper = new ScreenHelper(context);

        enemy = new Enemy(x, y, radius);

        endlessEnemies = new EndlessEnemies(screenHelper, x, y, radius);

        control = new Control(context, screenHelper);

        spaceship = new Spaceship(context, screenHelper);

        GestureDetector.SimpleOnGestureListener gestureListener = new ShipControl(spaceship);

        gestureDetector = new GestureDetector(context, gestureListener);

        collisionDetector = new CollisionDetector(endlessEnemies, spaceship);
    }

    public void pause(){
        isRunning = false;
    }

    public void resume(){
        isRunning = true;
    }

    @Override
    public void run() {
        while(isRunning){
            if (!holder.getSurface().isValid()) continue;

            Canvas canvas = holder.lockCanvas();

            canvas.drawColor(Color.BLACK);

            // TODO: Movimento dos elementos do jogo

            spaceship.drawNode(canvas);
            control.drawNode(canvas);
            endlessEnemies.drawNode(canvas);
            endlessEnemies.falling();
            collisionDetector.collision(canvas);
            if(collisionDetector.gameover){
                break;
            }


            holder.unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        if (motionEvent.getY() > control.getY())
            gestureDetector.onTouchEvent(motionEvent);

        //Log.i("Debug", "onTouch - DroidShip");

        return false;
    }
}
