package com.dy.fastframework.update;

import java.io.Serializable;

public class UpdateInfo implements Serializable {

    /**
     * name : 1.0.2
     * type : 0
     * version : 2
     * url : https://img.780360.com/update/signal/signal.apk
     * memo : 更新
     */

    private String name;
    private int type;
    private int version;
    private String url;
    private String memo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
