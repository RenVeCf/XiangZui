package com.ipd.xiangzui.common.view;

/**
 * Description :RecyclerView 线性布局间距
 * Author : rmy
 * Email : 942685687@qq.com
 * Time : 2018/loading1/12
 */

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * 设置RecyclerView 线性 间隔
 * Created by john on 17-loading1-5.
 */

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

    private int type;
    private int space;

    public SpacesItemDecoration(int type, int space) {
        this.type = type;
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        switch (type) {
            case 1:
                outRect.left = space;
                outRect.right = space;
                outRect.bottom = space;
                break;
            case 2:
                outRect.top = space;
                break;
        }

//        // Add top margin only for the first item to avoid double space between items
//        if (parent.getChildLayoutPosition(view) == 0) {
//            outRect.top = space;
//        } else {
//            outRect.top = 0;
//        }
    }
}
