package cn.surine.element.base.controller;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePal;

import cn.surine.element.base.utils.Toasts;
import cn.surine.element.lib_event.Action;

/**
 * Intro：
 * @author sunliwei
 * @date 2019-08-12 11:38
 */
public class App extends Application {

    private static Context context;


    public static Context getContext(){
        return context;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        //初始化数据库
        LitePal.initialize(this);
        LitePal.getDatabase();

        //toast组件初始化
        Toasts.init(this);
    }

}
