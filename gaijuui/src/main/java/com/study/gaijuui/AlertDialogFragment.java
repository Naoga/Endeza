package com.study.gaijuui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

/**
 * Created by Naoga on 2016/09/14.
 */
public class AlertDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        Dialog dialog = builder.setTitle("はたけまもるより")
                        .setMessage("害獣が現れています!!")
                        .setIcon(R.drawable.ic_hatake_web)
                        .setPositiveButton("アプリを起動する", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //アプリ起動手続き
                                Intent intent = new Intent();
                                intent.setAction(Intent.ACTION_VIEW);
                                intent.setClassName("com.study.gaijuui","com.study.gaijuui.GaijuActivity");
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("また後で", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create();
        dialog.setCanceledOnTouchOutside(true);
        /*dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));*/
        return dialog;
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().finish();
    }

}
