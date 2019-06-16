package com.example.lazy.littlehero.Tools;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

public class ImgButton
{
    private Bitmap bitmap;
    private Bitmap bitmap2;
    private Rect rect;

    public ImgButton(Bitmap bitmap, Point topLeftRectanglePoint, int width, int height)
    {
        this.bitmap = bitmap;
        rect = new Rect(topLeftRectanglePoint.x, topLeftRectanglePoint.y, topLeftRectanglePoint.x+width, topLeftRectanglePoint.y+height);
    }

    public ImgButton(Bitmap bitmap, Bitmap bitmap2, Point topLeftRectanglePoint, int width, int height)
    {
        this.bitmap = bitmap;
        this.bitmap2 = bitmap2;
        rect = new Rect(topLeftRectanglePoint.x, topLeftRectanglePoint.y, topLeftRectanglePoint.x+width, topLeftRectanglePoint.y+height);
    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(bitmap, null, rect, new Paint());
    }
    public void draw(Canvas canvas, boolean x)
    {
        if (bitmap2!=null)
        {
            if (x)
                canvas.drawBitmap(bitmap, null, rect, new Paint());
            else
                canvas.drawBitmap(bitmap2, null, rect, new Paint());
        }
    }

    public Rect getRect()
    {
        return rect;
    }
}

