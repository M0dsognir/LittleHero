package com.example.lazy.littlehero.Obstacles;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.example.lazy.littlehero.Tools.Graphics;
import com.example.lazy.littlehero.Tools.RelativePoint;

public class TreeObstacle extends Obstacle
{
    public TreeObstacle(Point obstacleStartingPoint, RelativePoint relativePoint) {
        super(obstacleStartingPoint, relativePoint);
    }

    @Override
    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(Graphics.TREE_BITMAP, null, rect, new Paint());
    }
}
