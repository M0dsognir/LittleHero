package com.example.lazy.littlehero;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.example.lazy.littlehero.Tools.RelativePoint;
import com.example.lazy.littlehero.Characters.Enemies.EnemiesTraits;
import com.example.lazy.littlehero.Characters.Enemies.Enemy;

import java.util.ArrayList;

public class SpawnSpot
{
    private Point startingPoint;
    private RelativePoint relativePoint;
    private Point currentPosition;
    private int enemyId;
    private int mapId;
    private int lastSpawnTime;
    private int timeSinceLastSpawn;
    private int enemySpawnTime;
    private ArrayList<Enemy> enemies;

    public SpawnSpot(Point startingPoint, RelativePoint relativePoint, ArrayList<Enemy> enemies, int enemyId, int mapId)
    {
        this.startingPoint = startingPoint;
        this.relativePoint = relativePoint;
        this.currentPosition = new Point(startingPoint.x, startingPoint.y);
        this.enemyId = enemyId;
        this.mapId = mapId;
        enemySpawnTime = EnemiesTraits.enemiesSpawnTime[enemyId];
        lastSpawnTime = (int)System.currentTimeMillis()/1000;
        this.enemies = enemies;
    }

    public void update()
    {
        this.currentPosition.set(startingPoint.x+relativePoint.getThisPointCord().x, startingPoint.y+relativePoint.getThisPointCord().y);
        /*timeSinceLastSpawn = (int)System.currentTimeMillis()/1000-lastSpawnTime;
        //Log.i("lastST vs enemyST", timeSinceLastSpawn+" "+enemySpawnTime);
        if(timeSinceLastSpawn>=enemySpawnTime)
        {
            //Log.i("spawn", "initiated");
            spawnEnemy();
        }*/
    }

    /*public void spawnEnemy()
    {
        //Log.i("spawn spot", currentPosition.x+", "+currentPosition.y);
        //Log.i("is it a boss spawn?", EnemiesTraits.enemiesName[enemyId]+" "+EnemiesTraits.enemiesStats[enemyId][7]);
        if (EnemiesTraits.enemiesStats[enemyId][7]==1)
        {
            //Log.i("it's ", "a boss");
            //Log.i("checking if", "boss is already spawned");
            int x = 0;
            for (Enemy enemy: enemies)
            {
                //Log.i("enemy"+x, "boss? "+enemy.isBoss());
                if (enemy.isBoss())
                {
                    //Log.i("yup", "there's a boss spawned already");
                    return;
                }
            }
        }
        if (enemies.size()>=EnemiesTraits.maxEnemiesPerMap[mapId])
        {
            Log.i("max enemies on map", "reached");
            return;
        }
        else
        {
            Log.i("new enemy", "spawned");
            lastSpawnTime = (int)System.currentTimeMillis()/1000;
            enemies.add(new Enemy(currentPosition, relativePoint, enemyId));
        }
    }*/
    public Point getCord()
    {
        return currentPosition;
    }
    public Point getStartingPoint()
    {
        return startingPoint;
    }

    public void draw(Canvas canvas)
    {
        Paint p = new Paint();
        p.setColor(Color.RED);
        Rect r = new Rect(currentPosition.x, currentPosition.y, currentPosition.x+100, currentPosition.y+100);
        canvas.drawRect(r, p);
    }
    public int getEnemyId()
    {
        return enemyId;
    }
}
