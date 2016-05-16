package br.edu.ifsp.droidship.game;

import android.content.Context;
import android.graphics.Canvas;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by danielmarcoto on 14/05/16.
 */
public class Explosions extends DrawObject {
    private Context context;
    private HashMap<DrawObject, Explosion> explosionHashMap;
    private ExplosionDelegate delegate;

    public Explosions(Context context, ExplosionDelegate delegate){
        super(context);

        this.context = context;
        this.explosionHashMap = new HashMap<>();
        this.delegate = delegate;
    }

    public void addExplosion(DrawObject objectExploded){
        Explosion explosion = new Explosion(this.context,
                objectExploded.getX(), objectExploded.getY());
        if (!explosionHashMap.containsKey(objectExploded))
            explosionHashMap.put(objectExploded, explosion);
    }

    @Override
    public void drawNode(Canvas canvas) {
        Iterator<DrawObject> iterator = explosionHashMap.keySet().iterator();

        while(iterator.hasNext()){
            DrawObject drawObject = iterator.next();

            Explosion explosion = explosionHashMap.get(drawObject);
            explosion.drawNode(canvas);

            if (explosion.hasFinished()){
                explosionHashMap.remove(drawObject);

                delegate.explosionHasEnded(drawObject instanceof Spaceship);
            }
        }
    }


}
