package com.example.lazy.littlehero.Items;

public class Item
{
    protected String itemName, itemDescription;
    private int itemId;
    private boolean isSelected = false;
    private int inventorySlot;

    public Item(int itemId)
    {
        this.itemId = itemId;
    }

    public void select()
    {
        if (isSelected) isSelected = false;
        else isSelected = true;
    }

    public boolean isSelected()
    {
        return isSelected;
    }
    public String getItemName()
    {
        return itemName;
    }

    public int getItemId() {
        return itemId;
    }
    public int getAmount()
    {
        return 0;
    }
    public void setInventorySlot(int i)
    {
        inventorySlot = i;
    }
    public int getInventorySlot()
    {
        return inventorySlot;
    }
    public String getItemDescription()
    {
        return itemDescription;
    }
    public String getItemStats()
    {
        return "error";
    }
    public boolean isWearable()
    {
        return false;
    }
    public int getEqSlotOccupied()
    {
        return 99;
    }
}
