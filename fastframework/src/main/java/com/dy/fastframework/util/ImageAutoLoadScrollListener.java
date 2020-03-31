package com.dy.fastframework.util;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE;
import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_SETTLING;

public class ImageAutoLoadScrollListener extends RecyclerView.OnScrollListener {
  Context context;
  public ImageAutoLoadScrollListener(Context context) {
    this.context=context;
  }

  @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
      super.onScrolled(recyclerView, dx, dy);
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
      super.onScrollStateChanged(recyclerView, newState);
      switch (newState){
        case SCROLL_STATE_IDLE: // The RecyclerView is not currently scrolling.
          //当屏幕停止滚动，加载图片
          try {
            if(context != null) Glide.with(context).resumeRequests();
          }
          catch (Exception e) {
            e.printStackTrace();
          }
          break;
        case SCROLL_STATE_DRAGGING: // The RecyclerView is currently being dragged by outside input such as user touch input.
          //当屏幕滚动且用户使用的触碰或手指还在屏幕上，停止加载图片
          try {
            if(context != null) Glide.with(context).pauseRequests();
          }
          catch (Exception e) {
            e.printStackTrace();
          }
          break;
        case SCROLL_STATE_SETTLING: // The RecyclerView is currently animating to a final position while not under outside control.
          //由于用户的操作，屏幕产生惯性滑动，停止加载图片
          try {
            if(context != null) Glide.with(context).pauseRequests();
          }
          catch (Exception e) {
            e.printStackTrace();
          }
          break;
      }
  }
}
