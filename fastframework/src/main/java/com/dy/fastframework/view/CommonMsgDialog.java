package com.dy.fastframework.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dy.fastframework.R;

import yin.deng.normalutils.utils.NoDoubleClickListener;
import yin.deng.normalutils.utils.ScreenUtils;
import yin.deng.normalutils.view.MsgDialog;

public class CommonMsgDialog extends Dialog {
    public ViewHolder holder;

    public CommonMsgDialog(Context context) {
        super(context, R.style.ActionSheetDialogStyle);
        View view = View.inflate(context, R.layout.progress_dialog_normal, null);
        setContentView(view);
        //获取当前Activity所在的窗体
        Window dialogWindow = getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.CENTER);
        //设置动画
        dialogWindow.setWindowAnimations(R.style.ActionSheetDialogAnimation);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = ScreenUtils.dipTopx(context, 300f);
        //       将属性设置给窗体
        dialogWindow.setAttributes(lp);
        holder = new ViewHolder(view);
        holder.tvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public void showMsg(String msg){
        holder.tvContent.setText(msg);
        holder.tvSure.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                dismiss();
            }
        });
        show();
    }

    public void showMsg(String msg, View.OnClickListener onSureClick){
        holder.tvContent.setText(msg);
        holder.tvSure.setOnClickListener(onSureClick);
        show();
    }

    public void showMsgWithCancle(String msg, View.OnClickListener onSureClick,View.OnClickListener onCancleClick){
        holder.tvContent.setText(msg);
        holder.tvSure.setOnClickListener(onSureClick);
        holder.tvCancle.setVisibility(View.VISIBLE);
        holder.tvMiddle.setVisibility(View.VISIBLE);
        holder.tvCancle.setOnClickListener(onSureClick);
        holder.tvCancle.setText("取消");
        holder.tvSure.setText("确定");
        show();
    }

    public ViewHolder getHolder() {
        return holder;
    }

    public static class ViewHolder {
        public FrameLayout fmImgAd;
        public ImageView ivImg;
        public ImageView ivClose;
        public LinearLayout llTextContent;
        public TextView tvTitle;
        public LinearLayout llProgress;
        public ProgressBar progressBar;
        public TextView tvProgress;
        public TextView tvContent;
        public LinearLayout llBottom;
        public TextView tvCancle;
        public TextView tvMiddle;
        public TextView tvSure;
        public TextView tvHLine;

        public  ViewHolder(View view) {
            fmImgAd = (FrameLayout) view.findViewById(R.id.fm_img_ad);
            ivImg = (ImageView) view.findViewById(R.id.iv_img);
            ivClose = (ImageView) view.findViewById(R.id.iv_close);
            llTextContent = (LinearLayout) view.findViewById(R.id.ll_text_content);
            tvTitle = (TextView) view.findViewById(R.id.tv_title);
            llProgress = (LinearLayout) view.findViewById(R.id.ll_progress);
            progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
            tvProgress = (TextView) view.findViewById(R.id.tv_progress);
            tvContent = (TextView) view.findViewById(R.id.tv_content);
            llBottom = (LinearLayout) view.findViewById(R.id.ll_bottom);
            tvCancle = (TextView) view.findViewById(R.id.tv_cancle);
            tvMiddle = (TextView) view.findViewById(R.id.tv_middle);
            tvSure = (TextView) view.findViewById(R.id.tv_sure);
            tvHLine = (TextView) view.findViewById(R.id.tv_h_line);
        }

        public View getView(View view,int id){
            return view.findViewById(id);
        }
    }
}
