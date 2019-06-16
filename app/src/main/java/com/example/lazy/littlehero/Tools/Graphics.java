package com.example.lazy.littlehero.Tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.lazy.younglord.R;

import java.util.ArrayList;

public class Graphics
{
    public static Bitmap BLUE_FRAME_BITMAP;
    static Bitmap ARROW_LEFT;
    static Bitmap ARROW_UP;
    static Bitmap ARROW_RIGHT;
    static Bitmap ARROW_DOWN;

    public static Bitmap PLAYER_DUEL_BITMAP;

    public static Bitmap BTN_HIT_BITMAP, BTN_HIT_GREY_BITMAP, BTN_TRY_TO_RUN_BITMAP, BTN_TRY_TO_RUN_GREY_BITMAP,
            BTN_RECEIVE_REWARDS_BITMAP, BTN_RECEIVE_REWARDS_GREY_BITMAP, BTN_RECEIVE_REWARDS_ALL_BITMAP, BTN_RECEIVE_REWARDS_ALL_GREY_BITMAP;

    public static Bitmap BACKPACK_BITMAP, SKILLS_BITMAP, APPLE_BITMAP;

    public static Bitmap TREE_BITMAP;
    public static Bitmap ROCK_BITMAP;

    static Bitmap[] TILES_BITMAP;

    static Bitmap[] OBSTACLES_BITMAP;

    public static Bitmap[][] CHARACTERS_BITMAP;

    public static Bitmap[][] ENEMIES_BITMAP;

    public static Bitmap[] ITEMS_BITMAP;

    public static Bitmap[] POWER_BAR_RED_BITMAPS;
    public static Bitmap[] POWER_BAR_BLUE_BITMAPS;


    public static void loadGraphics()
    {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

        BLUE_FRAME_BITMAP = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.blue_frame, options);
        BTN_HIT_BITMAP = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.btn_hit, options);
        BTN_HIT_GREY_BITMAP = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.btn_hit_grey, options);
        BTN_TRY_TO_RUN_BITMAP = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.btn_run, options);
        BTN_TRY_TO_RUN_GREY_BITMAP = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.btn_run_grey, options);
        BTN_RECEIVE_REWARDS_BITMAP = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.blue_frame, options);

        ARROW_LEFT = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.arrow_left, options);
        ARROW_UP = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.arrow_up, options);
        ARROW_RIGHT = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.arrow_right, options);
        ARROW_DOWN = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.arrow_down, options);

        BACKPACK_BITMAP = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.bag200, options);
        SKILLS_BITMAP = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.book, options);

        PLAYER_DUEL_BITMAP = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.playerfromback, options);

        //DAGGER_BITMAP = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.item_weapon_dagger, options);

        loadTiles();
        loadObstacles();
        loadCharactersGraphics();
        loadEnemiesBitmaps();
        loadPowerBars();
        loadItems();
    }

    private static void loadItems()
    {
        int itemCount = 100;
        ITEMS_BITMAP = new Bitmap[itemCount];
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        ITEMS_BITMAP[1] = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.dagger);
        ITEMS_BITMAP[2] = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.short_sword);
        ITEMS_BITMAP[3] = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.battle_axe);
        ITEMS_BITMAP[4] = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.helmet_leather);
        ITEMS_BITMAP[5] = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.long_sword);
        ITEMS_BITMAP[6] = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.shield_round);
    }

    private static void loadPowerBars()
    {
        POWER_BAR_RED_BITMAPS = new Bitmap[11];
        POWER_BAR_BLUE_BITMAPS = new Bitmap[11];

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        Bitmap bitmapSheet = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.power_bar_red, options);
        for (int x=0; x<11; x++)
        {
            POWER_BAR_RED_BITMAPS[x] = Bitmap.createBitmap(bitmapSheet, 0, x*70, 700, 70);
        }
        bitmapSheet = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.power_bar_blue, options);
        for (int x=0; x<11; x++)
        {
            POWER_BAR_BLUE_BITMAPS[x] = Bitmap.createBitmap(bitmapSheet, 0, x*70, 700, 70);
        }
    }

    private static void loadEnemiesBitmaps()
    {
        int BITMAPS_PET_ENEMY = 1;
        int NUMBER_OF_ENEMIES = 4;
        ENEMIES_BITMAP = new Bitmap[NUMBER_OF_ENEMIES][BITMAPS_PET_ENEMY];
        int bitmapResize = 2;

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

        Bitmap enemiesSheet = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.enemies_sheet, options);

        for (int x=0; x<NUMBER_OF_ENEMIES; x++)
        {
            for (int y=0; y<BITMAPS_PET_ENEMY; y++)
            {
                ENEMIES_BITMAP[x][y] = Bitmap.createBitmap(enemiesSheet, y*Constants.TILE_SIZE*bitmapResize, x*Constants.TILE_SIZE*bitmapResize, Constants.TILE_SIZE*bitmapResize, Constants.TILE_SIZE*bitmapResize);
            }
        }
    }
    private static void loadCharactersGraphics()
    {
        int BITMAPS_PER_CHARACTER = 9;
        int NUMBER_OF_CHARACTERS = 1;

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

        Bitmap charactersSheet = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.characters100, options);
        CHARACTERS_BITMAP = new Bitmap[NUMBER_OF_CHARACTERS][BITMAPS_PER_CHARACTER];
        for (int x=0; x<BITMAPS_PER_CHARACTER; x++)
        {
            for (int y=0; y<NUMBER_OF_CHARACTERS; y++)
            {
                CHARACTERS_BITMAP[y][x] = Bitmap.createBitmap(charactersSheet, x*Constants.TILE_SIZE,y*Constants.TILE_SIZE, Constants.TILE_SIZE, Constants.TILE_SIZE);
            }
        }

    }
    private static void loadObstacles()
    {
        OBSTACLES_BITMAP = new Bitmap[40];
        int bitmapSize = 150;

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

        Bitmap obstaclesSheet= BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.obstaclesheet100, options);
        Bitmap treesSheet = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.treespritesheet, options);
        //TREE_BITMAP = Bitmap.createBitmap(obstaclesSheet, 0,0, Constants.TILE_SIZE, Constants.TILE_SIZE);
        for (int y=0; y<4; y++)
        {
            for (int x=0; x<10; x++)
            {
                OBSTACLES_BITMAP[x+y*10] = Bitmap.createBitmap(treesSheet, bitmapSize*x,bitmapSize*y, bitmapSize, bitmapSize);
            }
        }
        TREE_BITMAP = Bitmap.createBitmap(treesSheet, 150,0, 150,150);
        ROCK_BITMAP = Bitmap.createBitmap(obstaclesSheet, 0,Constants.TILE_SIZE, Constants.TILE_SIZE, Constants.TILE_SIZE);
    }
    private static void loadTiles()
    {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

        Bitmap tileSheet= BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.tilesheet100, options);

        TILES_BITMAP = new Bitmap[250];

        for (int x=0; x<10; x++)
        {
            for (int y=0; y<10; y++)
            {
                TILES_BITMAP[x+y*10+1] = Bitmap.createBitmap(tileSheet, x*Constants.TILE_SIZE, y*Constants.TILE_SIZE, Constants.TILE_SIZE, Constants.TILE_SIZE);
            }
        }
    }
}

