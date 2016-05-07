package br.edu.ifsp.droidship;

import android.app.AlertDialog;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import br.edu.ifsp.droidship.dataBase.DataBase;
import br.edu.ifsp.droidship.dataBase.ScoreRepository;
import br.edu.ifsp.droidship.game.DroidShip;
import br.edu.ifsp.droidship.game.Score;

public class MainActivity extends AppCompatActivity {
    private DataBase dataBase;
    private SQLiteDatabase conn;
    private ScoreRepository scoreRepository;
    private DroidShip droidShip;
    private Score score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        droidShip = (DroidShip) findViewById(R.id.droidship);

        try {
            dataBase = new DataBase(this);
            conn = dataBase.getWritableDatabase();

            scoreRepository = new ScoreRepository(conn);


        }catch (SQLException ex){

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao criar o banco: "+ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();

        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        droidShip.resume();

        new Thread(droidShip).start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        droidShip.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (conn != null)
        {
            conn.close();
        }
    }

    private void save(){

        try {
              scoreRepository.addScore(score);
                Toast.makeText(this, "Dados incluídos com sucesso", Toast.LENGTH_LONG).show();

            }catch(Exception ex){

               AlertDialog.Builder dlg = new AlertDialog.Builder(this);
               dlg.setMessage("Erro ao inserir os dados: "+ex.getMessage());
               dlg.setNeutralButton("OK", null);
               dlg.show();
        }

    }

}
