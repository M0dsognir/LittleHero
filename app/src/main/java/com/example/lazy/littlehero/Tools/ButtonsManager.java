package com.example.lazy.littlehero.Tools;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.example.lazy.littlehero.Tools.Dialogs.InventoryDialog;
import com.example.lazy.littlehero.Tools.Dialogs.SkillDialog;


public class ButtonsManager
{
    private ImgButton btnArrowLeft, btnArrowUp, btnArrowRight, btnArrowDown, btnBackpack, btnSkills;
    private Paint paint;
    private Rect whiteBottomRect;

    private MoveManagerAndCollisionDetector moveManagerAndCollisionDetector;

    public ButtonsManager(MoveManagerAndCollisionDetector moveManagerAndCollisionDetector)
    {
        paint = new Paint();
        paint.setColor(Color.WHITE);
        whiteBottomRect = new Rect(0, Constants.SCREEN_HEIGHT-200, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT+100);

        btnArrowLeft = new ImgButton(Graphics.ARROW_LEFT, new Point(Constants.SCREEN_WIDTH-475, Constants.SCREEN_HEIGHT-475), 150, 150);
        btnArrowUp = new ImgButton(Graphics.ARROW_UP, new Point(Constants.SCREEN_WIDTH-325, Constants.SCREEN_HEIGHT-575), 150, 150);
        btnArrowRight = new ImgButton(Graphics.ARROW_RIGHT, new Point(Constants.SCREEN_WIDTH-175, Constants.SCREEN_HEIGHT-475), 150, 150);
        btnArrowDown = new ImgButton(Graphics.ARROW_DOWN, new Point(Constants.SCREEN_WIDTH-325, Constants.SCREEN_HEIGHT-375), 150, 150);

        btnBackpack = new ImgButton(Graphics.BACKPACK_BITMAP, new Point(0 ,Constants.SCREEN_HEIGHT-200), 200, 200);
        btnSkills = new ImgButton(Graphics.SKILLS_BITMAP, new Point(btnBackpack.getRect().right, btnBackpack.getRect().top), 200, 200);

        this.moveManagerAndCollisionDetector = moveManagerAndCollisionDetector;


    }

    public void update()
    {

    }


    public void draw(Canvas canvas)
    {
        canvas.drawRect(whiteBottomRect, paint);
        btnArrowLeft.draw(canvas);
        btnArrowUp.draw(canvas);
        btnArrowRight.draw(canvas);
        btnArrowDown.draw(canvas);
        btnBackpack.draw(canvas);
        btnSkills.draw(canvas);
    }

    public void receiveTouch(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:

                if (btnArrowLeft.getRect().contains((int)event.getX(), (int)event.getY()))
                {
                    moveManagerAndCollisionDetector.movePlayer(1);
                }
                else if(btnArrowUp.getRect().contains((int)event.getX(), (int)event.getY()))
                {
                    moveManagerAndCollisionDetector.movePlayer(2);
                }
                else if (btnArrowRight.getRect().contains((int)event.getX(), (int)event.getY()))
                {
                    moveManagerAndCollisionDetector.movePlayer(3);
                }
                else if (btnArrowDown.getRect().contains((int)event.getX(), (int)event.getY()))
                {
                    moveManagerAndCollisionDetector.movePlayer(4);
                }
                else if (btnBackpack.getRect().contains((int)event.getX(), (int)event.getY()))
                {
                    InventoryDialog.showInventoryDialog(moveManagerAndCollisionDetector.player, false);
                    Timer.SHOULD_I_RESUME_GAME = false;
                    Timer.STOP_GAME();
                }
                else if (btnSkills.getRect().contains((int)event.getX(), (int)event.getY()))
                {
                    SkillDialog.showSkillsDialog(moveManagerAndCollisionDetector.player);
                    Timer.SHOULD_I_RESUME_GAME = false;
                    Timer.STOP_GAME();
                }
                break;
            case MotionEvent.ACTION_UP:
                moveManagerAndCollisionDetector.playerCanGoAnywhere();
                moveManagerAndCollisionDetector.movePlayer(0);
                break;
        }
    }

    public boolean wasButtonTouched(MotionEvent e)
    {
        if(btnArrowUp.getRect().contains((int)e.getX(), (int)e.getY()) ||
                btnArrowRight.getRect().contains((int)e.getX(), (int)e.getY()) ||
                btnArrowDown.getRect().contains((int)e.getX(), (int)e.getY()) ||
                btnArrowLeft.getRect().contains((int)e.getX(), (int)e.getY()) ||
                btnBackpack.getRect().contains((int)e.getX(), (int)e.getY()) ||
                btnSkills.getRect().contains((int)e.getX(), (int)e.getY()))
            return true;
        else return false;
    }
    public boolean wasArrowTouched(MotionEvent e)
    {
        if(btnArrowUp.getRect().contains((int)e.getX(), (int)e.getY()) ||
                btnArrowRight.getRect().contains((int)e.getX(), (int)e.getY()) ||
                btnArrowDown.getRect().contains((int)e.getX(), (int)e.getY()) ||
                btnArrowLeft.getRect().contains((int)e.getX(), (int)e.getY()))
            return true;
        else return false;
    }

    /*private void showBackpackDialog()
    {
        Dialog dialog = new Dialog(Constants.CURRENT_CONTEXT);
        dialog.setContentView(R.layout.inventory_layout);
        dialog.getWindow().setLayout(1100,800);
        dialog.setOnCancelListener(
                new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        Log.i("wychodzimy", "z dialogu");
                    }
                }
        );
        dialog.show();
    }*/
    public void stopPlayer()
    {
        moveManagerAndCollisionDetector.playerCanGoAnywhere();
        moveManagerAndCollisionDetector.movePlayer(0);
    }
}
