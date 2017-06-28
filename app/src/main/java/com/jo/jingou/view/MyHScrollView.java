package com.jo.jingou.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

/**
 * Created by dfyu on 2016/4/11.
 */
public class MyHScrollView extends HorizontalScrollView {


    private int zeroPoint = -1;
    private int xValue = -1;
    private int oldxValue = -1;
    private int wigth = -1;
    private int scroll_status = ScrollView.FOCUS_LEFT;
    private OnScrollListener onScrollListener;

    public MyHScrollView(Context context) {
        super(context);
    }

    public MyHScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyHScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @SuppressLint("NewApi")
    public MyHScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        this.xValue = l;
        this.oldxValue = oldl;
        if (oldl > wigth) this.wigth = oldl;

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                if (zeroPoint != -1 && xValue != -1)
                    if (oldxValue <= xValue)
                        if (xValue > zeroPoint) scroll(ScrollView.FOCUS_RIGHT);
                        else scroll(ScrollView.FOCUS_LEFT);
                    else if (oldxValue > xValue)
                        if ((wigth - xValue) > zeroPoint) scroll(ScrollView.FOCUS_LEFT);
                        else scroll(ScrollView.FOCUS_RIGHT);
                break;
        }
        return super.onTouchEvent(ev);
    }

    //自动滑动到指定位置
    public void scroll(final int focus) {
        this.post(new Runnable() {
            public void run() {
                MyHScrollView.this.fullScroll(focus);
                scroll_status = focus;
                if (onScrollListener != null) {
                    onScrollListener.onScroll(scroll_status);
                }
            }
        });
    }

    //设置滑动零界值
    public void setScrollZeroPoint(int zeroPoint) {
        this.zeroPoint = zeroPoint;
    }


    public interface OnScrollListener {
        public void onScroll(final int focus);
    }

}
