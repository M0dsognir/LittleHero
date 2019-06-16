package com.example.lazy.littlehero.Characters.Enemies;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.example.lazy.littlehero.Tools.Constants;
import com.example.lazy.littlehero.Tools.Graphics;
import com.example.lazy.littlehero.Tools.RelativePoint;

import java.util.Random;

public class Enemy
{
    private int id;

    private Point currentTilePosition, currentPosition;
    private Point startingTilePosition;
    private RelativePoint relativePoint;
    private Rect rect;
    private int[][] possibleLoot;
    private String name;
    private int attackStrength, defence, wisdom, hp, movingSpeed, expToGive;
    private int isItBig, isBoss;

    private boolean isMoving = false;
    private boolean canGoLeft = true, canGoUp = true, canGoRight = true, canGoDown = true;
    private Random random;
    private int intMovingDirection = 0, recentMovingDirection = 0;
    private int counter, rest;
    private int shiftX = 0, shiftY = 0;

    public Enemy(Point startingTilePosition, RelativePoint relativePoint, int enemyId)
    {
        this.id = enemyId;
        this.name = EnemiesTraits.enemiesName[enemyId];
        this.attackStrength = EnemiesTraits.enemiesStats[enemyId][0];
        this.defence = EnemiesTraits.enemiesStats[enemyId][1];
        this.wisdom = EnemiesTraits.enemiesStats[enemyId][2];
        this.hp = EnemiesTraits.enemiesStats[enemyId][3];
        this.movingSpeed = EnemiesTraits.enemiesStats[enemyId][4];
        this.expToGive = EnemiesTraits.enemiesStats[enemyId][5];
        this.isItBig = EnemiesTraits.enemiesStats[enemyId][6];
        this.isBoss = EnemiesTraits.enemiesStats[enemyId][7];
        this.possibleLoot = EnemiesTraits.enemiesPossibleLoot[enemyId];
        this.startingTilePosition = startingTilePosition;
        currentTilePosition = new Point(startingTilePosition.x+shiftX, startingTilePosition.y+shiftY);
        this.relativePoint = relativePoint;
        currentPosition = new Point(startingTilePosition.x+relativePoint.getThisPointCord().x+shiftX, startingTilePosition.y+relativePoint.getThisPointCord().y+shiftY);
        this.counter = Constants.TILE_SIZE/ movingSpeed;
        this.rest = Constants.TILE_SIZE-counter* movingSpeed;

        if (isItBig==0)
        {
            rect = new Rect(currentPosition.x, currentPosition.y,currentPosition.x+Constants.TILE_SIZE, currentPosition.y+Constants.TILE_SIZE);
        }
        else
        {
            rect = new Rect(currentPosition.x, currentPosition.y,currentPosition.x+Constants.TILE_SIZE*2, currentPosition.y+Constants.TILE_SIZE*2);
        }
        random = new Random();
    }

    public void update()
    {
        currentPosition.set(startingTilePosition.x+relativePoint.getThisPointCord().x+shiftX, startingTilePosition.y+relativePoint.getThisPointCord().y+shiftY);
        if (isItBig==0)
        {
            rect.set(currentPosition.x, currentPosition.y,currentPosition.x+Constants.TILE_SIZE, currentPosition.y+Constants.TILE_SIZE);
        }
        else
        {
            rect.set(currentPosition.x, currentPosition.y,currentPosition.x+Constants.TILE_SIZE*2, currentPosition.y+Constants.TILE_SIZE*2);
        }
        randomMoves();
    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(Graphics.ENEMIES_BITMAP[id][0], null, rect, new Paint());
        /*if(id==0)
        {

        }
        else
        {
            Paint paint = new Paint();
            paint.setColor(Color.BLUE);
            canvas.drawRect(rect, paint);
        }*/
    }
    public boolean isBoss()
    {
        if (isBoss==1) return true;
        else return false;
    }
    public void cantGoLeft()
    {
        canGoLeft = false;
    }
    public void cantGoUp()
    {
        canGoUp = false;
    }
    public void cantGoRight()
    {
        canGoRight = false;
    }
    public void cantGoDown()
    {
        canGoDown = false;
    }
    public void canGoAnywhere()
    {
        canGoLeft = true;
        canGoUp = true;
        canGoRight = true;
        canGoDown = true;
    }
    private void randomMoves()
    {
        if (!isMoving)
        {
            int i = random.nextInt(7);
            switch (i)
            {
                //case 0: intMovingDirection = recentMovingDirection;
                //    break;
                case 1: if (canGoLeft)
                {
                    intMovingDirection = 1;
                    isMoving = true;
                }
                    break;
                case 2: if (canGoUp)
                {
                    intMovingDirection = 2;
                    isMoving = true;
                }
                    break;
                case 3: if (canGoRight)
                {
                    intMovingDirection = 3;
                    isMoving = true;
                }
                    break;
                case 4: if (canGoDown)
                {
                    intMovingDirection = 4;
                    isMoving = true;
                }
                    break;
                case 5: intMovingDirection = 0;
                        isMoving = true;
                    break;
                case 6: intMovingDirection = 0;
                        isMoving = true;
                    break;
            }
        }
        else
        {
            switch (intMovingDirection)
            {
                case 0: move(0);
                    break;
                case 1: move(1);
                    break;
                case 2: move(2);
                    break;
                case 3: move(3);
                    break;
                case 4: move(4);
                    break;
            }
        }
    }
    public void move(int direction)
    {
        if (counter>0)
        {
            switch (direction)
            {
                case 0:
                    break;
                case 1: shiftX -= movingSpeed;
                    break;
                case 2: shiftY -= movingSpeed;
                    break;
                case 3: shiftX += movingSpeed;
                    break;
                case 4: shiftY += movingSpeed;
                    break;
            }
            counter--;
        }
        else
        {
            switch (direction)
            {
                case 1: shiftX -= rest;
                    break;
                case 2: shiftY -= rest;
                    break;
                case 3: shiftX += rest;
                    break;
                case 4: shiftY += rest;
                    break;
            }
            currentTilePosition.set(startingTilePosition.x+shiftX, startingTilePosition.y+shiftY);
            isMoving = false;
            counter = Constants.TILE_SIZE/movingSpeed;
        }
    }
    public Point getCurrentFieldPosition()
    {
        return currentTilePosition;
    }
    public boolean isBig()
    {
        if (isItBig==1)return true;
        else return false;
    }
    public Rect getRect()
    {
        return rect;
    }
    public int getId()
    {
        return id;
    }
    public int getDefence()
    {
        return defence;
    }
    public void gotHit(int damage)
    {
        hp-=damage;
    }
    public int getHp()
    {
        return hp;
    }
    public int getAttackStrength()
    {
        return attackStrength;
    }
    public int getMovingSpeed()
    {
        return movingSpeed;
    }
    public int getWisdom()
    {
        return wisdom;
    }
    public int getExpToGive()
    {
        return expToGive;
    }
    public int[][] getLootPossible()
    {
        return EnemiesTraits.enemiesPossibleLoot[id];
    }
}
