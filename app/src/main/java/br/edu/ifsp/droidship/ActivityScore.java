package br.edu.ifsp.droidship;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.edu.ifsp.droidship.game.GameHelper;


/**
 * Created by Eduardo on 13/05/2016.
 */
public class ActivityScore extends Activity implements View.OnClickListener {

    private EditText edtNome;
    private EditText edtScore;
    private Button btnInserir;
    private Button btnPlayAgain;
    private Button btnSair;
    private int totalScore;
    private byte[] bitmapEndGame;

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

        Intent intent = getIntent();

        if (intent.hasExtra(GameHelper.KEY_SCORE)) {
            totalScore = intent.getIntExtra(GameHelper.KEY_SCORE, 0);
            edtScore.setText(String.valueOf(totalScore));
        }

        if (intent.hasExtra(GameHelper.KEY_BITMAP)){
            bitmapEndGame = intent.getByteArrayExtra(GameHelper.KEY_BITMAP);
            Log.i("Debug", "Tem KeyBitmap em ActivityScore");
        }

        // Omitir o botão sair
        btnSair.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {

        if ( v == btnPlayAgain){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }else if( v == btnInserir){
            Intent intent = new Intent(this, ActivityListScore.class);
            intent.putExtra(GameHelper.KEY_NAME, edtNome.getText().toString());
            intent.putExtra(GameHelper.KEY_SCORE, edtScore.getText().toString());
            intent.putExtra(GameHelper.KEY_BITMAP, bitmapEndGame);
            startActivity(intent);
        }else{
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

    }
}
