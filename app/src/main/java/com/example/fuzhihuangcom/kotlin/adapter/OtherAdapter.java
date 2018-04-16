package com.example.fuzhihuangcom.kotlin.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fuzhihuangcom.kotlin.R;
import com.example.fuzhihuangcom.kotlin.service.bean.wechat.WeChatCategoryInfo;

import java.util.List;

public class OtherAdapter extends BaseAdapter {
    private Context context;
    public List<WeChatCategoryInfo> categoryInfoList;
    private TextView item_text;
    boolean isVisible = true;
    public int remove_position = -1;

    public OtherAdapter(Context context, List<WeChatCategoryInfo> categoryInfoList) {
        this.context = context;
        this.categoryInfoList = categoryInfoList;
    }

    @Override
    public int getCount() {
        return categoryInfoList == null ? 0 : categoryInfoList.size();
    }

    @Override
    public WeChatCategoryInfo getItem(int position) {
        if (categoryInfoList != null && categoryInfoList.size() != 0) {
            return categoryInfoList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.subscribe_category_item, null);
        item_text = view.findViewById(R.id.text_item);
        WeChatCategoryInfo channel = getItem(position);
        item_text.setText(channel.getName());
        if (!isVisible && (position == -1 + categoryInfoList.size())) {
            item_text.setText("");
        }
        if (remove_position == position) {
            item_text.setText("");
        }
        return view;
    }


    public List<WeChatCategoryInfo> getCategoryInfoList() {
        return categoryInfoList;
    }


    public void addItem(WeChatCategoryInfo categoryInfo) {
        categoryInfoList.add(categoryInfo);
        notifyDataSetChanged();
    }


    public void setRemove(int position) {
        remove_position = position;
        notifyDataSetChanged();
    }


    public void remove() {
        categoryInfoList.remove(remove_position);
        remove_position = -1;
        notifyDataSetChanged();
    }

    public void setListDate(List<WeChatCategoryInfo> list) {
        categoryInfoList = list;
        notifyDataSetChanged();
    }


    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}