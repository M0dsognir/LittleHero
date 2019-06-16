package com.example.lazy.littlehero.Maps;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.example.lazy.littlehero.Tools.StaticVariable;
import com.example.lazy.littlehero.Characters.Player;

import java.util.ArrayList;

public class MapsManager
{
    ArrayList<Map> maps;
    Player player;

    public MapsManager(Player player)
    {
        this.player = player;
        maps = new ArrayList<>();
        maps.add(new DragonsRage(player, this));
    }

    public void update()
    {
        maps.get(StaticVariable.ACTIVE_MAP).update();
    }
    public void draw(Canvas canvas)
    {
        maps.get(StaticVariable.ACTIVE_MAP).draw(canvas);
    }
    public void receiveTouch(MotionEvent event)
    {
        maps.get(StaticVariable.ACTIVE_MAP).receiveTouch(event);
    }
    public ArrayList<Map> getMaps()
    {
        return maps;
    }
}
