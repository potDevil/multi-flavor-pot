package com.example.fuzhihuangcom.kotlin.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fuzhihuangcom.kotlin.R;
import com.example.fuzhihuangcom.kotlin.service.bean.wechat.WeChatCategoryInfo;
import com.orhanobut.hawk.Hawk;

import java.util.List;

public class UserAdapter extends BaseAdapter {

    private final static String TAG = "UserAdapter";

    private boolean isItemShow = false;
    private Context context;
    private int holdPosition;
    private boolean isChanged = false;
    boolean isVisible = true;
    public List<WeChatCategoryInfo> categoryInfoList;
    private TextView item_text;
    public int remove_position = -1;

    public UserAdapter(Context context, List<WeChatCategoryInfo> categoryInfoList) {
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
        WeChatCategoryInfo categoryInfo = getItem(position);
        item_text.setText(categoryInfo.getName());
        if ((position == 0) || (position == 1)) {
            item_text.setEnabled(false);
        }
        if (isChanged && (position == holdPosition) && !isItemShow) {
            item_text.setText("");
            item_text.setSelected(true);
            item_text.setEnabled(true);
            isChanged = false;
        }
        if (!isVisible && (position == -1 + categoryInfoList.size())) {
            item_text.setText("");
            item_text.setSelected(true);
            item_text.setEnabled(true);
        }
        if (remove_position == position) {
            item_text.setText("");
        }
        return view;
    }


    public void addItem(WeChatCategoryInfo categoryInfo) {
        categoryInfoList.add(categoryInfo);
        notifyDataSetChanged();
    }


    public void exchange(int dragPostion, int dropPostion) {
        holdPosition = dropPostion;
        WeChatCategoryInfo dragItem = getItem(dragPostion);
        Log.d(TAG, "startPostion=" + dragPostion + ";endPosition=" + dropPostion);
        if (dragPostion < dropPostion) {
            categoryInfoList.add(dropPostion + 1, dragItem);
            categoryInfoList.remove(dragPostion);
        } else {
            categoryInfoList.add(dropPostion, dragItem);
            categoryInfoList.remove(dragPostion + 1);
        }
        isChanged = true;
        // 保存移动后的位置
        Hawk.put("WECHAT_USER_INFO",categoryInfoList);
        notifyDataSetChanged();
    }


    public List<WeChatCategoryInfo> getCategoryInfoList() {
        return categoryInfoList;
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
    }


    public boolean isVisible() {
        return isVisible;
    }


    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public void setShowDropItem(boolean show) {
        isItemShow = show;
    }
}