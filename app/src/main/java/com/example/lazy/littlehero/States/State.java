package com.example.lazy.littlehero.States;

import android.graphics.Canvas;
import android.view.MotionEvent;

public interface State
{
    public void update();
    public void draw(Canvas canvas);
    public void receiveTouch(MotionEvent event);
}
