package com.example.suraj.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;

/**
 * Created by suraj on 29-06-2017.
 */

public class Ball {
    public float ballRadius;
    public float ballX;
    public float ballY;
    public float ballSpeedX;
    public float ballSpeedY;
    public RectF ballBounds;
    public Paint paint;



    public Ball(float ballX, float ballY, float ballRadius, float ballSpeedX, float ballSpeedY) {
        this.ballX = ballX;
        this.ballY = ballY;
        this.ballRadius = ballRadius;
        this.ballSpeedX = ballSpeedX;
        this.ballSpeedY = ballSpeedY;
        this.ballBounds = new RectF();

        this.paint = new Paint();
        paint.setColor(Color.WHITE);
    }

    public void setBallSpeed(float ballSpeedX, float ballSpeedY) {
        this.ballSpeedX = ballSpeedX;
        this.ballSpeedY = ballSpeedY;
    }

    public void update() {

        this.ballX += this.ballSpeedX;
        this.ballY += this.ballSpeedY;

        if (this.ballX + this.ballRadius > Constants.SCREEN_WIDTH) {
            this.ballSpeedX = -this.ballSpeedX;
            this.ballX = Constants.SCREEN_WIDTH - this.ballRadius;
        } else if (this.ballX - this.ballRadius < 0) {
            this.ballSpeedX = -this.ballSpeedX;
            this.ballX = this.ballRadius;
        }

        if (this.ballY + this.ballRadius > Constants.SCREEN_HEIGHT) {
            this.ballSpeedY = -this.ballSpeedY;
            this.ballY = Constants.SCREEN_HEIGHT - this.ballRadius;
        } else if (this.ballY - this.ballRadius < 0) {
            this.ballSpeedY = -this.ballSpeedY;
            this.ballY = this.ballRadius;
        }
    }



}
