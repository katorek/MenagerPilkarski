package com.meneger.model.boisko;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "STADIONY")
public class Stadion extends Boisko{

    @Column(name = "CHRONI_PRZED_DESZCZEM", columnDefinition = "TINYINT(1)")
    private boolean chroniPrzedDeszczem;

    public boolean isChroniPrzedDeszczem() {
        return chroniPrzedDeszczem;
    }

    public void setChroniPrzedDeszczem(boolean chroniPrzedDeszczem) {
        this.chroniPrzedDeszczem = chroniPrzedDeszczem;
    }
}
