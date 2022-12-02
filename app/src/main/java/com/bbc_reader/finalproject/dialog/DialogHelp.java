package com.bbc_reader.finalproject.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.ImageView;

import com.bbc_reader.finalproject.R;

@SuppressLint("NewApi")
public class DialogHelp {

    private Dialog dialog;
    private ImageView ivHelp;

    public DialogHelp(Context context, Integer res){
        this.dialog = new Dialog(context);
        this.dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.dialog.setContentView(R.layout.dialog_help);
        this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        connectView();
        ivHelp.setImageResource(res);
    }

    private void connectView(){
        this.ivHelp = this.dialog.findViewById(R.id.iv_help);
    }

    public void show(){
        this.dialog.show();
    }

    public void hide(){
        this.dialog.dismiss();
    }

}
