package cn.surine.element.base.utils;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build.VERSION_CODES;
import android.provider.Settings;

import androidx.core.app.NotificationCompat;

import cn.surine.element.R;

import static android.os.Build.VERSION.SDK_INT;

/**
 * Intro：通知管理器
 * @author sunliwei
 * @date 2019-07-25 11:31
 */
public class NotifyManager {

    private NotificationManager notificationManager;
    private String channelId;
    private Context context;
    private int id;
    private NotificationCompat.Builder builder;


    /**Android 8.0 初始化*/
    public static NotifyManager init(Context context, String channel, int id){
        NotifyManager notifyManager = new NotifyManager();
        notifyManager.notificationManager = (NotificationManager) context.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notifyManager.id = id;
        notifyManager.context = context;
        notifyManager.channelId = channel;
        return notifyManager;
    }

    /**Android 8.0 以下初始化*/
    public static NotifyManager init(Context context, int id){
        if(SDK_INT >= 26){
            throw new IllegalArgumentException("please invokeCommon the init() with channel parm");
        }else{
            return init(context,null,id);
        }
    }


    /**
     * 发送通知
     * @param content 通知内容
     * @param title 通知标题
     * @param intent Intent
     * */
    public NotifyManager build(String title, String content, PendingIntent intent){
        if(SDK_INT >= 26){
           initChannel(channelId);
           builder = new NotificationCompat.Builder(context,channelId)
                   .setContentTitle(title)
                   .setContentText(content)
                   .setAutoCancel(true)
                   .setLargeIcon(BitmapFactory.decodeResource(context.getApplicationContext().getResources(), R.mipmap.ic_launcher_round))
                   .setSmallIcon(R.mipmap.ic_launcher)
                   .setContentIntent(intent);
       }else{
            //应针对8.0 之下的通知栏进行适配
            builder = new NotificationCompat.Builder(context)
                    .setContentTitle(title)
                    .setContentText(content)
                    .setAutoCancel(true)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(intent);
       }
        return this;
    }


    /**
     * 带进度显示的通知
     * */
    public NotifyManager build(String title, String content, PendingIntent intent, int progress){
        if(null == builder){
            build(title,content,intent);
        }
        builder.setProgress(100,progress,false);
        return this;
    }



    /**
     * 更新内容
     * @param content 文本内容
     * */
    public NotifyManager setContentText(String content){
        builder.setContentText(content);
        return this;
    }



    /**
     * 发送通知
     * @param foreground 是否前台
     * */
    public void send(boolean foreground){
        if(null == builder){
            return;
        }
        if(foreground){
            sendForegroud(builder.build());
        }else {
            sendNotification(builder.build());
        }
    }


    /**
     * 渠道只会创建一次，下次系统会自动监测是否已经创建，不影响效率
     * */
    @SuppressLint("NewApi")
    public void initChannel(String c){
        createNotificationChannel();
        NotificationChannel channel = new NotificationChannel(c, "ChannelName，目前写死，可定制",
                NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription(c);
        channel.enableLights(true);
        notificationManager.createNotificationChannel(channel);
    }



    private void sendNotification(Notification notification) {
        notificationManager.notify(id,notification);
    }


    private void sendForegroud(Notification notification){
        Service s;
        //启动前台
        if(context instanceof Service){
            s = (Service) context;
            s.startForeground(id,notification);
        }
    }



    public void createNotificationChannel(){
        if (SDK_INT >= VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("_message","消息通知", NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("这是用于接受消息~");
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void openSetting(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = notificationManager.getNotificationChannel("_message");
            if(channel.getImportance() == NotificationManager.IMPORTANCE_NONE){
                Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_APP_PACKAGE,"cn.surine.asynctaskdemo");
                intent.putExtra(Settings.EXTRA_CHANNEL_ID,"_message");
                context.startActivity(intent);
            }
        }
    }
}
