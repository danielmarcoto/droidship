package br.edu.ifsp.droidship.dataBase;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Eduardo on 07/05/2016.
 */
public class ScoreRepository {
    private SQLiteDatabase conn;

    public ScoreRepository(SQLiteDatabase conn){
        this.conn = conn;
    }

    public void addScore(int score){

        ContentValues values = new ContentValues();

        //values.put("NOME"             ,      score.nome);
        values.put("PONTOS"           ,      score);

        conn.insertOrThrow("HIGHSCORE", null, values);

    }

}
