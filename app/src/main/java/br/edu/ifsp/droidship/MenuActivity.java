package br.edu.ifsp.droidship;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import br.edu.ifsp.droidship.game.GameHelper;

import static br.edu.ifsp.droidship.game.DroidShip.GameControlMode.Accelerometer;
import static br.edu.ifsp.droidship.game.DroidShip.GameControlMode.Touch;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void playGameByTouch(View view){
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        intent.putExtra(GameHelper.KEY_GAME_MODE, Touch);
        startActivity(intent);
    }

    public void playGameByAccelerometer(View view){
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        intent.putExtra(GameHelper.KEY_GAME_MODE, Accelerometer);
        startActivity(intent);
    }

    public void highScore(View view) {
        Intent intent = new Intent(getBaseContext(), ActivityListScore.class);
        startActivity(intent);
    }
}
