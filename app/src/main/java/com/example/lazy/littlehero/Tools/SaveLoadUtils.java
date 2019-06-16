package com.example.lazy.littlehero.Tools;

import android.graphics.Point;
import android.util.Log;

import com.example.lazy.littlehero.Characters.Enemies.Enemy;
import com.example.lazy.littlehero.Characters.Player;
import com.example.lazy.littlehero.Items.Eq;
import com.example.lazy.littlehero.Items.Item;
import com.example.lazy.littlehero.Items.ItemManager;
import com.example.lazy.littlehero.Maps.Map;
import com.example.lazy.littlehero.Maps.MapsManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SaveLoadUtils
{
    ///////////~~SAVE~~////////////
    public static void saveGame(Player player, ArrayList<Map> maps)
    {
        saveMainThings();
        savePlayer(player);
        saveEnemiesPerMap(maps);
    }
    private static void saveEnemiesPerMap(ArrayList<Map> maps)
    {
        String saveString = maps.size()+" ";
        for (int x=0; x<maps.size(); x++)
        {
            saveString+= maps.get(x).getEnemies().size()+" ";
            for (Enemy enemy: maps.get(x).getEnemies())
            {
                saveString+=enemy.getCurrentFieldPosition().x+" "+enemy.getCurrentFieldPosition().y+" "+enemy.getId()+" ";
            }
        }
        saveToFile(saveString, "maps.txt");
    }
    private static void saveMainThings()
    {
        String saveString="";
        saveString+=StaticVariable.ACTIVE_MAP+" ";
        saveString+=Timer.GET_TIME_PASSED()+" ";

        saveToFile(saveString, "main_things.txt");
    }
    private static void savePlayer(Player player)
    {
        String saveString = "";
        saveString += player.getName()+" ";
        saveString += player.getShiftX()+" "+player.getShiftY()+" ";
        saveString += player.getHp()+" ";
        saveString += player.getStr()+" "+player.getVit()+" "+player.getWisdom()+" ";
        saveString += player.getCoins()+" ";
        saveString += player.getLvl()+" ";
        saveString += player.getExp()+" ";

        saveString += player.getInventory().size()+" ";
        for (Item i: player.getInventory())
        {
            saveString += i.getItemId()+" "+i.getInventorySlot()+" ";
        }
        saveString += player.getEq().size()+" ";
        for (Eq eq: player.getEq())
        {
            saveString += eq.getItemId()+" "+eq.getEqSlotOccupied()+" ";
            Log.i("test", "zapisujemy eq");
        }
        Log.e("test", saveString);
        saveToFile(saveString, "player.txt");
    }

    ///////////~~LOAD~~////////////
    public static void loadMainThings()
    {
        String[] loadString = loadFromFile("main_things.txt");
        StaticVariable.ACTIVE_MAP =  Integer.parseInt(loadString[0]);
        Timer.SET_TIME_PASSED(Integer.parseInt(loadString[1]));
    }
    public static void loadPlayer(Player player)
    {
        String[] loadString = loadFromFile("player.txt");
        player.setName(loadString[0]);
        player.setNewPlayersPosition(new Point(Integer.parseInt(loadString[1]),Integer.parseInt(loadString[2])));
        player.setHp(Integer.parseInt(loadString[3]));
        player.setStr(Integer.parseInt(loadString[4]));
        player.setVit(Integer.parseInt(loadString[5]));
        player.setWisdom(Integer.parseInt(loadString[6]));
        player.setCoins(Integer.parseInt(loadString[7]));
        player.setLvl(Integer.parseInt(loadString[8]));
        player.setExp(Integer.parseInt(loadString[9]));
        int temp = 10;
        int x=0;
        for (x=0; x<(2*Integer.parseInt(loadString[temp]));)
        {
            player.loadInventory(ItemManager.addItemToList(Integer.parseInt(loadString[++x +temp])),Integer.parseInt(loadString[++x +temp]));
        }
        x++;
        for (int y=0; y<(2*Integer.parseInt(loadString[(x)+temp]));)
        {
            player.loadEq((Eq)ItemManager.addItemToList(Integer.parseInt(loadString[++y +temp+x])),  Integer.parseInt(loadString[++y +temp+x]));
        }
    }
    public static void loadEnemies(MapsManager mapsManager)
    {
        String[] loadString = loadFromFile("maps.txt");
        for (int x=0; x<Integer.parseInt(loadString[0]); x++)
        {
            for (int y=0; y<(3*Integer.parseInt(loadString[1])); y+=3)
            {
                mapsManager.getMaps().get(x).getEnemies().add(new Enemy(new Point(Integer.parseInt(loadString[y+2]),Integer.parseInt(loadString[y+1+2])), mapsManager.getMaps().get(x).getRelativePoint(), Integer.parseInt(loadString[y+2+2])));
            }
        }
    }


    /////////////////////////////
    private static void saveToFile(String saveString, String fileName)
    {
        File file = new File(Constants.CURRENT_CONTEXT.getFilesDir(), fileName);
        if (file.isFile())
        {
            file.delete();
            Log.i(fileName, "deleted");
        }
        try {
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
            bufferWriter.write(saveString);
            bufferWriter.close();
            Log.i(fileName, "saved");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(fileName, "not saved");
        }
    }

    private static String[] loadFromFile(String fileName)
    {
        String [] tempArray;
        String line;
        try {
            File file = new File(Constants.CURRENT_CONTEXT.getFilesDir(), fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            StringBuilder sb = new StringBuilder();
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
            tempArray = sb.toString().split("\\s+");
        }catch (Exception e)
        {
            tempArray = new String[]{"error"};
            e.printStackTrace();
        }
        return tempArray;
    }
}
