package com.example.login.models;

public class Product {

    private int id;
    private String name;
    private float value;
    private Barbershop barbershop;

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

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Barbershop getBarbershop() {
        if (barbershop == null) {
            barbershop = new Barbershop();
        }
        return barbershop;
    }

    public void setBarbershop(Barbershop barbershop) {
        this.barbershop = barbershop;
    }
}
