package com.example.login.models;

import java.util.Date;

public class Invite {

    private Barbershop barbershop;
    private User user;
    private User barber;
    private Date schedule;
    private int situation;

    public Barbershop getBarbershop() {
        if (this.barbershop == null) {
            this.barbershop = new Barbershop();
        }
        return this.barbershop;
    }

    public void setBarbershop(Barbershop barbershop) {
        this.barbershop = barbershop;
    }

    public User getUser() {
        if (this.user == null) {
            this.user = new User();
        }
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getBarber() {
        if (this.barber == null) {
            this.barber = new User();
        }
        return this.barber;
    }

    public void setBarber(User barber) {
        this.barber = barber;
    }

    public Date getSchedule() {
        return schedule;
    }

    public void setSchedule(Date schedule) {
        this.schedule = schedule;
    }

    public int getSituation() {
        return situation;
    }

    public void setSituation(int situation) {
        this.situation = situation;
    }
}
