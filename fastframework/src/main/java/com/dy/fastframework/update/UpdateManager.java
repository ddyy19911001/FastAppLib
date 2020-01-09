package com.dy.fastframework.update;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.View;

import androidx.core.content.FileProvider;

import com.dy.fastframework.R;
import com.dy.fastframework.downloader.Error;
import com.dy.fastframework.downloader.OnDownloadListener;
import com.dy.fastframework.downloader.OnProgressListener;
import com.dy.fastframework.downloader.OnStartOrResumeListener;
import com.dy.fastframework.downloader.PRDownloader;
import com.dy.fastframework.downloader.Progress;
import com.dy.fastframework.downloader.utils.Utils;
import java.io.File;
import java.util.List;

import yin.deng.dyrequestutils.http.LogUtils;
import yin.deng.superbase.activity.SuperBaseActivity;
import yin.deng.superbase.activity.permission.PermissionListener;


/**
 * 更新应用版本管理器
 */
public class UpdateManager {
   private static UpdateManager updateManager;
    private static String dirPath="";
    private String apkPath;
    private ProgressDialog progressDialog;

    private UpdateManager() {
    }

    public static UpdateManager getInstance(){
        if(updateManager==null){
            updateManager=new UpdateManager();
        }
        return updateManager;
    }



    public String getApkPath() {
        return apkPath;
    }


    /**
     * 请求权限并开始下载
      * @param updateInfo
     * @param activity
     * @param progressLogo
     */
    public void requestPermissionAndDownLoadStart(final UpdateInfo updateInfo, final SuperBaseActivity activity, final int progressLogo) {
        dirPath = Utils.getRootDirPath(activity)+ File.separator;
        String[]permissions={Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        activity.requestRunTimePermission(permissions, new PermissionListener() {
            @Override
            public void onGranted() {
                LogUtils.i("权限获取成功");
                downLoadStart(updateInfo,activity,progressLogo);
            }

            @Override
            public void onGranted(List<String> grantedPermission) {

            }

            @Override
            public void onDenied(List<String> deniedPermission) {
                activity.showTs("请授予应用读取/写入内部存储相关权限");
            }
        });
    }

    /**
     * 请求权限并开始下载
     * @param updateInfo
     * @param activity
     * @param PermissionListener
     */
    public void requestPermissionAndDownLoadStart(final SuperBaseActivity activity,PermissionListener permissionListener) {
        dirPath = Utils.getRootDirPath(activity)+ File.separator;
        String[]permissions={Manifest.permission.WRITE_EXTERNAL_STORAGE};
        activity.requestRunTimePermission(permissions,permissionListener);
    }


    /**
     * 此处开始真正的下载操作
     * @param updateInfo
     * @param activity
     */
    public void downLoadStart(final UpdateInfo updateInfo, final SuperBaseActivity activity, final int appLogo) {
        String saveName=activity.getResources().getString(R.string.app_name)+"_"+updateInfo.getName()+".apk";
        apkPath=dirPath+saveName;
        PRDownloader.download(updateInfo.getUrl(),dirPath,
                saveName)
        .build().setOnStartOrResumeListener(new OnStartOrResumeListener() {
            @Override
            public void onStartOrResume() {
                showProgressDialog(activity,updateInfo,appLogo);
            }
        }).setOnProgressListener(new OnProgressListener() {
            @Override
            public void onProgress(Progress progress) {
                updateProgress(progress);
                LogUtils.i("安装包下载中："+progress.currentBytes+"bytes/"+progress.totalBytes+"bytes");
            }
        }).start(new OnDownloadListener() {
            @Override
            public void onDownloadComplete() {
                if(progressDialog!=null&&progressDialog.isShowing()){
                    progressDialog.dismiss();
                    progressDialog=null;
                }
                installApk(activity,apkPath);
            }

            @Override
            public void onError(Error error) {
                activity.showTs("下载失败:"+error.getServerErrorMessage());
            }
        });
    }

    public void showProgressDialog(SuperBaseActivity activity, UpdateInfo updateInfo,int appLogo) {
        if(progressDialog==null) {
            progressDialog = new ProgressDialog(activity);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setTitle("正在下载 v" + updateInfo.getName()+"版本");
            progressDialog.setIcon(appLogo);
            progressDialog.setMessage(updateInfo.getMemo());
            progressDialog.setCancelable(false);// 能够返回
            progressDialog.setCanceledOnTouchOutside(false);// 点击外部返回
            progressDialog.setButton(ProgressDialog.BUTTON_POSITIVE,"后台下载",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            progressDialog=null;
                        }
                    });
        }
        progressDialog.show();
    }

    public void updateProgress(Progress progress){
        if(progressDialog!=null&&progressDialog.isShowing()){
            progressDialog.setProgress((int) (progress.currentBytes/1024));
            progressDialog.setMax((int) (progress.totalBytes/1024));
        }
    }

    /**
     * 安装已经下载好的apk
     */
    public void installApk(Activity activity,String filePath) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        File file = new File(filePath);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 24) {//大于7.0使用此方法
            Uri apkUri = FileProvider.getUriForFile(activity, activity.getPackageName()+".fileProvider", file);///-----ide文件提供者名
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        }else {//小于7.0就简单了
            // 由于没有在Activity环境下启动Activity,设置下面的标签
            intent.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
        }
        activity.startActivity(intent);
    }

}
