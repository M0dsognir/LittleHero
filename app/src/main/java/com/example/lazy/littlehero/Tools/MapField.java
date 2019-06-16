package com.example.lazy.littlehero.Tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

import com.example.lazy.younglord.R;

public class MapField
{
    int cordX, cordY, tileId, obstacleId;
    RelativePoint relativePoint;
    Rect rect, rect2;
    Paint p;
    Point test;

    public MapField(int cordX, int cordY, int tileId, int obstacleId, RelativePoint relativePoint)
    {
        this.relativePoint = relativePoint;
        this.cordX = cordX;
        this.cordY = cordY;
        this.tileId = tileId;
        this.obstacleId = obstacleId;
        p = new Paint();
        rect = new Rect(cordX*Constants.TILE_SIZE+relativePoint.getThisPointCord().x, cordY*Constants.TILE_SIZE+relativePoint.getThisPointCord().y,
                cordX*Constants.TILE_SIZE+relativePoint.getThisPointCord().x+Constants.TILE_SIZE, cordY*Constants.TILE_SIZE+relativePoint.getThisPointCord().y+Constants.TILE_SIZE);
        rect2 = new Rect();
        test = new Point();
    }


    public boolean isAnObstacleForPlayer()
    {
        if (obstacleId==0||obstacleId==121) return false;
        else return true;
    }
    public boolean isAnObstaclesForMob()
    {
        if (obstacleId==0) return false;
        else return true;
    }

    public boolean isARoad()
    {
        if (tileId>=1&&tileId<=29&&tileId!=9)return true;
        else return false;
    }

    public void update()
    {
        /*rect = new Rect(cordX*Constants.TILE_SIZE+relativePoint.getThisPointCord().x, cordY*Constants.TILE_SIZE+relativePoint.getThisPointCord().y,
                cordX*Constants.TILE_SIZE+relativePoint.getThisPointCord().x+Constants.TILE_SIZE, cordY*Constants.TILE_SIZE+relativePoint.getThisPointCord().y+Constants.TILE_SIZE);*/
        rect.set(cordX*Constants.TILE_SIZE+relativePoint.getThisPointCord().x, cordY*Constants.TILE_SIZE+relativePoint.getThisPointCord().y,
                cordX*Constants.TILE_SIZE+relativePoint.getThisPointCord().x+Constants.TILE_SIZE, cordY*Constants.TILE_SIZE+relativePoint.getThisPointCord().y+Constants.TILE_SIZE);
        rect2.set(cordX*Constants.TILE_SIZE+relativePoint.getThisPointCord().x, cordY*Constants.TILE_SIZE-50+relativePoint.getThisPointCord().y,
                cordX*Constants.TILE_SIZE+relativePoint.getThisPointCord().x+Constants.TILE_SIZE+50, cordY*Constants.TILE_SIZE+relativePoint.getThisPointCord().y+Constants.TILE_SIZE);
        test.set(cordX*Constants.TILE_SIZE+relativePoint.getThisPointCord().x, cordY*Constants.TILE_SIZE-50+relativePoint.getThisPointCord().y);
    }
    public void draw(Canvas canvas)
    {
        //canvas.drawBitmap(Graphics.TILES_BITMAP[tileId], null, rect, p);
        //canvas.drawBitmap(Graphics.TILES_BITMAP[tileId], test.x, test.y, p);
        canvas.drawBitmap(Graphics.TILES_BITMAP[tileId], test.x, test.y, null);
    }
    public void drawObstacle(Canvas canvas)
    {
        /*if (isAnObstacleForPlayer())
        {
            switch(obstacleId)
            {
                //case 101: canvas.drawBitmap(Graphics.TREE_BITMAP, null, rect2, new Paint());
                //    break;
                case 106: canvas.drawBitmap(Graphics.ROCK_BITMAP, test.x, test.y, null);
                    break;
                default: canvas.drawBitmap(Graphics.OBSTACLES_BITMAP[obstacleId-141], test.x, test.y, null);
                    //canvas.drawBitmap(Graphics.OBSTACLES_BITMAP[obstacleId-141], null, rect2, p);
                    // canvas.drawBitmap(Graphics.TREE_BITMAP, null, rect, new Paint());
            }
        }*/
    }
    public int getObstacleId()
    {
        return obstacleId;
    }

    public Rect getRect() {
        return rect;
    }
}
