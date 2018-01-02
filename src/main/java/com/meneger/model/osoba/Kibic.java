package com.meneger.model.osoba;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "KIBICE")
public class Kibic extends Osoba {

    @Column(name = "ZNIZKA", columnDefinition = "TINYINT(1)")
    private boolean znizka;

    public boolean isZnizka() {
        return znizka;
    }

    public void setZnizka(boolean znizka) {
        this.znizka = znizka;
    }
}
