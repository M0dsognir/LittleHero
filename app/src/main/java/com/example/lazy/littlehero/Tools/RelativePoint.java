package com.example.lazy.littlehero.Tools;

import android.graphics.Point;

import com.example.lazy.littlehero.Characters.Player;

public class RelativePoint
{
    Point thisPointCord;
    Player player;

    public RelativePoint(Player player)
    {
        this.player = player;
        thisPointCord = new Point(Constants.BASE_SHIFT_X, Constants.BASE_SHIFT_Y);
    }

    public Point getThisPointCord()
    {
        return thisPointCord;
    }

    public void update()
    {
        thisPointCord.set(Constants.BASE_SHIFT_X-player.getShiftX(), Constants.BASE_SHIFT_Y-player.getShiftY());
    }
}
