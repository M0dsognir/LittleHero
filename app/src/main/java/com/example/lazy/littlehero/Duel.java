package com.example.lazy.littlehero;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lazy.littlehero.Characters.Enemies.EnemiesTraits;
import com.example.lazy.littlehero.Items.Item;
import com.example.lazy.littlehero.Items.ItemManager;
import com.example.lazy.littlehero.Maps.Map;
import com.example.lazy.littlehero.Tools.Constants;
import com.example.lazy.littlehero.Tools.Dialogs.ItemInfoDialog;
import com.example.lazy.littlehero.Tools.Graphics;
import com.example.lazy.littlehero.Tools.ImgButton;
import com.example.lazy.littlehero.Tools.Dialogs.InventoryDialog;
import com.example.lazy.littlehero.Tools.Timer;
import com.example.lazy.littlehero.Characters.Enemies.Enemy;
import com.example.lazy.littlehero.Characters.Player;
import com.example.lazy.younglord.R;

import java.util.ArrayList;
import java.util.Random;

public class Duel
{
    public static boolean shouldICheckInvCap = false;

    private int numberOfItemsSelected = 0;
    private Player player;
    private Map map;
    private Enemy enemy;

    private Rect playersRect, enemiesRect;
    private Random random;

    private ImgButton btnHit, btnTryToRun;

    private boolean weFightingStill = true, canPlayerAttack = false, canEnemyAttack = false, canPlayerTryToRun = false, playerWon;

    private long lastPlayerAttacksTime, timeBetweenPlayerAttacks = 3000;
    private long lastPlayerTriedToRunTime, timeBetweenPlayerTryToRun = 10000;
    private long timeToShowHITorBLOCKText = 1000;
    private long lastEnemyAttacksTime, timeBetweenEnemyAttacks = 3000;
    private long lastRandomizingPowerTime, timeBetweenRandomizingPower = 200;
    private int enemyPower = 5, playerPower = 5;
    private int chanceThatEnemyAttacks, maxChanceThatEnemyAttacks=70, minChanceThatEnemyAttacks=30;

    private boolean shouldTextBeDrawnAfterEnemiesAttack = false, shouldTextBeDrawnAfterPlayersAttack = false;
    private long timeWhenTextWasShownAfterEnemiesAttack, timeWhenTextWasShownAfterPlayersAttack;
    private String drawTextAfterPlayersAttack = "", drawTextAfterEnemiesAttack = "";

    private ArrayList<Item> loot;
    private int coinLooted = 0;

    private Rect enemiesPowerRect, playersPowerRect;
    private int rectWidth = 700, rectHeight = 70;

    ImageButton btnTakeSelected;
    ImageButton btnTakeAll;
    Dialog dialog;

    public Duel(Player player, Map map)
    {
        this.player = player;
        this.map = map;
        playersRect = new Rect(100, Constants.SCREEN_HEIGHT/2, 650, Constants.SCREEN_HEIGHT/2+500);
        enemiesRect = new Rect(Constants.SCREEN_WIDTH-600, 100, Constants.SCREEN_WIDTH-300, 600);
        btnHit = new ImgButton(Graphics.BTN_HIT_BITMAP, Graphics.BTN_HIT_GREY_BITMAP, new Point(Constants.SCREEN_WIDTH-400, Constants.SCREEN_HEIGHT-450), 300, 100);
        btnTryToRun = new ImgButton(Graphics.BTN_TRY_TO_RUN_BITMAP, Graphics.BTN_TRY_TO_RUN_GREY_BITMAP, new Point(Constants.SCREEN_WIDTH-400, Constants.SCREEN_HEIGHT-300), 300, 100);
        random = new Random();
        lastPlayerAttacksTime = System.currentTimeMillis();
        lastEnemyAttacksTime = System.currentTimeMillis();
        timeWhenTextWasShownAfterPlayersAttack = System.currentTimeMillis();
        timeWhenTextWasShownAfterEnemiesAttack = System.currentTimeMillis();
        lastPlayerTriedToRunTime = System.currentTimeMillis();
        loot = new ArrayList<>();
        enemiesPowerRect = new Rect(Constants.SCREEN_WIDTH/2-rectWidth/2, Constants.SCREEN_HEIGHT/2-rectHeight-150,
                                Constants.SCREEN_WIDTH/2+rectWidth/2, Constants.SCREEN_HEIGHT/2-150);
        playersPowerRect = new Rect(Constants.SCREEN_WIDTH/2-rectWidth/2, Constants.SCREEN_HEIGHT/2-140,
                                Constants.SCREEN_WIDTH/2+rectWidth/2, Constants.SCREEN_HEIGHT/2+rectHeight-140);
    }

    public void update()
    {
        if (weFightingStill)
        {
            if (System.currentTimeMillis() - lastPlayerAttacksTime >= timeBetweenPlayerAttacks)
            {
                canPlayerAttack = true;
            }
            else canPlayerAttack = false;
            if (System.currentTimeMillis() - lastEnemyAttacksTime >= timeBetweenEnemyAttacks)
            {
                canEnemyAttack = true;
            }
            else canEnemyAttack = false;
            if (System.currentTimeMillis() - lastPlayerTriedToRunTime >= timeBetweenPlayerTryToRun)
            {
                canPlayerTryToRun = true;
            }
            else canPlayerTryToRun = false;
            if (System.currentTimeMillis()-lastRandomizingPowerTime>=timeBetweenRandomizingPower)
            {
                randomizeEnemyPower();
                randomizePlayerPower();
                lastRandomizingPowerTime = System.currentTimeMillis();
            }
            if (System.currentTimeMillis() - timeWhenTextWasShownAfterPlayersAttack < timeToShowHITorBLOCKText)
            {
                shouldTextBeDrawnAfterPlayersAttack = true;
            }
            else
            {
                shouldTextBeDrawnAfterPlayersAttack = false;
            }
            if(System.currentTimeMillis() - timeWhenTextWasShownAfterEnemiesAttack < timeToShowHITorBLOCKText)
            {
                shouldTextBeDrawnAfterEnemiesAttack = true;
            }
            else
            {
                shouldTextBeDrawnAfterEnemiesAttack = false;
            }
            if (canEnemyAttack)
            {
                Log.i("enemy", "moze atakowac");
                enemyAttacks();
            }
        }
        if (dialog!=null&&shouldICheckInvCap)
        {
            if (player.checkIfPlayerCanTakeThatManyItems(loot.size()))
            {
                btnTakeAll.setBackground(Constants.CURRENT_CONTEXT.getDrawable(R.drawable.btn_take_all));
            }
            else btnTakeAll.setBackground(Constants.CURRENT_CONTEXT.getDrawable(R.drawable.btn_take_all_grey));
            if (player.checkIfPlayerCanTakeThatManyItems(numberOfItemsSelected))
            {
                btnTakeSelected.setBackground(Constants.CURRENT_CONTEXT.getDrawable(R.drawable.btn_take));
            }
            else btnTakeSelected.setBackground(Constants.CURRENT_CONTEXT.getDrawable(R.drawable.btn_take_grey));
            shouldICheckInvCap = false;
        }
    }

    public void draw(Canvas canvas)
    {
        canvas.drawColor(Color.WHITE);
        btnHit.draw(canvas, canPlayerAttack);
        btnTryToRun.draw(canvas, canPlayerTryToRun);

        Paint paint = new Paint();
        paint.setTextSize(60);

        canvas.drawBitmap(Graphics.ENEMIES_BITMAP[enemy.getId()][0],null, enemiesRect, paint);
        canvas.drawBitmap(Graphics.PLAYER_DUEL_BITMAP,null, playersRect, paint);

        paint.setColor(Color.GREEN);
        canvas.drawText("HP: "+player.getHp(), playersRect.right, playersRect.top+paint.getTextSize(), paint);
        canvas.drawText("HP: "+enemy.getHp(), enemiesRect.right, enemiesRect.top+paint.getTextSize(), paint);
        paint.setColor(Color.RED);
        canvas.drawText("ATT: "+player.getTotalAttackPower(), playersRect.right, playersRect.top+paint.getTextSize()*2, paint);
        canvas.drawText("ATT: "+enemy.getAttackStrength(), enemiesRect.right, enemiesRect.top+paint.getTextSize()*2, paint);
        paint.setColor(Color.BLUE);
        canvas.drawText("DEF: "+player.getTotalDefence(), playersRect.right, playersRect.top+paint.getTextSize()*3, paint);
        canvas.drawText("DEF: "+enemy.getDefence(), enemiesRect.right, enemiesRect.top+paint.getTextSize()*3, paint);

        canvas.drawBitmap(Graphics.POWER_BAR_RED_BITMAPS[enemyPower], null,enemiesPowerRect, new Paint());
        canvas.drawBitmap(Graphics.POWER_BAR_BLUE_BITMAPS[playerPower], null,playersPowerRect, new Paint());
    }

    public void receiveTouch(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            {
                if (btnHit.getRect().contains((int)event.getX(), (int)event.getY()))
                {
                    if (canPlayerAttack)
                    {
                        playerAttacks();
                        lastPlayerAttacksTime = System.currentTimeMillis();
                    }
                    else Toast.makeText(Constants.CURRENT_CONTEXT, "nie atakujemy", Toast.LENGTH_SHORT).show();
                }
                else if (btnTryToRun.getRect().contains((int)event.getX(), (int)event.getY()))
                {
                    if (canPlayerTryToRun)
                    {
                        tryToRun();
                        lastPlayerTriedToRunTime = System.currentTimeMillis();
                    }
                    else Toast.makeText(Constants.CURRENT_CONTEXT, "nie uciekamy", Toast.LENGTH_SHORT).show();
                }
            }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
    }
    private void enemyAttacks()
    {
        chanceThatEnemyAttacks = (int)((((enemy.getAttackStrength()+enemyPower)-(player.getTotalAttackPower()+playerPower)*((enemy.getWisdom()-player.getWisdom())))*0.5f
                /((enemy.getAttackStrength()+enemyPower)+(player.getTotalAttackPower()+playerPower)+enemy.getWisdom()+player.getWisdom()))*100);
        Log.i("chanceThatEnemyAttacks", chanceThatEnemyAttacks+"");
        Log.i("chanceThatEnemyAttacks", (((enemy.getAttackStrength()+enemyPower)-(player.getTotalAttackPower()+playerPower)*((enemy.getWisdom()-player.getWisdom())))*0.5f
                /((enemy.getAttackStrength()+enemyPower)+(player.getTotalAttackPower()+playerPower)+enemy.getWisdom()+player.getWisdom()))*100+"");
        if(chanceThatEnemyAttacks>maxChanceThatEnemyAttacks) chanceThatEnemyAttacks = maxChanceThatEnemyAttacks;
        else if (chanceThatEnemyAttacks<minChanceThatEnemyAttacks) chanceThatEnemyAttacks = minChanceThatEnemyAttacks;
        Log.i("chanceThatEnemyAttacks", chanceThatEnemyAttacks+"");
        if (random.nextInt(100)<chanceThatEnemyAttacks)
        {
            Log.i("enemy atakuje", (enemy.getAttackStrength()+enemyPower)+" vs. "+(player.getTotalDefence()+playerPower));
            if ((enemyPower+enemy.getAttackStrength())>(player.getTotalDefence()+playerPower))
            {
                player.getHit((enemyPower+enemy.getAttackStrength())-(player.getTotalDefence()+playerPower));
                drawTextAfterEnemiesAttack = "HIT";
                if (player.getHp()<=0) fightIsOver();
                Log.i("enemy trafil", (enemy.getAttackStrength()+enemyPower)-(player.getTotalDefence()+playerPower)+"");
            }
            else
            {
                drawTextAfterEnemiesAttack = "BLOCK";
                Log.i("enemy trafil", "blok");
            }
            shouldTextBeDrawnAfterEnemiesAttack = true;
            timeWhenTextWasShownAfterEnemiesAttack = System.currentTimeMillis();
            Log.i("koniec", "ataku");
        }
        lastEnemyAttacksTime = System.currentTimeMillis();
    }
    private void tryToRun()
    {
        if (random.nextInt(player.getBaseMovingSpeed()+enemy.getMovingSpeed())>player.getBaseMovingSpeed())
        {
            Toast.makeText(Constants.CURRENT_CONTEXT, "You didn't managed to run away", Toast.LENGTH_SHORT).show();
        }
        else
        {
            //Toast.makeText(Constants.CURRENT_CONTEXT, "Tutaj będzie ucieczka", Toast.LENGTH_SHORT).show();
            map.getEnemies().remove(enemy);
            endDuel();
        }
    }
    private void playerAttacks()
    {
        if (playerPower+player.getTotalAttackPower()>enemyPower+enemy.getDefence())
        {
            drawTextAfterPlayersAttack = "HIT";
            enemy.gotHit(playerPower+player.getTotalAttackPower()-(enemyPower+enemy.getDefence()));
            if (enemy.getHp()<=0) fightIsOver();
        }
        else
        {
            drawTextAfterPlayersAttack = "BLOCK";
        }
        timeWhenTextWasShownAfterPlayersAttack = System.currentTimeMillis();
        shouldTextBeDrawnAfterPlayersAttack = true;
    }
    private void randomizeEnemyPower()
    {
        int addOrSubtract = 0;
        switch (random.nextInt(4))
        {
            case 0: addOrSubtract = 1;
                break;
            case 1: addOrSubtract = -1;
                break;
            case 2: //addOrSubtract = -1;
                break;
            case 3:
                break;
        }
        if (addOrSubtract<0)
        {
            if (enemyPower<1)
            {
                addOrSubtract = -addOrSubtract;
            }
        }
        else if (addOrSubtract>0)
        {
            if(enemyPower>9)
            {
                addOrSubtract = -addOrSubtract;
            }
        }
        enemyPower+=addOrSubtract;
    }
    private void randomizePlayerPower()
    {
        int addOrSubtract = 0;
        switch (random.nextInt(4))
        {
            case 0: addOrSubtract = 1;
                break;
            case 1: addOrSubtract = -1;
                break;
            case 2: //addOrSubtract = -1;
                break;
            case 3:
                break;
        }
        if (addOrSubtract<0)
        {
            if (playerPower<1)
            {
                addOrSubtract = -addOrSubtract;
            }
        }
        else if (addOrSubtract>0)
        {
            if(playerPower>9)
            {
                addOrSubtract = -addOrSubtract;
            }
        }
        playerPower+=addOrSubtract;
    }

    private void fightIsOver()
    {
        weFightingStill = false;
        if (enemy.getHp()<=0) playerWon = true;
        else playerWon = false;
        if (playerWon)
        {
            prepareLoot();
            //LootDialog.shodLootDialog(loot, player, enemy);
            /*final Dialog*/ dialog = new Dialog(Constants.CURRENT_CONTEXT);
            dialog.setContentView(R.layout.loot_layout);
            dialog.setCancelable(false);
            dialog.setOnCancelListener(
                    new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog)
                        {
                            Timer.SHOULD_I_RESUME_GAME = true;
                        }
                    }
            );
            LinearLayout llLoot = dialog.findViewById(R.id.LLLoot);
            TextView tv = dialog.findViewById(R.id.tvXPInfo);
            tv.setText("+"+enemy.getExpToGive()+" XP");
            ImageButton ibToInventory = dialog.findViewById(R.id.ibToInventory);
            ibToInventory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    InventoryDialog.showInventoryDialog(player, true);
                }
            });
            tv.append("      +"+coinLooted+" gold");

            btnTakeSelected = dialog.findViewById(R.id.btnTakeSelected);
            btnTakeSelected.setBackground(Constants.CURRENT_CONTEXT.getDrawable(R.drawable.btn_take));
            btnTakeAll = dialog.findViewById(R.id.btnTakeAll);
            if (player.checkIfPlayerCanTakeThatManyItems(loot.size()))
            {
                btnTakeAll.setBackground(Constants.CURRENT_CONTEXT.getDrawable(R.drawable.btn_take_all));
            }
            else btnTakeAll.setBackground(Constants.CURRENT_CONTEXT.getDrawable(R.drawable.btn_take_all_grey));
            btnTakeSelected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (player.checkIfPlayerCanTakeThatManyItems(numberOfItemsSelected))
                    {
                        for (Item i: loot)
                        {
                            if (i.isSelected()) player.addLootToPlayersInventory(i);
                        }
                        player.addOrRemoveCoins(coinLooted);
                        player.addExp(EnemiesTraits.enemiesStats[enemy.getId()][5]);
                        coinLooted = 0;
                        dialog.cancel();
                        endDuel();
                    }
                    else Toast.makeText(Constants.CURRENT_CONTEXT, "Your inventory cannot hold that much!", Toast.LENGTH_SHORT).show();
                }
            });
            btnTakeAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(player.checkIfPlayerCanTakeThatManyItems(loot.size()))
                    {
                        player.addLootToPlayersInventory(loot);
                        player.addOrRemoveCoins(coinLooted);
                        player.addExp(EnemiesTraits.enemiesStats[enemy.getId()][5]);
                        coinLooted = 0;
                        dialog.cancel();
                        endDuel();
                    }
                    else Toast.makeText(Constants.CURRENT_CONTEXT, "Your inventory cannot hold that much!", Toast.LENGTH_SHORT).show();
                }
            });
            for (int x = 0; x<loot.size(); x++)
            {
                final ImageButton ib = new ImageButton(Constants.CURRENT_CONTEXT);
                ib.setBackground(Constants.CURRENT_CONTEXT.getDrawable(R.drawable.box_200));
                ib.setImageBitmap(Graphics.ITEMS_BITMAP[loot.get(x).getItemId()]);
                ib.setScaleType(ImageView.ScaleType.CENTER_CROP);
                llLoot.addView(ib);
                ib.getLayoutParams().height = 200;
                ib.getLayoutParams().width = 200;
                final int finalX = x;
                ib.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (loot.get(finalX).isSelected())
                        {
                            loot.get(finalX).select();
                            ib.setBackground(Constants.CURRENT_CONTEXT.getDrawable(R.drawable.box_200));
                            numberOfItemsSelected--;
                            Log.i(player.getInventory().size()+"", numberOfItemsSelected+"");
                            if (player.checkIfPlayerCanTakeThatManyItems(numberOfItemsSelected))
                            {
                                btnTakeSelected.setBackground(Constants.CURRENT_CONTEXT.getDrawable(R.drawable.btn_take));
                            }
                            else btnTakeSelected.setBackground(Constants.CURRENT_CONTEXT.getDrawable(R.drawable.btn_take_grey));
                        }
                        else
                        {
                            Log.i(player.getInventory().size()+"", numberOfItemsSelected+"");
                            loot.get(finalX).select();
                            ib.setBackground(Constants.CURRENT_CONTEXT.getDrawable(R.drawable.box_200_selected));
                            numberOfItemsSelected++;
                            if (player.checkIfPlayerCanTakeThatManyItems(numberOfItemsSelected))
                            {
                                btnTakeSelected.setBackground(Constants.CURRENT_CONTEXT.getDrawable(R.drawable.btn_take));
                            }
                            else btnTakeSelected.setBackground(Constants.CURRENT_CONTEXT.getDrawable(R.drawable.btn_take_grey));
                        }
                    }
                });
                ib.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        ItemInfoDialog.showItemInfoDialog(loot.get(finalX));
                        return true;
                    }
                });
            }
            dialog.show();
        }
        else
        {
            Toast.makeText(Constants.CURRENT_CONTEXT, "You've LOST!", Toast.LENGTH_SHORT).show();
        }
    }
    private void prepareLoot()
    {
        for (int x=0; x<enemy.getLootPossible().length; x++)
        {
            if (enemy.getLootPossible()[x][0]==0)
            {
                coinLooted+=random.nextInt(enemy.getLootPossible()[x][2])+enemy.getLootPossible()[x][1];
            }
            else
            {
                if (random.nextInt(100)<enemy.getLootPossible()[x][1])
                loot.add(ItemManager.addItemToList(enemy.getLootPossible()[x][0]));
            }
        }
    }

    public void startDuel(Enemy enemy)
    {
        this.enemy = enemy;
        weFightingStill = true;
        lastPlayerAttacksTime = System.currentTimeMillis();
        lastPlayerTriedToRunTime = System.currentTimeMillis();
        lastRandomizingPowerTime = System.currentTimeMillis();
        lastEnemyAttacksTime = System.currentTimeMillis();
        loot.clear();
        numberOfItemsSelected = 0;
        if (EnemiesTraits.enemiesStats[enemy.getId()][6]==0)
        {
            //duży
        }
        else
        {
            enemiesRect.set(Constants.SCREEN_WIDTH/2, 100, Constants.SCREEN_WIDTH/2+200, 300);
        }
        Timer.SHOULD_I_RESUME_GAME = false;
    }
    private void endDuel()
    {
        map.finishDuel();
        map.getEnemies().remove(enemy);
        Timer.START_GAME();
    }
}
