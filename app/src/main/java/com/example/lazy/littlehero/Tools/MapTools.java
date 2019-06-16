package com.example.lazy.littlehero.Tools;

import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.example.lazy.littlehero.Characters.Player;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MapTools
{
    private static Random r = new Random();

    public static int[] convertTxtFileToMapIntTable(String fileName)
    {
        int [] mapIntTable;

        AssetManager assetManager = Constants.CURRENT_CONTEXT.getAssets();
        try {
            InputStream inputStream = assetManager.open(fileName);
            String mapLongString = Utils.convertStreamToString(inputStream);
            String[] mapSimpleCharacters = Utils.splitString(mapLongString);
            mapIntTable = Utils.parseInt(mapSimpleCharacters);
        } catch (IOException e) {
            e.printStackTrace();
            mapIntTable = new int[1];
        }
        return mapIntTable;
    }

    public static void createArrayMap(ArrayList<MapField> map, int[] mapInt, int[] obstacleInt, RelativePoint relativePoint)
    {
        for (int y=0; y<mapInt[1]; y++)
        {
            for (int x=0; x<mapInt[0]; x++)
            {
                map.add(new MapField(x, y, mapInt[x+y*mapInt[1]+2],obstacleInt[x+y*mapInt[1]+2], relativePoint));
            }
        }
    }

    public static void drawMap(Canvas canvas, Player player, ArrayList<MapField> map)
    {
        int a = player.getShiftX()/Constants.TILE_SIZE;
        int b = player.getShiftY()/Constants.TILE_SIZE;
        for (int x = a-Constants.VISIBILITY_X; x<=a+Constants.VISIBILITY_X; x++)
        {
            if (x>=0&&x<=99)
            {
                for (int y = b-Constants.VISIBILITY_Y; y<=b+Constants.VISIBILITY_Y*2-1; y++)
                {
                    if (y>=0&&y<=99)
                    {
                        map.get(x+y*100).update();
                        map.get(x+y*100).draw(canvas);
                    }
                }
            }
        }
    }
    public static void drawObstacles(Canvas canvas, Player player, ArrayList<MapField> map)
    {
        int a = player.getShiftX()/Constants.TILE_SIZE;
        int b = player.getShiftY()/Constants.TILE_SIZE;
        for (int x = a-Constants.VISIBILITY_X; x<=a+Constants.VISIBILITY_X; x++)
        {
            if (x>=0&&x<=99)
            {
                for (int y = b-Constants.VISIBILITY_Y; y<=b+Constants.VISIBILITY_Y*2-1; y++)
                {
                    if (y>=0&&y<=99)
                    {
                        //map.get(x+y*100).update();
                        map.get(x+y*100).drawObstacle(canvas);
                    }
                }
            }
        }
    }
}
