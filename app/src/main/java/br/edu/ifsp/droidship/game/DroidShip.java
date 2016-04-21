package br.edu.ifsp.droidship.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.Random;
import android.util.Log;
import android.view.DragEvent;

/**
 * Created by danielmarcoto on 13/04/16.
 */
public class DroidShip extends SurfaceView implements Runnable  {

    private Enemy enemy;
    private EndlessEnemies endlessEnemies;
    private Random random = new Random();

    private final SurfaceHolder holder = getHolder();

    private Context context;
    private Spaceship spaceship;
    private Control control;
    private ScreenHelper screenHelper;

    private boolean isRunning;

    public DroidShip(Context context, AttributeSet attributeSet){
        super(context, attributeSet);

        this.context = context;

        initialize();
    }

    private void initialize(){

        float x = random.nextInt(600);
        float y = 0;
        float radius = random.nextInt(50 - 20) + 20;

        enemy = new Enemy(x, y, radius);

        endlessEnemies = new EndlessEnemies(x, y, radius);

        screenHelper = new ScreenHelper(context);

        control = new Control(context, screenHelper);

        spaceship = new Spaceship(context, screenHelper);

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


            holder.unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public boolean onDragEvent(DragEvent dragEvent) {

        Log.i("Debug", "Drag: " + dragEvent.getAction());

        if (dragEvent.getAction() == DragEvent.ACTION_DRAG_STARTED){
            Log.i("Debug", "Drag Started");
        }

        if (dragEvent.getAction() == DragEvent.ACTION_DRAG_ENDED){
            Log.i("Debug", "Drag Ended");
        }

        if (dragEvent.getAction() == DragEvent.ACTION_DROP){
            Log.i("Debug", "Drag Drop");
        }

        return super.onDragEvent(dragEvent);
    }
}
