package br.edu.ifsp.droidship;

import android.content.ContentValues;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.List;

import br.edu.ifsp.droidship.dataBase.DataBase;
import br.edu.ifsp.droidship.dataBase.ScoreAdapter;
import br.edu.ifsp.droidship.dataBase.ScoreModel;
import br.edu.ifsp.droidship.dataBase.ScoreRepository;

public class ActivityListScore extends AppCompatActivity {

    private ListView lstScore;
    private DataBase dataBase;
    private SQLiteDatabase conn;
    private String nome;
    private String score;
    private List<ScoreModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitylistscore);

        Intent intent = getIntent();

        nome = intent.getStringExtra("NOME");
        score = intent.getStringExtra("SCORE");

        try {
            dataBase = new DataBase(this);
            conn = dataBase.getWritableDatabase();
            ScoreRepository scoreRepository = new ScoreRepository(conn);
            addScore();

            list = scoreRepository.listAll();

            //adpScore = scoreRepository.findScore(this);
            //lstScore.setAdapter(adpScore);


        }catch (SQLException ex){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro na conexão");
            dlg.setNeutralButton("Ok", null);
            dlg.show();
        }

        lstScore = (ListView) findViewById(R.id.lstScore);
        lstScore.setAdapter(new ScoreAdapter(this, list));
    }

    public void addScore(){

        ContentValues values = new ContentValues();

        values.put("NOME", nome );
        values.put("PONTOS", score );

        conn.insertOrThrow("HIGHSCORE", null, values);

    }


}
