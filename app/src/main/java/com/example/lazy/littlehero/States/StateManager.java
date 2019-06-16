package com.example.lazy.littlehero.States;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.example.lazy.littlehero.Tools.StaticVariable;
import com.example.lazy.littlehero.Tools.Timer;

import java.util.ArrayList;

public class StateManager
{
    private ArrayList<State> states = new java.util.ArrayList<>();

    public StateManager()
    {
        StaticVariable.ACTIVE_STATE=0;
        states.add(new MenuState(this));
    }

    public void update()
    {
        states.get(StaticVariable.ACTIVE_STATE).update();
    }
    public void draw(Canvas canvas)
    {
        states.get(StaticVariable.ACTIVE_STATE).draw(canvas);
    }
    public void receiveTouch(MotionEvent event)
    {
        states.get(StaticVariable.ACTIVE_STATE).receiveTouch(event);
    }

    public void startGame()
    {
        states.add(new GameState());
        StaticVariable.ACTIVE_STATE=1;
        Timer.START_GAME();
    }
}
