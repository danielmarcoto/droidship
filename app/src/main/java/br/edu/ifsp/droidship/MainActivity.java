package br.edu.ifsp.droidship;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.edu.ifsp.droidship.game.DroidShip;

public class MainActivity extends AppCompatActivity {

    private DroidShip droidShip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        droidShip = (DroidShip) findViewById(R.id.droidship);
    }

    @Override
    protected void onResume() {
        super.onResume();
        droidShip.retomar();

        new Thread(droidShip).start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        droidShip.pausar();
    }
}
