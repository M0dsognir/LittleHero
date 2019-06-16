package com.example.lazy.littlehero;

import android.graphics.Canvas;
import android.graphics.Point;

import com.example.lazy.littlehero.Tools.Constants;
import com.example.lazy.littlehero.Tools.RelativePoint;
import com.example.lazy.littlehero.Tools.Timer;
import com.example.lazy.littlehero.Characters.Enemies.EnemiesTraits;
import com.example.lazy.littlehero.Characters.Enemies.Enemy;

import java.util.ArrayList;
import java.util.Random;

public class SpawnManager
{
    ArrayList <SpawnSpot> spawnSpots;
    ArrayList <Enemy> enemies;
    int mapId;
    private long timeLastSpawn;
    Random r;
    RelativePoint relativePoint;

    public SpawnManager (ArrayList <Enemy> enemies, RelativePoint relativePoint, int mapId)
    {
        spawnSpots = new ArrayList<>();
        this.relativePoint = relativePoint;
        this.enemies = enemies;
        this.mapId = mapId;
        timeLastSpawn = Timer.GET_TIME_PASSED();
        r = new Random();
    }
    public void draw(Canvas canvas)
    {
        for (SpawnSpot s: spawnSpots)
            s.draw(canvas);
    }
    public void addSpawnSpot(Point shiftXY, RelativePoint relativePoint, int enemyId)
    {
        spawnSpots.add(new SpawnSpot(shiftXY, relativePoint, enemies, enemyId, mapId));
    }

    public void update()
    {
        for (SpawnSpot spawnSpot: spawnSpots)
        {
            spawnSpot.update();
        }
        //Log.i(Timer.GET_TIME_PASSED()+"-"+timeLastSpawn, Constants.SPAWN_TIME+"");
        if (Timer.GET_TIME_PASSED()-timeLastSpawn > Constants.SPAWN_TIME)
        {
            //Log.i("spawnujemy", "zaczynamy");
            spawnEnemy();
        }
    }

    private void spawnEnemy()
    {
        //Log.i("enemies size", enemies.size()+"");
        //Log.i("max enemies on this map",EnemiesTraits.maxEnemiesPerMap[mapId]+"");
        if (enemies.size() <= EnemiesTraits.maxEnemiesPerMap[mapId])
        {
            int temp = r.nextInt(spawnSpots.size());
            //Log.i("temp - spawnspot id", temp+"");
            if (EnemiesTraits.enemiesStats[spawnSpots.get(temp).getEnemyId()][7]==1)
            {
                //Log.i("boss spawn?", "tak");
                for (Enemy enemy: enemies)
                {
                    if (enemy.isBoss())
                    {
                        spawnEnemy();
                        //Log.i("boss?", "tak, i jeszcze raz petelka");
                    }
                    //Log.i("boss?", "nie");
                }
                enemies.add(new Enemy(spawnSpots.get(temp).getStartingPoint(), relativePoint, spawnSpots.get(temp).getEnemyId()));
                //Log.i("nowy", "boss");
            }
            else
            {
                //Log.i("nie byl to boss", "spawn normalnego mobka");
                enemies.add(new Enemy(spawnSpots.get(temp).getStartingPoint(), relativePoint, spawnSpots.get(temp).getEnemyId()));
            }
            //Log.i("ile jest mobkow?", enemies.size()+"");
            timeLastSpawn = Timer.GET_TIME_PASSED();
        }
    }
    public ArrayList<SpawnSpot> getList()
    {
        return spawnSpots;
    }
}
