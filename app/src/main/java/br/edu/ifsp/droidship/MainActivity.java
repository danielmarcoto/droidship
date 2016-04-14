package br.edu.ifsp.droidship;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    DroidShip view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //logica do jogo
        view = new DroidShip(this);
        //configura view
        setContentView(view);
    }

    protected void onResume(){
        super.onResume();
        view.resume();
    }
}
