package com.dy.fastframework.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatImageView;


/**
 * 点击有反馈效果的imageView
 */
public class ClickImageView extends AppCompatImageView {
    public ClickImageView(Context context) {
        super(context);
    }
    public ClickImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public ClickImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                this.setColorFilter(0x99000000);
                return true;
            case MotionEvent.ACTION_UP:
                performClick();
            case MotionEvent.ACTION_CANCEL:
                this.setColorFilter(null);
                break;
        }
        return super.onTouchEvent(event);
    }
}

