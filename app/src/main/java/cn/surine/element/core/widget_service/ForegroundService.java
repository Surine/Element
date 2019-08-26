package cn.surine.element.core.widget_service;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import cn.surine.element.base.BaseConfig;
import cn.surine.element.base.utils.NotifyManager;
import cn.surine.element.lib_event.Action;

public class ForegroundService extends Service {
    public ForegroundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent wakeup = new Intent(Action.WAKE_UP);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, -1, wakeup, PendingIntent.FLAG_UPDATE_CURRENT);
        NotifyManager.init(this, BaseConfig.NOTIFICATION_CHANNEL,1).build(
             "Element前台服务","数据正在刷新……",pendingIntent
        ).send(true);

        return super.onStartCommand(intent, flags, startId);
    }
}
