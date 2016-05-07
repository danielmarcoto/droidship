package br.edu.ifsp.droidship.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Eduardo on 07/05/2016.
 */
public class DataBase extends SQLiteOpenHelper{
    public DataBase(Context context){
        super(context,"HIGHSCORE",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ScriptSQL.getCreateScore());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
