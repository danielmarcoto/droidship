package br.edu.ifsp.droidship.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.edu.ifsp.droidship.game.Score;

/**
 * Created by Eduardo on 07/05/2016.
 */
public class ScoreRepository {
    private SQLiteDatabase conn;

    public ScoreRepository(SQLiteDatabase conn){
        this.conn = conn;
    }

    public void addScore(Score score){

        ContentValues values = new ContentValues();

        //values.put("NOME"             ,      score.nome);
        values.put("PONTOS"           ,      score.score);

        conn.insertOrThrow("HIGHSCORE", null, values);

    }

}
