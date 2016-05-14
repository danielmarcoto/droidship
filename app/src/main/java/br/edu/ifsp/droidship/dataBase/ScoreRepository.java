package br.edu.ifsp.droidship.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import br.edu.ifsp.droidship.game.Score;

/**
 * Created by Eduardo on 07/05/2016.
 */
public class ScoreRepository {
    private SQLiteDatabase conn;

    public ScoreRepository(SQLiteDatabase conn){
        this.conn = conn;
    }

    public ArrayAdapter<String> findScore(Context context){
        ArrayAdapter<String> adpScore = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1);
        Cursor cursor = conn.query("HIGHSCORE", null, null, null, null, null, null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                String nome = cursor.getString(1);
                adpScore.add(nome);
            }while (cursor.moveToNext());
        }
        return adpScore;
    }

    public void addScore(){

        ContentValues values = new ContentValues();

        values.put("NOME", "Eduardo");
        //values.put("PONTOS"           , 10000);

        conn.insertOrThrow("HIGHSCORE", null, values);

    }

}
