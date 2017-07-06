package com.example.suraj.game;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by suraj on 29-06-2017.
 */

public class ClassicGameplayScene extends Activity implements Scene {

    Ball ball;
    private int life = 3;
    private int score = 0;
    private long gameOverTime;
    private long gameStartTime;
    private boolean gameOver = false;
    private Rect r = new Rect();
    Vibrator vibe;
    View view;
    SoundPool soundPool;
    int correct;


    public ClassicGameplayScene() {
        ball = new Ball(200,200,Constants.SCREEN_WIDTH/8, ((float)Math.random() * 6 + 5),((float)Math.random() * 6 + 5));
        gameStartTime = System.currentTimeMillis();
        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC,0);
        correct = soundPool.load(Constants.CURRENT_CONTEXT,R.raw.correct,1);
    }


    @Override
    public void update() {
        if (!gameOver) {
            ball.update();
        }

    }

    @Override
    public void draw(Canvas canvas) {
        if (!gameOver) {
            ball.ballBounds.set(ball.ballX - ball.ballRadius, ball.ballY - ball.ballRadius,
                    ball.ballX + ball.ballRadius, ball.ballY + ball.ballRadius);
            canvas.drawOval(ball.ballBounds, ball.paint);
        }

        else {
            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            paint.setTextSize(175);
            drawCenterText(canvas,paint,"GAME OVER");
        }

        Paint paint2 = new Paint();
        paint2.setColor(Color.WHITE);
        paint2.setTextSize(100);
        canvas.drawText("" + score, 50, Constants.SCREEN_HEIGHT - 120 - paint2.ascent() , paint2);

        Resources res = Constants.CURRENT_CONTEXT.getResources();
        Bitmap bitmap;
        switch (life) {
            case 0:
                bitmap = BitmapFactory.decodeResource(res, R.drawable.heart0);
                break;
            case 1:
                bitmap = BitmapFactory.decodeResource(res, R.drawable.heart1);
                break;
            case 2:
                bitmap = BitmapFactory.decodeResource(res, R.drawable.heart2);
                break;
            case 3:
                bitmap = BitmapFactory.decodeResource(res, R.drawable.heart3);
                break;

            default:
                bitmap = BitmapFactory.decodeResource(res, R.drawable.heart3);

        }

        canvas.drawBitmap(bitmap, Constants.SCREEN_WIDTH - bitmap.getWidth() - 50, Constants.SCREEN_HEIGHT - 120, null);
    }

    @Override
    public void terminate() {
        //ball.terminate();
    }

    @Override
    public void receiveTouch(MotionEvent event) {
        if (!gameOver) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (ball.ballBounds.contains(event.getX(), event.getY())) {
                    score++;
                    ball.ballRadius = ball.ballRadius/(1.03f);
                    ball.ballSpeedX = ball.ballSpeedX * (1.03f);
                    ball.ballSpeedY = ball.ballSpeedY * (1.03f);
                    vibe = (Vibrator) Constants.CURRENT_CONTEXT.getSystemService(Constants.CURRENT_CONTEXT.VIBRATOR_SERVICE);
                    vibe.vibrate(100);
                    soundPool.play(correct,1.0f,1.0f,1,0,1.0f);
                }
                else {
                    life--;
                    vibe = (Vibrator) Constants.CURRENT_CONTEXT.getSystemService(Constants.CURRENT_CONTEXT.VIBRATOR_SERVICE);
                    vibe.vibrate(1000);
                }
                break;
            }

        if (life==0)  { gameOver=true; gameOverTime = System.currentTimeMillis(); }
        }

        if (gameOver && (System.currentTimeMillis() - gameOverTime >= 2000) )
            reset ();
    }

    public void reset (){
        ball =  new Ball(200,200,Constants.SCREEN_WIDTH/8, ((float)Math.random() * 6 + 5),((float)Math.random() * 6 + 5));
        life = 3;
        score = 0;
        gameOver = false;
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
