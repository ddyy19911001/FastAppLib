package com.dy.fastframework.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.dy.fastframework.R;


/**
 *  *<cn.carbs.android.maxheightview.library.MaxHeightView
 *  *      android:layout_width="match_parent"
 *  *      android:layout_height="wrap_content"
 *  *      app:mhv_HeightRatio="0.3">
 *  *
 *  *      <ScrollView
 *  *          android:layout_width="match_parent"
 *  *          android:layout_height="wrap_content">
 *  *
 *  *          <LinearLayout
 *  *              android:layout_width="match_parent"
 *  *              android:layout_height="wrap_content"
 *  *              android:orientation="vertical">
 *  *
 *  *              // add your content view here
 *  *
 *  *          </LinearLayout>
 *  *      </ScrollView>
 *  *  </cn.carbs.android.maxheightview.library.MaxHeightView>
 *
 *  可设置最大可显示高度
 */

public class MaxHeightView extends FrameLayout {

    private static final float DEFAULT_MAX_RATIO_WITHOUT_ARGU = 0.6f;
    private static final float DEFAULT_MAX_RATIO = 0f;
    private static final float DEFAULT_MAX_DIMEN = 0f;

    private float mMaxRatio = DEFAULT_MAX_RATIO;
    private float mMaxDimen = DEFAULT_MAX_DIMEN;
    private float mMaxHeight = 0;

    public MaxHeightView(Context context) {
        super(context);
        init();
    }

    public MaxHeightView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        init();
    }

    public MaxHeightView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initAttrs(context, attrs);
        init();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.MaxHeightView);

        final int count = a.getIndexCount();
        for (int i = 0; i < count; ++i) {
            int attr = a.getIndex(i);
            if(attr == R.styleable.MaxHeightView_mhv_HeightRatio){
                mMaxRatio = a.getFloat(attr, DEFAULT_MAX_RATIO);
            }else if(attr == R.styleable.MaxHeightView_mhv_HeightDimen){
                mMaxDimen = a.getDimension(attr, DEFAULT_MAX_DIMEN);
            }
        }
        a.recycle();
    }

    private void init(){
        if(mMaxDimen <= 0 && mMaxRatio <= 0){
            mMaxHeight = DEFAULT_MAX_RATIO_WITHOUT_ARGU * (float) getScreenHeight(getContext());
        } else if (mMaxDimen <= 0 && mMaxRatio > 0) {
            mMaxHeight = mMaxRatio * (float) getScreenHeight(getContext());
        } else if(mMaxDimen > 0 && mMaxRatio <= 0) {
            mMaxHeight = mMaxDimen;
        } else{
            mMaxHeight = Math.min(mMaxDimen, mMaxRatio * (float) getScreenHeight(getContext()));
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (heightMode == MeasureSpec.EXACTLY) {
            heightSize = heightSize <= mMaxHeight ? heightSize
                    : (int) mMaxHeight;
        }

        if (heightMode == MeasureSpec.UNSPECIFIED) {
            heightSize = heightSize <= mMaxHeight ? heightSize
                    : (int) mMaxHeight;
        }
        if (heightMode == MeasureSpec.AT_MOST) {
            heightSize = heightSize <= mMaxHeight ? heightSize
                    : (int) mMaxHeight;
        }
        int maxHeightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize,
                heightMode);
        super.onMeasure(widthMeasureSpec, maxHeightMeasureSpec);
    }

    private int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getHeight();
    }

}
