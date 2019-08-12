package cn.surine.element.base.controller;

import android.app.Application;

import org.litepal.LitePal;

/**
 * Intro：
 *
 * @author sunliwei
 * @date 2019-08-12 11:38
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //初始化数据库
        LitePal.initialize(this);
        LitePal.getDatabase();
    }
}
