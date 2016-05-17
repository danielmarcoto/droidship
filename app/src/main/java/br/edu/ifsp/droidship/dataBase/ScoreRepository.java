package br.edu.ifsp.droidship.dataBase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eduardo on 07/05/2016.
 */
public class ScoreRepository {
    private SQLiteDatabase conn;

    public ScoreRepository(SQLiteDatabase conn){
        this.conn = conn;
    }

    public List<ScoreModel> listAll(){
        List<ScoreModel> list = new ArrayList<>();
        Cursor cursor = conn.query("HIGHSCORE", null, null, null, null, null, "PONTOS DESC");
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                ScoreModel model = new ScoreModel();
                model.setName(cursor.getString(1));
                model.setScore(cursor.getString(2));
                list.add(model);
            }while (cursor.moveToNext());
        }
        return list;
    }

}
