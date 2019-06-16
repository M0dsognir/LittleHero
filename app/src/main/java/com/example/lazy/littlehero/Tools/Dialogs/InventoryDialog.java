package com.example.lazy.littlehero.Tools.Dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lazy.littlehero.Characters.Player;
import com.example.lazy.littlehero.Duel;
import com.example.lazy.littlehero.Items.Eq;
import com.example.lazy.littlehero.Items.Item;
import com.example.lazy.littlehero.Items.ItemManager;
import com.example.lazy.littlehero.Tools.Constants;
import com.example.lazy.littlehero.Tools.Graphics;
import com.example.lazy.littlehero.Tools.Timer;
import com.example.lazy.younglord.R;

import java.util.ArrayList;

public class InventoryDialog
{
    private static boolean someInvIsSelectedAlready = false, someEqIsSelectedAlready = false;
    private static Item itemThatWasSelected;

    public static void showInventoryDialog(final Player player, final boolean backArrowVisibility)
    {
        Timer.SHOULD_I_RESUME_GAME = false;

        final Dialog dialog = new Dialog(Constants.CURRENT_CONTEXT);
        dialog.setContentView(R.layout.inventory_layout);
        dialog.getWindow().setLayout(1090,995);
        dialog.setOnCancelListener(
                new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog)
                    {
                        someInvIsSelectedAlready = false;
                        Timer.SHOULD_I_RESUME_GAME = true;
                        if(!backArrowVisibility)
                        Timer.START_GAME();
                    }
                }
        );

        TextView tvInvLvl = dialog.findViewById(R.id.tvInvLvl);
        tvInvLvl.setTextSize(10);
        tvInvLvl.setText("Lvl. "+player.getLvl()+" ("+player.getExp()+"/"+player.EXP_TABLE[player.getLvl()-1]+")");
        TextView tvInvHp = dialog.findViewById(R.id.tvInvHp);
        tvInvHp.setTextSize(10);
        tvInvHp.setText(player.getHp()+"/"+player.getMaxHp());

        final ArrayList <ImageButton> ibs = new ArrayList<>();
        final ImageButton ib0 = dialog.findViewById(R.id.ivInv0);ImageButton ib1 = dialog.findViewById(R.id.ivInv1);ImageButton ib2 = dialog.findViewById(R.id.ivInv2);ImageButton ib3 = dialog.findViewById(R.id.ivInv3);ImageButton ib4 = dialog.findViewById(R.id.ivInv4);
        ImageButton ib5 = dialog.findViewById(R.id.ivInv5);ImageButton ib6 = dialog.findViewById(R.id.ivInv6);ImageButton ib7 = dialog.findViewById(R.id.ivInv7);ImageButton ib8 = dialog.findViewById(R.id.ivInv8); ImageButton ib9 = dialog.findViewById(R.id.ivInv9);
        ImageButton ib10 = dialog.findViewById(R.id.ivInv10);ImageButton ib11 = dialog.findViewById(R.id.ivInv11);ImageButton ib12 = dialog.findViewById(R.id.ivInv12);ImageButton ib13 = dialog.findViewById(R.id.ivInv13);ImageButton ib14 = dialog.findViewById(R.id.ivInv14);
        ImageButton ib15 = dialog.findViewById(R.id.ivInv15);ImageButton ib16 = dialog.findViewById(R.id.ivInv16);ImageButton ib17 = dialog.findViewById(R.id.ivInv17);ImageButton ib18 = dialog.findViewById(R.id.ivInv18);ImageButton ib19 = dialog.findViewById(R.id.ivInv19);
        ImageButton ib20 = dialog.findViewById(R.id.ivInv20);ImageButton ib21 = dialog.findViewById(R.id.ivInv21);ImageButton ib22 = dialog.findViewById(R.id.ivInv22);ImageButton ib23 = dialog.findViewById(R.id.ivInv23);ImageButton ib24 = dialog.findViewById(R.id.ivInv24);
        ImageButton ib25 = dialog.findViewById(R.id.ivInv25);ImageButton ib26 = dialog.findViewById(R.id.ivInv26);ImageButton ib27 = dialog.findViewById(R.id.ivInv27);ImageButton ib28 = dialog.findViewById(R.id.ivInv28);ImageButton ib29 = dialog.findViewById(R.id.ivInv29);
        ImageButton ib30 = dialog.findViewById(R.id.ivInv30);ImageButton ib31 = dialog.findViewById(R.id.ivInv31);ImageButton ib32 = dialog.findViewById(R.id.ivInv32);ImageButton ib33 = dialog.findViewById(R.id.ivInv33);ImageButton ib34 = dialog.findViewById(R.id.ivInv34);
        ibs.add(ib0);ibs.add(ib1);ibs.add(ib2);ibs.add(ib3);ibs.add(ib4);
        ibs.add(ib5);ibs.add(ib6);ibs.add(ib7);ibs.add(ib8);ibs.add(ib9);
        ibs.add(ib10);ibs.add(ib11);ibs.add(ib12);ibs.add(ib13);ibs.add(ib14);
        ibs.add(ib15);ibs.add(ib16);ibs.add(ib17);ibs.add(ib18);ibs.add(ib19);
        ibs.add(ib20);ibs.add(ib21);ibs.add(ib22);ibs.add(ib23);ibs.add(ib24);
        ibs.add(ib25);ibs.add(ib26);ibs.add(ib27);ibs.add(ib28);ibs.add(ib29);
        ibs.add(ib30);ibs.add(ib31);ibs.add(ib32);ibs.add(ib33);ibs.add(ib34);

        final ArrayList<ImageButton> ibsEq = new ArrayList<>();
        ImageButton ibEq0 = dialog.findViewById(R.id.ivInvHead);        ibsEq.add(ibEq0);
        ImageButton ibEq1 = dialog.findViewById(R.id.ivInvTop);         ibsEq.add(ibEq1);
        ImageButton ibEq2 = dialog.findViewById(R.id.ivInvBottom);      ibsEq.add(ibEq2);
        ImageButton ibEq3 = dialog.findViewById(R.id.ivInvShoes);       ibsEq.add(ibEq3);
        ImageButton ibEq4 = dialog.findViewById(R.id.ivInvRightHand);   ibsEq.add(ibEq4);
        ImageButton ibEq5 = dialog.findViewById(R.id.ivInvLeftHand);    ibsEq.add(ibEq5);
        ImageButton ibEq6 = dialog.findViewById(R.id.ivInvNecklace);    ibsEq.add(ibEq6);
        ImageButton ibEq7 = dialog.findViewById(R.id.ivInvRing);        ibsEq.add(ibEq7);
        ImageButton ibEq8 = dialog.findViewById(R.id.ivInvRing2);       ibsEq.add(ibEq8);

        TextView tvCoins = dialog.findViewById(R.id.tvCoins);
        tvCoins.setText(player.getCoins()+"");

        final ImageButton btnUseIt = dialog.findViewById(R.id.btnUseIt);
        final ImageButton btnDropIt = dialog.findViewById(R.id.btnDropIt);
        
        for(int x=0; x<ibsEq.size(); x++)
        {
            final int finalX = x;
            ibsEq.get(x).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (someInvIsSelectedAlready) //inv było zaznaczone
                    {
                        //odznaczamy inv, zaznaczamy eq
                        ibs.get(itemThatWasSelected.getInventorySlot()).setBackground(Constants.CURRENT_CONTEXT.getDrawable(R.drawable.box_200));
                        ibsEq.get(finalX).setBackground(Constants.CURRENT_CONTEXT.getDrawable(R.drawable.box_200_selected));
                        itemThatWasSelected.select();
                        someInvIsSelectedAlready = false;
                        for (Eq e: player.getEq())
                        {
                            if (e.getEqSlotOccupied()==finalX)
                            {
                                e.select();
                                itemThatWasSelected = e;
                                someEqIsSelectedAlready = true;
                                break;
                            }
                        }
                    }
                    else //inv nie było zaznaczone
                    {
                        if (player.getEqSlotOccupied()[finalX]) //cuś tu jest
                        {
                            if (someEqIsSelectedAlready)
                            {
                                if (finalX == itemThatWasSelected.getEqSlotOccupied())
                                {
                                    //odznaczamy
                                    ibsEq.get(finalX).setBackground(Constants.CURRENT_CONTEXT.getDrawable(R.drawable.box_200));
                                    itemThatWasSelected.select();
                                    itemThatWasSelected = null;
                                    someEqIsSelectedAlready = false;
                                    player.checkEqSlotOccupied();
                                }
                                else
                                {
                                    //zmiana zaznaczenia w obrębie EQ
                                    ibsEq.get(itemThatWasSelected.getEqSlotOccupied()).setBackground(Constants.CURRENT_CONTEXT.getDrawable(R.drawable.box_200));
                                    ibsEq.get(finalX).setBackground(Constants.CURRENT_CONTEXT.getDrawable(R.drawable.box_200_selected));
                                    for (Eq e : player.getEq()) {
                                        if (e.getEqSlotOccupied() == finalX) {
                                            itemThatWasSelected = e;
                                            break;
                                        }
                                    }
                                }
                            }
                            else
                            {
                                ibsEq.get(finalX).setBackground(Constants.CURRENT_CONTEXT.getDrawable(R.drawable.box_200_selected));
                                for (Eq e: player.getEq())
                                {
                                    if (e.getEqSlotOccupied()==finalX)
                                    {
                                        itemThatWasSelected = e;
                                        someEqIsSelectedAlready = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            });
            ibsEq.get(x).setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    for (Eq e: player.getEq())
                    {
                        if (e.getEqSlotOccupied()==finalX)
                        {
                            ItemInfoDialog.showItemInfoDialog(e);
                            break;
                        }
                    }
                    return true;
                }
            });
        }

        btnDropIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (someInvIsSelectedAlready)
                {
                    player.getInventory().remove(itemThatWasSelected);
                    player.checkInventorySlotOccupied();
                    showInventory(player, ibs, ibsEq);
                    btnDropIt.setBackground(Constants.CURRENT_CONTEXT.getDrawable(R.drawable.btn_drop_it_grey));
                    btnUseIt.setBackground(Constants.CURRENT_CONTEXT.getDrawable(R.drawable.btn_use_it_grey));
                    someInvIsSelectedAlready = false;
                    if (backArrowVisibility)
                    {
                        Duel.shouldICheckInvCap = true;
                    }
                }
                else
                {
                    if(someEqIsSelectedAlready)
                    {
                        player.getEq().remove(itemThatWasSelected);
                        player.checkEqSlotOccupied();
                        showInventory(player, ibs, ibsEq);
                        btnDropIt.setBackground(Constants.CURRENT_CONTEXT.getDrawable(R.drawable.btn_drop_it_grey));
                        btnUseIt.setBackground(Constants.CURRENT_CONTEXT.getDrawable(R.drawable.btn_use_it_grey));
                        someEqIsSelectedAlready = true;
                    }
                    else
                    {
                        btnDropIt.setBackground(Constants.CURRENT_CONTEXT.getDrawable(R.drawable.btn_drop_it_grey));
                        btnUseIt.setBackground(Constants.CURRENT_CONTEXT.getDrawable(R.drawable.btn_use_it_grey));
                    }
                }
            }
        });
        btnUseIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.checkEqSlotOccupied();
                player.checkInventorySlotOccupied();
                if (someInvIsSelectedAlready)
                {
                    if(itemThatWasSelected.isWearable()) //itemek jest EQ
                    {
                        if (player.meetRequirements((Eq)itemThatWasSelected))
                        {
                            player.checkEqSlotOccupied();
                            if (!player.getEqSlotOccupied()[ItemManager.whereShouldItBePut[itemThatWasSelected.getItemId()]])
                            {
                                //zakładamy eq
                                for (Item i: player.getInventory())
                                {
                                    if(i.isSelected())
                                    {
                                        ibs.get(i.getInventorySlot()).setBackground(Constants.CURRENT_CONTEXT.getDrawable(R.drawable.box_200));
                                        break;
                                    }
                                }
                                player.getInventory().remove(itemThatWasSelected);
                                player.getEq().add((Eq)itemThatWasSelected);
                                ((Eq) itemThatWasSelected).setEqSlotOccupied(ItemManager.whereShouldItBePut[itemThatWasSelected.getItemId()]);

                                showInventory(player, ibs, ibsEq);

                                someInvIsSelectedAlready = false;
                                itemThatWasSelected = null;
                                Log.e("eq", player.getEq().size()+"");
                            }
                            else
                            {
                                //podmieniamy eq
                                Item temp;
                                for (Item i: player.getInventory())
                                {
                                    if(i.isSelected())
                                    {
                                        ibs.get(i.getInventorySlot()).setBackground(Constants.CURRENT_CONTEXT.getDrawable(R.drawable.box_200));
                                        break;
                                    }
                                }
                                for (Eq eq: player.getEq())
                                {
                                    if (eq.getEqSlotOccupied()==ItemManager.whereShouldItBePut[itemThatWasSelected.getItemId()])
                                    {
                                        temp = eq;
                                        temp.setInventorySlot(itemThatWasSelected.getInventorySlot());
                                        player.checkInventorySlotOccupied();
                                        player.getEq().remove(temp);
                                        player.getEq().add((Eq)itemThatWasSelected);
                                        ((Eq) itemThatWasSelected).setEqSlotOccupied(ItemManager.whereShouldItBePut[itemThatWasSelected.getItemId()]);
                                        player.getInventory().remove(itemThatWasSelected);
                                        player.getInventory().add((Item)temp);
                                        break;
                                    }
                                }

                                showInventory(player, ibs, ibsEq);
                                someInvIsSelectedAlready = false;
                                itemThatWasSelected = null;
                                player.checkInventorySlotOccupied();
                                player.checkEqSlotOccupied();
                            }
                        }
                        else Toast.makeText(Constants.CURRENT_CONTEXT, "You do not meet requirements to wear it", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        //Itemek nie jest EQ
                    }
                }
                else
                {
                    if (someEqIsSelectedAlready)
                    {
                        if (player.checkIfPlayerCanTakeThatManyItems(1))
                        {
                            player.addLootToPlayersInventory(itemThatWasSelected);
                            player.getEq().remove(itemThatWasSelected);
                            itemThatWasSelected = null;
                            someEqIsSelectedAlready = false;
                            player.checkInventorySlotOccupied();
                            showInventory(player, ibs, ibsEq);
                        }
                    }
                }
            }
        });

        ImageButton ibBackArrow = dialog.findViewById(R.id.ibArrowBack);
        if (backArrowVisibility) ibBackArrow.setVisibility(View.VISIBLE);
        else ibBackArrow.setVisibility(View.INVISIBLE);
        ibBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                someInvIsSelectedAlready = false;
                dialog.dismiss();
            }
        });

        checkSlotsAvailability(player);
        showInventory(player, ibs, ibsEq);

        for (int x=0; x<ibs.size(); x++)
        {
            final int finalX = x;
            ibs.get(x).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (player.getInventorySlotOccupied()[finalX])
                    {
                        if (someInvIsSelectedAlready)
                        {
                            for (int y=0; y<player.getInventory().size(); y++)
                            {
                                if (player.getInventory().get(y).getInventorySlot()==finalX)
                                {

                                    if (itemThatWasSelected.getInventorySlot()==finalX)
                                    {
                                        //odznacz
                                        itemThatWasSelected.select();
                                        itemThatWasSelected = null;
                                        someInvIsSelectedAlready = false;
                                        btnDropIt.setBackground(Constants.CURRENT_CONTEXT.getDrawable(R.drawable.btn_drop_it_grey));
                                        btnUseIt.setBackground(Constants.CURRENT_CONTEXT.getDrawable(R.drawable.btn_use_it_grey));
                                        ibs.get(finalX).setBackground(Constants.CURRENT_CONTEXT.getDrawable(R.drawable.box_200));
                                        break;
                                    }
                                    else
                                    {
                                        //zamieniamy miejscami
                                        for (Item i: player.getInventory())
                                        {
                                            if (i.getInventorySlot()==finalX)
                                            {
                                                int temp = itemThatWasSelected.getInventorySlot();
                                                itemThatWasSelected.setInventorySlot(i.getInventorySlot());
                                                i.setInventorySlot(temp);
                                                break;
                                            }
                                        }
                                        showInventory(player, ibs, ibsEq);
                                        player.checkInventorySlotOccupied();
                                        someInvIsSelectedAlready = false;
                                        btnDropIt.setBackground(Constants.CURRENT_CONTEXT.getDrawable(R.drawable.btn_drop_it_grey));
                                        btnUseIt.setBackground(Constants.CURRENT_CONTEXT.getDrawable(R.drawable.btn_use_it_grey));
                                        break;
                                    }
                                }
                            }
                        }
                        else
                        {
                            if (someEqIsSelectedAlready)
                            {
                                //odznaczanie eq
                                someEqIsSelectedAlready = false;
                                for (Eq e: player.getEq())
                                {
                                    if (e.getEqSlotOccupied()==itemThatWasSelected.getEqSlotOccupied())
                                    {
                                        player.checkEqSlotOccupied();
                                        ibsEq.get(itemThatWasSelected.getEqSlotOccupied()).setBackground(Constants.CURRENT_CONTEXT.getDrawable(R.drawable.box_200));
                                        break;
                                    }
                                }
                            }
                            //zaznaczam inv
                            for (int y=0; y<player.getInventory().size(); y++)
                            {
                                if (player.getInventory().get(y).getInventorySlot()==finalX)
                                {
                                    player.getInventory().get(y).select();
                                    someInvIsSelectedAlready = true;
                                    btnDropIt.setBackground(Constants.CURRENT_CONTEXT.getDrawable(R.drawable.btn_drop_it));
                                    btnUseIt.setBackground(Constants.CURRENT_CONTEXT.getDrawable(R.drawable.btn_use_it));
                                    itemThatWasSelected = player.getInventory().get(y);
                                    ibs.get(finalX).setBackground(Constants.CURRENT_CONTEXT.getDrawable(R.drawable.box_200_selected));
                                    break;
                                }
                            }
                        }
                    }
                    else
                    {
                        if (someInvIsSelectedAlready)
                        {
                            //nowe miejsce przedmiotu
                            ibs.get(itemThatWasSelected.getInventorySlot()).setBackground(Constants.CURRENT_CONTEXT.getDrawable(R.drawable.box_200));
                            itemThatWasSelected.setInventorySlot(finalX);
                            showInventory(player, ibs, ibsEq);
                            someInvIsSelectedAlready = false;
                            btnDropIt.setBackground(Constants.CURRENT_CONTEXT.getDrawable(R.drawable.btn_drop_it_grey));
                            btnUseIt.setBackground(Constants.CURRENT_CONTEXT.getDrawable(R.drawable.btn_use_it_grey));
                            player.checkInventorySlotOccupied();
                        }
                    }
                }
            });
            ibs.get(x).setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (player.getInventorySlotOccupied()[finalX])
                    {
                        for (int y=0; y<player.getInventory().size(); y++) {
                            if (player.getInventory().get(y).getInventorySlot() == finalX)
                            {
                                ItemInfoDialog.showItemInfoDialog(player.getInventory().get(y));
                            }
                        }
                    }
                    return true;
                }
            });
        }
        dialog.show();
    }
    private static void showInventory(Player player, ArrayList<ImageButton> ibs, ArrayList<ImageButton> ibsEq)
    {
        for (ImageButton ib: ibs)
        {
            ib.setBackground(Constants.CURRENT_CONTEXT.getDrawable(R.drawable.box_200));
            ib.setImageBitmap(null);
        }
        if (!player.getInventory().isEmpty())
        {
            for (Item i: player.getInventory())
            {
                ibs.get(i.getInventorySlot()).setImageBitmap(Graphics.ITEMS_BITMAP[i.getItemId()]);
                ibs.get(i.getInventorySlot()).setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        }
        for (ImageButton ib: ibsEq)
        {
            ib.setBackground(Constants.CURRENT_CONTEXT.getDrawable(R.drawable.box_200));
            ib.setImageBitmap(null);
        }
        if (!player.getEq().isEmpty())
        {
            for (Eq i: player.getEq())
            {
                ibsEq.get(i.getEqSlotOccupied()).setImageBitmap(Graphics.ITEMS_BITMAP[i.getItemId()]);
                ibsEq.get(i.getEqSlotOccupied()).setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        }
    }

    private static void checkSlotsAvailability(Player player)
    {
        if (!player.getInventory().isEmpty())
        {
            for (boolean b: player.getInventorySlotOccupied())
            {
                b = false;
            }
        }
        for (Item i: player.getInventory())
        {
            player.getInventorySlotOccupied()[i.getInventorySlot()] = true;
        }
    }
}
