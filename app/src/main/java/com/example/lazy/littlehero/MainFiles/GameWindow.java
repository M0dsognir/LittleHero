package com.example.lazy.littlehero.MainFiles;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.lazy.littlehero.Tools.Constants;
import com.example.lazy.littlehero.Tools.Graphics;
import com.example.lazy.littlehero.States.StateManager;

public class GameWindow extends SurfaceView implements SurfaceHolder.Callback
{
    GameMainThread gameMainThread;
    StateManager stateManager;

    public GameWindow(Context context)
    {
        super(context);
        getHolder().addCallback(this);
        Constants.CURRENT_CONTEXT=context;

        checkingNavigationPanelHeight(context);

        Constants.BASE_SHIFT_X = Constants.SCREEN_WIDTH/2-Constants.TILE_SIZE/2;
        Constants.BASE_SHIFT_Y = Constants.SCREEN_HEIGHT/3-Constants.TILE_SIZE/2;
        Constants.VISIBILITY_X = Constants.SCREEN_WIDTH/2/Constants.TILE_SIZE+1;
        Constants.VISIBILITY_Y = Constants.SCREEN_HEIGHT/3/Constants.TILE_SIZE+1;

        Graphics.loadGraphics();

        gameMainThread = new GameMainThread(getHolder(), this);
        setFocusable(true);
        stateManager = new StateManager();
    }

    public void update()
    {
        stateManager.update();
    }

    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        stateManager.draw(canvas);
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        stateManager.receiveTouch(event);
        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        gameMainThread.setRunning(true);
        gameMainThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        gameMainThread.interrupt();
       /* boolean retry = true;
        while (retry)
        {
            try {
                gameMainThread.setRunning(false);
                gameMainThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }*/
    }

    private void checkingNavigationPanelHeight(Context context)
    {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) Constants.SCREEN_HEIGHT-= resources.getDimensionPixelSize(resourceId);
    }
}
