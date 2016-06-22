package br.edu.ifsp.droidship;

import android.app.AlertDialog;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import br.edu.ifsp.droidship.dataBase.DataBase;
import br.edu.ifsp.droidship.dataBase.ScoreRepository;
import br.edu.ifsp.droidship.game.DroidShip;
import br.edu.ifsp.droidship.game.GameHelper;
import br.edu.ifsp.droidship.game.OnGameEndDelegate;

public class MainActivity extends AppCompatActivity implements OnGameEndDelegate {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    private DataBase dataBase;
    private SQLiteDatabase conn;
    private ScoreRepository scoreRepository;
    private DroidShip droidShip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DroidShip.GameControlMode gameControlMode = DroidShip.GameControlMode.Touch;

        if (getIntent().hasExtra(GameHelper.KEY_GAME_MODE)) {
            gameControlMode = (DroidShip.GameControlMode)getIntent()
                    .getSerializableExtra(GameHelper.KEY_GAME_MODE);
        }

        //droidShip = (DroidShip) findViewById(R.id.droidship);
        droidShip = new DroidShip(this);
        droidShip.setGameEndDelegate(this);
        droidShip.setGameControlMode(gameControlMode);

        FrameLayout container = (FrameLayout) findViewById(R.id.container);
        container.addView(droidShip);

        // Inicializar o acelerometro
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

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
        
        mSensorManager
                .registerListener(droidShip, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);

        droidShip.resume();

        new Thread(droidShip).start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        mSensorManager.unregisterListener(droidShip);

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

    @Override
    public Bitmap captureScreen() {
        // create bitmap screen capture
        //View v1 = getWindow().getDecorView().getRootView();
        View v1 = droidShip.getRootView();
        v1.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
        //v1.setDrawingCacheEnabled(false);
        return  bitmap;
    }
}
