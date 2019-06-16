package com.example.lazy.littlehero.Tools;

public class Timer
{
    public static long gameTimeRunning;
    public static long gameTimeOverall=0;
    public static long timeGameStart;
    public static long timeGameStop;
    public static boolean IS_GAME_RUNNING;
    public static boolean SHOULD_I_RESUME_GAME = true;

    public static void START_GAME()
    {
        gameTimeRunning = 0;
        timeGameStart = System.currentTimeMillis();
        IS_GAME_RUNNING = true;
    }
    public static void STOP_GAME()
    {
        timeGameStop = System.currentTimeMillis();
        gameTimeOverall += gameTimeRunning;
        gameTimeRunning = 0;
        IS_GAME_RUNNING = false;
    }
    public static void update()
    {
        if (IS_GAME_RUNNING)
        gameTimeRunning = System.currentTimeMillis() - timeGameStart;
    }
    public static long GET_TIME_PASSED()
    {
        return gameTimeOverall+gameTimeRunning;
    }
    public static void SET_TIME_PASSED(long time)
    {
        gameTimeOverall=time;
    }
}


