package com.example.lazy.littlehero.Items;

import java.util.ArrayList;

public class ItemManager
{
    public static ArrayList<Item> items;
    public static int[][] eqStats = new int[][]
            {   //att, def, str, vit, wisdom, speed
                    {0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0},
                    {2, 1, 0, 0, 0, 0},
                    {2, 1, 0, 0, 0, 0},
                    {2, 1, 0, 0, 0, 0},
                    {2, 1, 0, 0, 0, 0},
            };
    public static int[][] eqRequirements = new int[][]
            {
                    {0,0,0,0},
                    {1,1,1,0}, //1
                    {1,2,1,0}, //2
                    {0,0,0,0}, //3
                    {0,0,0,0}, //4
                    {0,0,0,0},
                    {0,0,0,0}
            };
    public static int[] whereShouldItBePut = new int[]{99,
            4,4,4,0,4,5};

    public static Item addItemToList(int itemId)
    {
        switch (itemId)
        {
            case 1: return new Dagger();
            case 2: return new ShortSword();
            case 3: return new BattleAxe();
            case 4: return new LeatherHelmet();
            case 5: return new LongSword();
            case 6: return new RoundShield();
            default: return new Crosier();
        }
    }
}
