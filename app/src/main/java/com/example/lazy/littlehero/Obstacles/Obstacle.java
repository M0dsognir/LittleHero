package com.example.lazy.littlehero.Obstacles;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

import com.example.lazy.littlehero.Tools.Constants;
import com.example.lazy.littlehero.Tools.RelativePoint;

public class Obstacle
{
    Rect rect;
    Point startingPoint;
    RelativePoint relativePoint;

    public Obstacle (Point obstacleStartingPoint, RelativePoint relativePoint)
    {
        this.startingPoint = obstacleStartingPoint;
        this.relativePoint = relativePoint;
        rect = new Rect(obstacleStartingPoint.x, obstacleStartingPoint.y, obstacleStartingPoint.x+ Constants.TILE_SIZE, obstacleStartingPoint.y+Constants.TILE_SIZE);
    }

    public void update()
    {
        rect.set(startingPoint.x+relativePoint.getThisPointCord().x, startingPoint.y+relativePoint.getThisPointCord().y,
                startingPoint.x+ Constants.TILE_SIZE+relativePoint.getThisPointCord().x, startingPoint.y+ Constants.TILE_SIZE+relativePoint.getThisPointCord().y);
    }

    public void draw(Canvas canvas)
    {

    }

    public Rect getRect() {
        return rect;
    }
}
