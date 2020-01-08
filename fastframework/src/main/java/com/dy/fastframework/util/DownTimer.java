package com.dy.fastframework.util;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

import yin.deng.superbase.activity.LogUtils;


/**
 * 倒计时专用
 */
public class DownTimer {
    private long totalTime = -1;
    private long intervalTime = 0;
    private long remainTime;
    private long systemAddTotalTime;
    private static final int TIME = 1;
    private TimeListener listener;
    private long curReminTime;
    private boolean isPause = false;
    private static DownTimer timer;

    /**
     * 倒计时开始走
     * 传入总时长
     */
    public static void startTimer(int timeInSeconds,DownTimer.TimeListener listener) {
        timer = new DownTimer();//实例化
        timer.setTotalTime(timeInSeconds*1000);//设置毫秒数
        timer.setIntervalTime(1000);//设置间隔数
        timer.setTimerLiener(listener);
        timer.start();
    }

    /**
     * 结束倒计时
     */
    public static void stopTimer() {
        if(timer!=null){
            timer.cancel();
            timer=null;
        }
//        timer.resume();
//        timer.cancel();
//        timer.pause();
    }

    public DownTimer() {
    }

    public void setIntervalTime(long intervalTime) {
        this.intervalTime = intervalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    public long getIntervalTime() {
        return intervalTime;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void start() {
        if (totalTime <= 0 && intervalTime <= 0) {
            throw new RuntimeException("you must set the totalTime > 0 or intervalTime >0");
        }

        systemAddTotalTime = SystemClock.elapsedRealtime() + totalTime;

        if (null != mHandler)
            mHandler.sendEmptyMessage(TIME);
    }

    public void cancel() {
        if(mHandler !=null)
        {
            mHandler.removeMessages(TIME);
            mHandler = null;
        }

    }

    public void pause() {
        if(mHandler !=null)
        {
            mHandler.removeMessages(TIME);
            isPause = true;
            curReminTime = remainTime;
        }

    }

    public void resume() {
        if (isPause == true) {
            isPause = false;
            totalTime = curReminTime;
            start();
        }

    }

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case TIME:
                    if (!isPause)
                        soloveTime();
                    break;
                case 2:
                    isPause = true;
                    break;
                default:
                    break;
            }
        }
    };


    private void soloveTime() {
        remainTime = systemAddTotalTime - SystemClock.elapsedRealtime();
        LogUtils.e("remainTime:"+remainTime+"\nintervalTime:"+intervalTime);
        if (remainTime/1000 <= 0) {
            if (listener != null) {
                listener.onFinish();
                cancel();
            }
        } else {
            long curSystemTime = SystemClock.elapsedRealtime();
            if (listener != null) {
                listener.onInterval(remainTime);
            }

            long delay = curSystemTime + intervalTime - SystemClock.elapsedRealtime();

            while (delay < 0) delay += intervalTime;

            if (null != mHandler) {
                mHandler.sendEmptyMessageDelayed(TIME, delay);
            }
        }
    }

    public interface TimeListener {
        public void onFinish();
        public void onInterval(long remainTime);
    }

    public void setTimerLiener(TimeListener listener) {
        this.listener = listener;
    }

}
