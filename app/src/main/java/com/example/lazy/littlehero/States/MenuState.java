package com.example.lazy.littlehero.States;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.view.MotionEvent;
import android.widget.Toast;

import com.example.lazy.littlehero.Tools.Constants;
import com.example.lazy.littlehero.Tools.Graphics;
import com.example.lazy.littlehero.Tools.ImgButton;
import com.example.lazy.littlehero.Tools.StaticVariable;

public class MenuState implements State
{
    StateManager stateManager;

    ImgButton btnContinueGame, btnNewGame, btnSettings, btnCredits, btnExit;

    int btnHeight;
    int btnNumber;

    public MenuState(StateManager stateManager)
    {
        this.stateManager = stateManager;

        btnNumber = 5;
        btnHeight = Constants.SCREEN_HEIGHT/5;

        Graphics.BLUE_FRAME_BITMAP = Bitmap.createScaledBitmap(Graphics.BLUE_FRAME_BITMAP, Constants.SCREEN_WIDTH, btnHeight, false);
        btnContinueGame = new ImgButton(Graphics.BLUE_FRAME_BITMAP, new Point(0,0), Constants.SCREEN_WIDTH, btnHeight);
        btnNewGame = new ImgButton(Graphics.BLUE_FRAME_BITMAP, new Point(0,btnHeight), Constants.SCREEN_WIDTH, btnHeight);
        btnSettings = new ImgButton(Graphics.BLUE_FRAME_BITMAP, new Point(0,2*btnHeight), Constants.SCREEN_WIDTH, btnHeight);
        btnCredits = new ImgButton(Graphics.BLUE_FRAME_BITMAP, new Point(0,3*btnHeight), Constants.SCREEN_WIDTH, btnHeight);
        btnExit = new ImgButton(Graphics.BLUE_FRAME_BITMAP, new Point(0,4*btnHeight), Constants.SCREEN_WIDTH, btnHeight);
    }

    public void update()
    {

    }

    public void draw(Canvas canvas)
    {
        canvas.drawColor(Color.WHITE);

        btnContinueGame.draw(canvas);
        btnNewGame.draw(canvas);
        btnSettings.draw(canvas);
        btnCredits.draw(canvas);
        btnExit.draw(canvas);
    }

    public void receiveTouch(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                if (btnContinueGame.getRect().contains((int)event.getX(), (int)event.getY()))
                {
                    //Toast.makeText(Constants.CURRENT_CONTEXT, "jeszcze nie działa", Toast.LENGTH_SHORT).show();
                    stateManager.startGame();
                    StaticVariable.IS_NEW_GAME = false;
                }
                else if (btnNewGame.getRect().contains((int)event.getX(), (int)event.getY()))
                {
                    StaticVariable.IS_NEW_GAME = true;
                    stateManager.startGame();
                }
                else if (btnSettings.getRect().contains((int)event.getX(), (int)event.getY()))
                {
                    Toast.makeText(Constants.CURRENT_CONTEXT, "jeszcze nie działa", Toast.LENGTH_SHORT).show();
                }
                else if (btnCredits.getRect().contains((int)event.getX(), (int)event.getY()))
                {
                    Toast.makeText(Constants.CURRENT_CONTEXT, "jeszcze nie działa", Toast.LENGTH_SHORT).show();
                }
                else if (btnExit.getRect().contains((int)event.getX(), (int)event.getY()))
                {
                    System.exit(0);
                }
                break;
        }
    }

}
