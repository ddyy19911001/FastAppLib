package com.dy.fastdemo;

import java.util.ArrayList;
import java.util.List;

public class UserInfo {
    private String userName;
    private int age;
    private String userSex;
    private List<String> userLikes=new ArrayList<>();

    public UserInfo(String userName, int age, String userSex, List<String> userLikes) {
        this.userName = userName;
        this.age = age;
        this.userSex = userSex;
        this.userLikes = userLikes;
    }

    public UserInfo() {
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                ", userSex='" + userSex + '\'' +
                ", userLikes=" + userLikes +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public List<String> getUserLikes() {
        return userLikes;
    }

    public void setUserLikes(List<String> userLikes) {
        this.userLikes = userLikes;
    }
}
