package br.edu.ifsp.droidship.game;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;

/**
 * Created by danielmarcoto on 13/04/16.
 */
public class Control extends DrawObject implements View.OnDragListener
{
    private static final String TAG = "tag_name";
    private ScreenHelper screenHelper;
    private Context context;

    public Control(Context context, ScreenHelper screenHelper){
        super(context);

        this.context = context;
        this.screenHelper = screenHelper;

        setY((screenHelper.getHeight() / 5) * 3);
        setTag(TAG);

        setOnDragListener(this);
    }

    @Override
    public void drawNode(Canvas canvas) {

        ClipData.Item item = new ClipData.Item(TAG);

        ClipData clipData = new ClipData(
                new ClipDescription("desc",
                        new String[] { ClipDescription.MIMETYPE_TEXT_PLAIN }), item);

        View.DragShadowBuilder myShadow = new DragShadowBuilder(this);

        // TODO: corrigir a forma de detectar o toque movendo para direita e esquerda:
        // http://developer.android.com/intl/pt-br/guide/topics/ui/drag-drop.html
        boolean isDrag = startDrag(clipData, myShadow, null, 0);

        //Log.i("Debug", "isDrag; " + String.valueOf(isDrag));

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAlpha(3);

        canvas.drawRect(0, getY(),
                screenHelper.getWidth(),
                screenHelper.getHeight(), paint);
    }

    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {

        Log.i("Debug", "Drag: " + dragEvent.getAction());

        if (dragEvent.getAction() == DragEvent.ACTION_DRAG_STARTED){
            Log.i("Debug", "Drag Started");
        }

        if (dragEvent.getAction() == DragEvent.ACTION_DRAG_ENTERED){
            Log.i("Debug", "Drag Entered");
        }

        if (dragEvent.getAction() == DragEvent.ACTION_DRAG_ENDED){
            Log.i("Debug", "Drag Ended");
        }

        return false;
    }
}
