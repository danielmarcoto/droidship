package br.edu.ifsp.droidship;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import br.edu.ifsp.droidship.dataBase.DataBase;
import br.edu.ifsp.droidship.dataBase.ScoreRepository;

public class ActivityListScore extends AppCompatActivity {

    private ListView lstScore;
    private DataBase dataBase;
    private SQLiteDatabase conn;
    private ArrayAdapter<String> adpScore;
    private ScoreRepository scoreRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitylistscore);

        lstScore = (ListView) findViewById(R.id.lstScore);
        dataBase = new DataBase(this);
        conn = dataBase.getWritableDatabase();

        scoreRepository = new ScoreRepository(conn);
        adpScore = scoreRepository.findScore(this);

    }
}
