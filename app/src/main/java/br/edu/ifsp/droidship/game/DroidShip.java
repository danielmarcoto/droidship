package br.edu.ifsp.droidship.game;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.SoundPool;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import br.edu.ifsp.droidship.ActivityScore;

/**
 * Created by danielmarcoto on 13/04/16.
 */
public class DroidShip extends SurfaceView implements Runnable,
        View.OnTouchListener, ExplosionDelegate, SoundPool.OnLoadCompleteListener {

    private final SurfaceHolder holder = getHolder();

    private GestureDetector gestureDetector;
    private EndlessEnemies endlessEnemies;
    private Spaceship spaceship;
    private Control control;
    private ScreenHelper screenHelper;
    private CollisionDetector collisionDetector;
    private BackgroundGame backgroundGame;
    private Timer timer;
    private Score score;
    private Explosions explosions;
    private Sound sound;
    private Context context;


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

        sound = new Sound(context, this);

        endlessEnemies = new EndlessEnemies(context, screenHelper, sound);

        control = new Control(context, screenHelper);

        spaceship = new Spaceship(context, screenHelper);

        // Detector de gestos complexos
        GestureDetector.SimpleOnGestureListener gestureListener = new ShipControl(spaceship);
        gestureDetector = new GestureDetector(context, gestureListener);

        collisionDetector = new CollisionDetector(endlessEnemies, spaceship);

        backgroundGame = new BackgroundGame(context, screenHelper);

        timer = new Timer();

        score = new Score(timer);

        explosions = new Explosions(context, sound);
        explosions.setDelegate(this);
    }

    public void pause(){
        isRunning = false;

        sound.pauseBackgroundSound();
    }

    public void resume(){
        isRunning = true;

        sound.playBackgroundSound();
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
            explosions.drawNode(canvas);

            // Sorteia criação de inimigos
            endlessEnemies.randomCreateEnemy(timer);

            endlessEnemies.falling();
            backgroundGame.move();

            timer.increment();

            // Verifica se há colisão e encerra quando colide
            if (collisionDetector.hasHit()){
                explosions.addExplosion(spaceship);
                spaceship.setAlpha(0);

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

        // TODO: Passar para a próxima activity a pontuação atual

        Intent intent = new Intent(context, ActivityScore.class);
        intent.putExtra("SCORE", score.getScore());
        context.startActivity(intent);
        //Context context = getContext();
        //context.startActivity(new Intent(context, ActivityScore.class));

    }

    @Override
    public void explosionHasEnded(boolean isSpaceship) {
        if (isSpaceship){
            // new Gameover(context, screenHelper).drawNode(canvas);

            isRunning = false;

            recordScore();
        }
    }

    @Override
    public void onLoadComplete(SoundPool soundPool, int i, int i1) {
        if (i == Sound.NEW_ENEMY) {
            Log.i("Debug", "onLoadComplete: NEW_ENEMY");
        }
        if (i == Sound.SPACESHIP_EXPLODE) {
            Log.i("Debug", "onLoadComplete: SPACESHIP_EXPLODE");
        }
    }
}
