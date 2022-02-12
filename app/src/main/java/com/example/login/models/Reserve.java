package com.example.login.models;

import java.util.Date;

public class Reserve {

    public static final int SITUACAO_ABERTO = 1,
                            SITUACAO_CONFIRMADO = 2,
                            SITUACAO_CANCELADO = 3;

    private Date schedule;
    private int situation;
    private Barbershop barbershop;
    private User user;
    private User barber;

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

    public Barbershop getBarbershop() {
        if (barbershop == null) {
            barbershop = new Barbershop();
        }
        return barbershop;
    }

    public void setBarbershop(Barbershop barbershop) {
        this.barbershop = barbershop;
    }

    public User getUser() {
        if (user == null) {
            user = new User();
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getBarber() {
        if (barber == null) {
            barber = new User();
        }
        return barber;
    }

    public void setBarber(User barber) {
        this.barber = barber;
    }

    public String getDescriptionSituation() {
        switch (this.getSituation()) {
            case SITUACAO_ABERTO: return "Aberto";
            case SITUACAO_CONFIRMADO: return "Confirmado";
            case SITUACAO_CANCELADO: return "Cancelado";
            default: return null;
        }
    }
}
