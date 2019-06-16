package com.example.lazy.littlehero.Items;

public class BattleAxe extends Eq implements EqInterface
{
    private int whereIsItPut;

    public BattleAxe()
    {
        super(3);
        super.itemName = "battle axe";
        super.itemDescription = "Big and not necessarily easy to use piece of weapon.";
    }

    @Override
    public int getAtt() {
        return ItemManager.eqStats[getItemId()][0];
    }

    @Override
    public int getDef() {
        return ItemManager.eqStats[getItemId()][1];
    }

    @Override
    public int getStr() {
        return ItemManager.eqStats[getItemId()][2];
    }

    @Override
    public int getVit() {
        return ItemManager.eqStats[getItemId()][3];
    }

    @Override
    public int getWisdom() {
        return ItemManager.eqStats[getItemId()][4];
    }

    @Override
    public int getSpeed() {
        return ItemManager.eqStats[getItemId()][5];
    }

    @Override
    public boolean isItOn() {
        return isItOn();
    }

    @Override
    public int whereIsItPut() {
        return 0;
    }
    public String getItemStats()
    {
        return "<font color='red'>Att: "+getAtt()+"</font><font color='blue'><br>Def: "+getDef()+"</font>";
    }
}
