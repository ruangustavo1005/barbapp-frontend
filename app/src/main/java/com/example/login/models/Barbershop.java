package com.example.login.models;

public class Barbershop {

    private int id;
    private String name;
    private String phone;
    private String address;
    private User owner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public User getOwner() {
        if (owner == null) {
            owner = new User();
        }
        return owner;
    }

    public void setOwner(User user) {
        this.owner = user;
    }
}
