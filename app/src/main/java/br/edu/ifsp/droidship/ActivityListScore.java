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

        try {
            dataBase = new DataBase(this);
            conn = dataBase.getWritableDatabase();
            scoreRepository = new ScoreRepository(conn);
            scoreRepository.addScore();
            adpScore = scoreRepository.findScore(this);
            lstScore.setAdapter(adpScore);

        }catch (SQLException ex){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro na conexão");
            dlg.setNeutralButton("Ok", null);
            dlg.show();

        }

    }
}
