package com.meneger.model.mecz;

import com.meneger.model.boisko.Boisko;

import javax.persistence.*;

@Entity
@Table(name = "MECZE")
public class Mecz {

    @Id
    @Column(name = "ID")
    private Integer id;


    @ManyToOne
    @JoinColumn(name = "BOISKO_ID")
    private Boisko boisko;

    public Boisko getBoisko() {
        return boisko;
    }

    public void setBoisko(Boisko boisko) {
        this.boisko = boisko;
    }
}
