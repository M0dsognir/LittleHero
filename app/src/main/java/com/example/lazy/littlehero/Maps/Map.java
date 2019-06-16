package com.example.lazy.littlehero.Maps;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.example.lazy.littlehero.Tools.MapField;
import com.example.lazy.littlehero.Characters.Enemies.Enemy;
import com.example.lazy.littlehero.Tools.RelativePoint;

import java.util.ArrayList;

public interface Map
{
    void draw(Canvas canvas);
    void update();
    void receiveTouch(MotionEvent event);
    ArrayList<Enemy> getEnemies();
    ArrayList<MapField> getMap();
    void startDuel(Enemy enemy);
    void finishDuel();
    RelativePoint getRelativePoint();
}
