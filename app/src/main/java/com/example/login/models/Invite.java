package com.example.login.models;

import java.util.Date;

public class Invite {

    static public int SITUACAO_PENDENTE = 1;
    static public int SITUACAO_CONFIRMADO = 2;
    static public int SITUACAO_CANCELADO = 3;

    private int id;
    private Barbershop barbershop;
    private User user;
    private User barber;
    private Date schedule;
    private int situation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public boolean isSituacaoPendente() {
        return this.getSituation() == Invite.SITUACAO_PENDENTE;
    }

    public boolean isSituacaoConfirmado() {
        return this.getSituation() == Invite.SITUACAO_CONFIRMADO;
    }

    public boolean isSituacaoCancelado() {
        return this.getSituation() == Invite.SITUACAO_CANCELADO;
    }

}
