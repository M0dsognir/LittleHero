package com.example.lazy.littlehero.Characters;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.widget.Toast;

import com.example.lazy.littlehero.Items.Eq;
import com.example.lazy.littlehero.Items.Item;
import com.example.lazy.littlehero.Tools.Constants;
import com.example.lazy.littlehero.Tools.Animation.AnimationManager;

import java.util.ArrayList;

public class Player
{
    private Point rectTopLeftPoint;
    private Rect playersRect;

    private int shiftX = 0, shiftY = 0;
    private int movingState = 0;

    private int coins = 0;

    private String name = "Zdzisiu";

    private int lvl = 1, exp = 0;
    public static int[] EXP_TABLE = {10, 100, 500, 1500, 3200, 5500, 8500, 12000};

    private int str = 110, vit = 1, wisdom = 0, unassigned = 1, baseMovingSpeed = 15;
    private int hpOnStart = 30;
    private int hp = 30;

    private boolean isMoving = false;

    private AnimationManager animationManager;

    private ArrayList<Item> inventory;
    private ArrayList<Eq> eq;

    private int inventoryMaxItems = 35;
    private boolean[] inventorySlotOccupied = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,
                                                false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
    private boolean[] eqSlotOccupied = {false, false, false, false, false, false, false, false, false};

    public Player()
    {
        rectTopLeftPoint = new Point(Constants.SCREEN_WIDTH/2 - Constants.TILE_SIZE/2, Constants.SCREEN_HEIGHT/3 - Constants.TILE_SIZE/2);
        playersRect = new Rect(rectTopLeftPoint.x, rectTopLeftPoint.y, Constants.SCREEN_WIDTH/2 - Constants.TILE_SIZE/2+Constants.TILE_SIZE, Constants.SCREEN_HEIGHT/3 - Constants.TILE_SIZE/2+Constants.TILE_SIZE);
        animationManager = new AnimationManager(0);
        inventory = new ArrayList<>();
        eq = new ArrayList<>();
    }

    public void update()
    {
        checkIfPlayersIsMoving();
        animationManager.playAnimation(movingState);
        animationManager.update();
    }

    public void draw(Canvas canvas)
    {
        animationManager.draw(canvas, playersRect);
    }

    public void receiveTouch(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                if (playersRect.contains((int)event.getX(), (int)event.getY()))
                {
                    Toast.makeText(Constants.CURRENT_CONTEXT, "player touch", Toast.LENGTH_SHORT).show();
                }
        }
    }

    //GAME REQUIRED METHODS

    public boolean meetRequirements(Eq eq)
    {
        if (eq.getRequirements()[0]<=lvl&&eq.getRequirements()[1]<=str&&eq.getRequirements()[2]<=vit&&eq.getRequirements()[3]<=wisdom)
        {
            return true;
        }
        else return false;
    }
    public Rect getPlayersRect()
    {
        return playersRect;
    }
    public Point getRectTopLeftPoint()
    {
        return rectTopLeftPoint;
    }

    public void addStr(int a)
    {
        str+=a;
    }
    public void addVit(int a)
    {
        vit+=a;
    }
    public void addWis(int a)
    {
        wisdom+=a;
    }
    public void addUnassigned(int a)
    {
        unassigned +=a;
    }
    public void addOrRemoveCoins(int change)
    {
        coins+=change;
    }
    public void addExp(int a)
    {
        exp+=a;
    }

    //inv
    public void addLootToPlayersInventory(ArrayList<Item> loot)
    {
        for (Item i: loot)
        {
            addLootToPlayersInventory(i);
        }
    }
    public void addLootToPlayersInventory(Item item)
    {
        inventory.add(item);
        if (inventory.isEmpty())
        {
            item.setInventorySlot(0);
            inventorySlotOccupied[0] = true;
        }
        else
        {
            for (int x=0; x<inventorySlotOccupied.length; x++)
            {
                if (inventorySlotOccupied[x]);
                else
                {
                    item.setInventorySlot(x);
                    inventorySlotOccupied[x] = true;
                    return;
                }
            }
        }
    }
    public void checkInventorySlotOccupied()
    {
        for (int x=0; x<inventorySlotOccupied.length; x++)
        {
            inventorySlotOccupied[x]=false;
        }
        for (int x=0; x<inventory.size(); x++)
        {
            inventorySlotOccupied[inventory.get(x).getInventorySlot()] = true;
        }
    }
    public void checkEqSlotOccupied()
    {
        for (int x=0; x<eqSlotOccupied.length; x++)
        {
            eqSlotOccupied[x]=false;
        }
        for (int x=0; x<eq.size(); x++)
        {
            eqSlotOccupied[eq.get(x).getEqSlotOccupied()] = true;
        }
    }
    public boolean[] getInventorySlotOccupied()
    {
        return inventorySlotOccupied;
    }
    public boolean[] getEqSlotOccupied()
    {
        return eqSlotOccupied;
    }

    //MOVING
    public boolean isMoving() {
        return isMoving;
    }
    public void setMovingState(int direction)
    {
        movingState = direction;
    }
    private void checkIfPlayersIsMoving()
    {
        if (movingState == 0) isMoving = false;
        else isMoving = true;
    }
    public void move(int moveX, int moveY)
    {
        shiftX += moveX;
        shiftY += moveY;
    }
    public int getShiftX()
    {
        return shiftX;
    }
    public int getShiftY()
    {
        return shiftY;
    }
    public void setNewPlayersPosition(Point p)
    {
        shiftX = p.x;
        shiftY = p.y;
    }

    //GETTERS
    public int getBaseMovingSpeed()
    {
        return baseMovingSpeed;
    }
    public int getTotalAttackPower()
    {
        return getAttackPowerFromEq()+str;
    }
    private int getAttackPowerFromEq()
    {
        return 0;
    }
    public int getTotalDefence()
    {
        return ((str/2)+(vit/3)+getDefenceFromEq());
    }
    private int getDefenceFromEq()
    {
        return 0;
    }
    public int getHp()
    {
        return hp;
    }
    public int getMaxHp()
    {
        return hpOnStart+vit*10+hpFromEq();
    }
    private int hpFromEq()
    {
        return 0;
    }
    private int getWisdomFromEq()
    {
        return 0;
    }
    public void getHit(int damage)
    {
        hp-=damage;
    }
    public boolean checkIfPlayerCanTakeThatManyItems(int i)
    {
        if (inventory.size()+i>inventoryMaxItems) return false;
        else return true;
    }
    public ArrayList<Item> getInventory()
    {
        return inventory;
    }
    public ArrayList<Eq> getEq() {
        return eq;
    }
    public String getName()
    {
        return name;
    }
    public int getStr()
    {
        return str;
    }
    public int getVit()
    {
        return vit;
    }
    public int getWisdom()
    {
        return wisdom+getWisdomFromEq();
    }
    public int getUnassigned() {
        return unassigned;
    }
    public int getCoins() {
        return coins;
    }
    public int getLvl() {
        return lvl;
    }
    public int getExp() {
        return exp;
    }

    //to load char
    public void setName(String name)
    {
        this.name = name;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public void setStr(int str) {
        this.str = str;
    }
    public void setVit(int vit) {
        this.vit = vit;
    }
    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }
    public void loadInventory(Item i, int slotOccupied)
    {
        addLootToPlayersInventory(i);
        i.setInventorySlot(slotOccupied);
        checkInventorySlotOccupied();
    }
    public void loadEq(Eq i, int slotOccupied)
    {
        eq.add(i);
        i.setEqSlotOccupied(slotOccupied);
        checkEqSlotOccupied();
    }
    public void setLvl(int lvl) {
        this.lvl = lvl;
    }
    public void setExp(int exp) {
        this.exp = exp;
    }
    public void setCoins(int coins) {
        this.coins = coins;
    }






}
