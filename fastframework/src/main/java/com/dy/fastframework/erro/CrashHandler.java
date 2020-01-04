package com.dy.fastframework.erro;

import android.content.Context;
import android.util.Log;

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "DYLOG-ERRO(erro)-";
    private static CrashHandler sCrashHandler;
    private static Context sContext;

    public static CrashHandler getInstance() {
        if (sCrashHandler == null) {
            synchronized (CrashHandler.class) {
                if (sCrashHandler == null) {
                    sCrashHandler = new CrashHandler();
                }
            }
        }
        return sCrashHandler;
    }


    public void init(Context context) {  //初始化，把当前对象设置成UncaughtExceptionHandler处理器
        sContext = context;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 有未处理的异常时
     *
     * @param thread
     * @param e
     */
    @Override
    public void uncaughtException(Thread thread, Throwable e) {
        Log.e(TAG, "uncaughtException, " + " 报错线程: " + thread.getName() +
                " 线程id: " + thread.getId() + ",exception信息: "
                + e);
        String threadName = thread.getName();
        if ("UIThread".equals(threadName)) {
            Log.e(TAG, "根据Thread，可以保存异常信息");
        }
    }
}
