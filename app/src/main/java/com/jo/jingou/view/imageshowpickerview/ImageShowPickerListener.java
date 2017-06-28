package com.jo.jingou.view.imageshowpickerview;

import android.view.View;

import java.util.List;

/**
 * Author 姚智胜
 * Version V1.0版本
 * Description: picker点击事件监听
 * Date: 2017/4/15
 */

public interface ImageShowPickerListener {

    void addOnClickListener(int remainNum);

    void picOnClickListener(List<ImageShowPickerBean> list, int position, int remainNum,View view);

    void delOnClickListener(int position, int remainNum);
}
