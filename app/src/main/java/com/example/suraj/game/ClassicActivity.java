package com.example.suraj.game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ClassicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classic);
        SceneManager.ACTIVE_SCENE=1;

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this,GameModeActivity.class);
        startActivity(i);
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
}
