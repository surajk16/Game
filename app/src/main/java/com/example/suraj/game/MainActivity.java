package com.example.suraj.game;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.media.audiofx.BassBoost;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    TextView t2,t3;
    RelativeLayout home;
    static MediaPlayer mediaPlayer;
    int bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_HEIGHT = dm.heightPixels;
        Constants.SCREEN_WIDTH = dm.widthPixels;

        setContentView(R.layout.home_screen);

        t2 = (TextView) findViewById(R.id.textView2);
        t2.setOnClickListener(this);
        t3 = (TextView) findViewById(R.id.textView3);
        t3.setOnClickListener(this);
        home = (RelativeLayout) findViewById(R.id.home);

        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.bg);
            mediaPlayer.setLooping(true);
            mediaPlayer.setVolume(0.3f,0.3f);
            mediaPlayer.start();
        }

    }



    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.textView2) {
            Intent i = new Intent(this,GameModeActivity.class);
            startActivity(i);
        }


    }

    @Override
    public void onBackPressed() {
        mediaPlayer.pause();
        finishAffinity();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer.start();
        Log.i("gdg","dgdg");
    }
}
