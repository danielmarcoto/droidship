package br.edu.ifsp.droidship;

import android.content.ContentValues;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

import java.io.ByteArrayInputStream;
import java.util.List;

import br.edu.ifsp.droidship.dataBase.DataBase;
import br.edu.ifsp.droidship.dataBase.ScoreAdapter;
import br.edu.ifsp.droidship.dataBase.ScoreModel;
import br.edu.ifsp.droidship.dataBase.ScoreRepository;
import br.edu.ifsp.droidship.game.GameHelper;

public class ActivityListScore extends AppCompatActivity {

    private ListView lstScore;
    private DataBase dataBase;
    private SQLiteDatabase conn;
    private String nome;
    private String score;
    private List<ScoreModel> list;
    private Bitmap bitmapEndGame;
    private Button shareButton;

    private CallbackManager callbackManager;
    private ShareDialog shareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitylistscore);

        shareButton = (Button)findViewById(R.id.shareButton);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);

        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {
                Log.i("Debug", "Sucesso, compartilhado no facebook");
            }

            @Override
            public void onCancel() {
                Log.i("Debug", "Cancelado, compartilhado no facebook");
            }

            @Override
            public void onError(FacebookException error) {
                Log.i("Debug", "Erro!!! Compartilhado no facebook");
            }
        });

        Intent intent = getIntent();

        // Obtem a imagem capturada anteriormente
        if (intent.hasExtra(GameHelper.KEY_BITMAP)){
            byte[] byteArray = intent.getByteArrayExtra(GameHelper.KEY_BITMAP);
            ByteArrayInputStream stream = new ByteArrayInputStream(byteArray);
            bitmapEndGame = BitmapFactory.decodeStream(stream);
        }

        if (intent.hasExtra(GameHelper.KEY_SCORE) &&
                intent.hasExtra(GameHelper.KEY_NAME)){
            score = intent.getStringExtra(GameHelper.KEY_SCORE);
            nome = intent.getStringExtra(GameHelper.KEY_NAME);
            Log.i("Debug", "Tem chave de score/name");
        } else {
            // Esconde o botão caso não tenha valor para exibir
            shareButton.setVisibility(View.INVISIBLE);
        }

        try {
            dataBase = new DataBase(this);
            conn = dataBase.getWritableDatabase();
            ScoreRepository scoreRepository = new ScoreRepository(conn);

            // Salva apenas se não for vazio a pontuação
            if (score != null && !score.isEmpty())
                addScore();

            list = scoreRepository.listAll();

        } catch (SQLException ex){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro na conexão");
            dlg.setNeutralButton("Ok", null);
            dlg.show();
        }

        lstScore = (ListView) findViewById(R.id.lstScore);
        lstScore.setAdapter(new ScoreAdapter(this, list));

        // Apenas para testar
        if (score == null){

            score = "300";
            nome = "Daniel Marcoto";
        }


    }

    public void backButton(View view) {
        Intent intent = new Intent(getBaseContext(), MenuActivity.class);
        startActivity(intent);
    }

    public void addScore(){

        ContentValues values = new ContentValues();

        values.put("NOME", nome );
        values.put("PONTOS", score );

        conn.insertOrThrow("HIGHSCORE", null, values);

    }

    public void onShare(View view){

        Log.i("Debug", "Chamou método de compartilhar!!!");

        String description = String.format(
                "E aí galera, consegui fazer %s pontos no DROIDSHIP, querem tentar?", score);
        /*
        SharePhoto sharePhoto = new SharePhoto.Builder()
                .setBitmap(bitmapEndGame)
                .setCaption(description)
                .setUserGenerated(true)
                .build();

        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(sharePhoto)
                .build();
        */
        //if (ShareDialog.canShow(ShareLinkContent.class)) {
        if (ShareDialog.canShow(SharePhotoContent.class)) {

            ShareLinkContent content = new ShareLinkContent.Builder()
                    .setContentTitle("Compartilhar")
                    .setContentUrl(Uri.parse("https://sbv.ifsp.edu.br/"))
                    .setContentDescription(description)
                    .build();

            shareDialog.show(content, ShareDialog.Mode.AUTOMATIC);

            Log.i("Debug", "Chamou o diálogo");
        }
    }


}
