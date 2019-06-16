package com.example.lazy.littlehero.Items;

public class ShortSword extends Eq implements EqInterface
{
    private int att = 3;
    private int def = 1;
    private int whereIsItPut;

    public ShortSword()
    {
        super(2);
        super.itemName = "short sword";
        super.itemDescription = "Small, but sharp.";
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
        if (whereIsItPut==0) return false;
        else return true;
    }

    @Override
    public int whereIsItPut() {
        return whereIsItPut;
    }
    /*public String getItemStats()
    {
        return "<font color='red'>Att: "+getAtt()+"</font><font color='blue'><br>Def: "+getDef()+"</font>";
    }*/
}
