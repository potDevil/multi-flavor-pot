package com.kotlin.service.bean.wechat;

import android.annotation.SuppressLint;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by fzh on 2018/4/9.
 */

public class WeChatCategoryInfo implements Serializable {

    /**
     * cid : 1
     * name : 时尚
     */

    private String cid;
    private String name;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @SuppressLint("NewApi")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WeChatCategoryInfo other = (WeChatCategoryInfo) obj;
        return Objects.equals(this.name, other.name);
    }
}
