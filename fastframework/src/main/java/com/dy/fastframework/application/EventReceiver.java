package com.dy.fastframework.application;

import android.os.Message;

import yin.deng.normalutils.utils.EventHolder;
import yin.deng.normalutils.utils.LogUtils;

/**
 * 便于日志打印
 */
public class EventReceiver {
    private static EventReceiver eventReceiver;
    private EventReceiver(){}
    public static EventReceiver getInstance(){
        if(eventReceiver==null){
            eventReceiver=new EventReceiver();
        }
        return eventReceiver;
    }

    public void register(){
        EventHolder.getInstance().setOnEventListener("-1", new EventHolder.OnEventGetListener() {
            @Override
            public void onEventGetInBackGround(Message msg) {
                LogUtils.i("事件接收成功：msg.what="+msg.what+",msg.obj="+msg.obj);
            }
        });
    }
}
