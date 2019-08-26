package cn.surine.element.ui.view.dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import cn.surine.element.R;
import cn.surine.element.base.controller.App;

/**
 * Intro：
 *
 * @author sunliwei
 * @date 2019-08-23 16:21
 */
public class Sialog {
    public static AlertDialog.Builder getInput(String title, final OnPositiveClickListener positiveClickListener){
        AlertDialog.Builder builder = new AlertDialog.Builder(App.getContext());
        builder.setTitle(title);
        View view = LayoutInflater.from(App.getContext()).inflate(R.layout.dialog_input,null);
        final EditText input = view.findViewById(R.id.input);
        builder.setView(view);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(input.getText().toString().isEmpty()){
                   return;
                }
                positiveClickListener.onClick(input.getText().toString());
            }
        });
        return builder;
    }
}

