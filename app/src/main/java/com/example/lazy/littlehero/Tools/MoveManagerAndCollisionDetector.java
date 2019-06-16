package com.example.lazy.littlehero.Tools;

import com.example.lazy.littlehero.Characters.Enemies.Enemy;
import com.example.lazy.littlehero.Characters.Player;
import com.example.lazy.littlehero.Maps.Map;

import java.util.ArrayList;

public class MoveManagerAndCollisionDetector
{
    Player player;

    boolean canPlayerGoLeft = true, canPlayerGoUp = true, canPlayerGoRight = true, canPlayerGoDown = true;

    Map map;

    int counter, rest;
    int direction, backupDirection;
    boolean isMoving = false;
    ArrayList<Enemy> enemies;

    public MoveManagerAndCollisionDetector(Player player, Map map)
    {
        this.player = player;
        this.map = map;
        counter = Constants.TILE_SIZE/player.getBaseMovingSpeed();
        rest = Constants.TILE_SIZE-player.getBaseMovingSpeed()*counter;
        enemies = map.getEnemies();
    }

    public void update()
    {
        playerMoving();
        enemyCollisionDetector();
        playerVsEnemyCollision();
    }
    private void playerMoving()
    {
        if (player.getShiftY()%100==0&&player.getShiftX()%100==0)
            checkWherePlayerCanGo();
        if (!isMoving)
        {
            player.setMovingState(direction);
            switch (direction)
            {
                case 0:
                    break;
                case 1: if (canPlayerGoLeft) isMoving = true;
                    break;
                case 2: if (canPlayerGoUp) isMoving = true;
                    break;
                case 3: if (canPlayerGoRight) isMoving = true;
                    break;
                case 4: if (canPlayerGoDown) isMoving = true;
                    break;
            }
            backupDirection = direction;
        }
        if (isMoving)
        {
            switch (backupDirection)
            {
                case 0:
                    break;
                case 1: if (canPlayerGoLeft) player.move(-player.getBaseMovingSpeed(), 0);
                    break;
                case 2: if (canPlayerGoUp) player.move(0, -player.getBaseMovingSpeed());
                    break;
                case 3: if (canPlayerGoRight) player.move(player.getBaseMovingSpeed(), 0);
                    break;
                case 4: if (canPlayerGoDown) player.move(0, player.getBaseMovingSpeed());
                    break;
            }
            counter--;
            if (counter==0)
            {
                switch (backupDirection)
                {
                    case 0:
                        break;
                    case 1: if (canPlayerGoLeft) player.move(-rest, 0);
                        rest=0;
                        break;
                    case 2: if (canPlayerGoUp) player.move(0, -rest);
                        rest=0;
                        break;
                    case 3: if (canPlayerGoRight) player.move(rest, 0);
                        rest=0;
                        break;
                    case 4: if (canPlayerGoDown) player.move(0, rest);
                        rest=0;
                        break;
                }
                isMoving = false;
                counter = Constants.TILE_SIZE/player.getBaseMovingSpeed();
                rest = Constants.TILE_SIZE-player.getBaseMovingSpeed()*counter;
                playerCanGoAnywhere();
            }
        }
    }
    public void movePlayer(int direction)
    {
        this.direction = direction;
    }

    private void checkWherePlayerCanGo()
    {
        int a = player.getShiftX()/Constants.TILE_SIZE;
        int b = player.getShiftY()/Constants.TILE_SIZE;
        if (map.getMap().get(a-1+b*100).isAnObstacleForPlayer()) canPlayerGoLeft = false;
        if (map.getMap().get(a+(b-1)*100).isAnObstacleForPlayer()) canPlayerGoUp = false;
        if (map.getMap().get(a+1+b*100).isAnObstacleForPlayer()) canPlayerGoRight = false;
        if (map.getMap().get(a+(b+1)*100).isAnObstacleForPlayer()) canPlayerGoDown = false;
    }
    public void playerCanGoAnywhere()
    {
        canPlayerGoLeft = true;
        canPlayerGoUp = true;
        canPlayerGoRight = true;
        canPlayerGoDown = true;
    }

    private void enemyCollisionDetector()
    {
        for (Enemy enemy: enemies)
        {
            enemy.canGoAnywhere();

            int a = enemy.getCurrentFieldPosition().x/100;
            int b = enemy.getCurrentFieldPosition().y/100;

            if (!enemy.isBig())
            {
                if (map.getMap().get(a-1+b*100).isAnObstaclesForMob()) enemy.cantGoLeft();
                if (map.getMap().get(a+(b-1)*100).isAnObstaclesForMob()) enemy.cantGoUp();
                if (map.getMap().get(a+1+b*100).isAnObstaclesForMob()) enemy.cantGoRight();
                if (map.getMap().get(a+(b+1)*100).isAnObstaclesForMob()) enemy.cantGoDown();
            }
            else
            {
                if (map.getMap().get(a-1+b*100).isAnObstaclesForMob()) enemy.cantGoLeft();
                if (map.getMap().get(a-1+(b+1)*100).isAnObstaclesForMob()) enemy.cantGoLeft();
                if (map.getMap().get(a+(b-1)*100).isAnObstaclesForMob()) enemy.cantGoUp();
                if (map.getMap().get(a+(b+2)*100).isAnObstaclesForMob()) enemy.cantGoDown();
                if (map.getMap().get(a+1+(b-1)*100).isAnObstaclesForMob()) enemy.cantGoUp();
                if (map.getMap().get(a+1+(b+2)*100).isAnObstaclesForMob()) enemy.cantGoDown();
                if (map.getMap().get(a+2+b*100).isAnObstaclesForMob()) enemy.cantGoRight();
                if (map.getMap().get(a+2+(b+1)*100).isAnObstaclesForMob()) enemy.cantGoRight();

                if (map.getMap().get(a-1+b*100).isAnObstaclesForMob()) enemy.cantGoLeft();
                if (map.getMap().get(a+(b-1)*100).isAnObstaclesForMob()) enemy.cantGoUp();
                if (map.getMap().get(a+1+b*100).isAnObstaclesForMob()) enemy.cantGoRight();
                if (map.getMap().get(a+(b+1)*100).isAnObstaclesForMob()) enemy.cantGoDown();
            }
        }
    }

    private void playerVsEnemyCollision()
    {
        if (!enemies.isEmpty())
        for (Enemy enemy: enemies)
        {
            if (enemy.getRect().intersect((player.getPlayersRect())))
            {
                map.startDuel(enemy);
                Timer.STOP_GAME();
                movePlayer(0);
            }
        }
    }
}
