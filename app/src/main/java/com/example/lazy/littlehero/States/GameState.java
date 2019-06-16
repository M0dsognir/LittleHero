package com.example.lazy.littlehero.States;

import android.graphics.Canvas;
import android.graphics.Point;
import android.view.MotionEvent;

import com.example.lazy.littlehero.Characters.Player;
import com.example.lazy.littlehero.Maps.MapsManager;
import com.example.lazy.littlehero.Tools.SaveLoadUtils;
import com.example.lazy.littlehero.Tools.StaticVariable;

public class GameState implements State
{
    MapsManager mapsManager;
    Player player;

    public GameState()
    {
        player = new Player();
        mapsManager = new MapsManager(player);

        if (StaticVariable.IS_NEW_GAME)
        {
            player.setNewPlayersPosition(new Point(400,400));
            StaticVariable.ACTIVE_MAP=0;
        }
        else
        {
            SaveLoadUtils.loadMainThings();
            SaveLoadUtils.loadPlayer(player);
            SaveLoadUtils.loadEnemies(mapsManager);
        }

    }

    @Override
    public void update()
    {
        mapsManager.update();
    }

    @Override
    public void draw(Canvas canvas)
    {
        mapsManager.draw(canvas);
    }

    @Override
    public void receiveTouch(MotionEvent event)
    {
        mapsManager.receiveTouch(event);
    }
}
