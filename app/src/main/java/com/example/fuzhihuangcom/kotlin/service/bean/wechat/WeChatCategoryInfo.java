package com.example.fuzhihuangcom.kotlin.service.bean.wechat;

import java.io.Serializable;

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
}
