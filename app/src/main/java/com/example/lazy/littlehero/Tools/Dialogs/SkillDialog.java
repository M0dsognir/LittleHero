package com.example.lazy.littlehero.Tools.Dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lazy.littlehero.Characters.Player;
import com.example.lazy.littlehero.Tools.Constants;
import com.example.lazy.littlehero.Tools.Timer;
import com.example.lazy.younglord.R;

public class SkillDialog
{
    private static int tempStr = 0, tempVit = 0, tempWis = 0, tempUn = 0;

    public static void showSkillsDialog(final Player player)
    {
        tempStr = 0; tempVit = 0; tempWis = 0; tempUn = 0;
        final Dialog dialog = new Dialog(Constants.CURRENT_CONTEXT);
        dialog.setContentView(R.layout.skills_layout);
        dialog.setCancelable(true);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Timer.SHOULD_I_RESUME_GAME = true;
                Timer.START_GAME();
            }
        });
        TextView tvLvl = dialog.findViewById(R.id.tvLvl);
        TextView tvStr = dialog.findViewById(R.id.tvStr);
        TextView tvVit = dialog.findViewById(R.id.tvVit);
        TextView tvWis = dialog.findViewById(R.id.tvWis);
        TextView tvUnassigned = dialog.findViewById(R.id.tvUnassigned);
        TextView tvStrVal = dialog.findViewById(R.id.tvStrVal);
        TextView tvVitVal = dialog.findViewById(R.id.tvVitVal);
        TextView tvWisVal = dialog.findViewById(R.id.tvWisVal);
        TextView tvUnassignedVal = dialog.findViewById(R.id.tvUnassignedVal);
        final TextView tvStrTemp = dialog.findViewById(R.id.tvStrTemp);
        final TextView tvVitTemp = dialog.findViewById(R.id.tvVitTemp);
        final TextView tvWisTemp = dialog.findViewById(R.id.tvWisTemp);
        final TextView tvUnTemp = dialog.findViewById(R.id.tvUnTemp);

        ImageButton ibStrAdd = dialog.findViewById(R.id.ibStrAdd);
        ImageButton ibStrSub = dialog.findViewById(R.id.ibStrSub);
        ImageButton ibVitAdd = dialog.findViewById(R.id.ibVitAdd);
        ImageButton ibVitSub = dialog.findViewById(R.id.ibVitSub);
        ImageButton ibWisAdd = dialog.findViewById(R.id.ibWisAdd);
        ImageButton ibWisSub = dialog.findViewById(R.id.ibWisSub);
        ImageButton ibSkillsOk = dialog.findViewById(R.id.ibSkillsOk);
        ImageButton ibSkillsReset = dialog.findViewById(R.id.ibSkillsReset);

        tvLvl.setText("Lvl. "+player.getLvl()+" ("+player.getExp()+"/"+player.EXP_TABLE[player.getLvl()-1]+")");
        tvLvl.setTextSize(10);
        tvStrVal.setText(player.getStr()+"");
        tvVitVal.setText(player.getVit()+"");
        tvWisVal.setText(player.getWisdom()+"");
        tvUnassignedVal.setText(player.getUnassigned()+"");

        tvStrTemp.setText("("+tempStr+")");
        tvVitTemp.setText("("+tempVit+")");
        tvWisTemp.setText("("+tempWis+")");
        tvUnTemp.setText("("+tempUn+")");

        ibStrAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((player.getUnassigned()-(tempStr+tempVit+tempWis))>0)
                {
                    tempStr++;
                    tempUn--;
                    tvStrTemp.setText("("+tempStr+")");
                    tvUnTemp.setText("("+tempUn+")");
                }
            }
        });
        ibVitAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((player.getUnassigned()-(tempStr+tempVit+tempWis))>0)
                {
                    tempVit++;
                    tempUn--;
                    tvVitTemp.setText("("+tempVit+")");
                    tvUnTemp.setText("("+tempUn+")");
                }
            }
        });
        ibWisAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((player.getUnassigned()-(tempStr+tempVit+tempWis))>0)
                {
                    tempWis++;
                    tempUn--;
                    tvWisTemp.setText("("+tempWis+")");
                    tvUnTemp.setText("("+tempUn+")");
                }
            }
        });

        ibSkillsOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.addStr(tempStr);
                player.addVit(tempVit);
                player.addWis(tempWis);
                player.addUnassigned(tempUn);
                dialog.cancel();
            }
        });

        ibSkillsReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempStr = 0;
                tempVit = 0;
                tempWis = 0;
                tempUn = 0;
                tvStrTemp.setText("("+tempStr+")");
                tvVitTemp.setText("("+tempVit+")");
                tvWisTemp.setText("("+tempWis+")");
                tvUnTemp.setText("("+tempUn+")");
                //dialog.cancel();
            }
        });

        dialog.show();
    }
}
