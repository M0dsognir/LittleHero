package com.example.lazy.littlehero.MainFiles;

import android.app.Activity;
import android.os.Bundle;
import android.service.quicksettings.Tile;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.lazy.littlehero.Tools.Constants;
import com.example.lazy.littlehero.Tools.SaveLoadUtils;
import com.example.lazy.littlehero.Tools.Timer;
import com.example.lazy.littlehero.Tools.Utils;

public class MainActivity extends Activity
{
    long startTime=0,
            endTime=0,
            targetTime=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics dm = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WIDTH=dm.widthPixels;
        Constants.SCREEN_HEIGHT=dm.heightPixels;
        setContentView(new GameWindow(this));
    }

    @Override
    protected void onPause() {
        super.onPause();
        Timer.STOP_GAME();
        Log.i("Game", "STOPPED");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Timer.SHOULD_I_RESUME_GAME)
        {
            Timer.START_GAME();
            Log.i("Game", "STARTED");
        }
    }

    /*@Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Timer.START_GAME();
    }*/

    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            endTime=System.currentTimeMillis();
            if (endTime-startTime>targetTime)
            {
                Toast.makeText(Constants.CURRENT_CONTEXT, "Do you want to quit the game? \nClick \"back\" button again to confirm.", Toast.LENGTH_LONG).show();
                startTime=System.currentTimeMillis();
            }
            else
            {

                System.exit(0);
            }
        }
        return true;
    }
}
