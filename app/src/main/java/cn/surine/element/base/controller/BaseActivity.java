package cn.surine.element.base.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import cn.surine.element.lib_event.Action;

/**
 * Intro：BaseActivity for project
 * @author sunliwei
 * @date 2019-06-18 14:27
 */
public abstract class BaseActivity extends AppCompatActivity {
    public static final String PARAM = "_param";
    public final int NULL_CONTAINER = -1;
    private FragmentController fManager;

    private FragmentTransaction fm = getSupportFragmentManager().beginTransaction();

    /**
     * return the special layout
     * @return layout id
     * */
    public abstract int layoutId();


    /**
     * initialization work
     * */
    public abstract void onInit();


    /**
     * fragment container view id
     * if your layout has a  fragment container
     * you should override this method and return the view id
     * */
    public int fContainer(){
       return NULL_CONTAINER;
    }


    /**
     * set status bar color and the status bar text color
     * */
    public void setStatusBarUi(){

    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarUi();
        setContentView(layoutId());
        fManager = FragmentController.getInstance(this);
        onInit();
    }

    /**
     * start an activity with param
     * @param context from
     * @param cls to
     * @param param bundle param
     * */
    public void start(Context context, Class<? extends Activity> cls, Bundle param){
        final Intent intent = new Intent(context,cls);
        if(null != param){
            intent.putExtra(PARAM,param);
        }
        context.startActivity(intent);
    }

    /**overload*/
    public void start(Context context, Class<? extends Activity> cls){
        start(context,cls,null);
    }

    /**overload*/
    public void start(Class<? extends Activity> cls){
        start(this,cls);
    }

    @SuppressWarnings("unchecked")
    public <V extends View> V findView(int id){
        return (V)findViewById(id);
    }


    /**
     * open the fragment
     * @param fragment target fragment
     * */
    public void open(Fragment fragment){
        try{
            fManager.open(fragment);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * replace the fragment
     * @param fragment target fragment
     * */
    public void replace(Fragment fragment){
        try{
            fManager.replace(fragment);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**hide the fragment*/
    public void hide(Fragment fragment){
        fManager.hide(fragment);
    }

    /**show the fragment*/
    public void show(Fragment fragment){
        fManager.show(fragment);
    }

    /**close the fragment*/
    public void close(Fragment fragment){
        fManager.close(fragment);
    }

    /**pop*/
    public void close(){
        fManager.close();
    }


    /**get the fragment count in the stack*/
    public int getStackFragmentCount(){
       return fManager.getStackFragmentCount();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            int count = getStackFragmentCount();
            if (count > 1) {
                close();
            } else {
                finish();
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
