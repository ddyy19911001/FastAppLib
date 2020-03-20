package com.dy.fastdemo;

public class SplanshInfo {

    /**
     * code : 200
     * msg : success
     * time : 1581586712
     * data : {"id":2,"title":"2","img":"http://6vdapi.hzyyd.top/storage/img/2020-02-13/11-47-56-5e44c6ecd8460.jpg","duration":3,"android_action_method":2,"android_action":"http://baidu.com","ios_action_method":1,"ios_action":"http://hao123.com","status":1,"created_at":"2020-02-13 11:48:01","updated_at":"2020-02-13 11:48:01"}
     */

    private int code;
    private String msg;
    private int time;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 2
         * title : 2
         * img : http://6vdapi.hzyyd.top/storage/img/2020-02-13/11-47-56-5e44c6ecd8460.jpg
         * duration : 3
         * android_action_method : 2
         * android_action : http://baidu.com
         * ios_action_method : 1
         * ios_action : http://hao123.com
         * status : 1
         * created_at : 2020-02-13 11:48:01
         * updated_at : 2020-02-13 11:48:01
         */

        private int id;
        private String title;
        private String img;
        private int duration;
        private int android_action_method;
        private String android_action;
        private int ios_action_method;
        private String ios_action;
        private int status;
        private String created_at;
        private String updated_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getAndroid_action_method() {
            return android_action_method;
        }

        public void setAndroid_action_method(int android_action_method) {
            this.android_action_method = android_action_method;
        }

        public String getAndroid_action() {
            return android_action;
        }

        public void setAndroid_action(String android_action) {
            this.android_action = android_action;
        }

        public int getIos_action_method() {
            return ios_action_method;
        }

        public void setIos_action_method(int ios_action_method) {
            this.ios_action_method = ios_action_method;
        }

        public String getIos_action() {
            return ios_action;
        }

        public void setIos_action(String ios_action) {
            this.ios_action = ios_action;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }
    }
}
