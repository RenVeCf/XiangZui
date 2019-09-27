package com.ipd.xiangzui.common.view;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.xuexiang.xui.widget.textview.marqueen.MarqueeFactory;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/7/5.
 */
public class SimpleNoticeMFs extends MarqueeFactory<TextView, CharSequence> {
    private LayoutInflater inflater;

    public SimpleNoticeMFs(Context context) {
        super(context);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public TextView generateMarqueeItemView(CharSequence data) {
        TextView view = (TextView) inflater.inflate(com.xuexiang.xui.R.layout.marqueen_layout_notice_item, null);
        view.setTextColor(Color.parseColor("#FFAAAAAA"));
        view.setTextSize(12);
        view.setText(data);
        return view;
    }
}
