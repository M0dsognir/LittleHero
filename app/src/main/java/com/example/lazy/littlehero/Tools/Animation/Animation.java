package com.example.lazy.littlehero.Tools.Animation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class Animation
{
    private Bitmap[] frames;
    private int frameIndex;
    private float frameTime;
    private long lastFrame;
    private boolean isPlaying = false;

    public Animation(Bitmap[] frames, float animTime)
    {
        this.frames = frames;
        frameIndex = 0;
        frameTime=animTime/frames.length;
    }

    public void play()
    {
        isPlaying=true;
        frameIndex=0;
        lastFrame=System.currentTimeMillis();
    }

    public void stop()
    {
        isPlaying=false;
    }
    public boolean isPlaying()
    {
        return isPlaying;
    }

    public void draw (Canvas canvas, Rect destination)
    {
        if (!isPlaying) return;
            canvas.drawBitmap(frames[frameIndex], null, destination, new Paint());
    }
    public void update()
    {
        if(!isPlaying) return;

        if (System.currentTimeMillis()-lastFrame>frameTime*10000)
        {
            frameIndex++;
            frameIndex = frameIndex>=frames.length ? 0 :frameIndex;
            lastFrame = System.currentTimeMillis();
        }
    }
}
