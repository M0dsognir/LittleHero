package com.example.lazy.littlehero.Obstacles;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.example.lazy.littlehero.Tools.Graphics;
import com.example.lazy.littlehero.Tools.RelativePoint;

public class RockObstacle extends Obstacle
{
    public RockObstacle(Point obstacleStartingPoint, RelativePoint relativePoint) {
        super(obstacleStartingPoint, relativePoint);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(Graphics.ROCK_BITMAP, null, rect, new Paint());
    }
}
