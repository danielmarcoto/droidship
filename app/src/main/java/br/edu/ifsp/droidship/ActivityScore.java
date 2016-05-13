package br.edu.ifsp.droidship;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ifsp.droidship.dataBase.DataBase;
import br.edu.ifsp.droidship.dataBase.ScoreRepository;
import br.edu.ifsp.droidship.game.Score;

/**
 * Created by Eduardo on 13/05/2016.
 */
public class ActivityScore extends Activity implements View.OnClickListener {

    private EditText edtNome;
    private EditText edtScore;
    private Button btnInserir;
    private Button btnPlayAgain;
    private Button btnSair;
    private DataBase dataBase;
    private SQLiteDatabase conn;
    private ScoreRepository scoreRepository;
    private Score score ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityscore);

        edtNome      = (EditText) findViewById(R.id.editText);
        edtScore     = (EditText) findViewById(R.id.editText2);
        btnInserir   = (Button) findViewById(R.id.btnInserir);
        btnPlayAgain = (Button) findViewById(R.id.btnPlayAgain);
        btnSair      = (Button) findViewById(R.id.btnSair);

        btnPlayAgain.setOnClickListener(this);
        btnInserir.setOnClickListener(this);
        btnSair.setOnClickListener(this);
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


    @Override
    public void onClick(View v) {

        if ( v == btnPlayAgain){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }else if( v == btnInserir){
            save();
            Intent intent = new Intent(this, ActivityListScore.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

    }
}
