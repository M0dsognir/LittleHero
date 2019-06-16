package com.example.lazy.littlehero.MainFiles;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameMainThread extends Thread
{
    private SurfaceHolder surfaceHolder;
    private GameWindow gameWindow;
    private boolean running;
    public static Canvas canvas;

    public final int MAX_FPS = 100;
    private double averageFPS;

    public GameMainThread (SurfaceHolder surfaceHolder, GameWindow gameWindow)
    {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gameWindow = gameWindow;

    }

    public void setRunning (Boolean isRunning)
    {
        running = isRunning;
    }

    public void run()
    {
        long starTime;
        long timeMillis;
        long waitTime;
        int frameCount = 0;
        long totalTime = 0;
        long targetTime = 1000/MAX_FPS;
        /////////////////////////

        //super.run();
        while (running)
        {
            /////////////////////////////
            starTime = System.nanoTime();
            /////////////////////////////
            canvas = null;
            try
            {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder)
                {
                    this.gameWindow.update();
                    this.gameWindow.draw(canvas);
                }
            } catch (Exception e) {
            } finally {
                if(canvas!=null)
                {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            ///////////////////////////////////////////////////////
            timeMillis = (System.nanoTime() - starTime)/1000000;
            waitTime = targetTime - timeMillis;
            try {
                if(waitTime>0)
                {
                    this.sleep(waitTime);
                }

            } catch (InterruptedException e) {e.printStackTrace();
            }
            totalTime +=System.nanoTime() - starTime;
            frameCount++;
            if(frameCount == MAX_FPS)
            {
                averageFPS = 1000/((totalTime/frameCount)/1000000);
                frameCount=0;
                totalTime=0;

                Log.i("FPS", ""+averageFPS);
            }
            /////////////////////////////////////////////////////////
        }
    }
}
