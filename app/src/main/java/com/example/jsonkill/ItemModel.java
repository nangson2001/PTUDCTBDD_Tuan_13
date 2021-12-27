package com.example.jsonkill;

import java.io.Serializable;

public class ItemModel implements Serializable {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String avatar;
    private String userName;
    private String email;
    private String name;
    private String company;
    private String phone;
    private String address;

    public ItemModel(int id, String avatar, String userName, String email, String name, String company, String phone, String address) {
        this.avatar = avatar;
        this.userName = userName;
        this.email = email;
        this.name = name;
        this.company = company;
        this.phone = phone;
        this.address = address;
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
