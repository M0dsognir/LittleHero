package com.example.lazy.littlehero.Tools.Animation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.lazy.littlehero.Tools.Graphics;

import java.util.ArrayList;

public class AnimationManager
{
    private ArrayList<Animation> animations;
    private int index=0;

    public AnimationManager(int characterId)
    {
        animations = new ArrayList<>();
        animations.add(new Animation(new Bitmap[]{Graphics.CHARACTERS_BITMAP[characterId][0]}, 1f));
        animations.add(new Animation(new Bitmap[]{Graphics.CHARACTERS_BITMAP[characterId][3],Graphics.CHARACTERS_BITMAP[characterId][4]}, 0.04f));
        animations.add(new Animation(new Bitmap[]{Graphics.CHARACTERS_BITMAP[characterId][7],Graphics.CHARACTERS_BITMAP[characterId][8]}, 0.04f));
        animations.add(new Animation(new Bitmap[]{Graphics.CHARACTERS_BITMAP[characterId][1],Graphics.CHARACTERS_BITMAP[characterId][2]}, 0.04f));
        animations.add(new Animation(new Bitmap[]{Graphics.CHARACTERS_BITMAP[characterId][5],Graphics.CHARACTERS_BITMAP[characterId][6]}, 0.04f));
    }
    public void playAnimation(int index)
    {
        for(int i = 0; i<animations.size(); i++)
        {
            if(i==index)
            {
                if (!animations.get(index).isPlaying())
                    animations.get(index).play();
            }
            else animations.get(i).stop();
        }
        this.index = index;
    }
    public void draw(Canvas canvas, Rect rect)
    {
        if (animations.get(index).isPlaying())
            animations.get(index).draw(canvas, rect);
    }

    public void update()
    {
        if (animations.get(index).isPlaying())
            animations.get(index).update();
    }

}
