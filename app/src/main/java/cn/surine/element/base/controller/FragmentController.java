package cn.surine.element.base.controller;

import android.annotation.SuppressLint;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * Intro：a manager for fragment
 * @author sunliwei
 * @date 2019-06-18 16:38
 */
public class FragmentController {

    private BaseActivity activity;
    private FragmentManager fm;
    private FragmentTransaction ft;

    private FragmentController(BaseActivity activity) {
        this.activity = activity;
        fm = activity.getSupportFragmentManager();
        ft = fm.beginTransaction();
    }


    private static class Instance{
        @SuppressLint("StaticFieldLeak")
        static FragmentController INSTANCE;
        static FragmentController getInstance(BaseActivity activity){
            return (INSTANCE = new FragmentController(activity));
        }
    }

    static FragmentController getInstance(BaseActivity activity){
        return Instance.getInstance(activity);
    }

    public void open(Fragment fragment) {
        if(activity.fContainer() == activity.NULL_CONTAINER){
            throw new IllegalArgumentException("fragment container is error");
        }else{
            ft = fm.beginTransaction();
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.addToBackStack(null);
            ft.add(activity.fContainer(),fragment).commit();
        }
    }

    public void replace(Fragment fragment){
        if(activity.fContainer() == activity.NULL_CONTAINER){
            throw new IllegalArgumentException("fragment container is error");
        }else{
            ft = fm.beginTransaction();
            ft.addToBackStack(null);
            ft.replace(activity.fContainer(),fragment).commit();
        }
    }

    public void close(Fragment fragment){
        ft = fm.beginTransaction();
        ft.remove(fragment).commit();
    }


    public void close() {
        ft = fm.beginTransaction();
        if(fm.getBackStackEntryCount() > 0){
            fm.popBackStackImmediate();
        }
    }


    public int getStackFragmentCount(){
        return fm.getBackStackEntryCount();
    }

    public void hide(Fragment fragment){
        ft = fm.beginTransaction();
        ft.hide(fragment).commit();
    }

    public void show(Fragment fragment){
        ft = fm.beginTransaction();
        ft.show(fragment).commit();
    }
}
