package com.dy.fastframework.notification;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.dy.fastframework.application.SuperBaseApp;

import static android.content.Context.NOTIFICATION_SERVICE;

public class NotificationUtils {


    /**
     * 创建/更新 一条普通的消息通知（创建或更新取决于notifyId）
     * @param context
     * @param notifyId
     * @param content
     * @param clickOpenClass
     * @param icon
     * @param title
     */
    public static void notifyNormalMsg(Context context,int notifyId,String content,Class clickOpenClass,int icon, String title){
        getNotificationManager(context).notify(notifyId, getNormalNotification(context,content,clickOpenClass,icon,title));
    }


    /**
     * 取消通知
     * @param context
     * @param notifyId
     */
    public static void cancleNotification(Context context,int notifyId){
        getNotificationManager(context).cancel(notifyId);
    }


    /**
     * 关于相应的Notification进行提示通知
     *
     * 1.使用通知的时候，创建NotificationManager  使用 getSystemService
     *
     * 2.设置Notification
     *
     * 3.最后使用NotificationManager进行Notification的调用
     */
    public static NotificationManager getNotificationManager(Context context){
        return ((NotificationManager)context.getSystemService(NOTIFICATION_SERVICE));
    }


    /**
     * 获取带进度条的通知
     * @param title
     * @param progress
     * @return
     */
    public static Notification getProgressNotification(Context context,Class clickOpenClass,int icon, String title, int progress){
        //点击通知的操作
        Intent intent = new Intent(context, clickOpenClass);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);
        //为了保证所有的通知一致，使用V7包的NotificationCompat.Builder()
        String CHANNEL_ID = SuperBaseApp.app.getPackageName();
        String CHANNEL_NAME = "NormalMsg";
        NotificationChannel notificationChannel = null;
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            NotificationManager notificationManager =getNotificationManager(context);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,CHANNEL_ID);
        builder.setSmallIcon(android.R.drawable.ic_input_add);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), icon));
        builder.setContentTitle(title);
        builder.setContentIntent(pendingIntent);
        if(progress>0){
            //当progress大于0
            builder.setContentText(progress+"%");
            builder.setProgress(100,progress,false);
        }
        return builder.build();
    }


    /**
     * 获取普通的消息通知
     * @param context
     * @param content
     * @param clickOpenClass
     * @param icon
     * @param title
     * @return
     */
    public static Notification getNormalNotification(Context context,String content,Class clickOpenClass,int icon, String title){
        //点击通知的操作
        Intent intent = new Intent(context, clickOpenClass);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);
        //为了保证所有的通知一致，使用V7包的NotificationCompat.Builder()
        String CHANNEL_ID = SuperBaseApp.app.getPackageName();
        String CHANNEL_NAME = "NormalMsg";
        NotificationChannel notificationChannel = null;
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            NotificationManager notificationManager =getNotificationManager(context);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,CHANNEL_ID);
        builder.setSmallIcon(android.R.drawable.ic_input_add);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), icon));
        builder.setContentTitle(title);
        builder.setContentIntent(pendingIntent);
        builder.setContentText(content);
        return builder.build();
    }
}
