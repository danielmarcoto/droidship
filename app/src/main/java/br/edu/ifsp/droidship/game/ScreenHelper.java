package br.edu.ifsp.droidship.game;

import android.util.DisplayMetrics;
import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by danielmarcoto on 09/03/16.
 */
public class ScreenHelper {

    private DisplayMetrics metrics;

    public ScreenHelper(Context context){
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);

        Display display = wm.getDefaultDisplay();

        metrics = new DisplayMetrics();

        display.getMetrics(metrics);
    }

    public int getHeight(){
        return metrics.heightPixels;
    }

    public int getWidth(){
        return metrics.widthPixels;
    }
}

