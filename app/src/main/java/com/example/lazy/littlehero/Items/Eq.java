package com.example.lazy.littlehero.Items;

public class Eq extends Item
{
    private int eqSlotOccupied;
    public Eq(int itemId)
    {
        super(itemId);
    }

    public boolean isWearable()
    {
        return true;
    }
    public int[] getRequirements()
    {
        return ItemManager.eqRequirements[getItemId()];
    }
    public String getItemStats()
    {
        String result="";
        if (ItemManager.eqStats[getItemId()][0]!=0) result += "<font color='red'>Att: "+ItemManager.eqStats[getItemId()][0]+"<br></font>";
        if (ItemManager.eqStats[getItemId()][1]!=0) result += "<font color='blue'>Def: "+ItemManager.eqStats[getItemId()][1]+"<br></font>";
        if (ItemManager.eqStats[getItemId()][2]!=0) result += "<font color='green'>Str: "+ItemManager.eqStats[getItemId()][2]+"<br></font>";
        if (ItemManager.eqStats[getItemId()][3]!=0) result += "<font color='green'>Vit: "+ItemManager.eqStats[getItemId()][3]+"<br></font>";
        if (ItemManager.eqStats[getItemId()][4]!=0) result += "<font color='green'>Wisdom: "+ItemManager.eqStats[getItemId()][4]+"<br></font>";
        if (ItemManager.eqStats[getItemId()][5]!=0) result += "<font color='green'>Speed: "+ItemManager.eqStats[getItemId()][5]+"<br></font>";
        return result;
    }

    public void setEqSlotOccupied(int i)
    {
        eqSlotOccupied = i;
    }
    public int getEqSlotOccupied() {
        return eqSlotOccupied;
    }
}
