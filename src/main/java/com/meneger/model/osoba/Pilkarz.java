package com.meneger.model.osoba;

import com.meneger.model.druzyna.Druzyna;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PILKARZE")
public class Pilkarz extends Osoba{
    private static final int NAME_MAX_LENGTH = 30;
    private static final int PESEL_LENGTH = 11;

    @ManyToOne
    @JoinColumn(name = "DRUZYNA_ID")
    private Druzyna druzyna;

    @Column(name = "NUMER")
    private Integer numer;

    @Enumerated(EnumType.STRING)
    private Pozycja pozycja;

    public Druzyna getDruzyna() {
        return druzyna;
    }

    public void setDruzyna(Druzyna druzyna) {
        this.druzyna = druzyna;
    }

    public Integer getNumer() {
        return numer;
    }

    public void setNumer(Integer numer) {
        this.numer = numer;
    }

    public Pozycja getPozycja() {
        return pozycja;
    }

    public void setPozycja(Pozycja pozycja) {
        this.pozycja = pozycja;
    }

    @Override
    public String toString() {
        return "Pilkarz{" +
                "druzyna=" + druzyna +
                ", numer=" + numer +
                ", pozycja=" + pozycja +
                "} " + super.toString();
    }
}
