package br.edu.ifsp.droidship;

import android.app.AlertDialog;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import br.edu.ifsp.droidship.dataBase.DataBase;
import br.edu.ifsp.droidship.dataBase.ScoreRepository;
import br.edu.ifsp.droidship.game.DroidShip;

public class MainActivity extends AppCompatActivity {
    private DataBase dataBase;
    private SQLiteDatabase conn;
    private ScoreRepository scoreRepository;
    private DroidShip droidShip;


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
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);

        Log.i("Debug", "Memory alert in " + level);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // TODO: Salvar o estado do jogo para continuar
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


}
