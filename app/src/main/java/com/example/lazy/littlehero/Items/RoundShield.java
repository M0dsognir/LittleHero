package com.example.lazy.littlehero.Items;

public class RoundShield extends Eq implements EqInterface
{
    private int att = 0;
    private int def = 3;
    private int whereIsItPut;

    public RoundShield()
    {
        super(6);
        super.itemName = "round shield";
        super.itemDescription = "I think Romans used similar ones.";
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
}
