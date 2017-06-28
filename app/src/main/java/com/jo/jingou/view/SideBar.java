package com.jo.jingou.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import caesar.feng.framework.utils.Utility;

public class SideBar extends View {

    private TextView textDialog;
    private OnSelectChangeListener changeListener;

    // 26个字母
    public static String[] b = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y",
            "Z"};
    private int choose = -1;// 选中
    private Paint paint = new Paint();

    public SideBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SideBar(Context context) {
        super(context);
    }

    public void setTextView(TextView textView) {
        this.textDialog = textView;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int singleHeight = height / b.length;
        for (int i = 0; i < b.length; i++) {
            paint.setColor(Color.rgb(102, 102, 102));
            //paint.setTypeface(Typeface.DEFAULT_BOLD);
            paint.setAntiAlias(true);
            paint.setTextSize(Utility.dip2px(getContext(), 10));
            // 选中状态
            if (i == choose) {
                paint.setColor(Color.parseColor("#3399ff"));
                paint.setFakeBoldText(true);
            }
            float x = width / 2 - paint.measureText(b[i]) / 2;
            float y = singleHeight * i + singleHeight;
            canvas.drawText(b[i], x, y, paint);
            paint.reset();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int action = event.getAction();
        final float y = event.getY();// 点击y坐标
        int c = (int) (y / getHeight() * b.length);
        switch (action) {
            case MotionEvent.ACTION_UP:
//                setBackgroundResource(R.color.text_hint_color);  //选中时的背景颜色
                choose = -1;
                invalidate();
                if (textDialog != null)
                    textDialog.setVisibility(View.INVISIBLE);
                break;
            default:
//                setBackgroundColor(Color.parseColor("#28000000"));
                if (choose != c) {
                    if (c >= 0 && c < b.length) {
                        if (changeListener != null)
                            changeListener.onSelectChange(b[c]);
                        if (textDialog != null) {
                            textDialog.setText(b[c]);
                            textDialog.setVisibility(View.VISIBLE);
                        }
                        choose = c;
                        invalidate();
                    }
                }
                break;
        }
        return true;
    }

    public void setOnSelectChangeListener(OnSelectChangeListener listener) {
        this.changeListener = listener;
    }

    public interface OnSelectChangeListener {
        void onSelectChange(String letter);
    }
}
