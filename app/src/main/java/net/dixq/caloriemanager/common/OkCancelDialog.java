package net.dixq.caloriemanager.common;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;

/**
 * Created by dixq on 2018/03/25.
 */

public class OkCancelDialog {
    public AlertDialog _dialog;
    public OkCancelDialog(Context context, String msg, Dialog.OnClickListener implOK, Dialog.OnClickListener implNG){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setMessage(msg);
        alertDialogBuilder.setPositiveButton("OK", implOK);
        alertDialogBuilder.setNegativeButton("キャンセル", implNG);
        alertDialogBuilder.setCancelable(true);
        _dialog = alertDialogBuilder.create();
        _dialog.show();
    }
    public boolean isShowing(){
        return _dialog.isShowing();
    }
}
