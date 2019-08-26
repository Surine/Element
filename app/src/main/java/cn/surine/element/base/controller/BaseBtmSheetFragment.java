package cn.surine.element.base.controller;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/**
 * Intro：bottom sheet dialog 抽象基类
 * @author sunliwei
 * @date 2019-08-22 19:41
 */
public abstract class BaseBtmSheetFragment extends BottomSheetDialogFragment {
    protected Context context;
    protected BottomSheetBehavior bottomSheetBehavior;
    protected ViewGroup rootView;
    protected Dialog dialog;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onStart() {
        super.onStart();
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((ViewGroup)rootView.getParent()).removeView(rootView);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog =  super.onCreateDialog(savedInstanceState);
        if(rootView == null){
            rootView = (ViewGroup) View.inflate(context,getLayoutId(),null);
            initView(rootView);
        }
        dialog.setContentView(rootView);
        bottomSheetBehavior = BottomSheetBehavior.from((View)rootView.getParent());
        bottomSheetBehavior.setHideable(true);
        ((View)rootView.getParent()).setBackgroundColor(Color.TRANSPARENT);
        rootView.post(new Runnable() {
            @Override
            public void run() {
                bottomSheetBehavior.setPeekHeight(rootView.getHeight());
            }
        });
        return dialog;
    }


    protected abstract void initView(View rootView);

    protected abstract int getLayoutId();


    /**
     * 关闭对话框
     * */
    public void close(){
        if(bottomSheetBehavior != null){
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        }
    }
}
