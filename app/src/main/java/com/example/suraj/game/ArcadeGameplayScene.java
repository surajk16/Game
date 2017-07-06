package com.example.suraj.game;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by suraj on 03-07-2017.
 */

public class ArcadeGameplayScene implements Scene {
    Ball ball;
    private long gameOverTime;
    private long gameStartTime;
    private long prevTime;
    private int score = 0;
    private int level = 1;
    private float time = 5;
    private boolean gameOver = false;
    private boolean gameStart = false;
    private Rect r = new Rect();
    Vibrator vibe;
    View view;
    SoundPool soundPool;
    int correct;

    public ArcadeGameplayScene () {
        ball = new Ball (200,200,3*Constants.SCREEN_WIDTH/32,((float)Math.random() * 6 + 15),((float)Math.random() * 6 + 20));
        gameStartTime = System.currentTimeMillis();
        prevTime = gameStartTime;
        if (ArcadeActivity.ACTIVITY_STARTED ==1) {
            ArcadeActivity.maxSet((int) time * 1000);
            ArcadeActivity.progressSet((int) (time * 1000));
        }

        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC,0);
        correct = soundPool.load(Constants.CURRENT_CONTEXT,R.raw.correct,1);

    }

    @Override
    public void update() {
        if (!gameOver) {
            ball.update();
            if (!gameStart && ArcadeActivity.ACTIVITY_STARTED == 1) {
                ArcadeActivity.maxSet((int) time * 1000);
                ArcadeActivity.progressSet((int) (time * 1000));
                gameStart = true;
            }
            if ((System.currentTimeMillis() - prevTime) >= 50 && ArcadeActivity.ACTIVITY_STARTED == 1 && gameStart) {
                ArcadeActivity.progressSet((int) (time * 1000 - (System.currentTimeMillis() - gameStartTime)));
                prevTime = System.currentTimeMillis();
                if (ArcadeActivity.progressGet() <=0) {
                    gameOver = true;
                    gameOverTime = System.currentTimeMillis();
                }
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if (!gameOver) {
            ball.ballBounds.set(ball.ballX - ball.ballRadius, ball.ballY - ball.ballRadius,
                    ball.ballX + ball.ballRadius, ball.ballY + ball.ballRadius);
            canvas.drawOval(ball.ballBounds, ball.paint);
        } else {
            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            paint.setTextSize(175);
            drawCenterText(canvas, paint, "GAME OVER");
        }

        Paint paint2 = new Paint();
        paint2.setColor(Color.WHITE);
        paint2.setTextSize(50);
        canvas.drawText("Level:" + level, 30, Constants.SCREEN_HEIGHT - 80 - paint2.ascent(), paint2);
        canvas.drawText("Score:" + score, Constants.SCREEN_WIDTH - 200, Constants.SCREEN_HEIGHT - 80 - paint2.ascent(), paint2);

    }

    @Override
    public void terminate() {

    }

    @Override
    public void receiveTouch(MotionEvent event) {
        if (!gameOver) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (ball.ballBounds.contains(event.getX(), event.getY())) {
                        level++;
                        score = (int) (score + time - ((System.currentTimeMillis()) - gameStartTime)/1000);
                        gameStartTime = System.currentTimeMillis();
                        ArcadeActivity.maxSet((int) (time* 1000/1.03));
                        ArcadeActivity.progressSet((int) (time* 1000/1.03));
                        time = time/1.05f;

                        vibe = (Vibrator) Constants.CURRENT_CONTEXT.getSystemService(Constants.CURRENT_CONTEXT.VIBRATOR_SERVICE);
                        vibe.vibrate(100);
                        soundPool.play(correct,1.0f,1.0f,1,0,1.0f);

                    } else {
                        vibe = (Vibrator) Constants.CURRENT_CONTEXT.getSystemService(Constants.CURRENT_CONTEXT.VIBRATOR_SERVICE);
                        vibe.vibrate(1000);
                        score = score - 2;
                    }
            }
        }

        else if (System.currentTimeMillis() - gameOverTime >= 2000) {
            reset();
        }
    }

    public void reset (){
        ball =  new Ball(200,200,3*Constants.SCREEN_WIDTH/32,((float)Math.random() * 6 + 15),((float)Math.random() * 6 + 20));
        score = 0;
        level = 1;
        time = 5;
        gameOver = false;
        gameStartTime = System.currentTimeMillis();
        prevTime = gameStartTime;
        if (ArcadeActivity.ACTIVITY_STARTED ==1) {
            ArcadeActivity.maxSet((int) time * 1000);
            ArcadeActivity.progressSet((int) (time * 1000));
        }


    }

    public void drawCenterText(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = cHeight / 2f + r.height() / 2f - r.bottom;
        canvas.drawText(text, x, y, paint);
    }

}