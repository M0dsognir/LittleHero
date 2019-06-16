package com.example.lazy.littlehero.Items;

public class Dagger extends Eq implements EqInterface
{
    private int att = 0;
    private int def = 3;
    private int whereIsItPut;

    public Dagger() {
        super(1);
        super.itemName ="dagger";
        super.itemDescription = "Not an impressive tool, but can do the work.\n-If you know what i mean.";
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
