package br.edu.ifsp.droidship.game;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by danielmarcoto on 13/04/16.
 */
public class DroidShip extends SurfaceView implements Runnable, View.OnDragListener  {
    private Context context;
    private Spaceship spaceship;
    private final SurfaceHolder holder = getHolder();
    private ScreenHelper screenHelper;

    private boolean isRunning;

    public DroidShip(Context context, AttributeSet attributeSet){
        super(context, attributeSet);

        this.context = context;

        initialize();

        setOnDragListener(this);

        DragShadowBuilder a = new DragShadowBuilder();


        //boolean isDragStart = this.startDrag()
    }

    private void initialize(){

        screenHelper = new ScreenHelper(context);
        // TODO: Mover o cálculo para outro local

        float yNave = (screenHelper.getHeight() / 5) * 3;
        float xNave = (screenHelper.getLarguraWidth() / 2);

        this.spaceship = new Spaceship(xNave, yNave);
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

            // TODO: Movimento dos elementos do jogo
            spaceship.draw(canvas);


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

    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {

        Log.i("Debug", "Drag: " + dragEvent.getAction());

        if (dragEvent.getAction() == DragEvent.ACTION_DRAG_STARTED){
            Log.i("Debug", "Drag Started");
        }

        if (dragEvent.getAction() == DragEvent.ACTION_DRAG_ENDED){
            Log.i("Debug", "Drag Ended");
        }
        return false;
    }
}
