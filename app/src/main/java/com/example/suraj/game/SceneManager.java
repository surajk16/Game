package com.example.suraj.game;

import android.app.Activity;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.ViewGroup;

import java.util.ArrayList;


import static com.example.suraj.game.R.layout.home_screen;

/**
 * Created by suraj on 29-06-2017.
 */

public class SceneManager extends Activity {
    private ArrayList<Scene> scenes = new ArrayList<> ();
    public static int ACTIVE_SCENE;


    public SceneManager () {
        ACTIVE_SCENE = 0;
        scenes.add(new HomeScreen());
        scenes.add(new ClassicGameplayScene());
        scenes.add(new ArcadeGameplayScene());
    }

    public void update () {
        scenes.get(ACTIVE_SCENE).update();
    }

    public void draw (Canvas canvas) {
        scenes.get(ACTIVE_SCENE).draw(canvas);
    }

    public void receiveTouch(MotionEvent event) {
        scenes.get(ACTIVE_SCENE).receiveTouch(event);
    }
}
