package com.example.lazy.littlehero.Characters.Enemies;

public class EnemiesTraits
{

    public static int[][] enemiesStats = {
       //att, def, wis, hp, speed, exp, isItBig, isBoss
            {3, 2, 1, 10, 3, 5, 0, 0},               // rat
            {4, 1, 1, 10, 7, 7, 0, 0},               // crazy rat
            {5, 3, 3, 20, 7, 10, 0, 0},              // wolf
            {10, 20, 5, 50, 3, 20, 0, 1},            // giantRat
            {},
            {}


                                        };
    public static int[][][] enemiesPossibleLoot = {
            {{0, 1, 4},{1, 90},{4, 90},{5, 90},{6, 90}},                         // rat
            {{0, 2, 6},{1, 15}},                         // crazy rat
            {{5}},                        // wolf
            {{10}},                       // giantRat
            {},
            {},

                                        };
    public static int[] enemiesSpawnTime = {
            20,
            20,
            20,
            20
                                        };
    public static String[] enemiesName = {
            "rat",
            "crazy rat",
            "wolf",
            "giant rat"
    };
    public static int[] maxEnemiesPerMap = {
            20,
            10,
            20
                                        };

}
