package com.example.lazy.littlehero.Tools;

import android.util.TypedValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Utils
{
    public static String convertStreamToString(InputStream inputStream)
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();  //is
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static String[] splitString(String longString)
    {
        longString = longString.replace(", ", " ");
        //Log.e("long string", longString);
        return longString.split("\\s+");
    }
    public static int[] parseInt(String[] simpleCharacters)
    {
        int[] intTable = new int[simpleCharacters.length];
        for (int i=0; i<simpleCharacters.length; i++)
        {
            try
            {
                intTable[i]=Integer.parseInt(simpleCharacters[i]);
            }catch (NumberFormatException e)
            {
                e.printStackTrace();
                intTable[i]=0;
            }
        }
        return intTable;
    }

    public static int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,   dp,Constants.CURRENT_CONTEXT.getResources().getDisplayMetrics());
    }
    public static float px2dp(float px)  {
        return (float) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px,Constants.CURRENT_CONTEXT.getResources().getDisplayMetrics());
    }
}
