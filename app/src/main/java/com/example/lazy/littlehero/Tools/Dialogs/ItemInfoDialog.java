package com.example.lazy.littlehero.Tools.Dialogs;

import android.app.Dialog;
import android.os.Build;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lazy.littlehero.Items.Item;
import com.example.lazy.littlehero.Tools.Constants;
import com.example.lazy.littlehero.Tools.Graphics;
import com.example.lazy.younglord.R;

public class ItemInfoDialog
{


    public static void showItemInfoDialog(Item item)
    {
        final Dialog dialog = new Dialog(Constants.CURRENT_CONTEXT);
        dialog.setContentView(R.layout.item_info_dialog_layout);
        dialog.setCancelable(true);
        TextView tvItemInfoName = dialog.findViewById(R.id.tvItemInfoName);
        TextView tvItemInfoDescription = dialog.findViewById(R.id.tvItemInfoDescription);
        TextView tvItemInfoStats = dialog.findViewById(R.id.tvItemInfoStats);
        ImageView ivItemInfo = dialog.findViewById(R.id.ivItemInfo);
        Button btnItemInfoOk = dialog.findViewById(R.id.btnItemInfo);
        Log.e("wearable", item.isWearable()+"");

        tvItemInfoName.setText(item.getItemName());
        tvItemInfoDescription.setText(item.getItemDescription());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tvItemInfoStats.setText(Html.fromHtml(item.getItemStats(),  Html.FROM_HTML_MODE_LEGACY), TextView.BufferType.SPANNABLE);
        } else {
            tvItemInfoStats.setText(Html.fromHtml(item.getItemStats()), TextView.BufferType.SPANNABLE);
        }
        ivItemInfo.setImageBitmap(Graphics.ITEMS_BITMAP[item.getItemId()]);

        btnItemInfoOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
