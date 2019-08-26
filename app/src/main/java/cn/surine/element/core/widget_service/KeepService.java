package cn.surine.element.core.widget_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import cn.surine.element.base.utils.Logs;
import cn.surine.element.lib_event.Action;

import static cn.surine.element.base.BaseConfig.ACTION_EVENT_DATA;
import static cn.surine.element.base.BaseConfig.ACTION_NAME;
import static cn.surine.element.base.BaseConfig.ACTION_SUB_EVENT;
import static cn.surine.element.base.BaseConfig.APP_WIDGET_ID;

public class KeepService extends Service {
    public KeepService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        Logs.d("service create");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logs.d("service startcommand");

        //转发action消息
        Intent myIntent = new Intent(intent.getStringExtra(ACTION_NAME));
        myIntent.putExtra(APP_WIDGET_ID,intent.getIntExtra(APP_WIDGET_ID,-1));
        myIntent.putExtra(ACTION_SUB_EVENT,intent.getStringExtra(ACTION_SUB_EVENT));
        myIntent.putExtra(ACTION_EVENT_DATA,intent.getStringExtra(ACTION_EVENT_DATA));

        Action.send(this,myIntent);


        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Logs.d("service be destroyed");
        super.onDestroy();
    }
}
