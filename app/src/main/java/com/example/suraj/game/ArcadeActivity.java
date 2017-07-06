package com.example.suraj.game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ArcadeActivity extends AppCompatActivity {

    public static ProgressBar progressBar;
    public static int ACTIVITY_STARTED = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arcade);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        ACTIVITY_STARTED =1;

        SceneManager.ACTIVE_SCENE=2;


    }

    public static void progressSet(int p) {
        progressBar.setProgress(p);
    }

    public static void maxSet (int p) {
        progressBar.setMax(p);
    }

    public static int progressGet () {
        return progressBar.getProgress();
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
