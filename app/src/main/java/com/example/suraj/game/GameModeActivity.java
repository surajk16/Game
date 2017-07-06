package com.example.suraj.game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameModeActivity extends AppCompatActivity implements View.OnClickListener {

    TextView t5,t6,t7;
    public static GamePanel gamePanel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode);
        gamePanel = new GamePanel(this);


        t5 = (TextView) findViewById(R.id.textView5);   t5.setOnClickListener(this);
        t6 = (TextView) findViewById(R.id.textView6);   t6.setOnClickListener(this);
        t7 = (TextView) findViewById(R.id.textView7);   t7.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.textView5:
                Intent i = new Intent(this,ClassicActivity.class);
                startActivity(i);
                break;

            case R.id.textView6:
                i = new Intent(this,ArcadeActivity.class);
                startActivity(i);
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        SceneManager.ACTIVE_SCENE = 0;
    }

    @Override
    protected void onPause() {
        super.onPause();
        MainActivity.mediaPlayer.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MainActivity.mediaPlayer.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent (this,MainActivity.class);
        startActivity(i);
    }
}
