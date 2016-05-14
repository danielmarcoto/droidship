package br.edu.ifsp.droidship.game;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import br.edu.ifsp.droidship.ActivityScore;
import br.edu.ifsp.droidship.MainActivity;

/**
 * Created by danielmarcoto on 13/04/16.
 */
public class DroidShip extends SurfaceView implements Runnable, View.OnTouchListener {

    private final SurfaceHolder holder = getHolder();

    private GestureDetector gestureDetector;
    private EndlessEnemies endlessEnemies;
    private Context context;
    private Spaceship spaceship;
    private Control control;
    private ScreenHelper screenHelper;
    private CollisionDetector collisionDetector;
    private BackgroundGame backgroundGame;
    private Timer timer;
    private Score score;

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

        endlessEnemies = new EndlessEnemies(context, screenHelper);

        control = new Control(context, screenHelper);

        spaceship = new Spaceship(context, screenHelper);

        GestureDetector.SimpleOnGestureListener gestureListener = new ShipControl(spaceship);

        gestureDetector = new GestureDetector(context, gestureListener);

        collisionDetector = new CollisionDetector(endlessEnemies, spaceship);

        backgroundGame = new BackgroundGame(context, screenHelper);

        timer = new Timer();

        score = new Score(timer);
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

            backgroundGame.drawNode(canvas);
            spaceship.drawNode(canvas);
            control.drawNode(canvas);
            endlessEnemies.drawNode(canvas);
            score.drawScore(canvas);

            // Sorteia criação de inimigos
            endlessEnemies.randomCreateEnemy(timer);

            endlessEnemies.falling();
            backgroundGame.move();

            timer.increment();

            // Verifica se há colisão e encerra quando colide
            if (collisionDetector.hasHit()){
                new Gameover(context, screenHelper).drawNode(canvas);

                isRunning = false;

                recordScore();//abre a tela de cadastro do recorde

            }

            holder.unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        if (motionEvent.getY() > control.getY())
            gestureDetector.onTouchEvent(motionEvent);

        return false;
    }

    public void recordScore(){
        Context context = getContext();
        context.startActivity(new Intent(context, ActivityScore.class));

    }
}
