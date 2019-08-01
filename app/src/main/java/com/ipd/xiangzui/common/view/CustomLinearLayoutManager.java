package com.ipd.xiangzui.common.view;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * Description ：RecyclerView和ScrollView嵌套显示不全等问题解决方法
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/4/29.
 *
 * Test:
 * 布局中 ScrollView替换为NestedScrollView
 * //设置RecyclerView方向和是否反转
 * CustomLinearLayoutManager NotUseList = new CustomLinearLayoutManager(this);
 * NotUseList.setScrollEnabled(false);
 * {{RecyclerView}}.setLayoutManager(NotUseList);
 * {{RecyclerView}}.setHasFixedSize(true); //item如果一样的大小，可以设置为true让RecyclerView避免重新计算大小
 * {{RecyclerView}}.setItemAnimator(new DefaultItemAnimator()); //默认动画
 */
public class CustomLinearLayoutManager extends LinearLayoutManager {
    private boolean isScrollEnabled = true;

    public CustomLinearLayoutManager(Context context) {
        super(context);
    }

    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
        return isScrollEnabled && super.canScrollVertically();
    }
}
