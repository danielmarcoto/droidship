package br.edu.ifsp.droidship.game;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.media.SoundPool;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.io.ByteArrayOutputStream;

import br.edu.ifsp.droidship.ActivityScore;
import br.edu.ifsp.droidship.R;

/**
 * Created by danielmarcoto on 13/04/16.
 */
public class DroidShip extends SurfaceView implements Runnable,
        View.OnTouchListener, ExplosionDelegate,
        SoundPool.OnLoadCompleteListener, SensorEventListener {

    private final SurfaceHolder holder = getHolder();

    private OnGameEndDelegate gameEndDelegate;
    private GestureDetector gestureDetector;
    private EndlessEnemies endlessEnemies;
    private Spaceship spaceship;
    private Control control;
    private ScreenHelper screenHelper;
    private CollisionDetector collisionDetector;
    private BackgroundStarsGame backgroundStarsGame;
    private Timer timer;
    private Score score;
    private Explosions explosions;
    private Sound sound;
    private Context context;
    private Bitmap background;

    private GameControlMode gameControlMode;

    private boolean isRunning;
    private Bitmap bitmapEndGame;

    public DroidShip(Context context, AttributeSet attributeSet){
        super(context, attributeSet);

        this.context = context;

        setClickable(true);
        setOnTouchListener(this);

        initialize();
    }

    public DroidShip(Context context){
        super(context);

        this.context = context;

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

        backgroundStarsGame = new BackgroundStarsGame(context, screenHelper);

        timer = new Timer();

        score = new Score(timer);

        explosions = new Explosions(context, sound);
        explosions.setDelegate(this);

        // Define um padrão para interação do jogo
        gameControlMode = GameControlMode.Touch;

        // Inclusão da imagem de fundo
        Bitmap bitmap = BitmapFactory.decodeResource(
                context.getResources(), R.drawable.background1);
        background = Bitmap
                .createScaledBitmap(bitmap, bitmap.getWidth(), screenHelper.getHeight(), false);
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

            canvas.drawBitmap(background, 0, 0, new Paint());
            backgroundStarsGame.drawNode(canvas);

            if (gameControlMode == GameControlMode.Touch) {
                control.drawNode(canvas);
            }

            endlessEnemies.drawNode(canvas);
            score.drawScore(canvas);
            spaceship.drawNode(canvas);
            explosions.drawNode(canvas);

            // Sorteia criação de inimigos
            endlessEnemies.randomCreateEnemy(timer);

            endlessEnemies.falling();
            backgroundStarsGame.move();

            timer.incrementIfAble();

            // Verifica se há colisão e encerra quando colide
            if (collisionDetector.hasHit()){
                if (gameEndDelegate != null) {
                    bitmapEndGame = gameEndDelegate.captureScreen();
                }
                explosions.addExplosion(spaceship);
                spaceship.setAlpha(0);
                timer.stop();
            }

            holder.unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        if (gameControlMode == GameControlMode.Touch &&
                motionEvent.getY() > control.getY())
            gestureDetector.onTouchEvent(motionEvent);

        return false;
    }

    public void recordScore(){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmapEndGame.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        Intent intent = new Intent(context, ActivityScore.class);
        intent.putExtra(GameHelper.KEY_BITMAP, byteArray);
        intent.putExtra(GameHelper.KEY_SCORE, score.getScore());
        context.startActivity(intent);
    }

    @Override
    public void explosionHasEnded(boolean isSpaceship) {
        // Quando for colisão da nave principal termina o jogo
        if (isSpaceship){

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

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (gameControlMode != GameControlMode.Accelerometer) return;

        // atualizar o valor da nave pelo sensor do acelerometro
        //spaceship.setX(sensorEvent.values[0]);
        //spaceship.setY(sensorEvent.values[1]);

        //final int noise = 1;

        float xAccelerometer = sensorEvent.values[0];
        float yAccelerometer = sensorEvent.values[1];

        //Log.i("Debug", "Accelerometer X: " + xAccelerometer);
        //Log.i("Debug", "Accelerometer Y: " + yAccelerometer);

        float newX = spaceship.getX() - (xAccelerometer * 2);
        float newY = spaceship.getY() + (yAccelerometer * 2);

        //if (xAccelerometer < -noise)

        if (!spaceship.isOutOfScreenLeft() && spaceship.getX() > newX)
            spaceship.setX(newX);

        if (!spaceship.isOutOfScreenRight() && spaceship.getX() < newX)
            spaceship.setX(newX);

        if (!spaceship.isOutOfScreenTop() && spaceship.getY() > newY)
            spaceship.setY(newY);

        if (!spaceship.isOutOfScreenBottom() && spaceship.getY() < newY)
            spaceship.setY(newY);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        //
    }

    public OnGameEndDelegate getGameEndDelegate() {
        return gameEndDelegate;
    }

    public void setGameEndDelegate(OnGameEndDelegate gameEndDelegate) {
        this.gameEndDelegate = gameEndDelegate;
    }

    public void setGameControlMode(GameControlMode gameControlMode) {
        this.gameControlMode = gameControlMode;
    }

    public enum GameControlMode {
        Touch,
        Accelerometer
    }
}
