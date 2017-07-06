package com.example.suraj.game;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by suraj on 01-07-2017.
 */

public class HomeScreen implements Scene {

    Ball ball;
    private Rect r = new Rect();


    public HomeScreen() {
        ball = new Ball(3*Constants.SCREEN_HEIGHT/4,3*Constants.SCREEN_HEIGHT/4,Constants.SCREEN_WIDTH/16,5,5);
    }

    @Override
    public void update() {
        ball.update();
    }

    @Override
    public void draw(Canvas canvas) {
        ball.ballBounds.set(ball.ballX - ball.ballRadius, ball.ballY - ball.ballRadius,
                ball.ballX + ball.ballRadius, ball.ballY + ball.ballRadius);
        canvas.drawOval(ball.ballBounds, ball.paint);
    }

    @Override
    public void terminate() {

    }

    @Override
    public void receiveTouch(MotionEvent event) {

    }
}
