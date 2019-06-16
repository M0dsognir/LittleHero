package com.example.lazy.littlehero.Maps;

import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;

import com.example.lazy.littlehero.Tools.ButtonsManager;
import com.example.lazy.littlehero.Tools.Constants;
import com.example.lazy.littlehero.Tools.MapField;
import com.example.lazy.littlehero.Tools.MapTools;
import com.example.lazy.littlehero.Tools.MoveManagerAndCollisionDetector;
import com.example.lazy.littlehero.Tools.RelativePoint;
import com.example.lazy.littlehero.Tools.SaveLoadUtils;
import com.example.lazy.littlehero.Tools.Timer;
import com.example.lazy.littlehero.Characters.Enemies.Enemy;
import com.example.lazy.littlehero.Characters.Player;
import com.example.lazy.littlehero.Duel;
import com.example.lazy.littlehero.Passage;
import com.example.lazy.littlehero.SpawnManager;

import java.util.ArrayList;

public class DragonsRage implements Map
{
    private MapsManager mapsManager;
    private Player player;
    private ButtonsManager buttonsManager;
    private MoveManagerAndCollisionDetector moveManagerAndCollisionDetector;

    boolean wasArrowTouched = false;

    private ArrayList<MapField> map;
    private RelativePoint relativePoint;

    private ArrayList<Passage> passages;
    private ArrayList<Enemy> enemies;
    private SpawnManager spawnManager;

    private boolean isDuel = false;
    private Duel duel;

    DragonsRage(Player player, MapsManager mapsManager)
    {
        this.mapsManager = mapsManager;
        this.player = player;
        passages = new ArrayList<>();
        enemies = new ArrayList<>();
        int[] mapIntTable = MapTools.convertTxtFileToMapIntTable("dr_map.txt");
        int[] obstacleIntTable = MapTools.convertTxtFileToMapIntTable("dr_obstacles.txt");
        relativePoint = new RelativePoint(player);
        map = new ArrayList<>();
        MapTools.createArrayMap(map, mapIntTable, obstacleIntTable, relativePoint);
        relativePoint.update();
        moveManagerAndCollisionDetector = new MoveManagerAndCollisionDetector(player, this);
        buttonsManager = new ButtonsManager(moveManagerAndCollisionDetector);
        spawnManager = new SpawnManager(enemies, relativePoint, 0);
        createSpawnSpotsAndPassages();
        enemies.add(new Enemy(new Point(700,700), relativePoint, 0));
        duel = new Duel(player, this);
    }

    @Override
    public void update()
    {
        Timer.update();
        if (!isDuel)
        {
            if (Timer.IS_GAME_RUNNING)
            {
                player.update();
                relativePoint.update();
                moveManagerAndCollisionDetector.update();
                if (!enemies.isEmpty())
                    for (Enemy enemy: enemies)
                    {
                        enemy.update();
                    }
                buttonsManager.update();
                spawnManager.update();
            }
        }
        else
        {
            duel.update();
        }
    }

    @Override
    public void draw(Canvas canvas)
    {
        if (!isDuel)
        {
            MapTools.drawMap(canvas, player, map);
            player.draw(canvas);
            if (!enemies.isEmpty())
                for (Enemy enemy: enemies)
                {
                    enemy.draw(canvas);
                }
            MapTools.drawObstacles(canvas, player, map);
            buttonsManager.draw(canvas);
        }
        else
        {
            duel.draw(canvas);
        }
    }

    @Override
    public void receiveTouch(MotionEvent event)
    {
        if (!isDuel)
        {
            switch (event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    if (buttonsManager.wasButtonTouched(event))
                    {
                        wasArrowTouched = buttonsManager.wasArrowTouched(event);
                        buttonsManager.receiveTouch(event);
                    }
                    if (player.getPlayersRect().contains((int)event.getX(), (int)event.getY()))
                    {
                        Log.e("time", Timer.GET_TIME_PASSED()+"");
                        SaveLoadUtils.saveGame(player, mapsManager.getMaps());
                        //SaveLoadUtils.saveGame(player);
                        /*if (Timer.IS_GAME_RUNNING) Timer.STOP_GAME();
                        else Timer.START_GAME();*/
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    if (wasArrowTouched)
                    {
                        buttonsManager.stopPlayer();
                        wasArrowTouched = false;
                    }
                    if (buttonsManager.wasButtonTouched(event))
                    {
                        buttonsManager.receiveTouch(event);
                    }
                    break;
            }
        }
        else
        {
            duel.receiveTouch(event);
        }
    }

    private void createSpawnSpotsAndPassages()
    {
        int[] spawnsAndPassagesIntTable = MapTools.convertTxtFileToMapIntTable("dr_spawns.txt");
        for (int y=0; y<spawnsAndPassagesIntTable[1]; y++)
        {
            for (int x=0; x<spawnsAndPassagesIntTable[0]; x++)
            {
                switch (spawnsAndPassagesIntTable[x+y*spawnsAndPassagesIntTable[1]])
                {
                    case 116: passages.add(new Passage());
                    break;
                    case 126: spawnManager.addSpawnSpot(new Point(x* Constants.TILE_SIZE,+y*Constants.TILE_SIZE), relativePoint, 0);
                    break;
                    case 131: spawnManager.addSpawnSpot(new Point(x*Constants.TILE_SIZE,+y*Constants.TILE_SIZE), relativePoint, 1);
                    break;
                    case 136: spawnManager.addSpawnSpot(new Point(x*Constants.TILE_SIZE,+y*Constants.TILE_SIZE), relativePoint, 3);
                    break;
                }
            }
        }
    }

    public ArrayList<Enemy> getEnemies()
    {
        return enemies;
    }
    public ArrayList<MapField> getMap()
    {
        return map;
    }
    @Override
    public void startDuel(Enemy enemy)
    {
        isDuel = true;
        duel.startDuel(enemy);
        Timer.STOP_GAME();
    }
    @Override
    public void finishDuel()
    {
        isDuel = false;
        Timer.START_GAME();
    }
    @Override
    public RelativePoint getRelativePoint()
    {
        return relativePoint;
    }
}
